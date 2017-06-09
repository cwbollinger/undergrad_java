package edu.ncsu.csc216.airline.passengers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airline.airplane.Flight;

/**
 * Test class for FlightReservation
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class FlightReservationTest {

	/** A first class FlightReservation. */
	FlightReservation fc;
	/** A business FlightReservation. */
	FlightReservation b;
	/** A coach FlightReservation. */
	FlightReservation c;

	/** A flight to make reservations on. */
	Flight flight;

	/**
	 * Creates a Flight and 3 FlightReservations, one of each type.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws IOException {
		flight = new Flight("tiny-plane.txt");

		fc = new FirstClass("Alice", flight, true);
		b = new Business("Bob", flight, true);
		c = new Coach("Carol", flight, false);

	}

	/**
	 * Tests findSeat() and its ability to find a seat matching passenger
	 * preferences.
	 */
	@Test
	public void testFindSeat() {
		fc.findSeat();
		// seat matches first free First Class window seat in tiny plane
		assertEquals("1A", fc.getSeat());

		b.findSeat();
		// seat matches first free Business window seat in tiny plane
		assertEquals("1E", b.getSeat());

		c.findSeat();
		// seat matches first free Business window seat in tiny plane
		assertEquals("6C", c.getSeat());
	}

	/**
	 * Tests wantsWindowSeat() to confirm it returns the value of the passengers
	 * seat preference.
	 */
	@Test
	public void testWantsWindowSeat() {
		assertTrue(fc.wantsWindowSeat());
		assertTrue(b.wantsWindowSeat());
		assertFalse(c.wantsWindowSeat());
	}

	/**
	 * Tests stringForPrint() to confirm that it prints the ticket type followed
	 * by the seat reservation and the passenger's name.
	 */
	@Test
	public void testStringForPrint() {
		assertEquals("First Class  " + "none" + "  " + fc.getName(),
				fc.stringForPrint());
		assertEquals("Business     " + "none" + "  " + b.getName(),
				b.stringForPrint());
		assertEquals("Coach        " + "none" + "  " + c.getName(),
				c.stringForPrint());
	}

	/**
	 * Test that compareTo() properly compares different FlightReservation
	 * objects.
	 */

	@Test
	public void testCompareTo() {
		assertTrue((0 > fc.compareTo(b)));
		assertTrue((0 > b.compareTo(c)));
		assertTrue((0 < c.compareTo(fc)));
	}

	/**
	 * Tests that cancelReservation() properly removes reservations on a seat.
	 * 
	 */
	@Test
	public void testCancelReservation() {
		fc.mySeat = fc.getVehicle().reserveFirstClassSeat(true);
		String reservationSeat = fc.getSeat();

		assertEquals("1A", reservationSeat);

		// confirm that the seat is reserved
		assertTrue(flight.getSeatOccupationMap()[0][0]);

		fc.cancelReservation();

		// now confirm that the reservation is cancelled
		assertFalse(flight.getSeatOccupationMap()[0][0]);
	}
}
