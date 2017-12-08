package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class TestB {

    @Test
    public void testB22() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("AY*=X"), "*AX;Y**;*=X");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.flipRight(new Position(2, 0));

        // Exercise example
        try {
            move.apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        MatchThreeBoard board2 = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        
        // Exercise example
        move.apply(board2);
        assertEquals(board2.toTokenString(), "*A=X;Y**=;X*=X;=XX*");
        
    }
    
    @Test
    public void testB22reverse() {
        
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.flipRight(new Position(2, 0));
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        
        // Exercise examples
        move.apply(board);
        move.reverse().apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;X*=X;=XX*");
        
        move.reverse().apply(board);
        move.apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;X*=X;=XX*");
        
        move.reverse().reverse().apply(board);
        assertEquals(board.toTokenString(), "*A=X;Y**=;X*=X;=XX*");
    }

    @Test
    public void testB22affected() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.flipRight(new Position(2, 0));
        
        assertEquals(move.getAffectedPositions(board), 
                new HashSet<Position>(Arrays.asList(new Position(3, 0), new Position(2, 0))));
        
    }
    
    @Test
    public void testB23() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("AY*=X"), "*AX;Y**;*=X");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.flipDown(new Position(0, 2));

        // Exercise example
        try {
            move.apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        MatchThreeBoard board2 = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        
        // Exercise example
        move.apply(board2);
        assertEquals(board2.toTokenString(), "*AX=;Y**=;=*=X;XXX*");
        
    }
    
    @Test
    public void testB23reverse() {
        
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.flipDown(new Position(0, 2));
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        
        // Exercise examples
        move.apply(board);
        move.reverse().apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;X*=X;=XX*");
        
        move.reverse().apply(board);
        move.apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;X*=X;=XX*");
        
        move.reverse().reverse().apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;=*=X;XXX*");
    }

    @Test
    public void testB23affected() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.flipDown(new Position(0, 2));
        
        assertEquals(move.getAffectedPositions(board), 
                new HashSet<Position>(Arrays.asList(new Position(0, 2), new Position(0, 3))));
        
    }
    
    @Test
    public void testB24() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*AXY**X*= "), "*AX;Y**;X*=");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateSquareClockwise(new Position(0, 2));

        // Out of bounds
        try {
            move.apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        try {
            moveFactory.rotateSquareClockwise(new Position(1, 2)).apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        try {
            moveFactory.rotateSquareClockwise(new Position(0, 2)).apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        // Exercise example
        moveFactory.rotateSquareClockwise(new Position(1, 0)).apply(board);
        assertEquals(board.toTokenString(), "**A;Y*X;X*=");
    }
    
    @Test
    public void testB24reverse() {
        
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateSquareClockwise(new Position(0, 2));
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        
        // Exercise examples
        move.apply(board);
        move.reverse().apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;X*=X;=XX*");
        
        move.reverse().apply(board);
        move.apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;X*=X;=XX*");
        
        move.reverse().reverse().apply(board);
        assertEquals(board.toTokenString(), "*AX=;Y**=;*X=X;X=X*");
        
        MatchThreeBoard board2 = new MatchThreeBoard(Token.set("*AXY**X*="), "*AX;Y**;X*=");
        moveFactory.rotateSquareClockwise(new Position(0, 1)).reverse().apply(board2);
        assertEquals(board2.toTokenString(), "*AX;***;YX=");
    }

    @Test
    public void testB24affected() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX=;Y**=;X*=X;=XX*");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateSquareClockwise(new Position(1, 0));
        
        assertEquals(move.getAffectedPositions(board), 
                new HashSet<Position>(
                        Arrays.asList(new Position(1, 0), new Position(2, 1), new Position(1, 1), new Position(2, 0))));
        
    }
    
    @Test
    public void testB25() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*AXY**X*="), "*AX;Y**;X*=");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateColumnDown(0);

        // Out of bounds
        try {
            moveFactory.rotateColumnDown(3).apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        try {
            moveFactory.rotateColumnDown(4).apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        // Exercise example
        moveFactory.rotateColumnDown(0).apply(board);
        assertEquals(board.toTokenString(), "XAX;***;Y*=");
    }
    
    @Test
    public void testB25reverse() {
        
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateColumnDown(2);
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX;Y**;X*=");
        
        // Exercise examples
        move.apply(board);
        move.reverse().apply(board);
        assertEquals(board.toTokenString(), "*AX;Y**;X*=");
        
        move.reverse().apply(board);
        move.apply(board);
        assertEquals(board.toTokenString(), "*AX;Y**;X*=");
        
        move.reverse().reverse().apply(board);
        assertEquals(board.toTokenString(), "*A*;Y*=;X*X"); // TODO: Reverse functions are wrong
        
        MatchThreeBoard board2 = new MatchThreeBoard(Token.set("*AXY**X*="), "*AX;Y**;X*=");
        moveFactory.rotateColumnDown(2).reverse().apply(board2);
        assertEquals(board2.toTokenString(), "*A*;Y*=;X*X");
    }

    @Test
    public void testB25affected() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX;Y**;X*=");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateColumnDown(0);
        
        assertEquals(move.getAffectedPositions(board), 
                new HashSet<Position>(
                        Arrays.asList(new Position(0, 0), new Position(0, 1), new Position(0, 2))));
        
    }
    
    @Test
    public void testB26() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*AXY**X*="), "*AX;Y**;X*=");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateRowRight(0);

        // Out of bounds
        try {
            moveFactory.rotateRowRight(3).apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        try {
            moveFactory.rotateRowRight(4).apply(board);
            fail();
        } catch (BoardDimensionException ex) { }
        
        // Exercise example
        moveFactory.rotateRowRight(0).apply(board);
        assertEquals(board.toTokenString(), "X*A;Y**;X*=");
    }
    
    @Test
    public void testB26reverse() {
        
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateRowRight(2);
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX;Y**;X*=");
        
        // Exercise examples
        move.apply(board);
        move.reverse().apply(board);
        assertEquals(board.toTokenString(), "*AX;Y**;X*=");
        
        move.reverse().apply(board);
        move.apply(board);
        assertEquals(board.toTokenString(), "*AX;Y**;X*=");
        
        //move.reverse().reverse().apply(board);
        //assertEquals(board.toTokenString(), "*A*;Y*=;X*X"); // TODO: Reverse functions are wrong
        
        MatchThreeBoard board2 = new MatchThreeBoard(Token.set("*AXY**X*="), "*AX;Y**;X*=");
        moveFactory.rotateRowRight(2).reverse().apply(board2);
        assertEquals(board2.toTokenString(), "*AX;Y**;*=X");
    }

    @Test
    public void testB26affected() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("*A=YX"), "*AX;Y**;X*=");
        MoveFactoryImplementation moveFactory = new MoveFactoryImplementation();
        Move move = moveFactory.rotateRowRight(2);
        
        assertEquals(move.getAffectedPositions(board), 
                new HashSet<Position>(
                        Arrays.asList(new Position(0, 2), new Position(1, 2), new Position(2, 2))));
        
    }
}
