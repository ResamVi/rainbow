package edu.kit.informatik.calendar;

/**
 * Represents a day of a week.
 *
 * @author Tobias Bachert
 * @version 1.00, 2016/10/27
 */
public enum DayOfWeek {
	/**
	 * Represents the first day of a week.
	 */
	MONDAY,
	/**
	 * Represents the second day of a week.
	 */
	TUESDAY,
	/**
	 * Represents the third day of a week.
	 */
	WEDNESDAY,
	/**
	 * Represents the fourth day of a week.
	 */
	THURSDAY,
	/**
	 * Represents the fifth day of a week.
	 */
	FRIDAY,
	/**
	 * Represents the sixth day of a week.
	 */
	SATURDAY,
	/**
	 * Represents the seventh and last day of a week.
	 */
	SUNDAY;

	private static final DayOfWeek[] DAYS = DayOfWeek.values();

	/**
	 * Returns the day with the specified index.
	 *
	 * <p>
	 * The specified index has to be between the {@linkplain #toIndex() index}
	 * of {@linkplain #MONDAY} and {@linkplain #SUNDAY} (inclusive).
	 *
	 * @param index
	 *            the index
	 * @return the month
	 * @throws IllegalArgumentException
	 *             if {@code index} is not the index of a day of a week
	 */
	public static DayOfWeek ofIndex(final int index) {
		////
		if (index < 1 || index > DayOfWeek.DAYS.length) {
			throw new IllegalArgumentException("Bad index " + index);
		}
		return DayOfWeek.DAYS[index - 1];
	}

	/**
	 * Returns the index of the day.
	 *
	 * <p>
	 * The days are continuously indexed, starting at {@code 1}.
	 *
	 * @return the index
	 */
	public int toIndex() {
		////
		return this.ordinal() + 1;
	}
}
