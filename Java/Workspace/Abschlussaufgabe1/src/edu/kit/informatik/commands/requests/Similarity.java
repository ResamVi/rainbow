package edu.kit.informatik.commands.requests;

import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class Similarity extends Command {

    
    /**
     * Returns the jaccard index of both the 
     * publications's set of keywords.
     * 
     * Arguments: [publicationId][publicationId]
     */
    public Similarity() {
        super(2, "similarity");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException {
        
        // Check if publications exist
        Publication former = database.findPublication(args[0]);
        Publication latter = database.findPublication(args[1]);
        
        return new Output(Jaccard.calculateJaccard(former.getKeywords(), latter.getKeywords())); 
    }

}
