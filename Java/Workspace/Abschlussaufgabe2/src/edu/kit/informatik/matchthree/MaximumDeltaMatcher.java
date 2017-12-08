package edu.kit.informatik.matchthree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.MatcherInitializationException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;

/**
 *
 */
public class MaximumDeltaMatcher implements Matcher {
    
    private final Set<Delta> deltas;
    
    /**
     * The exercise's implementation of their match-three rules.
     * 
     * @param deltas allowed steps
     */
    public MaximumDeltaMatcher(Set<Delta> deltas) {

        if (deltas == null || deltas.contains(new Delta(0, 0)) || deltas.size() < 1)
            throw new MatcherInitializationException();
        
        this.deltas = deltas; // TODO: filter deltas that cause immediate out of bounds
        
    }

    @Override
    public Set<Set<Position>> match(Board board, Position initial) throws BoardDimensionException {

        // Initial position should be insde board dimensions
        if (!board.containsPosition(initial))
            throw new BoardDimensionException();
        
        // Put all matched tokens in set
        Set<Position> match = new HashSet<>(Arrays.asList(initial));
        
        // Token at initial position
        Token initialToken = board.getTokenAt(initial);
        
        // Empty tokens cannot be matched
        if (initialToken == null)
            return new HashSet<Set<Position>>();

        // Start to iterate through board
        int currentSize;
        do {

            // Control variable, when matches found: match set will increases in size, therefore loop again 
            currentSize = match.size();
            
            // We cannot modify and iterate at the same time, thus copy
            Set<Position> currentMatches = new HashSet<>(match);
            
            // Start from every position that has been matched
            for (Position start : currentMatches) {
            
                // Iterate through board by delta
                for (Delta delta : deltas) {
                    
                    // Go delta forwards
                    Position forwards = start.plus(delta);

                    // Check if not out of bounds
                    if (board.containsPosition(forwards)) {
                        
                        // When they match add to set
                        if (board.getTokenAt(forwards).equals(initialToken))
                            match.add(forwards);
                    }
                    
                    // Go delta backwards
                    Position backwards = start.plus(delta.negate());

                    // Check if not out of bounds
                    if (board.containsPosition(backwards)) {
                        
                        // When they match add to set
                        if (board.getTokenAt(backwards).equals(initialToken))
                            match.add(backwards);
                    }
                }
            }
            
        // do this process until no new matches can be found anymore
        } while (currentSize < match.size());
        
        return new HashSet<Set<Position>>(Arrays.asList(match));
    }

    @Override
    public Set<Set<Position>> matchAll(Board board, Set<Position> initial) throws BoardDimensionException {
        
        Set<Set<Position>> matches = new HashSet<Set<Position>>();
        
        for (Position position : initial)
            matches.addAll(match(board, position));
        
        return matches;
        
    }
}
