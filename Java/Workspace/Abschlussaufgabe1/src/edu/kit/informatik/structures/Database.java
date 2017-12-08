package edu.kit.informatik.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.Author;
import edu.kit.informatik.entities.ConferenceSeries;
import edu.kit.informatik.entities.Journal;
import edu.kit.informatik.entities.Publication;
import edu.kit.informatik.styles.ChicagoSimplified;
import edu.kit.informatik.styles.IEEESimplified;

public class Database {

    private List<Author> authors;
    private List<Journal> journals;
    private List<ConferenceSeries> series;
    private List<Publication> publications;
    private List<Style> styles;
    
    /**
     * Creates an empty database
     */
    public Database() {
        authors = new ArrayList<>();
        journals = new ArrayList<>();
        series = new ArrayList<>();
        publications = new ArrayList<>();
        
        styles = new ArrayList<>();
        styles.addAll(Arrays.asList(
                new IEEESimplified(),
                new ChicagoSimplified()
        ));
    }
    
    // --------------------Add functions--------------------
    
    /**
     * Adds author
     * 
     * @param author to add
     */
    public void addAuthor(Author author) {
        authors.add(author);
    }

    /**
     * Adds journal
     * 
     * @param journal to add
     */
    public void addJournal(Journal journal) {
        journals.add(journal);
    }
    
    /**
     * Adds series
     * 
     * @param series to add
     */
    public void addSeries(ConferenceSeries series) {
        this.series.add(series);
    }
    
    /**
     * Adds publication
     * 
     * @param publication to add
     */
    public void addPublication(Publication publication) {
        publications.add(publication);
        
    }
    
    // --------------------Find functions-------------------- 
    /**
     * Find author in database
     * 
     * @param authorId of author
     * @return author if found else null
     * @throws DataNotFoundException thrown when the given author does not exist in the database
     */
    public Author findAuthor(String authorId) throws DataNotFoundException {
        
        for (Author author : authors) {
            if (author.getId().equals(authorId))
                return author;
        }
        throw new DataNotFoundException("\"" + authorId + "\" does not exist");
    }

    /**
     * Find journal in database
     * 
     * @param journalId of journal
     * @return journal if found
     * @throws DataNotFoundException thrown when the given journal does not exist in the database
     */
    public Journal findJournal(String journalId) throws DataNotFoundException {
        
        for (Journal journal : journals) {
            if (journal.getId().equals(journalId))
                return journal;
        }
        throw new DataNotFoundException("\"" + journalId + "\" does not exist");
    }
    
    /**
     * Find series in database
     * 
     * @param seriesId of series
     * @return series if found
     * @throws DataNotFoundException thrown when the given series does not exist in the database
     */
    public ConferenceSeries findSeries(String seriesId) throws DataNotFoundException {
        
        for (ConferenceSeries series : series) {
            if (series.getId().equals(seriesId))
                return series;
        }
        throw new DataNotFoundException("\"" + seriesId + "\" does not exist");
    }
    
    /**
     * Find publication in database
     * 
     * @param publicationId of article
     * @return publication if found
     * @throws DataNotFoundException thrown when the given publication does not exist in the database
     */
    public Publication findPublication(String publicationId) throws DataNotFoundException {
        
        for (Publication publication : publications) {
            if (publication.getId().equals(publicationId))
                return publication;
        }
        throw new DataNotFoundException("\"" + publicationId + "\" does not exist");
    }

    /**
     * Finds style in database
     * 
     * @param styleName name of style
     * @return style if found
     * @throws DataNotFoundException thrown when the given style does not exist in the database
     */
    public Style findStyle(String styleName) throws DataNotFoundException {
        
        for (Style style : styles) {
            if (style.getName().equals(styleName))
                return style;
        }
        throw new DataNotFoundException("\"" + styleName + "\" does not exist");
    }
    
    // --------------------Get functions--------------------     
    
    /**
     * Get all the series or an empty list
     * if none available
     * 
     * @return list of all series
     */
    public List<ConferenceSeries> getSeries() {
        return series;
    }

    /**
     * Get all the authors or an empty list
     * if none available
     * 
     * @return list of all authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Get all the journals or an empty list
     * if none available
     * 
     * @return list of journals
     */
    public List<Journal> getJournal() {
        return journals;
    }

    /**
     * Get all the publications of both journals
     * and series. Returns an empty list if none exist.
     * 
     * @return list of publications
     */
    public List<Publication> getPublications() {
        return publications;
    }
    
    // --------------------has functions--------------------
    
    /**
     * Returns true if the given author is already added
     * 
     * @param authorId of author
     * @return true if author already added
     */
    public boolean hasAuthor(String authorId) {
        for (Author author : authors) {
            if (author.getId().equals(authorId))
                return true;
        }
        return false;
    }

    /**
     * Returns true if the given series is already added
     * 
     * @param seriesId of series
     * @return true if series already added
     */
    public boolean hasSeries(String seriesId) {
        for (ConferenceSeries series : series) {
            if (series.getId().equals(seriesId))
                return true;
        }
        return false;
    }
    
    /**
     * Returns true if the given journal is already added
     * 
     * @param journalId of journal
     * @return true if journal already added
     */
    public boolean hasJournal(String journalId) {
        for (Journal journal : journals) {
            if (journal.getId().equals(journalId))
                return true;
        }
        return false;
    }
    
    /**
     * Returns true if the given publication is already added
     * 
     * @param publicationId of publication
     * @return true if publication already added
     */
    public boolean hasPublication(String publicationId) {
        for (Publication publication : publications) {
            if (publication.getId().equals(publicationId))
                return true;
        }
        return false;
    }
}
