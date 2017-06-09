package edu.ncsu.csc.csc216.fsm;

/**
 * Horner's Rule code is from Suzanne Balik's and 
 * Matthias Stallmann's Finite-State Machines handout. 
 * @author Dr. Sarah Heckman
 */
public class HornersRule {
	
	/** 
	 * Parses the given string and returns the double
	 * value it represents.  If the String is invalid
	 * then 0.0 is returned.
	 * @param s String to parse into a double
	 * @return double value of the String or 0.0 if invalid
	 */
	public static double toDouble(String s) {
		double sign = 1; //sign of number
		double value = 0; //current value of number
		double power = 0.1; //power of 10 for decimal digits
		
		int i = 0; //index into string
		final int START = 0;
		final int INTEGER = 1;
		final int DECIMAL = 2;
		final int ERROR = 3;
		
		int state = START; //current state - always start at START state
		char ch; //current character
		
		while (state != ERROR && i < s.length()) {
			ch = s.charAt(i++); //Get the character at i then increment i
			switch (state) {
				case START: 
					if (ch == '.') {
						state = DECIMAL;
					} else if (ch == '-') {
						sign = -1.0;
						state = INTEGER;
					} else if (ch == '+') {
						state = INTEGER;
					} else if (Character.isDigit(ch)) {
						value = ch - '0';
						state = INTEGER;
					} else {
						state = ERROR;
					}
					break;
				case INTEGER:
					if (ch == '.') {
						state = DECIMAL;
					} else if (Character.isDigit(ch)) {
						value = 10.0 * value + (ch - '0');
					} else {
						value = 0.0;
						state = ERROR;
					}
					break;
				case DECIMAL: 
					if (Character.isDigit(ch)) {
						value += power * (ch - '0');
						power /= 10.0;
					} else {
						value = 0.0;
						state = ERROR;
					}
					break;
				default:
					System.out.println("Invalid state: " + state);
			}
			
		}
		return sign * value;
	}
	
	/**
	 * Starts the program.
	 * Usage: java HornersRule <input-to-parse>
	 * @param args command line arguments
	 */
	public static void main(String [] args) {
		if (args.length == 1) {
			System.out.println(toDouble(args[0]));
		} else {
			System.out.println("Usage: java HornersRule <input>");
		}
	}

}
