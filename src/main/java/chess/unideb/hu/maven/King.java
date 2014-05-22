package chess.unideb.hu.maven;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gergo0720
 * Class of King
 */
public class King {
	
	/**
	 * Logger to debug, log information and warnings.
	 */
	private static Logger logger = LoggerFactory.getLogger(King.class);
	
	/**
	 * 8x8 matrices, contains blocks for light king.
	 */
	private static boolean[] isDark = new boolean[8];
	
	/**
	 * 8x8 matrices, contains blocks for dark king.
	 */
	private static boolean[] isLight = new boolean[8];
	
	/**
	 * 8x8 matrices, contains block for kings.
	 */
	private static boolean[] isOkay = new boolean[8];
	
	/**
	 * The name of the piece that should be moved.
	 */
	private static String newPiece;
	
	/**
	 * The type of the piece that should be moved.
	 */
	private static String newType;
	
	/**
	 * The previous row position of the piece that should be moved.
	 */
	private static int oldPosRow;
	
	/**
	 * The previous column position of the piece that should be moved.
	 */
	private static int oldPosCol;
	
	/**
	 * The number of the moves of the light king.
	 */
	private static int moveCounterLight = 0;
	
	/**
	 * The number of the moves of the dark king.
	 */
	private static int moveCounterDark = 0;
	
	/**
	 * Get the number of the moves of the light king.
	 * @return the number of the taken moves.
	 */
	public static int getMoveCounterLight() {
		return moveCounterLight;
	}

	/**
	 * Get the number of the moves of dark king.
	 * @return the number of the taken moves.
	 */
	public static int getMoveCounterDark() {
		return moveCounterDark;
	}
	
	/**
	 * 8x8 matrices that contains the possible moves of the king.
	 */
	private static boolean[][] possibleMoves = new boolean[8][8];
	
	/**
	 * 8x8 matrices that contains all the possible place where king can be given chess from.
	 */
	private static boolean[][] possibleChess = new boolean[8][8];
	
	/**
	 * The operators to determine the possible moves of the king.
	 */
	private static Integer move[][][] = 
	{
		{
			{1,1},
			{-1,-1},
			{1,0},
			{0,1},
			{-1,0},
			{0,-1},
			{1,-1},
			{-1,1}
		}
	};
	
	/**
	 * The operators to determine the possible chess except for knight.
	 */
	private static Integer isChess[][][] = {
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
				{ 8, -8 }, { 0, 8 } }};
	
	/**
	 * The operators to determine the possible chess by the knight.
	 */
	private static Integer isChess2[][][] = {
		{ { 2, 1 }, { 2, -1 }, { -2, 1 },
			{ -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }  }
	};
	
	/**
	 * Constructor of King.
	 */
	public King(){
		
	}
	
	/**
	 * Examine what piece are around the king and fill {@code possibleMoves} accordingly.
	 * In the case of dark king it remembers the light pieces if it finds one and the so does the light king with dark pieces.
	 * If king can see its own type of piece it will remember and set not to able move that way.
	 * @param currPosRow is the row position of the piece before move.
	 * @param currPosCol is the column position of the piece before move.
	 * @param chessSquare is the matrices of the chess board
	 * @param player is which player is active, it is need to know to determine which king is active.
	 */
	static void moves(int currPosRow, int currPosCol, JButton[][] chessSquare, boolean player){
		inits();
			
		if(getMoveCounterLight() == 0 && Rook.getMoveCounterLightRight() == 0 && ChessPiece.chessPieces[7][5].compareTo("EMPTY") == 0 &&
				ChessPiece.chessPieces[7][6].compareTo("EMPTY") == 0 && player) {
			possibleMoves[7][6] = true;
		}
		if(getMoveCounterLight() == 0 && Rook.getMoveCounterLightLeft() == 0 && ChessPiece.chessPieces[7][1].compareTo("EMPTY") == 0 &&
				ChessPiece.chessPieces[7][2].compareTo("EMPTY") == 0 && ChessPiece.chessPieces[7][3].compareTo("EMPTY") == 0 && player) {
			possibleMoves[7][2] = true;
		}
		
		if(getMoveCounterDark() == 0 && Rook.getMoveCounterDarkLeft() == 0 && ChessPiece.chessPieces[0][5].compareTo("EMPTY") == 0 &&
				ChessPiece.chessPieces[0][6].compareTo("EMPTY") == 0 && !player) {
			possibleMoves[0][6] = true;
		}
		if(getMoveCounterDark() == 0 && Rook.getMoveCounterDarkRight() == 0 && ChessPiece.chessPieces[0][1].compareTo("EMPTY") == 0 &&
				ChessPiece.chessPieces[0][2].compareTo("EMPTY") == 0 && ChessPiece.chessPieces[0][3].compareTo("EMPTY") == 0 && !player) {
			possibleMoves[0][2] = true;
		}
		
		
		for(int i = 0; i < 1; i++){
			 for(int j = 0; j < 8; j++){
				 if( ((currPosRow+move[i][j][0]) >= 0)  && ((currPosRow+move[i][j][0]) < 8) && ((currPosCol+move[i][j][1]) >= 0) && ((currPosCol+move[i][j][1]) < 8)){
					 if (ChessPiece.chessPiecesTypes[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]].compareTo("LIGHT") == 0 && player){ 
							isOkay[j] = false;
							continue;
					}else if (ChessPiece.chessPiecesTypes[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]].compareTo("LIGHT") != 0 && player) {
						if (isOkay[j] == false || isDark[j] == true)
							continue;
						else{
							possibleMoves[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]] = true;
						}	
					}
				 	if (ChessPiece.chessPiecesTypes[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]].compareTo("DARK") == 0 && player){
						isDark[j] = true;
					}
				 	
				 	
				 	if (ChessPiece.chessPiecesTypes[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]].compareTo("DARK") == 0 && !player){ 
						isOkay[j] = false;
						continue;
					}else if (ChessPiece.chessPiecesTypes[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]].compareTo("DARK") != 0 && !player) {
						if (isOkay[j] == false || isLight[j] == true)
							continue;
						else{
							possibleMoves[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]] = true;
						}	
					}
				 	if (ChessPiece.chessPiecesTypes[currPosRow+move[i][j][0]][currPosCol+move[i][j][1]].compareTo("LIGHT") == 0 && !player){
						isLight[j] = true;
					}
				 }
			 }
		}
	}
	
	/**
	 * Examine the king's current position and set up the {@code possibleChess} matrices with the possible places where king can be given chess from.
	 * @param currPosRow is the row position of the piece before move.
	 * @param currPosCol is the column position of the piece before move.
	 * @param chessSquare is the matrices of the chess board.
	 * @param player is which player is active, it is need to know to determine which king is active.
	 */
	static void chess(int currPosRow, int currPosCol, JButton[][] chessSquare, boolean player){
		inits();
		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < 8; j++) {
				if( ((currPosRow+isChess2[i][j][0]) >= 0) && ((currPosRow+isChess2[i][j][0]) < 8) &&
						((currPosCol+isChess2[i][j][1]) >= 0) && ((currPosCol+isChess2[i][j][1]) < 8)) {
					
					if(ChessPiece.chessPiecesTypes[currPosRow+isChess2[i][j][0]][currPosCol+isChess2[i][j][1]].compareTo("LIGHT") == 0 && player)
						continue;
					else if(ChessPiece.chessPiecesTypes[currPosRow+isChess2[i][j][0]][currPosCol+isChess2[i][j][1]].compareTo("LIGHT") != 0 && player)
						possibleChess[currPosRow+isChess2[i][j][0]][currPosCol+isChess2[i][j][1]] = true;
					
					else if(ChessPiece.chessPiecesTypes[currPosRow+isChess2[i][j][0]][currPosCol+isChess2[i][j][1]].compareTo("DARK") == 0 && !player)
						continue;
					else if (ChessPiece.chessPiecesTypes[currPosRow+isChess2[i][j][0]][currPosCol+isChess2[i][j][1]].compareTo("DARK") != 0 && !player)
						possibleChess[currPosRow+isChess2[i][j][0]][currPosCol+isChess2[i][j][1]] = true;
				
				}
			}
		}
		
		
		for(int i = 0; i < 8; i++){
			 for(int j = 0; j < 8; j++){
				 if( ((currPosRow+isChess[i][j][0]) >= 0)  && ((currPosRow+isChess[i][j][0]) < 8) && ((currPosCol+isChess[i][j][1]) >= 0) && ((currPosCol+isChess[i][j][1]) < 8)){
					if (ChessPiece.chessPiecesTypes[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]].compareTo("LIGHT") == 0 && player){ 
							isOkay[j] = false;
							continue;
					}else if (ChessPiece.chessPiecesTypes[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]].compareTo("LIGHT") != 0 && player) {
						if (isOkay[j] == false || isDark[j] == true)
							continue;
						else{
							possibleChess[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]] = true;
						}
					}
				 	if (ChessPiece.chessPiecesTypes[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]].compareTo("DARK") == 0 && player){
						isDark[j] = true;
					}
				 	
				 	
				 	if (ChessPiece.chessPiecesTypes[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]].compareTo("DARK") == 0 && !player){ 
						isOkay[j] = false;
						continue;
					}else if (ChessPiece.chessPiecesTypes[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]].compareTo("DARK") != 0 && !player) {
						if (isOkay[j] == false || isLight[j] == true)
							continue;
						else{
							possibleChess[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]] = true;
						}	
					}
				 	if (ChessPiece.chessPiecesTypes[currPosRow+isChess[i][j][0]][currPosCol+isChess[i][j][1]].compareTo("LIGHT") == 0 && !player){
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
		logger.info("Number of King's possible moves: " + King.getNumberOfMoves(King.getPossibleMoves()));
		GUIchess.drawPossibleMoves(possibleMoves, GUIchess.getChessSquare());
		newPiece = ChessPiece.chessPieces[i][j];
		newType = ChessPiece.chessPiecesTypes[i][j];
		oldPosRow = ChessPiece.chessPiecesPositions[i][j][0];
		oldPosCol = ChessPiece.chessPiecesPositions[i][j][1];
	}

	/**
	 * Place the piece to the new place.
	 * @param i is the row position where piece should be placed.
	 * @param j is the column position where piece should be placed.
	 * @param chessSquare is the matrices of the chess board.
	 */
	public static void placePiece(int i, int j, JButton[][] chessSquare) {
		try {	
			if(possibleMoves[i][j]) {
				if(ChessPiece.chessPieces[oldPosRow][oldPosCol].compareTo("DarkKing") == 0) {
					moveCounterDark++;
				} else if(ChessPiece.chessPieces[oldPosRow][oldPosCol].compareTo("LightKing") == 0) {
					moveCounterLight++;
				}
				
				if(Rook.getMoveCounterLightRight() == 0 && King.getMoveCounterLight() == 1) {
					if(i == 7 && j == 6) {
						Rook.moves(7, 7, chessSquare, Game.isPlayer());
						ChessPiece.chessPieces[i][j] = newPiece;
						ChessPiece.chessPiecesTypes[i][j] = newType;
						ChessPiece.chessPiecesPositions[i][j][0] = i;
						ChessPiece.chessPiecesPositions[i][j][1] = j;
						ChessPiece.chessPieces[oldPosRow][oldPosCol] = ChessPiece.empty;
						ChessPiece.chessPiecesTypes[oldPosRow][oldPosCol] = ChessPiece.typeOfPiece.NONE.toString();
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][0] = -1;
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][1] = -1;
						chessSquare[oldPosRow][oldPosCol].setIcon(null);
						
						Rook.grabPiece(7, 7);
						Rook.placePiece(7, 5, chessSquare);
						GUIchess.paintDefaultSquare();
						Game.setPlayer(!Game.isPlayer());
						if (Game.isPlayer())
							Game.setActivePlayer(Players.getUserName() + " (light) ");
						else
							Game.setActivePlayer(Players.getUserName2() + " (dark) ");
						GUIchess.setLabelMessage(Game.getActivePlayer() + " 's round");
						if(newType.compareTo("LIGHT") == 0)
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getLightKing()));
						else
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getDarkKing()));
						GUIchess.setActive(true);
						GUIchess.setMovesDisplay(ChessPiece.chessPieces[i][j] + 
								"  " + GUIchess.rowConverter(oldPosRow) + 
								"  " + oldPosCol + " -> " + GUIchess.rowConverter(i) + "  " +j );
						
					}
				}
				if(Rook.getMoveCounterLightLeft() == 0 && King.getMoveCounterLight() == 1) {
					if(i == 7 && j == 2) {
						Rook.moves(7, 0, chessSquare, Game.isPlayer());
						ChessPiece.chessPieces[i][j] = newPiece;
						ChessPiece.chessPiecesTypes[i][j] = newType;
						ChessPiece.chessPiecesPositions[i][j][0] = i;
						ChessPiece.chessPiecesPositions[i][j][1] = j;
						ChessPiece.chessPieces[oldPosRow][oldPosCol] = ChessPiece.empty;
						ChessPiece.chessPiecesTypes[oldPosRow][oldPosCol] = ChessPiece.typeOfPiece.NONE.toString();
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][0] = -1;
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][1] = -1;
						chessSquare[oldPosRow][oldPosCol].setIcon(null);
						
						Rook.grabPiece(7, 0);
						Rook.placePiece(7, 3, chessSquare);
						Game.setPlayer(!Game.isPlayer());
						if (Game.isPlayer())
							Game.setActivePlayer(Players.getUserName() + " (light) ");
						else
							Game.setActivePlayer(Players.getUserName2() + " (dark) ");
						GUIchess.setLabelMessage(Game.getActivePlayer() + " 's round");
						GUIchess.paintDefaultSquare();
						if(newType.compareTo("LIGHT") == 0)
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getLightKing()));
						else
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getDarkKing()));
						GUIchess.setActive(true);GUIchess.setMovesDisplay(ChessPiece.chessPieces[i][j] + 
								"  " + GUIchess.rowConverter(oldPosCol) + 
								"  " + (oldPosRow+1) + " -> " + GUIchess.rowConverter(oldPosCol) + "  " + (i+1) + "\n");
						
					}	
					
				}
				if(Rook.getMoveCounterDarkRight() == 0 && King.getMoveCounterDark() == 1) {
					if(i == 0 && j == 2) {
						Rook.moves(0, 0, chessSquare, !Game.isPlayer());
						ChessPiece.chessPieces[i][j] = newPiece;
						ChessPiece.chessPiecesTypes[i][j] = newType;
						ChessPiece.chessPiecesPositions[i][j][0] = i;
						ChessPiece.chessPiecesPositions[i][j][1] = j;
						ChessPiece.chessPieces[oldPosRow][oldPosCol] = ChessPiece.empty;
						ChessPiece.chessPiecesTypes[oldPosRow][oldPosCol] = ChessPiece.typeOfPiece.NONE.toString();
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][0] = -1;
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][1] = -1;
						chessSquare[oldPosRow][oldPosCol].setIcon(null);
						
						Rook.grabPiece(0, 0);
						Rook.placePiece(0, 3, chessSquare);
						GUIchess.paintDefaultSquare();
						if(newType.compareTo("LIGHT") == 0)
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getLightKing()));
						else
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getDarkKing()));
						GUIchess.setActive(true);
						GUIchess.setActive(true);GUIchess.setMovesDisplay(ChessPiece.chessPieces[i][j] + 
								"  " + GUIchess.rowConverter(oldPosCol) + 
								"  " + (oldPosRow+1) + " -> " + GUIchess.rowConverter(oldPosCol) + "  " + (i+1) + "\n");
					}	
					
				}
				if(Rook.getMoveCounterDarkLeft() == 0 && King.getMoveCounterDark() == 1) {
					if(i == 0 && j == 6) {
						Rook.moves(0, 7, chessSquare, !Game.isPlayer());
						ChessPiece.chessPieces[i][j] = newPiece;
						ChessPiece.chessPiecesTypes[i][j] = newType;
						ChessPiece.chessPiecesPositions[i][j][0] = i;
						ChessPiece.chessPiecesPositions[i][j][1] = j;
						ChessPiece.chessPieces[oldPosRow][oldPosCol] = ChessPiece.empty;
						ChessPiece.chessPiecesTypes[oldPosRow][oldPosCol] = ChessPiece.typeOfPiece.NONE.toString();
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][0] = -1;
						ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][1] = -1;
						chessSquare[oldPosRow][oldPosCol].setIcon(null);
						
						Rook.grabPiece(0, 7);
						Rook.placePiece(0, 5, chessSquare);
						GUIchess.paintDefaultSquare();
						if(newType.compareTo("LIGHT") == 0)
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getLightKing()));
						else
							chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getDarkKing()));
						GUIchess.setActive(true);
						GUIchess.setActive(true);GUIchess.setMovesDisplay(ChessPiece.chessPieces[i][j] + 
								"  " + GUIchess.rowConverter(oldPosCol) + 
								"  " + (oldPosRow+1) + " -> " + GUIchess.rowConverter(oldPosCol) + "  " + (i+1) + "\n");
					}	
					
				} else {
					ChessPiece.chessPieces[i][j] = newPiece;
					ChessPiece.chessPiecesTypes[i][j] = newType;
					ChessPiece.chessPiecesPositions[i][j][0] = i;
					ChessPiece.chessPiecesPositions[i][j][1] = j;
					ChessPiece.chessPieces[oldPosRow][oldPosCol] = ChessPiece.empty;
					ChessPiece.chessPiecesTypes[oldPosRow][oldPosCol] = ChessPiece.typeOfPiece.NONE.toString();
					ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][0] = -1;
					ChessPiece.chessPiecesPositions[oldPosRow][oldPosCol][1] = -1;
					chessSquare[oldPosRow][oldPosCol].setIcon(null);
					if(newType.compareTo("LIGHT") == 0)
						chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getLightKing()));
					else
						chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getDarkKing()));
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
					logger.info("DarkKing's number of moves: " + getMoveCounterDark());
					logger.info("LightKing's number of moves: " + getMoveCounterLight());
				}
				
				
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
		for(int i = 0; i < possibleMoves.length; i++){
			for(int j = 0; j < possibleMoves.length; j++){
				possibleMoves[i][j] = false;
			}
		}
		for(int i = 0; i < possibleChess.length; i++){
			for(int j = 0; j < possibleChess.length; j++){
				possibleChess[i][j] = false;
			}
		}
		for(int i = 0; i < isOkay.length; i++){
			isLight[i] = false;
		}
		for(int i = 0; i < isOkay.length; i++){
			isDark[i] = false;
		}
		for(int i = 0; i < isOkay.length; i++){
			isOkay[i] = true;
		}
		
	}
	
	/**
	 * Get the piece all the possible moves.
	 * @return all the possible moves.
	 */
	public static boolean[][] getPossibleMoves() {
		return possibleMoves;
	}

	/**
	 * Get the piece all the possible places where it can be given chess from.
	 * @return all the places where it can be given a chess from.
	 */
	public static boolean[][] getPossibleChess() {
		return possibleChess;
	}
	
	/**
	 * Compute the number of the piece's possible moves.
	 * @param pM is the matrices, which contains the possible moves.
	 * @return the number of piece's possible moves.
	 */
	public static int getNumberOfMoves(boolean[][] pM) {
		int counter = 0;
		for(int i = 0; i < pM.length; i++) {
			for(int j = 0; j < pM.length; j++) {
				if(pM[i][j] == true)
					counter++;
			}
		}
		return counter;
	}
} 
