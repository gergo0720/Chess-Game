package chess.unideb.hu.maven;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class BishopTest {
	Game game = new Game("/base.xml");
	ChessPiece cp = new ChessPiece("/base.xml");
	Bishop b = new Bishop();
	JButton[][] chessSquare = new JButton[8][8];
	@Test
	public void movesTest() {
		b.moves(5, 0, chessSquare, true);
		boolean[][] actual = b.getPossibleMoves();
		boolean[][] expected = new boolean[8][8];
		expected[4][1] = true;
		expected[3][2] = true;
		expected[2][3] = true;
		expected[1][4] = true;
		
		assertEquals(expected, actual);
	}

}
