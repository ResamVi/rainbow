package edu.kit.informatik.commands.changes;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Conference;
import edu.kit.informatik.entities.ConferenceSeries;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class AddConference extends Command {

    /**
     * Adds a Conference object to the database
     * Arguments: [seriesId],[year],[location] 
     */
    public AddConference() {
        super(3, "add conference");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException, DataNotFoundException {
        
        // Get the series (if it exists) create and add a new conference
        ConferenceSeries series = database.findSeries(args[0]);
        Conference newConference = new Conference(series, args[1], args[2]);
        
        // Check if same conference already occured in year
        for (Conference conf : series.getConferences()) {
            if (conf.getId().equals(newConference.getId()))
                throw new BadArgumentException("a conference was already held in the same year");
        }
        
        // Print error if already occured
        series.addConference(newConference);
        return new Output(true);
    }
}
