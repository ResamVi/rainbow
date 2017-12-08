package edu.kit.informatik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.kit.informatik.Exceptions.*;
import edu.kit.informatik.commands.bibliography.*;
import edu.kit.informatik.commands.changes.*;
import edu.kit.informatik.commands.requests.*;
import edu.kit.informatik.structures.*;

public class UserInterface {

    /**
     * Begin user interaction
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        
        // TODO: Remove when uploading to praktomat
        // Locale.setDefault(Locale.US);
        
        Database database = new Database();
        List<Command> commands = initializeCommands();
        
        // Keep user interface working until "quit" command
        for (String input = Terminal.readLine(); !input.equals("quit"); input = Terminal.readLine()) {
            
            // Command to execute if found
            Command finalCommand = null;
            
            // After user input check all commands if they exist
            for (Command cmd : commands) {
                
                if (input.startsWith(cmd.getKeyword() + " ") || input.equals(cmd.getKeyword())) {
                    finalCommand = cmd;
                    break;
                }
            }
            
            // If command was found, execute it with parsed input
            if (finalCommand != null) {
                
                // if count of argument does not match command argument count throw exception
                try {
                    Output output = finalCommand.execute(database, parseInput(finalCommand, input));
                    output.printToTerminal();
                } catch (BadArgumentException | DataNotFoundException e) {
                    Terminal.printError(e.getMessage());
                }
            
            } else {
                Terminal.printError("unavailable command.");
            }
        }
        
        // Ok after quit
        Terminal.printLine("Ok");
        
    }
    
    /**
     * Initialize list of commands
     * 
     * @return list of commands this interface can interpret
     */
    private static List<Command> initializeCommands() {
        
        List<Command> commands = new ArrayList<>();

        // AddSeries before AddConference because AddConference command is prefix of AddSeries command
        // Samme applies to AddKeywordConference and AddKeyword
        commands.addAll(Arrays.asList(new AddAuthor(), new AddJournal(), new AddSeries(), new AddConference(),
                new AddPublicationSeries(), new AddPublicationJournal(), new WrittenBy(), new Cite(),
                new AddKeywordConference(), new AddKeyword(), new AllPublication(), new InvalidPublication(),
                new PublicationsBy(), new InProceedings(), new FindKeywords(), new Jaccard(), new Similarity(),
                new DirectHIndex(), new HIndex(), new CoauthorOf(), new ForeignCitationOf(),
                new DirectPrintConference(), new DirectPrintJournal(), new PrintBibliography()
        ));
        
        return commands;
    }
    
    /**
     * Tailor the input to suit execute() function
     * 
     * @param cmd command that will be be executed
     * @param input raw user input
     * @return array of arguments cut in this way to make it suitable for interpretation 
     * @throws BadArgumentException if amount of arguments do not match with command's expected amount
     */
    private static String[] parseInput(Command cmd, String input) throws BadArgumentException {
        
        // Because "jaccard" lists elements seperated by spaces.
        String corrected = input;
        if (cmd.getKeyword().equals("jaccard")) {
            
            // Pre-emptive measure: check if arguments were given 
            if (!(corrected.split(" ").length > 2))
                throw new BadArgumentException("illegal argument count");
            
            // Only two sets allowed
            if (corrected.split(" ").length != 3)
                throw new BadArgumentException("illegal argument count");
            
            corrected = "jaccard " + corrected.split(" ")[1] + "," + corrected.split(" ")[2];
        }
        
        // Fill empty arguments with meaningful content 
        while (corrected.contains(",,"))
            corrected = corrected.replace(",,", "," + Output.EMPTY_ARGUMENT + ",");

        // Prevents trailing semicola, commata being ignored
        corrected = corrected.endsWith(",") ? corrected + Output.EMPTY_ARGUMENT : corrected;
        corrected = corrected.endsWith(";") ? corrected + Output.EMPTY_ARGUMENT : corrected;
        
        // cut keyword, split arguments, then pass result as arguments    
        corrected = corrected.replace(cmd.getKeyword() + " ", "");
        
        String[] arguments = corrected.split(",");
        
        // For commands without arguments (arguments contains the command itself) treat as empty 
        if (arguments.length == 1 && arguments[0].equals(cmd.getKeyword()))
            arguments = new String[0];
        
        // Wrong argument count
        if (arguments.length != cmd.getArgumentLength())
            throw new BadArgumentException("illegal argument count");
        
        return arguments;
    }

}
