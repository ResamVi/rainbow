package edu.kit.informatik.calendar;

/**
 * A date consisting of a year, month and day.
 *
 * @author Tobias Bachert
 * @author Joshua Gleitze
 * @version 2.0
 */
public class Date {

	/**
	 * The number of months in a year.
	 */
	private static int MONTHS_IN_YEAR = 12;

	/**
	 * This date’s year. Is ≥ 0.
	 */
	private final int year;

	/**
	 * This date’s month. 1 ≤ {@link #month} ≤ 12.
	 */
	// „private final Month month“ would be handier, but is forbidden by the
	// task.
	private final int month;
	/**
	 * This date’s day. 1 ≤ {@link #day} ≤
	 * {@code Month.ofIndex(this.month).getLength(year)}.
	 */
	private final int day;

	/**
	 * Queries whether the provided year is a leap year.
	 *
	 * @param year
	 *            A year to check. An integer ≥ 0.
	 * @return {@code true} iff the February in {@code year} has 29 days.
	 */
	public static boolean isLeapYear(final int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}

	/**
	 * Creates a valid date out of the arguments, which may represent an invalid
	 * date. Correction is done such that the resulting, valid date, is the same
	 * number of days in the future of 0000-01-01 as the (virtual, invalid) date
	 * formed by the arguments is.
	 * 
	 * @param yearl
	 *            The invalid date’s year. Must be ≥ 0.
	 * @param month
	 *            Then invalid date’s month.
	 * @param dayOfMonth
	 *            The invalid date’s day.
	 * @return A new valid date, that’s the same number of days in the future of
	 *         0000-01-01 as the date formed by the arguments.
	 */
	private static Date correctedDate(final int year, final int month, final int dayOfMonth) {
		int correctedYear = year;
		int correctedMonth = month;
		int correctedDay = dayOfMonth;
		boolean wasCorrected;
		do {
			wasCorrected = false;
			correctedYear += Math.floorDiv(correctedMonth - 1, Date.MONTHS_IN_YEAR);
			// shift to 0-based index, take modulo, shift back to 1-based index
			correctedMonth = Math.floorMod(correctedMonth - 1, Date.MONTHS_IN_YEAR) + 1;
			final int correctedMonthLength = Month.ofIndex(correctedMonth).getLength(correctedYear);
			if (correctedDay > correctedMonthLength) {
				correctedDay -= correctedMonthLength;
				correctedMonth++;
				wasCorrected = true;
			} else if (correctedDay <= 0) {
				correctedDay += Month.ofIndex(--correctedMonth).getLength(correctedYear);
				wasCorrected = true;
			}
		} while (wasCorrected);
		return new Date(correctedYear, correctedMonth, correctedDay);
	}

	/**
	 * Creates a new date out of the provided year, month and day.
	 *
	 * @param year
	 *            The date’s year. Must be ≥ 0.
	 * @param month
	 *            The date’s month. Must satisfy 1 ≤ {@code month} ≤ 12.
	 * @param dayOfMonth
	 *            The date’s day. Must satisfy 1 ≤ {@code day} ≤
	 *            {@code Month.ofIndex(month).getLength(year)}.
	 */
	public Date(final int year, final int month, final int dayOfMonth) {
		this.year = year;
		this.month = month;
		this.day = dayOfMonth;
	}

	/**
	 * Create a {@link DateTime} at this date and the provided time.
	 *
	 * @param time
	 *            The time for the {@link DateTime}. Must not be {@code null}.
	 * @return A new {@link DateTime}, representing the provided {@code time} at
	 *         this date.
	 */
	public DateTime atTime(final Time time) {
		return new DateTime(this, time);
	}

	/**
	 * Queries the {@link DayOfWeek} of this date.
	 *
	 * @return The day
	 */
	public DayOfWeek getDayOfWeek() {
		final int dayOfWeekIndex = (this.inDaysSinceBeginning() + 6) % 7;
		return DayOfWeek.ofIndex(dayOfWeekIndex == 0 ? 7 : dayOfWeekIndex);
	}

	/**
	 * Queries the number of days passed in this date’s year, including the
	 * current day.
	 *
	 * @return The number of days passed in the current year.
	 */
	public int getDayOfYear() {
		int previousDays = 0;
		for (int previousMonth = 1; previousMonth < this.month; previousMonth++) {
			previousDays += Month.ofIndex(previousMonth).getLength(this.year);
		}
		return previousDays + this.day;
	}

	/**
	 * Queries whether this date’s year is a leap year.
	 *
	 * @return {@code true} iff the February in this date’s year has 29 days.
	 */
	public boolean isLeapYear() {
		return Date.isLeapYear(this.year);
	}

	/**
	 * Adds the provided number of years to this date.
	 *
	 * @param years
	 *            The number of years to add. Any integer such that the
	 *            resulting date is at or after the 01-01-0000.
	 * @return A new date instance, representing this date plus the provided
	 *         {@code years}. If the resulting date would not exist, it is
	 *         corrected to the date with the same number of days from
	 *         0000-01-01 as the non-existent date.
	 */
	public Date plusYears(final int years) {
		return Date.correctedDate(this.year + years, this.month, this.day);
	}

	/**
	 * Subtracts the provided number of years from this date.
	 *
	 * @param years
	 *            The number of years to subtract. Any integer such that the
	 *            resulting date is at or after the 01-01-0000.
	 * @return A new date instance, representing this date minus the provided
	 *         {@code years}. If the resulting date would not exist, it is
	 *         corrected to the date with the same number of days from
	 *         0000-01-01 as the non-existent date.
	 */
	public Date minusYears(final int years) {
		return this.plusYears(-years);
	}

	/**
	 * Adds the provided number of months to this date.
	 *
	 * @param months
	 *            The number of months to add. Any integer such that the
	 *            resulting date is at or after the 01-01-0000.
	 * @return A new date instance, representing this date plus the provided
	 *         {@code months}. If the resulting date would not exist, it is
	 *         corrected to the date with the same number of days from
	 *         0000-01-01 as the non-existent date.
	 */
	public Date plusMonths(final int months) {
		return Date.correctedDate(this.year, this.month + months, this.day);
	}

	/**
	 * Subtracts the provided number of months from this date.
	 *
	 * @param months
	 *            The number of months to subtract. Any integer such that the
	 *            resulting date is at or after the 01-01-0000.
	 * @return A new date instance, representing this date minus the provided
	 *         {@code months}. If the resulting date would not exist, it is
	 *         corrected to the date with the same number of days from
	 *         0000-01-01 as the non-existent date.
	 */
	public Date minusMonths(final int months) {
		return this.plusMonths(-months);
	}

	/**
	 * Adds the provided number of days to this date.
	 *
	 * @param days
	 *            The number of days to add. Any integer such that the resulting
	 *            date is at or after the 01-01-0000.
	 * @return A new date instance, representing this date plus the provided
	 *         {@code days}. If the resulting date would not exist, it is
	 *         corrected to the date with the same number of days from
	 *         0000-01-01 as the non-existent date.
	 */
	public Date plusDays(final int days) {
		return Date.correctedDate(this.year, this.month, this.day + days);
	}

	/**
	 * Subtracts the provided number of days from this date.
	 *
	 * @param days
	 *            The number of days to subtract. Any integer such that the
	 *            resulting date is at or after the 01-01-0000.
	 * @return A new date instance, representing this date minus the provided
	 *         {@code days}. If the resulting date would not exist, it is
	 *         corrected to the date with the same number of days from
	 *         0000-01-01 as the non-existent date.
	 */
	public Date minusDays(final int days) {
		return this.plusDays(-days);
	}

	/**
	 * Adds the provided date to this date. Behaves as if this method call was
	 * equivalent to
	 * {@code this.plusDays(date.day).plusMonths(date.month).plusYears(date.year)}.
	 *
	 * @param date
	 *            The date to add. Any date such that the resulting date is at
	 *            or after the 01-01-0000. Must not be {@code null}.
	 * @return A new instance, representing this date plus {@code date}.
	 * @see {@link #plusDays(int)}
	 * @see {@link #plusMonths(int)}
	 * @see {@link #plusYears(int)}
	 */
	public Date plus(final Date date) {
		return this.plusDays(date.day).plusMonths(date.month).plusYears(date.year);
	}

	/**
	 * Subtracts the provided date to this date. Behaves as if this method call
	 * was equivalent to
	 * {@code this.minusDays(date.day).minusMonths(date.month).minusYears(date.year)}.
	 *
	 * @param date
	 *            The date to subtract. Any date such that the resulting date is
	 *            at or after the 01-01-0000. Must not be {@code null}.
	 * @return A new instance, representing this date minus {@code date}.
	 * @see {@link #minusDays(int)}
	 * @see {@link #minusMonths(int)}
	 * @see {@link #minusYears(int)}
	 */
	public Date minus(final Date date) {
		return this.minusDays(date.day).minusMonths(date.month).minusYears(date.year);
	}

	@Override
	public String toString() {
		return String.format("%02d-%02d-%d", this.day, this.month, this.year);
	}

	/**
	 * Queries whether this date is an earlier point in time than another date.
	 *
	 * @param other
	 *            A date to check. Must not be {@code null}.
	 * @return {@code true} iff this date comes before {@code other}.
	 */
	public boolean isBefore(final Date other) {
		return this.inDaysSinceBeginning() < other.inDaysSinceBeginning();
	}

	/**
	 * Queries whether this represents the same date as another date.
	 *
	 * @param other
	 *            A date to check. Must not be {@code null}.
	 * @return {@code true} iff this date represents the same day in the same
	 *         month in the same year as {@code other}.
	 */
	public boolean isEqual(final Date other) {
		return this.year == other.year && this.month == other.month && this.day == other.day;
	}

	/**
	 * Queries whether this date is a later point in time than another date.
	 *
	 * @param other
	 *            A date to check. Must not be {@code null}.
	 * @return {@code true} iff this date comes after {@code other}.
	 */
	public boolean isAfter(final Date other) {
		return other.isBefore(this);
	}

	/**
	 * Queries the sum of days passed since the (virtual date) 01/01/0000.
	 *
	 * @return The number of days passed since the end of 01/01/0000.
	 */
	private int inDaysSinceBeginning() {
		int passedDays = 0;
		if (this.year > 0) {
			final int previousYear = this.year - 1;
			// +1 because year 0 is a leap year, too.
			final int passedLeapDays = previousYear / 400 + previousYear / 4 - previousYear / 100 + 1;
			passedDays = this.year * 365 + passedLeapDays;
		}
		// -1 to subtract 01/01/0000
		return passedDays + this.getDayOfYear() - 1;
	}

	/**
	 * Queries the {@link Month} constant representing this date’s month.
	 *
	 * @return This date’s {@link Month}. Is not {@code null}.
	 */
	public Month getMonth() {
		return Month.ofIndex(this.month);
	}

	/**
	 * Queries this date’s year.
	 *
	 * @return This date’s year. Is ≥ 0.
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Queries this date’s month integer value.
	 *
	 * @return This date’s month. Satisfies 1 ≤ {@code this.getMonthValue()} ≤
	 *         12 and {@code this.getMonthValue() == this.getMonth().toIndex()}
	 */
	public int getMonthValue() {
		return this.day;
	}

	/**
	 * Queries this date’s day.
	 *
	 * @return This date’s day. Satisfies 1 ≤ {@code this.getDay()} ≤
	 *         {@code this.getMonth().getLength(year)}.
	 */
	public int getDayOfMonth() {
		return this.day;
	}
}
