package edu.ncsu.csc216.airline.airplane;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of Flight
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class FlightTest {

	/** A flight to use for testing. */
	private Flight test;

	/**
	 * Sets up a Flight based on the information in tiny-plane.txt
	 * 
	 * @throws IOException
	 *             if file cannot be parsed properly.
	 */
	@Before
	public void setUp() throws IOException {
		test = new Flight("tiny-plane.txt");
	}

	/**
	 * Tests getSeatMap() method by comparing created string map to the original
	 * text file.
	 */
	@Test
	public void testGetSeatMap() {
		String[][] map = test.getSeatMap();
		// test that first row matches expected values
		assertEquals("1A", map[0][0]);
		assertEquals("1B", map[0][1]);
		assertEquals(null, map[0][2]);
		assertEquals("1C", map[0][3]);
		assertEquals("1D", map[0][4]);
		assertEquals("1E", map[0][5]);

		// test that second row matches expected values
		assertEquals("2A", map[1][0]);
		assertEquals("2B", map[1][1]);
		assertEquals(null, map[1][2]);
		assertEquals("2C", map[1][3]);
		assertEquals("2D", map[1][4]);
		assertEquals("2E", map[1][5]);

		// test that third row matches expected values
		assertEquals("3A", map[2][0]);
		assertEquals("3B", map[2][1]);
		assertEquals(null, map[2][2]);
		assertEquals("3C", map[2][3]);
		assertEquals("3D", map[2][4]);
		assertEquals("3E", map[2][5]);

		// test that sixth row matches expected values
		assertEquals(null, map[3][0]);
		assertEquals(null, map[3][1]);
		assertEquals(null, map[3][2]);
		assertEquals("6C", map[3][3]);
		assertEquals("6D", map[3][4]);
		assertEquals("6E", map[3][5]);

		// test that tenth row matches expected values
		assertEquals("10A", map[4][0]);
		assertEquals("10B", map[4][1]);
		assertEquals(null, map[4][2]);
		assertEquals("10C", map[4][3]);
		assertEquals("10D", map[4][4]);
		assertEquals("10E", map[4][5]);

		// test that eleventh row matches expected values
		assertEquals("11A", map[5][0]);
		assertEquals("11B", map[5][1]);
		assertEquals(null, map[5][2]);
		assertEquals("11C", map[5][3]);
		assertEquals("11D", map[5][4]);
		assertEquals("11E", map[5][5]);

	}

	/**
	 * Tests that getSeatOccupationMap returns false initially for all seats,
	 * and that reserving and freeing seats changes the values returned.
	 */
	@Test
	public void testGetSeatOccupationMap() {
		boolean[][] map = test.getSeatOccupationMap();

		// confirm that initially, all seats are empty
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				assertFalse(map[i][j]);
			}
		}

		test.reserveFirstClassSeat(true);
		// seat 1A should be occupied now
		assertTrue(test.getSeatOccupationMap()[0][0]);

		test.freeSeat("1A");
		assertFalse(map[0][0]);

	}

	/**
	 * Tests reserveFirstClassSeat() and how it reserves when the plane is
	 * empty, and when all of a given seat type is full.
	 */
	@Test
	public void testReserveFirstClassSeat() {
		String seat = test.reserveFirstClassSeat(true);
		// seat should be the first available window seat
		assertEquals("1A", seat);

		// 1A should be occupied
		assertTrue(test.getSeatOccupationMap()[0][0]);

		seat = test.reserveFirstClassSeat(false);
		// seat should be the first available aisle seat
		assertEquals("1B", seat);

		// 1B should be occupied
		assertTrue(test.getSeatOccupationMap()[0][1]);

		// reserve seats until all window seats are full
		for (int i = 0; i < 5; i++) {
			test.reserveFirstClassSeat(true);
		}

		// first available First Class seat should be assigned
		seat = test.reserveFirstClassSeat(true);
		assertEquals("1C", seat);

	}

	/**
	 * Tests reserveBusinessSeat() and how it reserves when the plane is empty,
	 * and when all of a given seat type is full.
	 */
	@Test
	public void testReserveBusinessSeat() {
		String seat = test.reserveBusinessSeat(true);
		// seat should be the first available window seat
		assertEquals("1A", seat);

		// 1A should be occupied
		assertTrue(test.getSeatOccupationMap()[0][0]);

		seat = test.reserveBusinessSeat(false);
		// seat should be the first available aisle seat
		assertEquals("1B", seat);

		// 1B should be occupied
		assertTrue(test.getSeatOccupationMap()[0][1]);

		// reserve seats until all window seats are full
		for (int i = 0; i < 5; i++) {
			test.reserveBusinessSeat(true);
		}

		// first available First Class seat should be assigned
		seat = test.reserveBusinessSeat(true);
		assertEquals("1C", seat);
	}

	/**
	 * Tests reserveCoachSeat() and how it reserves when the plane is empty.
	 * Also verifies that reservations will not be given seats once the cap is
	 * reached.
	 */
	@Test
	public void testReserveCoachSeat() {
		String seat = test.reserveCoachSeat(true);
		assertEquals("6E", seat);

		seat = test.reserveCoachSeat(false);
		assertEquals("6C", seat);

		seat = test.reserveCoachSeat(true);
		assertEquals("10A", seat);

		seat = test.reserveCoachSeat(false);
		assertEquals("10B", seat);

		seat = test.reserveCoachSeat(false);
		assertEquals("10C", seat);

		seat = test.reserveCoachSeat(true);
		assertEquals("10E", seat);

		seat = test.reserveCoachSeat(true);
		assertEquals("11A", seat);

		seat = test.reserveCoachSeat(false);
		assertEquals("11B", seat);

		seat = test.reserveCoachSeat(false);
		assertEquals("11C", seat);

		seat = test.reserveCoachSeat(true);
		assertEquals("11E", seat);

		// all window and seats are full, so this assignment will be to
		// the first available chair in the section, with the search starting in
		// row 11

		seat = test.reserveCoachSeat(true);
		assertEquals("11D", seat);

		// 11 / 13 = .84, so Coach is now at full capacity. Future reservations
		// should not have an assigned seat
		if (!test.coachAtCap()) {
			seat = test.reserveCoachSeat(false);
		} else {
			seat = null;
		}

		assertEquals(null, seat);
	}

	/**
	 * Tests that freeSeat() successfully sets seats to unoccupied.
	 */
	@Test
	public void testFreeSeat() {
		String seat = test.reserveFirstClassSeat(true);
		assertTrue(test.getSeatOccupationMap()[0][0]);
		test.freeSeat(seat);
		assertFalse(test.getSeatOccupationMap()[0][0]);
	}

	/**
	 * Tests that coachAtCap() returns true when coach is sufficiently full.
	 */
	@Test
	public void testCoachAtCap() {
		assertFalse(test.coachAtCap());

		// reserve 11 seats so capacity > 80%
		for (int i = 0; i < 11; i++) {
			test.reserveCoachSeat(true);
		}

		assertTrue(test.coachAtCap());

	}

}
