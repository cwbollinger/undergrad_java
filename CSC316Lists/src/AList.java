// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis"
// by Clifford A. Shaffer, Prentice Hall, 1998.
// Copyright 1998 by Clifford A. Shaffer

class AList implements List {   // Array-based list implementation

private static final int defaultSize = 10; // Default array size

private int msize;              // Maximum size of list
private int numInList;          // Actual number of Objects in list
private int curr;               // Position of current Object
private Object[] listArray;     // Array holding list Objects

AList() { setup(defaultSize); } // Constructor: use default size

AList(int sz) { setup(sz); }    // Constructor: user-specified size

private void setup(int sz) {    // Do actual initialization work
  msize = sz;
  numInList = curr = 0;
  listArray = new Object[sz];   // Create listArray
}

public void clear()             // Remove all Objects from list
{ numInList = curr = 0; }       // Simply reinitialize values

public void insert(Object it) { // Insert Object at current position
  Assert.notFalse(numInList < msize, "List is full");
  Assert.notFalse((curr >= 0) && (curr <= numInList),
	              "Bad value for curr");
  for (int i=numInList; i>curr; i--) // Shift Objects to make room
	listArray[i] = listArray[i-1];
  listArray[curr] = it;
  numInList++;                  // Increment list size
}

public void append(Object it) { // Insert Object at tail of list
  Assert.notFalse(numInList < msize, "List is full");
  listArray[numInList++] = it;  // Increment list size
}

public Object remove() {        // Remove and return current Object
  Assert.notFalse(!isEmpty(), "Can't delete from empty list");
  Assert.notFalse(isInList(), "No current element");
  Object it = listArray[curr];  // Hold removed Object
  for(int i=curr; i<numInList-1; i++) // Shift elements down
    listArray[i] = listArray[i+1];
  numInList--;                  // Decrement list size
  return it;
}

public void setFirst() { curr = 0; } // Set curr to first position
public void prev() { curr--; }  // Move curr to previous position

public void next() { curr++; }  // Move curr to next position

public int length()             // Return length of list
{ return numInList; }

public void setPos(int pos)     // Set curr to specified position
{ curr = pos; }

public void setValue(Object it) { // Set current Object's value
  Assert.notFalse(isInList(), "No current element");
  listArray[curr] = it;
}

public Object currValue() {     // Return current Object's value
  Assert.notFalse(isInList(), "No current element");
  return listArray[curr];
}

public boolean isEmpty()        // Return true if list is empty
{ return numInList == 0; }

public boolean isInList()       // True if curr is within list
{ return (curr >= 0) && (curr < numInList); }

public void print() {           // Print all list's Objects
  if (isEmpty()) System.out.println("()");
  else {
    System.out.print("( ");
    for (setFirst(); isInList(); next())
      System.out.print(currValue() + " ");
    System.out.println(")");
  }
}
} // class Alist

