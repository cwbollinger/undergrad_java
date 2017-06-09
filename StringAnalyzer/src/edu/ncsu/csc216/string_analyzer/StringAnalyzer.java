package edu.ncsu.csc216.string_analyzer;

/**
 * Maintains the string and min/max digit information
 * to analyze.  The class provides the analysis functionality
 * for the current data.
 * @author SarahHeckman
 */
public class StringAnalyzer {
	
	/** Smallest digit */
	public  static final int MIN_DIGIT = 0;
	/** Largest digit */
	public  static final int MAX_DIGIT = 9;
	
	/** String to analyze */
	private String s;
	/** Minimum digit value to search for */
	private int minValue;
	/** Maximum digit value to search for */
	private int maxValue;
	
	/**
	 * Constructs a StringAnalyzer object.  Parameters
	 * are checked that they are valid.  If not, an
	 * IllegalArgumentException is thrown.
	 * @param s the String to analyze
	 * @param minValue the min digit to search for
	 * @param maxValue the max digit to search for
	 */
	public StringAnalyzer(String s, int minValue, int maxValue) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (minValue < MIN_DIGIT || minValue > MAX_DIGIT) {
			throw new IllegalArgumentException();
		}
		if (maxValue < MIN_DIGIT || maxValue > MAX_DIGIT) {
			throw new IllegalArgumentException();
		}
		if (maxValue < minValue) {
			throw new IllegalArgumentException();
		}
		this.s = s; 
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	/**
	 * Returns the number of digits that are between
	 * the minValue and maxValue, inclusive, for the 
	 * given string
	 */
	public int countDigits() {
		int digitCount = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				int v = Character.getNumericValue(c);
				if (v >= minValue && v <= maxValue) {
					digitCount++;
				}
			}
		}
		return digitCount;
	}

	/**
	 * Returns String under analysis
	 * @return String under analysis
	 */
	public String getString() {
		return s;
	}

	/**
	 * Returns the min digit value.
	 * @return the minValue
	 */
	public int getMinValue() {
		return minValue;
	}

	/**
	 * Returns the max digit value.
	 * @return the maxValue
	 */
	public int getMaxValue() {
		return maxValue;
	}

}
