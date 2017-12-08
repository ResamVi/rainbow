package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Tests for rotate square rotation move
 * 
 * @author Micha Hanselmann & Florian Pfisterer
 */
public class MoveRotateSquareClockwiseMoveTest {
    
    @Test
    public void apply() {
        
        Board board = new MatchThreeBoard(Token.set("*AXY="), "*AX;Y**;X*=");
        Move rotateCW = new MoveFactoryImplementation().rotateSquareClockwise(Position.at(1, 0));
        assertTrue(rotateCW.canBeApplied(board));
        rotateCW.apply(board);
        assertEquals("**A;Y*X;X*=", board.toTokenString());
    }

    @Test
    public void applyWithEmpties1() {

        final Board board = new MatchThreeBoard(Token.set("abc"), "a b; b ");
        final Move rotateCW = new MoveFactoryImplementation().rotateSquareClockwise(Position.at(0, 0));

        assertTrue(rotateCW.canBeApplied(board));
        rotateCW.apply(board);
        assertEquals(" ab;b  ", board.toTokenString());
    }

    @Test
    public void reverseApplyWithEmpties1() {

        final Board board = new MatchThreeBoard(Token.set("abc"), " ab;b  ");
        final Move rotateCW = new MoveFactoryImplementation().rotateSquareClockwise(Position.at(0, 0)).reverse();

        assertTrue(rotateCW.canBeApplied(board));
        rotateCW.apply(board);
        assertEquals("a b; b ", board.toTokenString());
    }
    
    @Test
    public void reverse1() {
        
        Board board = new MatchThreeBoard(Token.set("*AXY="), "*AX;Y**;X*=");
        Move rotateCCW = new MoveFactoryImplementation().rotateSquareClockwise(Position.at(1, 0)).reverse();
        assertTrue(rotateCCW.canBeApplied(board));
        rotateCCW.apply(board);
        assertEquals("*X*;YA*;X*=", board.toTokenString());
    }
    
    @Test
    public void reverse2() {
        
        Board board = new MatchThreeBoard(Token.set("*AXY="), "*AX;Y**;X*=");
        Move rotateCCW = new MoveFactoryImplementation().rotateSquareClockwise(Position.at(0, 1)).reverse();
        assertTrue(rotateCCW.canBeApplied(board));
        rotateCCW.apply(board);
        assertEquals("*AX;***;YX=", board.toTokenString());
    }

    @Test
    public void affectedPositions1() {

        Board board = new MatchThreeBoard(Token.set("*AXY="), "*AX;Y**;X*=");
        Position current = Position.at(1, 0);
        Move rotateCW = new MoveFactoryImplementation().rotateSquareClockwise(current);
        TestUtils.assertSetEquals(rotateCW.getAffectedPositions(board), Stream
                .of(current, current.plus(1, 0), current.plus(1, 1), current.plus(0, 1)).collect(Collectors.toSet()));
    }

    @Test
    public void affectedPositions2() {

        Board board = new MatchThreeBoard(Token.set("*AXY="), "*AX;Y**;X*=");
        Position current = Position.at(1, 0);
        Move rotateCCW = new MoveFactoryImplementation().rotateSquareClockwise(current).reverse();
        TestUtils.assertSetEquals(rotateCCW.getAffectedPositions(board), Stream
                .of(current, current.plus(1, 0), current.plus(1, 1), current.plus(0, 1)).collect(Collectors.toSet()));
    }

}
