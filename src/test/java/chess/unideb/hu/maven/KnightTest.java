package chess.unideb.hu.maven;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class KnightTest {
	Game game = new Game("/base.xml");
	ChessPiece cp = new ChessPiece("/base.xml");
	Knight q = new Knight();
	JButton[][] chessSquare = new JButton[8][8];
	@Test
	public void movesTest() {
		q.moves(5, 0, chessSquare, true);
		boolean[][] actual = q.getPossibleMoves();
		boolean[][] expected = new boolean[8][8];
		expected[3][1] = true;
		expected[4][2] = true;
		
		assertEquals(expected, actual);
	}

}
