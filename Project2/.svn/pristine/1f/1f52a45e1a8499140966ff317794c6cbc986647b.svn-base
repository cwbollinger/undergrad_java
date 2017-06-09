package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of the TaskList class, including adding,
 * removing, and accessing Tasks.
 * 
 * @author Chris Bollinger (cwbollin@gmail.com)
 * 
 */
public class TaskListTest {

	TaskList testList;
	TaskList fullTaskList;

	Category testCategory;
	Date startDate;
	Date endDate;

	/**
	 * Initializes two TaskLists, a Category, and two Dates for use in testing.
	 * 
	 */
	@Before
	public void setUp() {
		testCategory = new Category("TestCat", "Test", "C1");
		testList = new TaskList("Test", "T1");
		fullTaskList = new TaskList("Also a Test", "T2");
		startDate = new Date();
		endDate = new Date();
		fullTaskList.addTask("Task1", "The first task", startDate, endDate,
				testCategory);
		fullTaskList.addTask("Task2", "The second task", startDate, endDate,
				testCategory);
		fullTaskList.addTask("Task3", "The third task", startDate, endDate,
				testCategory);
	}

	/**
	 * Tests the functionality of getName.
	 */
	@Test
	public void testGetName() {
		assertEquals("Test", testList.getName());

	}

	/**
	 * Tests the functionality of setName.
	 */
	@Test
	public void testSetName() {
		testList.setName("Retest");
		assertEquals("Retest", testList.getName());
	}

	/**
	 * Tests the functionality of getTaskListID.
	 */
	@Test
	public void testGetTaskListID() {
		assertEquals("T1", testList.getTaskListID());
	}

	/**
	 * Tests the functionality of addTask.
	 */
	@Test
	public void testAddTask() {
		Task temp = new Task("TestTask", "This is a test task", new Date(),
				new Date(), testCategory, "T1-T1");
		testList.addTask("TestTask", "This is a test task", new Date(),
				new Date(), new Category("TestCat", "Test", "C1"));

		assertTrue(temp.equals(testList.getTaskAt(0)));

	}

	/**
	 * Tests the functionality of getTaskAt at all indices.
	 */
	@Test
	public void testGetTaskAt() {
		// get front task
		assertTrue(new Task("Task1", "The first task", startDate, endDate,
				testCategory, "T2-T1").equals(fullTaskList.getTaskAt(0)));
		// get middle task
		assertTrue(new Task("Task2", "The second task", startDate, endDate,
				testCategory, "T2-T2").equals(fullTaskList.getTaskAt(1)));
		// get end task
		assertTrue(new Task("Task3", "The third task", startDate, endDate,
				testCategory, "T2-T3").equals(fullTaskList.getTaskAt(2)));
	}

	/**
	 * Tests the functionality of indexOf.
	 */
	@Test
	public void testIndexOf() {
		assertEquals(0, fullTaskList.indexOf("T2-T1"));
		assertEquals(1, fullTaskList.indexOf("T2-T2"));
		assertEquals(2, fullTaskList.indexOf("T2-T3"));
	}

	/**
	 * Tests the functionality of size.
	 */
	@Test
	public void testSize() {
		assertEquals(3, fullTaskList.size());
		assertEquals(0, testList.size());
	}

	/**
	 * Tests the functionality of isEmpty.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(testList.isEmpty());
		assertFalse(fullTaskList.isEmpty());
	}

	/**
	 * Tests the functionality of removeTaskAt, and checks that an
	 * IndexOutOfBoundsException is thrown when an illegal index is passed in.
	 */
	@Test
	public void testRemoveTaskAt() {
		Task removedTask = fullTaskList.removeTaskAt(1);
		assertTrue(new Task("Task2", "The second task", startDate, endDate,
				testCategory, "T2-T2").equals(removedTask));

		removedTask = fullTaskList.removeTaskAt(1);
		assertTrue(new Task("Task3", "The third task", startDate, endDate,
				testCategory, "T2-T3").equals(removedTask));

		removedTask = fullTaskList.removeTaskAt(0);
		assertTrue(new Task("Task1", "The first task", startDate, endDate,
				testCategory, "T2-T1").equals(removedTask));

		try {
			fullTaskList.removeTaskAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, fullTaskList.size());
		}

	}

	/**
	 * Tests the functionality of removeTask, and checks that the list is
	 * unaffected if an ID is passed in that does not match a Task in the list.
	 */
	@Test
	public void testRemoveTask() {

		boolean isRemoved = fullTaskList.removeTask("T2-T5");
		assertFalse(isRemoved);
		assertEquals(3, fullTaskList.size());

		isRemoved = fullTaskList.removeTask("T2-T2");
		assertTrue(isRemoved);
		assertEquals(2, fullTaskList.size());

		isRemoved = fullTaskList.removeTask("T2-T3");
		assertTrue(isRemoved);
		assertEquals(1, fullTaskList.size());

		isRemoved = fullTaskList.removeTask("T2-T1");
		assertTrue(isRemoved);
		assertEquals(0, fullTaskList.size());

		isRemoved = fullTaskList.removeTask("T2-T1");
		assertFalse(isRemoved);
		assertEquals(0, fullTaskList.size());
	}

	/**
	 * Tests the functionality of get2DArray.
	 */
	@Test
	public void testGet2DArray() {
		Object[][] array = fullTaskList.get2DArray();

		assertEquals("T2-T1", (String) array[0][0]);
		assertEquals("Task1", (String) array[0][1]);
		assertEquals("The first task", (String) array[0][2]);
		assertEquals(startDate, (Date) array[0][3]);
		assertEquals(endDate, (Date) array[0][4]);
		assertEquals(null, (Date) array[0][5]);
		assertEquals(false, (boolean) array[0][6]);
		assertEquals(testCategory, (Category) array[0][7]);

		assertEquals("T2-T2", (String) array[1][0]);
		assertEquals("Task2", (String) array[1][1]);
		assertEquals("The second task", (String) array[1][2]);
		assertEquals(startDate, (Date) array[1][3]);
		assertEquals(endDate, (Date) array[1][4]);
		assertEquals(null, (Date) array[1][5]);
		assertEquals(false, (boolean) array[1][6]);
		assertEquals(testCategory, (Category) array[1][7]);

		assertEquals("T2-T3", (String) array[2][0]);
		assertEquals("Task3", (String) array[2][1]);
		assertEquals("The third task", (String) array[2][2]);
		assertEquals(startDate, (Date) array[2][3]);
		assertEquals(endDate, (Date) array[2][4]);
		assertEquals(null, (Date) array[2][5]);
		assertEquals(false, (boolean) array[2][6]);
		assertEquals(testCategory, (Category) array[2][7]);
	}

}
