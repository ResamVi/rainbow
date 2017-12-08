package edu.kit.informatik.commands.requests;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.ConferenceSeries;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;
import edu.kit.informatik.structures.Util;

public class InProceedings extends Command {

    /**
     * Lists all publicationIds of the series in the given year.
     * 
     * Arguments: [seriesId],[year]
     */
    public InProceedings() {
        super(2, "in proceedings");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException, BadArgumentException {
        
        // Check if series exists
        ConferenceSeries series = database.findSeries(args[0]);
        
        // Check if valid year inputted
        if (!Util.isValidYear(args[1]))
            throw new BadArgumentException("invalid year");
        
        // Iterate, list all with matching year
        Output output = new Output(false);
        int entries = 0;
        for (Publication publication : series.getPublications()) {
            if (publication.getReleaseYear() == Integer.parseInt(args[1])) {
                output.addEntry(publication.getId());
                entries++;
            }
        }
        
        // Exception if none were found
        if (entries == 0)
            throw new DataNotFoundException("no conference in " + args[1] + " found");
        
        // Empty if none found
        return output;
    }

}
