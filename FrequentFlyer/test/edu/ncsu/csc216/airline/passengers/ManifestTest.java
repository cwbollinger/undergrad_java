package edu.ncsu.csc216.airline.passengers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airline.airplane.Flight;

/**
 * Test class for Manifest.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class ManifestTest {

	/** A manifest for testing. */
	Manifest reservations;
	/** An empty manifest. */
	Manifest emptyList;
	/** A flight to initialize the manifests. */
	Flight testFlight;

	/**
	 * Initializes two manifests, one empty and the other with three
	 * reservations, one in each section.
	 * 
	 * @throws IOException
	 *             if Flight is passed a bad file.
	 */
	@Before
	public void setUp() throws IOException {
		testFlight = new Flight("tiny-plane.txt");
		reservations = new Manifest();
		reservations.add(new Business("Boris", testFlight, false));
		reservations.add(new Coach("Mary", testFlight, false));
		reservations.add(new FirstClass("Davy", testFlight, true));
		emptyList = new Manifest();
	}

	/**
	 * Tests removePassenger() to ensure that reservations are actually removed,
	 * and that an exception is thrown when an invalid index is passed in.
	 */
	@Test
	public void testRemovePassenger() {
		// remove the first reservation
		reservations.removePassenger(0);

		// attempt to remove a passenger from an illegal position
		try {
			reservations.removePassenger(-1);
			fail("illegal index");

		} catch (IllegalArgumentException e) {
			// good, this is expected
			// make sure that reservations still has 2 passengers
			assertEquals(2, ennumerateReservations(reservations));
		}

		// remove other passengers
		reservations.removePassenger(0);
		reservations.removePassenger(0);

		// attempt to remove passenger from an empty list
		try {
			reservations.removePassenger(0);
			fail("illegal index");

		} catch (IllegalArgumentException e) {
			// good, this is expected
			// make sure that reservations is still empty
			assertEquals(0, ennumerateReservations(reservations));
		}

	}

	/**
	 * Tests add() to ensure that the number of reservations increases when one
	 * is added.
	 */
	@Test
	public void testAdd() {
		// add a passenger
		reservations.add(new Coach("Billy", testFlight, false));

		// check that number of reservations has increased
		assertEquals(4, ennumerateReservations(reservations));

		// add a passenger
		reservations.add(new Coach("Jack", testFlight, false));

		// check that number of reservations has increased
		assertEquals(5, ennumerateReservations(reservations));

	}

	/**
	 * Tests that report() returns a newline separated list of all the
	 * reservations in a manifest.
	 */
	@Test
	public void testReport() {
		assertEquals("", emptyList.report());
		FlightReservation f = new Coach("Jim", testFlight, false);
		emptyList.add(f);
		assertEquals(f.stringForPrint() + '\n', emptyList.report());
		FlightReservation g = new Coach("Kevin", testFlight, false);
		emptyList.add(g);
		assertEquals(f.stringForPrint() + '\n' + g.stringForPrint() + '\n',
				emptyList.report());
	}

	/**
	 * Calculates the number of reservations in a manifest based on the number
	 * of newlines in report().
	 * 
	 * @param m
	 *            The manifest to be analyzed.
	 * @return The number of reservations in m.
	 */
	private int ennumerateReservations(Manifest m) {
		String report = m.report();

		int count = 0;
		int n = report.indexOf('\n');
		while (n != -1) {
			count++;
			n = report.indexOf('\n', n + 1);
		}

		return count;
	}

}
