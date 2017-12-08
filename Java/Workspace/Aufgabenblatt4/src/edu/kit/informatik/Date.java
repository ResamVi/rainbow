
package edu.kit.informatik;

/**
 * Represents a date consisting of a year, a {@linkplain Month month} and a day.
 * 
 * @author  Tobias Bachert
 * @version 1.04, 2016/11/23
 */
public final class Date implements Comparable<Date> {
    
    private final int year;
    private final int month;
    private final int day;
    
    /**
     * Constructs a {@code Date} with the specified arguments.
     * 
     * @param year the year
     * @param month the month
     * @param day the day
     */
    public Date(
            final int year,
            final int month,
            final int day) {
        ////
        this.year  = year;
        this.month = month;
        this.day   = day;
    }
    
    /**
     * Returns a {@linkplain DateTime} with this as date and the specified time as time.
     * 
     * @param  time the time
     * @return the date time
     */
    public DateTime atTime(
            final Time time) {
        ////
        return new DateTime(this, time);
    }
    
    /**
     * Returns whether the year of this date is a leap year.
     * 
     * @return {@code true} if the year is a leap year, {@code false} otherwise
     */
    public boolean isLeapYear() {
        ////
        return YearKind.of(year).isLeap();
    }
    
    /**
     * Returns the total count of days.
     * 
     * <p>The day zero is defined as the imaginary date {@code 0000/01/00}, which is equivalent to the date {@code
     * -0001/12/31}.
     * 
     * @return the count of days
     */
    private int totalDays() {
        ////
        final YearKind yearKind = YearKind.of(year);
        return yearKind.zerothDayOf(month) + day + (year > 0 && !yearKind.isLeap() ? 1 : 0)
                + year * 365 + year / 4 - year / 100 + year / 400;
    }
    
    /**
     * Returns the days that have passed since the zeroth of the specified year.
     * 
     * @param  year the year
     * @param  month the month
     * @param  day the day, may exceed the specified month
     * @return the day of year
     */
    private static int dayOfYear(
            final int year,
            final int month,
            final int day) {
        ////
        return YearKind.of(year).zerothDayOf(month) + day;
    }
    
    /**
     * Returns the index of the day of the week of this date.
     *
     * @return the day of the week
     * 
     * @see    DayOfWeek#ofIndex(int)
     */
    private int dayOfWeek() {
        ////
        final int modDays = DateUtil.mod(totalDays(), 7);
        return modDays <= 2 ? modDays + 5 : modDays - 2; // 0000/01/01==SATURDAY==D1==6
    }
    
    @Override
    public int compareTo(
            final Date other) {
        ////
        return Integer.compare(totalDays(), other.totalDays());
    }
    
    @Override
    public boolean equals(
            final Object obj) {
        ////
        return obj instanceof Date
                && compareTo((Date) obj) == 0;
    }
    
    @Override
    public int hashCode() {
        ////
        return Integer.hashCode(totalDays());
    }
    
    /**
     * Returns a string representation of this date.
     * 
     * <p>The returned string has the format
     * <blockquote><pre>
     * (dd)-(MM)-(yyyy)</pre>
     * </blockquote>
     */
    @Override
    public String toString() {
        ////
        return appendTo(new StringBuilder(10)).toString();
    }
    
    /**
     * Appends the string representation of this to the specified string builder.
     * 
     * <p>The string representation is appended as per invoking
     * <blockquote><pre>
     * sb.append({@linkplain #toString()});</pre>
     * </blockquote>
     * 
     * @param  sb the string builder to append to
     * @return a reference to {@code sb}
     */
    /*pkg*/ StringBuilder appendTo(
            final StringBuilder sb) {
        ////
        DateUtil.append(sb, day   , 2).append('-');
        DateUtil.append(sb, month , 2).append('-');
        DateUtil.append(sb, year  , 4);
        return sb;
    }
    
    //==================================================================================================================
    
    /**
     * Returns the year of this date.
     * 
     * @return the year
     */
    public int getYear() {
        ////
        return year;
    }
    
    /**
     * Returns the month of this date as {@linkplain Month#toIndex() index}.
     * 
     * @return the month
     */
    public int getMonthValue() {
        ////
        return month;
    }
    
    /**
     * Returns the month of this date.
     * 
     * @return the month
     */
    public Month getMonth() {
        ////
        return Month.ofIndex(getMonthValue());
    }
    
    /**
     * Returns the day number of this date in the year of this.
     * 
     * @return the day number
     */
    public int getDayOfYear() {
        ////
        return dayOfYear(year, month, day);
    }
    
    /**
     * Returns the day of this date.
     * 
     * @return the day
     */
    public int getDayOfMonth() {
        ////
        return day;
    }
    
    /**
     * Returns the {@linkplain DayOfWeek} of this date.
     * 
     * @return the day of the week
     */
    public DayOfWeek getDayOfWeek() {
        ////
        return DayOfWeek.ofIndex(dayOfWeek());
    }
    
    //==================================================================================================================
    
    /**
     * Returns whether {@code this} is before {@code other}.
     * 
     * @param  other the object to compare to
     * @return {@code true} if this is before {@code other}
     */
    public boolean isBefore(
            final Date other) {
        ////
        return compareTo(other) < 0;
    }
    
    /**
     * Returns whether {@code this} is equal to {@code other}.
     * 
     * @param  other the object to compare to
     * @return {@code true} if this is equal to {@code other}
     */
    public boolean isEqual(
            final Date other) {
        ////
        return compareTo(other) == 0;
    }
    
    /**
     * Returns whether {@code this} is after {@code other}.
     * 
     * @param  other the object to compare to
     * @return {@code true} if this is after {@code other}
     */
    public boolean isAfter(
            final Date other) {
        ////
        return compareTo(other) > 0;
    }
    
    //==================================================================================================================
    
    /**
     * Adds the specified date to this and returns the result.
     * 
     * @param  date the date to add
     * @return this plus {@code date}
     */
    public Date plus(
            final Date date) {
        ////
        return plusDays(date.day).plusMonths(date.month).plusYears(date.year);
    }
    
    /**
     * Adds the specified years to this and returns the result.
     * 
     * @param  years the years to add
     * @return this plus {@code years}
     */
    public Date plusYears(
            final int years) {
        ////
        return resolveDate(year + years, month, day);
    }
    
    /**
     * Adds the specified months to this and returns the result.
     * 
     * @param  months the months to add
     * @return this plus {@code months}
     */
    public Date plusMonths(
            final int months) {
        ////
        return resolveDate(year, month + months, day);
    }
    
    /**
     * Adds the specified days to this and returns the result.
     * 
     * @param  days the days to add
     * @return this plus {@code days}
     */
    public Date plusDays(
            final int days) {
        ////
        return resolveDate(year, month, day + days);
    }
    
    /**
     * Subtracts the specified date from this and returns the result.
     * 
     * @param  date the date to subtract
     * @return this minus {@code date}
     */
    public Date minus(
            final Date date) {
        ////
        return minusDays(date.day).minusMonths(date.month).minusYears(date.year);
    }
    
    /**
     * Subtracts the specified years from this and returns the result.
     * 
     * @param  years the years to subtract
     * @return this minus {@code years}
     */
    public Date minusYears(
            final int years) {
        ////
        return resolveDate(year - years, month, day);
    }
    
    /**
     * Subtracts the specified months from this and returns the result.
     * 
     * @param  months the months to subtract
     * @return this minus {@code months}
     */
    public Date minusMonths(
            final int months) {
        ////
        return resolveDate(year, month - months, day);
    }
    
    /**
     * Subtracts the specified days from this and returns the result.
     * 
     * @param  days the days to subtract
     * @return this minus {@code days}
     */
    public Date minusDays(
            final int days) {
        ////
        return resolveDate(year, month, day - days);
    }
    
    //==================================================================================================================
    
    /**
     * Returns a date for the specified arguments.
     * 
     * <p>This method handles any count of exceeding days and months gracefully. Note that exceeding months are handled
     * prior exceeding days are handled.
     * 
     * @param  year the year
     * @param  month the month
     * @param  day the day
     * @return the date
     */
    private static Date resolveDate(
            final int year,
            final int month,
            final int day) {
        ////
        final int nMonth = DateUtil.mod(month - 1, 12) + 1;
        final int nYear  = year + (month - nMonth) / 12;
        
        return isValid(nYear, nMonth, day)
                ? new Date(nYear, nMonth, day)
                : ofDayOfYear(nYear, dayOfYear(nYear, nMonth, day));
    }
    
    /**
     * Returns whether the specified data represents a valid date.
     * 
     * @param  year the year
     * @param  month the month
     * @param  day the day
     * @return {@code true} if the arguments represent a valid date, {@code false} otherwise
     */
    private static boolean isValid(
            final int year,
            final int month,
            final int day) {
        ////
        return day >= 1 && day <= YearKind.of(year).daysIn(month);
    }
    
    /**
     * Returns the date for the specified year and day of year.
     * 
     * <p>This method handles exceeding days gracefully, i.e. {@code day=0} will be treated as last day of the previous
     * year.
     * 
     * <p>This method can be used to revert the result of the {@linkplain #totalDays()} method by providing {@code 0}
     * for the year and {@code totalDays} for the day.
     * <blockquote><pre>
     * Date d;
     * ...
     * assert d.equals(ofDayOfYear(0, d.totalDays());</pre>
     * </blockquote>
     * 
     * @param  year the year
     * @param  day the day
     * @return the date
     */
    private static Date ofDayOfYear(
            final int year,
            final int day) {
        ////
        int remainingDays = day;
        
        // Adding/subtracting 365/366 days until day is within the current year
        final int daysIn400Years = 365 * 400 + 100 - 4 + 1;
        int nYear = year + remainingDays / daysIn400Years * 400;
        remainingDays %= daysIn400Years;
        
        if (remainingDays <= 0) {
            for (; remainingDays <= 0; remainingDays += daysInYear(--nYear)) { }
        } else {
            int diy = daysInYear(nYear);
            while (remainingDays > diy) {
                remainingDays -= diy;
                nYear++;
                diy = daysInYear(nYear);
            }
        }
        
        final YearKind yearKind = YearKind.of(nYear);
        final Month    month    = yearKind.monthByDayOfYear(remainingDays);
        
        return new Date(nYear, month.toIndex(), remainingDays - yearKind.zerothDayOf(month));
    }
    
    /**
     * Returns the days in the specified year.
     * 
     * @param  year the year
     * @return the days, either {@code 365} or {@code 366}
     */
    private static int daysInYear(
            final int year) {
        ////
        return YearKind.of(year).lastDayOf(Month.DECEMBER);
    }
}
