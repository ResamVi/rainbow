package edu.kit.informatik.helper;

import java.util.Set;

/**
 * A summarizable object can be used by the Summary command 
 * to print a brief text of specific data
 * 
 * @author Julien Midedji
 */
public interface Summarizable {

    /**
     * Should create a set to view this object's data
     * 
     * @return set should contain elements with summarized data of its object 
     */
    Set<String> summary();
    
}
