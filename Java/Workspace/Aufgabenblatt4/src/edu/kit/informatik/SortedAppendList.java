package edu.kit.informatik;

/**
 * An interface for objects that implement a flexible and self-sorting list.
 * 
 * @author Julien Midedji
 * @param <T> type of compared values
 */
public interface SortedAppendList<T extends Comparable<T>> {

    /**
     * Adds an element to list so that it's correctly ordered
     * 
     * @param element to add
     */
    void addSorted(T element);

    /**
     * Returns a new iterator for the list
     * 
     * @return The iterator to cycle through list
     */
    SortedIterator<T> iterator();

}
