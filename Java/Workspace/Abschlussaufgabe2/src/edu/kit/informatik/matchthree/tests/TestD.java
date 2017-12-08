package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Token;

public class TestD {

    @Test
    public void test1() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("O*"), "O*O;***;O*O;O*O");
        
        Set<Delta> deltas = new HashSet<Delta>(Arrays.asList(new Delta(1, 0), new Delta(0, 1)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        // Null
        try {
            MatchThreeGame game = new MatchThreeGame(board, null);
            fail();
        } catch (NullPointerException ex) { }
        
        // Null
        try {
            MatchThreeGame game = new MatchThreeGame(null, matcher);
            fail();
        } catch (NullPointerException ex) { }
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        
        assertEquals(0, game.getScore());
        
        
    }
    
    @Test
    public void test21() {
        
        MatchThreeBoard board = new MatchThreeBoard(Token.set("O*"), "O*O;***;O*O;O*O");
        
        Set<Delta> deltas = new HashSet<Delta>(Arrays.asList(new Delta(1, 0), new Delta(0, 1)));
        MaximumDeltaMatcher matcher = new MaximumDeltaMatcher(deltas);
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);

        // TODO: How do I test random shit?
        
        
    }

}
