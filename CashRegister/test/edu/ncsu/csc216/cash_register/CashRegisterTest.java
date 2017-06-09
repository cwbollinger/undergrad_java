/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for CashRegister.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class CashRegisterTest {

	/** A standard CashRegister object */
	private CashRegister register1;
	/** A standard CashRegister object */
	private CashRegister register2;
	/** A CurrencyCollection with one of each currency type */
	private CurrencyCollection collection1;

	/**
	 * Initializes all test variables before each test to their expected values.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		register1 = new CashRegister();
		register2 = new CashRegister();
		collection1 = new CurrencyCollection(1);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CashRegister#getCurrentBalance()}.
	 * 
	 * Tests that getCurrenctBalance() properly returns the register's balance
	 * before and after balance changes.
	 */
	@Test
	public void testGetCurrentBalance() {
		// register1 has 10 of each currency type. This means that its value is
		// equal to 10 * (20 + 10 + 5 + 1 + .25 + .10 + .05 + .01) = 364.1.
		// However,
		// balance is stored as an int, so the expected int value is 100x this,
		// or 36410
		assertEquals(36410, register1.getCurrentBalance());

		// empty register2
		register2.processRefund(register2.getCurrentBalance());

		// balance should change
		assertEquals(0, register2.getCurrentBalance());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CashRegister#processPurchase(int, edu.ncsu.csc216.cash_register.CurrencyCollection)}
	 * .
	 * 
	 * Tests that making a purchase increases the balance of the register,
	 * decreases the balance of the change given, and that it is not possible to
	 * buy something with insufficient funds.
	 */
	@Test
	public void testProcessPurchase() {
		// purchase amount is less that change
		int startingBalance = register1.getCurrentBalance();
		register1.processPurchase(100, collection1);
		// register balance should increase by 100
		assertEquals(startingBalance + 100, register1.getCurrentBalance());
		int collectionValue = collection1.getBalance();
		// purchase amount is equal to change
		register1.processPurchase(collectionValue, collection1);
		// register balance should increase by 36310
		assertEquals(startingBalance + 100 + collectionValue,
				register1.getCurrentBalance());

		// purchase amount is greater than change (collection1 is empty)
		try {
			register1.processPurchase(1000, collection1);
		} catch (IllegalArgumentException e) {
			// expected exception, continue
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CashRegister#processRefund(int)}.
	 * 
	 * Tests that the proper amount is refunded, and that a refund will not go
	 * through if there are insufficient funds in the register.
	 */
	@Test
	public void testProcessRefund() {

		// the proper amount should be refunded
		CurrencyCollection refund = register1.processRefund(100);
		assertEquals(100, refund.getBalance());

		// an error should be thrown if there isn't enough money to register to
		// refund
		try {
			register1.processRefund(1000000);
			fail(); // should throw exception
		} catch (IllegalArgumentException e) {
			// expected result, continue
		}

	}

}
