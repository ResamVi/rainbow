
package edu.kit.informatik;

/**
 * Represents a specific time of a day in seconds, minutes and hours.
 * 
 * @author  Tobias Bachert
 * @version 1.05, 2016/11/23
 */
public final class Time implements Comparable<Time> {
    
    private static final int HOURS_IN_DAY      = 24;
    private static final int MINUTES_IN_HOUR   = 60;
    private static final int SECONDS_IN_MINUTE = 60;
    
    private static final int SECONDS_IN_HOUR   = SECONDS_IN_MINUTE * MINUTES_IN_HOUR;
    /**
     * The count of seconds in a 24 hour day, {@value}.
     */
    /*pkg*/ static final int SECONDS_IN_DAY    = SECONDS_IN_HOUR * HOURS_IN_DAY;
    
    private final int hour;
    private final int minute;
    private final int second;
    
    /**
     * Constructs a {@code Time} with the specified arguments.
     * 
     * @param hour the hours
     * @param minute the minutes
     * @param second the seconds
     */
    public Time(
            final int hour,
            final int minute,
            final int second) {
        ////
        this.hour   = hour;
        this.minute = minute;
        this.second = second;
    }
    
    /**
     * Converts the specified count of seconds to a time of day.
     * 
     * <p>Note that the returned time will be within a 24-hour cycle. Any exceeding day will be discarded.
     * <blockquote><pre>
     * Time t0 = ofSeconds(seconds);
     * Time t1 = ofSeconds(seconds % {@value #SECONDS_IN_DAY});
     * assert t0.isEqual(t1);</pre>
     * </blockquote>
     * 
     * @param  seconds the seconds
     * @return the time
     */
    public static Time ofSeconds(
            final long seconds) {
        ////
        int time = (int) DateUtil.mod(seconds, SECONDS_IN_DAY);
        final int second = time - (time / SECONDS_IN_MINUTE) * SECONDS_IN_MINUTE;
        time /= SECONDS_IN_MINUTE;
        final int minute = time - (time / MINUTES_IN_HOUR)   * MINUTES_IN_HOUR;
        time /= MINUTES_IN_HOUR;
        final int hour   = time;
        
        return new Time(hour, minute, second);
    }
    
    /**
     * Returns a {@linkplain DateTime} with this as time and the specified date as date.
     * 
     * @param  date the date
     * @return the date time
     */
    public DateTime atDate(
            final Date date) {
        ////
        return new DateTime(date, this);
    }
    
    /**
     * Returns the total count of seconds in this time.
     * 
     * @return the total seconds
     * 
     * @see    #ofSeconds(long)
     */
    /*pkg*/ int inSeconds() {
        ////
        return hour * SECONDS_IN_HOUR + minute * SECONDS_IN_MINUTE + second;
    }
    
    @Override
    public int compareTo(
            final Time other) {
        ////
        return Integer.compare(inSeconds(), other.inSeconds());
    }
    
    @Override
    public boolean equals(
            final Object obj) {
        ////
        return obj instanceof Time
                && compareTo((Time) obj) == 0;
    }
    
    @Override
    public int hashCode() {
        ////
        return Integer.hashCode(inSeconds());
    }
    
    /**
     * Returns a string representation of this time.
     * 
     * <p>The returned string has the format
     * <blockquote><pre>
     * (hh):(mm):(ss)</pre>
     * </blockquote>
     */
    @Override
    public String toString() {
        ////
        return appendTo(new StringBuilder(8)).toString();
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
        DateUtil.append(sb, hour   , 2).append(':');
        DateUtil.append(sb, minute , 2).append(':');
        DateUtil.append(sb, second , 2);
        return sb;
    }
    
    //==================================================================================================================
    
    /**
     * Returns the hour of this time.
     * 
     * @return the hour
     */
    public int getHour() {
        ////
        return hour;
    }
    
    /**
     * Returns the minute of this time.
     * 
     * @return the minute
     */
    public int getMinute() {
        ////
        return minute;
    }
    
    /**
     * Returns the second of this time.
     * 
     * @return the second
     */
    public int getSecond() {
        ////
        return second;
    }
    
    //==================================================================================================================
    
    /**
     * Returns whether {@code this} is before {@code other}.
     * 
     * @param  other the object to compare to
     * @return {@code true} if this is before {@code other}
     */
    public boolean isBefore(
            final Time other) {
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
            final Time other) {
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
            final Time other) {
        ////
        return compareTo(other) > 0;
    }
    
    //==================================================================================================================
    
    /**
     * Adds the specified time to this returns the result.
     * 
     * @param  time the time to add
     * @return this plus {@code time}
     */
    public Time plus(
            final Time time) {
        ////
        return ofSeconds(inSeconds() + time.inSeconds());
    }
    
    /**
     * Subtracts the specified time from this and returns the result.
     * 
     * @param  time the time to subtract
     * @return this minus {@code time}
     */
    public Time minus(
            final Time time) {
        ////
        return ofSeconds(inSeconds() - time.inSeconds());
    }
}
