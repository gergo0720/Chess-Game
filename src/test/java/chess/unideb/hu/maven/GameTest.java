package chess.unideb.hu.maven;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.Test;

public class GameTest {
	Game game = new Game("/base.xml");
	//GUIchess g = new GUIchess(game);
	JButton[][] chessSquare = new JButton[8][8];
	@Test
	public void kingDistanceTest() {
		Integer actual = game.kingDistance(0, 5, 7, 5);
		Integer expected = 7;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest() {
		boolean[][] actual = game.setChessRoad(5, 5, 7, 7);
		boolean[][] expected = new boolean[8][8];
		expected[6][6] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest2() {
		boolean[][] actual = game.setChessRoad(5, 1, 2, 4);
		boolean[][] expected = new boolean[8][8];
		expected[3][3] = true;
		expected[4][2] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest3() {
		boolean[][] actual = game.setChessRoad(2, 2, 5, 2);
		boolean[][] expected = new boolean[8][8];
		expected[3][2] = true;
		expected[4][2] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest4() {
		boolean[][] actual = game.setChessRoad(2, 2, 2, 5);
		boolean[][] expected = new boolean[8][8];
		expected[2][3] = true;
		expected[2][4] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest5() {
		boolean[][] actual = game.setChessRoad(2, 5, 2, 2);
		boolean[][] expected = new boolean[8][8];
		expected[2][3] = true;
		expected[2][4] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest6() {
		boolean[][] actual = game.setChessRoad(1, 5, 4, 5);
		boolean[][] expected = new boolean[8][8];
		expected[2][5] = true;
		expected[3][5] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest7() {
		boolean[][] actual = game.setChessRoad(4, 5, 1, 5);
		boolean[][] expected = new boolean[8][8];
		expected[2][5] = true;
		expected[3][5] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void setChessRoadTest8() {
		boolean[][] actual = game.setChessRoad(4, 3, 1, 6);
		boolean[][] expected = new boolean[8][8];
		expected[3][4] = true;
		expected[2][5] = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void kingVisibleTest() {
		ChessPiece cp = new ChessPiece("/base.xml");
		boolean[][] pM = new boolean[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				pM[i][j] = false;
			}
		}
		pM[4][5] = true;
		cp.chessPieces[4][5] = "DarkKing";
		cp.chessPieces[0][4] = "EMPTY";
		GUIchess g = new GUIchess(game);
		g.setVisible(false);
		boolean actual = game.kingVisible(pM, g.getChessSquare(), "DarkKing");
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void kingVisibleTest2() {
		ChessPiece cp = new ChessPiece("/base.xml");
		boolean[][] pM = new boolean[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				pM[i][j] = false;
			}
		}
		pM[3][5] = true;
		cp.chessPieces[4][5] = "DarkKing";
		cp.chessPieces[0][4] = "EMPTY";
		boolean actual = game.kingVisible(pM, GUIchess.getChessSquare(), "DarkKing");
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPosRowTest() {
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
		boolean actual = game.kingInChess("LightKing", GUIchess.getChessSquare());
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	public void kingInChessTest1() {
		ChessPiece cp = new ChessPiece("/base.xml");
		cp.chessPiecesPositions[4][5][0] = 4;
		cp.chessPiecesPositions[4][5][1] = 5;
		cp.chessPiecesPositions[0][0][0] = -1;
		cp.chessPiecesPositions[0][0][1] = -1;
		cp.chessPieces[4][5] = "DarkRookRight";
		cp.chessPieces[0][0] = "EMPTY";
		
		cp.chessPiecesPositions[4][3][0] = 4;
		cp.chessPiecesPositions[4][3][1] = 3;
		cp.chessPiecesPositions[7][4][0] = -1;
		cp.chessPiecesPositions[7][4][1] = -1;
		cp.chessPieces[4][3] = "LightKing";
		cp.chessPieces[7][4] = "EMPTY";
		boolean actual = game.kingInChess("LightKing", chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void kingInChessTest2() {
		game.setPlayer(false);
		boolean actual = game.kingInChess("DarkKing", chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void canHitByChessTest() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][0] = "EMPTY";
		pc.chessPieces[5][5] = "DarkRookRight";
		pc.chessPiecesPositions[0][0][0] = -1;
		pc.chessPiecesPositions[0][0][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][0] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 2, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest2() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][7] = "EMPTY";
		pc.chessPieces[5][5] = "DarkRookLeft";
		pc.chessPiecesPositions[0][7][0] = -1;
		pc.chessPiecesPositions[0][7][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][7] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 2, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest3() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][6] = "EMPTY";
		pc.chessPieces[5][5] = "DarkKnightLeft";
		pc.chessPiecesPositions[0][6][0] = -1;
		pc.chessPiecesPositions[0][6][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][6] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(3, 4, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest4() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][1] = "EMPTY";
		pc.chessPieces[5][5] = "DarkKnightRight";
		pc.chessPiecesPositions[0][1][0] = -1;
		pc.chessPiecesPositions[0][1][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][1] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(3, 4, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest5() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][2] = "EMPTY";
		pc.chessPieces[5][5] = "DarkBishopRight";
		pc.chessPiecesPositions[0][2][0] = -1;
		pc.chessPiecesPositions[0][2][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][2] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(3, 3, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest6() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][5] = "EMPTY";
		pc.chessPieces[5][5] = "DarkBishopLeft";
		pc.chessPiecesPositions[0][5][0] = -1;
		pc.chessPiecesPositions[0][5][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][5] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(3, 3, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest7() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][3] = "EMPTY";
		pc.chessPieces[5][5] = "DarkQueen";
		pc.chessPiecesPositions[0][3][0] = -1;
		pc.chessPiecesPositions[0][3][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][3] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(3, 3, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest8() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][0] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn1";
		pc.chessPiecesPositions[1][0][0] = -1;
		pc.chessPiecesPositions[1][0][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][0] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest9() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][1] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn2";
		pc.chessPiecesPositions[1][1][0] = -1;
		pc.chessPiecesPositions[1][1][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][1] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest10() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][2] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn3";
		pc.chessPiecesPositions[1][2][0] = -1;
		pc.chessPiecesPositions[1][2][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][2] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest11() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][3] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn4";
		pc.chessPiecesPositions[1][3][0] = -1;
		pc.chessPiecesPositions[1][3][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][3] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest12() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][4] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn5";
		pc.chessPiecesPositions[1][4][0] = -1;
		pc.chessPiecesPositions[1][4][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][4] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest13() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][5] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn6";
		pc.chessPiecesPositions[1][5][0] = -1;
		pc.chessPiecesPositions[1][5][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][5] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest14() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][6] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn7";
		pc.chessPiecesPositions[1][6][0] = -1;
		pc.chessPiecesPositions[1][6][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][6] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest15() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[1][7] = "EMPTY";
		pc.chessPieces[4][5] = "DarkPawn8";
		pc.chessPiecesPositions[1][7][0] = -1;
		pc.chessPiecesPositions[1][7][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][7] = "NONE";
		pc.chessPiecesTypes[4][5] = "DARK";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest16() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(false);
		
		pc.chessPieces[0][4] = "EMPTY";
		pc.chessPieces[5][5] = "DarkKing";
		pc.chessPiecesPositions[0][4][0] = -1;
		pc.chessPiecesPositions[0][4][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[0][4] = "NONE";
		pc.chessPiecesTypes[5][5] = "DARK";
		boolean actual = game.canHitChessBy(4, 4, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest17() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[7][0] = "EMPTY";
		pc.chessPieces[5][5] = "LightRookRight";
		pc.chessPiecesPositions[7][0][0] = -1;
		pc.chessPiecesPositions[7][0][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[7][0] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(5, 2, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest18() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[7][7] = "EMPTY";
		pc.chessPieces[5][5] = "LightRookLeft";
		pc.chessPiecesPositions[7][7][0] = -1;
		pc.chessPiecesPositions[7][7][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[7][7] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(5, 2, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest19() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[7][6] = "EMPTY";
		pc.chessPieces[5][5] = "LightKnightLeft";
		pc.chessPiecesPositions[7][6][0] = -1;
		pc.chessPiecesPositions[7][6][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[7][6] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(3, 4, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest20() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[7][1] = "EMPTY";
		pc.chessPieces[5][5] = "LightKnightRight";
		pc.chessPiecesPositions[7][1][0] = -1;
		pc.chessPiecesPositions[7][1][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[7][1] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(3, 4, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest21() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[7][2] = "EMPTY";
		pc.chessPieces[5][5] = "LightBishopRight";
		pc.chessPiecesPositions[7][2][0] = -1;
		pc.chessPiecesPositions[7][2][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[7][2] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(3, 3, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest22() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[7][5] = "EMPTY";
		pc.chessPieces[5][5] = "LightBishopLeft";
		pc.chessPiecesPositions[7][5][0] = -1;
		pc.chessPiecesPositions[7][5][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[7][5] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(3, 3, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest23() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[7][3] = "EMPTY";
		pc.chessPieces[5][5] = "LightQueen";
		pc.chessPiecesPositions[7][3][0] = -1;
		pc.chessPiecesPositions[7][3][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[7][3] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(3, 3, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest24() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][0] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn8";
		pc.chessPiecesPositions[6][0][0] = -1;
		pc.chessPiecesPositions[6][0][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[1][0] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest25() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][1] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn7";
		pc.chessPiecesPositions[6][1][0] = -1;
		pc.chessPiecesPositions[6][1][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[6][1] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest26() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][2] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn6";
		pc.chessPiecesPositions[6][2][0] = -1;
		pc.chessPiecesPositions[6][2][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[6][2] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest27() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][3] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn5";
		pc.chessPiecesPositions[6][3][0] = -1;
		pc.chessPiecesPositions[6][3][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[6][3] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest28() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][4] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn4";
		pc.chessPiecesPositions[6][4][0] = -1;
		pc.chessPiecesPositions[6][4][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[6][4] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest29() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][5] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn3";
		pc.chessPiecesPositions[6][5][0] = -1;
		pc.chessPiecesPositions[6][5][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[6][5] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest30() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][6] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn2";
		pc.chessPiecesPositions[6][6][0] = -1;
		pc.chessPiecesPositions[6][6][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[6][6] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest31() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][7] = "EMPTY";
		pc.chessPieces[4][5] = "LightPawn1";
		pc.chessPiecesPositions[6][7][0] = -1;
		pc.chessPiecesPositions[6][7][1] = -1;
		pc.chessPiecesPositions[4][5][0] = 4;
		pc.chessPiecesPositions[4][5][1] = 5;
		pc.chessPiecesTypes[6][7] = "NONE";
		pc.chessPiecesTypes[4][5] = "Light";
		boolean actual = game.canHitChessBy(5, 5, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest32() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][4] = "EMPTY";
		pc.chessPieces[5][5] = "LightKing";
		pc.chessPiecesPositions[6][4][0] = -1;
		pc.chessPiecesPositions[6][4][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[6][4] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(4, 4, chessSquare);
		boolean expected = true;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void canHitByChessTest33() {
		ChessPiece pc = new ChessPiece("/base.xml");
		game.setPlayer(true);
		
		pc.chessPieces[6][4] = "EMPTY";
		pc.chessPieces[5][5] = "LightKing";
		pc.chessPiecesPositions[6][4][0] = -1;
		pc.chessPiecesPositions[6][4][1] = -1;
		pc.chessPiecesPositions[5][5][0] = 5;
		pc.chessPiecesPositions[5][5][1] = 5;
		pc.chessPiecesTypes[6][4] = "NONE";
		pc.chessPiecesTypes[5][5] = "Light";
		boolean actual = game.canHitChessBy(2, 4, chessSquare);
		boolean expected = false;
		assertEquals(expected, actual);
		
	}
	
	
	 @Test
	 public void canInterruptChess() {
		 game.setPlayer(false);
		 boolean actual = game.canInterruptChess(2, 0, chessSquare, 2, 7);
		 boolean expected = true;
		 assertEquals(expected, actual);
	 }
	 
	 @Test
	 public void canInterruptChess2() {
		 game.setPlayer(true);
		 boolean actual = game.canInterruptChess(5, 0, chessSquare, 5, 7);
		 boolean expected = true;
		 assertEquals(expected, actual);
	 }
}