package edu.kit.informatik.commands.changes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Conference;
import edu.kit.informatik.entities.ConferenceSeries;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class AddKeywordConference extends Command {

    /**
     *  Adds a keyword to the conference.
     *  As conferences differ a lot from other entities
     *  it has its own class.
     *  
     *   Arguments: [seriesId],[year]:[keyword];[keyword];....
     */
    public AddKeywordConference() {
        super(2, "add keywords to conference");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException, DataNotFoundException {

        // Further parsing
        String seriesId = args[0];
        
        // Get the year then remove substring from arguments
        String year = args[1].split(":")[0];
        args[1] = args[1].replace(year + ":", "");
        
        Set<String> keywords = new HashSet<>(Arrays.asList(args[1].split(";")));
        
        // Look thorugh series' conferences for the right one
        ConferenceSeries series = database.findSeries(seriesId);
        Conference conference = null;
        
        for (Conference entry : series.getConferences()) {
            if (entry.getId().equals(year))
                conference = entry;
        }
        
        // Check if keywords lowercase,
        for (String keyword : keywords) {
            if (!keyword.matches("[a-z]+"))
                throw new BadArgumentException(keyword + " is not a valid keyword");
        }
        
        // Add
        for (String keyword : keywords)
            conference.addKeyword(keyword);
        
        return new Output(true);
    }

}
