package edu.kit.informatik.styles;

import java.util.List;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Style;
import edu.kit.informatik.structures.Util;

public class ChicagoSimplified extends Style {

    /**
     * Chicago Simplified style as specified in B.2
     */
    public ChicagoSimplified() {
        super("chicago");
    }

    @Override
    public String formatConference(int index, List<String> authors, String title, String series, String location,
            String year) throws BadArgumentException {

        String result = "";

        // <bibidentifier>
        result += "(" + authors.get(0).split(" ")[1] + ", " + year + ") ";

        // Format names
        List<String> authorNames = getValidNames(authors);

        // <author list>
        String lastMention = authorNames.get(authorNames.size() - 1);
        
        // Put "and" at the end
        if (authorNames.size() > 1) {
            authorNames.remove(authorNames.size() - 1);
            result += String.join(", ", authorNames) + ", and " + lastMention + ". ";
        } else
            result += lastMention + ". ";

        // <title>
        result += "\"" + title + ".\" ";

        // <conference series name>
        result += "Paper presented at " + series + ", ";

        // <year of conference>
        if (!Util.isValidYear(year))
            throw new BadArgumentException("invalid year");
        
        result += year + ", ";

        // <location of conference>
        result += location + ".";

        return result;
    }

    @Override
    public String formatJournal(int index, List<String> authors, String title, String journal, String year) 
            throws BadArgumentException {
        
        String result = "";
        
        // <bibidentifier>
        result += "(" + authors.get(0).split(" ")[1] + ", " + year + ") ";
        
        // Format names
        List<String> authorNames = getValidNames(authors);
        
        /// <author list>
        String lastMention = authorNames.get(authorNames.size() - 1);
        
        // Put "and" at the end
        if (authorNames.size() > 1) {
            authorNames.remove(authorNames.size() - 1);
            result += String.join(", ", authorNames) + ", and " + lastMention + ". ";
        } else
            result += lastMention + ". ";
        
        // <title>
        result += "\"" + title + ".\" ";
        
        // <journal title>
        result += journal + " ";
        
        // <year>
        result += "(" + year + ").";
        
        return result;
    }

    @Override
    protected String formatName(String input) throws BadArgumentException {

        // Check if argument syntax is correct
        if (input.split(" ").length != 2)
            throw new BadArgumentException("\"" + input + "\" is not a valid name");
        
        String firstName = input.split(" ")[0];
        String lastName = input.split(" ")[1];
        return lastName + ", " + firstName;
    }
}
