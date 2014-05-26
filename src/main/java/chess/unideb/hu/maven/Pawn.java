package chess.unideb.hu.maven;



import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gergo0720
 * Class of the {@code Pawn}. It has a {@link #moves} method, which sets up a 8x8 matrices with true value.
 * These matrices {@link #moveLight} and {@link #moveDark} show that positions on the chess board where the piece can move.
 */
public class Pawn {
	/**
	 * Logger to debug, log information and warnings.
	 * Log information about the {@link Pawn}'s possible moves.
	 */
	private static Logger logger = LoggerFactory.getLogger(Pawn.class);
	
	/**
	 * An array to contain blocks for light pawn.
	 * It has a true value at a certain position where {@link #moves} found a dark piece
	 * in the case of light pawn.
	 */
	private static boolean isDark[] = new boolean[1];
	
	/**
	 * An array to contain blocks for dark pawn.
	 * It has a true value at a certain position where {@link #moves} found a light piece
	 * in the case of dark pawn.
	 */
	private static boolean isLight[] = new boolean[1];
	
	/**
	 * An array to contain blocks for pawn.
	 * It has a false value at a certain position where {@link #moves} found an own type of piece.
	 */
	private static boolean isOkay[] = new boolean[1];
	
	/**
	 * 8x8 matrices that has true values at certain positions where the piece can move on.
	 */
	private static boolean[][] possibleMoves = new boolean[8][8];
	
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
	 * The operators to determine the possible moves of the dark pawn.
	 * Used in {@link #moves(int, int, JButton[][], boolean)} to add this coordinates to the current position.
	 */
	private static Integer moveDark[][][] = { { { 1, 0 }, } };
	
	/**
	 * The operators to determine the possible moves of the light pawn.
	 * Used in {@link #moves(int, int, JButton[][], boolean)} to add this coordinates to the current position.
	 */
	private static Integer moveLight[][][] = { { { -1, 0 }, } };
	
	/**
	 * 8x8 matrices that contains the places where pawn can hit an opponent piece.
	 */
	private static boolean[][] hitableMoves = new boolean[8][8];

	
	/**
	 * Constructor of Pawn.
	 */
	public Pawn() {

	}
	
	/**
	 * Examine what piece are around the pawn and fill {@link #possibleMoves} accordingly.
	 * If a place is found where the pawn is able to hit an opponent piece, set the moves.
	 * If the pawn can see its own type of piece, it will ignore.
	 * @param currPosRow is the row position of the piece before move.
	 * @param currPosCol is the column position of the piece before move.
	 * @param chessSquare is the matrices of the chess board.
	 * @param player is which player is active, it is need to know to determine which pawn is active.
	 */
	static void hits(int currPosRow, int currPosCol, JButton[][] chessSquare,
			boolean player) {
		inits();
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				if (((currPosRow + moveLight[i][j][0]) >= 0)
						&& ((currPosRow + moveLight[i][j][0]) < 8)
						&& ((currPosCol + moveLight[i][j][1]) >= 0)
						&& ((currPosCol + moveLight[i][j][1]) < 8)) {

					if (player) {
						if ((currPosCol + moveLight[i][j][1] - 1) >= 0) {

							if ((ChessPiece.chessPiecesTypes[currPosRow
									+ moveLight[i][j][0]][currPosCol
									+ moveLight[i][j][1] - 1]
										.compareTo("LIGHT") != 0)) {
								hitableMoves[currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1] - 1] = true;

							}

						}
						if ((currPosCol + moveLight[i][j][1] + 1) < 8) {

							if ((ChessPiece.chessPiecesTypes[currPosRow
									+ moveLight[i][j][0]][currPosCol
									+ moveLight[i][j][1] + 1]
										.compareTo("LIGHT") != 0)) {
								hitableMoves [currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1] + 1] = true;
							}
						}
					}
					
					if (((currPosRow + moveDark[i][j][0]) >= 0)
							&& ((currPosRow + moveDark[i][j][0]) < 8)
							&& ((currPosCol + moveDark[i][j][1]) >= 0)
							&& ((currPosCol + moveDark[i][j][1]) < 8)) {

						if (!player) {
							if ((currPosCol + moveDark[i][j][1] - 1) >= 0) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1] - 1]
											.compareTo("DARK") != 0)) {
									hitableMoves[currPosRow
											+ moveDark[i][j][0]][currPosCol
											+ moveDark[i][j][1] - 1] = true;

								}

							}
							if ((currPosCol + moveDark[i][j][1] + 1) < 8) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1] + 1]
											.compareTo("DARK") != 0)) {
									hitableMoves[currPosRow
											+ moveDark[i][j][0]][currPosCol
											+ moveDark[i][j][1] + 1] = true;

								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Examine what piece are around the pawn and fill {@code possibleMoves} accordingly.
	 * In the case of dark pawn it remembers the light pieces if it finds one and the so does the light pawn with dark pieces.
	 * If pawn can see its own type of piece it will remember and set not to able move that way.
	 * @param currPosRow is the row position of the piece before move.
	 * @param currPosCol is the column position of the piece before move.
	 * @param chessSquare is the matrices of the chess board
	 * @param player is which player is active, it is need to know to determine which pawn is active.
	 */
	static void moves(int currPosRow, int currPosCol, JButton[][] chessSquare,
			boolean player) {
		inits();
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				if (((currPosRow + moveLight[i][j][0]) >= 0)
						&& ((currPosRow + moveLight[i][j][0]) < 8)
						&& ((currPosCol + moveLight[i][j][1]) >= 0)
						&& ((currPosCol + moveLight[i][j][1]) < 8)) {

					if (player) {
						if (currPosRow == 6) {

							if ((currPosCol + moveLight[i][j][1] - 1) >= 0) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1] - 1]
											.compareTo("DARK") == 0)) {
									possibleMoves[currPosRow
											+ moveLight[i][j][0]][currPosCol
											+ moveLight[i][j][1] - 1] = true;

								}

							}
							if ((currPosCol + moveLight[i][j][1] + 1) < 8) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1] + 1]
											.compareTo("DARK") == 0)) {
									possibleMoves[currPosRow
											+ moveLight[i][j][0]][currPosCol
											+ moveLight[i][j][1] + 1] = true;
								}
							}
							if (ChessPiece.chessPiecesTypes[currPosRow
									+ moveLight[i][j][0]][currPosCol
									+ moveLight[i][j][1]].compareTo("NONE") == 0) {
								possibleMoves[currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1]] = true;

								if (ChessPiece.chessPiecesTypes[currPosRow
										+ moveLight[i][j][0] - 1][currPosCol
										+ moveLight[i][j][1]]
											.compareTo("NONE") == 0) {
									possibleMoves[currPosRow
											+ moveLight[i][j][0] - 1][currPosCol
											+ moveLight[i][j][1]] = true;
								}

							}
						} else {
							if ((currPosCol + moveLight[i][j][1] - 1) >= 0) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1] - 1]
											.compareTo("DARK") == 0)) {
									possibleMoves[currPosRow
											+ moveLight[i][j][0]][currPosCol
											+ moveLight[i][j][1] - 1] = true;

								}

							}
							if ((currPosCol + moveLight[i][j][1] + 1) < 8) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1] + 1]
											.compareTo("DARK") == 0)) {
									possibleMoves[currPosRow
											+ moveLight[i][j][0]][currPosCol
											+ moveLight[i][j][1] + 1] = true;
								}
							}
							if (ChessPiece.chessPiecesTypes[currPosRow
									+ moveLight[i][j][0]][currPosCol
									+ moveLight[i][j][1]].compareTo("NONE") == 0) {
								possibleMoves[currPosRow
										+ moveLight[i][j][0]][currPosCol
										+ moveLight[i][j][1]] = true;
							}
						}
					}
				}

				if (((currPosRow + moveDark[i][j][0]) >= 0)
						&& ((currPosRow + moveDark[i][j][0]) < 8)
						&& ((currPosCol + moveDark[i][j][1]) >= 0)
						&& ((currPosCol + moveDark[i][j][1]) < 8)) {

					if (!player) {
						if (currPosRow == 1) {

							if ((currPosCol + moveDark[i][j][1] - 1) >= 0) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1] - 1]
											.compareTo("LIGHT") == 0)) {
									possibleMoves[currPosRow
											+ moveDark[i][j][0]][currPosCol
											+ moveDark[i][j][1] - 1] = true;

								}

							}
							if ((currPosCol + moveDark[i][j][1] + 1) < 8) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1] + 1]
											.compareTo("LIGHT") == 0)) {
									possibleMoves[currPosRow
											+ moveDark[i][j][0]][currPosCol
											+ moveDark[i][j][1] + 1] = true;

								}
							}
							if (ChessPiece.chessPiecesTypes[currPosRow
									+ moveDark[i][j][0]][currPosCol
									+ moveDark[i][j][1]].compareTo("NONE") == 0) {
								possibleMoves[currPosRow + moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1]] = true;

								if (ChessPiece.chessPiecesTypes[currPosRow
										+ moveDark[i][j][0] + 1][currPosCol
										+ moveDark[i][j][1]].compareTo("NONE") == 0) {
									possibleMoves[currPosRow
											+ moveDark[i][j][0] + 1][currPosCol
											+ moveDark[i][j][1]] = true;
								}

							}
						} else {
							if ((currPosCol + moveDark[i][j][1] - 1) >= 0) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1] - 1]
											.compareTo("LIGHT") == 0)) {
									possibleMoves[currPosRow
											+ moveDark[i][j][0]][currPosCol
											+ moveDark[i][j][1] - 1] = true;

								}

							}
							if ((currPosCol + moveDark[i][j][1] + 1) < 8) {

								if ((ChessPiece.chessPiecesTypes[currPosRow
										+ moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1] + 1]
											.compareTo("LIGHT") == 0)) {
									possibleMoves[currPosRow
											+ moveDark[i][j][0]][currPosCol
											+ moveDark[i][j][1] + 1] = true;

								}
							}
							if (ChessPiece.chessPiecesTypes[currPosRow
									+ moveDark[i][j][0]][currPosCol
									+ moveDark[i][j][1]].compareTo("NONE") == 0) {
								possibleMoves[currPosRow + moveDark[i][j][0]][currPosCol
										+ moveDark[i][j][1]] = true;

							}
						}
					}
				}
			}
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
		for (int i = 0; i < hitableMoves.length; i++) {
			for (int j = 0; j < hitableMoves.length; j++) {
				hitableMoves[i][j] = false;
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
	
	/**
	 * Get the all possible moves where pawn can hit the opponent piece.
	 * @return all the hitable moves.
	 */
	public static boolean[][] getHitableMoves() {
		return hitableMoves;
	}
		
	/**
	 * If the queen is not on the board and the pawn reach the opponent start side, the player get back his/her queen.
	 * Both column and row position are needed to replace the pawn with queen.
	 * @param row is the row position, if the pawn reach it, the player get back the queen.
	 * @param col is the column position.
	 * @param pawn is the name of the pawn.
	 * @param chessSquare is the matrices of the chess board.
	 */
	public static void replacePawnWithQueen(int row, int col, String pawn, JButton[][] chessSquare) {
		if(pawn.toLowerCase().contains("darkpawn")) {
			if(row == 7 && !Game.isOnBoard("DarkQueen", chessSquare)) {
				ChessPiece.chessPieces[row][col] = "DarkQueen";
				chessSquare[row][col].setIcon(new ImageIcon(GUIchess.getDarkQueen()));
			}
		}
		
		if(pawn.toLowerCase().contains("lightpawn")) {
			if(row == 0 && !Game.isOnBoard("LightQueen", chessSquare)) {
				ChessPiece.chessPieces[row][col] = "LightQueen";
				chessSquare[row][col].setIcon(new ImageIcon(GUIchess.getLightQueen()));
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
					chessSquare[i][j]
							.setIcon(new ImageIcon(GUIchess.getLightPawn()));
				else
					chessSquare[i][j]
							.setIcon(new ImageIcon(GUIchess.getDarkPawn()));
				GUIchess.setActive(true);
				replacePawnWithQueen(i, j, ChessPiece.chessPieces[i][j], chessSquare);
				
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
		} catch(Exception e) {
			logger.error("Invalid move!");
			GUIchess.setActive(true);
		}
	}
}
