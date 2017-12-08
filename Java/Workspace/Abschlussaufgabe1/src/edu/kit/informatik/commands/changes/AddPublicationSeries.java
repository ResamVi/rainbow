package edu.kit.informatik.commands.changes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Conference;
import edu.kit.informatik.entities.ConferenceSeries;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;
import edu.kit.informatik.structures.Util;

public class AddPublicationSeries extends Command {

    /**
     * Adds a Publication published at a series to the database
     * Arguments: [seriesId:publicationId],[year],[title] 
     */
    public AddPublicationSeries() {
        super(3, "add article to series");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException, DataNotFoundException {
    
        // Pre-emptive measures before parsing
        if (!args[0].matches("(.+):(.+)"))
            throw new BadArgumentException("wrong syntax");
        
        Pattern pattern         = Pattern.compile("(.+):(.+)");
        Matcher firstArgument   = pattern.matcher(args[0]);
        
        // Additional parsing of first argument, after each step cut from argument
        String seriesId = null;
        String publicationId = null;
        
        if (firstArgument.find()) {
            seriesId          = firstArgument.group(1);
            publicationId     = firstArgument.group(2);
        }
        
        // Check valid year
        if (!Util.isValidYear(args[1]))
            throw new BadArgumentException("year should be between a number between 1000-9999");
        
        // Check if venue exists
        ConferenceSeries series = database.findSeries(seriesId);
          
        // Check if series has a conference matching publication's year 
        Publication newPublication;
        boolean match = false;
        
        // Iterate through all conferences in series
        for (Conference conference : series.getConferences()) {
            if (conference.getId().equals(args[1])) {
                
                // Create publication
                newPublication = new Publication(series, publicationId, args[1], args[2], conference.getLocation());
                
                // Store references
                series.addPublication(newPublication);
                database.addPublication(newPublication);
                match = true;
            }
        }
        
        // Print error
        if (!match) 
            throw new BadArgumentException("conference does not exist in given year");
            
        return new Output(true);
    
    }

}
