package edu.ncsu.csc216.todolist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * 
 * This class tests the functionality of the ToDoList class, including managing
 * TaskLists and file load/save operations.
 * 
 * @author Chris Bollinger (cwbollin@gmail.com)
 * 
 */
public class ToDoListTest {

	ToDoList test;
	ToDoList test2;

	/**
	 * Sets up two ToDoList objects for testing. The first is only initialized,
	 * and the second one has additional TaskLists and Categories added.
	 * 
	 */
	@Before
	public void setUp() {
		test = new ToDoList();
		test2 = new ToDoList();
		TaskList temp = test2.getTaskList(test2.addTaskList());
		temp.setName("List2");
		temp = test2.getTaskList(test2.addTaskList());
		temp.setName("List3");
		test2.getCategoryList().addCategory("Category", "This is a category");
	}

	/**
	 * Tests the functionality of the constructor, and that it properly
	 * initializes the first TaskList and CategoryList.
	 */
	@Test
	public void testToDoList() {
		assertEquals("New List", test.getTaskList(0).getName());
		assertFalse(test.isChanged());
		assertEquals(1, test.getNumTaskLists());
		assertEquals("Categories", test2.getCategoryList().getName());
	}

	/**
	 * Tests the functionality of setFilename and getFileName. Ensures that
	 * filename cannot be set to null or an empty string.
	 */
	@Test
	public void testFileName() {
		test.setFilename("Hello");
		// getFilename should return filename
		assertEquals("Hello", test.getFilename());

		// filename cannot be set to null
		try {
			test.setFilename(null);
			fail();
		} catch (IllegalArgumentException e) {
			// filename should not have changed
			assertEquals("Hello", test.getFilename());
		}

		// filename cannot be an empty string
		try {
			test.setFilename("");
			fail();
		} catch (IllegalArgumentException e) {
			// filename should not have changed
			assertEquals("Hello", test.getFilename());
		}
	}

	/**
	 * Tests the functionality of getTaskList, and that it returns the correct
	 * TaskList at a given index.
	 */
	@Test
	public void testGetTaskList() {
		// Test that the list name is the same as the one at the expected
		// position
		assertEquals("List2", test2.getTaskList(1).getName());
		assertEquals("List3", test2.getTaskList(2).getName());
		assertEquals("New List", test2.getTaskList(0).getName());
	}

	/**
	 * Tests the functionality of getNumTaskLists. It should return the number
	 * of TaskLists currently created.
	 */
	@Test
	public void testGetNumTaskLists() {
		assertEquals(1, test.getNumTaskLists());
		assertEquals(3, test2.getNumTaskLists());
	}

	/**
	 * Tests the functionality of addTaskList and removeTaskList. addTaskList
	 * should add onto the end of the current list, and removeTaskList should
	 * remove the taskList at the specified index.
	 */
	@Test
	public void testTaskLists() {
		TaskList temp = test.getTaskList(test.addTaskList());
		temp.setName("Remove This One");
		assertEquals(2, test.getNumTaskLists());
		test.removeTaskList(1);
		assertEquals(1, test.getNumTaskLists());
		// test that the correct list has been removed
		assertEquals("New List", test.getTaskList(0).getName());
	}

	/**
	 * Tests the functionality of resize, which is a private method responsible
	 * for expanding the list of TaskLists when it fills up.
	 */
	@Test
	public void testResize() {
		// Test that the resize functionality operates correctly
		// add 9 task lists. Plus the initial list, this brings the number of
		// lists to 10, the maximum initial capacity
		for (int i = 0; i < 9; i++) {
			test.addTaskList();
		}

		// add another taskList
		int idx = test.addTaskList();
		try {
			test.getTaskList(idx);
		} catch (IndexOutOfBoundsException e) {
			// TaskList wasn't added correctly
			fail();
		}

	}

	/**
	 * Tests the functionality of saveDateFile and openDataFile.
	 */
	@Test
	public void testDataFile() {
		// save the contents of test1 to file
		test2.saveDataFile("./TestFile.tdl");
		test.openDataFile("./TestFile.tdl");
		// Task Lists from test2 should be loaded into test
		assertEquals(3, test.getNumTaskLists());
	}

}
