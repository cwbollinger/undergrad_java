package edu.ncsu.csc216.movie_inventory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The MovieInventoryUI class is responsible for processing user input, creating
 * Movie objects, and storing them in MovieInventory.
 * 
 * @author Sarah Heckman (sarah_heckman@ncsu.edu)
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * @version 1.0
 * 
 */
public class MovieInventoryUI {

	/**
	 * Store all movies for MovieInventoryUI.
	 */
	private MovieInventory inventory;

	/**
	 * Used to process all user input from the console.
	 */
	private Scanner console;

	/**
	 * Constructs a MovieInventoryUI object that contains a MovieInventory and a
	 * Scanner for reading console input.
	 */
	public MovieInventoryUI() {
		console = new Scanner(System.in);
		inventory = new MovieInventory();
	}

	/**
	 * Controls the looping of the user interface. and decides which action
	 * should take place based on user input.
	 */
	public void userInterface() {
		String title;
		while (true) {
			printMenu();
			int promptNumber = mainMenuPrompt();
			if (promptNumber == 1) {
				displayInventory();
			} else if (promptNumber == 2) {
				promptForMovie();
			} else if (promptNumber == 3) {
				title = promptForMovieRemoval();
				if (inventory.removeMovie(title)) {
					System.out.println("Movie removed from the collection.");
				} else {
					System.out
							.println("Movie cannot be removed from the collection.");
				}
			} else if (promptNumber == 4) {
				System.exit(0);
			}
		}

	}

	/**
	 * Prints main menu prompt in the console.
	 */
	private void printMenu() {
		System.out.println(""); // padding menu
		System.out.println("MovieInventory Menu");
		System.out.println("1. List Movies");
		System.out.println("2. Add Movie");
		System.out.println("3. Remove Movie By Title");
		System.out.println("4. Quit");
	}

	/**
	 * Prompts the user for input, expecting an integer between 1 and 4
	 * inclusive. If the user inputs a value outside this range, or a
	 * non-integer value, this method will prompt the user recursively until a
	 * valid entry occurs.
	 * 
	 * @return An integer between 1 and 4 inclusive.
	 */
	private int mainMenuPrompt() {
		System.out.println("Entry? ");
		int promptNumber = 0;
		try {
			promptNumber = console.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid command");
			console.nextLine(); // flush user input
			mainMenuPrompt();
		}
		console.nextLine(); // flush user input

		if (promptNumber < 1 || promptNumber > 4) {
			System.out.println("Invalid command");
			mainMenuPrompt();
		}

		return promptNumber;

	}

	/**
	 * UI functionality for adding a Movie. Parses user input in the console and
	 * attempts to create a Movie object using this information. If successfully
	 * created, an attempt is made to add the Movie to inventory. This will
	 * occur if there is available space and a duplicate Movie is not already
	 * present.
	 * 
	 * @throws IllegalArgumentException
	 *             if a release date earlier than 1880 is entered.
	 * 
	 */
	public void promptForMovie() {
		System.out.println("\nTitle? ");
		String title = console.nextLine();

		int releaseYear = 0;
		while (releaseYear == 0) {
			System.out.println("Release Year? ");
			try {
				releaseYear = console.nextInt();
			} catch (InputMismatchException e) { // user input in non-numeric
				System.out.println("Invalid release year.");
			}

			console.nextLine(); // throw away rest of line
		}

		System.out.println("Genre? ");
		String genre = console.nextLine();

		System.out.println("Rating? ");
		String rating = console.nextLine();

		try {
			Movie m = new Movie(title, releaseYear, genre, rating);
			if (inventory.addMovie(m)) {
				System.out.println("Movie added to collection.");
			} else {
				System.out.println("Movie cannot be added to the collection.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid release year.");
		}
	}

	/**
	 * Prompts the user for the name of a Movie.
	 * 
	 * @return The name of the Movie to be removed from MovieInventory.
	 */
	public String promptForMovieRemoval() {
		System.out.println("\nTitle? ");
		String title = console.nextLine();

		return title;

	}

	/**
	 * Lists all movies present in this MovieInventory object, along with any
	 * empty slots in storage.
	 */
	public void displayInventory() {
		System.out.println("Movie Inventory:");
		for (int i = 0; i < MovieInventory.getInventorySize(); i++) {
			System.out.print((i + 1) + ". ");
			System.out.println(inventory.readMovie(i));
		}
	}

	/**
	 * Starts the MovieInventory program, initializes MovieInventoryUI and
	 * starts the UI loop.
	 * 
	 * @param args
	 *            command line args
	 */
	public static void main(String[] args) {
		MovieInventoryUI ui = new MovieInventoryUI();
		ui.userInterface();
	}
}
