// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis"
// by Clifford A. Shaffer, Prentice Hall, 1998.
// Copyright 1998 by Clifford A. Shaffer

interface List {                  // List ADT
public void clear();              // Remove all Objects from list
public void insert(Object item);  // Insert Object at curr position
public void append(Object item);  // Insert Object at tail of list
public Object remove();           // Remove/return current Object
public void setFirst();           // Set current to first position
public void next();               // Move current to next position
public void prev();               // Move current to prev position
public int length();              // Return current length of list
public void setPos(int pos);      // Set current to specified pos
public void setValue(Object val); // Set current Object's value
public Object currValue();        // Return value of current Object
public boolean isEmpty();         // Return true if list is empty
public boolean isInList();        // True if current is within list
public void print();              // Print all of list's elements
} // interface List

