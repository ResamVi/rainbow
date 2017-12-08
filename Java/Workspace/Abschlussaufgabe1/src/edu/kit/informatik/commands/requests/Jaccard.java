package edu.kit.informatik.commands.requests;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.Exceptions.BadArgumentException;
import edu.kit.informatik.structures.Command;
import edu.kit.informatik.structures.Database;
import edu.kit.informatik.structures.Output;

public class Jaccard extends Command {

    /**
     * Calculates the jaccard index for two sets of keywords
     * 
     * Argument : [word];[word];... [word];[word];...
     */
    public Jaccard() {
        super(2, "jaccard");
    }

    @Override
    public Output execute(Database database, String... args) throws BadArgumentException {
        
        // Only two sets allowed
        if (args.length > 2)
            throw new BadArgumentException("only two sets allowed");
        
        // Parsing
        Set<String> former = new HashSet<>(Arrays.asList(args[0].split(";")));
        Set<String> latter = new HashSet<>(Arrays.asList(args[1].split(";")));
        
        // Check if valid keyword
        for (String keyword : former) {
            if (!keyword.matches("[a-z]+"))
                throw new BadArgumentException("\"" + keyword + "\" is not a valid keyword");
        }
        
        for (String keyword : latter) {
            if (!keyword.matches("[a-z]+"))
                throw new BadArgumentException("\"" + keyword + "\" is not a valid keyword");
        }
        
        return new Output(calculateJaccard(former, latter));
    }

    /**
     * Calculates the jaccard index
     * 
     * @param former first set
     * @param latter second set
     * @return jaccard index
     */
    public static String calculateJaccard(Set<String> former, Set<String> latter) {

        // A and B empty
        if (former.size() == 0 && latter.size() == 0)
            return "1.000";
        
        // A union B
        Set<String> totalElements = new HashSet<String>(former);
        totalElements.addAll(latter);
        
        // A intersect B
        Set<String> sharedElements = new HashSet<String>(former);
        sharedElements.retainAll(latter);
        
        // Round to decimal places
        double result = sharedElements.size() / (double) totalElements.size();
        
        return Output.roundDecimals(result);
    }
    
}
