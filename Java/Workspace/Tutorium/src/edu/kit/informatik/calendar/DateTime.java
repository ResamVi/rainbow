package edu.kit.informatik.calendar;

/**
 * Represents a specific point in time, represented by a {@linkplain Time} at a
 * {@linkplain Date}.
 *
 * @author Tobias Bachert
 * @author Joshua Gleitze
 * @version 2.0
 */
public final class DateTime {

	/**
	 * The date. Is not {@code null}.
	 */
	private final Date date;
	/**
	 * The time. Is not {@code null}.
	 */
	private final Time time;

	/**
	 * Creates a new point in time out of the provided {@link Time} and
	 * {@link Date}.
	 *
	 * @param date
	 *            The point’s date. Must not be {@code null}.
	 * @param time
	 *            The point’s time. Must not be {@code null}.
	 */
	public DateTime(final Date date, final Time time) {
		this.date = date;
		this.time = time;
	}

	/**
	 * Adds the provided {@link DateTime} on this one. Behaves as if calling
	 * this method was equivalent to calling
	 * {@code this.plus(datetime.time).plus(datetime.date)}.
	 *
	 * @param datetime
	 *            The {@link DateTime} to add. Must not be {@code null}.
	 * @return A new instance representing the sum of both datetimes.
	 * @see {@link #plus(Date)}
	 * @see {@link #plus(Time)}
	 */
	public DateTime plus(final DateTime datetime) {
		return this.plus(datetime.time).plus(datetime.date);
	}

	/**
	 * Subtracts the provided {@link DateTime} from this one. Behaves as if
	 * calling this method was equivalent to calling
	 * {@code this.minus(datetime.time).minus(datetime.date)}.
	 *
	 * @param datetime
	 *            The {@link DateTime} to subtract. Must not be {@code null}.
	 * @return A new instance representing the difference of both datetimes.
	 * @see {@link #minus(Date)}
	 * @see {@link #minus(Time)}
	 */
	public DateTime minus(final DateTime datetime) {
		return this.minus(datetime.time).minus(datetime.date);
	}

	/**
	 * Adds the provided {@link Date} on this point in time’s date. Behaves like
	 * {@link Date#plus}.
	 *
	 * @param date
	 *            The date to add. Must not be {@code null}.
	 * @return A new instance representing the point in time consisting of this
	 *         date plus {@code date} and this time.
	 * @see {@link Date#plus(Date)}
	 */
	public DateTime plus(final Date date) {
		return new DateTime(this.date.plus(date), this.time);
	}

	/**
	 * Subtracts the provided {@link Date} from this point in time’s date.
	 * Behaves like {@link Date#minus}.
	 *
	 * @param date
	 *            The date to subtract. Must not be {@code null}.
	 * @return A new instance representing the point in time consisting of this
	 *         date minus {@code date} and this time.
	 * @see {@link Date#minus(Date)}
	 */
	public DateTime minus(final Date Date) {
		return new DateTime(this.date.minus(Date), this.time);
	}

	/**
	 * Adds the provided {@link Time} on this point in time’s time. Behaves like
	 * {@link Time#plus}, but adds the carry to this point’s date if the
	 * addition overflows.
	 *
	 * @param date
	 *            The time to add. Must not be {@code null}.
	 * @return A new instance representing the point in time consisting of this
	 *         date plus plus possible carry and and this time plus
	 *         {@code time}.
	 * @see {@link Time#plus(Time)}
	 */
	public DateTime plus(final Time time) {
		final Time addedTime = this.time.plus(time);
		final Date carryDate = addedTime.isBefore(time) ? this.date.plusDays(1) : this.date;
		return new DateTime(carryDate, addedTime);
	}

	/**
	 * Subtracts the provided {@link Time} from this point in time’s time.
	 * Behaves like {@link Time#minus}, but subtracts the carry from this
	 * point’s date if the subtraction overflows.
	 *
	 * @param time
	 *            The time to subtract. Must not be {@code null}.
	 * @return A new instance representing the point in time consisting of this
	 *         date plus plus possible carry and and this time plus
	 *         {@code time}.
	 * @see {@link Time#minus(Time)}
	 */
	public DateTime minus(final Time time) {
		final Time subtractedTime = this.time.minus(time);
		final Date carryDate = subtractedTime.isAfter(this.time) ? this.date.minusDays(1) : this.date;
		return new DateTime(carryDate, subtractedTime);
	}

	/**
	 * Adds the provided number of years to this point in time’s date. Behaves
	 * like {@link Date#plusYears}.
	 *
	 * @param years
	 *            The years to add. Any integer such that the resulting date is
	 *            at or after the 01-01-0000.
	 * @return A new instance representing the point in time consisting of this
	 *         date plus the provided {@code years} and this time.
	 * @see {@link Date#plusYears(int)}
	 */
	public DateTime plusYears(final int years) {
		return new DateTime(this.date.plusYears(years), this.time);
	}

	/**
	 * Subtracts the provided number of years from this point in time’s date.
	 * Behaves like {@link Date#minusYears}.
	 *
	 * @param years
	 *            The years to subtract. Any integer such that the resulting
	 *            date is at or after the 01-01-0000.
	 * @return A new instance representing the point in time consisting of this
	 *         date minus the provided {@code years} and this time.
	 * @see {@link Date#minusYears(int)}
	 */
	public DateTime minusYears(final int years) {
		return new DateTime(this.date.minusYears(years), this.time);
	}

	/**
	 * Adds the provided number of months to this point in time’s date. Behaves
	 * like {@link Date#plusMonths}.
	 *
	 * @param months
	 *            The months to add. Any integer such that the resulting date is
	 *            at or after the 01-01-0000.
	 * @return A new instance representing the point in time consisting of this
	 *         date plus the provided {@code months} and this time.
	 * @see {@link Date#plusMonths(int)}
	 */
	public DateTime plusMonths(final int months) {
		return new DateTime(this.date.plusMonths(months), this.time);
	}

	/**
	 * Subtracts the provided number of months to this point in time’s date.
	 * Behaves like {@link Date#minusMonths}.
	 *
	 * @param months
	 *            The months to subtract. Any integer such that the resulting
	 *            date is at or after the 01-01-0000.
	 * @return A new instance representing the point in time consisting of this
	 *         date minus the provided {@code months} and this time.
	 * @see {@link Date#minusMonths(int)}
	 */
	public DateTime minusMonths(final int months) {
		return new DateTime(this.date.minusMonths(months), this.time);
	}

	/**
	 * Adds the provided number of days to this point in time’s date. Behaves
	 * like {@link Date#plusDays}.
	 *
	 * @param days
	 *            The days to add. Any integer such that the resulting date is
	 *            at or after the 01-01-0000.
	 * @return A new instance representing the point in time consisting of this
	 *         date plus the provided {@code days} and this time.
	 * @see {@link Date#plusDays(int)}
	 */
	public DateTime plusDays(final int days) {
		return new DateTime(this.date.plusDays(days), this.time);
	}

	/**
	 * Subtracts the provided number of years from this point in time’s date.
	 * Behaves like {@link Date#minusDays}.
	 *
	 * @param days
	 *            The days to subtract. Any integer such that the resulting date
	 *            is at or after the 01-01-0000.
	 * @return A new instance representing the point in time consisting of this
	 *         date minus the provided {@code days} and this time.
	 * @see {@link Date#minusDays(int)}
	 */
	public DateTime minusDays(final int days) {
		return new DateTime(this.date.minusDays(days), this.time);
	}

	/**
	 * Queries whether this is an earlier point in time than another datetime.
	 *
	 * @param other
	 *            A datetime to check. Must not be {@code null}.
	 * @return {@code true} iff this point in time comes before {@code other}.
	 */
	public boolean isBefore(final DateTime other) {
		return this.date.isBefore(other.date) || (this.date.isEqual(other.date) && this.time.isBefore(other.time));
	}

	/**
	 * Queries whether this represents the same point in time as another
	 * datetime.
	 *
	 * @param other
	 *            A date to check. Must not be {@code null}.
	 * @return {@code true} iff this datetime represents the same date at the
	 *         same time as {@code other}.
	 */
	public boolean isEqual(final DateTime other) {
		return this.date.isEqual(other.date) && this.time.isEqual(other.time);
	}

	/**
	 * Queries whether this is a later point in time than another datetime.
	 *
	 * @param other
	 *            A datetime to check. Must not be {@code null}.
	 * @return {@code true} iff this point in time comes after {@code other}.
	 */
	public boolean isAfter(final DateTime other) {
		return other.isBefore(this);
	}

	@Override
	public String toString() {
		return String.format("%sT%s", this.date, this.time);
	}

	/**
	 * Queries this point in time’s date.
	 *
	 * @return This point’s date. Is not {@code null}.
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Queries this point in time’s time.
	 *
	 * @return This point’s time. Is not {@code null}.
	 */
	public Time getTime() {
		return this.time;
	}

	/**
	 * Queries this date’s year.
	 *
	 * @see {@link Date#getYear()}
	 */
	public int getYear() {
		return this.date.getYear();
	}

	/**
	 * Queries this date’s month integer value.
	 *
	 * @see {@link Date#getMonthValue()}
	 */
	public int getMonthValue() {
		return this.date.getMonthValue();
	}

	/**
	 * Queries this date’s month.
	 *
	 * @see {@link Date#getMonth()}
	 */
	public Month getMonth() {
		return this.date.getMonth();
	}

	/**
	 * Queries this date’s day index.
	 *
	 * @see {@link Date#getDayOfYear()}
	 */

	public int getDayOfYear() {
		return this.date.getDayOfYear();
	}

	/**
	 * Queries this date’s day integer value.
	 *
	 * @see {@link Date#getDayOfMonth()}
	 */
	public int getDayOfMonth() {
		return this.date.getDayOfMonth();
	}

	/**
	 * Queries this date’s {@link DayOfWeek}.
	 *
	 * @see {@link Date#getDayOfWeek()}
	 */
	public DayOfWeek getDayOfWeek() {
		return this.date.getDayOfWeek();
	}

	/**
	 * Queries this time’s hour.
	 *
	 * @see {@link DateTime#getHour()}
	 */
	public int getHour() {
		return this.time.getHour();
	}

	/**
	 * Queries this time’s minute.
	 *
	 * @see {@link Time#getMinute()}
	 */
	public int getMinute() {
		return this.time.getMinute();
	}

	/**
	 * Queries this time’s second.
	 *
	 * @see {@link Time#getSecond()}
	 */
	public int getSecond() {
		return this.time.getSecond();
	}
}
