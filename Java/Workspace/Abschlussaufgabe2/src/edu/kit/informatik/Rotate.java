package edu.kit.informatik;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class Rotate implements Move {

    private final int       index;
    private final boolean   column;
    
    /**
     * Switches the token at the given position with its
     * right neighbour token.
     * 
     * @param index of row/column to rotate
     * @param column if true then rotates the column 
     * when false rotates rows
     */
    public Rotate(int index, boolean column) {
        this.index = index;
        this.column = column; 
    }
    
    @Override
    public boolean canBeApplied(Board board) {
        
        if (column)
            return board.containsPosition(Position.at(index, 0));
        else
            return board.containsPosition(Position.at(0, index));
    }

    @Override
    public void apply(Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException();
        
        if (column) {

            // Start from bottom, rotate each pair upwards
            for (int column = board.getRowCount() - 1; column > 0; column--)
                board.swapTokens(Position.at(index, column), Position.at(index, column - 1));
        
        } else {
            
            // Start from right, rotate each pair leftwards
            for (int row = board.getColumnCount() - 1; row > 0; row--)
                board.swapTokens(Position.at(row, index), Position.at(row - 1, index));
            
        }
    }

    @Override
    public Move reverse() {
        
        // Reverse inner class
        class ReverseRotate extends Rotate {

            /**
             * @see Rotate reversed
             * @param index of row/column to rotate
             * @param column if true then rotates the column 
             * when false rotates rows
             */
            public ReverseRotate(int index, boolean column) {
                super(index, column); 
            }
            
            @Override
            public void apply(Board board) {
                
                if (column) {

                    // Start from top, rotate each pair downwards
                    for (int column = 0; column < board.getRowCount() - 1; column++)
                        board.swapTokens(Position.at(index, column), Position.at(index, column + 1));
                
                } else {
                    
                    // Start from left, rotate each pair rightwards
                    for (int row = 0; row < board.getColumnCount() - 1; row++)
                        board.swapTokens(Position.at(row, index), Position.at(row + 1, index));
                    
                }
            }
        }
        
        return new ReverseRotate(index, column);
    }

    @Override
    public Set<Position> getAffectedPositions(Board board) {
        HashSet<Position> changes = new HashSet<>();

        // List all tokens in column/row
        if (column) {
            for (int column = 0; column < board.getRowCount(); column++)
                changes.add(Position.at(index, column));
        } else {
            for (int row = 0; row < board.getColumnCount(); row++)
                changes.add(Position.at(row, index));
        }
        
        return changes;
    }
}
