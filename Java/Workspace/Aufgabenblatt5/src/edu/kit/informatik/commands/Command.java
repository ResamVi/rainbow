package edu.kit.informatik.commands;

import edu.kit.informatik.helper.Database;

/**
 * Represents a command that can be invoked to change or return states.
 * 
 * @author Julien Midedji
 */
public abstract class Command {
    
    /**
     * Code that should be executed when command is invoked
     * 
     * @param database commands can change/read the state of this database
     * @param param various amounts of parameters
     */
    public abstract void execute(Database database, String... param);

}