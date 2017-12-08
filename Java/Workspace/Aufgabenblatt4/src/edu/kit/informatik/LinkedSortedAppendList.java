package edu.kit.informatik;

/**
 * Creates a linked list that is sorted
 * 
 * @author Julien Midedji
 * @param <T> The variable type to sort
 */
public class LinkedSortedAppendList<T extends Comparable<T>> implements SortedAppendList<T> {

    /**
     * First entry in list
     */
    private Cell head;

    /**
     * Initialize empty List
     */
    public LinkedSortedAppendList() {
        head = null;
    }

    /**
     * A Cell represents a single element in the list holding the value and a
     * reference to its neighbour cells
     *
     * @param <T> type of value
     */
    private class Cell {
        private T content;
        private Cell prev;
        private Cell next;

        private Cell(T content, Cell prev, Cell next) {
            this.content = content;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * An Iterator can cycle through the list and return its content
     */
    private class Iterator implements SortedIterator<T> {

        private int pointer;

        private Iterator() {
            pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return getElement(pointer) != null;
        }

        @Override
        public T next() {

            Cell current = getElement(pointer);

            if (current == null) {
                System.out.println("No such element");
                return null;
            }

            pointer++;
            return current.content;

        }

    }

    /**
     * Returns the element at the specific position in the list
     * 
     * @param index Position of element
     * @return Element at position
     */
    private Cell getElement(int index) {
        Cell current = head;

        for (int i = 0; i < index; i++)
            current = current.next;

        return current;
    }

    /**
     * Adds an element to list in correct order
     * 
     * @param element to add
     */
    @Override
    public void addSorted(T element) {

        // Pointer element
        Cell current = head;

        boolean added = false;

        // Initialize head element
        if (current == null) {
            head = new Cell(element, null, null);

            // Initialize elements after head was initialized
        } else {

            // Special case: New element needs to be before head
            // Need to change head element
            if (element.compareTo(current.content) < 0) {
                Cell temp = head;
                Cell newElement = new Cell(element, null, temp);
                temp.prev = newElement;
                head = newElement;
                added = true;
            }

            // Iterate through list until end reached
            while (current.next != null && !added) {

                // When new element is lower than current neighbour,
                // put it in between
                if (element.compareTo(current.next.content) < 0) {

                    Cell newElement = new Cell(element, current, current.next);

                    // Right neighbour of newCell has to point on newCell
                    newElement.next.prev = newElement;

                    // Left neighbour of newCell has to point on newCell
                    current.next = newElement;
                    added = true;
                    break;
                }

                // When new element is equal or higher, continue down the list
                else if (element.compareTo(current.content) >= 0)
                    current = current.next;
            }

            // Reached end but not yet added to list: put at the end
            if (!added)
                current.next = new Cell(element, current, null);
        }
    }

    @Override
    public SortedIterator<T> iterator() {
        return new Iterator();
    }
}
