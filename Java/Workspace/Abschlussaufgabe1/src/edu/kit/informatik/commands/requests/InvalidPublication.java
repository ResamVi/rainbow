package edu.kit.informatik.commands.requests;

import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class InvalidPublication extends Command {

    /**
     * Lists all publicationIds of publications
     * that have no author assigned.
     * 
     * Arguments: no arguments
     */
    public InvalidPublication() {
        super(0, "list invalid publications");
    }

    @Override
    public Output execute(Database database, String... args) {

        // Output
        Output output = new Output(false);
        
        for (Publication publication : database.getPublications()) {
            if (publication.getAuthors().size() == 0)
                output.addEntry(publication.getId());
        }
        
        return output;
    }

}
