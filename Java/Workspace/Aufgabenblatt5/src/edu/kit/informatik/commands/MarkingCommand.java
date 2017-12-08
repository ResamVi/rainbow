package edu.kit.informatik.commands;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.accounts.Student;
import edu.kit.informatik.events.Lecture;
import edu.kit.informatik.helper.Database;
import edu.kit.informatik.helper.GradeAlreadyGivenException;

/**
 * Command structure for changing grades for a student in a lecture
 * 
 * @author Julien Midedji
 */
public class MarkingCommand extends Command {

    /**
     * Gives the specificed student the grade
     * 
     * @param database
     *            a database to change/read
     * @param param
     *            string in format: [id];[matrnr];[mark]
     */
    @Override
    public void execute(Database database, String... param) {

        boolean hasErred = false;

        // Check if arguments are missing TODO:
        if (param.length != 3) {
            Terminal.printError("wrong argument count");
            return;
        }

        Lecture lecture = database.findLectureEntry(param[0]);
        Student student = database.findStudentEntry(param[1]);
        double grade = Double.parseDouble(param[2]);

        if (lecture == null || student == null) {
            Terminal.printError("lecture or student was not found");
            return;
        }
        if (grade < 1 || grade > 5) {
            Terminal.printError("grade should be between 1.0 and 5.0 inclusive");
            return;
        }

        try {
            lecture.gradeStudent(student.getMatrNumber(), grade);
        } catch (GradeAlreadyGivenException e) {
            Terminal.printError("student already graded");
            hasErred = true;
        }
        student.addGrade(lecture, grade);

        if (!hasErred)
            Terminal.printLine("Ok");
    }

}
