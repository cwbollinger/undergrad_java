package edu.ncsu.csc216.airline.passengers;

import edu.ncsu.csc216.airline.airplane.Flight;

/**
 * Represents a reservation for a passenger with a FirstClass ticket.
 * 
 * @author Chris Bollinger(cwbollin@ncsu.edu)
 * 
 */
public class FirstClass extends FlightReservation {

	/**
	 * Constructs a FirstClass reservation. Passes all parameters to the
	 * FlightReservation constructor.
	 * 
	 * @param name
	 *            Name of the passenger.
	 * @param myFlight
	 *            The flight that the reservation is for.
	 * @param wantsWindow
	 *            The seating preference of the passenger.
	 */
	public FirstClass(String name, Flight myFlight, boolean wantsWindow) {
		super(name, myFlight, wantsWindow);

	}

	/**
	 * Finds the seat that most closely matches the passengers preferences as
	 * possible. If a business seat is not available, will look in Coach for a
	 * seat. If no seat is found, will return null.
	 */
	public void findSeat() {
		mySeat = super.getVehicle().reserveFirstClassSeat(
				super.wantsWindowSeat());

		// if no seat was available in First Class, check Business
		if (mySeat == null) {
			mySeat = super.getVehicle().reserveBusinessSeat(
					super.wantsWindowSeat());
		}

		// if no seat was available in Business, check Coach
		if (mySeat == null) {
			mySeat = super.getVehicle().reserveCoachSeat(
					super.wantsWindowSeat());
		}

	}

	/**
	 * Returns a formatted string which appends "First Class" onto the front of the
	 * stringForPrint() method of FlightReservation.
	 * 
	 * @return formatted string displaying "First Class", the seat location, and
	 *         the passenger name.
	 */
	public String stringForPrint() {
		String reservation = "First Class  " + super.stringForPrint();
		return reservation;
	}

}