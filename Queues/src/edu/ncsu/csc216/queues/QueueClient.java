package edu.ncsu.csc216.queues;

import java.util.Queue;

public class QueueClient {
	
	public static String mystery3(Queue<Integer> q) {
		int size = q.size();
		for (int i = 0; i < size; i++) {
			int n = q.remove();
			if (n > 0) {
				q.add(-n);
			}
		}
		return q.toString();
	}
	
	public static String mystery3a(Queue<Integer> q) {
		for (int i = 0; i < q.size(); i++) {
			int n = q.remove();
			if (n > 0) {
				q.add(-n);
			}
		}
		return q.toString();
	}

}
