package edu.ncsu.csc216.string_analyzer;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringAnalyzerTest {

	@Test
	public void testStringAnalyzer() {
		//Test a valid path
		StringAnalyzer s1 = null;
		try {
			s1 = new StringAnalyzer("String", 3, 7);
			assertEquals("String", s1.getString());
			assertEquals(3, s1.getMinValue());
			assertEquals(7, s1.getMaxValue());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		//Test exception when string is null
		StringAnalyzer s2 = null;
		try {
			s2 = new StringAnalyzer(null, 3, 4);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2); //Not constructed
		}
		
		//Test exception when minValue out of bounds
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", -3, 7);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 13, 7);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		//Boundary test
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", -1, 7);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		//Boundary test
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 10, 7);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		//Test exception when maxValue out of bounds
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 3, -3);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 3, 13);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		//Boundary test
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 3, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		//Boundary test
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 3, 10);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		//Test that maxValue can't be less than min
		//value
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 7, 3);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}
		
		//Test that minValue and maxValue can
		//be equal
		s2 = null;
		try {
			s2 = new StringAnalyzer("String", 3, 3);
			assertEquals("String", s2.getString());
			assertEquals(3, s2.getMinValue());
			assertEquals(3, s2.getMaxValue());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}

	@Test
	public void testCountDigits1() {
		//Path 1 - 2 - 9
		StringAnalyzer s1 = new StringAnalyzer("", 3, 7);
		assertEquals(0, s1.countDigits());
		
	}
	
	@Test
	public void testCountDigits2() {
		//Test path 1-2-3-4-5-6-7-8-2-9
		StringAnalyzer st = new StringAnalyzer("5", 3, 7);
		assertEquals(1, st.countDigits());
	}
	
	@Test
	public void testCountDigits3() {
		//Test path 1-2-3-4-2-9
		StringAnalyzer st = new StringAnalyzer("a", 3, 7);
		assertEquals(0, st.countDigits());
	}
	
	@Test
	public void testCountDigits4() {
		//Test path 1-2-3-4-5-6-2-9
		StringAnalyzer st = new StringAnalyzer("1", 3, 7);
		assertEquals(0, st.countDigits());
	}
	
	@Test
	public void testCountDigits5() {
		//Test path 1-2-3-4-5-6-7-2-9
		StringAnalyzer st = new StringAnalyzer("9", 3, 7);
		assertEquals(0, st.countDigits());
	}
}
