package edu.kit.informatik.accounts;

import edu.kit.informatik.helper.Identifiable;
import edu.kit.informatik.helper.Summarizable;

/**
 * A User represents a personal account with associated data.
 * 
 * @author Julien Midedji
 */
public abstract class User implements Summarizable, Identifiable {

    /**
     * If a user is not graded use this constant
     */
    public static final double NOT_ENTERED = -1.0;

    private final String firstName;
    private final String lastName;

    /**
     * Creates a User that represents a personal account with associated data.
     * 
     * @param firstName
     *            User's first name. Should only have letters only and atleast
     *            one letter.
     * @param lastName
     *            User's last name. Should only have letters only and atleast
     *            one letter.
     */
    protected User(String firstName, String lastName) {

        // Arguments should only contain letters
        if (!firstName.matches("[a-z]+") || !lastName.matches("[a-z]+"))
            throw new IllegalArgumentException();

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns first name
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns last name
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * If grade is available convert to string, else put none
     * 
     * @param grade
     *            grade
     * @return string of grade
     */
    public static String gradeToString(double grade) {

        if (grade != NOT_ENTERED)
            return String.format("%.2f", grade);
        else
            return "none";

    }
}
