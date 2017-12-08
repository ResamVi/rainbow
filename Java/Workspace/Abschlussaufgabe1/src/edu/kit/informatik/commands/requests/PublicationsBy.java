package edu.kit.informatik.commands.requests;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class PublicationsBy extends Command {

    /**
     * Lists all publicationIds in which at least one of the given
     * authors contributed to
     * 
     * Arguments: [authorId];[authorId];...
     */
    public PublicationsBy() {
        super(1, "publications by");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException {

        // Additional parsing
        String authorIds[] = args[0].split(";");
        
        // Bundle authors, check if authors exist
        Set<Author> authors = new HashSet<>();
        for (String authorId : authorIds)
            authors.add(database.findAuthor(authorId));
        
        // Find all publications, ignore duplicates
        Set<String> allPublications = new HashSet<>();
        for (Author author : authors) {
            for (Publication publication : author.getPublication())
                allPublications.add(publication.getId());
        }
        
        // Print output
        Output output = new Output(false);
        for (String entry : allPublications)
            output.addEntry(entry);

        return output;
    }

}
