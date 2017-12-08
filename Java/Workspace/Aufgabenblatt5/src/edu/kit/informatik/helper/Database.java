package edu.kit.informatik.helper;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.accounts.Professor;
import edu.kit.informatik.accounts.Student;
import edu.kit.informatik.accounts.User;
import edu.kit.informatik.commands.EntryType;
import edu.kit.informatik.events.Lecture;
import edu.kit.informatik.events.Module;

/**
 * Keeps track of accounts
 * 
 * @author Julien Midedji
 */
public final class Database {

    /**
     * A list of all types available
     */
    private List<Identifiable> list;

    /**
     * Creates an empty database
     */
    public Database() {
        list = new ArrayList<>();
    }

    /**
     * Returns a sorted list of all students in the database
     * 
     * @param type
     *            elements of this type
     * @return list of students
     */
    public List<Identifiable> getEntryListByType(EntryType type) {

        List<Identifiable> matches = new ArrayList<>();

        // Get all entries that match the type
        for (Identifiable user : list) {

            if (type.classType().isInstance(user))
                matches.add(user);
        }
        
        if (type == EntryType.STUDENT)
            matches.sort(new StudentComparator());
        
        return matches;
    }

    /**
     * Adds an entry of type professor to the database
     * 
     * @param entry
     *            to add
     * @throws DuplicateException
     *             if entry already exists
     * @throws EntryNotFoundException
     *             if entry was not found
     */
    public void addProfessorEntry(Professor entry) throws DuplicateException {
        if (findProfessorEntry(entry.getId()) != null)
            throw new DuplicateException();
        else
            list.add(entry);
    }

    /**
     * Adds an entry of type module to the database
     * 
     * @param entry
     *            to add
     * @throws DuplicateException
     *             if entry already exists
     * @throws EntryNotFoundException
     *             if entry was not found
     */
    public void addModuleEntry(Module entry) throws DuplicateException {
        if (findModuleEntry(entry.getId()) != null)
            throw new DuplicateException();
        else
            list.add(entry);
    }

    /**
     * Adds an entry of type student to the database
     * 
     * @param entry
     *            to add
     * @throws DuplicateException
     *             if entry already exists
     * @throws EntryNotFoundException
     *             if entry was not found
     */
    public void addStudentEntry(Student entry) throws DuplicateException {

        if (findStudentEntry(entry.getId()) != null)
            throw new DuplicateException();
        else
            list.add(entry);

        // Register student for all current lectures
        for (Identifiable lecture : list) {
            if (lecture instanceof Lecture)
                ((Lecture) lecture).addStudent(entry);
        }

    }

    /**
     * Adds an entry of type lecture to the database
     * 
     * @param entry
     *            lecture entry to add
     * @param moduleId
     *            the associated module
     * @param lecturerId
     *            lecturer that teaches
     * @throws DuplicateException
     *             if entry already exists
     * @throws LimitReachedException
     *             when total of lectures reaches above 45
     */
    public void addLectureEntry(Lecture entry, String moduleId, String lecturerId)
            throws DuplicateException, LimitReachedException {

        if (findLectureEntry(entry.getId()) != null)
            throw new DuplicateException();
        else
            list.add(entry);

        // Lets the module know it has a new lecture added
        Module module = findModuleEntry(moduleId);
        if (module != null)
            module.addLecture(entry);
        else
            throw new DuplicateException(); // should be EntryNotFound - 3
                                            // throws not allowed ?????

        // Lets the professor know he has a new lecture to teach
        Professor lecture = findProfessorEntry(lecturerId);
        if (lecture != null)
            lecture.addLecture(entry);
        else
            throw new DuplicateException(); // should be EntryNotFound - 3
                                            // throws not allowed ?????

        // Every student is automatically registered for this lecture
        for (Identifiable student : list) {
            if (student instanceof Student)
                ((Student) student).addGrade(entry, User.NOT_ENTERED);
        }
    }

    /**
     * Looks for the entry
     * 
     * @param id
     *            of entry
     * @return entry when entry was found else null
     */
    private Identifiable findEntry(String id) {
        for (Identifiable entry : list) {

            if (entry.getId().equals(id))
                return entry;

        }
        return null;
    }

    /**
     * Looks into the list if a student of the given id exists, returns null
     * otherwise
     * 
     * @param id
     *            should be a 6-digit number
     * @return the entry or null if none
     */
    public Student findStudentEntry(String id) {
        return (Student) findEntry(id);
    }

    /**
     * Looks into the list if a module of the given id exists, returns null
     * otherwise
     * 
     * @param id
     *            should be a number
     * @return entry or null if none
     */
    public Module findModuleEntry(String id) {
        return (Module) findEntry(id);
    }

    /**
     * Looks into the list if a Professor with the given id exists, returns null
     * otherwise
     * 
     * @param id
     *            should be of format "[firstname] [lastname] [chair]"
     * @return the entry or null if none
     */
    public Professor findProfessorEntry(String id) {
        return (Professor) findEntry(id);
    }

    /**
     * Looks into the list if a lecture of the given id exists, returns null
     * otherwise
     * 
     * @param id
     *            should be a number
     * @return entry or null if none found
     */
    public Lecture findLectureEntry(String id) {
        return (Lecture) findEntry(id);
    }
}
