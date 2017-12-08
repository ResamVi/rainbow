package edu.kit.informatik.entities;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Util;

public class Journal extends Venue {

    private String  title;
    
    @SuppressWarnings("unused")
    private String  publisher;
    
    
    /**
     * A Journal is a venue that  routinely publishes magazines of specific academic subjects.
     * It varies from conferences in their
     * 
     * @param name title of journal
     * @param publisher publisher of journal
     * @throws BadArgumentException if the journal name is empty or non-alphabetical
     */
    public Journal(String name, String publisher) throws BadArgumentException {
        super(VenueType.JOURNAL);
        
        // Check for valid names
        if (!Util.isValidTitle(name))
            throw new BadArgumentException("\"" + name + "\" is not a valid name");
        
        if (!Util.isValidTitle(publisher))
            throw new BadArgumentException("\"" + publisher + "\" is not a valid name");
        
        this.title = name;
        this.publisher = publisher;
    }
    
    // The id of a conference [journalId] is its title
    @Override
    public String getId() {
        return title;
    }
}
