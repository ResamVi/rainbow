
package edu.kit.informatik;

import static edu.kit.informatik.YearKind.NO_LEAP_YEAR;

/**
 * Represents a month of a year.
 * 
 * @author  Tobias Bachert
 * @version 1.02, 2016/11/23
 */
public enum Month {
    /**
     * Represents the first month of the year, 31 days.
     */
    JANUARY,
    /**
     * Represents the second month of the year, 29 days in a leap year, 28 otherwise.
     */
    FEBRUARY,
    /**
     * Represents the third month of the year, 31 days.
     */
    MARCH,
    /**
     * Represents the fourth month of the year, 30 days.
     */
    APRIL,
    /**
     * Represents the fifth month of the year, 31 days.
     */
    MAY,
    /**
     * Represents the sixth month of the year, 30 days.
     */
    JUNE,
    /**
     * Represents the seventh month of the year, 31 days.
     */
    JULY,
    /**
     * Represents the eight month of the year, 31 days.
     */
    AUGUST,
    /**
     * Represents the ninth month of the year, 30 days.
     */
    SEPTEMBER,
    /**
     * Represents the tenth month of the year, 31 days.
     */
    OCTOBER,
    /**
     * Represents the eleventh month of the year, 30 days.
     */
    NOVEMBER,
    /**
     * Represents the twelfth and last month of the year, 31 days.
     */
    DECEMBER;
    
    private static final Month[] MONTHS = values();
    
    private int zerothDay;
    
    static {
        int days = 0;
        for (final Month month : MONTHS) {
            month.zerothDay = days;
            days += NO_LEAP_YEAR.daysIn(month);
        }
        assert days == 365 : days;
    }
    
    /**
     * Returns the month with the specified index.
     * 
     * @param  index the index
     * @return the month
     * @throws IllegalArgumentException if {@code index} is not the index of a month
     */
    public static Month ofIndex(
            final int index) {
        ////
        assert MONTHS.length == 12;
        
        if (index < 1 || index > 12)
            throw new IllegalArgumentException("Invalid index " + index + " (expected [1,12])");
        
        return MONTHS[index - 1];
    }
    
    /**
     * Returns the index of the month.
     * 
     * <p>The months are continuously indexed, starting at {@code 1}.
     * 
     * @return the index
     */
    public int toIndex() {
        ////
        return ordinal() + 1;
    }
    
    /**
     * Returns the month after {@code this}.
     * 
     * @return the next month
     */
    public Month nextMonth() {
        ////
        return MONTHS[DateUtil.mod(ordinal() + 1, 12)];
    }
    
    /**
     * Returns the month before {@code this}.
     * 
     * @return the previous month
     */
    public Month previousMonth() {
        ////
        return MONTHS[DateUtil.mod(ordinal() - 1, 12)];
    }
    
    /**
     * Returns the zeroth day of this month for the specified year kind.
     * 
     * @param  year the kind of year
     * @return the zeroth day of this month
     */
    /*pkg*/ int zerothDay(
            final YearKind year) {
        ////
        return year.isLeap() && compareTo(FEBRUARY) > 0
                ? zerothDay + 1
                : zerothDay;
    }
    
    /**
     * Returns the count of days in this month for the specified year kind.
     * 
     * @param  year the kind of year
     * @return the count of days
     */
    /*pkg*/ int days(
            final YearKind year) {
        ////
        return days(year.isLeap());
    }
    
    /**
     * Returns how many days the month has.
     * 
     * <p>The argument {@code isLeap} has no effect unless {@code this} is {@linkplain #FEBRUARY}.
     * 
     * @param  isLeap whether to return the days for a leap year
     * @return the count of days in the month
     */
    private int days(
            final boolean isLeap) {
        ////
        switch (this) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return 31;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                return isLeap ? 29 : 28;
            default:
                throw new AssertionError("unreachable");
        }
    }
}
