package edu.kit.informatik.structures;

public abstract class Command implements Executable {

    private final int       argumentLength;
    private final String    keyword;
    
    /**
     * Creates a command that can be executed
     * 
     * @param argumentLength amount of arguments it can be given
     * @param keyword name to invoke this command
     */
    public Command(int argumentLength, String keyword) {
        this.argumentLength = argumentLength;
        this.keyword = keyword;
    }
    
    /**
     * Amount of arguments to make a valid command call
     * 
     * @return argumentLength
     */
    public int getArgumentLength() {
        return argumentLength;
    }
    
    /**
     * Gets the String literal to invoke this command
     * 
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }
}
