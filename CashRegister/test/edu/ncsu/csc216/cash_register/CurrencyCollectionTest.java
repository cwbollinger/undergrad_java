/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for CurrencyCollection.
 * 
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * 
 */
public class CurrencyCollectionTest {

	/** an empty currency collection. */
	private CurrencyCollection collection1;
	/** a currency collection with ten of each currency. */
	private CurrencyCollection collection2;
	/** a currency collection with one of each currency. */
	private CurrencyCollection collection3;

	/**
	 * Creates some CurrencyCollection objects to be tested.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		collection1 = new CurrencyCollection(); // empty collection
		collection2 = new CurrencyCollection(10);
		collection3 = new CurrencyCollection(1);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyAtIdx(int)}
	 * . Checks boundary conditions on both ends of the array.
	 */
	@Test
	public void testGetCurrencyAtIdx() {
		// check that position 0 works correctly
		assertEquals(new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, 0),
				collection1.getCurrencyAtIdx(0));

		// check that final position works correctly
		assertEquals(new Currency(CurrencyCollection.TWENTY_VALUE,
				CurrencyCollection.TWENTY_NAME, 0),
				collection1.getCurrencyAtIdx(CurrencyCollection.NUM_SLOTS - 1));

		try { // attempt to index a negative value
			collection1.getCurrencyAtIdx(-1);
			fail("Illegal index"); // should throw an exception
		} catch (IndexOutOfBoundsException e) {
			// expected result, continue test
		}

		// attempt to index beyond the end of the array.
		try {
			collection1.getCurrencyAtIdx(CurrencyCollection.NUM_SLOTS);
			fail(); // should throw an exception
		} catch (IndexOutOfBoundsException e) {
			// expected result, continue test
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#modifyDenomination(int, int)}
	 * .
	 * 
	 * Tests that modifying the denomination of a collection changes its
	 * balance, and that it is not possible to modify nonexistent currencies or
	 * cause the collection to have a negative number of a certain currency.
	 */
	@Test
	public void testModifyDenomination() {

		// collection1 has nothing in it
		assertEquals(0, collection1.getBalance());

		// modifying the collection should change its value
		collection1.modifyDenomination(CurrencyCollection.ONE_VALUE, 1);
		assertEquals(100, collection1.getBalance());

		// Test illegal negative value
		try {
			collection1.modifyDenomination(-12, 5);
			fail(); // should throw exception
		} catch (IllegalArgumentException e) {
			// expected result, continue
		}

		// Test illegal high value
		try {
			collection1.modifyDenomination(15, 5);
			fail(); // should throw exception
		} catch (IllegalArgumentException e) {
			// expected result, continue
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#depositCurrencyCollection(edu.ncsu.csc216.cash_register.CurrencyCollection)}
	 * .
	 * 
	 * Tests that making a deposit changes the balance of a collection.
	 */
	@Test
	public void testDepositCurrencyCollection() {
		// collection1 is empty, deposit collection2
		collection1.depositCurrencyCollection(collection2);

		// collection1 and collection2 should have equal balances
		assertEquals(collection1.getBalance(), collection2.getBalance());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#refundByAmount(int)}
	 * .
	 * 
	 * Tests that the refund is the correct amount, the value of the collection
	 * decreases correctly, and that is is not possible to refund when there is
	 * an insufficient balance in the register.
	 */
	@Test
	public void testRefundByAmount() {

		// collection3 has 1 of each currency type. This means that its value is
		// equal to 20 + 10 + 5 + 1 + .25 + .10 + .05 + .01 = 36.41. However,
		// balance is stored as an int, so the expected int value is 100x this,
		// or 3641

		// refunding less than the total amount
		CurrencyCollection refund = collection3.refundByAmount(3640);

		// the proper amount should be refunded
		assertEquals(3640, refund.getBalance());

		// balance should change
		assertEquals(1, collection3.getBalance());

		// refunding by the total amount
		refund = collection3.refundByAmount(1);

		// the proper amount should be refunded
		assertEquals(1, refund.getBalance());

		// balance should change
		assertEquals(0, collection3.getBalance());

		// refunding more than the current balance
		try {
			collection3.refundByAmount(1);
			fail(); // exception should be thrown
		} catch (IllegalArgumentException e) {
			// good, continue
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyCollection()}
	 * .
	 * 
	 * Tests that the proper collection is returned, and that currency values
	 * match the expected values.
	 */
	@Test
	public void testGetCurrencyCollection() {
		// the method's returned array should match the initial value of the
		// currency objects (0 for collection1, 10 for collection2)
		assertEquals(0, collection1.getCurrencyCollection()[0].getCount());
		assertEquals(10, collection2.getCurrencyCollection()[0].getCount());

		// modifying the collection should not change this equality
		collection1.depositCurrencyCollection(collection2);
		assertEquals(10, collection1.getCurrencyCollection()[0].getCount());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getBalance()}.
	 * 
	 * Tests that getBalance() returns the proper balance of a collection.
	 */
	@Test
	public void testGetBalance() {
		// collection1 is empty, so its value should be zero
		assertEquals(0, collection1.getBalance());

		// collection3 has 1 of each currency type. This means that its value is
		// equal to 20 + 10 + 5 + 1 + .25 + .10 + .05 + .01 = 36.41. However,
		// balance is stored as an int, so the expected int value is 100x this,
		// or 3641

		assertEquals(3641, collection3.getBalance());

		// the balance should change after a transaction.
		collection1.depositCurrencyCollection(collection3);

		// value of collection1 is now 3641
		assertEquals(3641, collection1.getBalance());
	}

}
