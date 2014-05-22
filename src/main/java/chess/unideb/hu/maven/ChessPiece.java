package chess.unideb.hu.maven;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ChessPiece {
	private static Logger logger = LoggerFactory.getLogger(ChessPiece.class);
	static final String empty = "EMPTY";
	static String[][] chessPieces = new String[8][8];
	static String[][] chessPiecesTypes = new String[8][8];
	static Integer[][][] chessPiecesPositions = new Integer[8][8][2];
	private static String filename;

	static enum typeOfPiece {
		DARK, LIGHT, NONE;
	}

	public ChessPiece(String fileName) {
		setFileName(fileName);
		for (int i = 0; i < chessPieces.length; i++) {
			for (int j = 0; j < chessPieces.length; j++) {
				chessPieces[i][j] = empty;
				chessPiecesTypes[i][j] = typeOfPiece.NONE.toString();
			}
		}

		for (int i = 0; i < chessPiecesPositions.length; i++) {
			for (int j = 0; j < chessPiecesPositions.length; j++) {
				chessPiecesPositions[i][j][0] = -1;
				chessPiecesPositions[i][j][1] = -1;
			}
		}
		readXml(filename);

	}

	private void readXml(String fileName) {
		try {
			
			File file = new File("SavedGames"+File.separator+fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			logger.info("Reading xml file: " + getFileName() + "...");
			
			Node node = doc.getElementsByTagName("activeSide").item(0);
			Element e = (Element) node;
			if(e.getTextContent().compareTo("true") == 0)
				Game.setPlayer(true);
			else
				Game.setPlayer(false);
			
			
			
			NodeList pieceList = doc.getElementsByTagName("piece");
			int pieceCounterDark = 0;
			for(int i = 0;i < pieceList.getLength();i++)
	    	{
	    		Node Nodes = pieceList.item(i);     		
	    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
	        	{
	        		Element element = (Element) Nodes.getParentNode();
	        		if(element.getAttribute("id").toString().compareTo("Dark") == 0)
	        			pieceCounterDark++;  			
	        	}
	    	}
			
			
			for(int i = 0; i < pieceCounterDark; i++) {
				Node nod = pieceList.item(i);
				Element el = (Element) nod;
				int r = Integer.parseInt(el
						.getElementsByTagName("row").item(0).getTextContent());;
				int c = Integer.parseInt(el
						.getElementsByTagName("column").item(0).getTextContent());;
				
				chessPieces[r][c] = el.getElementsByTagName("name").item(0)
						.getTextContent();
				chessPiecesPositions[r][c][0] = Integer.parseInt(el
						.getElementsByTagName("row").item(0).getTextContent());
				chessPiecesPositions[r][c][1] = Integer.parseInt(el
						.getElementsByTagName("column").item(0)
						.getTextContent());
				chessPiecesTypes[r][c] = typeOfPiece.DARK.toString();
			}
			
			int pieceCounterLight = 0;
			for(int i = 0;i < pieceList.getLength();i++)
	    	{
	    		Node Nodes = pieceList.item(i);     		
	    		if ( Nodes.getNodeType() == Node.ELEMENT_NODE)
	        	{
	        		Element element = (Element) Nodes.getParentNode();
	        		if(element.getAttribute("id").toString().compareTo("Light") == 0) {
	        			pieceCounterLight++;
	        			
	        		}
	        	}	   
	    	}
			
			for(int i = pieceCounterDark; i < pieceCounterDark + pieceCounterLight; i++) {
				Node nod = pieceList.item(i);
				Element el = (Element) nod;
				int r = Integer.parseInt(el
						.getElementsByTagName("row").item(0).getTextContent());
				int c = Integer.parseInt(el
						.getElementsByTagName("column").item(0).getTextContent());
				
				chessPieces[r][c] = el.getElementsByTagName("name").item(0)
						.getTextContent();
				chessPiecesPositions[r][c][0] = Integer.parseInt(el
						.getElementsByTagName("row").item(0).getTextContent());
				chessPiecesPositions[r][c][1] = Integer.parseInt(el
						.getElementsByTagName("column").item(0)
						.getTextContent());
				chessPiecesTypes[r][c] = typeOfPiece.LIGHT.toString();
			}
			
			logger.info("Xml file was read properly!");
			logger.info("Base matrices were filled successfully!");

		} catch (Exception e) {
			logger.error("Failed to read the xml file");

		}

	}

	public static String getFileName() {
		return filename;
	}
	
	public static void setFileName(String s) {
		ChessPiece.filename = s;
	}
}