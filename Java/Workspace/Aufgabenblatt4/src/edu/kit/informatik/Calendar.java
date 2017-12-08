package edu.kit.informatik;

import edu.kit.informatik.Terminal;

/**
 * Stores Appointments and applies commands to the set
 * 
 * @author Julien Midedji
 *
 */
public class Calendar {

    LinkedSortedAppendList<Appointment> list;

    public Calendar() {
        list = new LinkedSortedAppendList<>();
    }

    /**
     * Adds entry to Calendar
     * 
     * <p>
     * The input argument has to match the format: <blockquote>
     * 
     * <pre>
     * &lt;from&gt; &lt;to&gt; &lt;name&gt;
     * </pre>
     * 
     * </blockquote>
     * 
     * @param input
     *            the entry
     */
    public void add(Appointment input) {
        list.addSorted(input);
    }

    /**
     * Prints all entries to console
     */
    public void printAll() {

        SortedIterator<Appointment> iterator = list.iterator();

        while (iterator.hasNext())
            Terminal.printLine(iterator.next());
    }

    /**
     * Prints all entries before the input
     * 
     * @param input An Appointment entry string
     */
    public void printBefore(DateTime input) {

        SortedIterator<Appointment> iterator = list.iterator();

        while (iterator.hasNext()) {
            Appointment entry = iterator.next();

            if (entry.getFrom().isBefore(input))
                Terminal.printLine(entry);
        }
    }

    /**
     * Prints all entries on specified date
     * 
     * @param input
     *            Entries at date
     */
    public void printOn(Date input) {

        SortedIterator<Appointment> iterator = list.iterator();

        while (iterator.hasNext()) {
            Appointment entry = iterator.next();

            // DateTime objects with same date but at 00:00:00 o'clock and
            // 23:59:59
            DateTime startOfDay = new DateTime(input, new Time(0, 0, 0));
            DateTime endOfDay = startOfDay.plus(new Time(23, 59, 59));

            // Start of entry is after startOfDay and end of entry is before
            // endOfDay
            if (entry.getFrom().isAfter(startOfDay) && entry.getTo().isBefore(endOfDay))
                Terminal.printLine(entry);
        }

    }

    /**
     * 
     * Prints all entries between the specified start and end date
     * 
     * @param start left margin
     * @param end right margin
     */
    public void printBetween(DateTime start, DateTime end) {

        SortedIterator<Appointment> iterator = list.iterator();

        while (iterator.hasNext()) {
            Appointment entry = iterator.next();

            // Start of entry is after start and
            // end of entry is before end
            if (entry.getFrom().isAfter(start) && entry.getTo().isBefore(end))
                Terminal.printLine(entry);
        }

    }

    /**
     * Print conflicting Appointments
     * 
     * Appoints conflict if their respective ranges overlap
     * 
     * @param input name of Appointment
     */
    public void printConflicting(String input) {

        Appointment conflict = getEntry(input);

        if (conflict == null)
            Terminal.printLine("Appointment not found.");
        else {

            SortedIterator<Appointment> iterator = list.iterator();

            while (iterator.hasNext()) {
                Appointment entry = iterator.next();

                // entry's end is between margin
                if (entry.getTo().isAfter(conflict.getFrom()) && entry.getTo().isBefore(conflict.getTo()))
                    Terminal.printLine(entry);

                // entry's start is between the margin
                else if (entry.getFrom().isAfter(conflict.getFrom()) && entry.getFrom().isBefore(conflict.getTo()))
                    Terminal.printLine(entry);

            }
        }
    }

    /**
     * Find the Appointment with the inputted name
     * 
     * @param string name of Appointment
     * @return Appointment with the name, or null if none found
     */
    private Appointment getEntry(String string) {

        SortedIterator<Appointment> iterator = list.iterator();

        while (iterator.hasNext()) {
            Appointment entry = iterator.next();

            if (entry.getName().equals(string))
                return entry;
        }

        return null;
    }

}