package edu.kit.informatik.commands.changes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.Exceptions.DataNotFoundException;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Entity;
import edu.kit.informatik.structures.Output;

public class AddKeyword extends Command {

    /**
     *  Adds a keyword to the entity. Keywords are used
     *  to further describe properties of the entity.
     *  
     *   Arguments: [entityType] [entityId]:[keyword];[keyword];....
     */
    public AddKeyword() {
        super(1, "add keywords to");
    }

    @Override
    public Output execute(Database database, String... args) throws DataNotFoundException, BadArgumentException {
        
        // Pre-emptive measures before parsing
        if (!args[0].matches("(.+) (.+):(.+)"))
            throw new BadArgumentException("wrong syntax");
        
        Pattern pattern         = Pattern.compile("(.+) (.+):(.+)");
        Matcher firstArgument   = pattern.matcher(args[0]);
        
        // Additional parsing of first argument
        String type = null;
        String typeId = null;
        String[] keywords = null;
        
        if (firstArgument.find()) {
            type            = firstArgument.group(1);
            typeId          = firstArgument.group(2);
            keywords        = firstArgument.group(3).split(";");
        }
        
        // Look for entity depending on type
        Entity entity = null;
        switch (type) {
        
        case "pub": entity = database.findPublication(typeId);
            break;
            
        case "journal": entity = database.findJournal(typeId);
            break;
            
        case "series": entity = database.findSeries(typeId);
            break;
        
        default:
            throw new BadArgumentException("illegal type argument");
        }
        
        // Check if keywords lowercase,
        for (String keyword : keywords) {
            if (!keyword.matches("[a-z]+"))
                throw new BadArgumentException(keyword + " is not a valid keyword");
        }
        
        // Add
        for (String keyword : keywords)
                entity.addKeyword(keyword);
        
        return new Output(true);
    }

}
