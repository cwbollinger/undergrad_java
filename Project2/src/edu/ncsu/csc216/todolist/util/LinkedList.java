package edu.ncsu.csc216.todolist.util;

import java.io.Serializable;

/**
 * The Class LinkedList holds a list of objects and methods of how to manipulate
 * the list.
 * 
 * @param <E>
 *            the element type
 * @author Cole Logar
 */
public class LinkedList<E> implements List, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7459L;

	/** The size of the list. */
	private int size;

	/** The front of the list. */
	private Node front;

	/**
	 * Instantiates a new linked list.
	 */
	public LinkedList() {
		front = null;
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc216.todolist.util.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc216.todolist.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return front == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc216.todolist.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object obj) {
		Node current = front; // avoid losing list
		// if current is null, this list is empty, return false
		if (current == null) {
			return false;
		}
		while (current != null) {
			if (current.data.equals(obj)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc216.todolist.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (this.contains(value))
			throw new IllegalArgumentException();
		// code based on in class slides
		if (front == null) {
			// adding to an empty list
			front = new Node(value, null);
		} else {
			// adding to the end of an existing list
			Node current = front; // avoid losing list
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Node(value, null);
		}
		// end code from class
		size++;
		return true;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param idx
	 *            the element at the specified position in this list
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc216.todolist.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int idx, Object obj) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (obj == null) {
			throw new NullPointerException();
		}
		if (this.contains(obj))
			throw new IllegalArgumentException();

		// code based on in class slides
		if (idx == 0) {
			// add to front of list
			front = new Node(obj, front);
		} else {
			// inserting into an existing list
			Node current = front;
			// stop BEFORE index to add at
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			current.next = new Node(obj, current.next);
		}
		// end code from class

		size++;
	}

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the list.
	 * Got this part of this code from the lecture notes.
	 * 
	 * @param idx
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 * 
	 */
	@Override
	public Object remove(int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		Object value = null;
		if (front == null) {
			throw new IndexOutOfBoundsException();
		}
		if (idx == 0) {
			value = front.data;
			front = front.next;
		} else {
			Node current = front;
			int i = 0;
			while (current.next != null && i < idx - 1) {
				current = current.next;
				i++;
			}
			if (current.next == null) {
				throw new IndexOutOfBoundsException();
			}
			value = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return value;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element. More
	 * formally, returns the lowest index i such that (o==null ? get(i)==null :
	 * o.equals(get(i))), or -1 if there is no such index. Got part of this code
	 * from the lecture notes.
	 * 
	 * @param obj
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			return -1;
		} else {
			Node current = front;
			int i = 0;
			while (i < size) {
				if (current.data.equals(obj))
					return i;
				current = current.next;
				i++;
			}
		}

		return -1;
	}

	/**
	 * The Class Node that contains building blocks of the Linked List Class.
	 */
	private class Node implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 484909840L;

		/** The data contained in the Node. */
		protected E data;

		/** The next Node object in the list. */
		Node next;

		/**
		 * Instantiates a new node. Got this code from the lecture notes.
		 * 
		 * @param obj
		 *            the object to be added to the data of the Node
		 * @param n
		 *            the node that the object will be added to
		 */
		@SuppressWarnings("unchecked")
		public Node(Object obj, Node n) {
			this.data = (E) obj;
			this.next = n;
		}
	}
}
