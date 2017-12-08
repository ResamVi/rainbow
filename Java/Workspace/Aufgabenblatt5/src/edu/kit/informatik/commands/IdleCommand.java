package edu.kit.informatik.commands;

import edu.kit.informatik.helper.Database;

/**
 * Command structure for staying idle
 * 
 * @author Julien Midedji
 */
public class IdleCommand extends Command {

    /**
     * Does nothing. Use this instead of null.
     * 
     * @param database a database to change/read
     * @param param parameters with data to change the state
     */
    @Override
    public void execute(Database database, String... param) {
    }

}
