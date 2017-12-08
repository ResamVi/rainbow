package edu.kit.informatik.commands.bibliography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;
import edu.kit.informatik.structures.Style;

public class PrintBibliography extends Command {

    /**
     * Prints a sorted bibliography of the given publications
     * 
     * Arguments: [style]:[publicationId],[publicationId],...
     */
    public PrintBibliography() {
        super(1, "print bibliography");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException, BadArgumentException {
        
        // Check if style valid
        Style style = database.findStyle(args[0].split(":")[0]);
        
        // Correct the first argument, 
        // by removing the "[style]:" part, for easier processing.
        // args[0] only contains "publicationId;publicationId;..." then.
        args[0] = args[0].split(":")[1];
        
        // Check if publications exist
        List<Publication> publications = new ArrayList<>();
        for (String publicationId : Arrays.asList(args[0].split(";"))) {
            Publication publication = database.findPublication(publicationId);
            publications.add(publication);
        }
        
        // Remove duplicates
        Set<Publication> noDuplicates = new HashSet<>(publications);
        publications.clear();
        publications.addAll(noDuplicates);
        
        // Check if publications are valid
        for (Publication publication : publications) {
            if (publication.getAuthors().size() == 0)
                throw new BadArgumentException(publication.getId() + " is an invalid publication.");
        }
        
        // Sort publications
        Collections.sort(publications);
                
        // Print
        Output output = new Output(false);
        int index = 1;
        for (Publication publication : publications) {
            
            String entry;
            
            // Get list of contributors
            List<String> contributors = new ArrayList<>();
            for (Author author : publication.getAuthors())
                contributors.add(author.getId());
            
            // Depending on venue type, print appropriate format 
            switch (publication.getVenue().getType()) {
            
            // Journal format
            case JOURNAL:
                entry = style.formatJournal(
                        index, 
                        contributors, 
                        publication.getTitle(), 
                        publication.getVenue().getId(), 
                        Integer.toString(publication.getReleaseYear()));   
                break;
            
            // Series format
            case SERIES:
                entry = style.formatConference(
                        index, 
                        contributors, 
                        publication.getTitle(), 
                        publication.getVenue().getId(), 
                        publication.getLocation(), 
                        Integer.toString(publication.getReleaseYear()));   
                break;
            default:
                throw new RuntimeException("fatal: venuetype does not exist.");
            }
            
            output.addEntry(entry);
            index++;
        }
        
        return output;
    }

}
