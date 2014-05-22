package chess.unideb.hu.maven;



import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pawn {
	private static Logger logger = LoggerFactory.getLogger(Pawn.class);
	private static boolean isDark[] = new boolean[1];
	private static boolean isLight[] = new boolean[1];
	private static boolean isOkay[] = new boolean[1];
	private static boolean[][] possibleMoves = new boolean[8][8];
	private static String newPiece;
	private static String newType;
	private static int oldPosRow;
	private static int oldPosCol;
	private static int moveCounter = 0;
	public static int getMoveCounter() {
		return moveCounter;
	}

	

	public static void setMoveCounter(int moveCounter) {
		Pawn.moveCounter = moveCounter;
	}
	private static Integer moveDark[][][] = { { { 1, 0 }, } };
	private static Integer moveLight[][][] = { { { -1, 0 }, } };
	private static boolean[][] hitableMoves = new boolean[8][8];

	public Pawn() {

	}
	
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

	public static boolean[][] getPossibleMoves() {
		return possibleMoves;
	}
	
	public static boolean[][] getHitableMoves() {
		return hitableMoves;
	}
		
	public static void replacePawnWithQueen(int row, int col, String pawn, JButton[][] chessSquare) {
		if(pawn.toLowerCase().contains("darkpawn")) {
			if(row == 7 && !Game.isInBoard("DarkQueen", chessSquare)) {
				ChessPiece.chessPieces[row][col] = "DarkQueen";
				chessSquare[row][col].setIcon(new ImageIcon(GUIchess.getDarkQueen()));
			}
		}
		
		if(pawn.toLowerCase().contains("lightpawn")) {
			if(row == 7 && !Game.isInBoard("LightQueen", chessSquare)) {
				ChessPiece.chessPieces[row][col] = "LightQueen";
				chessSquare[row][col].setIcon(new ImageIcon(GUIchess.getLightQueen()));
			}
		}
	}

	public static void grabPiece(int i, int j) {
		GUIchess.drawPossibleMoves(possibleMoves, GUIchess.getChessSquare());
		newPiece = ChessPiece.chessPieces[i][j];
		newType = ChessPiece.chessPiecesTypes[i][j];
		oldPosRow = ChessPiece.chessPiecesPositions[i][j][0];
		oldPosCol = ChessPiece.chessPiecesPositions[i][j][1];
	}

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
