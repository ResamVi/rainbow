package edu.kit.informatik.entities;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Identifiable;
import edu.kit.informatik.structures.Util;

public class Author implements Identifiable {

    private String          firstName;
    private String          lastName;
    
    private Set<Publication>   publications;
    
    /**
     * Authors can write Publications.
     * 
     * @param firstName first name
     * @param lastName last name
     * @throws BadArgumentException if first- or last name is empty or non-alphabetic
     */
    public Author(String firstName, String lastName) throws BadArgumentException {
        
        // Check for valid names
        if (!Util.isValidName(firstName) || !Util.isValidName(lastName))
            throw new BadArgumentException("\"" + firstName + " " + lastName + "\" is not a valid name");
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.publications = new HashSet<>();
    }
    
    // The id of an author [authorId] is his first- and last name
    @Override
    public String getId() {
        return firstName + " " + lastName;
    }

    /**
     * Add publication this author has contributed to
     * 
     * @param publication to add
     */
    public void hasWritten(Publication publication) {
        publications.add(publication);
    }

    /**
     * Return a list of all articles this author has
     * contributed to
     * 
     * @return list of publications
     */
    public Set<Publication> getPublication() {
        return publications;
    }
    
    /**
     * First name of author
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Last name of author
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public String toString() {
        return getId();
    }
}
