package edu.kit.informatik.entities;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Util;

public class ConferenceSeries extends Venue {
    
    private String                  title;
    private List<Conference>        conferences;
    
    /**
     * Creates an empty conference series with the given name
     * 
     * @param title of conference series
     * @throws BadArgumentException if the title is either empty or non-alphabetic
     */
    public ConferenceSeries(String title) throws BadArgumentException {
        super(VenueType.SERIES);
        
        // Check if title is valid
        if (!Util.isValidTitle(title))
            throw new BadArgumentException("\"" + title + "\" is not a valid series name");
        
        this.title = title;
        conferences = new ArrayList<>();
    }
    
    // The id of a conference series [seriesId] is its title
    @Override
    public String getId() {
        return title;
    }
    
    /**
     * Gets all the conferences that are part of this series
     * empty list if there are none
     * 
     * @return conferences list
     */
    public List<Conference> getConferences() {
        return conferences;
    }

    /**
     * Adds a single conference to the series
     * Will not contain duplicates due to AddConference class keeping track
     * 
     * @param conference to add
     */
    public void addConference(Conference conference) {
        conferences.add(conference);
    }

}
