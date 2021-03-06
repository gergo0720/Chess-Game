package chess.unideb.hu.maven;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gergo0720
 * Class of the game logic
 */
public class Game {
	
	/**
	 * Logger to debug, log information and warnings.
	 */
	private static Logger logger = LoggerFactory.getLogger(Game.class);
	
	/**
	 * Active player.
	 */
	private static boolean player; //Light - true, Dark - false
	
	/**
	 * Active player's name.
	 */
	private static String activePlayer;
	
	/**
	 * Sign to know if it is chess.
	 */
	private static boolean chess = false;
	
	/**
	 * Constructor of Game logic, set up piece vectors.
	 * @param fileName is the input file.
	 */
	public Game(String fileName) {
		new ChessPiece(fileName);
		saveXml.chessPiecesDark = new Vector<String>();
		saveXml.chessPiecesDark.add("DarkRookRight");
		saveXml.chessPiecesDark.add("DarkKnightRight");
		saveXml.chessPiecesDark.add("DarkBishopRight");
		saveXml.chessPiecesDark.add("DarkQueen");
		saveXml.chessPiecesDark.add("DarkKing");
		saveXml.chessPiecesDark.add("DarkBishopLeft");
		saveXml.chessPiecesDark.add("DarkKnightLeft");
		saveXml.chessPiecesDark.add("DarkRookLeft");
		saveXml.chessPiecesDark.add("DarkPawn1");
		saveXml.chessPiecesDark.add("DarkPawn2");
		saveXml.chessPiecesDark.add("DarkPawn3");
		saveXml.chessPiecesDark.add("DarkPawn4");
		saveXml.chessPiecesDark.add("DarkPawn5");
		saveXml.chessPiecesDark.add("DarkPawn6");
		saveXml.chessPiecesDark.add("DarkPawn7");
		saveXml.chessPiecesDark.add("DarkPawn8");
		
	saveXml.chessPiecesLight = new Vector<String>();
		saveXml.chessPiecesLight.add("LightRookLeft");
		saveXml.chessPiecesLight.add("LightKnightLeft");
		saveXml.chessPiecesLight.add("LightBishopLeft");
		saveXml.chessPiecesLight.add("LightQueen");
		saveXml.chessPiecesLight.add("LightKing");
		saveXml.chessPiecesLight.add("LightBishopRight");
		saveXml.chessPiecesLight.add("LightKnightRight");
		saveXml.chessPiecesLight.add("LightRookRight");
		saveXml.chessPiecesLight.add("LightPawn8");
		saveXml.chessPiecesLight.add("LightPawn7");
		saveXml.chessPiecesLight.add("LightPawn6");
		saveXml.chessPiecesLight.add("LightPawn5");
		saveXml.chessPiecesLight.add("LightPawn4");
		saveXml.chessPiecesLight.add("LightPawn3");
		saveXml.chessPiecesLight.add("LightPawn2");
		saveXml.chessPiecesLight.add("LightPawn1");
	}
	
	/**
	 * It is the controller of the moves of Pieces.
	 * @param i is the row coordinate of piece.
	 * @param j is the column coordinate of piece.
	 */
	static void setMoves(int i, int j) {
		if(player) {
			if (ChessPiece.chessPieces[i][j].toLowerCase().contains("lightrook")) {
				Rook.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("lightknight")) {
				Knight.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("lightbishop")) {
				Bishop.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("lightqueen")) {
				Queen.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("lightking")) {
				King.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("lightpawn")) {
				Pawn.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
		
			}
		} else{
			if (ChessPiece.chessPieces[i][j].toLowerCase().contains("darkrook")) {
				Rook.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("darkknight")) {
				Knight.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("darkbishop")) {
				Bishop.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("darkqueen")) {
				Queen.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("darkking")) {
				King.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			} else if (ChessPiece.chessPieces[i][j].toLowerCase().contains("darkpawn")) {
				Pawn.moves(i, j, GUIchess.getChessSquare(),Game.isPlayer());
				
			}
		}
		
	}
	
	/**
	 * Get who is active.
	 * @return true if light is active, false if dark is active.
	 */
	public static boolean isPlayer() {
		return player;
	}

	/**
	 * Set who is active.
	 * @param player is the active player.
	 */
	public static void setPlayer(boolean player) {
		Game.player = player;
	}
	
	/**
	 * Get the active player name.
	 * @return the name of the light player it {@code player} is true, else return with the name of the dark player.
	 */
	public static String getActivePlayer() {
		return Game.activePlayer;
	}
	
	/**
	 * Set the name of the active player.
	 * @param s is the name of the player.
	 */
	public static void setActivePlayer(String s) {
		Game.activePlayer = s;
	}

	/**
	 * It is the controller of grabbing pieces.
	 * @param i is the row coordinate of piece.
	 * @param j is the column coordinate of piece.
	 * @param chessPieces is the name of the piece.
	 */
	public static void grabPieces(int i, int j, String chessPieces) {
		new saveXml();
		if(chessPieces.toLowerCase().contains("pawn"))	
			Pawn.grabPiece(i,j);
		else if(chessPieces.toLowerCase().contains("knight"))
			Knight.grabPiece(i, j);
		else if(chessPieces.toLowerCase().contains("bishop"))	
			Bishop.grabPiece(i,j);
		else if(chessPieces.toLowerCase().contains("queen"))
			Queen.grabPiece(i, j);
		else if(chessPieces.toLowerCase().contains("king"))	
			King.grabPiece(i,j);
		else if(chessPieces.toLowerCase().contains("rook"))
			Rook.grabPiece(i, j);
		
	}

	/**
	 * It is the controller of placing pieces.
	 * @param i is the row coordinate of the piece.
	 * @param j is the column coordinate of the piece.
	 * @param chessSquare is the matrices of the chess board.
	 * @param chessPieces is the name of the piece.
	 */
	public static void placePieces(int i, int j, JButton[][] chessSquare, String chessPieces) {
		if(chessPieces.toLowerCase().contains("pawn"))
			Pawn.placePiece(i, j, chessSquare);
		else if(chessPieces.toLowerCase().contains("knight"))
			Knight.placePiece(i, j, chessSquare);
		if(chessPieces.toLowerCase().contains("bishop"))	
			Bishop.placePiece(i, j, chessSquare);
		else if(chessPieces.toLowerCase().contains("queen"))
			Queen.placePiece(i, j, chessSquare);
		if(chessPieces.toLowerCase().contains("king"))	
			King.placePiece(i, j, chessSquare);
		else if(chessPieces.toLowerCase().contains("rook"))
			Rook.placePiece(i, j, chessSquare);
		
		logger.info("Distance between kings: "+kingDistance(getPosRow("LightKing"), getPosCol("LightKing"), getPosRow("DarkKing"), getPosCol("DarkKing")).toString());
		if(kingDistance(getPosRow("LightKing"), getPosCol("LightKing"), getPosRow("DarkKing"), getPosCol("DarkKing")) == 1) {
			chessSituation();
		}
		King.chess(getPosRow("LightKing"), getPosCol("LightKing"), chessSquare, true);
		isChess(King.getPossibleChess(), chessSquare, "LightKing");
		King.chess(getPosRow("DarkKing"), getPosCol("DarkKing"), chessSquare, false);
		isChess(King.getPossibleChess(), chessSquare, "DarkKing");
	
	}
	
	/**
	 * If the king in chess and the player wants to move without interrupting the chess, the move will be unavailable.
	 */
	public static void chessSituation(){
		GUIchess.removeIcons();
		new ChessPiece(saveXml.getSavedFile());
		GUIchess.fillSquares();
		if (Game.isPlayer())
			Game.setActivePlayer(Players.getUserName() + " (világos) ");
		else
			Game.setActivePlayer(Players.getUserName2() + " (sötét) ");
		GUIchess.setLabelMessage(Game.getActivePlayer() + " következik!");
		setIsChess(false);
	}

	/**
	 * It decides whether king is in chess or not, if the opponent piece can see the king, the king is in chess.
	 * @param possibleChess is the possible moves of the kings.
	 * @param chessSquare is the matrices of the chess board.
	 * @param king is the name of the king.
	 */
	public static void isChess(boolean[][] possibleChess,
			JButton[][] chessSquare, String king) {
		for(int i = 0; i < possibleChess.length; i++) {
			for(int j = 0; j < possibleChess.length; j++) {
				if(possibleChess[i][j] && king.compareTo("DarkKing") == 0 ) {
					//chessSquare[i][j].setBackground(Color.BLACK);
					if(ChessPiece.chessPieces[i][j].toLowerCase().contains("lightpawn")) {
						Pawn.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Pawn.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare) && 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Pawn.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Pawn.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
					
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("lightknight")) {
						Knight.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Knight.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare )&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Knight.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Knight.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
			
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("lightbishop")) {
						Bishop.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Bishop.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("DarkKing"), getPosCol("DarkKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Bishop.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Bishop.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("DarkKing"), getPosCol("DarkKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
				
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("lightqueen")) {
						Queen.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Queen.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("DarkKing"), getPosCol("DarkKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Queen.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Queen.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("DarkKing"), getPosCol("DarkKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
						
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("lightrook")) {
						Rook.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Rook.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("DarkKing"), getPosCol("DarkKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Rook.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Rook.getPossibleMoves(), chessSquare,"DarkKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("DarkKing"), getPosCol("DarkKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
					}
				} else if(possibleChess[i][j] && king.compareTo("LightKing") == 0 ) {
					//chessSquare[i][j].setBackground(Color.BLACK);
					if(ChessPiece.chessPieces[i][j].toLowerCase().contains("darkpawn")) {
						Pawn.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Pawn.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Pawn.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Pawn.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
					
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("darkknight")) {
						Knight.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Knight.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Knight.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Knight.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
			
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("darkbishop")) {
						Bishop.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Bishop.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("LightKing"), getPosCol("LightKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Bishop.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Bishop.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("LightKing"), getPosCol("LightKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
				
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("darkqueen")) {
						Queen.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Queen.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("LightKing"), getPosCol("LightKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Queen.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Queen.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("LightKing"), getPosCol("LightKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
						
					} else if(ChessPiece.chessPieces[i][j].toLowerCase().contains("darkrook")) {
						Rook.moves(i, j, chessSquare, !Game.isPlayer());
						if(kingVisible(Rook.getPossibleMoves(), chessSquare,"LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("LightKing"), getPosCol("LightKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
						}
						Rook.moves(i, j, chessSquare, Game.isPlayer());
						if(kingVisible(Rook.getPossibleMoves(), chessSquare, "LightKing")) {
							logger.info("Chess by " + ChessPiece.chessPieces[i][j]);
							if(!canInterruptChess(i, j, chessSquare, getPosRow("LightKing"), getPosCol("LightKing")) &&
									!canHitChessBy(i, j, chessSquare)&& 
									kingInChess(king, chessSquare))
								logger.info("Game is Over");
							chessSquare[i][j].setBackground(Color.BLUE);
							setIsChess(true);
							chessSituation();
							
						}
					}
				}
			}
		}
	}
	
	/**
	 * If the chess piece can see the opponent king, set the chess square of the king red.
	 * @param possibleMoves is the possible moves of the piece.
	 * @param chessSquare is the matrices of chess board.
	 * @param king is the name of the king.
	 * @return true if piece can see the king or false if it is not.
	 */
	public static boolean kingVisible(boolean[][] possibleMoves,
			JButton[][] chessSquare, String king) {
		for(int i = 0; i < possibleMoves.length; i++) {
			for(int j = 0; j < possibleMoves.length; j++) {
				if(possibleMoves[i][j] && (ChessPiece.chessPieces[i][j].compareTo(king) == 0)) {
					chessSquare[i][j].setBackground(Color.RED);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Set a matrices with true, but only between the chess giver and the king in chess.
	 * @param r1 is the first row position.
	 * @param c1 is the first column position.
	 * @param r2 is the second row position.
	 * @param c2 is the second column position.
	 * @return the filled matrices
	 */
	public static boolean[][] setChessRoad(int r1, int c1, int r2, int c2) {
		boolean[][] result = new boolean[8][8];
		for(int i = 0; i < result.length; i++) {
			for(int j = 0; j < result.length; j++) {
				result[i][j] = false;
			}
		}
		
		Vector<Integer> rows = new Vector<Integer>();
		Vector<Integer> cols = new Vector<Integer>();
		
		if(r1 < r2) {
			for(int i = r1+1; i < r2; i++) {
				rows.add(i);
			}
		} else if (r1 == r2) { 
			if(c1 < c2) {
				for(int i = c1+1; i < c2; i++) {
					rows.add(r1);
				}
			} else {
				for(int i = c1-1; i > c2; i--) {
					rows.add(r1);
				}
			}
		} else {
			for(int i = r1-1; i > r2; i--) {
				rows.add(i);
			}
		}
		
		if(c1 < c2) {
			for(int i = c1+1; i < c2; i++) {
				cols.add(i);
			}
		}  else if (c1 == c2) { 
			if(r1 < r2) {
				for(int i = r1+1; i < r2; i++) {
					cols.add(c1);
				}
			} else {
				for(int i = r1-1; i > r2; i--) {
					cols.add(c1);
				}
			}
		} else {
			for(int i = c1-1; i > c2; i--) {
				cols.add(i);
			}
		}
		
		for(int i = 0; i < rows.size(); i++) {
			result[rows.elementAt(i)][cols.elementAt(i)] = true;
		}
		
		return result;
	}
	
	/**
	 * Get the row coordinate of the piece.
	 * @param piece is the name of the piece
	 * @return the row coordinate of the piece.
	 */
	public static int getPosRow(String piece) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(ChessPiece.chessPieces[i][j].compareTo(piece) == 0) {
					return ChessPiece.chessPiecesPositions[i][j][0];
				}
			}
		}
		return -1;
	}
	
	/**
	 * Get the column coordinate of the piece.
	 * @param piece is the name of the piece.
	 * @return the column coordinate of the piece.
	 */
	public static int getPosCol(String piece) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(ChessPiece.chessPieces[i][j].compareTo(piece) == 0) {
					return ChessPiece.chessPiecesPositions[i][j][1];
				}
			}
		}
		return -1;
	}
	
	/**
	 * Get whether it is chess.
	 * @return chess true if it is chess, false if it is not.
	 */
	public static boolean getIsChess() {
		return chess;
	}
	
	/**
	 * Set the message which player is in chess.
	 * @param ischess is the sign there is chess or no.
	 */
	public static void setIsChess(boolean ischess) {
		if(Game.isPlayer()) {
			GUIchess.setLabelMessage(Players.getUserName() + " is in chess");
		}
		else
			GUIchess.setLabelMessage(Players.getUserName2() + " is in chess");
		Game.chess = ischess;
	}
	
	/**
	 * Compute the distance between the two kings.
	 * @param k1r is the first king's row coordinate.
	 * @param k1c is the first king's column coordinate.
	 * @param k2r is the second king's row coordinate.
	 * @param k2c is the second king's column coordinate
	 * @return the distance between the two kings.
	 */
	public static Integer kingDistance(int k1r, int k1c , int k2r, int k2c) {
		return (int) Math.sqrt((k1r-k2r)*(k1r-k2r) + (k1c-k2c)*(k1c-k2c));
	}

	
	/**
	 * Examine king in chess has any possible move or not.
	 * @param king is the name of the king.
	 * @param chessSquare is the matrices of the chess board.
	 * @return true if king has possible move or false it it has not.
	 */
	public static boolean kingInChess(String king, JButton[][] chessSquare) {
		boolean[][] pM2 = new boolean[8][8];
		boolean[][] pM = new boolean[8][8];
		Vector<Integer> row = new Vector<Integer>();
		Vector<Integer> col = new Vector<Integer>();
		if(!Game.isPlayer()) {	
			King.moves(getPosRow(king), getPosCol(king), GUIchess.getChessSquare(), player);
			pM2 = King.getPossibleMoves();
			if(king.compareTo("DarkKing") == 0) {
				if(isOnBoard("LightRookRight", chessSquare)) {
					Rook.moves(getPosRow("LightRookRight"), getPosCol("LightRookRight"), chessSquare, player);
					pM = Rook.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
	
				}
				
				if(isOnBoard("LightRookLeft", chessSquare)) {
					Rook.moves(getPosRow("LightRookLeft"), getPosCol("LightRookLeft"), chessSquare, player);
					pM = Rook.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightKnightRight", chessSquare)) {
					Knight.moves(getPosRow("LightKnightRight"), getPosCol("LightKnightRight"), chessSquare, player);
					pM = Knight.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightKnightLeft", chessSquare)) {
					Knight.moves(getPosRow("LightKnightLeft"), getPosCol("LightKnightLeft"), chessSquare, player);
					pM = Knight.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightBishopRight", chessSquare)) {
					Bishop.moves(getPosRow("LightBishopRight"), getPosCol("LightBishopRight"), chessSquare, player);
					pM = Bishop.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightBishopLeft", chessSquare)) {
					Bishop.moves(getPosRow("LightBishopLeft"), getPosCol("LightBishopLeft"), chessSquare, player);
					pM = Bishop.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn1", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn1"), getPosCol("LightPawn1"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn2", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn2"), getPosCol("LightPawn2"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn3", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn3"), getPosCol("LightPawn3"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn4", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn4"), getPosCol("LightPawn4"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn5", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn5"), getPosCol("LightPawn5"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn6", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn6"), getPosCol("LightPawn6"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn7", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn7"), getPosCol("LightPawn7"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightPawn8", chessSquare)) {
					Pawn.moves(getPosRow("LightPawn8"), getPosCol("LightPawn8"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("LightQueen", chessSquare)) {
					Queen.moves(getPosRow("LightQueen"), getPosCol("LightQueen"), chessSquare, player);
					pM = Queen.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				for(Integer i: row) {
					logger.info(king+"'s in chess possible moves: " + i + " ");
				}
				for(Integer i: col) {
					logger.info(king+"'s in chess possible moves: " + i + " ");
				}
				
				if(row.size() == King.getNumberOfMoves(pM2))
					return true;
				
				row.clear();
				col.clear();
			}
		
		} if(Game.isPlayer()) {	
			King.moves(getPosRow(king), getPosCol(king), GUIchess.getChessSquare(), player);
			pM2 = King.getPossibleMoves();
			if(king.compareTo("LightKing") == 0) {
				if(isOnBoard("DarkRookRight", chessSquare)) {
					Rook.moves(getPosRow("DarkRookRight"), getPosCol("DarkRookRight"), chessSquare, player);
					pM = Rook.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
	
				}
				
				if(isOnBoard("DarkRookLeft", chessSquare)) {
					Rook.moves(getPosRow("DarkRookLeft"), getPosCol("DarkRookLeft"), chessSquare, player);
					pM = Rook.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkKnightRight", chessSquare)) {
					Knight.moves(getPosRow("DarkKnightRight"), getPosCol("DarkKnightRight"), chessSquare, player);
					pM = Knight.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkKnightLeft", chessSquare)) {
					Knight.moves(getPosRow("DarkKnightLeft"), getPosCol("DarkKnightLeft"), chessSquare, player);
					pM = Knight.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkBishopRight", chessSquare)) {
					Bishop.moves(getPosRow("DarkBishopRight"), getPosCol("DarkBishopRight"), chessSquare, player);
					pM = Bishop.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkBishopLeft", chessSquare)) {
					Bishop.moves(getPosRow("DarkBishopLeft"), getPosCol("DarkBishopLeft"), chessSquare, player);
					pM = Bishop.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn1", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn1"), getPosCol("DarkPawn1"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn2", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn2"), getPosCol("DarkPawn2"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn3", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn3"), getPosCol("DarkPawn3"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn4", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn4"), getPosCol("DarkPawn4"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn5", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn5"), getPosCol("DarkPawn5"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn6", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn6"), getPosCol("DarkPawn6"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn7", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn7"), getPosCol("DarkPawn7"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkPawn8", chessSquare)) {
					Pawn.moves(getPosRow("DarkPawn8"), getPosCol("DarkPawn8"), chessSquare, player);
					pM = Pawn.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
				
				if(isOnBoard("DarkQueen", chessSquare)) {
					Queen.moves(getPosRow("DarkQueen"), getPosCol("DarkQueen"), chessSquare, player);
					pM = Queen.getPossibleMoves();
					for(int i = 0; i < pM.length; i++) {
						for(int j = 0; j < pM.length; j++) {
							if(pM2[i][j] == true && pM[i][j] == true && pM[i][j] == pM2[i][j]) {
								if((row.contains(i) && col.contains(j)) && (col.elementAt(row.indexOf(i)) != j)) {
									row.add(i);
									col.add(j);
								} else if(!row.contains(i) || !col.contains(j)) {
									row.add(i);
									col.add(j);
								}
							}
						}
					}
				}
			}
			
			
			for(Integer i: row) {
				logger.info("LightKing's in chess possible moves: " + i + " ");
			}
			for(Integer i: col) {
				logger.info("LightKing's in chess possible moves: " + i + " ");
			}
		
			logger.info(row.size() + " " + King.getNumberOfMoves(pM2));
			if(row.size() == King.getNumberOfMoves(pM2))
				return true;
			
			row.clear();
			col.clear();
		}
			
			
		return false;
	}
	
	/**
	 * Examine whether there is a piece which can hit the chess giver.
	 * @param posR is the row position of the chess giver.
	 * @param posC is the column position of the chess giver.
	 * @param chessSquare is the matrices of chess board.
	 * @return true if there is a piece which is able to hit the chess giver or false if there is no one.
	 */
	public static boolean canHitChessBy(int posR, int posC, JButton[][] chessSquare) {
		boolean[][] pM = new boolean[8][8];
		if(!Game.isPlayer()) {
			if(isOnBoard("DarkRookRight", chessSquare)) {
				Rook.moves(getPosRow("DarkRookRight"), getPosCol("DarkRookRight"), chessSquare, player);
				pM = Rook.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkRookLeft", chessSquare)) {
				Rook.moves(getPosRow("DarkRookLeft"), getPosCol("DarkRookLeft"), chessSquare, player);
				pM = Rook.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkKnightRight", chessSquare)) {
				Knight.moves(getPosRow("DarkKnightRight"), getPosCol("DarkKnightRight"), chessSquare, player);
				pM = Knight.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkKnightLeft", chessSquare)) {
				Knight.moves(getPosRow("DarkKnightLeft"), getPosCol("DarkKnightLeft"), chessSquare, player);
				pM = Knight.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkBishopRight", chessSquare)) {
				Bishop.moves(getPosRow("DarkBishopRight"), getPosCol("DarkBishopRight"), chessSquare, player);
				pM = Bishop.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}	
			
			if(isOnBoard("DarkBishopLeft", chessSquare)) {
				Bishop.moves(getPosRow("DarkBishopLeft"), getPosCol("DarkBishopLeft"), chessSquare, player);
				pM = Bishop.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn1", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn1"), getPosCol("DarkPawn1"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn2", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn2"), getPosCol("DarkPawn2"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn3", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn3"), getPosCol("DarkPawn3"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn4", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn4"), getPosCol("DarkPawn4"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn5", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn5"), getPosCol("DarkPawn5"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn6", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn6"), getPosCol("DarkPawn6"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn7", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn7"), getPosCol("DarkPawn7"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkPawn8", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn8"), getPosCol("DarkPawn8"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("DarkQueen", chessSquare)) {
				Queen.moves(getPosRow("DarkQueen"), getPosCol("DarkQueen"), chessSquare, player);
				pM = Queen.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
				
			if(isOnBoard("DarkKing", chessSquare)) {
				King.moves(getPosRow("DarkKing"), getPosCol("DarkKing"), chessSquare, player);
				pM = King.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
				
		} else if(Game.isPlayer()) {
			if(isOnBoard("LightRookRight", chessSquare)) {
				Rook.moves(getPosRow("LightRookRight"), getPosCol("LightRookRight"), chessSquare, player);
				pM = Rook.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightRookLeft", chessSquare)) {
				Rook.moves(getPosRow("LightRookLeft"), getPosCol("LightRookLeft"), chessSquare, player);
				pM = Rook.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightKnightRight", chessSquare)) {
				Knight.moves(getPosRow("LightKnightRight"), getPosCol("LightKnightRight"), chessSquare, player);
				pM = Knight.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightKnightLeft", chessSquare)) {
				Knight.moves(getPosRow("LightKnightLeft"), getPosCol("LightKnightLeft"), chessSquare, player);
				pM = Knight.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightBishopRight", chessSquare)) {
				Bishop.moves(getPosRow("LightBishopRight"), getPosCol("LightBishopRight"), chessSquare, player);
				pM = Bishop.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}	
			
			if(isOnBoard("LightBishopLeft", chessSquare)) {
				Bishop.moves(getPosRow("LightBishopLeft"), getPosCol("LightBishopLeft"), chessSquare, player);
				pM = Bishop.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn1", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn1"), getPosCol("LightPawn1"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn2", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn2"), getPosCol("LightPawn2"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn3", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn3"), getPosCol("LightPawn3"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn4", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn4"), getPosCol("LightPawn4"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn5", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn5"), getPosCol("LightPawn5"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn6", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn6"), getPosCol("LightPawn6"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn7", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn7"), getPosCol("LightPawn7"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightPawn8", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn8"), getPosCol("LightPawn8"), chessSquare, player);
				pM = Pawn.getHitableMoves();
				if(pM[posR][posC] == true)
					return true;
			}
			
			if(isOnBoard("LightQueen", chessSquare)) {
				Queen.moves(getPosRow("LightQueen"), getPosCol("LightQueen"), chessSquare, player);
				pM = Queen.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
				
			if(isOnBoard("LightKing", chessSquare)) {
				King.moves(getPosRow("LightKing"), getPosCol("LightKing"), chessSquare, player);
				pM = King.getPossibleMoves();
				if(pM[posR][posC] == true)
					return true;
			}
		}
	
		
		return false;
	}
	
	/**
	 * Examine whether there is a piece, which can move between the king in chess and the opponent which gives the chess.
	 * @param posR is the row position of the chess giver.
	 * @param posC is the column position of the chess giver.
	 * @param chessSquare is the matrices of chess board.
	 * @param kingPosR is the row position of the king in chess.
	 * @param kingPosC is the column position of the king in chess.
	 * @return true if there is a piece which is able to interrupt or false if there is no one.
	 */
	public static boolean canInterruptChess(int posR, int posC, JButton[][] chessSquare, int kingPosR, int kingPosC) {
		boolean[][] pM = new boolean[8][8];
		boolean[][] pM2 = new boolean[8][8];
		pM = setChessRoad(posR, posC, kingPosR, kingPosC);
		
		if(!Game.isPlayer()) {
			if(isOnBoard("DarkRookLeft", chessSquare)) {
				Rook.moves(getPosRow("DarkRookLeft"), getPosCol("DarkRookLeft"), chessSquare, player);
				pM2 = Rook.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkRookLeft " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkRookRight", chessSquare)) {
				Rook.moves(getPosRow("DarkRookRight"), getPosCol("DarkRookRight"), chessSquare, player);
				pM2 = Rook.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkRookRight " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkBishopLeft", chessSquare)) {
				Bishop.moves(getPosRow("DarkBishopLeft"), getPosCol("DarkBishopLeft"), chessSquare, player);
				pM2 = Bishop.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkBishopLeft " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkBishopRight", chessSquare)) {
				Bishop.moves(getPosRow("DarkBishopRight"), getPosCol("DarkBishopRight"), chessSquare, player);
				pM2 = Bishop.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkBishopRight " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkQueen", chessSquare)) {
				Queen.moves(getPosRow("DarkQueen"), getPosCol("DarkQueen"), chessSquare, player);
				pM2 = Queen.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkQueen " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn1", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn1"), getPosCol("DarkPawn1"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn1 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn2", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn2"), getPosCol("DarkPawn2"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn2 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn3", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn3"), getPosCol("DarkPawn3"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn3 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn4", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn4"), getPosCol("DarkPawn4"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn4 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn5", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn5"), getPosCol("DarkPawn5"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn5 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn6", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn6"), getPosCol("DarkPawn6"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn6 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn7", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn7"), getPosCol("DarkPawn7"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn7 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("DarkPawn8", chessSquare)) {
				Pawn.moves(getPosRow("DarkPawn8"), getPosCol("DarkPawn8"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("DarkPawn8 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
		} else if(Game.isPlayer()) {
			if(isOnBoard("LightRookLeft", chessSquare)) {
				Rook.moves(getPosRow("LightRookLeft"), getPosCol("LightRookLeft"), chessSquare, player);
				pM2 = Rook.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightRookLeft " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightRookRight", chessSquare)) {
				Rook.moves(getPosRow("LightRookRight"), getPosCol("LightRookRight"), chessSquare, player);
				pM2 = Rook.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightRookRight " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightBishopLeft", chessSquare)) {
				Bishop.moves(getPosRow("LightBishopLeft"), getPosCol("LightBishopLeft"), chessSquare, player);
				pM2 = Bishop.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightBishopLeft " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightBishopRight", chessSquare)) {
				Bishop.moves(getPosRow("LightBishopRight"), getPosCol("LightBishopRight"), chessSquare, player);
				pM2 = Bishop.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightBishopRight " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightQueen", chessSquare)) {
				Queen.moves(getPosRow("LightQueen"), getPosCol("LightQueen"), chessSquare, player);
				pM2 = Queen.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightQueen " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn1", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn1"), getPosCol("LightPawn1"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn1 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn2", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn2"), getPosCol("LightPawn2"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn2 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn3", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn3"), getPosCol("LightPawn3"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn3 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn4", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn4"), getPosCol("LightPawn4"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn4 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn5", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn5"), getPosCol("LightPawn5"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn5 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn6", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn6"), getPosCol("LightPawn6"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn6 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn7", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn7"), getPosCol("LightPawn7"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn7 " + i + " " + j );
							return true;
						}
					}
				}
			}
			
			if(isOnBoard("LightPawn8", chessSquare)) {
				Pawn.moves(getPosRow("LightPawn8"), getPosCol("LightPawn8"), chessSquare, player);
				pM2 = Pawn.getPossibleMoves();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(pM[i][j] == true && pM[i][j] == pM2[i][j]) {
							logger.info("LightPawn8 " + i + " " + j );
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Examine a piece is on board or not.
	 * @param piece is the name of the piece.
	 * @param chessSquare is the matrices of chess board.
	 * @return true if the piece is on board, false if it is not.
	 */
	public static boolean isOnBoard(String piece, JButton[][] chessSquare) {
		for(int i = 0; i < chessSquare.length; i++) {
			for(int j = 0; j < chessSquare.length; j++) {
				if(piece.compareTo(ChessPiece.chessPieces[i][j]) == 0) {
					return true;
				}
			}
		}
		return false;
	}
}
