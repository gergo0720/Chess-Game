package chess.unideb.hu.maven;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gergo0720
 * Class of the {@code Queen}. It has a {@link #moves} method, which sets up a 8x8 matrices with true value.
 * This matrices {@link #move} shows that positions on the chess board where the piece can move.
 */
public class Queen {
	
	/**
	 * Logger to debug, log information and warnings.
	 * Log information about the {@link #Queen}'s possible moves.
	 */
	private static Logger logger = LoggerFactory.getLogger(Queen.class);
	
	/**
	 * An array to contain blocks for light queen.
	 * It has a true value at a certain position where {@link #moves} found a dark piece
	 * in the case of light queen.
	 */
	private static boolean isDark[] = new boolean[8];
	
	/**
	 * An array to contain blocks for dark queen.
	 * It has a true value at a certain position where {@link #moves} found a light piece
	 * in the case of dark queen.
	 */
	private static boolean isLight[] = new boolean[8];
	
	/**
	 * An array to contain blocks for queen.
	 * It has a false value at a certain position where {@link #moves} found an own type of piece.
	 */
	private static boolean isOkay[] = new boolean[8];
	

	/**
	 * The name of the piece that should be moved.
	 * Used in {@link #grabPiece(int, int)} to know which piece we need to move.
	 */
	private static String newPiece;
	
	/**
	 * The type of the piece that should be moved.
	 * Used in {@link #grabPiece(int, int)} to know which piece we need to move.
	 */
	private static String newType;
	
	/**
	 * The previous row position of the piece that should be moved.
	 * Used in {@link #placePiece(int, int, JButton[][])} to know which square on the board need to be set null.
	 */
	private static int oldPosRow;
	
	/**
	 * The previous column position of the piece that should be moved.
	 * Used in {@link #placePiece(int, int, JButton[][])} to know which square on the board need to be set null.
	 */
	private static int oldPosCol;

	/**
	 * 8x8 matrices that has true values at certain positions where the piece can move on.
	 */
	private static boolean[][] possibleMoves = new boolean[8][8];
	
	/**
	 * The operators to determine the possible moves of the queen.
	 * Used in {@link #moves(int, int, JButton[][], boolean)} to add this coordinates to the current position.
	 */
	private static Integer move[][][] = {
			{ { 1, 1 }, { 1, 0 }, { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, -1 },
					{ 1, -1 }, { 0, 1 } },
			{ { 2, 2 }, { 2, 0 }, { -2, 2 }, { -2, 0 }, { -2, -2 }, { 0, -2 },
					{ 2, -2 }, { 0, 2 } },
			{ { 3, 3 }, { 3, 0 }, { -3, 3 }, { -3, 0 }, { -3, -3 }, { 0, -3 },
					{ 3, -3 }, { 0, 3 } },
			{ { 4, 4 }, { 4, 0 }, { -4, 4 }, { -4, 0 }, { -4, -4 }, { 0, -4 },
					{ 4, -4 }, { 0, 4 } },
			{ { 5, 5 }, { 5, 0 }, { -5, 5 }, { -5, 0 }, { -5, -5 }, { 0, -5 },
					{ 5, -5 }, { 0, 5 } },
			{ { 6, 6 }, { 6, 0 }, { -6, 6 }, { -6, 0 }, { -6, -6 }, { 0, -6 },
					{ 6, -6 }, { 0, 6 } },
			{ { 7, 7 }, { 7, 0 }, { -7, 7 }, { -7, 0 }, { -7, -7 }, { 0, -7 },
					{ 7, -7 }, { 0, 7 } },
			{ { 8, 8 }, { 8, 0 }, { -8, 8 }, { -8, 0 }, { -8, -8 }, { 0, -8 },
					{ 8, -8 }, { 0, 8 } } };

	/**
	 * Constructor of Queen.
	 */
	public Queen() {

	}

	/**
	 * Examine what piece are around the queen and fill {@link #possibleMoves} accordingly.
	 * In the case of dark queen it remembers the light pieces if it finds one and so does the light queen as well with dark pieces.
	 * If queen can see its own type of piece it will remember and set not to able move that way.
	 * @param currPosRow is the row position of the piece before move.
	 * @param currPosCol is the column position of the piece before move.
	 * @param chessSquare is the matrices of the chess board
	 * @param player is which player is active, it is need to know to determine which queen is active.
	 */
	static void moves(int currPosRow, int currPosCol, JButton[][] chessSquare, boolean player) {
		inits();
		for (int i = 0; i < chessSquare.length; i++) {
			for (int j = 0; j < chessSquare.length; j++) {
				if (((currPosRow + move[i][j][0]) >= 0)
						&& ((currPosRow + move[i][j][0]) < 8)
						&& ((currPosCol + move[i][j][1]) >= 0)
						&& ((currPosCol + move[i][j][1]) < 8)) {

					if (ChessPiece.chessPiecesTypes[currPosRow + move[i][j][0]][currPosCol
							+ move[i][j][1]].compareTo("LIGHT") == 0
							&& player) {
						isOkay[j] = false;
						continue;
					} else if (ChessPiece.chessPiecesTypes[currPosRow
							+ move[i][j][0]][currPosCol + move[i][j][1]]
							.compareTo("LIGHT") != 0 && player) {
						if (isOkay[j] == false || isDark[j] == true)
							continue;
						else {
							possibleMoves[currPosRow + move[i][j][0]][currPosCol
									+ move[i][j][1]] = true;
							//chessSquare[currPosRow + move[i][j][0]][currPosCol
							//		+ move[i][j][1]].setBackground(Color.CYAN);
						}
					}
					if (ChessPiece.chessPiecesTypes[currPosRow + move[i][j][0]][currPosCol
							+ move[i][j][1]].compareTo("DARK") == 0
							&& player) {
						isDark[j] = true;
					}

					if (ChessPiece.chessPiecesTypes[currPosRow + move[i][j][0]][currPosCol
							+ move[i][j][1]].compareTo("DARK") == 0
							&& !player) {
						isOkay[j] = false;
						continue;
					} else if (ChessPiece.chessPiecesTypes[currPosRow
							+ move[i][j][0]][currPosCol + move[i][j][1]]
							.compareTo("DARK") != 0 && !player) {
						if (isOkay[j] == false || isLight[j] == true)
							continue;
						else {
							possibleMoves[currPosRow + move[i][j][0]][currPosCol
									+ move[i][j][1]] = true;
							//chessSquare[currPosRow + move[i][j][0]][currPosCol
								//	+ move[i][j][1]].setBackground(Color.CYAN);
						}
					}
					if (ChessPiece.chessPiecesTypes[currPosRow + move[i][j][0]][currPosCol
							+ move[i][j][1]].compareTo("LIGHT") == 0
							&& !player) {
						isLight[j] = true;
					}
				}
			}
		}
	}

	/**
	 * Grab the piece, {@code newPiece} and {@code newType} are set with the information of the piece.
	 * Store the previous position before move.
	 * @param i is the previous row position of the piece.
	 * @param j is the previous column position of the piece.
	 */
	public static void grabPiece(int i, int j) {
		GUIchess.drawPossibleMoves(possibleMoves, GUIchess.getChessSquare());
		newPiece = ChessPiece.chessPieces[i][j];
		newType = ChessPiece.chessPiecesTypes[i][j];
		oldPosRow = ChessPiece.chessPiecesPositions[i][j][0];
		oldPosCol = ChessPiece.chessPiecesPositions[i][j][1];
	}

	/**
	 * Place the piece to the new place.
	 * Set the piece icons on the chess board.
	 * Change the active player if the move was valid.
	 * @param i is the row position where piece should be placed.
	 * @param j is the column position where piece should be placed.
	 * @param chessSquare is the matrices of the chess board.
	 */
	public static void placePiece(int i, int j, JButton[][] chessSquare) {
		try {
			if (possibleMoves[i][j]) {
				ChessPiece.chessPieces[i][j] = newPiece;
				ChessPiece.chessPiecesTypes[i][j] = newType;
				ChessPiece.chessPiecesPositions[i][j][0] = i;
				ChessPiece.chessPiecesPositions[i][j][1] = j;
				ChessPiece.chessPieces[oldPosRow][oldPosCol] = ChessPiece.empty;
				ChessPiece.chessPiecesTypes[oldPosRow][oldPosCol] = ChessPiece.typeOfPiece.NONE
						.toString();
				ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][0] = -1;
				ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][1] = -1;
				chessSquare[oldPosRow][oldPosCol].setIcon(null);
				if (newType.compareTo("LIGHT") == 0)
					chessSquare[i][j].setIcon(new ImageIcon(GUIchess
							.getLightQueen()));
				else
					chessSquare[i][j]
							.setIcon(new ImageIcon(GUIchess.getDarkQueen()));
				
				
				
				GUIchess.setActive(true);
				Game.setPlayer(!Game.isPlayer());
				if (Game.isPlayer())
					Game.setActivePlayer(Players.getUserName() + " (light) ");
				else
					Game.setActivePlayer(Players.getUserName2() + " (dark) ");
				GUIchess.setLabelMessage(Game.getActivePlayer() + " 's round");
				GUIchess.setActive(true);GUIchess.setMovesDisplay(ChessPiece.chessPieces[i][j] + 
						"  " + GUIchess.rowConverter(oldPosCol) + 
						(oldPosRow+1) + " -> " + GUIchess.rowConverter(j) + (i+1) + "\n");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("Invalid move!");
			GUIchess.setActive(true);
		}
	
	}

	/**
	 * Initialize all the matrices and arrays.
	 * {@code possibleMoves}, {@code possibleChess}, {@code isOkay}, {@code isDark} and {@code isLight}.
	 */
	private static void inits() {
		for (int i = 0; i < possibleMoves.length; i++) {
			for (int j = 0; j < possibleMoves.length; j++) {
				possibleMoves[i][j] = false;
			}
		}
		for (int i = 0; i < isOkay.length; i++) {
			isLight[i] = false;
		}
		for (int i = 0; i < isOkay.length; i++) {
			isDark[i] = false;
		}
		for (int i = 0; i < isOkay.length; i++) {
			isOkay[i] = true;
		}

	}

	/**
	 * Get the all the possible moves.
	 * @return all the possible moves.
	 */
	public static boolean[][] getPossibleMoves() {
		return possibleMoves;
	}
}
