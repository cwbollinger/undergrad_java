package edu.ncsu.csc.csc216.state;

/**
 * Interface for the Horner's Rule FSM.
 * A state needs to handle the following input:
 * "+", "-", ".", [0-9], and all other possible 
 * input.
 * 
 * The implementation of the State pattern for 
 * Horner's Rule comes from Dr. Ed Gehringer's notes
 * 
 * @author Dr. Sarah Heckman
 */
public interface State {
	
	/** 
	 * Handle "+" input
	 */
	void onPlus();
	/**
	 * Handle "-" input
	 */
	void onMinus();
	/** 
	 * Handle "." input
	 */
	void onPoint();
	/**
	 * Handle numeric input - the digits 0-9
	 */
	void onDigit();
	/**
	 * Handle all other input other than those
	 * listed above
	 */
	void onOther();

}
