package chess.unideb.hu.maven;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.Test;

public class GameTest {

	@Test
	public void kingDistanceTest() {
		Game game = new Game("/base.xml");
		Integer actual = game.kingDistance(0, 5, 7, 5);
		Integer expected = 7;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(5, 5, 7, 7);
		boolean[][] expected = new boolean[8][8];
		expected[6][6] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest2() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(5, 1, 2, 4);
		boolean[][] expected = new boolean[8][8];
		expected[3][3] = true;
		expected[4][2] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest3() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(2, 2, 5, 2);
		boolean[][] expected = new boolean[8][8];
		expected[3][2] = true;
		expected[4][2] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest4() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(2, 2, 2, 5);
		boolean[][] expected = new boolean[8][8];
		expected[2][3] = true;
		expected[2][4] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest5() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(2, 5, 2, 2);
		boolean[][] expected = new boolean[8][8];
		expected[2][3] = true;
		expected[2][4] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest6() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(1, 5, 4, 5);
		boolean[][] expected = new boolean[8][8];
		expected[2][5] = true;
		expected[3][5] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest7() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(4, 5, 1, 5);
		boolean[][] expected = new boolean[8][8];
		expected[2][5] = true;
		expected[3][5] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest8() {
		Game game = new Game("/base.xml");
		boolean[][] actual = game.setChessRoad(4, 3, 1, 6);
		boolean[][] expected = new boolean[8][8];
		expected[3][4] = true;
		expected[2][5] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void kingVisibleTest() {
		Game game = new Game("/base.xml");
		ChessPiece chesspiece = new ChessPiece("/base.xml");
		GUIchess gc = new GUIchess(game);
		boolean[][] pM = new boolean[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				pM[i][j] = false;
			}
		}
		pM[4][5] = true;
		chesspiece.chessPieces[4][5] = "DarkKing";
		chesspiece.chessPieces[0][4] = "EMPTY";
		boolean actual = game.kingVisible(pM, gc.getChessSquare(), "DarkKing");
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void kingVisibleTest2() {
		Game game = new Game("/base.xml");
		ChessPiece chesspiece = new ChessPiece("/base.xml");
		GUIchess gc = new GUIchess(game);
		boolean[][] pM = new boolean[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				pM[i][j] = false;
			}
		}
		pM[3][5] = true;
		chesspiece.chessPieces[4][5] = "DarkKing";
		chesspiece.chessPieces[0][4] = "EMPTY";
		boolean actual = game.kingVisible(pM, gc.getChessSquare(), "DarkKing");
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPosRowTest() {
		Game game = new Game("/base.xml");
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPieces[4][3] = "DarkQueen";
		cp.chessPieces[0][3] = "EMPTY";
		cp.chessPiecesPositions[0][3][0] = -1;
		cp.chessPiecesPositions[0][3][1] = -1;
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		int actual = game.getPosRow("DarkQueen");
		int expected = 4;
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPosRowTest1() {
		Game game = new Game("/base.xml");
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPieces[0][3] = "EMPTY";
		cp.chessPiecesPositions[0][3][0] = -1;
		cp.chessPiecesPositions[0][3][1] = -1;
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		int actual = game.getPosRow("DarkQueen");
		int expected = -1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPosColTest() {
		Game game = new Game("/base.xml");
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPieces[4][3] = "DarkQueen";
		cp.chessPieces[0][3] = "EMPTY";
		cp.chessPiecesPositions[0][3][0] = -1;
		cp.chessPiecesPositions[0][3][1] = -1;
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		int actual = game.getPosCol("DarkQueen");
		int expected = 3;
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPosColTest1() {
		Game game = new Game("/base.xml");
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPieces[0][3] = "EMPTY";
		cp.chessPiecesPositions[0][3][0] = -1;
		cp.chessPiecesPositions[0][3][1] = -1;
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		int actual = game.getPosCol("DarkQueen");
		int expected = -1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void kingInChessTest() {
		Game game = new Game("/base.xml");
		GUIchess gc = new GUIchess(game);
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPiecesPositions[4][5][0] = 4;
		cp.chessPiecesPositions[4][5][1] = 5;
		cp.chessPiecesPositions[0][7][0] = -1;
		cp.chessPiecesPositions[0][7][1] = -1;
		cp.chessPieces[4][5] = "DarkRookLeft";
		cp.chessPieces[0][7] = "EMPTY";
		
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		cp.chessPiecesPositions[7][4][0] = -1;
		cp.chessPiecesPositions[7][4][1] = -1;
		cp.chessPieces[4][3] = "LightKing";
		cp.chessPieces[7][4] = "EMPTY";
		boolean actual = game.kingInChess("LightKing", gc.getChessSquare());
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	public void kingInChessTest1() {
		Game game = new Game("/base.xml");
		GUIchess gc = new GUIchess(game);
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPiecesPositions[4][5][0] = 4;
		cp.chessPiecesPositions[4][5][1] = 5;
		cp.chessPiecesPositions[0][1][0] = -1;
		cp.chessPiecesPositions[0][1][1] = -1;
		cp.chessPieces[4][5] = "DarkRookRight";
		cp.chessPieces[0][1] = "EMPTY";
		
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		cp.chessPiecesPositions[7][4][0] = -1;
		cp.chessPiecesPositions[7][4][1] = -1;
		cp.chessPieces[4][3] = "LightKing";
		cp.chessPieces[7][4] = "EMPTY";
		boolean actual = game.kingInChess("LightKing", gc.getChessSquare());
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	public void kingInChessTest3() {
		Game game = new Game("/base.xml");
		GUIchess gc = new GUIchess(game);
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPiecesPositions[2][5][0] = 2;
		cp.chessPiecesPositions[2][5][1] = 5;
		cp.chessPiecesPositions[0][7][0] = -1;
		cp.chessPiecesPositions[0][7][1] = -1;
		cp.chessPieces[2][5] = "DarkBishopLeft";
		cp.chessPieces[0][5] = "EMPTY";
		
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		cp.chessPiecesPositions[7][4][0] = -1;
		cp.chessPiecesPositions[7][4][1] = -1;
		cp.chessPieces[4][3] = "LightKing";
		cp.chessPieces[7][4] = "EMPTY";
		boolean actual = game.kingInChess("LightKing", gc.getChessSquare());
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	public void kingInChessTest2() {
		Game game = new Game("/base.xml");
		GUIchess gc = new GUIchess(game);
		game.setPlayer(false);
		boolean actual = game.kingInChess("DarkKing", gc.getChessSquare());
		boolean expected = true;
		assertEquals(expected, actual);
	}

}