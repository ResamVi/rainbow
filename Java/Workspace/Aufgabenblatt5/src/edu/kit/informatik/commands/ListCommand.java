package edu.kit.informatik.commands;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.helper.Database;
import edu.kit.informatik.helper.Identifiable;

/**
 * Command structure for listing entries in databases
 * 
 * @author Julien Midedji
 */
public class ListCommand extends Command {

    private EntryType type;

    /**
     * Creates a command that can list entries with the given type
     * 
     * @param type
     *            the type of the given entries
     */
    public ListCommand(EntryType type) {
        this.type = type;
    }

    /**
     * Prints all entries of the specific type in the database to the console
     * 
     * @param param
     *            input parameters that should be processed
     * @throws IllegalArguments
     *             if param is not <firstname><lastname><chair>
     */
    @Override
    public void execute(Database database, String... param) {

        // TODO: if list is empty?

        // Iterate through list
        for (Identifiable entry : database.getEntryListByType(type)) {
            String line = entry.listing();
            Terminal.printLine(line);
        }
    }
}
