package edu.kit.informatik.entities;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.structures.Entity;

public abstract class Venue extends Entity {

    private Set<Publication> publications;
    private VenueType venueType;
    
    /**
     * A venue is a scene where articles can be published.
     * Venues are currently either journals or conference series
     * 
     * @param venueType type classification of this venue
     */
    public Venue(VenueType venueType) {
        super();
        this.venueType = venueType;
        this.publications = new HashSet<>();
    }
    
    /**
     * Returns all the publications that have been
     * published under this venue's name.
     * 
     * @return full list of publications
     */
    public Set<Publication> getPublications() {
        return publications;
    }

    /**
     * Adds a article to this venue
     * 
     * @param publication article to add
     */
    public void addPublication(Publication publication) {
        publications.add(publication);
    }
    
    /**
     * The type of this venue 
     * @see VenueType
     * @return type
     */
    public VenueType getType() {
        return venueType;
    }
}
