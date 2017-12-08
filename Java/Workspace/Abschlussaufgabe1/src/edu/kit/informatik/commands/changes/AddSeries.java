package edu.kit.informatik.commands.changes;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.entities.ConferenceSeries;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class AddSeries extends Command {

    /**
     * Adds a Series object to the database
     * Arguments: [name],[publisher] 
     */
    public AddSeries() {
        super(1, "add conference series");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException {
        
        ConferenceSeries newSeries = new ConferenceSeries(args[0]);
        
        // New series should not exist already
        if (!database.hasSeries(newSeries.getId()))
            database.addSeries(newSeries);
        else
            throw new BadArgumentException("series with same name already added");
        
        return new Output(true);
    }
    
}
