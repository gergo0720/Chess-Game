package chess.unideb.hu.maven;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class QueenTest {
	Game game = new Game("/base.xml");
	ChessPiece cp = new ChessPiece("/base.xml");
	Queen q = new Queen();
	JButton[][] chessSquare = new JButton[8][8];
	@Test
	public void movesTest() {
		q.moves(5, 0, chessSquare, true);
		boolean[][] actual = q.getPossibleMoves();
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
		
		assertEquals(expected, actual);
	}

}
