package edu.kit.informatik.commands;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.accounts.Professor;
import edu.kit.informatik.accounts.Student;
import edu.kit.informatik.events.Lecture;
import edu.kit.informatik.events.Module;
import edu.kit.informatik.helper.Database;
import edu.kit.informatik.helper.DuplicateException;
import edu.kit.informatik.helper.IllegalIdException;
import edu.kit.informatik.helper.LimitReachedException;

/**
 * Command structure for adding entries to databases
 * 
 * @author Julien Midedji
 */
public class AddCommand extends Command {

    private EntryType type;

    /**
     * Creates a command that can add entries with the given type
     * 
     * @param type
     *            the type of the given entries
     */
    public AddCommand(EntryType type) {
        this.type = type;
    }

    /**
     * Adds the entry to the database
     * 
     * @param param
     *            input parameters that should be processed
     * @throws IllegalArguments
     *             if param is not [firstname][lastname][chair]
     */
    @Override
    public void execute(Database database, String... param) {

        // No errors happened
        boolean hasErred = false;

        // Check if arguments are missing
        if (param.length != type.argumentCount()) {
            Terminal.printError("wrong argument count");
            return;
        }

        // Add entry depending on type
        switch (type) {

        case LECTURE:
            hasErred = lectureCase(database, param[0], param[1], param[2] + " " + param[3] + " " + param[4],
                    Integer.parseInt(param[5]));
            break;

        case PROFESSOR:
            hasErred = professorCase(database, param[0], param[1], param[2]);
            break;

        case MODULE:
            hasErred = moduleCase(database, param[0]);
            break;

        case STUDENT:
            hasErred = studentCase(database, param[0], param[1], param[2]);
            break;

        default:
            break;

        }
        if (!hasErred)
            Terminal.printLine("Ok");
    }

    /**
     * Is executed when a lecture is given
     * 
     * @param database database to change/read
     * @param name name of lecture
     * @param moduleId associated lecture
     * @param professorId lecturer
     * @param credits how much ects this lecture grants
     * @return true if no error has happened
     */
    private boolean lectureCase(Database database, String name, String moduleId, String professorId, int credits) {

        boolean hasErred = false;

        // Interpret parameters
        Lecture lecture = new Lecture(database, name, moduleId, credits);

        try {
            database.addLectureEntry(lecture, moduleId, professorId);

        } catch (DuplicateException e) {
            Terminal.printError("lecture already exists");
            hasErred = true;

        } catch (LimitReachedException e) {
            Terminal.printError("credit points limit reached");
            hasErred = true;

        }
        return hasErred;
    }

    /**
     * Is executed when a professor is given
     * 
     * @param database database to change/read
     * @param firstName first name of professor
     * @param lastName last name of professor
     * @param chair associated university chair
     * @return true when no error has happened
     */
    private boolean professorCase(Database database, String firstName, String lastName, String chair) {

        boolean hasErred = false;

        try {
            database.addProfessorEntry(new Professor(firstName, lastName, chair));

        } catch (DuplicateException e) {
            Terminal.printError("professor already exists.");
            hasErred = true;

        } catch (IllegalArgumentException e) {
            Terminal.printError("arguments should only contain lower-case letters");
            hasErred = true;
        }

        return hasErred;
    }

    /**
     * Executed when a module is given
     * 
     * @param database database to change/read
     * @param moduleId name of the module
     * @return true when no error
     */
    private boolean moduleCase(Database database, String moduleId) {

        boolean hasErred = false;

        try {
            database.addModuleEntry(new Module(moduleId));

        } catch (DuplicateException e) {
            Terminal.printError("module already exists.");
            hasErred = true;
        }

        return hasErred;
    }

    /**
     * Is executed when a student is given
     * 
     * @param database to change/read
     * @param firstName first name of student
     * @param lastName last name of student
     * @param matrNumber matriculation number
     * @return true when no error occured
     */
    private boolean studentCase(Database database, String firstName, String lastName, String matrNumber) {

        boolean hasErred = false;

        try {
            Student student = new Student(firstName, lastName, matrNumber);
            database.addStudentEntry(student);

        } catch (DuplicateException e) {
            Terminal.printError("student already exists.");
            hasErred = true;

        } catch (IllegalIdException e) {
            Terminal.printError("matriculation number is not valid");
            hasErred = true;
        }

        return hasErred;
    }
}
