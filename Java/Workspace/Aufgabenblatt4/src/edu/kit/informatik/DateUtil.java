
package edu.kit.informatik;

import static java.lang.Integer.parseInt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for date classes.
 * 
 * @author  Tobias Bachert
 * @version 1.03, 2016/11/15
 */
final class DateUtil {
    private DateUtil() {
        ////
    }
    
    /**
     * Appends the specified value to the specified string builder, padded with leading zeroes to at least {@code
     * digits} length.
     * 
     * <p>If {@code value} is negative, then an ASCII minus sign '-' ('\u002D') is appended in front of the padded
     * absolute value.
     * 
     * @param  sb the string builder to append to
     * @param  value the value to append
     * @param  digits the count of digits to append
     * @return a reference to {@code sb}
     */
    public static StringBuilder append(
            final StringBuilder sb,
            final int value,
            final int digits) {
        ////
        final int start = value < 0 ? sb.length() + 1 : sb.length();
        final int count = sb.append(value).length() - start;
        if (count < digits)
            sb.insert(start, nTimes('0', digits - count));
        return sb;
    }
    
    /**
     * Returns a char sequence that contains the specified character {@code n} times.
     * 
     * <p>The type of the returned char sequence is undefined but ensured to be serializable.
     * 
     * @param  ch the character
     * @param  n the length of the returned sequence
     * @return the char sequence
     * @throws IllegalArgumentException if {@code n} is negative
     */
    private static CharSequence nTimes(
            final char ch,
            final int n) {
        ////
        return new RepeatedCharacter(ch, n);
    }
    
    /**
     * Returns the positive modulus of the specified arguments.
     * 
     * <p>The returned value will be element of the least residue system modulo {@code m}. Let {@code a} be the result
     * of {@code mod(n, m)}, then {@code a} is determined as by the formula
     * <blockquote><code>
     * a &equiv;<sub>m</sub> n &and; 0 &le; a &lt; |m|</code>
     * </blockquote>
     * 
     * <p><br>
     * Examples (taken from {@linkplain Math#floorMod(int, int)}):
     * <ul>
     *   <li>If the signs of dividend and divisor are positive, the results of {@code mod}, {@code floorMod} and the
     *       {@code %} operator are the same.<br>
     *       <ul>
     *         <li>{@code mod(+4, +3) == +1}, &nbsp; {@code floorMod(+4, +3) == +1}, &nbsp; {@code (+4 % +3) == +1}</li>
     *       </ul>
     *   </li>
     *   <li>If at least one of the arguments is negative, the results differ from {@code floorMod} and the {@code %}
     *       operator.<br>
     *       <ul>
     *         <li>{@code mod(+4, -3) == +1}, &nbsp; {@code floorMod(+4, -3) == -2}, &nbsp; {@code (+4 % -3) == +1}</li>
     *         <li>{@code mod(-4, +3) == +2}, &nbsp; {@code floorMod(-4, +3) == +2}, &nbsp; {@code (-4 % +3) == -1}</li>
     *         <li>{@code mod(-4, -3) == +2}, &nbsp; {@code floorMod(-4, -3) == -1}, &nbsp; {@code (-4 % -3) == -1}</li>
     *       </ul>
     *   </li>
     * </ul>
     * 
     * <p><br>
     * This method behaves exactly like the following code snippet (while providing better performance characteristics):
     * <blockquote><pre>
     * import static java.lang.Math.*;
     * ...
     * int mod = n % m;
     * if (mod &lt; 0)
     *     mod += abs(m);
     * return mod;</pre>
     * </blockquote>
     * 
     * <p>Note that the solution described in the <i>JavaDoc</i> of {@linkplain Math#floorMod(int, int)} is not overflow
     * safe ({@code floorMod(n, m) + abs(m)} may overflow if {@code m} is positive) and thus should not be used.
     * <span style="font-size:.7em;">(Besides that it would be significantly slower.)</span>
     * 
     * @param  n the dividend
     * @param  m the divisor
     * @return the positive modulus
     * @throws ArithmeticException if the divisor {@code m} is zero
     */
    public static int mod(
            final int n,
            final int m) {
        ////
        final int mod = n % m;
        return mod < 0 ? mod + Math.abs(m) : mod;
    }
    
    /**
     * Returns the positive modulus of the specified arguments.
     * 
     * <p>The returned value will be element of the least residue system modulo {@code m}. Let {@code a} be the result
     * of {@code mod(n, m)}, then {@code a} is determined as by the formula
     * <blockquote><code>
     * a &equiv;<sub>m</sub> n &and; 0 &le; a &lt; |m|</code>
     * </blockquote>
     * 
     * <p><br>
     * For examples, see {@linkplain #mod(int, int)}.
     * 
     * <p><br>
     * This method behaves exactly like the following code snippet (while providing better performance characteristics):
     * <blockquote><pre>
     * import static java.lang.Math.*;
     * ...
     * long mod = n % m;
     * if (mod &lt; 0)
     *     mod += abs(m);
     * return mod;
     * ...
     * return (floorMod(n, m) + abs(m)) % abs(m);</pre>
     * </blockquote>
     * 
     * <p>Note that the solution described in the <i>JavaDoc</i> of {@linkplain Math#floorMod(int, int)} is not overflow
     * safe ({@code floorMod(n, m) + abs(m)} may overflow if {@code m} is positive) and thus should not be used.
     * <span style="font-size:.7em;">(Besides that it would be significantly slower.)</span>
     * 
     * @param  n the dividend
     * @param  m the divisor
     * @return the positive modulus
     * @throws ArithmeticException if the divisor {@code m} is zero
     */
    public static long mod(
            final long n,
            final long m) {
        ////
        final long mod = n % m;
        return mod < 0 ? mod + Math.abs(m) : mod;
    }
    
    //==================================================================================================================
    
    /**
     * Returns the appointment represented by the specified argument.
     *
     * <p>The specified argument has to match the format:
     * <blockquote><pre>
     * &lt;from&gt; &lt;to&gt; &lt;name&gt;</pre>
     * </blockquote>
     *
     * @param  charSequence the input argument
     * @return the appointment
     * @throws NullPointerException if {@code charSequence} is {@code null}
     * 
     * @see    #parseDateTime(CharSequence)
     */
    public static Appointment parseAppointment(
            final CharSequence charSequence) {
        ////
        final Matcher m = PatternHolder.APPOINTMENT_RE.matcher(charSequence);
        if (!m.matches())
            throw new IllegalArgumentException("Not a valid appointment");
        return new Appointment(m.group(13), dateTime(m, 0), dateTime(m, 6));
    }
    
    /**
     * Returns the datetime represented by the specified argument.
     *
     * <p>The specified argument has to match the format:
     * <blockquote><pre>
     * &lt;date&gt;T&lt;time&gt;</pre>
     * </blockquote>
     *
     * @param  charSequence the input argument
     * @return the datetime
     * @throws NullPointerException if {@code charSequence} is {@code null}
     * 
     * @see    #parseDate(CharSequence)
     * @see    #parseTime(CharSequence)
     */
    public static DateTime parseDateTime(
            final CharSequence charSequence) {
        ////
        final Matcher m = PatternHolder.DATEIME_RE.matcher(charSequence);
        if (!m.matches())
            throw new IllegalArgumentException("Not a valid datetime");
        return dateTime(m, 0);
    }
    
    /**
     * Returns the date represented by the specified argument.
     *
     * <p>The specified argument has to match the format:
     * <blockquote><pre>
     * &lt;day&gt;-&lt;month&gt;-&lt;year&gt;</pre>
     * </blockquote>
     *
     * @param  charSequence the input argument
     * @return the date
     * @throws NullPointerException if {@code charSequence} is {@code null}
     */
    public static Date parseDate(
            final CharSequence charSequence) {
        ////
        final Matcher m = PatternHolder.DATE_RE.matcher(charSequence);
        if (!m.matches())
            throw new IllegalArgumentException("Not a valid date");
        return date(m, 0);
    }
    
    /**
     * Returns the time represented by the specified argument.
     *
     * <p>The specified argument has to match the format:
     * <blockquote><pre>
     * &lt;hour&gt;:&lt;minute&gt;:&lt;second&gt;</pre>
     * </blockquote>
     *
     * @param  charSequence the input argument
     * @return the time
     * @throws NullPointerException if {@code charSequence} is {@code null}
     */
    public static Time parseTime(
            final CharSequence charSequence) {
        ////
        final Matcher m = PatternHolder.TIME_RE.matcher(charSequence);
        if (!m.matches())
            throw new IllegalArgumentException("Not a valid time");
        return time(m, 0);
    }
    
    private static DateTime dateTime(
            final Matcher m,
            final int off) {
        ////
        return new DateTime(date(m, 0 + off), time(m, 3 + off));
    }
    
    private static Date date(
            final Matcher m,
            final int off) {
        ////
        return new Date(parseInt(m.group(3 + off)), parseInt(m.group(2 + off)), parseInt(m.group(1 + off)));
    }
    
    private static Time time(
            final Matcher m,
            final int off) {
        ////
        return new Time(parseInt(m.group(1 + off)), parseInt(m.group(2 + off)), parseInt(m.group(3 + off)));
    }
    
    /**
     * Represents a char sequence consisting of a single repeated character.
     * 
     * @author  Tobias Bachert
     * @version 1.02, 2016/11/13
     */
    private static final class RepeatedCharacter implements CharSequence {
        
        private final char ch;
        private final int length;
        
        /**
         * Constructs a new {@code RepeatedCharacter}.
         * 
         * @param ch the character to repeat
         * @param length the count of repetitions
         */
        RepeatedCharacter(
                final char ch,
                final int length) {
            ////
            this.ch = ch;
            this.length = length;
        }
        
        @Override
        public int length() {
            ////
            return length;
        }
        
        @Override
        public char charAt(
                final int index) {
            ////
            return ch;
        }
        
        @Override
        public CharSequence subSequence(
                final int start,
                final int end) {
            ////
            return end - start == length ? this : nTimes(ch, end - start);
        }
        
        @Override
        public String toString() {
            ////
            return new StringBuilder(length).append(this).toString();
        }
    }
    
    private static final class PatternHolder {
        private static final String DATE = "(\\d{1,2})-(\\d{1,2})-(-?\\d{1,6})";
        private static final String TIME = "(\\d{1,2}):(\\d{1,2}):(\\d{1,2})";
        private static final String DATETIME = DATE + 'T' + TIME;
        private static final String APPOINTMENT = DATETIME + ' ' + DATETIME + ' ' + "(.*)";
        
        static final Pattern DATE_RE = Pattern.compile(DATE);
        static final Pattern TIME_RE = Pattern.compile(TIME);
        static final Pattern DATEIME_RE = Pattern.compile(DATETIME);
        static final Pattern APPOINTMENT_RE = Pattern.compile(APPOINTMENT);
        
        private PatternHolder() { /**/ }
    }
}
