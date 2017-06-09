/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Currency.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class CurrencyTest {

	/** Currency object with the value of a penny */
	private Currency penny;
	/** Currency object with the value of a dollar */
	private Currency dollar;

	/**
	 * Sets up the CurrencyTest by creating two representative Currency objects:
	 * one with the value of a penny and the other with the value of a dollar.
	 */
	@Before
	public void setUp() throws Exception {
		penny = new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, 10);
		dollar = new Currency(CurrencyCollection.ONE_VALUE,
				CurrencyCollection.ONE_NAME, 10);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#hashCode()}
	 * . Tests that the hash codes of identical objects are the same.
	 */
	@Test
	public void testHashCode() {
		// Create a new penny object that has the same state as our
		// field named penny
		Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, 10);

		// Assert that both of these objects have the same has code.
		// When using assertTrue, the expected value is true. The actual
		// value is the result of the argument.
		assertTrue(penny.hashCode() == penny2.hashCode());

		// Assert that the penny and dollar objects have different
		// hashcodes.
		assertTrue(penny.hashCode() != dollar.hashCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getValue()}
	 * .
	 * 
	 * The test determines if the value returned from the Currency object is the
	 * same as what the value was initialized to.
	 */
	@Test
	public void testGetValue() {
		// The PENNY_VALUE constant is our expected value
		// The actual value is the call to the getValue() method
		assertEquals(CurrencyCollection.PENNY_VALUE, penny.getValue());

		// The ONE_VALUE constant is our expected value
		// The actual value is the call to the getValue method
		assertEquals(CurrencyCollection.ONE_VALUE, dollar.getValue());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getName()}.
	 * 
	 * The test determines if the name of a Currency object is the same as what
	 * it was initialized to.
	 */
	@Test
	public void testGetName() {

		// The PENNY_NAME constant is our expected string
		// The actual string is the call to the getName() method
		assertEquals(CurrencyCollection.PENNY_NAME, penny.getName());

		// The ONE_NAME constant is our expected string
		// The actual string is the call to the getName method
		assertEquals(CurrencyCollection.ONE_NAME, dollar.getName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getCount()}
	 * .
	 * 
	 * The test determines if the Currency's count is the same as the counter's
	 * initial value.
	 */
	@Test
	public void testGetCount() {
		// penny was initialized to a count of 10, check against current count.
		assertEquals(10, penny.getCount());
		// dollar was initialized to a count of 10, check against current count.
		assertEquals(10, dollar.getCount());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.Currency#modifyCount(int)}.
	 * 
	 * The test uses the getCount() method to understand how the count is
	 * changed when using the modifyCount() method
	 */
	@Test
	public void testModifyCount() {
		// Ensure we're starting with 10 pennies
		// We can't assume that the getCount() method has been tested at this
		// point
		// so this is our sanity check that we can use the getCount() method to
		// evaluate
		// modifyCount()
		// 10 is our expected value and penny.getCount() is our actual value
		assertEquals(10, penny.getCount());

		// Increase the count of pennies by 3
		penny.modifyCount(3);

		// Check that the count changed
		assertEquals(13, penny.getCount());

		// Decrease the count of pennies by 5
		penny.modifyCount(-5);

		// Check that the count changed
		assertEquals(8, penny.getCount());

		try {
			// Decrease the count of pennies by 9
			penny.modifyCount(-9);
			fail(); // We should never reach this point, if we do, the test
					// fails
		} catch (IllegalArgumentException e) {
			// Check that the count did NOT change
			assertEquals(8, penny.getCount());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.Currency#equals(java.lang.Object)}.
	 * 
	 * Tests that identical objects are considered equal, and non-identical
	 * objects are considered not equal.
	 */
	@Test
	public void testEqualsObject() {
		// Create a new penny object that has the same state as our
		// field named penny
		Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, 10);

		// Assert that both of these objects are the same using the
		// equals method.
		// When using assertTrue, the expected value is true. The actual
		// value is the result of the argument.
		assertTrue(penny.equals(penny2));

		// Assert that the penny and dollar objects are not the same.
		// When using assertFalse, the expected value is false. The actual
		// value is the result of the argument.
		assertFalse(penny.equals(dollar));
	}

}
