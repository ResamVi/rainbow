package edu.kit.informatik.commands.changes;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class AddAuthor extends Command {

    /**
     * Adds an author object to the database
     * Arguments: [first name],[last name] 
     */
    public AddAuthor() {
        super(2, "add author");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException {
        
        // Create and add an author to database
        Author newAuthor = new Author(args[0], args[1]);
        if (!database.hasAuthor(newAuthor.getId())) {
            database.addAuthor(newAuthor);
            return new Output(true);
        
        // If author is already added
        } else
            throw new BadArgumentException("\"" + newAuthor.getId() + "\" has already been added");
    }

}
