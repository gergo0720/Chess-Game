package chess.unideb.hu.maven;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Bishop {
	private static Logger logger = LoggerFactory.getLogger(Bishop.class);
	private static boolean[] isDark = new boolean[4];
	private static boolean[] isLight = new boolean[4];
	private static boolean[] isOkay = new boolean[4];
	private static String newPiece;
	private static String newType;
	private static int oldPosRow;
	private static int oldPosCol;
	private static int moveCounter = 0;
	public static int getMoveCounter() {
		return moveCounter;
	}



	public static void setMoveCounter(int moveCounter) {
		Bishop.moveCounter = moveCounter;
	}
	private static boolean[][] possibleMoves = new boolean[8][8];
	private static Integer move[][][] = 
	{
		{
			{1,1},
			{-1,-1},
			{1,-1},
			{-1,1}
		},
		{
			{2,2},
			{-2,-2},
			{2,-2},
			{-2,2}
		},
		{	
			{3,3},
			{-3,-3},
			{3,-3},
			{-3,3}
		},
		{	
			{4,4},
			{-4,-4},
			{4,-4},
			{-4,4}
		},
		{
			{5,5},
			{-5,-5},
			{5,-5},
			{-5,5}
		},
		{
			{6,6},
			{-6,-6},
			{6,-6},
			{-6,6}
		},
		{
			{7,7},
			{-7,-7},
			{7,-7},
			{-7,7}
		},
		{
			{8,8},
			{-8,-8},
			{8,-8},
			{-8,8}
		}
	};
	
	public Bishop(){
		
	}
	
	static void moves(int currPosRow, int currPosCol, JButton[][] chessSquare, boolean player){
		inits();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 4; j++){
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
						else {
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
	
	public static void grabPiece(int i, int j) {
		GUIchess.drawPossibleMoves(possibleMoves, GUIchess.getChessSquare());
		newPiece = ChessPiece.chessPieces[i][j];
		newType = ChessPiece.chessPiecesTypes[i][j];
		oldPosRow = ChessPiece.chessPiecesPositions[i][j][0];
		oldPosCol = ChessPiece.chessPiecesPositions[i][j][1];
	}

	public static void placePiece(int i, int j, JButton[][] chessSquare) {
		try {	
			if(possibleMoves[i][j]) {	
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
					chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getLightBishop()));
				else
					chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getDarkBishop()));
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
		} catch(Exception e) {
			logger.error("Invalid move!");
			GUIchess.setActive(true);
		}
	}

	private static void inits() {
		for(int i = 0; i < possibleMoves.length; i++){
			for(int j = 0; j < possibleMoves.length; j++){
				possibleMoves[i][j] = false;
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
	
	public static boolean[][] getPossibleMoves() {
		return possibleMoves;
	}
	
}
