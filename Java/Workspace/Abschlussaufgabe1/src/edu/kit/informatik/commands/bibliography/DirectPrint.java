package edu.kit.informatik.commands.bibliography;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.entities.VenueType;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;
import edu.kit.informatik.structures.Style;
import edu.kit.informatik.structures.Util;

public abstract class DirectPrint extends Command {
    
    /**
     * Prints a bibliography of the given argument.
     * This class is used to derive the "direct print conference" and 
     * "direct print journal" commands, as both contain the same logic
     * but do different calls to their appropriate style function 
     * 
     * @param argumentLength how many arguments allowed
     * @param keyword string to invoke command
     */
    public DirectPrint(int argumentLength, String keyword) {
        super(argumentLength, keyword);
    }

    /**
     * Processes the arguments with the given type in mind
     * 
     * @param database to get data from
     * @param type of what type the entry is
     * @param args arguments to process
     * @return entry of arguments
     * @throws DataNotFoundException 
     * @throws BadArgumentException 
     */
    public String getEntry(Database database, VenueType type, String... args) 
            throws DataNotFoundException, BadArgumentException {
        
        // Check if style valid
        Style style = database.findStyle(args[0].split(":")[0]);
        
        // Correct the first argument, 
        // by removing the "[style]:" part, for easier processing.
        // args[0] only contains an authorId then.
        args[0] = args[0].split(":")[1];
        
        // Check if first author is given
        if (args[0].equals(""))
            throw new BadArgumentException("main author missing");

        // Check valid year
        if (type == VenueType.SERIES && !Util.isValidYear(args[6]))
            throw new BadArgumentException("not a valid year");
        else if (type == VenueType.JOURNAL && !Util.isValidYear(args[5]))
            throw new BadArgumentException("not a valid year");
        
        // Check for duplicates
        List<String> authorIds = Arrays.asList(new String[]{args[0], args[1], args[2]});
        
        // Multiple empty arguments will shrink the set as well. emptySpace counteracts this
        int emptySpace = 0;
        if (args[1].equals(Output.EMPTY_ARGUMENT) && args[2].equals(Output.EMPTY_ARGUMENT))
            emptySpace = 1;
        
        Set<String> noDuplicates = new HashSet<>(authorIds);
        if (noDuplicates.size() + emptySpace < authorIds.size())
            throw new BadArgumentException("duplicate authors");
        
        // Format depending on type
        String entry = null;
        
        if (type == VenueType.SERIES)
            entry = style.formatConference(1, authorIds, args[3], args[4], args[5], args[6]);
        else if (type == VenueType.JOURNAL)
            entry = style.formatJournal(1, authorIds, args[3], args[4], args[5]);
        else
            throw new BadArgumentException("style does not exist");
        
        return entry;
    }
}
