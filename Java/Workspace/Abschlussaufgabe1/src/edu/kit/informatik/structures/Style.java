package edu.kit.informatik.structures;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.Exceptions.BadArgumentException;

public abstract class Style {

    private final String name;
    
    /**
     * Creates a new style of citation used by bibliographies.
     * 
     * @param name of style
     */
    public Style(String name) {
        this.name = name;
    }
    
    /**
     * Should return an entry for conferences containing all the given data
     * in the desired format.
     * 
     * @param index of entry
     * @param authors authors, must have atleast 1 main author
     * @param title of article
     * @param series name of series
     * @param location name of location
     * @param year of release
     * @return formatted entry
     * @throws BadArgumentException if the arguments were invalid
     */
    public abstract String formatConference(int index, List<String> authors, String title,
            String series, String location, String year) throws BadArgumentException;

    /**
     * Should return an entry for journals containing all the
     * given data in the desired format.
     * 
     * @param index of entry
     * @param authors authors, must have at least 1 main author
     * @param title of article
     * @param journal name
     * @param year of release
     * @return formatted entry
     * @throws BadArgumentException if the arguments were invalid
     */
    public abstract String formatJournal(int index, List<String> authors, String title, String journal, String year) 
            throws BadArgumentException;

    /**
     * Returns name of the style
     * 
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Filters out empty arguments and
     * formats all names
     * 
     * @param names
     *            of authors
     * @return list of valid names
     * @throws BadArgumentException if an invalid name was found
     */
    protected List<String> getValidNames(List<String> names) throws BadArgumentException {

        List<String> authorNames = new ArrayList<>();

        for (String authorName : names) {
            
            // Filter & format
            if (!authorName.equals(Output.EMPTY_ARGUMENT))
                authorNames.add(formatName(authorName));
        }

        return authorNames;
    }

    /**
     * Should determine how the name is shown in the list entry
     * 
     * @param name the full name as "[firstName] [lastName]"
     * @return formatted name 
     * @throws BadArgumentException if name is not of format "[firstName] [lastName]"
     */
    protected abstract String formatName(String name) throws BadArgumentException;
}
