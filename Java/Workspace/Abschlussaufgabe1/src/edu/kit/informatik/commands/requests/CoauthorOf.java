package edu.kit.informatik.commands.requests;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class CoauthorOf extends Command {

    /**
     * Lists all authors that have worked
     * with the given author.
     * 
     * Argument: [authorId]
     */
    public CoauthorOf() {
        super(1, "coauthors of");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException {
       
        // Check if author exists
        Author author = database.findAuthor(args[0]);
        
        // Collect all coauthors
        Set<Author> coauthors = getCoauthors(database, author);
        
        // Output
        Output output = new Output(false);
        for (Author coauthor : coauthors)
            output.addEntry(coauthor.getId());
        
        return output;
    }

    /**
     * Lists all authors that have worked
     * with the given author.
     * 
     * Argument: [authorId]
     * 
     * @param database with stored data
     * @param author this author's coauthors
     * @return all coauthors or an empty list if none available, 
     * null if an author does not exist 
     */
    public Set<Author> getCoauthors(Database database, Author author) {
        
        // Collect all coauthors
        Set<Author> coauthors = new HashSet<>();
        for (Publication publication : author.getPublication()) {
            
            for (Author coauthor : publication.getAuthors())
                coauthors.add(coauthor);

        }

        // Remove itself
        coauthors.remove(author);
        
        return coauthors;
    }
}
