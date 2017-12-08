package edu.kit.informatik;

/**
 * Creates a single and sorted list of two SortedAppendLists 
 * 
 * @author Julien Midedji
 *
 * @param <T> type of values stored
 */
public class UnionSortedIterator<T extends Comparable<T>> implements SortedIterator<T> {

    private LinkedSortedAppendList<T> combination;
    private SortedIterator<T> iterator;

    public UnionSortedIterator(SortedIterator<T> iteratorA, SortedIterator<T> iteratorB) {
        
        // Combine both lists
        combination = new LinkedSortedAppendList<>();

        while (iteratorA.hasNext())
            combination.addSorted(iteratorA.next());

        while (iteratorB.hasNext())
            combination.addSorted(iteratorB.next());

        iterator = combination.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}
