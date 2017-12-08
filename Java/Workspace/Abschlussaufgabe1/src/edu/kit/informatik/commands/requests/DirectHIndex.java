package edu.kit.informatik.commands.requests;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class DirectHIndex extends Command {

    /**
     * Calculates the h-index of an author.
     * The h-index is a metric to measure the impact
     * of an author's publication in terms of citations.
     * 
     * Arguments: [int];[int];[int];... 
     */
    public DirectHIndex() {
        super(1, "direct h-index");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException {
        
        try {
            Output output = new Output(false);
            output.addEntry(HIndex.calculateHIndex(args[0]));
            return output;            
        } catch (NumberFormatException ex) {
            throw new BadArgumentException(ex.getMessage());
        }
    }
}
