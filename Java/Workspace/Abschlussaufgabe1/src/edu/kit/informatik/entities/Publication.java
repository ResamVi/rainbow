package edu.kit.informatik.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.structures.Entity;
import edu.kit.informatik.structures.Util;

public class Publication extends Entity implements Comparable<Publication> {

    private Venue               venue;
    private String              id;
    private int                 year;
    private String              title;
    
    private String              location; // if venue is series
    private Conference          conference; // if venue is series
    
    private List<Author>        authors;
    private Set<Publication>    citations;
    private int                 cited;
    
    /**
     * Publications are scientific articles published by Conferences or Journals
     * 
     * @param venue type of venue (conference series/journal)
     * @param id unique identification 
     * @param year of release
     * @param title of publication
     * @throws BadArgumentException if not a valid year between 1000-9999
     */
    private Publication(Venue venue, String id, String year, String title) throws BadArgumentException {
        
        // Check for valid year
        if (!Util.isValidYear(year))
            throw new BadArgumentException("year should be between a number between 1000-9999");
        
        this.venue = venue;
        this.id = id;
        this.year = Integer.parseInt(year);
        this.title = title;
        
        this.authors = new ArrayList<>();
        this.citations = new HashSet<>();
        this.cited = 0;
    }
    
    /**
     * Publications are scientific articles published by Conferences or Journals
     * 
     * @param journal this publication's venue
     * @param id unique identification 
     * @param year of release
     * @param title of publication
     * @throws BadArgumentException 
     */
    public Publication(Journal journal, String id, String year, String title) throws BadArgumentException {
        this((Venue) journal, id, year, title);
    }

    /**
     * Publications are scientific articles published by Conferences or Journals.
     * For conferences a location should be given as well
     * 
     * @param series this publication's venue
     * @param id unique identification 
     * @param year of release
     * @param title of publication
     * @param location of conference
     * @throws BadArgumentException  if not a valid year
     * @throws DataNotFoundException if the conference does not exist in series
     */
    public Publication(ConferenceSeries series, String id, String year, String title, String location) 
            throws BadArgumentException, DataNotFoundException {
        this((Venue) series, id, year, title);
        this.location = location; 
        
        // Look for conference in series
        for (Conference conference : ((ConferenceSeries) venue).getConferences()) {
            if (conference.getId().equals(year + ""))
                this.conference = conference;
        }

        // If conference does not exist
        if (this.conference == null)
            throw new DataNotFoundException("conference does not exist in year " + year);
    }

    // The id of an article [publicationId] is its "written" id
    @Override
    public String getId() {
        return id;
    }

    @Override
    public Set<String> getKeywords() {
        
        // Inherits keywords of its associated conference series/journal
        Set<String> allKeywords = new HashSet<>(venue.getKeywords());
        allKeywords.addAll(super.getKeywords());
        
        if (venue.getType() == VenueType.SERIES)
            allKeywords.addAll(conference.getKeywords());
        
        return allKeywords;
    }
    
    
    
    /**
     * Adds author that contributed to this publication
     * 
     * @param author to add
     */
    public void isWrittenBy(Author author) {
        authors.add(author);
    }
    
    
    /**
     * Returns list of authors that contributed
     * to this publication. Returns an empty list
     * if none are assigned.
     * 
     * @return list of authors
     */
    public List<Author> getAuthors() {
        return authors;
    }
    
    /**
     * Adds publication that this article cites
     * to the reference index.
     * 
     * @param publication source
     */
    public void cites(Publication publication) {
        citations.add(publication);
    }
    
    /**
     * Return list of publications this article cites
     * 
     * @return list of publications
     */
    public Set<Publication> getCitations() {
        return citations;
    }
    
    /**
     * Tell this publication it has been cited.
     */
    public void isCited() {
        cited++;
    }
    
    /**
     * Returns how much this publication
     * has been cited.
     * 
     * @return cited amount
     */
    public int getCitedAmount() {
        return cited;
    }
    
    /**
     * Returns date this article was published
     * 
     * @return year of release
     */
    public int getReleaseYear() {
        return year;
    }

    /**
     * Returns this publication's venue
     * 
     * @return venue
     */
    public Venue getVenue() {
        return venue;
    }
    
    /**
     * Returns this publication's title
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Returns the location if venue type is series
     * 
     * @return location of conference
     * @throws DataNotFoundException if this publication is not of type series
     */
    public String getLocation() throws DataNotFoundException {
        if (location != null)
            return location;
        else
            throw new DataNotFoundException("publications of journals do not contain a location");
    }
    
    @Override
    public int compareTo(Publication other) {
        
        int minSize = Math.min(authors.size(), other.authors.size());
        int diff;
        
        // Check names
        for (int i = 0; i < minSize; i++) {
            Author thisAuthor = this.authors.get(i);
            Author otherAuthor = other.authors.get(i);
            
            diff = thisAuthor.getLastName().compareTo(otherAuthor.getLastName());
            if (diff != 0) return diff;
            diff = thisAuthor.getFirstName().compareTo(otherAuthor.getFirstName());
            if (diff != 0) return diff;
        }
        
        // If same names, check author count
        diff = Integer.compare(authors.size(), other.authors.size());
        if (diff != 0) return diff;
        
        // Same author count, check title
        diff = title.compareTo(other.title);
        if (diff != 0) return diff;
        
        // Same title, check release date
        diff = Integer.compare(year, other.year);
        if (diff != 0) return diff;
        
        // Finally, check id
        diff = id.compareTo(other.id);
        if (diff != 0) return diff;
        
        return 0;
    }
    
    @Override
    public boolean equals(Object other) {
        
        if (this == other) {
            return true;
        }
        if (other instanceof Publication) {
            Publication anotherPub = (Publication) other;
            
            if (anotherPub.getId().equals(this.getId()))
                return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString() + " about: \"" + title + "\"";
    }
    
}
