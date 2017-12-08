package edu.kit.informatik.commands;

import java.util.Set;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.helper.Database;
import edu.kit.informatik.helper.Summarizable;

public class SummaryCommand extends Command {

    private EntryType type;

    /**
     * Creates a command that can list entries with the given type
     * 
     * @param type
     *            the type of the given entries
     */
    public SummaryCommand(EntryType type) {
        this.type = type;
    }

    /**
     * Prints a summary of the elements
     * 
     * @param database
     *            list of entries
     * @param param
     *            given parameters
     */
    @Override
    public void execute(Database database, String... param) {

        // Check if arguments are missing TODO:
        if (param.length != type.argumentCount()) {
            // throw exception

            // Check if not all lower-case
        } else if (param.length != 2) {
            // throw exception

            // Check if numbers or "special letters"
        } else if (param.length != 3) {
            // throw exception
        }

        Summarizable input;

        // Add entry depending on type
        switch (type) {

        case LECTURE:
            input = database.findLectureEntry(param[0]);
            break;
        case PROFESSOR:
            input = database.findProfessorEntry(param[0] + " " + param[1] + " " + param[2]);
            break;
        case MODULE:
            input = database.findModuleEntry(param[0]); // !Exception handling
                                                        // TODO
            break;
        case STUDENT:
            input = database.findStudentEntry(param[2]); // Look if matrNUmber
                                                         // fits first,last
                                                         // name? TODO
            break;
        default:
            // This case would only happen if this class got an invalid type in
            // its initialization
            input = null;
            break;
        }

        printSummary(input.summary());
    }

    /**
     * TODO:
     * 
     * @param set
     */
    private void printSummary(Set<String> set) {

        for (String line : set)
            Terminal.printLine(line);

    }
}
