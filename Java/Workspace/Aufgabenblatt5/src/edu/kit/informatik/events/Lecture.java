package edu.kit.informatik.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.accounts.Student;
import edu.kit.informatik.accounts.User;
import edu.kit.informatik.commands.EntryType;
import edu.kit.informatik.helper.Database;
import edu.kit.informatik.helper.GradeAlreadyGivenException;
import edu.kit.informatik.helper.Identifiable;

/**
 * A lecture is the an elemental course unit and multiple make up a module
 * 
 * @author Julien Midedji
 */
public class Lecture extends Course {

    /**
     * If the student has not yet been graded
     */
    private static final double NOT_ENTERED = -1.0;
    
    /**
     * List of participants and their associated grade in this lecture
     */
    private HashMap<Student, Double> grades;

    private int credits;

    /**
     * Creates a new lecture that is part of a module
     * 
     * @param database
     *            to save the students name
     * @param name
     *            name of the lecture
     * @param moduleId
     *            id of the module this lecture is part of
     * @param credits
     *            total amount of credits this course grants
     *
     * @throws IllegalArgumentException
     *             if credit is not in interval [1, 9]
     * 
     */
    public Lecture(Database database, String name, String moduleId, int credits) {
        super(name);

        // Maximum of 9 credits total allowed
        if (credits < 10 && credits > 0)
            this.credits = credits;
        else
            throw new IllegalArgumentException(); // TODO: wrong parameter

        this.grades = new HashMap<>();

        // Register all the available students but do not give them a grade yet
        for (Identifiable participant : database.getEntryListByType(EntryType.STUDENT))
            grades.put((Student) participant, NOT_ENTERED);

    }

    /**
     * Return the amount of credit this lecture grants in total
     */
    @Override
    public int getCredits() {
        return credits;
    }

    /**
     * Lists all the properties of this lecture (id, name, credits, average)
     * 
     * @return format
     */
    @Override
    public String listing() {
        return String.format("%s %s %d %s", getId(), getName(), credits, User.gradeToString(getAverage()));
    }

    /**
     * Calculates the average grade of all students in this lecture Students who
     * have not been graded yet are disregarded.
     * 
     * @return average real number between 1 and 5
     */
    public double getAverage() {

        double totalMark = 0;
        int totalStudents = 0;

        for (Student student : grades.keySet()) {

            // Get mark
            double mark = grades.get(student);

            // Add to total if available
            if (mark != NOT_ENTERED) {
                totalMark += mark;
                totalStudents++;
            }
        }
        // Avoid division with 0
        if (totalStudents > 0)
            return totalMark / totalStudents;
        else
            return NOT_ENTERED;
    }

    /**
     * Gives student a grade
     * 
     * @param matrNum id of student
     * @param mark the grade
     * @throws GradeAlreadyGivenException when grade is already set
     */
    public void gradeStudent(String matrNum, double mark) throws GradeAlreadyGivenException {

        // Find student with matrNum
        for (Student student : grades.keySet()) {

            // If found: set grade
            if (student.getMatrNumber() == matrNum) {

                // if grade not already set then
                if (grades.get(student) == User.NOT_ENTERED)
                    grades.put(student, mark);
                else
                    throw new GradeAlreadyGivenException();
            }

        }
        // TODO: student not found?

    }

    /**
     * Add student to this lecture, no grade received yet
     * 
     * @param student
     *            to add
     */
    public void addStudent(Student student) {
        grades.put(student, User.NOT_ENTERED);
    }

    /**
     * Creates a summary containing matrNum, firstname, lastname and average
     * grade of each student
     * 
     * @return set
     */
    @Override
    public Set<String> summary() {
        Set<String> output = new HashSet<>();

        // Create line of each student
        for (Student student : grades.keySet()) {

            String element = String.format("%s %s %s %sf", student.getMatrNumber(), student.getFirstName(),
                    student.getLastName(), User.gradeToString(grades.get(student)));
            output.add(element);
        }

        return output;
    }
}
