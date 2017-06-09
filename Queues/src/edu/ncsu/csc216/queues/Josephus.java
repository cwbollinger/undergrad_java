package edu.ncsu.csc216.queues;

import java.util.LinkedList;
import java.util.Queue;

public class Josephus {
	
	public static void main(String [] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Josephus N M");
			System.exit(1);
		} else {
			eliminatePopulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		}
	}
	
	/**
	 * Creates a queue with the given number of people.
	 * Then every mth person is eliminated until only one
	 * person remains (the last person in the queue).  The 
	 * method returns the position of safety.
	 * @param people number of people in population
	 * @param m every mth person to remove
	 * @return queue position for safety
	 */
	public static int eliminatePopulation(int people, int m) {
		Queue<Integer> queue = new LinkedList<Integer>();
		//Set up queue
		for (int i = 0; i < people; i++) {
			queue.add(i);
		}
		
		while(queue.size() > 1) {
			for(int i = 1; i < m; i++) {
				queue.add(queue.remove());
			}
			System.out.println(queue.remove());
		}
		int i = queue.remove();
		System.out.println(i);
		return i;
	}

}
