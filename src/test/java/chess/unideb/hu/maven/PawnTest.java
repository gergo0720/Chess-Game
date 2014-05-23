package chess.unideb.hu.maven;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

public class PawnTest {
	Game game = new Game("/base.xml");
	ChessPiece cp = new ChessPiece("/base.xml");
	Pawn p = new Pawn();
	JButton[][] chessSquare = new JButton[8][8];
	
	@Test
	public void movesTest() {
		p.moves(5, 0, chessSquare, true);
		boolean[][] actual = p.getPossibleMoves();
		boolean[][] expected = new boolean[8][8];
		expected[4][0] = true;
		
		assertEquals(expected, actual);
	}
	
	public void movesTes2() {
		p.moves(2, 0, chessSquare, false);
		boolean[][] actual = p.getPossibleMoves();
		boolean[][] expected = new boolean[8][8];
		expected[3][0] = true;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void hitsTest() {
		p.hits(5, 0, chessSquare, true);
		boolean[][] actual = p.getHitableMoves();
		boolean[][] expected = new boolean[8][8];
		expected[4][1] = true;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void hitsTest2() {
		p.hits(2, 0, chessSquare, false);
		boolean[][] actual = p.getHitableMoves();
		boolean[][] expected = new boolean[8][8];
		expected[3][1] = true;
		
		assertEquals(expected, actual);
	}
	
}
