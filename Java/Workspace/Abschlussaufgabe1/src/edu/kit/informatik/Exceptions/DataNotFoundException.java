package edu.kit.informatik.Exceptions;

public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     * Thrown when a given argument could not be found in the database
     * 
     * @param message of exception
     */
    public DataNotFoundException(String message) {
        super(message + ".");
    }
}
