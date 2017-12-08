package edu.kit.informatik.structures;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;

public interface Executable {

    /**
     * Executes its recipe
     * 
     * @param database database that can be changed
     * @param args various arguments to proces
     * @return processed data appropriate for display
     * @throws BadArgumentException if arguments do not conform to logic
     * @throws DataNotFoundException if the given arguments are not found in database
     */
    Output execute(Database database, String... args) throws BadArgumentException, DataNotFoundException;
}
