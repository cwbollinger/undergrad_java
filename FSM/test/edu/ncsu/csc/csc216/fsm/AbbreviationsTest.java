package edu.ncsu.csc.csc216.fsm;

import junit.framework.TestCase;

public class AbbreviationsTest extends TestCase {
	
	private Abbreviations a;

	protected void setUp() throws Exception {
		super.setUp();
		a = new Abbreviations();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testContainsLol() {
		assertTrue(a.containsLol("lol"));
		assertFalse(a.containsLol("lollipop"));
		assertTrue(a.containsLol("LOL"));
		assertFalse(a.containsLol("Alol"));
		assertTrue(a.containsLol("lol."));
	}

}
