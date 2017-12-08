package iterator;

import edu.kit.informatik.Date;
import edu.kit.informatik.FixedDeltaDateIterator;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.UnionSortedIterator;

public class Main {

    public static void main(String[] args) {
        FixedDeltaDateIterator dateIterator1 = new FixedDeltaDateIterator(new Date(2016, 11, 15), new Date(2017, 1, 25),
                0, 0, 14);
        FixedDeltaDateIterator dateIterator2 = new FixedDeltaDateIterator(new Date(2016, 10, 26),
                new Date(2016, 12, 28), 0, 0, 7);

        UnionSortedIterator<Date> unionIterator = new UnionSortedIterator<Date>(dateIterator1, dateIterator2);
        while (unionIterator.hasNext()) {
            Date date = unionIterator.next();
            Terminal.printLine(date.toString() + " " + date.getDayOfWeek());
        }
    }
}
