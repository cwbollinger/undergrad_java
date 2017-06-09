package edu.ncsu.csc216.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JosephusTest {

	@Test
	public void testEliminatePopulation() {
		assertEquals(6, Josephus.eliminatePopulation(7, 2));
	}

}
