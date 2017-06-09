package edu.ncsu.csc216.lists;

public class LinkedIntList {

	private ListNode front;

	public LinkedIntList() {
		this(null);
	}

	private LinkedIntList(ListNode front) {
		this.front = front;
	}

	private int countPositive(ListNode front) {
		if (front == null) {
			return 0;
		} else if (front.data > 0) {
			return 1 + countPositive(front.next);
		} else {
			return countPositive(front.next);
		}
	}

	public int countPositive() {
		return countPositive(front);
	}

	private boolean contains(ListNode front, int value) {
		if (front == null) {
			return false;
		} else if (front.data == value) {
			return true;
		} else {
			return contains(front.next, value);
		}
	}

	public boolean contains(int value) {
		return contains(front, value);
	}

	public boolean recursiveAdd(int value) {
		if (front == null) {
			front = new ListNode(value);
			return true;
		} else {
			return front.add(value);
		}
	}

	public void recursiveAdd(int idx, int value) {
		return;
	}

	public boolean recursiveRemove(int value) {
		if (front == null) {
			return false;
		} else if (front.data == value) {
			front = front.next;
			return true;
		} else {
			return front.remove(value);
		}
	}

	public boolean add(int value) {
		if (front == null) {
			front = new ListNode(value);
			return true;
		} else {
			ListNode cur = front;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = new ListNode(value);
			return true;
		}
	}

	private class ListNode {
		public int data;
		public ListNode next;

		public ListNode() {
			this(0);
		}

		public ListNode(int data) {
			this(data, null);
		}

		public ListNode(int data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		public boolean contains(int value) {
			return false;
		}

		public boolean add(int value) {
			if (next == null) {
				next = new ListNode(value);
				return true;
			} else {
				return next.add(value);
			}
		}

		public void add(int idx, int value) {

		}

		public boolean remove(int value) {
			if (next == null) {
				return false;
			} else if (next.data == value) {
				next = next.next;
				return true;
			} else {
				return next.remove(value);
			}
		}
	}

}
