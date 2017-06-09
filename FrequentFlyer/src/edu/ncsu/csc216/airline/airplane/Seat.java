/**
 * 
 */
package edu.ncsu.csc216.airline.airplane;

/**
 * Holds state information about an individual airplane seat.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class Seat {

	/**
	 * The seat's location on the aircraft. Format is row number followed by
	 * column letter
	 */
	private String location;
	/**
	 * The current occupancy status of the seat. True if occupied, false if not.
	 */
	private boolean occupied;
	/**
	 * Stores whether or not this seat is a window seat. True if the seat is
	 * next to a window, false otherwise.
	 */
	private boolean windowSeat;
	/**
	 * Stores whether or not this seat is an aisle seat. True if the seat is
	 * next to an aisle, false otherwise.
	 */
	private boolean aisleSeat;

	/**
	 * Constructs a new Seat object in the given location. Will be considered a
	 * window seat and/or an aisle seat based on the other parameters.
	 * 
	 * @param location The location of the seat on the plane.
	 * @param windowSeat Whether this seat is a window seat.
	 * @param aisleSeat Whether this seat is an aisle seat.
	 */
	public Seat(String location, boolean windowSeat, boolean aisleSeat) {
		this.location = location.trim();
		this.windowSeat = windowSeat;
		this.aisleSeat = aisleSeat;
		this.occupied = false;
	}

	/**
	 * Returns a string holding the location of the seat. Should be a number followed by a letter.
	 * 
	 * @return the location of the seat.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Returns whether the seat is occupied or not. True if occupied, false otherwise.
	 * 
	 * @return the seat's current occupancy status.
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Calling this method occupies the seat.
	 */
	public void occupy() {
		occupied = true;
	}
	
	/**
	 * Calling this method clears the seat's occupancy.
	 */

	public void clear() {
		occupied = false;
	}

	/**
	 * Returns true if this seat is a window seat, false otherwise.
	 * 
	 * @return Whether this seat is a window seat.
	 */
	public boolean isWindowSeat() {
		return windowSeat;
	}

	/**
	 * Returns true if this seat is an aisle seat, false otherwise.
	 * 
	 * @return Whether this seat is an aisle seat.
	 */
	public boolean isAisleSeat() {
		return aisleSeat;
	}

}
