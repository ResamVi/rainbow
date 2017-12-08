package edu.kit.informatik.calendar;

/**
 * A month of a year.
 *
 * @author Tobias Bachert
 * @author Joshua Gleitze
 * @version 2.0
 */
public enum Month {
	/**
	 * Represents the first month of the year, 31 days.
	 */
	JANUARY(31),
	/**
	 * Represents the second month of the year, 29 days in a leap year, 28
	 * otherwise.
	 */
	FEBRUARY(28),
	/**
	 * Represents the third month of the year, 31 days.
	 */
	MARCH(31),
	/**
	 * Represents the fourth month of the year, 30 days.
	 */
	APRIL(30),
	/**
	 * Represents the fifth month of the year, 31 days.
	 */
	MAY(31),
	/**
	 * Represents the sixth month of the year, 30 days.
	 */
	JUNE(30),
	/**
	 * Represents the seventh month of the year, 31 days.
	 */
	JULY(31),
	/**
	 * Represents the eight month of the year, 31 days.
	 */
	AUGUST(31),
	/**
	 * Represents the ninth month of the year, 30 days.
	 */
	SEPTEMBER(30),
	/**
	 * Represents the tenth month of the year, 31 days.
	 */
	OCTOBER(31),
	/**
	 * Represents the eleventh month of the year, 30 days.
	 */
	NOVEMBER(30),
	/**
	 * Represents the twelfth and last month of the year, 31 days.
	 */
	DECEMBER(31);

	/**
	 * A copy of {@link #values()}. I don’t know why the example solution
	 * thought this was necessary.
	 */
	private static final Month[] MONTHS = Month.values();
	/**
	 * The number of days in this month, assuming we’re no in a leap year.
	 */
	private final int length;

	/**
	 * Constructs a month enum constant.
	 *
	 * @param length
	 *            The length of the month represented by the created constant,
	 *            assuming that we’ro not in a leap year.
	 */
	private Month(final int length) {
		this.length = length;
	}

	/**
	 * Returns the month with the specified index.
	 *
	 * <p>
	 * The specified index has to be between the {@linkplain #toIndex() index}
	 * of {@linkplain #JANUARY} and {@linkplain #DECEMBER} (inclusive).
	 *
	 * @param index
	 *            the index
	 * @return the month
	 * @throws IllegalArgumentException
	 *             if {@code index} is not the index of a month
	 */
	public static Month ofIndex(final int index) {
		if (index < 1 || index > Month.MONTHS.length) {
			throw new IllegalArgumentException("Bad index " + index);
		}
		return Month.MONTHS[index - 1];
	}

	/**
	 * Returns the index of the month.
	 *
	 * <p>
	 * The months are continuously indexed, starting at {@code 1}.
	 *
	 * @return the index
	 */
	public int toIndex() {
		return this.ordinal() + 1;
	}

	/**
	 * Queries the number of days in this month if it’s in the provided year.
	 *
	 * @param year
	 *            The year this month is assumed to be in. An integer ≥ 0.
	 * @return The number of days in this month if it’s in {@code year}.
	 */
	public int getLength(final int year) {
		if (Date.isLeapYear(year) && this == FEBRUARY) {
			return this.length + 1;
		}
		return this.length;
	}

	/**
	 * Checks whether {@code this} month comes after the provided {@code month}.
	 *
	 * @param month
	 *            A month to check.
	 * @return {@code true} iff {@code this} month comes after {@code month}.
	 */
	private boolean isAfter(final Month month) {
		return this.toIndex() > month.toIndex();
	}

	/**
	 * Checks whether {@code this} month comes before the provided
	 * {@code month}.
	 *
	 * @param month
	 *            A month to check.
	 * @return {@code true} iff {@code this} month comes before {@code month}.
	 */
	public boolean isBefore(final Month month) {
		return month.isAfter(this);
	}
}
