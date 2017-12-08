package edu.kit.informatik.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.accounts.User;
import edu.kit.informatik.helper.LimitReachedException;

/**
 * A module represents a bundle of lectures.
 * 
 * @author Julien Midedji
 */
public class Module extends Course {

    /**
     * List of lectures that are part of this module
     */
    private HashMap<Lecture, Double> lectures;

    /**
     * Creates a new module. Module is a set of lectures.
     * 
     * @param name
     *            Name of module
     */
    public Module(String name) {
        super(name);
        this.lectures = new HashMap<>();
    }

    /**
     * Adds the total and returns the total credit of its lectures
     * 
     * @return total credits this module grants
     */
    @Override
    public int getCredits() {
        int totalCredits = 0;

        for (Lecture lecture : lectures.keySet())
            totalCredits += lecture.getCredits();

        return totalCredits;
    }

    /**
     * Adds the lecture to this module
     * 
     * @param lecture lecture to add
     * @throws LimitReachedException
     *             when total of lectures reaches above 45
     */
    public void addLecture(Lecture lecture) throws LimitReachedException {

        if (getCredits() + lecture.getCredits() > 45)
            throw new LimitReachedException();

        lectures.put(lecture, User.NOT_ENTERED);
    }

    /**
     * Calculates the weighted average of all lectures in this module
     * 
     * @return average // TODO: rand case?
     */
    public double getAverage() {

        // Calculate maximum possible credits
        int credits = 0;
        for (Lecture lecture : lectures.keySet())
            credits += lecture.getCredits();

        // Calculate weighted average
        double grades = 0;
        for (Lecture lecture : lectures.keySet()) {
            double average = lecture.getAverage();
            
            if (average != User.NOT_ENTERED)
                grades += average * lecture.getCredits();
        }
        if (credits > 0)
            return grades / credits;
        else
            return User.NOT_ENTERED;
    }

    /**
     * Lists all the properties of this module (id, name, credits, average)
     * 
     * @return format
     */
    @Override
    public String listing() {
        return String.format("%s %s %d %s", getId(), getName(), getCredits(), User.gradeToString(getAverage()));
    }

    /**
     * Creates a summary containing id, name, credits and average mark of each
     * lecture
     * 
     * @return set
     */
    @Override
    public Set<String> summary() {

        Set<String> output = new HashSet<>();

        for (Lecture lecture : lectures.keySet()) {
            double grade = lectures.get(lecture);
            String element = String.format("%s %s %d %s", lecture.getId(), lecture.getName(), lecture.getCredits(),
                    User.gradeToString(grade));
            output.add(element);
        }

        return output;

    }

}
