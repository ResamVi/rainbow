package edu.kit.informatik.entities;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Entity;
import edu.kit.informatik.structures.Util;

public class Conference extends Entity {

    private ConferenceSeries series;
    private String           year;
    private String           location;
    
    /**
     * Creates a new academic conference at which
     * publications can be published. Conferences are part
     * of conference series and can be periodic annually. 
     * 
     * 
     * @param series this conference is part of
     * @param year of event
     * @param location of series
     * @throws BadArgumentException if year is not between 1000-9999 
     */
    public Conference(ConferenceSeries series, String year, String location) throws BadArgumentException {
        
        // Check for valid year
        if (!Util.isValidYear(year))
            throw new BadArgumentException("year should be between a number between 1000-9999");
        
        // Check for valid location name
        if (!Util.isValidTitle(location))
            throw new BadArgumentException(location + " is not a valid location name");
        
        this.series = series;
        this.year = year;
        this.location = location;
    }
    
    /**
     * Where this conference was held
     * 
     * @return location of conference
     */
    public String getLocation() {
        return location;
    }
    
    // The id of a conference [conferenceId] is its year of release
    @Override
    public String getId() {
        return year;
    }

    @Override
    public Set<String> getKeywords() {
        
        // Inherits keywords of its associated series
        Set<String> allKeywords = new HashSet<>(series.getKeywords());
        allKeywords.addAll(super.getKeywords());

        return allKeywords;
    }
    
}
