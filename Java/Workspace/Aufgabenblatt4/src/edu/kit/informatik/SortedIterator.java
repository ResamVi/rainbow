package edu.kit.informatik;

/**
 * An iterator for a list
 * 
 * @author Julien Midedji
 * @param <T> Type of the values in list
 */
public interface SortedIterator<T extends Comparable<T>> { // TODO: Public?

    /**
     * @return Returns true if the list contains more elements
     */
    boolean hasNext();

    /**
     * @return The next element in the list
     */
    T next();
}
