
package edu.kit.informatik;

/**
 * Represents an appointment with a specific name.
 * 
 * @author Julien Midedji
 * @version 1.0
 */
public final class Appointment implements Comparable<Appointment> {

    private String name;
    private final DateTime from;
    private DateTime to;

    /**
     * Constructs an {@code Appointment} with the specified arguments.
     * 
     * @param name
     *            the name of the appointment
     * @param from
     *            the start time
     * @param to
     *            the end time
     */
    public Appointment(final String name, final DateTime from, final DateTime to) {
        ////
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an {@code Appointment} with the specified arguments.
     * 
     * @param name
     *            the name of the appointment
     * @param from
     *            the start time
     * @param duration
     *            the duration
     */
    public Appointment(final String name, final DateTime from, final Time duration) {
        ////
        this(name, from, from.plus(duration));
    }

    @Override
    public boolean equals(final Object obj) {
        ////
        if (obj instanceof Appointment) {
            final Appointment other = (Appointment) obj;
            if (name.equals(other.name) && from.equals(other.from) && to.equals(other.to)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        ////
        return ((31 + name.hashCode()) * 31 + from.hashCode()) * 31 + to.hashCode();
    }

    /**
     * Returns a string representation of this appointment.
     * 
     * <p>
     * The returned string has the format <blockquote>
     * 
     * <pre>
     * (name) (from) (to)
     * </pre>
     * 
     * </blockquote>
     */
    @Override
    public String toString() {
        ////
        return appendTo(new StringBuilder(40 + name.length())).toString();
    }

    /**
     * Appends the string representation of this to the specified string
     * builder.
     * 
     * <p>
     * The string representation is appended as per invoking <blockquote>
     * 
     * <pre>
     * sb.append({@linkplain #toString()});
     * </pre>
     * 
     * </blockquote>
     * 
     * @param sb
     *            the string builder to append to
     * @return a reference to {@code sb}
     */
    /* pkg */ StringBuilder appendTo(final StringBuilder sb) {
        ////
        sb.append(name).append(' ');
        from.appendTo(sb).append(' ');
        to.appendTo(sb);
        return sb;
    }

    // ==============================================

    /**
     * Returns the name of this.
     * 
     * @return the name
     */
    public String getName() {
        ////
        return name;
    }

    /**
     * Returns the start time of this.
     * 
     * @return the start time
     */
    public DateTime getFrom() {
        ////
        return from;
    }

    /**
     * Returns the end time of this.
     * 
     * @return the end time
     */
    public DateTime getTo() {
        ////
        return to;
    }

    /**
     * Sets the name of this appointment to the specified name.
     * 
     * @param name
     *            the name
     */
    public void setName(final String name) {
        ////
        this.name = name;
    }

    /**
     * Sets the end time of this appointment to the specified datetime.
     * 
     * @param to
     *            the end time
     */
    public void setTo(final DateTime to) {
        ////
        this.to = to;
    }

    /**
     * {@inheritDoc} 
     * <h3> An Appointment is before another when: <br />
     *  - the start date is before its comparison start date <br />
     *  - if above statement does not apply when
     *   the end date is before its comparison's end date <br />
     *  - if the above statements do not apply order the names lexicographically
     *  </h3>
     */
    @Override
    public int compareTo(Appointment o) {

        if (!this.from.isEqual(o.from))
            return this.from.isBefore(o.from) ? -1 : 1;

        // Starts are equal so check end dates
        else {
            if (!this.to.isEqual(o.to))
                return this.to.isBefore(o.to) ? -1 : 1;

            // Starts and Ends are equal so check name
            else {

                if (this.name.compareTo(o.name) != 0)
                    return this.name.compareTo(o.name);

                // Everything's the same
                else
                    return 0;
            }
        }
    }
}