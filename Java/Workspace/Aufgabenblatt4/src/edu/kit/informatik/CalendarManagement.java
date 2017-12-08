package edu.kit.informatik;

/**
 * Reads, interprets and executes calendar commands
 * 
 * @author Julien Midedji
 *
 */
public class CalendarManagement {

    private static Calendar calendar;

    public static void main(String[] args) {

        calendar = new Calendar();

        // TODO: Formatting method that splits input correctly

        boolean isFinished = false;

        while (!isFinished) {
            String input[] = readInput();

            switch (input[0]) {

            // "add appointment <appointment>"
            case "add":
                String entry = String.format("%s %s %s", input[2], input[3], input[4]);
                calendar.add(DateUtil.parseAppointment(entry));
                break;

            case "print":

                // "print appointments"
                if (input.length == 2)
                    calendar.printAll();

                // "print appointments that start before <pointOfTime>"
                else if (input[3].equals("start"))
                    calendar.printBefore(DateUtil.parseDateTime(input[5]));

                // "print appointments on <date>"
                else if (input[2].equals("on"))
                    calendar.printOn(DateUtil.parseDate(input[3]));

                // "print appointments in interval <startDate> <endDate>"
                else if (input[2].equals("in"))
                    calendar.printBetween(DateUtil.parseDateTime(input[4]), DateUtil.parseDateTime(input[5]));

                // "print appointments that conflict with <appointmentTitle>"
                else if (input[3].equals("conflict"))

                    calendar.printConflicting(input[5]);
                else
                    System.out.println("Error in my code");

                break;

            // "quit"
            case "quit":
                isFinished = true;
                break;

            default:
                Terminal.printLine("Unknown command");
                break;

            }
        }
    }

    /**
     * Reads input and formats to a suitable String array
     * 
     * @return An array of arguments
     */
    private static String[] readInput() {

        // Splitting with spaces results in the name-argument being split as
        // well
        String[] input = Terminal.readLine().split(" ");
        String name = "";

        
        
        // If input might have a name argument, concatenate the name to one word
        // "add appointment <DateTime> <DateTime> <appointmentTitle>
        if (input.length > 2 && isDateTime(input[2]) && isDateTime(input[3])) {

            // Concatenate String
            for (int i = 4; i < input.length; i++)
                name += input[i];

            input = new String[] {input[0], input[1], input[2], input[3], name };

        // "print appointments that conflict with <appointmentTitle>"
        } else if (input.length > 2 && input[3].equals("conflict")) {

            // Concatenate String
            for (int i = 5; i < input.length; i++)
                name += input[i];

            input = new String[] {input[0], input[1], input[2], input[3], input[4], name };

        }

        return input;
    }

    /**
     * Returns whether input is a valid DateTime String
     * 
     * @param input
     *            to test
     * @return true if it has the format <br />
     *         "DD-DD-DDDDTDD:DD:DD" (D: A digit)
     */
    private static boolean isDateTime(String input) {
        return input.matches("\\d{2}-\\d{2}-\\d{4}T\\d{2}:\\d{2}:\\d{2}");
    }
}
