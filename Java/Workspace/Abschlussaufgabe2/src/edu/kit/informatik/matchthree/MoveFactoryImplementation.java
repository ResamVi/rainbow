package edu.kit.informatik.matchthree;

import edu.kit.informatik.Flip;
import edu.kit.informatik.Rotate;
import edu.kit.informatik.RotateSquare;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Move;
import edu.kit.informatik.matchthree.framework.interfaces.MoveFactory;

/**
 *
 */
public class MoveFactoryImplementation implements MoveFactory {
    /**
     * 
     */
    public MoveFactoryImplementation() { }

    @Override
    public Move flipRight(Position position) {
        return new Flip(position, false);
    }

    @Override
    public Move flipDown(Position position) {
        return new Flip(position, true);
    }

    @Override
    public Move rotateSquareClockwise(Position position) {
        return new RotateSquare(position);
    }

    @Override
    public Move rotateColumnDown(int columnIndex) {
        return new Rotate(columnIndex, true);
    }

    @Override
    public Move rotateRowRight(int rowIndex) {
        return new Rotate(rowIndex, false);
    }

}
