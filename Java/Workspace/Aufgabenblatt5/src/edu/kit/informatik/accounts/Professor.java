package edu.kit.informatik.accounts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.events.Lecture;

/**
 * Represents a professor account.
 * 
 * @author Julien Midedji
 */
public class Professor extends User implements Comparable<Professor> {

    private final String chair;
    private HashMap<Lecture, Double> lectures;

    /**
     * Creates a new professor account.
     * 
     * @param firstName
     *            first name
     * @param lastName
     *            last name
     * @param chair
     *            the professor's associate chair
     */
    public Professor(String firstName, String lastName, String chair) {
        super(firstName, lastName);

        if (!chair.matches("[a-z]+"))
            throw new IllegalArgumentException();

        this.chair = chair;

        /**
         * List of lectures with their general student average grade
         */
        this.lectures = new HashMap<>();
    }

    /**
     * Compares professors according to first name, then last name, then chair
     * name
     * 
     * @return negative if this precedes other lexicographically. Zero if they
     *         are identical, else positive
     */
    @Override
    public int compareTo(Professor user) {

        // Check first name
        int diff = getFirstName().compareTo(user.getFirstName());
        if (diff != 0)
            return diff;

        // Check last name
        diff = getLastName().compareTo(user.getLastName());
        if (diff != 0)
            return diff;

        // Check chair name
        diff = chair.compareTo(user.getChair());
        if (diff != 0)
            return diff;

        return 0;
    }

    /**
     * Returns the Chair object this professor belongs to
     * 
     * @return academic chair object
     */
    public String getChair() {
        return chair;
    }

    /**
     * The average grades of all lectures he teaches. A lecture's grade is
     * determined by the total average grade of its students.
     * 
     * @return average or -1 if there are no grades yet
     */
    public double getAverage() {

        double totalMark = 0;
        int totalLectures = 0;

        for (Lecture lecture : lectures.keySet()) {

            // Get mark
            double mark = lectures.get(lecture);

            // Add to total if available
            if (mark != User.NOT_ENTERED) {
                totalMark += mark;
                totalLectures++;
            }
        }

        // Avoid division with 0
        if (totalLectures > 0)
            return totalMark / totalLectures;
        else
            return User.NOT_ENTERED;
    }

    /**
     * Make this professor responsible for teaching a new lecture
     * 
     * @param lecture
     *            he should teach
     */
    public void addLecture(Lecture lecture) {
        lectures.put(lecture, User.NOT_ENTERED);
    }

    /**
     * Lists all the properties of this module (chair, first name, last name,
     * average)
     * 
     * @return format
     */
    @Override
    public String listing() {
        return String.format("%s %s %s %s", chair, getFirstName(), getLastName(), User.gradeToString(getAverage()));
    }

    /**
     * Creates a summary of lectures this professor teaches containing lecture
     * id, lecture name and average grade of all participating students in a
     * lectures
     * 
     * @return set
     */
    @Override
    public Set<String> summary() {
        Set<String> output = new HashSet<>();

        for (Lecture lecture : lectures.keySet()) {
            double grade = lectures.get(lecture);

            String element = String.format("%d %s %.2f", lecture.getId(), lecture.getName(), User.gradeToString(grade));
            output.add(element);
        }

        return output;
    }

    /**
     * To identify a professor
     * 
     * @return an id consisting of "[firstName] [lastName] [chair]"
     */
    @Override
    public String getId() {
        return getFirstName() + " " + getLastName() + " " + chair;
    }
}
