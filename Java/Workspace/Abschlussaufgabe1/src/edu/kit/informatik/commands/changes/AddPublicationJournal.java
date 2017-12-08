package edu.kit.informatik.commands.changes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Journal;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;
import edu.kit.informatik.structures.Util;

public class AddPublicationJournal extends Command {

    /**
     * Adds a Publication published at a series to the database
     * Arguments: [seriesId:publicationId],[year],[title] 
     */
    public AddPublicationJournal() {
        super(3, "add article to journal");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException, DataNotFoundException {
        
        // Pre-emptive measures before parsing
        if (!args[0].matches("(.+):(.+)"))
            throw new BadArgumentException("wrong syntax");
        
        Pattern pattern         = Pattern.compile("(.+):(.+)");
        Matcher firstArgument   = pattern.matcher(args[0]);
        
        // Additional parsing of first argument, after each step cut from argument
        String venueId = null;
        String publicationId = null;
        
        if (firstArgument.find()) {
            venueId          = firstArgument.group(1);
            publicationId     = firstArgument.group(2);
        }
        // Check valid year
        if (!Util.isValidYear(args[1]))
            throw new BadArgumentException("year should be between a number between 1000-9999");
        
        
        // Check if venue exists
        Journal journal = database.findJournal(venueId);
        Publication newPublication;

        // Check for dupliacte
        for (Publication publication : journal.getPublications()) {
            if (publication.getId().equals(publicationId))
                throw new BadArgumentException("\"" + publicationId + "\" already exists.");
        }
        
        // Create a journal-type publication
        newPublication = new Publication(journal, publicationId, args[1], args[2]);

        // Store references
        journal.addPublication(newPublication);
        database.addPublication(newPublication);
    
        return new Output(true);
    }

    
    
}
