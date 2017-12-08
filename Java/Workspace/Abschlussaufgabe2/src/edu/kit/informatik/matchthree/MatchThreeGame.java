package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 *
 */
public class MatchThreeGame implements Game {
    
    private Board   board;
    private Matcher matcher;
    private int     score = 0;
    
    private Set<Position> allPositions;
    
    /**
     * Start a game
     * 
     * @param board that is played on
     * @param matcher the rules that should be applied to the game
     */
    public MatchThreeGame(Board board, Matcher matcher) {
        
        if (board == null) // TODO: Empty board instead?
            throw new NullPointerException();
            
        this.board = board;
        setMatcher(matcher);
        
        // Set of all coordinates of board
        this.allPositions = new HashSet<Position>();
        for (int row = 0; row < board.getRowCount(); row++) {
            for (int column = 0; column < board.getColumnCount(); column++)
                allPositions.add(Position.at(column, row));
        }
    }

    private void scoreMatches(Set<Position> positions) {
        
        Set<Set<Position>> matches;
        Set<Position> positionsToCheck = positions;
        int multiplier = 1;
        
        
        do {
            matches = matcher.matchAll(board, positionsToCheck);
    
            // Filter out all sets of size less than 3
            Set<Set<Position>> copy = new HashSet<>(matches);
            for (Set<Position> set : copy) {
                if (set.size() < 3)
                    matches.remove(set);
            }
            
            // Filter out all sets that are subsets of others
            for (Set<Position> thisSet : matches) {
                for (Set<Position> otherSet : matches) {
                
                    // Check one set with all others except itself
                    if (!thisSet.equals(otherSet) && otherSet.containsAll(thisSet)) {
                        
                       // Delete from matches if subset found 
                       matches.remove(otherSet);
                    }
                }
            }
            
            // Increase score
            int baseScore = 0;
            for (Set<Position> set : matches)
                baseScore += 3 + (set.size() - 3) * 2;
            
            score += baseScore * matches.size() * multiplier;
            
            // Remove tokens
            for (Set<Position> set : matches) {
                for (Position position : set)
                    board.setTokenAt(position, null);
            }
            
            // Fill board
            board.moveTokensToBottom();
            board.fillWithTokens();
            
            // In case of another iteration, check all positions
            positionsToCheck = new HashSet<>(allPositions);
            
            // Increase chain-reaction multiplier
            multiplier++;
            
        // Repeat this process until no matches found
        } while(matches.size() > 0);
        
        // TODO: Do equal sets exist in matches?
    }
    
    @Override
    public void initializeBoardAndStart() {
        board.moveTokensToBottom();
        board.fillWithTokens();
        
        scoreMatches(allPositions);
    }

    @Override
    public void acceptMove(Move move) {
        
        // If trying to move tokens outside of board
        if (!move.canBeApplied(board))
            throw new BoardDimensionException();
        
        move.apply(board);
        
        // Determine score
        Set<Position> changes = move.getAffectedPositions(board);
        scoreMatches(changes);
        
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setMatcher(Matcher matcher) {
        if (matcher == null)
            throw new NullPointerException();
        
        this.matcher = matcher;
    }

}
