package edu.kit.informatik;

/**
 * Creates a new {@link FixedDeltaDateIterator} that will return all elements of
 * the (infinite) set of all Dates <code>startDate + (deltaDay, deltaMonth, deltaYear)</code>
 * lesser than endDate. The Dates are
 * returned in the natural ordering, starting with <code>startDate</code>
 * 
 * @author Julien Midedji
 * 
 **/
public class FixedDeltaDateIterator implements SortedIterator<Date> {

    private Date startDate;
    private Date endDate;
    private int deltaYear;
    private int deltaMonth;
    private int deltaDay;

    private int pointer;

    public FixedDeltaDateIterator(Date startDate, Date endDate, int deltaYear, int deltaMonth, int deltaDay) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.deltaYear = deltaYear;
        this.deltaMonth = deltaMonth;
        this.deltaDay = deltaDay;
        this.pointer = -1;
    }

    @Override
    public boolean hasNext() {
        Date next = getDate(pointer + 1);

        return endDate == null || next.isBefore(endDate) || next.isEqual(endDate);
    }

    @Override
    public Date next() {
        if (hasNext()) {
            pointer++;
            return getDate(pointer);
        } else
            return null;
    }

    /**
     * Returns the date at the given position
     * 
     * @return position of Date
     */
    private Date getDate(int position) {

        Date result = startDate;

        for (int i = 0; i < position; i++)
            result = result.plus(new Date(deltaYear, deltaMonth, deltaDay));

        return result;
    }

}
