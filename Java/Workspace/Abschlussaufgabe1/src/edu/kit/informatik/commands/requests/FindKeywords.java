package edu.kit.informatik.commands.requests;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class FindKeywords extends Command {

    /**
     * Lists all publicationIds that contain all
     * inputted keywords
     */
    public FindKeywords() {
        super(1, "find keywords");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException {
        
        // Additional parsing
        Set<String> keywords = new HashSet<>(Arrays.asList(args[0].split(";")));
        
        // Check if valid keyword
        for (String keyword : keywords) {
            if (!keyword.matches("[a-z]+"))
                throw new BadArgumentException("\"" + keyword + "\" is not a valid keyword");
        }
        
        // Check if empty
        if (keywords.size() == 1 && keywords.contains(""))
            throw new BadArgumentException("no keywords given");
        
        Output output = new Output(false);
        
        // Iterate through all pubs and look for matches
        for (Publication publication : database.getPublications()) {
            if (publication.getKeywords().containsAll(keywords))
                output.addEntry(publication.getId());
        }
        return output;
    }
}
