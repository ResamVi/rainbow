package edu.kit.informatik.commands.bibliography;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.VenueType;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class DirectPrintJournal extends DirectPrint {

    /**
     * Prints a bibliography of a journal entry of the given argument
     * 
     * Arguments: [style]:[authorId],[authorId],[authorId],[title],[journal name],[year]
     */
    public DirectPrintJournal() {
        super(6, "direct print journal");
    }

    @Override
    public Output execute(Database database, String... args) 
            throws DataNotFoundException, BadArgumentException {        
        return new Output(getEntry(database, VenueType.JOURNAL, args));
    }
}
