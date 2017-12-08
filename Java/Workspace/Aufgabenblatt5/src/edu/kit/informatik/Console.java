package edu.kit.informatik;

import edu.kit.informatik.commands.AddCommand;
import edu.kit.informatik.commands.Command;
import edu.kit.informatik.commands.EntryType;
import edu.kit.informatik.commands.IdleCommand;
import edu.kit.informatik.commands.ListCommand;
import edu.kit.informatik.commands.MarkingCommand;
import edu.kit.informatik.commands.SummaryCommand;
import edu.kit.informatik.helper.Database;

public class Console {

    /**
     * Start of program
     * 
     * @param args command arguments
     */
    public static void main(String[] args) {

        // Initialize database
        Database database = new Database();

        boolean isRunning = true;

        // Loops until "quit"
        while (isRunning) {

            // Read
            String line = Terminal.readLine();

            // Extract data of input
            String[] parameters = parseInput(line);
            String process = parameters[0];
            String type = parameters[1];
            String arguments = parameters[2];

            // This command will execute depending of the input
            Command command = new IdleCommand();

            // Find the correct command to execute
            switch (process) {

            case "add":
                command = new AddCommand(EntryType.valueOf(type));
                break;

            case "list":
                command = new ListCommand(EntryType.valueOf(type));
                break;

            case "summary":
                command = new SummaryCommand(EntryType.valueOf(type));
                break;

            case "examination":
                command = new MarkingCommand();
                break;

            case "reset":
                database = new Database();
                Terminal.printLine("Ok");
                break;

            case "quit":
                isRunning = false;
                break;

            default:
                command = null; // TODO: Handle Error
                break;

            }

            // Execute
            try {
                command.execute(database, arguments.split(";"));
            } catch (IllegalArgumentException e) {
                Terminal.printError("should contain any lower-case letters");
            }
        }
    }

    /**
     * Interprets the raw input of the user into their meaning 
     * 
     * @param line raw input of user
     * @return array of three element with <br />
     * [0]: process - eg. "add", "list", "reset" <br /> 
     * [1]: type - eg. "professor", "student", "" if not available <br />
     * [2]: parameter - eg. "max;mustermann;abc" or null if not available
     */
    private static String[] parseInput(String line) {

        // TODO: catch exceptions
        // TODO: check for trailing/leading spaces

        // Split input into command-keyword and given parameters (if available)
        String keyword = line.split(" ")[0];
        String parameter = line.contains(" ") ? line.split(" ")[1] : "";

        // Split keyword into process and type (only if apllicable, not like
        // "reset"/"quit")
        String process = keyword.split("-")[0];
        String type = keyword.contains("-") ? keyword.split("-")[1].toUpperCase() : " ";

        return new String[] {process, type, parameter };
    }
    
/* TESTS ------------------------------------------------------
add-professor andreas;kappel;sww
add-professor angelika;huffman;itc
add-professor dominik;saller;xxc
add-professor maximiliane;frei;hgs
add-professor robert;amsel;ifd
add-professor roman;forst;ddf

summary-professor roman;forst;ddf

add-student ada;smith;101215
add-student grace;smith;912906
add-student jean;doe;230328
add-student frances;nguyen;543210

summary-student ada;smith;101215

add-module algorithmentechnik
add-module parallelverarbeitung
add-module betriebssysteme

summary-module 3

add-lecture betriebssysteme;3;felix;martell;ittg;3
add-lecture betriebssysteme;3;francis;bruhn;igf;5
add-lecture graphenalgorithmen;3;joerg;hettel;idi;3

summary-lecture 3

examination-marking 3;230328;5.00
examination-marking 3;101215;4.50
examination-marking 3;230328;4.05
----------------------------------------------------------------
add-professor andreas;kappel;sww
add-professor angelika;huffman;itc
add-professor dominik;saller;xxc
add-professor maximiliane;frei;hgs
add-professor robert;amsel;ifd
add-professor roman;forst;ddf
add-professor joerg;hettel;idi;
add-student ada;smith;101215
add-student grace;smith;912906
add-student jean;doe;230328
add-student frances;nguyen;543210
add-module algorithmentechnik
add-module parallelverarbeitung
add-module betriebssysteme
add-lecture betriebssysteme;3;robert;amsel;ifd;3
add-lecture betriebssysteme;3;andreas;kappel;sww;5
add-lecture graphenalgorithmen;3;joerg;hettel;idi;3
-----------------------------------------------------------------*/

/*-------------------------------------------------------------
add-professor dominik;saller;xxc
add-professor robert;amsel;ifd
add-professor maximiliane;frei;hgs
add-professor andreas;kappel;sww
add-professor angelika;huffman;itc
add-professor angelika;huffman;abg
add-professor roman;forst;ddf;
add-student thorsten;doernbach;123455
add-student micha;hanselmann;197882
add-student max;maxtermann;817292
add-student der;fabi;201526
add-student jan;lang;127844
add-module algos
add-module betriebssys
add-module softwaretech
add-lecture proggen;3;roman;forst;ddf;9
add-lecture irgendwas;2;robert;amsel;ifd;5
add-lecture zocken;3;roman;forst;ddf;9
add-lecture undnochmalproggen;3;roman;forst;ddf;9
add-lecture einevl;3;roman;forst;ddf;9
add-lecture letztevlimmodul;3;maximiliane;frei;hgs;8
add-lecture genaumaxetcs;3;roman;forst;ddf;1
examination-marking 4;197882;1.105
examination-marking 5;197882;3.45
examination-marking 4;201526;2.00
examination-marking 6;127844;2.5
examination-marking 4;127844;5.0
examination-marking 6;817292;2.595
*/

// TODO: Error, three semicolons

}
