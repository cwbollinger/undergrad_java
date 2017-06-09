package edu.ncsu.csc216.todolist.util;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The Class ArrayList holds a list of objects and methods of how to manipulate
 * the list.
 * 
 * @param <E>
 *            the element type
 * @author Cole Logar
 */
public class ArrayList<E> implements List, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 28592L;

	/** The default size of the array. */
	private static final int DEFAULT_SIZE = 10;

	/** The list. */
	private E[] list;

	/** The size of the list. */
	private int size = 0;

	/**
	 * Instantiates a new array list.
	 * 
	 * @param capacity
	 *            the capacity of the Array
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException();
		list = (E[]) (new Object[capacity]);
		size = 0;
	}

	/**
	 * Instantiates a new array list. The capacity is set to the default value
	 * of 10.
	 * 
	 */
	public ArrayList() {
		this(DEFAULT_SIZE);
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
		return size == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc216.todolist.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		if (o == null)
			return false;
		for (int i = 0; i < size; i++) {
			if (o.equals(get(i)))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.ncsu.csc216.todolist.util.List#add(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (this.contains(o))
			throw new IllegalArgumentException();
		ensureCapacity(size + 1);
		list[size] = (E) o;
		size++;
		return true;
	}

	/**
	 * Ensures capacity to avoid an {@link IndexOutOfBoundsException}.
	 * 
	 * @param i
	 *            the number of to check
	 */
	private void ensureCapacity(int i) {
		if (i > list.length) {
			int newCapacity = list.length * 2 + 1;
			if (i > newCapacity) {
				newCapacity = i;

			}
			list = Arrays.copyOf(list, newCapacity);
		}
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            the element at the specified position in this list
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their
	 * indices).
	 * 
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 * @throws IllegalArgumentException
	 *             when the element already in the list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index > size)
	 * @throws NullPointerException
	 *             when the element is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (this.contains(element))
			throw new IllegalArgumentException();

		ensureCapacity(size + 1);
		for (int i = size - 1; i >= index; i--) {
			list[i + 1] = list[i];
		}
		list[index] = (E) element;
		size++;
	}

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the list.
	 * 
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size)
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E rtn = list[index];
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return rtn;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element. More
	 * formally, returns the lowest index i such that (o==null ? get(i)==null :
	 * o.equals(get(i))), or -1 if there is no such index. Got part of this code
	 * from the lecture notes.
	 * 
	 * @param o
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (list[i] == null)
				break;
			if (list[i].equals(o)) {
				return i;
			}

		}
		return -1;
	}

}
