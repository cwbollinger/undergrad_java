package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of the Task class.
 * 
 * @author Chris Bollinger (cwbollin@gmail.com)
 * 
 */
public class TaskTest {

	Task testTask;
	Category test;

	/**
	 * Initializes a Task and a Category for testing.
	 * 
	 */
	@Before
	public void setUp() {
		test = new Category("default", "the default", "1");
		testTask = new Task("title", "details", new Date(), new Date(), test,
				"T1");
	}

	/**
	 * Tests the functionality of setTitle, and checks that an
	 * IllegalArgumentException is thrown if an attempt is made to set the title
	 * to null or an empty string.
	 */
	@Test
	public void testSetTitle() {
		try {
			testTask.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			// title should not have changed
			assertEquals("title", testTask.getTitle());
		}

		try {
			testTask.setTitle("");
			fail();
		} catch (IllegalArgumentException e) {
			// title should not have changed
			assertEquals("title", testTask.getTitle());
		}
	}

	/**
	 * Tests the functionality of getDetails.
	 */
	@Test
	public void testGetDetails() {
		assertEquals("details", testTask.getDetails());
	}

	/**
	 * Tests the functionality of setDetails.
	 */
	@Test
	public void testSetDetails() {
		testTask.setDetails(null);
		assertEquals(null, testTask.getDetails());
		testTask.setDetails("");
		assertEquals("", testTask.getDetails());
	}

	/**
	 * Tests the functionality of setStartDateTime, and checks that an
	 * IllegalArgumentException is thrown if a null Date is passed in.
	 */
	@Test
	public void testSetStartDateTime() {
		Date oldDate = testTask.getStartDateTime();
		try {
			testTask.setStartDateTime(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(oldDate, testTask.getStartDateTime());
		}
	}

	/**
	 * Tests the functionality of setDueDateTime, and checks that an
	 * IllegalArgumentException is thrown if a null Date is passed in.
	 */
	@Test
	public void testSetDueDateTime() {
		Date oldDate = testTask.getDueDateTime();
		try {
			testTask.setDueDateTime(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(oldDate, testTask.getDueDateTime());
		}
		testTask.setDueDateTime(new Date(100));
		assertEquals(new Date(100), testTask.getDueDateTime());
	}

	/**
	 * Tests the functionality of setCompletedDateTime, and checks that an
	 * IllegalArgumentException is thrown if a null Date is passed in.
	 * 
	 */
	@Test
	public void testSetCompletedDateTime() {
		Date oldDate = testTask.getCompletedDateTime();
		try {
			testTask.setCompletedDateTime(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(oldDate, testTask.getCompletedDateTime());
		}
		testTask.setCompletedDateTime(new Date(100));
		assertEquals(new Date(100), testTask.getCompletedDateTime());
	}

	/**
	 * Tests the functionality of setCompleted.
	 */
	@Test
	public void testSetCompleted() {
		testTask.setCompleted(false);
		assertFalse(testTask.isCompleted());
		testTask.setCompleted(true);
		assertTrue(testTask.isCompleted());
	}

	/**
	 * Tests the functionality of setCategory, and checks that an
	 * IllegalArgumentException is thrown if a null Category is passed in.
	 */
	@Test
	public void testSetCategory() {
		Category newTest = new Category("Test", "This is a Test", "2");

		try {
			testTask.setCategory(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(test, testTask.getCategory());
		}

		testTask.setCategory(newTest);
		assertEquals(newTest, testTask.getCategory());
	}

	/**
	 * Tests the functionality of getTaskID.F
	 */
	@Test
	public void testGetTaskID() {
		assertEquals("T1", testTask.getTaskID());
	}

	/**
	 * Tests the functionality of equalsObject. Equality is determined based on
	 * a Task's ID.
	 */
	@Test
	public void testEqualsObject() {
		// Task ID should be the only thing relevant for the equivalence test
		assertTrue(testTask.equals(new Task("Blah", "Whatever", new Date(),
				new Date(), test, "T1")));
		assertFalse(testTask.equals(new Task("title", "details", new Date(),
				new Date(), test, "T2")));

		// Tasks should not equal other object types
		assertFalse(testTask.equals(new Date()));
	}

	/**
	 * Tests the functionality of hashCode, and that the hash code of two Tasks
	 * with the same IDs are equal.
	 */
	@Test
	public void testHashCode() {
		// the hash code of two Tasks w/ the same ID should be equal
		assertEquals(new Task("Blah", "Whatever", new Date(), new Date(), test,
				"T1").hashCode(), testTask.hashCode());
	}

	/**
	 * Tests the functionality of compareTo. Comparison is made based on the due
	 * date of a Task.
	 */
	@Test
	public void testCompareTo() {
		// test that two Tasks w/ equal DueDateTimes are considered equal
		Date dueDate = testTask.getDueDateTime();
		assertEquals(0, testTask.compareTo(new Task("Blah", "Whatever",
				new Date(), dueDate, test, "T1")));
		// test that a task compared to a task of later DueDateTime will return
		// a negative number
		Date newDate = new Date();
		newDate.setYear(3000);
		assertTrue(0 > testTask.compareTo(new Task("Blah", "Whatever",
				new Date(), newDate, test, "T2")));
		// test that a task compared to a task of earlier DueDateTime will
		// return a positive number

		Date oldDate = new Date();
		oldDate.setYear(0);
		assertTrue(0 < testTask.compareTo(new Task("Blah", "Whatever",
				new Date(), oldDate, test, "T0")));

	}

}
