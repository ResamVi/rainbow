
package edu.kit.informatik;

import static edu.kit.informatik.Month.DECEMBER;
import static edu.kit.informatik.Month.JANUARY;

/**
 * Represents the kind of a year. A year can be either a leap year or not a leap year.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/11/15
 */
public enum YearKind {
    /**
     * Represents a leap year.
     */
    LEAP_YEAR,
    /**
     * Represents a non leap year.
     */
    NO_LEAP_YEAR;
    
    /**
     * Returns the kind of the specified year.
     * 
     * @param  year the year
     * @return {@linkplain #LEAP_YEAR} if the specified year is a leap year, {@linkplain #NO_LEAP_YEAR} otherwise
     */
    public static YearKind of(
            final int year) {
        ////
        return (year & 3) == 0 && (year % 100 != 0 || year % 400 == 0) ? LEAP_YEAR : NO_LEAP_YEAR;
    }
    
    /**
     * Returns a boolean value representing whether {@code this} represents a leap year or not.
     * 
     * @return {@code true} if this is {@linkplain #LEAP_YEAR}, {@code false} if this is {@linkplain #NO_LEAP_YEAR}
     */
    public boolean isLeap() {
        ////
        return this == LEAP_YEAR;
    }
    
    /**
     * Returns the first day of the specified month.
     * 
     * @param  month the month
     * @return the first day
     */
    public int firstDayOf(
            final int month) {
        ////
        return firstDayOf(Month.ofIndex(month));
    }
    
    /**
     * Returns the first day of the specified month.
     * 
     * @param  month the month
     * @return the first day
     */
    public int firstDayOf(
            final Month month) {
        ////
        return zerothDayOf(month) + 1;
    }
    
    /**
     * Returns the last day of the specified month.
     * 
     * @param  month the month
     * @return the last day
     */
    public int lastDayOf(
            final int month) {
        ////
        return lastDayOf(Month.ofIndex(month));
    }
    
    /**
     * Returns the last day of the specified month.
     * 
     * @param  month the month
     * @return the last day
     */
    public int lastDayOf(
            final Month month) {
        ////
        return zerothDayOf(month) + daysIn(month);
    }
    
    /**
     * Returns the days in the specified month.
     * 
     * @param  month the month
     * @return the count of days
     */
    public int daysIn(
            final int month) {
        ////
        return daysIn(Month.ofIndex(month));
    }
    
    /**
     * Returns the days in the specified month.
     * 
     * @param  month the month
     * @return the count of days
     */
    public int daysIn(
            final Month month) {
        ////
        return month.days(this);
    }
    
    /**
     * Returns the month the specified day of year belongs to.
     * 
     * @param  day the day
     * @return the month
     */
    public Month monthByDayOfYear(
            final int day) {
        ////
        if (!isValidDay(day))
            throw new IllegalArgumentException("Invalid day " + day + " for " + this);
        
        for (Month month = DECEMBER;; month = month.previousMonth()) {
            if (zerothDayOf(month) < day)
                return month;
        }
    }
    
    /**
     * Returns whether the specified day is a valid day of a year.
     * 
     * @param  day the day to check
     * @return {@code true} if the day is valid, {@code false} otherwise
     * 
     * @see    #monthByDayOfYear(int)
     */
    private boolean isValidDay(
            final int day) {
        ////
        return day >= firstDayOf(JANUARY) && day <= lastDayOf(DECEMBER);
    }
    
    /**
     * Returns the zeroth day of the specified month.
     * 
     * @param  month the month
     * @return the zeroth day
     */
    /*pkg*/ int zerothDayOf(
            final int month) {
        ////
        return zerothDayOf(Month.ofIndex(month));
    }
    
    /**
     * Returns the zeroth day of the specified month.
     * 
     * @param  month the month
     * @return the zeroth day
     */
    /*pkg*/ int zerothDayOf(
            final Month month) {
        ////
        return month.zerothDay(this);
    }
}
