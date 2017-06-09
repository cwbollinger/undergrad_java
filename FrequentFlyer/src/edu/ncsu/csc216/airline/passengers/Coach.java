package edu.ncsu.csc216.airline.passengers;

import edu.ncsu.csc216.airline.airplane.Flight;

/**
 * Represents a reservation for a passenger with a Coach ticket.
 * 
 * @author Chris Bollinger(cwbollin@ncsu.edu)
 * 
 */
public class Coach extends FlightReservation {

	/**
	 * Constructs a Coach reservation. Passes all parameters to the
	 * FlightReservation constructor.
	 * 
	 * @param name
	 *            Name of the passenger.
	 * @param myFlight
	 *            The flight that the reservation is for.
	 * @param wantsWindow
	 *            The seating preference of the passenger.
	 */
	public Coach(String name, Flight myFlight, boolean wantsWindow) {
		super(name, myFlight, wantsWindow);

	}

	/**
	 * Finds the seat that most closely matches the passengers preferences as
	 * possible. If a seat is not available, will set mySeat to null.
	 */
	public void findSeat() {
		if(!super.getVehicle().coachAtCap()) { // if the coach isn't full
			mySeat = super.getVehicle().reserveCoachSeat(super.wantsWindowSeat());
		} else {
			mySeat = null; // if coach is full, no assigned seat
		}
		
	}

	/**
	 * Returns a formatted string which appends "Coach" onto the front of the
	 * stringForPrint() method of FlightReservation.
	 * 
	 * @return formatted string displaying "Coach", the seat location, and
	 *         the passenger name.
	 */
	public String stringForPrint() {
		String reservation = "Coach        " + super.stringForPrint();
		return reservation;
	}

}
