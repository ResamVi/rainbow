package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.DeterministicStrategy;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.IllegalTokenException;
import edu.kit.informatik.matchthree.framework.exceptions.NoFillingStrategyException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

public class TestA {

    /**
     * Constructor initialization
     */
    @Test
    public void testA13() {

        // Example given
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        assertEquals("A*s;OX ;+Y=", board.toTokenString());

        Board boardEmpty = new MatchThreeBoard(Token.set("whosdaking"), 3, 3);
        assertTrue(boardEmpty.toTokenString().equals("   ;   ;   "));

        Board boardEmpty2 = new MatchThreeBoard(Token.set("julienis"), "   ;   ;   ");
        assertTrue(boardEmpty2.toTokenString().equals("   ;   ;   "));

    }

    @Test
    public void testA14() {

        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        assertTrue(board.getColumnCount() == 3);
        assertTrue(board.getRowCount() == 3);

        Board board2 = new MatchThreeBoard(Token.set("ABC"), 2, 3);
        assertTrue(board2.getColumnCount() == 2);
        assertTrue(board2.getRowCount() == 3);

        Board boardEmpty2 = new MatchThreeBoard(Token.set("julienis"), "   ;   ;   ;   ");
        assertTrue(boardEmpty2.getColumnCount() == 3);
        assertTrue(boardEmpty2.getRowCount() == 4);
    }

    @Test
    public void testA15() {
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        assertTrue(board.getAllValidTokens().equals(Token.set("A*sOX+Y="))); // lol
    }

    @Test
    public void testA16() {
        
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        for (int row = 0; row < board.getRowCount(); row++) {
            for (int column = 0; column < board.getColumnCount(); column++) {
                
                Token token = board.getTokenAt(new Position(column, row));
                
                if (token == null) // Skip empty
                    continue;
                
                assertEquals(token, new Token("A*sOX +Y=".charAt(column + row * board.getColumnCount())));
            }

        }
        
        Board emptyBoard = new MatchThreeBoard(Token.set("AB"), 3, 3);
        assertEquals(null, emptyBoard.getTokenAt(new Position(0, 0)));
    }
    
    @Test
    public void TestA17() {
        
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        board.setTokenAt(new Position(0, 0), new Token('A'));
        assertEquals(board.getTokenAt(new Position(0, 0)), new Token('A'));
        
        // Wrong dimension
        try {
            board.setTokenAt(new Position(4, 4), new Token('A'));
            fail();
        } catch (BoardDimensionException ex) { }
        
        // Illegal Token
        try {
            board.setTokenAt(new Position(0, 0), new Token('B'));
            fail();
        } catch (IllegalTokenException ex) { }
    }
    
    @Test
    public void TestA18() {
        
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        
        for (int i = 0; i < board.getColumnCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++)
                assertTrue(board.containsPosition(new Position(i, j)));
        }
    }
    
    @Test
    public void TestA19() {
        
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        board.swapTokens(new Position(0, 0), new Position(1, 0));
        assertEquals(board.toTokenString(), "*As;OX ;+Y=");
        
        // Empty
        Board emptyBoard = new MatchThreeBoard(Token.set("AB"), 3, 3);
        emptyBoard.swapTokens(new Position(0, 0), new Position(0, 1));
        assertEquals(emptyBoard.toTokenString(), "   ;   ;   ");
        
        // Illegal Dimension
        try {
            board.swapTokens(new Position(4, 4), new Position(2, 2));
            fail();
        } catch (BoardDimensionException ex) { }
    }
    
    @Test
    public void TestA110() {
        
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        board.removeTokensAt(new HashSet<Position>(Arrays.asList(
                new Position(0, 1), 
                new Position(1, 1), 
                new Position(2, 1)
                )));
        assertEquals(board.toTokenString(), "A*s;   ;+Y=");
        
        // Wrong Dimension
        try {
            Board board2 = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
            board2.removeTokensAt(new HashSet<Position>(Arrays.asList(new Position(4, 1))));
        } catch (BoardDimensionException ex) { }
        
    }
    
    @Test
    public void TestA111() {
        
        try {
            Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
            board.fillWithTokens();
        } catch (NoFillingStrategyException ex) { }
        
        Board board = new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
        board.setFillingStrategy(new DeterministicStrategy());
        //board.fillWithTokens(); TODO: ?
    }
    
    @Test
    public void TestA113() {
        
        Board board = new MatchThreeBoard(Token.set("+A*Y"), "A AA;++  ; *A*;Y  Y");
        board.moveTokensToBottom();
        assertEquals("    ;A  A;++A*;Y*AY", board.toTokenString());
    }
}
