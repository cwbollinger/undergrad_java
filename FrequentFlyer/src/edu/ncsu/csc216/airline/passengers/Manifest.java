package edu.ncsu.csc216.airline.passengers;

import java.util.ArrayList;

/**
 * Holds a list of alphabetically sorted FlightReservations, and provides
 * methods to add, remove, and access these reservations.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class Manifest {

	/** A list of FlightReservations, used to store the manifest. */
	private ArrayList<FlightReservation> passengerList;

	/**
	 * Initializes the list of passengers to an empty list.
	 */
	public Manifest() {
		passengerList = new ArrayList<FlightReservation>();
	}

	/**
	 * Removes a reservation from the list at the given index.
	 * 
	 * @param idx
	 *            The index of the reservation to be removed.
	 * @throws IllegalArgumentException
	 *             if index is not a valid location in the list.
	 */
	public void removePassenger(int idx) {
		try {
			passengerList.get(idx).cancelReservation();
			passengerList.remove(idx);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("No reservation selected.");
		}

	}

	/**
	 * Adds a new reservation to the list, and inserts it into the list in
	 * alphabetical order.
	 * 
	 * @param newPassenger The reservation to add to the list.
	 */
	public void add(FlightReservation newPassenger) {
		if(newPassenger == null) {
			//nothing to add, return
			return;
		}
		// if the list is empty, just add to the list
		if(passengerList.isEmpty()) {
			passengerList.add(newPassenger);
			return;
		}
		
		int idx = 0;
		for (FlightReservation p : passengerList) {
			if (p.compareTo(newPassenger) < 0) {
				idx++;
			}
		}
		passengerList.add(idx, newPassenger);

	}

	/**
	 * Returns a formatted string representing every reservation in Manifest separated by newline characters '\n'.
	 * @return a formatted 
	 */
	public String report() {
		String report = "";
		for (FlightReservation p : passengerList) {
			report += p.stringForPrint()+'\n';
		}
		return report;
	}

}
