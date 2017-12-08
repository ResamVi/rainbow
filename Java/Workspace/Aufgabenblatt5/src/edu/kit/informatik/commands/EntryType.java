package edu.kit.informatik.commands;

import edu.kit.informatik.accounts.Professor;
import edu.kit.informatik.accounts.Student;
import edu.kit.informatik.events.Lecture;
import edu.kit.informatik.events.Module;

/**
 * The types a database entry can accept, 
 * holds information on how a valid command of this type is structured 
 * 
 * @author Julien Midedji
 */
public enum EntryType {
    
    /**
     * Professor type
     */
    PROFESSOR(3, Professor.class),
    
    /**
     * Student type
     */
    STUDENT(3, Student.class),
    
    /**
     * Module type
     */
    MODULE(1, Module.class),
    
    /**
     * Lecture type
     */
    LECTURE(6, Lecture.class);
    
    private final int argumentCount;
    private final Class<?> classType;
    
    /**
     * Creates A new entry database that can be stored into the database 
     * 
     * @param argumentCount how many arguments are allowed
     */
    private EntryType(int argumentCount, Class<?> classType) {
        this.argumentCount = argumentCount;
        this.classType = classType;
    }
    
    /**
     * Amount of arguments for a valid command
     * 
     * @return argument count
     */
    public int argumentCount() {
        return argumentCount;
    }
    
    /**
     * Returns associated class of this entry typs
     * 
     * @return class
     */
    public Class<?> classType() {
        return classType;
    }
}
