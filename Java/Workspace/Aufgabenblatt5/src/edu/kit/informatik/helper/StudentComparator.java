package edu.kit.informatik.helper;

import java.util.Comparator;

import edu.kit.informatik.accounts.Student;

/**
 * To sort lists with students
 * 
 * @author Julien Midedji
 */
public class StudentComparator implements Comparator<Identifiable> {

    /**
     * Compares two students
     * @param first first object
     * @param other other object to compare
     * @return int same as compareTo
     */
    @Override
    public int compare(Identifiable first, Identifiable other) {

        if (first instanceof Student) {
            Student a = (Student) first;
            Student b = (Student) other;
            return a.getMatrNumber().compareTo(b.getMatrNumber());
        }
        throw new IllegalArgumentException();
    }

}
