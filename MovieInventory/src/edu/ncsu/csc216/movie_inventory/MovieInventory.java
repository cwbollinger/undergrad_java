package edu.ncsu.csc216.movie_inventory;

/***
 * The MovieInventory class stores a list of unique Movie objects. This list is
 * stored in an array, and is used by MovieInventoryUI to store user input.
 * 
 * @author Sarah Heckman (sarah_heckman@ncsu.edu)
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * @version 1.0
 * 
 */
public class MovieInventory {

	/**
	 * Defines the number of movies that can be stored in MovieInventory.
	 */
	private static final int INVENTORY_SIZE = 10;

	/**
	 * Holds all Movies in MovieInventory.
	 */
	private Movie[] movies;

	/**
	 * Constructs a MovieInventory object. Initializes movies as an array of
	 * Movie objects of size INVENTORY_SIZE.
	 */
	public MovieInventory() {

		movies = new Movie[INVENTORY_SIZE];

	}

	/**
	 * Returns true if the Movie can be added to the inventory. If the Movie is
	 * a duplicate or if there is no more space, the method returns false.
	 * 
	 * @param m
	 *            Movie to add to the inventory
	 * @return true if the Movie can be added to the inventory, false otherwise.
	 */
	public boolean addMovie(Movie m) {
		// Check for duplicate movies
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null && movies[i].equals(m)) {
				return false; // movie already exists
			}
		}
		boolean added = false; // flag
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] == null) { // the movie slot is empty
				movies[i] = m;
				added = true;
				break; // if we added, break out of the loop
			}
		}
		return added; // return the flag
	}

	/**
	 * Finds the movie at the ith position of movies[], and returns it's
	 * toString() representation.
	 * 
	 * @param i
	 *            The position in movies[] of the movie to read. Should be a
	 *            number between 0 and INVENTORY_SIZE-1, inclusive.
	 * @return The toString representation of the Movie at position i in
	 *         movies[]
	 */
	public String readMovie(int i) {
		if (i >= 0 && i < INVENTORY_SIZE) {
			if (movies[i] != null) {
				return movies[i].toString();
			} else {
				return "Empty";
			}
		}
		return "ERROR: ARRAY INDEX OUT OF BOUNDS";
	}

	/**
	 * Gets the storage size of this MovieInventory.
	 * 
	 * @return INVENTORY_SIZE, a constant which determines the number of movies
	 *         that can be stored in MovieInventory.
	 */
	public static int getInventorySize() {
		return INVENTORY_SIZE;
	}

	/**
	 * Removes the first movie in movies[] whose title matches the parameter.
	 * Will not do anything to the list if there is no matching title.
	 * 
	 * @param title
	 *            Title of the movie to removed.
	 * @return true if the movie is successfully removed, false otherwise.
	 */
	public boolean removeMovie(String title) {

		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null && movies[i].getTitle().equals(title)) {
				movies[i] = null;
				return true;
			}
		}
		return false;

	}

}
