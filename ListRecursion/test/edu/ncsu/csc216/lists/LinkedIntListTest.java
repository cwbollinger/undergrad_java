package edu.ncsu.csc216.lists;

import junit.framework.TestCase;

public class LinkedIntListTest extends TestCase {
	
	private LinkedIntList l;

	protected void setUp() throws Exception {
		super.setUp();
		
		l = new LinkedIntList();
		l.add(4); l.add(-7); l.add(23); l.add(-1);
	}
	
	public void testCountPositive() {
		assertEquals(2, l.countPositive());
		
		l.add(-5);
		assertEquals(2, l.countPositive());
		
		l.add(15);
		assertEquals(3, l.countPositive());
	}
	
	public void testContains() {
		assertTrue(l.contains(4));
		assertFalse(l.contains(42));
	}
	
	public void testRecursiveAdd() {
		assertTrue(l.recursiveAdd(42));
		assertTrue(l.contains(42));
	}
	
	public void testRecursiveAddAtIdx() {
		l.recursiveAdd(0, 42);
		assertTrue(l.contains(42));
		
		l.recursiveAdd(5, 29);
		assertTrue(l.contains(29));
		
		l.recursiveAdd(3, 12);
		assertTrue(l.contains(12));
		
		try {
			l.recursiveAdd(23, 56);
			fail();
		} catch (IllegalArgumentException e) {
			assertFalse(l.contains(56));
		}
	}
	
	public void testRecursiveRemove() {
		assertTrue(l.recursiveRemove(4));
		
		assertFalse(l.recursiveRemove(11));
		
		assertTrue(l.recursiveRemove(23));
		
		assertTrue(l.recursiveRemove(-1));
		
		assertTrue(l.recursiveRemove(-7));
		
		assertFalse(l.recursiveRemove(3));
	}

}
