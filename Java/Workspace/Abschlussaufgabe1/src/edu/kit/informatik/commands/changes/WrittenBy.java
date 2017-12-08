package edu.kit.informatik.commands.changes;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class WrittenBy extends Command {

    /**
     * Adds a list of authors who contributed to a publication to the list
     * of authors
     * Arguments: [publicationId],[authorId];[authorId];... 
     */
    public WrittenBy() {
        super(2, "written-by");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException, BadArgumentException {
        
        // Check if publication exists
        Publication publication = database.findPublication(args[0]);
        
        // Check if authors exist, then append them to the contributor list
        String[] authorIds = args[1].split(";");
        for (String authorId : authorIds) {
            
            Author author = database.findAuthor(authorId);
            
            // Check if author already added
            for (Author element: publication.getAuthors()) {
                if (element.getId().equals(author.getId()))
                    throw new BadArgumentException("this publication is already "
                            + "written by \"" + element.getId() + "\"");
            }

            // If not: store references
            publication.isWrittenBy(author);
            author.hasWritten(publication);
        }
        return new Output(true); 
    }

}
