package edu.ncsu.csc.csc216.state;

/**
 * Horner's Rule code is from Suzanne Balik's and 
 * Matthias Stallmann's Finite-State Machines handout.
 * 
 * The implementation of the State pattern for 
 * Horner's Rule comes from Dr. Ed Gehringer's notes
 *  
 * @author Dr. Sarah Heckman
 */
public class HornersRule {
	
	private final State start = new Start();
	private final State integer = new Integer();
	private final State decimal = new Decimal();
	private State state = start; //always start in the initial state
	
	double sign = 1;
	double value = 0;
	double power = 0.1;
	double multi = 10.0;
	
	char ch;
	
	/** 
	 * Parses the given string and returns the double
	 * value it represents.  If the String is invalid
	 * then 0.0 is returned.
	 * @param s String to parse into a double
	 * @return double value of the String or 0.0 if invalid
	 * @throws NumberFormatException if not a valid double
	 */
	public double toDouble(String s) {
		//reset values
		state = start;
		sign = 1.0;
		value = 0;
		power = 0.1;
		multi = 10.0;
		int i = 0; 
		while (i < s.length()) {
			ch = s.charAt(i++);
			if (ch == '.') {
				state.onPoint();
			} else if (ch == '+') {
				state.onPlus();
			} else if (ch == '-') {
				state.onMinus();
			} else if (Character.isDigit(ch)) {
				state.onDigit();
			} else {
				state.onOther();
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
			try {
				System.out.println(new HornersRule().toDouble(args[0]));
			} catch (NumberFormatException e) {
				System.out.println("Can't process value");
			}
		} else {
			System.out.println("Usage: java HornersRule <input>");
		}
	}
	
	/**
	 * Implementation of the Start state for the 
	 * Horner's Rule FSM
	 * @author SarahHeckman
	 */
	private class Start implements State {

		public void onDigit() {
			value = ch - '0';
			state = integer;
		}

		public void onMinus() {
			sign = -1.0;
			state = integer;
		}

		public void onOther() {
			throw new NumberFormatException();
		}

		public void onPlus() {
			state = integer;
		}

		public void onPoint() {
			state = decimal;
		}
		
	}
	
	/**
	 * Implementation of the Integer state for the 
	 * Horner's Rule FSM
	 * @author SarahHeckman
	 */
	private class Integer implements State {

		public void onDigit() {
			
		}

		public void onMinus() {
		}

		public void onOther() {
		}

		public void onPlus() {
		}

		public void onPoint() {
		}
		
	}
	
	/**
	 * Implementation of the Decimal state for the 
	 * Horner's Rule FSM
	 * @author SarahHeckman
	 */
	private class Decimal implements State {
		
		public void onDigit() {
		}

		public void onMinus() {
		}

		public void onOther() {
		}

		public void onPlus() {
		}

		public void onPoint() {
		}
		
	}

}
