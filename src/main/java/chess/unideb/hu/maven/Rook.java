package chess.unideb.hu.maven;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Rook {
	private static Logger logger = LoggerFactory.getLogger(Rook.class);
	private static boolean[] isOkay = new boolean[4];
	private static boolean[] isLight = new boolean[4];
	private static boolean[] isDark = new boolean[4];
	private static String newPiece;
	private static String newType;
	private static int oldPosRow;
	private static int oldPosCol;
	private static int moveCounterDarkLeft = 0;
	private static int moveCounterDarkRight = 0;
	private static int moveCounterLightLeft = 0;
	private static int moveCounterLightRight = 0;

	public static int getMoveCounterDarkLeft() {
		return moveCounterDarkLeft;
	}



	public static void setMoveCounterDarkLeft(int moveCounterDarkLeft) {
		Rook.moveCounterDarkLeft = moveCounterDarkLeft;
	}



	public static int getMoveCounterDarkRight() {
		return moveCounterDarkRight;
	}



	public static void setMoveCounterDarkRight(int moveCounterDarkRight) {
		Rook.moveCounterDarkRight = moveCounterDarkRight;
	}



	public static int getMoveCounterLightLeft() {
		return moveCounterLightLeft;
	}



	public static void setMoveCounterLightLeft(int moveCounterLightLeft) {
		Rook.moveCounterLightLeft = moveCounterLightLeft;
	}



	public static int getMoveCounterLightRight() {
		return moveCounterLightRight;
	}



	public static void setMoveCounterLightDark(int moveCounterLightRight) {
		Rook.moveCounterLightRight = moveCounterLightRight;
	}

	private static boolean[][] possibleMoves = new boolean[8][8];
	private static Integer move[][][] = 
	{
		{
			{1,0},
			{0,1},
			{-1,0},
			{0,-1}
		},
		{
			{2,0},
			{0,2},
			{-2,0},
			{0,-2}
		},
		{	
			{3,0},
			{0,3},
			{-3,0},
			{0,-3}
		},
		{	
			{4,0},
			{0,4},
			{-4,0},
			{0,-4}
		},
		{
			{5,0},
			{0,5},
			{-5,0},
			{0,-5}
		},
		{
			{6,0},
			{0,6},
			{-6,0},
			{0,-6}
		},
		{
			{7,0},
			{0,7},
			{-7,0},
			{0,-7}
		},
		{
			{8,0},
			{0,8},
			{-8,0},
			{0,-8}
		}
	};
	
	public Rook(){
		
	}
	
	
	
	static void moves(int currPosRow, int currPosCol, JButton[][] chessSquare, boolean player){
		inits();
		for(int i = 0; i < 8; i++){
			 for(int j = 0; j < 4; j++){
				 if( ((currPosRow+move[i][j][0]) >= 0)  && ((currPosRow+move[i][j][0]) < 8) && ((currPosCol+move[i][j][1]) >= 0) && ((currPosCol+move[i][j][1]) < 8)) {
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
	
	public static void grabPiece(int i, int j) {
		GUIchess.drawPossibleMoves(possibleMoves, GUIchess.getChessSquare());
		newPiece = ChessPiece.chessPieces[i][j];
		newType = ChessPiece.chessPiecesTypes[i][j];
		oldPosRow = ChessPiece.chessPiecesPositions[i][j][0];
		oldPosCol = ChessPiece.chessPiecesPositions[i][j][1];
	}

	public static void placePiece(int i, int j, JButton[][] chessSquare) {
		try {	
			if(possibleMoves[i][j]){
				if(ChessPiece.chessPieces[oldPosRow][oldPosCol].compareTo("DarkRookRight") == 0) {
					moveCounterDarkRight++;
				} else if(ChessPiece.chessPieces[oldPosRow][oldPosCol].compareTo("DarkRookLeft") == 0) {
					moveCounterDarkLeft++;
				} else if(ChessPiece.chessPieces[oldPosRow][oldPosCol].compareTo("LightRookRight") == 0) {
					moveCounterLightRight++;
				} else if(ChessPiece.chessPieces[oldPosRow][oldPosCol].compareTo("LightRookLeft") == 0) {
					moveCounterLightLeft++;
				}
				
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
					chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getLightRook()));
				else
					chessSquare[i][j].setIcon(new ImageIcon(GUIchess.getDarkRook()));
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
				logger.info("DarkRookRight's number of moves: " + getMoveCounterDarkRight());
				logger.info("DarkRookLeft's number of moves: " + getMoveCounterDarkLeft());
				logger.info("LightRookRight's number of moves: " + getMoveCounterLightRight());
				logger.info("LightRookLeft's number of moves: " + getMoveCounterLightLeft());
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
