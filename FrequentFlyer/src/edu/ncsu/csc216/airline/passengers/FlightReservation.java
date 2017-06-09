package edu.ncsu.csc216.airline.passengers;

import edu.ncsu.csc216.airline.airplane.Flight;

/**
 * Models a reservation consisting of a passenger and their reserved seat.
 * Contains methods to access the reservations seat, which flight they are on,
 * the passenger's name, and their seating preference.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public abstract class FlightReservation {

	/** The name of the passenger making the reservation. */
	private String name;
	/** The location of the reserved seat. Null if no seat reserved. */
	protected String mySeat;
	/**
	 * The seating preference of the passenger. True if window seat preferred,
	 * false if aisle seat preferred.
	 */
	private boolean prefersWindow;
	/** The flight that the reservation is being made on. */
	private Flight myAirplane;

	/**
	 * Constructs a FlightReservation using the given parameters.
	 * 
	 * @param name
	 *            The name of the passenger.
	 * @param myFlight
	 *            The flight the reservation is being made on.
	 * @param wantsWindow
	 *            The seating preference of the passenger.
	 */
	public FlightReservation(String name, Flight myFlight, boolean wantsWindow) {
		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Passenger name cannot be blank.");
		}
		this.name = name.trim();

		if (myFlight == null) {
			throw new IllegalArgumentException();
		}
		this.myAirplane = myFlight;
		this.prefersWindow = wantsWindow;

	}

	/**
	 * An abstract method for finding a seat matching the passenger's
	 * preferences. Implemented by child classes.
	 */
	public abstract void findSeat();

	/**
	 * Returns the passenger's name.
	 * 
	 * @return The name of the passenger.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the location of the reserved seat.
	 * 
	 * @return The reserved seat's location, or null if no seat has been
	 *         reserved.
	 */
	public String getSeat() {
		return mySeat;
	}

	/**
	 * The seating preference of the passenger.
	 * 
	 * @return The passenger's seating preference, true if window preferred,
	 *         false if aisle preferred.
	 */
	public boolean wantsWindowSeat() {
		return prefersWindow;
	}

	/**
	 * Returns the reservation's flight.
	 * 
	 * @return the flight the reservation is being made for.
	 */
	public Flight getVehicle() {
		return myAirplane;
	}

	/**
	 * Returns a formatted String which displays the reserved seat and the name
	 * of the passenger.
	 * 
	 * @return a String containing the seat location and name of the passenger.
	 */
	public String stringForPrint() {
		String temp = "";

		if (mySeat == null) {
			temp += "none";
		} else {
			int padding = 4 - mySeat.length();
			for (; padding > 0; padding--) {
				temp += " ";
			}
			temp += mySeat;
		}
		temp += "  " + name;
		return temp;
	}

	/**
	 * Compares two FlightReservations. Equality is based on passenger names,
	 * and case is ignored.
	 * 
	 * @param reservation
	 *            The reservation to compare to this reservation.
	 * @return value < 0 if reservation is ordered after this reservation, value
	 *         > 0 if reservation is ordered before this reservation, value = 0
	 *         if reservation is ordered equally to this reservation. Exact
	 *         value is determined lexicographically based on
	 *         String.compareToIgnoreCase()
	 */
	public int compareTo(FlightReservation reservation) {
		return name.compareToIgnoreCase(reservation.name);
	}

	/**
	 * Frees seat associated with this reservation.
	 */
	public void cancelReservation() {
		myAirplane.freeSeat(mySeat);
		mySeat = null;
	}

}
