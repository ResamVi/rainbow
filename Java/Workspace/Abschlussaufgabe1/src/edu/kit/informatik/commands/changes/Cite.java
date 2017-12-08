package edu.kit.informatik.commands.changes;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class Cite extends Command {

    /**
     * If a publication cites another this command
     * can be used to keep track.
     * 
     * Arguments: [publicationId],[publicationId] 
     */
    public Cite() {
        super(2, "cites");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException, BadArgumentException {
        
        Publication citing = database.findPublication(args[0]);
        Publication source = database.findPublication(args[1]);
        
        // Cannot cite itself
        if (citing.getId().equals(source.getId()))
            throw new BadArgumentException("publications cannot cite themselves");
        
        // Release date of citing article has to be greater than source's date  
        if (citing.getReleaseYear() <= source.getReleaseYear())
            throw new BadArgumentException("publications can only cite articles of the past.");
        
        // Can only reference once
        for (Publication publication : citing.getCitations()) {
            if (source.equals(publication))
                throw new BadArgumentException("source already cited");
        }
        
        citing.cites(source);
        source.isCited();
        return new Output(true);
    }

}
