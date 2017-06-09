package edu.ncsu.csc216.queues;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class QueueClientTest {
	
	private Queue<Integer> q1;
	private Queue<Integer> q2;
	private Queue<Integer> q3;
	
	@Before
	public void setUp() {
		q1 = new LinkedList<Integer>();
		q2 = new LinkedList<Integer>();
		q3 = new LinkedList<Integer>();
		
		q1.add(1); q1.add(-2); q1.add(3); q1.add(-4); q1.add(5); q1.add(-6);
		q2.add(42); q2.add(-3); q2.add(4); q2.add(-15); q2.add(-9); q2.add(71);
		q3.add(-30); q3.add(-20); q3.add(10); q3.add(60); q3.add(50); q3.add(-40); q3.add(-3); q3.add(0);
	}


	@Test
	public void testMystery3() {
		assertEquals("[-1, -3, -5]", QueueClient.mystery3(q1));
		assertEquals("[-42, -4, -71]", QueueClient.mystery3(q2));
		assertEquals("[-10, -60, -50]", QueueClient.mystery3(q3));
	}
	
	@Test
	public void testMystery3a() {
		assertEquals("[5, -6, -1, -3]", QueueClient.mystery3a(q1));
		assertEquals("[-9, 71, -42, -4]", QueueClient.mystery3a(q2));
		assertEquals("[-3, 0, -10, -60, -50]", QueueClient.mystery3a(q3));
	}

}
