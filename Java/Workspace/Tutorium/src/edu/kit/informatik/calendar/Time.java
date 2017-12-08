package edu.kit.informatik.calendar;

/**
 * A specific time of a day in seconds, minutes and hours.
 *
 * @author Tobias Bachert
 * @author Joshua Gleitze
 * @version 2.0
 */
public final class Time {

	/**
	 * The number of seconds in a minute.
	 */
	private static final int SECONDS_IN_MINUTE = 60;
	/**
	 * The number of minutes in an hour.
	 */
	private static final int MINUTES_IN_HOUR = 60;
	/**
	 * The number of seconds in an hour.
	 */
	private static final int SECONDS_IN_HOUR = Time.SECONDS_IN_MINUTE * Time.MINUTES_IN_HOUR;
	/**
	 * The number of hours in a day.
	 */
	private static final int HOURS_IN_DAY = 24;
	/**
	 * The number of seconds in a day.
	 */
	private static final int SECONDS_IN_DAY = Time.SECONDS_IN_HOUR * Time.HOURS_IN_DAY;

	/**
	 * This time’s hour. 1 ≤ {@link #hour} ≤ 23.
	 */
	private final int hour;
	/**
	 * This time’s minute. 1 ≤ {@link #minute} ≤ 59.
	 */
	private final int minute;
	/**
	 * This time’s second. 1 ≤ {@link #second} ≤ 59.
	 */
	private final int second;

	/**
	 * Creates a new Time out of the provided hour, minute and second.
	 *
	 * @param hour
	 *            The time’s hour. Must satisfy 1 ≤ {@code hour} ≤ 23.
	 * @param minute
	 *            The time’s minute. Must satisfy 1 ≤ {@code hour} ≤ 59.
	 * @param second
	 *            The time’s secon. Must satisfy 1 ≤ {@code hour} ≤ 59.
	 */
	public Time(final int hour, final int minute, final int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	/**
	 * Creates a new time out of the provided sum of seconds.
	 *
	 * @param seconds
	 *            The sum of the time’s seconds. Must satisfy 0 ≤
	 *            {@code seconds} < {@link #SECONDS_IN_DAY}.
	 */
	private Time(final int seconds) {
		this(seconds / Time.SECONDS_IN_HOUR, (seconds / Time.SECONDS_IN_MINUTE) % Time.MINUTES_IN_HOUR,
				seconds % Time.SECONDS_IN_MINUTE);
	}

	/**
	 * Create a {@link DateTime} at the provided date and this time.
	 *
	 * @param date
	 *            The date for the {@link DateTime}. Must not be {@code null}.
	 * @return A new {@link DateTime}, representing this time at the provided
	 *         {@code date}.
	 */
	public DateTime atDate(final Date date) {
		return new DateTime(date, this);
	}

	/**
	 * Adds the provided time on this one.
	 *
	 * @param time
	 *            A time to add on this. Must not be {@code null}.
	 * @return A new instance, representing this time plus the provided
	 *         {@code time}. If the results hours exceed 23, the result
	 *         overflows, starting again at 00:00:00.
	 */
	public Time plus(final Time time) {
		return new Time(Math.floorMod(this.inSeconds() + time.inSeconds(), Time.SECONDS_IN_DAY));
	}

	/**
	 * Subtracts the provided time from this one.
	 *
	 * @param time
	 *            A time to subtract from this. Must not be {@code null}.
	 * @return A new instance, representing this time minus the provided
	 *         {@code time}. If the results hours get below 0, the result
	 *         overflows, starting again at 23:59:59.
	 */
	public Time minus(final Time time) {
		return new Time(Math.floorMod(this.inSeconds() - time.inSeconds(), Time.SECONDS_IN_DAY));
	}

	/**
	 * Queries this time’s hour.
	 *
	 * @return This time’s hour. Satisfies 1 ≤ {@code this.getHour()} ≤ 23.
	 */
	public int getHour() {
		return this.hour;
	}

	/**
	 * Queries this time’s minute.
	 *
	 * @return This time’s minute. Satisfies 1 ≤ {@code this.getMinute()} ≤ 59.
	 */
	public int getMinute() {
		return this.minute;
	}

	/**
	 * Queries this time’s second.
	 *
	 * @return This time’s second. Satisfies 1 ≤ {@code this.getSecond()} ≤ 59.
	 */
	public int getSecond() {
		return this.second;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", this.hour, this.minute, this.second);
	}

	/**
	 * Converts this time into its sum of seconds.
	 *
	 * @return The sum of seconds in this time.
	 */
	private int inSeconds() {
		return this.hour * Time.SECONDS_IN_HOUR + this.minute * Time.SECONDS_IN_MINUTE + this.second;
	}

	/**
	 * Queries whether this time is an earlier point in time than another time.
	 *
	 * @param other
	 *            The time to check. Must not be {@code null}.
	 * @return {@code true} iff this time comes before {@code other}.
	 */
	public boolean isBefore(final Time other) {
		return this.inSeconds() < other.inSeconds();
	}

	/**
	 * Queries whether this is the same time as another time.
	 *
	 * @param other
	 *            The time to check. Must not be {@code null}.
	 * @return {@code true} iff this time represents the same second in the same
	 *         minute in the same hour as {@code other}.
	 */
	public boolean isEqual(final Time other) {
		return this.hour == other.hour && this.minute == other.minute && this.second == other.second;
	}

	/**
	 * Queries whether this time is a later point in time than another time.
	 *
	 * @param other
	 *            The time to check. Must not be {@code null}.
	 * @return {@code true} iff this time comes after {@code other}.
	 */
	public boolean isAfter(final Time other) {
		return other.isBefore(this);
	}
}
