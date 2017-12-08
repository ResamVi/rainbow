package edu.kit.informatik.commands.requests;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class ForeignCitationOf extends Command {

    /**
     * Lists all citations of foreign publicationIds.
     * Foreign means publications in which the author and his
     * coauthors have not contributed to. 
     */
    public ForeignCitationOf() {
        super(1, "foreign citations of");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException {
        
        // Check if author exists
        Author author = database.findAuthor(args[0]);
        
        // Collect all authors that have worked with this author (i.e. coauthors)
        Set<Author> coauthors = new CoauthorOf().getCoauthors(database, author);
        
        // Look for all publications that cite this author
        Set<Publication> publicationsThatCited = new HashSet<>();
        
        // Iterate all publications
        for (Publication publication : database.getPublications()) {
            
            // Of every publications check if their cited sources contain the author
            for (Publication cites : publication.getCitations()) {

                if (cites.getAuthors().contains(author))
                    publicationsThatCited.add(publication);
            }
        }
        
        // Filter out all publications that contain coauthors
        Set<Publication> foreignCitations = new HashSet<>();
        
        for (Publication citingPublication : publicationsThatCited) {

            boolean isForeign = true;
            
            // Skip if citation's authors has a coauthor
            for (Author authorThatCited : citingPublication.getAuthors()) {
                if (coauthors.contains(authorThatCited)) {
                    isForeign = false;
                    break;
                }
            }
            
            // Else add
            if (isForeign)
                foreignCitations.add(citingPublication);
        }
        
        // Remove publications written of the author himself
        for (Publication publication : author.getPublication())
            foreignCitations.remove(publication);
        
        // Outputting
        Output output = new Output(false);
        for (Publication publication : foreignCitations)
            output.addEntry(publication.getId());
        
        return output;
    }
}
