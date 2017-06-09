package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of the CategoryList class, including
 * adding, removing, and accessing Categories stored in CategoryLists.
 * 
 * @author Chris Bollinger (cwbollin@gmail.com)
 * 
 */
public class CategoryListTest {

	CategoryList test;
	CategoryList emptyList;

	/**
	 * Initializes 2 CategoryLists for testing, one empty, and the other
	 * containing 3 categories.
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		test = new CategoryList();
		test.addCategory("Category 1", "This is the first category.");
		test.addCategory("Category 2", "This is the second category.");
		test.addCategory("Category 3", "This is the third category.");
		emptyList = new CategoryList();
	}

	/**
	 * Tests the functionality of getName.
	 */
	@Test
	public void testGetName() {
		assertEquals("Categories", test.getName());
	}

	/**
	 * Tests the functionality of adding categories to empty lists and to the
	 * end of filled lists.
	 */
	@Test
	public void testAddCategory() {
		// add a category to test
		test.addCategory("Category 4", "This is the fourth category.");
		// size should increase
		assertEquals(4, test.size());
		// Category data should match the inputed data and be in the expected
		// location
		assertEquals(new Category("Category 4", "This is the fourth category.",
				"C4"), test.getCategoryAt(3));

		// test adding to an empty list
		emptyList.addCategory("Solo", "Single Category");
		// size should increase
		assertEquals(1, emptyList.size());
		// Category data should match the inputed data and be in the expected
		// location
		assertEquals(new Category("Solo", "Single Category", "C1"),
				test.getCategoryAt(0));

	}

	/**
	 * Tests the functionality of getCategoryAt for accessing categories on both
	 * ends and in the middle of the list. Also tests that trying to enter an
	 * illegal index will throw an IndexOutOfBounds exception.
	 */
	@Test
	public void testGetCategoryAt() {
		Category temp = test.getCategoryAt(0);
		// check that getCategoryAt returns the first category, and that that
		// the category ID is correctly set to "C1"
		assertEquals(new Category("Category 1", "This is the first category.",
				"C1"), temp);

		temp = test.getCategoryAt(1);
		// check that getCategoryAt returns the second category, and that that
		// the category ID is correctly set to "C2"
		assertEquals(new Category("Category 2", "This is the second category.",
				"C2"), temp);

		temp = test.getCategoryAt(2);
		// check that getCategoryAt returns the third category, and that that
		// the category ID is correctly set to "C3"
		assertEquals(new Category("Category 3", "This is the third category.",
				"C3"), temp);

		// attempt to get a category at a negative index
		try {
			temp = test.getCategoryAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// temp should still be equal to the Category 3, as no value should
			// have been returned.
			assertEquals(new Category("Category 3",
					"This is the third category.", "C3"), temp);

		}

		// attempt to get a category at an index that is too large
		try {
			temp = test.getCategoryAt(500);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// temp should still be equal to the Category 3, as no value should
			// have been returned.
			assertEquals(new Category("Category 3",
					"This is the third category.", "C3"), temp);
		}
	}

	/**
	 * Tests the functionality of indexOf. Should return the index of the
	 * category with the given ID, or -1 if there is no category with that ID.
	 */
	@Test
	public void testIndexOf() {
		// C1 should be at index 1
		assertEquals(0, test.indexOf("C1"));
		// C2 should be at index 2
		assertEquals(1, test.indexOf("C2"));
		// C3 should be at index 3
		assertEquals(2, test.indexOf("C3"));

		// since there is no C4, -1 should be returned
		assertEquals(-1, test.indexOf("C4"));
	}

	/**
	 * Tests the functionality of indexOfName. Should return the index of the
	 * category with the given name, or -1 if there is no category with that
	 * name.
	 */
	@Test
	public void testIndexOfName() {
		// C1 should be at index 1
		assertEquals(0, test.indexOfName("Category 1"));
		// C2 should be at index 2
		assertEquals(1, test.indexOfName("Category 2"));
		// C3 should be at index 3
		assertEquals(2, test.indexOfName("Category 3"));

		// since there is no C4, -1 should be returned
		assertEquals(-1, test.indexOfName("FAKENAME"));
	}

	/**
	 * Tests the functionality of isEmpty.
	 */
	@Test
	public void testIsEmpty() {
		// test is not empty
		assertFalse(test.isEmpty());

		// and emptyList is
		assertTrue(emptyList.isEmpty());
	}

	/**
	 * Tests the functionality of removeCategoryAt, and checks that an
	 * IndexOutOfBoundsException is thrown when an illegal index is passed in.
	 */
	@Test
	public void testRemoveCategoryAt() {

		Category temp;

		// attempt to remove from a negative index
		try {
			temp = test.removeCategoryAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// list size should not change
			assertEquals(3, test.size());
		}

		// attempt to remove from an index past the end of the list
		try {
			temp = test.removeCategoryAt(10);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// list size should not change
			assertEquals(3, test.size());
		}

		// test middle remove

		// the removed category should be returned
		temp = test.removeCategoryAt(1);
		// the expected Category is the second one, Category 2
		assertEquals(new Category("Category 2", "This is the second category.",
				"C2"), temp);
		// the list size should decrease
		assertEquals(2, test.size());

		// test end remove

		// the removed category should be returned
		temp = test.removeCategoryAt(1);
		// the expected Category is the third one, Category 3
		assertEquals(new Category("Category 3", "This is the third category.",
				"C3"), temp);
		// the list size should decrease
		assertEquals(1, test.size());

		// test front remove

		// the removed category should be returned
		temp = test.removeCategoryAt(0);
		// the expected Category is the third one, Category 3
		assertEquals(new Category("Category 1", "This is the first category.",
				"C1"), temp);
		// the list size should decrease
		assertEquals(0, test.size());
		// the list should be empty
		assertTrue(test.isEmpty());

	}

	/**
	 * Tests the functionality of removeCategory, and that no changes are made
	 * when an index is passed in which does not match one of the category IDs in
	 * the list.
	 */
	@Test
	public void testRemoveCategory() {
		// C2 is in the list and can be removed
		assertTrue(test.removeCategory("C2"));
		// size should decrease
		assertEquals(2, test.size());

		// C7 is not in the list and cannot be removed
		assertFalse(test.removeCategory("C7"));
		// size should not change
		assertEquals(2, test.size());
	}

	/**
	 * Tests the functionality of get2DArray.
	 */
	@Test
	public void testGet2DArray() {
		Object[][] array = test.get2DArray();

		assertEquals("C1", (String) array[0][0]);
		assertEquals("Category 1", (String) array[0][1]);
		assertEquals("This is the first category.", (String) array[0][2]);

		assertEquals("C2", (String) array[1][0]);
		assertEquals("Category 2", (String) array[1][1]);
		assertEquals("This is the second category.", (String) array[1][2]);

		assertEquals("C3", (String) array[2][0]);
		assertEquals("Category 3", (String) array[2][1]);
		assertEquals("This is the third category.", (String) array[2][2]);
	}

}
