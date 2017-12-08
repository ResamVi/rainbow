package edu.kit.informatik.helper;

public interface Identifiable {
    
    /**
     * To identify a specific object use its id
     * 
     * @return a unique id for that object
     */
    String getId();
    
    /**
     * Can be used by the List command to print a String representation
     * of each element. 
     * 
     * @return string representation of element
     */
    String listing();
}
