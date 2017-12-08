package edu.kit.informatik.structures;

public class Util {
    
    /**
     * Tests for a valid year.
     * A valid is year is a number between 1000-9999
     * 
     * @param input to validate
     * @return true if valid
     */
    public static boolean isValidYear(String input) {
        return input.matches("[1-9][0-9][0-9][0-9]");
    }
    
    /**
     * Tests for a valid title.
     * A title is valid if it not empty, does not contain
     * trailing/leading whitespace and does not contain commata, semicola
     * 
     * @param input to validate
     * @return true if valid
     */
    public static boolean isValidTitle(String input) {
        
        // Not empty
        if (input.trim().equals(""))
            return false;
        
        // No commata/semicola
        if (input.contains(";") || input.contains(","))
            return false;
                    
        return true;
    }
    
    /**
     * Tests for a valid name.
     * A name is valid if it contains lower- and uppercase letters only.
     * 
     * @param input to validate
     * @return true if valid
     */
    public static boolean isValidName(String input) {
        return input.matches("[a-zA-Z]+");
    }
}
