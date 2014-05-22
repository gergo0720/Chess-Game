package chess.unideb.hu.maven;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gergo0720
 * Class to display the game
 */
public class GUIchess extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JFileChooser fc = new JFileChooser();
	private static Logger logger = LoggerFactory.getLogger(GUIchess.class);

	private static JButton[][] chessSquares = new JButton[8][8];

	private String[] sideLabel = { "1", "2", "3", "4", "5", "6", "7", "8" };
	private String[] topBottomLabel = { "A", "B", "C", "D", "E", "F", "G", "H" };
	private static int chessPieceSize = 65;

	private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth();
	private static String message = Players.getUserName()
			+ " (light) 's round!";
	private static boolean active = true;

	private static Image newImg;
	private static Image img;

	private JButton newGame;
	private JButton save;
	private JButton load;
	private JButton back;

	private JToolBar toolbar;
	private JPanel statusDetailPanel;
	private JPanel statusMovesPanel;
	private JPanel boardPanel;
	private JPanel statusPanel;
	private JPanel logoPanel;
	private JPanel statusToolBar;
	private JLabel labelLeftCornerTop;
	private JLabel labelTopBottom;
	private JLabel labelRightCornerTop;
	private JLabel sideLabelStart;
	private JLabel sideLabelEnd;
	private JLabel labelLeftCornerBottom;
	private JLabel labelRightCornerBottom;
	private static JLabel labelMessage;
	private static JLabel labelMoves;
	private JButton button;
	private JButton logo;
	private Runtime rt;

	private static Image DarkRook;
	private static Image DarkKnight;
	private static Image DarkBishop;
	private static Image DarkQueen;
	private static Image DarkKing;
	private static Image DarkPawn;
	private static Image LightRook;
	private static Image LightKnight;
	private static Image LightBishop;
	private static Image LightQueen;
	private static Image LightKing;
	private static Image LightPawn;

	private GridLayout grid;
	private int boardPanelWidth = 680;
	private int boardPanelHeight = 700;

	private Process process;

	private String acitvePiece;
	private String messageMoves = "Moves";

	public GUIchess(Game g) {
		initGui();
	}

	private void initGui() {
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout fl = new FlowLayout();
		getContentPane().setLayout(fl);

		rt = Runtime.getRuntime();
		fc = new JFileChooser();
		newGame = new JButton("New game");
		save = new JButton("Save");
		load = new JButton("Load");
		back = new JButton("Undo");
		boardPanel = new JPanel(new BorderLayout());
		logoPanel = new JPanel(new FlowLayout());
		statusPanel = new JPanel(new FlowLayout());
		statusToolBar = new JPanel(new BorderLayout());
		statusDetailPanel = new JPanel(new FlowLayout());
		statusMovesPanel = new JPanel(new FlowLayout());
		labelMessage = new JLabel(message);
		labelMoves = new JLabel(messageMoves);
		toolbar = new JToolBar();
		toolbar.setFloatable(false);
		boardPanel.setPreferredSize(new Dimension((int) screenWidth / 10 * 5,
				(int) screenWidth / 10 * 5));
		statusPanel.setPreferredSize(new Dimension((int) screenWidth / 10 * 3,
				(int) screenWidth / 10 * 5));
		statusToolBar.setPreferredSize(new Dimension(
				(int) screenWidth / 10 * 3, (int) screenWidth / 40));
		statusDetailPanel.setPreferredSize(new Dimension(
				(int) screenWidth / 10 * 3, (int) screenWidth / 40));
		statusMovesPanel.setPreferredSize(new Dimension(
				(int) screenWidth / 10 * 3, (int) screenWidth / 30));
		logoPanel.setPreferredSize(new Dimension((int) screenWidth / 10 * 3,
				(int) screenWidth / 2));
		boardPanel.setBorder(new LineBorder(Color.BLACK, 2));
		boardPanel.setBackground(new Color(0, 0, 100));
		getContentPane().add(boardPanel);
		getContentPane().add(statusPanel);

		pack();
		setLocationRelativeTo(null);
		setResizable(false);

		grid = new GridLayout(0, 10);
		boardPanel.setLayout(grid);

		statusPanel.add(statusToolBar);
		statusToolBar.add(toolbar, BorderLayout.PAGE_START);
		toolbar.add(newGame);
		newGame.addActionListener(this);
		toolbar.addSeparator();
		toolbar.add(back);
		back.addActionListener(this);
		toolbar.addSeparator();
		toolbar.add(save);
		save.addActionListener(this);
		toolbar.addSeparator();
		toolbar.add(load);
		load.addActionListener(this);
		toolbar.addSeparator();

		statusPanel.add(statusDetailPanel);
		statusDetailPanel.add(labelMessage);
		statusPanel.add(statusMovesPanel);
		statusMovesPanel.add(labelMoves);
		statusPanel.add(logoPanel);
		logo = new JButton();
		logo.addActionListener(this);
		logoPanel.add(logo);

		try {
			img = ImageIO.read(getClass().getResource("/chesslogo.png"));
			logo.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			logger.error("Failed to load logo!");
		}

		labelLeftCornerTop = new JLabel();
		labelLeftCornerTop.setText(" ");
		boardPanel.add(labelLeftCornerTop);
		for (int i = 0; i < topBottomLabel.length; i++) {
			labelTopBottom = new JLabel(topBottomLabel[i], JLabel.CENTER);
			labelTopBottom.setForeground(Color.WHITE);
			boardPanel.add(labelTopBottom);
		}
		labelRightCornerTop = new JLabel();
		labelRightCornerTop.setText(" ");
		boardPanel.add(labelRightCornerTop);

		for (int i = 0; i < chessSquares.length; i++) {
			sideLabelStart = new JLabel(sideLabel[i], JLabel.CENTER);
			sideLabelStart.setForeground(Color.WHITE);
			boardPanel.add(sideLabelStart);

			for (int j = 0; j < chessSquares.length; j++) {

				button = new JButton();
				boardPanel.add(button);

				if (i % 2 == 1 && j % 2 == 1 || i % 2 == 0 && j % 2 == 0) {
					button.setBackground(Color.WHITE);
				} else {
					button.setBackground(new Color(0, 0, 100));
				}
				chessSquares[i][j] = button;
			}
			sideLabelEnd = new JLabel(sideLabel[i], JLabel.CENTER);
			sideLabelEnd.setForeground(Color.WHITE);
			boardPanel.add(sideLabelEnd);
		}
		labelLeftCornerBottom = new JLabel();
		labelLeftCornerBottom.setText(" ");
		boardPanel.add(labelLeftCornerBottom);

		for (int i = 0; i < topBottomLabel.length; i++) {
			labelTopBottom = new JLabel(topBottomLabel[i], JLabel.CENTER);
			labelTopBottom.setForeground(Color.WHITE);
			boardPanel.add(labelTopBottom);
		}

		labelRightCornerBottom = new JLabel();
		labelRightCornerBottom.setText(" ");
		boardPanel.add(labelRightCornerBottom);

		fillSquares();
		fillImages();

		for (int i = 0; i < chessSquares.length; i++) {
			for (int j = 0; j < chessSquares.length; j++) {

				chessSquares[i][j].addMouseListener(this);
				chessSquares[i][j].addActionListener((ActionListener) this);
			}
		}
		setVisible(true);
	}

	private void fillImages() {
		try {
			img = ImageIO.read(getClass().getResource("/DarkRookLeft.png"));
			setDarkRook(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/DarkKnightLeft.png"));
			setDarkKnight(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/DarkBishopLeft.png"));
			setDarkBishop(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/DarkQueen.png"));
			setDarkQueen(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/DarkKing.png"));
			setDarkKing(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/DarkPawn1.png"));
			setDarkPawn(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/LightRookLeft.png"));
			setLightRook(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/LightKnightLeft.png"));
			setLightKnight(img.getScaledInstance(chessPieceSize,
					chessPieceSize, java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/LightBishopLeft.png"));
			setLightBishop(img.getScaledInstance(chessPieceSize,
					chessPieceSize, java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/LightQueen.png"));
			setLightQueen(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/LightKing.png"));
			setLightKing(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource("/LightPawn1.png"));
			setLightPawn(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));
		} catch (IOException e) {
			logger.error("Failed to load fillImages()");
		}

	}

	public static void setMovesDisplay(String s) {
		GUIchess.labelMoves.setText(s);
	}

	public static String rowConverter(int row) {
		switch (row) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		case 5:
			return "F";
		case 6:
			return "G";
		case 7:
			return "H";
		}
		return "Failed";
	}

	public static void fillSquares() {
		try {
			for (int i = 0; i < chessSquares.length; i++) {
				for (int j = 0; j < chessSquares.length; j++) {
					if (ChessPiece.chessPiecesPositions[i][j][0] != -1
							&& ChessPiece.chessPiecesPositions[i][j][1] != -1) {
						img = ImageIO.read(GameMain.class.getResource("/"
								+ ChessPiece.chessPieces[i][j].toString()
								+ ".png"));
						newImg = img.getScaledInstance(chessPieceSize,
								chessPieceSize, java.awt.Image.SCALE_SMOOTH);
						chessSquares[ChessPiece.chessPiecesPositions[i][j][0]][ChessPiece.chessPiecesPositions[i][j][1]]
								.setIcon(new ImageIcon(newImg));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Failed to load images!");
		}
	}

	public static void removeIcons() {
		try {
			for (int i = 0; i < chessSquares.length; i++) {
				for (int j = 0; j < chessSquares.length; j++) {
					chessSquares[i][j].setIcon(null);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to load images!");
		}
	}

	public void mousePressed(MouseEvent e) {
		if ((e.getClickCount() == 1 || e.getClickCount() == 2)
				&& !e.isConsumed()) {
			e.consume();

			for (int i = 0; i < chessSquares.length; i++) {
				for (int j = 0; j < chessSquares.length; j++) {

					if (e.getSource() == chessSquares[i][j]) {
						logger.info(ChessPiece.chessPiecesTypes[i][j]);
						paintDefaultSquare();
						if (active) {
							if (ChessPiece.chessPiecesTypes[i][j]
									.compareTo("LIGHT") == 0 && Game.isPlayer()) {
								Game.setMoves(i, j);
								Game.grabPieces(i, j,
										ChessPiece.chessPieces[i][j]);
								acitvePiece = ChessPiece.chessPieces[i][j];
								active = !active;
							} else if (ChessPiece.chessPiecesTypes[i][j]
									.compareTo("DARK") == 0 && !Game.isPlayer()) {
								Game.setMoves(i, j);
								Game.grabPieces(i, j,
										ChessPiece.chessPieces[i][j]);
								acitvePiece = ChessPiece.chessPieces[i][j];
								active = !active;
							}
						} else {
							Game.placePieces(i, j, chessSquares, acitvePiece);
						}
					}
				}
			}
		}

	}

	public static void paintDefaultSquare() {
		for (int k = 0; k < chessSquares.length; k++) {
			for (int l = 0; l < chessSquares.length; l++) {
				if (k % 2 == 1 && l % 2 == 1 || k % 2 == 0 && l % 2 == 0)
					chessSquares[k][l].setBackground(Color.WHITE);
				else
					chessSquares[k][l].setBackground(new Color(0, 0, 100));
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			new saveXml();
		} else if (e.getSource() == newGame) {
			int reply = JOptionPane.showConfirmDialog(this,
					"Are you sure to start a new game?", "New Game",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				saveXml.dir = new File("SavedGames");
				if (!saveXml.dir.exists()) {
					saveXml.dir.mkdir();
					saveXml.setBaseXml();
				} else
					saveXml.setBaseXml();
				removeIcons();
				paintDefaultSquare();
				new ChessPiece("base.xml");
				fillSquares();

			}

		} else if (e.getSource() == load) {
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				removeIcons();
				File file = fc.getSelectedFile();
				new ChessPiece(file.getName());
				fillSquares();
			}
		} else if (e.getSource() == back) {
			removeIcons();
			new ChessPiece(saveXml.getSavedFile());
			fillSquares();
			if (Game.isPlayer())
				Game.setActivePlayer(Players.getUserName() + " (light) ");
			else
				Game.setActivePlayer(Players.getUserName2() + " (dark) ");
			GUIchess.setLabelMessage(Game.getActivePlayer() + " 's round");
		} else if (e.getSource() == logo) {
			openWebsite();
		}
	}

	private void openWebsite() {
		try {
			setProcess(rt.exec("firefox"
					+ " http://www.chess.com/learn-how-to-play-chess"));
		} catch (Exception e1) {
			logger.error("Failed to open website");
		}

	}

	public void setBoardPanelWidth(int w) {
		boardPanelWidth = w;
	}

	public int getBoardPanelWidth() {
		return boardPanelWidth;
	}

	public void setBoardPanelHeight(int h) {
		boardPanelHeight = h;
	}

	public int getBoardPanelHeight() {
		return boardPanelHeight;
	}

	public int getWindowWidth() {
		return (int) this.getBounds().getWidth();
	}

	public int getWindowHeight() {
		return (int) this.getBounds().getHeight();
	}

	public static JButton[][] getChessSquare() {
		return chessSquares;
	}

	public void setMessage(String s) {
		message = s;
	}

	public static Image getNewImage() {
		return newImg;
	}

	public static Image getLightRook() {
		return LightRook;
	}

	public void setLightRook(Image lightRook) {
		LightRook = lightRook;
	}

	public static Image getLightKing() {
		return LightKing;
	}

	public void setLightKing(Image lightKing) {
		LightKing = lightKing;
	}

	public static Image getDarkRook() {
		return DarkRook;
	}

	public void setDarkRook(Image darkRook) {
		DarkRook = darkRook;
	}

	public static Image getDarkKnight() {
		return DarkKnight;
	}

	public void setDarkKnight(Image darkKnight) {
		DarkKnight = darkKnight;
	}

	public static Image getDarkQueen() {
		return DarkQueen;
	}

	public void setDarkQueen(Image darkQueen) {
		DarkQueen = darkQueen;
	}

	public static Image getDarkBishop() {
		return DarkBishop;
	}

	public void setDarkBishop(Image darkBishop) {
		DarkBishop = darkBishop;
	}

	public static Image getDarkKing() {
		return DarkKing;
	}

	public void setDarkKing(Image darkKing) {
		DarkKing = darkKing;
	}

	public static Image getDarkPawn() {
		return DarkPawn;
	}

	public void setDarkPawn(Image darkPawn) {
		DarkPawn = darkPawn;
	}

	public static Image getLightKnight() {
		return LightKnight;
	}

	public void setLightKnight(Image lightKnight) {
		LightKnight = lightKnight;
	}

	public static Image getLightBishop() {
		return LightBishop;
	}

	public void setLightBishop(Image lightBishop) {
		LightBishop = lightBishop;
	}

	public static Image getLightQueen() {
		return LightQueen;
	}

	public void setLightQueen(Image lightQueen) {
		LightQueen = lightQueen;
	}

	public static Image getLightPawn() {
		return LightPawn;
	}

	public void setLightPawn(Image lightPawn) {
		LightPawn = lightPawn;
	}

	public static void setActive(boolean active) {
		GUIchess.active = active;
	}

	public static boolean getActive() {
		return GUIchess.active;
	}

	public static void setLabelMessage(String s) {
		GUIchess.labelMessage.setText(s);
	}

	public static void drawPossibleMoves(boolean[][] possibleMoves,
			JButton[][] chessSquare) {
		for (int i = 0; i < possibleMoves.length; i++) {
			for (int j = 0; j < possibleMoves.length; j++) {
				if (possibleMoves[i][j]) {
					chessSquare[i][j].setBackground(Color.CYAN);
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

}
