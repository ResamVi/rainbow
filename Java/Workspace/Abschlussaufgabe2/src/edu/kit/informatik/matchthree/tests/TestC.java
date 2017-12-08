package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;

public class TestC {

    @Test
    public void abbildung14() {
        
        Set<Delta> deltas = new HashSet<>(Arrays.asList(new Delta(0, 1)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("An"), "An;An;AA");
        
        Set<Position> expected = new HashSet<Position>(
                Arrays.asList(new Position(0, 0), new Position(0, 1), new Position(0, 2)));
        assertEquals(new HashSet<Set<Position>>(Arrays.asList(expected)), matcher.match(board, new Position(0, 2)));
        
    }
    
    @Test
    public void abbildung15() {
        
        Set<Delta> deltas = new HashSet<>(Arrays.asList(new Delta(0, -1)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("nO"), "nOO;OOO;nnO");
        
        Set<Position> expected = new HashSet<Position>(
                Arrays.asList(new Position(2, 0), new Position(2, 1), new Position(2, 2)));
        assertEquals(new HashSet<Set<Position>>(Arrays.asList(expected)), matcher.match(board, new Position(2, 1)));
        
    }
    
    @Test
    public void abbildung16() {
        
        Set<Delta> deltas = new HashSet<>(Arrays.asList(new Delta(1, 0)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("nO"), "nOO;OOO;nnO");
        
        Set<Position> expected = new HashSet<Position>(
                Arrays.asList(new Position(0, 1), new Position(1, 1), new Position(2, 1)));
        assertEquals(new HashSet<Set<Position>>(Arrays.asList(expected)), matcher.match(board, new Position(2, 1)));
        
    }
    
    @Test
    public void abbildung17() {
        
        Set<Delta> deltas = new HashSet<>(Arrays.asList(new Delta(1, 1), new Delta(-1, 1)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("nO*"), "nOO;O*O;nnO");
        
        Set<Position> expected = new HashSet<Position>(
                Arrays.asList(new Position(0, 1), new Position(1, 0), new Position(2, 1)));
        assertEquals(new HashSet<Set<Position>>(Arrays.asList(expected)), matcher.match(board, new Position(2, 1)));
        
    }
    
    @Test
    public void abbildung18() {
        
        Set<Delta> deltas = new HashSet<>(Arrays.asList(new Delta(1, 0), new Delta(0, 1)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("nO"), "nOO;OOO;nnO");
        
        Set<Position> expected = new HashSet<Position>(
                Arrays.asList(new Position(0, 1), new Position(1, 0), new Position(1, 1),
                        new Position(2, 0), new Position(2, 1), new Position(2, 2)));
        assertEquals(new HashSet<Set<Position>>(Arrays.asList(expected)), matcher.match(board, new Position(2, 1)));
        
    }
    
    @Test
    public void abbildung19() {
        
        Set<Delta> deltas = new HashSet<>(Arrays.asList(new Delta(0, -2)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("nO"), "O ;n ;O ;n ;O ");
        
        Set<Position> expected = new HashSet<Position>(
                Arrays.asList(new Position(0, 0), new Position(0, 2), new Position(0, 4)));
        assertEquals(new HashSet<Set<Position>>(Arrays.asList(expected)), matcher.match(board, new Position(0, 0)));
        
    }
}
