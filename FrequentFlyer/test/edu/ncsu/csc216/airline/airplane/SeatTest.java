package edu.ncsu.csc216.airline.airplane;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Seat
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class SeatTest {

	/** A window seat */
	private Seat windowSeat;
	/** A test aisle seat. */
	private Seat aisleSeat;
	/** A seat that is both window and aisle. The best of all. */
	private Seat ultimateSeat;

	/**
	 * Creates 3 seats for use in testing.
	 * 
	 */
	@Before
	public void setUp() {
		windowSeat = new Seat("The Window", true, false);
		aisleSeat = new Seat("The Aisle", false, true);
		ultimateSeat = new Seat("The Ultimate Seat", true, true);
	}

	/** Tests that getLocation() returns the correct values. */
	@Test
	public void testGetLocation() {
		assertEquals("The Window", windowSeat.getLocation());
		assertEquals("The Aisle", aisleSeat.getLocation());
	}

	/** Tests that isOccupied() correctly returns the occupancy state of a seat. */
	@Test
	public void testIsOccupied() {
		// seat should initially be unoccupied
		assertFalse(windowSeat.isOccupied());

		// occupied seats should be occupied
		windowSeat.occupy();
		assertTrue(windowSeat.isOccupied());

		// a cleared seat should be unoccupied
		windowSeat.clear();
		assertFalse(windowSeat.isOccupied());

	}

	/**
	 * Tests isWindowSeat() for window seats, aisle seats, and seats that are
	 * both.
	 */
	@Test
	public void testIsWindowSeat() {
		assertTrue(windowSeat.isWindowSeat());
		assertFalse(aisleSeat.isWindowSeat());
		assertTrue(ultimateSeat.isWindowSeat());
	}

	/**
	 * Tests isAisleSeat() for window seats, aisle seats, and seats that are
	 * both.
	 */
	@Test
	public void testIsAisleSeat() {
		assertTrue(aisleSeat.isAisleSeat());
		assertFalse(windowSeat.isAisleSeat());
		assertTrue(ultimateSeat.isAisleSeat());
	}

}
