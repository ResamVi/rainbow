package edu.kit.informatik.commands.requests;

import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class AllPublication extends Command {

    /**
     * Lists all publicationIds
     * If no publications exist nothing is listed.
     * 
     * Arguments: no arguments
     */
    public AllPublication() {
        super(0, "all publications");
    }

    @Override
    public Output execute(Database database, String... args) {
        
        // List all ids
        Output output = new Output(false);
        
        for (Publication publication : database.getPublications())
            output.addEntry(publication.getId());
        
        return output;
    }

}
