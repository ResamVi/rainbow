package edu.kit.informatik.events;

import edu.kit.informatik.helper.Identifiable;
import edu.kit.informatik.helper.Summarizable;

/**
 * A course is an event that grants credits
 * 
 * @author Julien Midedji
 */
public abstract class Course implements Summarizable, Comparable<Course>, Identifiable {
    /**
     * Keeps count of current amount of instances.
     */
    private static int amount = 0;

    private final String name;
    private final String id;

    /**
     * Creates a new Course that can be attended and grants credits for
     * completion.
     * 
     * @param name
     *            name of the course
     * @throws IllegalArgumentException
     *             if name is empty or null
     */
    protected Course(String name) {

        // Arguments should only contain letters
        if (!name.matches("[a-z]+") || !name.matches("[a-z]+"))
            throw new IllegalArgumentException();

        this.name = name;
        this.id = String.valueOf(++amount);
    }

    /**
     * Should return the amount of credit this course grants in total
     * @return amount of credits
     */
    public abstract int getCredits();

    /**
     * Returns tne name of the course
     * 
     * @return name of course
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the id of the course Every course gets a unique identification
     * number
     * 
     * @return id of course
     */
    public String getId() {
        return id;
    }

    /**
     * A course is identical if this id matches the other otherwise use
     * numerical ordering
     * 
     * @return order
     */
    @Override
    public int compareTo(Course other) {
        return getId().compareTo(other.getId());
    }
}
