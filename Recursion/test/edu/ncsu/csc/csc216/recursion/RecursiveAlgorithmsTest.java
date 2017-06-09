package edu.ncsu.csc.csc216.recursion;

import junit.framework.TestCase;

public class RecursiveAlgorithmsTest extends TestCase {
	
	private RecursiveAlgorithms r;
	
	protected void setUp() throws Exception {
		super.setUp();
		r = new RecursiveAlgorithms();
	}
	
	public void testPrintBinary() {
		assertEquals("111", r.generateBinary(7)); //111
		assertEquals("1100", r.generateBinary(12)); //1100
		assertEquals("0", r.generateBinary(0)); //0
		assertEquals("1", r.generateBinary(1)); //1
	}
	
	public void testPow() {
		assertEquals(81, r.pow(3,4)); //81
		assertEquals(4, r.pow(2,2)); //4
		assertEquals(1, r.pow(1,0)); //1
	}
	
	public void testIsPalindrome() {
		assertTrue(r.isPalindrome("madam"));
		assertTrue(r.isPalindrome("racecar"));
		assertTrue(r.isPalindrome("step on no pets"));
		assertTrue(r.isPalindrome("able was I ere I saw elba"));
		assertFalse(r.isPalindrome("Java"));
		assertFalse(r.isPalindrome("rotater"));
		assertFalse(r.isPalindrome("byebye"));
		assertFalse(r.isPalindrome("notion"));
	}

}
