package edu.kit.informatik.commands.requests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class HIndex extends Command {

    /**
     * Calculates the h-index of an author
     * 
     * Arguments: [authorId]
     */
    public HIndex() {
        super(1, "h-index");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException {
        
        // Check if author exists
        Author author = database.findAuthor(args[0]);
        
        // Join to single argument 
        List<String> citedAmount = new ArrayList<>();
        for (Publication publication : author.getPublication())
            citedAmount.add(Integer.toString(publication.getCitedAmount()));
        
        // Output
        Output output = new Output(false);
        output.addEntry(calculateHIndex(String.join(";", citedAmount)));
        return output;
    }

    /**
     * Calcultes the h-index
     * 
     * @param arg set separated by ","
     * @return h-index
     * @throws NumberFormatException if argument is not a valid number
     */
    public static int calculateHIndex(String arg) throws NumberFormatException {
        
        // Parsing
        List<Integer> citations = new ArrayList<>();
        
        // Store the numbers in list
        for (String parameter : arg.split(";")) {
            
            // Only positive numbers allowed
            if (!parameter.matches("[0-9]+"))
                throw new NumberFormatException("is not a positive integer"); 
            
            citations.add(Integer.parseInt(parameter));
        }
        
        // Sorting
        Collections.sort(citations, Collections.reverseOrder());
        
        // Calculate
        for (int i = 0; i < citations.size(); i++) {
            
            if (i + 1 > citations.get(i))
                return i;
                
        }
        return Collections.max(citations);
    }
    
}
