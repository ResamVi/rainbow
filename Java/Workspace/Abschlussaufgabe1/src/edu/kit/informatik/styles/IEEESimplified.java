package edu.kit.informatik.styles;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Output;
import edu.kit.informatik.structures.Style;
import edu.kit.informatik.structures.Util;

public class IEEESimplified extends Style {

    /**
     * IEEE Simplified style as specified in B.1
     */
    public IEEESimplified() {
        super("ieee");
    }

    @Override
    public String formatConference(int index, List<String> authors, String title,
            String series, String location, String year) throws BadArgumentException {
        
        String result = "";
        
        // <bibidentifier>
        result += "[" + index + "] ";
        
        // Format names
        List<String> authorNames = getValidNames(authors);
        
        // <author list>
        result += listNames(authorNames);
        
        // <title>
        result += "\"" + title + ",\" ";
        
        // <conference series name>
        result += "in Proceedings of " + series + ", ";

        // <location of conference>
        result += location + ", ";
        
        // <year of conference>
        result += year + ".";
        
        return result;
    }

    @Override
    public String formatJournal(int index, List<String> authors, String title, String journal, String year) 
            throws BadArgumentException {
        
        String result = "";
        
        // <bibidentifier>
        result += "[" + index + "] ";
        
        // Format names
        List<String> authorNames = getValidNames(authors);
        
        // <author list>
        result += listNames(authorNames);
        
        // <title>
        result += "\"" + title + ",\" ";
        
        // <journal title>
        result += journal + ", ";
        
        // <year>
        if (!Util.isValidYear(year))
            throw new BadArgumentException("invalid year");
        
        result += year + ".";
        
        return result;
    }
    
    /**
     * Filters out empty arguments
     * 
     * @param names of authors
     * @return list of valid names
     * @throws BadArgumentException if an invalid name was found
     */
    protected List<String> getValidNames(List<String> names) throws BadArgumentException {
        // Format names
        List<String> authorNames = new ArrayList<>();
        
        for (String authorName : names) {
            if (!authorName.equals(Output.EMPTY_ARGUMENT))
                authorNames.add(formatName(authorName));
        }
        
        return authorNames;
    }
    
    @Override
    protected String formatName(String input) throws BadArgumentException {
        
        // Check if argument syntax is correct
        if (input.split(" ").length != 2)
            throw new BadArgumentException("\"" + input + "\" is not a valid name");
        
        String firstName = input.split(" ")[0].toUpperCase().charAt(0) + ". "; 
        String lastName = input.split(" ")[1];
        return firstName + lastName;
    }
    
    /**
     * author list as described
     * 
     * @param authorNames full names of authors (id)
     * @return author list
     */
    private static String listNames(List<String> authorNames) {

        // Exactly one author
        if (authorNames.size() == 1)
            return authorNames.get(0) + ", ";

        // Exactly two authors
        else if (authorNames.size() == 2)
            return authorNames.get(0) + " and " + authorNames.get(1) + ", ";
        
        // Three or more
        else 
            return authorNames.get(0) + " et al., ";
    }
}
