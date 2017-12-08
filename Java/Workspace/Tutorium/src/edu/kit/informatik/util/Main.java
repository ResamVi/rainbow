package edu.kit.informatik.util;

public class Main {

    public static void main(String[] args) {
        final TutorialArrayList islandBooks = new TutorialArrayList(3);
        islandBooks.add("Harry Potter");
        islandBooks.add("Lord of the Rings");
        islandBooks.add("The Art of Computer Programming");
        System.out.println(islandBooks.getReservedSize()); // 3
        islandBooks.add("The Catcher in the Rye");
        System.out.println(islandBooks.getReservedSize()); // 6
        System.out.println(islandBooks.getAt(2)); // The Art of Computer Programming
    }
}
