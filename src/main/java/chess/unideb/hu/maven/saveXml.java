package chess.unideb.hu.maven;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class saveXml {
	private static Logger logger = LoggerFactory.getLogger(saveXml.class);
	private static StreamResult sr;
	private static String savedFile;
	public static File dir;
	public static Vector<String> chessPiecesDark;
	public static Vector<String> chessPiecesLight;
	
	saveXml(){
		try{
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			Document document = dBuilder.newDocument();
			
			Element rootElement = document.createElement("pieces");
			document.appendChild(rootElement);
			
			Element activeSide = document.createElement("activeSide");
			rootElement.appendChild(activeSide);
			if(Game.isPlayer())
				activeSide.setTextContent("true");
			else
				activeSide.setTextContent("false");
			
			Element typeElementDark = document.createElement("type");
			rootElement.appendChild(typeElementDark);	
			typeElementDark.setAttribute("id", "Dark");
		
			Element typeElementLight = document.createElement("type");
			rootElement.appendChild(typeElementLight);
			typeElementLight.setAttribute("id", "Light");
			
			
			for(int i = 0; i < ChessPiece.chessPieces.length; i++){
				for(int j = 0; j < ChessPiece.chessPieces.length; j++){
					if(ChessPiece.chessPiecesTypes[i][j].compareTo("DARK") == 0){
						Element piece = document.createElement("piece");
						typeElementDark.appendChild(piece);
						Element name = document.createElement("name");
						Element row = document.createElement("row");
						Element column = document.createElement("column");
						piece.appendChild(name);
						piece.appendChild(row);
						piece.appendChild(column);
						
						name.setTextContent(ChessPiece.chessPieces[i][j]);
						row.setTextContent(ChessPiece.chessPiecesPositions[i][j][0].toString());
						column.setTextContent(ChessPiece.chessPiecesPositions[i][j][1].toString());
					}else if(ChessPiece.chessPiecesTypes[i][j].compareTo("LIGHT") == 0){
						Element piece = document.createElement("piece");
						typeElementLight.appendChild(piece);
						Element name = document.createElement("name");
						Element row = document.createElement("row");
						Element column = document.createElement("column");
						piece.appendChild(name);
						piece.appendChild(row);
						piece.appendChild(column);
						
						name.setTextContent(ChessPiece.chessPieces[i][j]);
						row.setTextContent(ChessPiece.chessPiecesPositions[i][j][0].toString());
						column.setTextContent(ChessPiece.chessPiecesPositions[i][j][1].toString());
					}
				}
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			String time = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			savedFile = Players.getUserName()+ "_vs_"+Players.getUserName2()+time+".xml";
			sr = new StreamResult(new File(dir+File.separator+Players.getUserName()+ "_vs_"+Players.getUserName2()+time+".xml"));
			
			t.transform(source, sr);
			logger.info("Play was saved successfully!");
					
		}catch(Exception e){
			logger.error("Failed to create save!");
		}
	}
	
	public static void setBaseXml() {
		try{
		chessPiecesDark = new Vector<String>();
			chessPiecesDark.add("DarkRookRight");
			chessPiecesDark.add("DarkKnightRight");
			chessPiecesDark.add("DarkBishopRight");
			chessPiecesDark.add("DarkQueen");
			chessPiecesDark.add("DarkKing");
			chessPiecesDark.add("DarkBishopLeft");
			chessPiecesDark.add("DarkKnightLeft");
			chessPiecesDark.add("DarkRookLeft");
			chessPiecesDark.add("DarkPawn1");
			chessPiecesDark.add("DarkPawn2");
			chessPiecesDark.add("DarkPawn3");
			chessPiecesDark.add("DarkPawn4");
			chessPiecesDark.add("DarkPawn5");
			chessPiecesDark.add("DarkPawn6");
			chessPiecesDark.add("DarkPawn7");
			chessPiecesDark.add("DarkPawn8");
			
		chessPiecesLight = new Vector<String>();
			chessPiecesLight.add("LightRookLeft");
			chessPiecesLight.add("LightKnightLeft");
			chessPiecesLight.add("LightBishopLeft");
			chessPiecesLight.add("LightQueen");
			chessPiecesLight.add("LightKing");
			chessPiecesLight.add("LightBishopRight");
			chessPiecesLight.add("LightKnightRight");
			chessPiecesLight.add("LightRookRight");
			chessPiecesLight.add("LightPawn8");
			chessPiecesLight.add("LightPawn7");
			chessPiecesLight.add("LightPawn6");
			chessPiecesLight.add("LightPawn5");
			chessPiecesLight.add("LightPawn4");
			chessPiecesLight.add("LightPawn3");
			chessPiecesLight.add("LightPawn2");
			chessPiecesLight.add("LightPawn1");
			
			
		Vector<String> chessPiecesDarkRow = new Vector<String>();
			for(int i = 0; i < 8; i++) {
				chessPiecesDarkRow.add("0");
			}
			for(int i = 0; i < 8; i++) {
				chessPiecesDarkRow.add("1");
			}
			
		Vector<String> chessPiecesLightRow = new Vector<String>();
			for(int i = 0; i < 8; i++) {
				chessPiecesLightRow.add("7");
			}
			for(int i = 0; i < 8; i++) {
				chessPiecesLightRow.add("6");
			}
			
		Vector<String> chessPiecesDarkCol = new Vector<String>();
			for(Integer i = 0; i < 8; i++) {
				chessPiecesDarkCol.add(i.toString());
			}
			for(Integer i = 0; i < 8; i++) {
				chessPiecesDarkCol.add(i.toString());
			}
			
		Vector<String> chessPiecesLightCol = new Vector<String>();
			for(Integer i = 0; i < 8; i++) {
				chessPiecesLightCol.add(i.toString());
			}
			for(Integer i = 0; i < 8; i++) {
				chessPiecesLightCol.add(i.toString());
			}
			
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			Document document = dBuilder.newDocument();
			
			Element rootElement = document.createElement("pieces");
			document.appendChild(rootElement);
			
			Element activeSide = document.createElement("activeSide");
			rootElement.appendChild(activeSide);
			activeSide.setTextContent("true");
			
			Element typeElementDark = document.createElement("type");
			rootElement.appendChild(typeElementDark);	
			typeElementDark.setAttribute("id", "Dark");
		
			Element typeElementLight = document.createElement("type");
			rootElement.appendChild(typeElementLight);
			typeElementLight.setAttribute("id", "Light");
			
			for(int i = 0; i < chessPiecesDark.size(); i++) {
				Element piece = document.createElement("piece");
				typeElementDark.appendChild(piece);
				Element name = document.createElement("name");
				Element row = document.createElement("row");
				Element column = document.createElement("column");
				piece.appendChild(name);
				piece.appendChild(row);
				piece.appendChild(column);
				
				name.setTextContent(chessPiecesDark.elementAt(i));
				row.setTextContent(chessPiecesDarkRow.elementAt(i));
				column.setTextContent(chessPiecesDarkCol.elementAt(i));
			}
			
			for(int i = 0; i < chessPiecesLight.size(); i++) {
				Element piece = document.createElement("piece");
				typeElementLight.appendChild(piece);
				Element name = document.createElement("name");
				Element row = document.createElement("row");
				Element column = document.createElement("column");
				piece.appendChild(name);
				piece.appendChild(row);
				piece.appendChild(column);
				
				name.setTextContent(chessPiecesLight.elementAt(i));
				row.setTextContent(chessPiecesLightRow.elementAt(i));
				column.setTextContent(chessPiecesLightCol.elementAt(i));
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			savedFile = "base.xml";
			sr = new StreamResult(new File(dir+File.separator+savedFile));
			
			t.transform(source, sr);
			logger.info("base.xml was created successfully!");
					
		}catch(Exception e){
			logger.error("Failed to create base.xml!");
		}
		
	}

	public static String getSavedFile() {
		return savedFile;
	}
	
}

