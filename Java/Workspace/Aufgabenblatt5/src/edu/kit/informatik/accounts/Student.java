package edu.kit.informatik.accounts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.events.Lecture;
import edu.kit.informatik.helper.IllegalIdException;

/**
 * Represents a student account.
 * 
 * @author Julien Midedji
 */
public class Student extends User implements Comparable<Student> {

    private final String matrNumber;
    private HashMap<Lecture, Double> attendedLectures;

    /**
     * Creates a new student account.
     * 
     * @param firstName
     *            first name
     * @param lastName
     *            last name
     * @param matrNumber
     *            The student's identification. A unique 6-figure number
     *            starting with a non-zero number
     * @throws IllegalIdException thrown when matriculation number invalid
     */
    public Student(String firstName, String lastName, String matrNumber) throws IllegalIdException {
        super(firstName, lastName);

        if (isValidMatrNumber(matrNumber))
            this.matrNumber = matrNumber;
        else
            throw new IllegalIdException();

        /**
         * Keeps journal of all grades this student has
         */
        this.attendedLectures = new HashMap<>();
    }

    /**
     * Returns the average grade of all graded lectures this student has
     * attended
     * 
     * @return average or none if there are no grades
     */
    public double getAverageGrade() {

        double totalGrade = 0;
        int lectureCredits = 0;

        for (Lecture lecture : attendedLectures.keySet()) {
            double grade = attendedLectures.get(lecture);
            if (grade != User.NOT_ENTERED) {
                totalGrade += grade * lecture.getCredits();
                lectureCredits += lecture.getCredits();
            }
        }

        if (lectureCredits > 0)
            return totalGrade / lectureCredits;
        else
            return User.NOT_ENTERED;
    }

    /**
     * Write the grade to this student's account
     * 
     * @param lecture
     *            lecture this grade is received in
     * @param grade
     *            the final grade of lecture
     */
    public void addGrade(Lecture lecture, Double grade) {

        // Add new lecture if not available
        if (!attendedLectures.containsKey(lecture))
            attendedLectures.put(lecture, User.NOT_ENTERED);

        // If available change it, (BUT ONLY IF IT HAS NOT BEEN CHANGED YET!)
        else if (attendedLectures.get(lecture) == User.NOT_ENTERED)
            attendedLectures.put(lecture, grade);
    }

    /**
     * Tests whether inputted matriculation number is valid A valid
     * matriculation number has 6 digits starts with a non-zero number
     * 
     * @return true if valid
     */
    private boolean isValidMatrNumber(String matrNumber) {
        if (matrNumber.charAt(0) == '0')
            return false;
        if (matrNumber.length() != 6)
            return false;
        if (!matrNumber.matches("[0-9]+"))
            return false;

        return true;
    }

    /**
     * Compares students ascending to their matriculation number.
     * 
     * @param other
     *            student to compare to.
     * @return negative if this matriculation number is before other, else
     *         positive.
     */
    @Override
    public int compareTo(Student other) {
        return matrNumber.compareTo(other.matrNumber);
    }

    /**
     * Returns a student's id
     * 
     * @return matriculation number, 6-digits
     */
    public String getMatrNumber() {
        return matrNumber;
    }

    /**
     * Lists all the properties of this module (matriculation number, first
     * name, last name, averageG)
     * 
     * @return format
     */
    @Override
    public String listing() {
        return String.format("%s %s %s %s", matrNumber, getFirstName(), getLastName(),
                User.gradeToString(getAverageGrade()));
    }

    /**
     * Creates a summary containing id, name and grade in all lectures of this
     * student grade of each student
     * 
     * @return set
     */
    @Override
    public Set<String> summary() {
        Set<String> output = new HashSet<>();

        for (Lecture lecture : attendedLectures.keySet()) {
            double grade = attendedLectures.get(lecture);

            String element = String.format("%d %s %.2f", lecture.getId(), lecture.getName(), User.gradeToString(grade));
            output.add(element);
        }
        return output;
    }

    /**
     * To identify a student
     * 
     * @return the matriculation number
     */
    @Override
    public String getId() {
        return matrNumber;
    }
}
