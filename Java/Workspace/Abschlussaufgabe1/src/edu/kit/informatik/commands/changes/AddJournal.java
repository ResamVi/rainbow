package edu.kit.informatik.commands.changes;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.entities.Journal;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class AddJournal extends Command {

    /**
     * Adds a journal object to the database
     * Arguments: [name],[publisher] 
     */
    public AddJournal() {
        super(2, "add journal");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException {
        
        // Create and add the journal
        Journal newJournal = new Journal(args[0], args[1]);
        
        if (!database.hasJournal(newJournal.getId())) {
            database.addJournal(newJournal);
            return new Output(true);
        } else
            throw new BadArgumentException("journal with same name already added");
    }
    
}
