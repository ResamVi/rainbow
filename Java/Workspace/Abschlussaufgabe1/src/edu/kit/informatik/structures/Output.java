package edu.kit.informatik.structures;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.Terminal;

public class Output {

    /**
     * Put this for empty arguments
     */
    public static final String EMPTY_ARGUMENT = " ";
    
    /**
     * If requirements fulfilled print this message 
     */
    private static final String SUCCESS = "Ok";

    /**
     * Round down to this much decimal places
     */
    private static final int DECIMAL_PLACES = 3;
    
    private boolean okMessage;
    private List<String> paragraphs;
    
    /**
     * Creates a new empty output object for
     * printing data to the screen 
     * 
     * @param okMessage true if OK should be printed
     */
    public Output(boolean okMessage) {
        paragraphs = new ArrayList<>();
        this.okMessage = okMessage;
    }
    
    /**
     * Creates an output object which prints
     * the given entry to the screen 
     * 
     * @param entry to print
     */
    public Output(Object entry) {
        this(false);
        paragraphs.add(entry.toString());
    }

    /**
     * Adds a new entry to be printed out as a
     * string representation
     * 
     * @param entry to print
     */
    public void addEntry(Object entry) {
        paragraphs.add(entry.toString());
    }

    /**
     * Prints all entries to the terminal
     */
    public void printToTerminal() {
        
        // Print to Terminal
        if (paragraphs.size() != 0) {
            for (String entry : paragraphs)
                Terminal.printLine(entry);
        }
        
        // Ok message
        if (okMessage)
            Terminal.printLine(SUCCESS);
    }

    /**
     * Rounds given numbers to specific amount of decimal places
     * 
     * @param input number to round
     * @return rounded number
     */
    public static String roundDecimals(double input) {
        
        // Round
        double rounded = Math.floor(input * Math.pow(10, DECIMAL_PLACES)) / Math.pow(10, DECIMAL_PLACES);
        
        // Convert
        String result = Double.toString(rounded);
        
        // Add trailing zeros
        while (result.length() < 5)
            result += "0";
        
        return result;
    }
    
}
