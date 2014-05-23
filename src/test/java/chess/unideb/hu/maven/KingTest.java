package chess.unideb.hu.maven;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class KingTest {
	Game game = new Game("/base.xml");
	ChessPiece cp = new ChessPiece("/base.xml");
	King k = new King();
	JButton[][] chessSquare = new JButton[8][8];
	
	@Test
	public void movesTest() {
		k.moves(5, 0, chessSquare, true);
		boolean[][] actual = k.getPossibleMoves();
		boolean[][] expected = new boolean[8][8];
		expected[4][0] = true;
		expected[5][1] = true;
		expected[4][1] = true;
		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void chessTest() {
		k.chess(5, 0, chessSquare, true);
		boolean[][] actual = k.getPossibleChess();
		boolean[][] expected = new boolean[8][8];
		expected[1][0] = true;
		expected[2][0] = true;
		expected[3][0] = true;
		expected[4][0] = true;
		expected[5][1] = true;
		expected[5][2] = true;
		expected[5][3] = true;
		expected[5][4] = true;
		expected[5][5] = true;
		expected[5][6] = true;
		expected[5][7] = true;
		expected[4][1] = true;
		expected[3][2] = true;
		expected[2][3] = true;
		expected[1][4] = true;
		expected[3][1] = true;
		expected[4][2] = true;
		
		assertEquals(expected, actual);
	}

}
