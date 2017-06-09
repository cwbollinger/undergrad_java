package edu.ncsu.csc216.airline.airplane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parses a text file into a two dimensional array of Seat objects, and provides
 * methods to reserve seats on the aircraft. Implements the SeatManager
 * interface.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class Flight implements SeatManager {

	/**
	 * Defines the maximum percentage of Coach that will be filled before
	 * reservations are no longer assigned seats
	 */
	final static int COACH_CAP = 80;

	/**
	 * A two dimensional array of Seat objects representing every seat on the
	 * flight
	 */
	private Seat[][] map;

	/** The number of rows on the flight. */
	private int numRows;
	/** The number of columns across each row. */
	private int numColumns;
	/** The first row in the First Class section. */
	private int startFirstClass;
	/** The first row in the Business section. */
	private int startBusiness;
	/** The first row in the Coach section. */
	private int startCoach;
	/** The number of seats currently reserved in Coach. */
	private int coachSeatsFull;
	/** The row of the most recent coach reservation. */
	private int mostRecentCoachRow;
	/** The total number of seats in Coach. */
	private int coachCapacity;
	/** True if FirstClass and Business sections are combined, false otherwise. */
	private boolean twoSectionsCombined;

	private boolean allSectionsCombined;

	/**
	 * Constructs a new Flight based on the configuration stored in the file
	 * referenced by airplaneFileName.
	 * 
	 * @param airplaneFileName
	 *            The name of the file holding the airplane's seating
	 *            arrangement.
	 * @throws IllegalArgumentException
	 *             if there are problems parsing the airplane text file.
	 */
	public Flight(String airplaneFileName) {
		File f = new File(airplaneFileName);

		Scanner fileReader;
		try {
			fileReader = new Scanner(f);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}

		readFromFile(fileReader);

		mostRecentCoachRow = startCoach;
		// calculate coach capacity
		coachCapacity = 0;
		for (int i = 0; i < numRows; i++) {
			if (getRowNumber(i) >= startCoach) {
				for (int j = 0; j < numColumns; j++) {
					if (map[i][j] != null) {
						coachCapacity++;
					}
				}
			}
		}
	}

	/**
	 * Reserves a seat in First Class. If possible, it reserves a seat based on
	 * the passenger's seating preference. If this is not possible, it will
	 * reserve any available seat in First Class.
	 * 
	 * @param prefersWindow
	 *            The passenger's seat type preference. True if a window is
	 *            preferred, false if an aisle is preferred.
	 * 
	 * @return A string representation of the reserved seat's location. If no
	 *         seats are available, will return null.
	 */
	public String reserveFirstClassSeat(boolean prefersWindow) {

		// change which row is the last in First Class based on whether or not
		// First Class and Business sections are combined
		int endClass;
		if (allSectionsCombined) {
			endClass = map.length;
		} else if (twoSectionsCombined) {
			endClass = startCoach;
		} else {
			endClass = startBusiness;
		}

		String location = null;
		int rowNumber;

		// go through the seating chart, looking for specified seat type
		for (int i = 0; i < map.length; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startFirstClass && rowNumber < endClass) {
				for (int j = 0; j < numColumns; j++) {
					location = reservePreferredSeat(i, j, prefersWindow);
					if (location != null) {
						return location;
					}
				}
			}
		}

		// if unable to find the preferred seat, go through the seating chart
		// again, looking for any available seats
		for (int i = 0; i < map.length; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startFirstClass && rowNumber < endClass) {
				for (int j = 0; j < numColumns; j++) {
					location = reserveAnySeat(i, j);
					if (location != null) {
						return location;
					}
				}
			}
		}
		return location;
	}

	/**
	 * Reserves a seat in the Business section. If possible, it reserves a seat
	 * based on the passenger's seating preference. If this is not possible, it
	 * will reserve any available seat in Business.
	 * 
	 * @param prefersWindow
	 *            The passenger's seat type preference. True if a window is
	 *            preferred, false if an aisle is preferred.
	 * 
	 * @return A string representation of the reserved seat's location. If no
	 *         seats are available, will return null.
	 */
	public String reserveBusinessSeat(boolean prefersWindow) {
		int endClass;
		if (allSectionsCombined) {
			endClass = map.length;
		} else {
			endClass = startCoach;
		}

		String location = null;
		int rowNumber;

		// go through the seating chart, looking for specified seat type
		for (int i = 0; i < map.length; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startBusiness && rowNumber < endClass) {
				for (int j = 0; j < numColumns; j++) {
					location = reservePreferredSeat(i, j, prefersWindow);
					if (location != null) {
						return location;
					}
				}
			}
		}

		// go through the seating chart again, looking for any available seats
		for (int i = 0; i < map.length; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startBusiness && rowNumber < startCoach) {
				for (int j = 0; j < numColumns; j++) {
					location = reserveAnySeat(i, j);
					if (location != null) {
						return location;
					}
				}
			}
		}
		return location;
	}

	/**
	 * Reserves a seat in the Coach section. If possible, it reserves a seat
	 * based on the passenger's seating preference in the same row as the
	 * previous reservation. If this is not possible, it will reserve any
	 * available seat in Coach. Coach reservation will not be assigned a seat if
	 * 80% of Coach is already reserved.
	 * 
	 * @param prefersWindow
	 *            The passenger's seat type preference. True if a window is
	 *            preferred, false if an aisle is preferred.
	 * 
	 * @return A string representation of the reserved seat's location. If no
	 *         seats are available, will return null.
	 */
	public String reserveCoachSeat(boolean prefersWindow) {
		String location = null;
		int rowNumber = 0;
		int firstRow = 0;
		int startRow = 0;

		// go through the seating chart, looking for specified seat type
		for (int i = 0; i < map.length; i++) {
			if (getRowNumber(i) == startCoach) {
				firstRow = i;
			}
			if (getRowNumber(i) == mostRecentCoachRow) {
				startRow = i;
				break;
			}
		}
		for (int i = startRow; i < map.length; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startCoach) {
				for (int j = 0; j < numColumns; j++) {
					location = reservePreferredSeat(i, j, prefersWindow);
					if (location != null) {
						coachSeatsFull++;
						mostRecentCoachRow = getRowNumber(i);
						return location;
					}
				}
			}
		}
		// work through the part of coach that comes before mostRecentCoachRow
		for (int i = firstRow; i < startRow; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startCoach) {
				for (int j = 0; j < numColumns; j++) {
					location = reservePreferredSeat(i, j, prefersWindow);
					if (location != null) {
						coachSeatsFull++;
						mostRecentCoachRow = getRowNumber(i);
						return location;
					}
				}
			}
		}

		// go through the seating chart again, looking for any available seats
		for (int i = startRow; i < map.length; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startCoach) {
				for (int j = 0; j < numColumns; j++) {
					location = reserveAnySeat(i, j);
					if (location != null) {
						coachSeatsFull++;
						mostRecentCoachRow = getRowNumber(i);
						return location;
					}
				}
			}
		}
		// work through the part of coach that comes before mostRecentCoachRow
		for (int i = firstRow; i < startRow; i++) {
			rowNumber = getRowNumber(i);
			if (rowNumber >= startCoach) {
				for (int j = 0; j < numColumns; j++) {
					location = reserveAnySeat(i, j);
					if (location != null) {
						coachSeatsFull++;
						mostRecentCoachRow = getRowNumber(i);
						return location;
					}
				}
			}
		}
		return location;
	}

	/**
	 * Returns a two dimensional array of the location Strings of each seat in
	 * the flight.
	 * 
	 * @return a two dimensional array of the location of each seat in the
	 *         flight. If a given seat location is empty (an aisle), that
	 *         location will be set to null.
	 */
	public String[][] getSeatMap() {
		String[][] temp = new String[map.length][map[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if (map[i][j] == null) {
					temp[i][j] = null;
				} else {
					temp[i][j] = map[i][j].getLocation();
				}
			}
		}
		return temp;
	}

	/**
	 * Returns a two dimensional array of the occupancy of each seat in the
	 * flight.
	 * 
	 * @return a two dimensional array of the occupancy status of each seat in
	 *         the flight. If a given seat location is empty (an aisle), the
	 *         occupancy will be set to false.
	 */
	public boolean[][] getSeatOccupationMap() {
		boolean[][] temp = new boolean[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == null) {
					temp[i][j] = false;
				} else {
					temp[i][j] = map[i][j].isOccupied();
				}
			}
		}
		return temp;
	}

	/**
	 * Sets the occupancy of the given seat to false.
	 * 
	 * @param location
	 *            The location of the seat to be freed.
	 */

	public void freeSeat(String location) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if ((map[i][j] != null)
						&& (map[i][j].getLocation().equals(location))) {
					map[i][j].clear();
					if (getRowNumber(i) > startCoach) {
						coachSeatsFull--;
					}
				}
			}
		}
	}

	/**
	 * Returns whether the Coach section is at its cap occupancy.
	 * 
	 * @return True if Coach is at its cap, false otherwise.
	 */
	public boolean coachAtCap() {
		return (100.0 * getNumInCoach() / coachCapacity) > COACH_CAP;
	}

	/**
	 * Attempts to reserve the seat at the given location in map, based on its
	 * seat type.
	 * 
	 * @param row
	 *            The row in map, NOT the aircraft row number, that this seat is
	 *            located in.
	 * @param column
	 *            The column this seat is located in.
	 * @param prefersWindow
	 *            The passenger's seating preference.
	 * @return The location String of this seat if the seat is unoccupied and
	 *         matches the passenger's seat preference, null otherwise.
	 */
	private String reservePreferredSeat(int row, int column,
			boolean prefersWindow) {
		Seat seat = map[row][column];
		if (seat == null) {
			return null;
		} else if (!(seat.isWindowSeat() || seat.isAisleSeat())) {
			return null;
		} else if (seat.isWindowSeat() && !prefersWindow) {
			return null;
		} else if (seat.isAisleSeat() && prefersWindow) {
			return null;
		} else if (seat.isOccupied()) {
			return null;
		} else {
			seat.occupy();
			return seat.getLocation();
		}
	}

	/**
	 * Attempts to reserve the seat at the given location in map, regardless of
	 * seat type.
	 * 
	 * @param row
	 *            The row in map, NOT the aircraft row number, that this seat is
	 *            located in.
	 * @param column
	 *            The column this seat is located in.
	 * 
	 * @return The location String of this seat if the seat is unoccupied, null
	 *         otherwise.
	 */

	private String reserveAnySeat(int row, int column) {
		Seat seat = map[row][column];
		if ((seat != null) && !seat.isOccupied()) {
			seat.occupy();
			return seat.getLocation();
		} else {
			return null;
		}
	}

	/**
	 * Reads all relevant information from the provided scanner to construct a
	 * Flight.
	 * 
	 * @param scanner
	 *            A scanner reading from the file provided to the Flight
	 *            constructor.
	 */

	private void readFromFile(Scanner scanner) {
		numRows = Integer.valueOf(scanner.nextLine());
		String seatLabels = scanner.nextLine();
		int numAisles = Integer.valueOf(scanner.nextLine());
		numColumns = seatLabels.length() + numAisles;
		map = new Seat[numRows][numColumns];
		startFirstClass = Integer.valueOf(scanner.nextLine());
		startBusiness = Integer.valueOf(scanner.nextLine());
		startCoach = Integer.valueOf(scanner.nextLine());
		if ((startFirstClass == startBusiness)
				&& (startFirstClass == startCoach)) {
			allSectionsCombined = true;
			twoSectionsCombined = true;
		} else if (startFirstClass == startBusiness) {
			allSectionsCombined = false;
			twoSectionsCombined = true;
		} else {
			allSectionsCombined = false;
			twoSectionsCombined = false;
		}
		fillSeatMap(scanner);
	}

	/**
	 * Fills map[][] based on data read from the airplane config file.
	 * 
	 * @param scanner
	 *            A scanner reading from the file provided to the Flight
	 *            constructor.
	 */
	private void fillSeatMap(Scanner scanner) {
		String temp;
		ArrayList<String> row;
		int rowNumber = 0;
		int col;
		while (scanner.hasNextLine()) {
			row = new ArrayList<String>();
			temp = scanner.nextLine();
			for (col = 0; col < numColumns; col++) {
				if (col == 0) {
					row.add(temp.substring(0, 3).trim());
				} else {
					row.add(temp.substring(4 * col, 4 * col + 3).trim());
				}
			}

			for (col = 0; col < numColumns; col++) {

				if (row.get(col).equals("XXX")) { // aisle
					map[rowNumber][col] = null;

				} else if (col == 0 || col == numColumns - 1) {
					// it is a window seat, now see if it's an aisle seat too

					if (col == 0) { // left side of the plane
						if (row.get(col + 1).equals("XXX")) {
							// seat is next to an aisle
							map[rowNumber][col] = new Seat(row.get(col), true,
									true);
						} else {
							// not an aisle seat
							map[rowNumber][col] = new Seat(row.get(col), true,
									false);
						}
					} else if (col == numColumns - 1) { // right side of
														// the plane
						if (row.get(col - 1).equals("XXX")) {
							// seat is next to an aisle
							map[rowNumber][col] = new Seat(row.get(col), true,
									true);
						} else {
							// not an aisle seat
							map[rowNumber][col] = new Seat(row.get(col), true,
									false);
						}
					}
				} else { // not a window seat
					if (row.get(col - 1).equals("XXX")
							|| row.get(col + 1).equals("XXX")) {
						// seat is next to an aisle
						map[rowNumber][col] = new Seat(row.get(col), false,
								true);
					} else {
						// not an aisle seat
						map[rowNumber][col] = new Seat(row.get(col), false,
								false);
					}

				}
			}

			rowNumber++;
		}
	}

	/**
	 * Returns how many seats have been reserved in Coach.
	 * 
	 * @return the number of reserved Coach seats.
	 */
	private int getNumInCoach() {
		return coachSeatsFull;
	}

	/**
	 * Determines the row of the aisle within the airplane, versus within map.
	 * For example, if rows 7-10 are skipped in an airplane seating chart, row 8
	 * in map does not correspond to row 8 on the seating chart.
	 * 
	 * @param rowNumber
	 *            The row number used to index map
	 * @return The row number on the flight seating chart
	 */
	private int getRowNumber(int rowNumber) {
		int seatNotNull;
		for (seatNotNull = 0; seatNotNull < numColumns; seatNotNull++) {

			if (map[rowNumber][seatNotNull] != null) {
				break;
			}

			// if the whole row has been traversed and there are no seats,
			// this row is an aisle. Indicate this w/ a -1
			if (seatNotNull == numColumns - 1) {
				return -1;
			}

		}
		String location = map[rowNumber][seatNotNull].getLocation();

		return Integer.parseInt(location.substring(0, location.length() - 1));
	}

}
