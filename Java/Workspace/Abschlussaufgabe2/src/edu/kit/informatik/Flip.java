package edu.kit.informatik;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class Flip implements Move {

    private final Position position;
    private final Position neighbourPos;
    
    /**
     * Switches the token at the given position with its
     * right neighbour token.
     * 
     * @param pos of switched token
     * @param bottom if true then flips bottom neighbour
     * when false switches with right neighbour
     */
    public Flip(Position pos, boolean bottom) {
        this.position = pos;
        this.neighbourPos = bottom ? Position.at(pos.x, pos.y + 1) : Position.at(pos.x + 1, pos.y);
    }
    
    @Override
    public boolean canBeApplied(Board board) {
        return board.containsPosition(position) && board.containsPosition(neighbourPos);
    }

    @Override
    public void apply(Board board) { // TODO: Check for null
        if (!canBeApplied(board))
            throw new BoardDimensionException();
        
        board.swapTokens(position, neighbourPos);
    }

    @Override
    public Move reverse() {
        return this;
    }

    @Override
    public Set<Position> getAffectedPositions(Board board) {
        return new HashSet<Position>(Arrays.asList(position, neighbourPos));
    }

}
