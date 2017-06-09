package edu.ncsu.csc216.searching_sorting.bst;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
	
	private BinarySearchTree tree;

	@Before
	public void setUp() throws Exception {
		tree = new BinarySearchTree();
		tree.add(55);
		tree.add(29);
		tree.add(87);
		tree.add(-3);
		tree.add(42);
		tree.add(60);
		tree.add(91);
	}

	@Test
	public void testAdd() {
		//Create empty tree
		tree = new BinarySearchTree();
		//Test empty tree
		assertEquals("", tree.inOrderTraversal());
		
		//Add element to root and test traversal
		tree.add(55);
		assertEquals("55 ", tree.inOrderTraversal());
		
		//Add element to left sub-tree and test traversal
		tree.add(29);
		assertEquals("29 55 ", tree.inOrderTraversal());
		
		//Add element to right sub-tree and test traversal
		tree.add(78);
		assertEquals("29 55 78 ", tree.inOrderTraversal());
		
		//Try to add duplicate
		tree.add(78);
		assertEquals("29 55 78 ", tree.inOrderTraversal());
	}
	
	@Test
	public void testTraversals() {
		assertEquals("55 29 -3 42 87 60 91 ", tree.preOrderTraversal());
		assertEquals("-3 29 42 55 60 87 91 ", tree.inOrderTraversal());
		assertEquals("-3 42 29 60 91 87 55 ", tree.postOrderTraversal());
	}

	@Test
	public void testContains() {
		assertTrue(tree.contains(29));
		assertTrue(tree.contains(55));
		assertFalse(tree.contains(63));
		assertFalse(tree.contains(35));
	}
	
	@Test
	public void testGetMin() {
		assertEquals(-3, tree.getMin());
		
		//Create a new tree and attempt to get min
		tree = new BinarySearchTree();
		try {
			tree.getMin();
			fail("Should throw an exception");
		} catch (NoSuchElementException e) {
			assertEquals("", tree.inOrderTraversal());
		}
	}
	
	@Test
	public void testRemove() {
		//Remove a node with no children
		tree.remove(42);
		assertEquals("-3 29 55 60 87 91 ", tree.inOrderTraversal());
		
		//Remove a node with left child
		tree.remove(29);
		assertEquals("-3 55 60 87 91 ", tree.inOrderTraversal());
		
		//Remove a node with two children
		tree.remove(87);
		assertEquals("-3 55 60 91 ", tree.inOrderTraversal());
		
		//Remove a node with right child
		tree.remove(60);
		assertEquals("-3 55 91 ", tree.inOrderTraversal());
		
		//Attempt to remove a node that doesn't exist
		tree.remove(60);
		assertEquals("-3 55 91 ", tree.inOrderTraversal());
		
		//Keep removing nodes
		tree.remove(55);
		assertEquals("-3 91 ", tree.inOrderTraversal());
		
		tree.remove(91);
		assertEquals("-3 ", tree.inOrderTraversal());
		
		tree.remove(-3);
		assertEquals("", tree.inOrderTraversal());
	}
	

}
