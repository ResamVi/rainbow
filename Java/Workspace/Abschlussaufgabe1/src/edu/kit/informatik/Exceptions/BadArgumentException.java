package edu.kit.informatik.Exceptions;

public class BadArgumentException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Thrown when the given argument do not conform to logic
     * 
     * @param message of exception
     */
    public BadArgumentException(String message) {
        super(message + ".");
    }
}
