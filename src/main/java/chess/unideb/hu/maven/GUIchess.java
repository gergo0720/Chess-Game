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
 * Class to display the game.
 */
public class GUIchess extends JFrame implements MouseListener, ActionListener {
	
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * File chooser to able to load saved games.
	 */
	private JFileChooser fc = new JFileChooser();
	
	/**
	 * Logger to debug, log information and warnings.
	 */
	private static Logger logger = LoggerFactory.getLogger(GUIchess.class);

	/**
	 * 8x8 matrice to represent the chess board.
	 */
	private static JButton[][] chessSquares = new JButton[8][8];
	
	/**
	 * Label to display row numbers of the chess board.
	 */
	private String[] sideLabel = { "1", "2", "3", "4", "5", "6", "7", "8" };
	
	/**
	 * Label to display column's name of the chess board.
	 */
	private String[] topBottomLabel = { "A", "B", "C", "D", "E", "F", "G", "H" };
	
	/**
	 * Size of the image of chess pieces.
	 */
	private static int chessPieceSize = 65;

	/**
	 * Get the screen width to size the chess board properly.
	 */
	private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth();
	
	/**
	 * String to set the message of {@code labelMessage}.
	 */
	private static String message = Players.getUserName()
			+ " (light) 's round!";
	
	/**
	 * Detect the choosen piece.
	 */
	private static boolean active = true;

	/**
	 * Image to contain resized chess piece images.
	 */
	private static Image newImg;
	
	/**
	 * Image to contain chess piece images.
	 */
	private static Image img;
	
	/**
	 * Button to start a new game.
	 */
	private JButton newGame;
	
	/**
	 * Button to save the current game.
	 */
	private JButton save;
	
	/**
	 * Button to load a saved game.
	 */
	private JButton load;
	
	/**
	 * Button to undo a move.
	 */
	private JButton back;

	/**
	 * Toolbar to store control buttons.
	 */
	private JToolBar toolbar;
	
	/**
	 * Base panel of the game details.
	 */
	private JPanel statusDetailPanel;
	
	/**
	 * Base panel of the {@code labelMoves}.
	 */
	private JPanel statusMovesPanel;
	
	/**
	 * Base panel of the chess board.
	 */
	private JPanel boardPanel;
	
	/**
	 * Base panel of {@code statusDetailPanel}, {@code statusMovePanel}, {@code movesDisplay}, {@code logoPanel}.
	 */
	private JPanel statusPanel;
	
	/**
	 * Base panel of the logo of the game.
	 */
	private JPanel logoPanel;
	
	/**
	 * Base panel of the {@code toolbar}.
	 */
	private JPanel statusToolBar;
	
	/**
	 * Empty string in the left-top corner of the board.
	 */
	private JLabel labelLeftCornerTop;
	
	/**
	 * Label to display top and bottom column ids.
	 */
	private JLabel labelTopBottom;
	
	/**
	 * Empty string of the right-top corner of the board.
	 */
	private JLabel labelRightCornerTop;
	
	/**
	 * Label to display left-side row ids.
	 */
	private JLabel sideLabelStart;
	
	/**
	 * Label to display right-side row ids.
	 */
	private JLabel sideLabelEnd;
	
	/**
	 * Empty string of the left-bottom corner of the board.
	 */
	private JLabel labelLeftCornerBottom;
	
	/**
	 * Empty string of the right-bottom corner of the board.
	 */
	private JLabel labelRightCornerBottom;
	
	/**
	 * Display who is in chess and whose round.
	 */
	private static JLabel labelMessage;
	
	/**
	 * Display the latest move.
	 */
	private static JLabel labelMoves;
	
	/**
	 * Square of the chess board.
	 */
	private JButton button;
	
	/**
	 * Game logo.
	 */
	private JButton logo;
	
	/**
	 * Runtime to system call to open a webpage of chess rules.
	 */
	private Runtime rt;

	/**
	 * Display dark rook image.
	 */
	private static Image DarkRook;
	
	/**
	 * Display dark knight image.
	 */
	private static Image DarkKnight;
	
	/**
	 * Display dark Bishop image.
	 */
	private static Image DarkBishop;
	
	/**
	 * Display dark queen image.
	 */
	private static Image DarkQueen;
	
	/**
	 * Display dark king image.
	 */
	private static Image DarkKing;
	
	/**
	 * Display dark pawn image.
	 */
	private static Image DarkPawn;
	
	/**
	 * Display light rook image.
	 */
	private static Image LightRook;
	
	/**
	 * Display light knight image.
	 */
	private static Image LightKnight;
	
	/**
	 * Display light bishop image.
	 */
	private static Image LightBishop;
	
	/**
	 * Display light queen image.
	 */
	private static Image LightQueen;
	
	/**
	 * Display light king image.
	 */
	private static Image LightKing;
	
	/**
	 * Display light pawn image.
	 */
	private static Image LightPawn;

	/**
	 * Layout of the chess board.
	 */
	private GridLayout grid;
	
	/**
	 * Width of the chess board.
	 */
	private final int boardPanelWidth = 680;
	
	/**
	 * Height of the chess board.
	 */
	private final int boardPanelHeight = 700;

	/**
	 * Start a process to open a webpage of chess rules with command line.
	 */
	private Process process;

	/**
	 * It is a parameter for the game logic to know which piece it should move.
	 */
	private String acitvePiece;
	
	/**
	 * Set default message of {@code labeMoves}.
	 */
	private String messageMoves = "Moves";
	
	/**
	 * Constructor of the GUI.
	 * @param g is the object of game logic.
	 */
	public GUIchess(Game g) {
		initGui();
	}

	/**
	 * Initialize gui.
	 * Set window parameters and handle piece and control buttons.
	 * Draw chess board and fill it with pieces.
	 */
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
			img = ImageIO.read(getClass().getResource(File.separator+"chesslogo.png"));
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
	
	/**
	 * Read piece images from resource.
	 */
	private void fillImages() {
		try {
			img = ImageIO.read(getClass().getResource(File.separator+"DarkRookLeft.png"));
			setDarkRook(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"DarkKnightLeft.png"));
			setDarkKnight(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"DarkBishopLeft.png"));
			setDarkBishop(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"DarkQueen.png"));
			setDarkQueen(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"DarkKing.png"));
			setDarkKing(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"DarkPawn1.png"));
			setDarkPawn(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"LightRookLeft.png"));
			setLightRook(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"LightKnightLeft.png"));
			setLightKnight(img.getScaledInstance(chessPieceSize,
					chessPieceSize, java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"LightBishopLeft.png"));
			setLightBishop(img.getScaledInstance(chessPieceSize,
					chessPieceSize, java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"LightQueen.png"));
			setLightQueen(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"LightKing.png"));
			setLightKing(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));

			img = ImageIO.read(getClass().getResource(File.separator+"LightPawn1.png"));
			setLightPawn(img.getScaledInstance(chessPieceSize, chessPieceSize,
					java.awt.Image.SCALE_SMOOTH));
		} catch (IOException e) {
			logger.error("Failed to load fillImages()");
		}

	}
	
	/**
	 * Set the actual valid move.
	 * @param s is the message that should be set.
	 */
	public static void setMovesDisplay(String s) {
		GUIchess.labelMoves.setText(s);
	}
	
	/**
	 * Convert the row numbers of the chess board to letters.
	 * @param row is the actual row number that should be converted.
	 * @return the converted letter if it was success or return with Failed.
	 */
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

	/**
	 * Put pieces on the board to their default positions.
	 */
	public static void fillSquares() {
		try {
			for (int i = 0; i < chessSquares.length; i++) {
				for (int j = 0; j < chessSquares.length; j++) {
					if (ChessPiece.chessPiecesPositions[i][j][0] != -1
							&& ChessPiece.chessPiecesPositions[i][j][1] != -1) {
						img = ImageIO.read(GameMain.class.getResource(File.separator
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
	
	/**
	 * Remove all the pieces from the chess board.
	 */
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

	/**
	 * Handle the event of selecting a chess piece to move.
	 * @param e pressed event
	 */
	public void mousePressed(MouseEvent e) {
		if ((e.getClickCount() == 1 || e.getClickCount() == 2)
				&& !e.isConsumed()) {
			e.consume();

			for (int i = 0; i < chessSquares.length; i++) {
				for (int j = 0; j < chessSquares.length; j++) {

					if (e.getSource() == chessSquares[i][j]) {
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

	/**
	 * Back up the default conditions of the chess squares, remove settings.
	 */
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
	
	/**
	 * Handle the event of pressing control buttons.
	 * @param e control buttons event
	 */
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

	/**
	 * Open website of the chess rules.
	 */
	private void openWebsite() {
		try {
			setProcess(rt.exec("firefox"
					+ " http://www.chess.com/learn-how-to-play-chess"));
		} catch (Exception e1) {
			logger.error("Failed to open website");
		}

	}

	/**
	 * Chess board width.
	 * @return the width of the chess board.
	 */
	public int getBoardPanelWidth() {
		return boardPanelWidth;
	}

	/**
	 * Chess board height.
	 * @return the height of the chess board.
	 */
	public int getBoardPanelHeight() {
		return boardPanelHeight;
	}

	/**
	 * Game window width.
	 * @return the width of the game window.
	 */
	public int getWindowWidth() {
		return (int) this.getBounds().getWidth();
	}

	/**
	 * Game window height.
	 * @return the height of the game window.
	 */
	public int getWindowHeight() {
		return (int) this.getBounds().getHeight();
	}
	
	/**
	 * Squares of the chess board.
	 * @return matrice of the chess board.
	 */
	public static JButton[][] getChessSquare() {
		return chessSquares;
	}

	/**
	 * Set message to display.
	 * @param s is the string that should set to display.
	 */
	public void setMessage(String s) {
		message = s;
	}

	/**
	 * Get light rook image.
	 * @return the image of the light rook.
	 */
	public static Image getLightRook() {
		return LightRook;
	}

	/**
	 * Set right rook image.
	 * @param lightRook is the image that should be set.
	 */
	public void setLightRook(Image lightRook) {
		LightRook = lightRook;
	}

	/**
	 * Get light king image.
	 * @return the image of the light rook.
	 */
	public static Image getLightKing() {
		return LightKing;
	}

	/**
	 * Set light king image.
	 * @param lightKing is the image that should be set.
	 */
	public void setLightKing(Image lightKing) {
		LightKing = lightKing;
	}

	/**
	 * Get dark rook image.
	 * @return the image of the dark rook.
	 */
	public static Image getDarkRook() {
		return DarkRook;
	}

	/**
	 * Set dark rook image.
	 * @param darkRook is the image that should be set.
	 */
	public void setDarkRook(Image darkRook) {
		DarkRook = darkRook;
	}

	/**
	 * Get dark knight image.
	 * @return the image of the dark knight.
	 */
	public static Image getDarkKnight() {
		return DarkKnight;
	}

	/**
	 * Set dark knight image.
	 * @param darkKnight is the image that should be set.
	 */
	public void setDarkKnight(Image darkKnight) {
		DarkKnight = darkKnight;
	}

	/**
	 * Get dark queen image.
	 * @return the image of the dark queen.
	 */
	public static Image getDarkQueen() {
		return DarkQueen;
	}

	/**
	 * Set dark queen image.
	 * @param darkQueen is the image that should be set.
	 */
	public void setDarkQueen(Image darkQueen) {
		DarkQueen = darkQueen;
	}

	/**
	 * Get dark bishop image.
	 * @return the image of the dark bishop.
	 */
	public static Image getDarkBishop() {
		return DarkBishop;
	}

	/**
	 * Set dark bishop image.
	 * @param darkBishop is the image that should be set.
	 */
	public void setDarkBishop(Image darkBishop) {
		DarkBishop = darkBishop;
	}

	/**
	 * Get dark king image.
	 * @return the image of the dark king.
	 */
	public static Image getDarkKing() {
		return DarkKing;
	}

	/**
	 * Set dark king image.
	 * @param darkKing is the image that should be set.
	 */
	public void setDarkKing(Image darkKing) {
		DarkKing = darkKing;
	}

	/**
	 * Get dark pawn image.
	 * @return the image of the dark pawn.
	 */
	public static Image getDarkPawn() {
		return DarkPawn;
	}

	/**
	 * Set dark pawn image.
	 * @param darkPawn is the image that should be set.
	 */
	public void setDarkPawn(Image darkPawn) {
		DarkPawn = darkPawn;
	}

	/**
	 * Get light knight image.
	 * @return the image of the light knight image.
	 */
	public static Image getLightKnight() {
		return LightKnight;
	}

	/**
	 * Set light knight image.
	 * @param lightKnight is the image that should be set.
	 */
	public void setLightKnight(Image lightKnight) {
		LightKnight = lightKnight;
	}

	/**
	 * Get light bishop image.
	 * @return the image of the light bishop.
	 */
	public static Image getLightBishop() {
		return LightBishop;
	}

	/**
	 * Set light bishop image.
	 * @param lightBishop is the image that should be set.
	 */
	public void setLightBishop(Image lightBishop) {
		LightBishop = lightBishop;
	}

	/**
	 * Get light queen image.
	 * @return the image of the light queen.
	 */
	public static Image getLightQueen() {
		return LightQueen;
	}

	/**
	 * Set light queen image.
	 * @param lightQueen is the image that should be set.
	 */
	public void setLightQueen(Image lightQueen) {
		LightQueen = lightQueen;
	}

	/**
	 * Get light pawn image.
	 * @return the image of the light pawn.
	 */
	public static Image getLightPawn() {
		return LightPawn;
	}

	/**
	 * Set light Pawn image.
	 * @param lightPawn is the image that should be set.
	 */
	public void setLightPawn(Image lightPawn) {
		LightPawn = lightPawn;
	}

	/**
	 * Set active which detects which square was pressed.
	 * @param active should be set to detects the pressed square.
	 */
	public static void setActive(boolean active) {
		GUIchess.active = active;
	}

	/**
	 * Get active.
	 * @return the pressed square.
	 */
	public static boolean getActive() {
		return GUIchess.active;
	}

	/**
	 * Set the message to show which player is active and who is in chess.
	 * @param s should be set to show the message.
	 */
	public static void setLabelMessage(String s) {
		GUIchess.labelMessage.setText(s);
	}

	/**
	 * Change the background color of the squares where the certain piece can move to.
	 * @param possibleMoves is a 8x8 matrice, which contains the possible moves.
	 * @param chessSquare is the 8x8 matrice of the chess board.
	 */
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

	/**
	 * Mouse click handling.
	 * @param e event.
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Mouse release handling.
	 * @param e event.
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Mouse entered handling.
	 * @param e event.
	 */
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * Mouse exited handling.
	 * @param e event.
	 */
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * Set the command to open the webpage.
	 * @param process is the parameter that should be set.
	 */
	public void setProcess(Process process) {
		this.process = process;
	}

}
