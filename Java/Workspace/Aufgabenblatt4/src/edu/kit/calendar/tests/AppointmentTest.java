package edu.kit.calendar.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.Appointment;
import edu.kit.informatik.Date;
import edu.kit.informatik.DateTime;
import edu.kit.informatik.Time;

public class AppointmentTest {

    @Test
    public void test() {

        DateTime first = new DateTime(new Date(2000, 1, 1), new Time(0, 0, 0));
        DateTime second = new DateTime(new Date(2000, 1, 2), new Time(0, 0, 0));
        DateTime third = new DateTime(new Date(2000, 1, 3), new Time(0, 0, 0));
        DateTime fourth = new DateTime(new Date(2000, 1, 4), new Time(0, 0, 0));
        DateTime fifth = new DateTime(new Date(2000, 1, 5), new Time(0, 0, 0));
        DateTime sixth = new DateTime(new Date(2000, 1, 5), new Time(0, 0, 0));
        DateTime seventh = new DateTime(new Date(2000, 1, 5), new Time(0, 0, 0));
        DateTime eigth = new DateTime(new Date(2000, 1, 5), new Time(0, 0, 0));
        DateTime ninth = new DateTime(new Date(2000, 1, 5), new Time(0, 0, 0));

        Appointment a = new Appointment("a", first, fifth);
        Appointment b = new Appointment("b", first, seventh);
        Appointment c = new Appointment("c", second, eigth);
        Appointment d = new Appointment("d", third, fifth);
        Appointment e = new Appointment("e", third, sixth);
        Appointment f = new Appointment("fg", third, seventh);
        Appointment g = new Appointment("fg", third, seventh);
        Appointment h = new Appointment("h", third, ninth);
        Appointment i = new Appointment("i", fourth, sixth);
        Appointment j = new Appointment("j", fifth, eigth);

        Appointment[] list = new Appointment[] {a, b, c, d, e, f, g, h, i, j };

        for (int ind = 0; ind < list.length - 1; ind++) {
            assertTrue(list[ind].compareTo(list[ind + 1]) <= 0);
        }

        assertTrue(f.compareTo(g) == 0);
    }

}
