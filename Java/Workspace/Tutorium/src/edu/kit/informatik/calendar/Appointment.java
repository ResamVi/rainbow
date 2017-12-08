package edu.kit.informatik.calendar;

/**
 * An appointment, having a name, a start and an end {@link DateTime}.
 *
 * @author Joshua Gleitze
 * @version 1.0
 */
public class Appointment {

	/**
	 * The start point. Is not {@code null}.
	 */
	private final DateTime start;
	/**
	 * The end point. Is not {@code null}.
	 */
	private DateTime end;
	/**
	 * The name. Is not {@code null}.
	 */
	private String name;

	/**
	 * Creates a new appointment with the provided name and start and end
	 * {@link DateTime}.
	 *
	 * @param name
	 *            The appointment’s name. Must not be {@code null}.
	 * @param from
	 *            The appointment’s start {@link DateTime}. Must not be
	 *            {@code null}.
	 * @param to
	 *            The appointment’s end {@link DateTime}. Must not be
	 *            {@code null}.
	 */
	public Appointment(final String name, final DateTime from, final DateTime to) {
		this.name = name;
		this.start = from;
		this.end = to;
	}

	/**
	 * Creates a new appointment with the provided name start {@link DateTime}
	 * and duration.
	 *
	 * @param name
	 *            The appointment’s name. Must not be {@code null}.
	 * @param from
	 *            The appointment’s start {@link DateTime}. Must not be
	 *            {@code null}.
	 * @param duration
	 *            The appointment’s duration. Must not be {@code null}.
	 */
	public Appointment(final String name, final DateTime from, final Time duration) {
		this(name, from, from.plus(duration));
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", this.name, this.start, this.end);
	}

	/**
	 * Queries this appointment’s start.
	 *
	 * @return The point in time this appointment starts at. Is not
	 *         {@code null}.
	 */
	public DateTime getFrom() {
		return this.start;
	}

	/**
	 * Queries this appointment’s end.
	 *
	 * @return The point in time this appointment ends at. Is not {@code null}.
	 */
	public DateTime getTo() {
		return this.end;
	}

	/**
	 * Redefines this appointment’s end.
	 *
	 * @return The point in time this appointment ends at. Must not be
	 *         {@code null}.
	 */
	public void setTo(final DateTime to) {
		this.end = to;
	}

	/**
	 * Queries this appointment’s name.
	 *
	 * @return This appointment’s name. Is not {@code null}.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets this appointment’s name.
	 *
	 * @param name
	 *            This appointment’s name. Must not be {@code null}.
	 */
	public void setName(final String name) {
		this.name = name;
	}
}
