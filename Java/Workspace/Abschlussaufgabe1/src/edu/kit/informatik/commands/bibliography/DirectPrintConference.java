package edu.kit.informatik.commands.bibliography;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.VenueType;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class DirectPrintConference extends DirectPrint {
    
    /**
     * Prints a bibliography of a conference entry of the given argument
     * 
     * Arguments: [style]:[authorId],[authorId],[authorId],[title],[series name],[location],[year]
     */
    public DirectPrintConference() {
        super(7, "direct print conference");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException, BadArgumentException {
        return new Output(getEntry(database, VenueType.SERIES, args));
    }    
}
