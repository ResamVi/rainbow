package edu.kit.informatik;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class RotateSquare implements Move {

    private Position position;
    private Position topRight;
    private Position bottomRight;
    private Position bottomLeft;
    
    /**
     * A square of tokens get switched clockwise.
     * 
     * @param pos of upper left token
     */
    public RotateSquare(Position pos) {
        this.position = pos;
        this.topRight = Position.at(position.x + 1, position.y);
        this.bottomRight = Position.at(position.x + 1, position.y + 1);
        this.bottomLeft = Position.at(position.x, position.y + 1);
    }
    
    @Override
    public boolean canBeApplied(Board board) {
        return board.containsPosition(position) && board.containsPosition(bottomRight);
    }

    @Override
    public void apply(Board board) {
        board.swapTokens(position, bottomLeft);
        board.swapTokens(bottomLeft, bottomRight);
        board.swapTokens(bottomRight, topRight);
    }

    @Override
    public Move reverse() {
        
        // Reverse inner class
        class ReverseRotateSquare extends RotateSquare {

            /**
             * @see RotateSqaure reversed
             * @param pos of upper left token
             */
            public ReverseRotateSquare(Position pos) {
                super(pos); 
            }
            
            @Override
            public void apply(Board board) {
                board.swapTokens(bottomRight, topRight);
                board.swapTokens(bottomLeft, bottomRight);
                board.swapTokens(position, bottomLeft);
            }
        }
        
        return new ReverseRotateSquare(position);
    }

    @Override
    public Set<Position> getAffectedPositions(Board board) {
        return new HashSet<Position>(Arrays.asList(position, topRight, bottomRight, bottomLeft));
    }

}
