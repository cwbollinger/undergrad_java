package edu.ncsu.csc216.string_analyzer;

import java.util.Scanner;

/** 
 * User interface for the StringAnalyzer 
 * program.
 * @author SarahHeckman
 */
public class StringAnalyzerUI {
	
	/** Constant string used to quit the program */
	private static final String QUIT_STRING = "quit";
	/** Constant string for error message */
	private static final String INVALID_BOUNDS = "Invalid bounds";
	
	/**
	 * Runs the user interface.
	 */
	public void run() {
		Scanner in = new Scanner(System.in);
		while (true) {
			//Process String input
			System.out.println("String? ");
			String s = in.nextLine();
			if (s.equals(QUIT_STRING)) {
				break;
			}
			
			int minValue = getIntegerValue("Minimum? ", in);
			int maxValue = getIntegerValue("Maximum? ", in);
			
			try {
				StringAnalyzer analyzer = new StringAnalyzer(s, minValue, maxValue);
				int digitCount = analyzer.countDigits();
				System.out.printf("The String contains %d digits between %d and %d, inclusive\n", digitCount, minValue, maxValue);
			} catch (IllegalArgumentException e) {
				System.out.println(INVALID_BOUNDS);
			}
		}
	}
	
	/**
	 * Helper method that will process integer input.  The
	 * method will loop the prompt until a valid integer 
	 * value is provided.
	 * @param prompt Prompt for the user
	 * @param in Console Scanner for processing user input
	 * @return a valid integer value (between MIN_INT and 
	 * MAX_INT, inclusive).
	 */
	private int getIntegerValue(String prompt, Scanner in) {
		while (true) {
			System.out.println(prompt);
			String valueString = in.nextLine();
			
			Scanner valueScanner = new Scanner(valueString);
			if (valueScanner.hasNextInt()) {
				int value = valueScanner.nextInt();
				if (value >= StringAnalyzer.MIN_DIGIT && value <= StringAnalyzer.MAX_DIGIT) {
					valueScanner.close();
					return value;
				} else {
					//Integer value is out of bounds
					System.out.println(INVALID_BOUNDS);
				}
			} else {
				//Input is not an int
				System.out.println(INVALID_BOUNDS);
			}
			valueScanner.close();
		}
	}
	
	public static void main(String[] args) {
		StringAnalyzerUI ui = new StringAnalyzerUI();
		ui.run();
	}

}
