package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of the Category class, including methods
 * for comparing Category objects.
 * 
 * @author Chris Bollinger (cwbollin@gmail.com)
 * @author Cole Logar (aclogar@gmail.com)
 * 
 */
public class CategoryTest {

	Category test;
	Category sameID;
	Category diffID1;
	Category diffID2;

	/**
	 * Initializes four categories for testing, two with the same ID, one with a
	 * lower ID, and one with a higher ID
	 * 
	 */
	@Before
	public void setUp() {
		test = new Category("default", "the default", "1");
		sameID = new Category("Same", "Same ID", "1");
		diffID1 = new Category("Diff", "Different ID", "2");
		diffID2 = new Category("Diff", "Different ID", "0");
	}

	/**
	 * Tests the functionality of hashCode by checking that Categories with
	 * equal IDs have indentical hash codes.
	 */
	@Test
	public void testHashCode() {
		assertEquals(sameID.hashCode(), test.hashCode());
		assertTrue(diffID1.hashCode() != test.hashCode());
		assertTrue(diffID2.hashCode() != test.hashCode());
	}

	/**
	 * Tests the functionality of getName.
	 */
	@Test
	public void testGetName() {
		// expected is default, actual is test.getName()
		assertEquals("default", test.getName());
	}

	/**
	 * Tests the functionality of setName.
	 */
	@Test
	public void testSetName() {
		// this is before the name is changed so
		// expected is default, actual is test.getName()
		assertEquals("default", test.getName());
		test.setName("Cole");
		// after the name change the expected is Cole, actual is test.getName
		assertEquals("Cole", test.getName());

	}

	/**
	 * Tests the functionality of getDescription.
	 */
	@Test
	public void testGetDescription() {
		// expected is the default, actual is test.getDescription()
		assertEquals("the default", test.getDescription());
	}

	/**
	 * Tests the functionality of setDescription.
	 */
	@Test
	public void testSetDescription() {
		// expected is the default, actual is test.getDescription()
		assertEquals("the default", test.getDescription());
		// change desc to "new"
		test.setDescription("new");
		// expected is new, actual is test.getDescription()
		assertEquals("new", test.getDescription());
	}

	/**
	 * Tests the functionality of getCategoryID.
	 */
	@Test
	public void testGetCategoryID() {
		// expected is 1, actual is test.getDescription()
		assertEquals("1", test.getCategoryID());
	}

	/**
	 * Tests the functionality of equalsObject. Equality is determined by a
	 * Category's ID.
	 */
	@Test
	public void testEqualsObject() {

		// ID is the only relevant field for Category comparison
		assertTrue(test.equals(sameID));
		assertFalse(test.equals(diffID1));
	}

	/**
	 * Tests the functionality of compareTo. Comparison is made based on
	 * Category ID.
	 */
	@Test
	public void testCompareTo() {
		// should return negative number when ID is lower than compared object
		assertTrue(0 > test.compareTo(diffID1));
		// should return positive number when ID is higher than compared object
		assertTrue(0 < test.compareTo(diffID2));
	}

	/**
	 * Tests the functionality of toString. ToString returns the name of the
	 * category.
	 */
	@Test
	public void testToString() {
		// test that the toString method returns the category name
		assertEquals("default", test.toString());
		assertEquals("Same", sameID.toString());
		assertEquals("Diff", diffID1.toString());
	}

}
