
package edu.kit.informatik;

/**
 * Represents a specific {@linkplain Time} at a specific {@linkplain Date}.
 * 
 * @author  Tobias Bachert
 * @version 1.03, 2016/11/18
 */
public final class DateTime implements Comparable<DateTime> {
    
    private final Date date;
    private final Time time;
    
    /**
     * Constructs a {@code DateTime} with the specified arguments.
     * 
     * @param date the date
     * @param time the time
     */
    public DateTime(
            final Date date,
            final Time time) {
        ////
        this.date = date;
        this.time = time;
    }
    
    /**
     * Returns the current date time in the UTC time zone.
     * 
     * @return the current date time
     */
    public static DateTime now() {
        ////
        return new Date(1970, 1, 1).atTime(new Time(0, 0, 0)).add(System.currentTimeMillis() / 1000);
    }
    
    /**
     * Adds the specified count of seconds to this.
     * 
     * @param  seconds the count of seconds to add
     * @return the resulting date time
     */
    private DateTime add(
            final long seconds) {
        ////
        final long totalSeconds = time.inSeconds() + seconds;
        
        final Time time = Time.ofSeconds(totalSeconds);
        final int  days = (int) ((totalSeconds - time.inSeconds()) / Time.SECONDS_IN_DAY);
        
        return date.plusDays(days).atTime(time);
    }
    
    @Override
    public int compareTo(
            final DateTime other) {
        ////
        final int dDif = date.compareTo(other.date);
        if (dDif != 0) return dDif;
        final int tDif = time.compareTo(other.time);
        if (tDif != 0) return tDif;
        return 0;
    }
    
    @Override
    public boolean equals(
            final Object obj) {
        ////
        return obj instanceof DateTime
                && compareTo((DateTime) obj) == 0;
    }
    
    @Override
    public int hashCode() {
        ////
        return date.hashCode() ^ time.hashCode();
    }
    
    /**
     * Returns a string representation of this date time.
     * 
     * <p>The returned string has the format
     * <blockquote><pre>
     * (dd)-(MM)-(yyyy)T(hh):(mm):(ss)</pre>
     * </blockquote>
     */
    @Override
    public String toString() {
        ////
        return appendTo(new StringBuilder(19)).toString();
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
        date.appendTo(sb).append('T');
        time.appendTo(sb);
        return sb;
    }
    
    //==================================================================================================================
    
    /**
     * Returns the {@linkplain Date} of this.
     * 
     * @return the date
     */
    public Date getDate() {
        ////
        return date;
    }
    
    /**
     * Returns the year of this date.
     * 
     * @return the year
     */
    public int getYear() {
        ////
        return date.getYear();
    }
    
    /**
     * Returns the month of this date as {@linkplain Month#toIndex() index}.
     * 
     * @return the month
     */
    public int getMonthValue() {
        ////
        return date.getMonthValue();
    }
    
    /**
     * Returns the month of this date.
     * 
     * @return the month
     */
    public Month getMonth() {
        ////
        return date.getMonth();
    }
    
    /**
     * Returns the day number of this date in the year of this.
     * 
     * @return the day number
     */
    public int getDayOfYear() {
        ////
        return date.getDayOfYear();
    }
    
    /**
     * Returns the day of this date.
     * 
     * @return the day
     */
    public int getDayOfMonth() {
        ////
        return date.getDayOfMonth();
    }
    
    /**
     * Returns the {@linkplain DayOfWeek} of this date.
     * 
     * @return the day of the week
     */
    public DayOfWeek getDayOfWeek() {
        ////
        return date.getDayOfWeek();
    }
    
    /**
     * Returns the {@linkplain Time} of this.
     * 
     * @return the time
     */
    public Time getTime() {
        ////
        return time;
    }
    
    /**
     * Returns the hour of this time.
     * 
     * @return the hour
     */
    public int getHour() {
        ////
        return time.getHour();
    }
    
    /**
     * Returns the minute of this time.
     * 
     * @return the minute
     */
    public int getMinute() {
        ////
        return time.getMinute();
    }
    
    /**
     * Returns the second of this time.
     * 
     * @return the second
     */
    public int getSecond() {
        ////
        return time.getSecond();
    }
    
    //==================================================================================================================
    
    /**
     * Returns whether {@code this} is before {@code other}.
     * 
     * @param  other the object to compare to
     * @return {@code true} if this is before {@code other}
     */
    public boolean isBefore(
            final DateTime other) {
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
            final DateTime other) {
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
            final DateTime other) {
        ////
        return compareTo(other) > 0;
    }
    
    //==================================================================================================================
    
    /**
     * Adds the specified date time to this and returns the sum.
     * 
     * @param  datetime the date time to add
     * @return the sum of this and {@code datetime}
     */
    public DateTime plus(
            final DateTime datetime) {
        ////
        return plus(datetime.time).plus(datetime.date);
    }
    
    /**
     * Subtracts the specified date time to this and returns the difference.
     * 
     * @param  datetime the date time to subtract
     * @return the difference of this and {@code datetime}
     */
    public DateTime minus(
            final DateTime datetime) {
        ////
        return minus(datetime.time).minus(datetime.date);
    }
    
    /**
     * Adds the specified date to this and returns the result.
     * 
     * @param  other the date to add
     * @return this plus {@code other}
     */
    public DateTime plus(
            final Date other) {
        ////
        return date.plus(other).atTime(time);
    }
    
    /**
     * Subtracts the specified date from this and returns the result.
     * 
     * @param  other the date to subtract
     * @return this minus {@code other}
     */
    public DateTime minus(
            final Date other) {
        ////
        return date.minus(other).atTime(time);
    }
    
    /**
     * Adds the specified time to this and returns the result.
     * 
     * @param  time the time to add
     * @return this plus {@code time}
     */
    public DateTime plus(
            final Time time) {
        ////
        return add(time.inSeconds());
    }
    
    /**
     * Subtracts the specified time from this and returns the result.
     * 
     * @param  time the time to subtract
     * @return this minus {@code time}
     */
    public DateTime minus(
            final Time time) {
        ////
        return add(-time.inSeconds());
    }
    
    /**
     * Adds the specified years to this and returns the result.
     * 
     * @param  years the years to add
     * @return this plus {@code years}
     */
    public DateTime plusYears(
            final int years) {
        ////
        return date.plusYears(years).atTime(time);
    }
    
    /**
     * Adds the specified months to this and returns the result.
     * 
     * @param  months the months to add
     * @return this plus {@code months}
     */
    public DateTime plusMonths(
            final int months) {
        ////
        return date.plusMonths(months).atTime(time);
    }
    
    /**
     * Adds the specified days to this and returns the result.
     * 
     * @param  days the days to add
     * @return this plus {@code days}
     */
    public DateTime plusDays(
            final int days) {
        ////
        return date.plusDays(days).atTime(time);
    }
    
    /**
     * Subtracts the specified years from this and returns the result.
     * 
     * @param  years the years to subtract
     * @return this minus {@code years}
     */
    public DateTime minusYears(
            final int years) {
        ////
        return date.minusYears(years).atTime(time);
    }
    
    /**
     * Subtracts the specified months from this and returns the result.
     * 
     * @param  months the months to subtract
     * @return this minus {@code months}
     */
    public DateTime minusMonths(
            final int months) {
        ////
        return date.minusMonths(months).atTime(time);
    }
    
    /**
     * Subtracts the specified days from this and returns the result.
     * 
     * @param  days the days to subtract
     * @return this minus {@code days}
     */
    public DateTime minusDays(
            final int days) {
        ////
        return date.minusDays(days).atTime(time);
    }
}
