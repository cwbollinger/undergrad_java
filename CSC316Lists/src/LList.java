// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis"
// by Clifford A. Shaffer, Prentice Hall, 1998.
// Copyright 1998 by Clifford A. Shaffer

class LList implements List { // Linked list class
private Link head;            // Pointer to list header
private Link tail;            // Pointer to last Object in list 
protected Link curr;          // Pointer to current Object

LList(int sz) { setup(); }    // Constructor -- Ignore sz
LList() { setup(); }          // Constructor

private void setup()          // Do initialization 
{ tail = head = curr = new Link(null); } // Create header node

public void clear() {         // Remove all Objects from list
  head.setNext(null);         // Drop access to rest of links
  curr = tail = head;         // Reinitialize
}

// Insert Object at current position
public void insert(Object it) {
  Assert.notNull(curr, "No current element");
  curr.setNext(new Link(it, curr.next()));  
  if (tail == curr)           // Appended new Object
    tail = curr.next();
}

// Insert Object at end of list
public void append(Object it) {
  tail.setNext(new Link(it, null));
  tail = tail.next();
}

public Object remove() {      // Remove and return current Object
  if (!isInList()) return null;
  Object it = curr.next().element();    // Remember value
  if (tail == curr.next()) tail = curr; // Removed last: set tail
  curr.setNext(curr.next().next());     // Remove from list
  return it;                            // Return value removed
}

public void setFirst()        // Set curr to first position
{ curr = head; }

public void next()            // Move curr to next position
{ if (curr != null) curr = curr.next(); }
public void prev() {          // Move curr to previous position
  if ((curr == null) || (curr == head)) // No previous Object
    { curr = null;  return; }           //   so just return
  Link temp = head;           // Start at front of list
  while ((temp != null) && (temp.next() != curr))
	temp = temp.next();
  curr = temp;                // Found previous link
}

public int length() {         // Return current length of list
  int cnt = 0;
  for (Link temp = head.next(); temp != null; temp = temp.next())
     cnt++;                   // Count the number of Objects
  return cnt;
}

public void setPos(int pos) { // Set curr to specified position
  curr = head;
  for(int i=0; (curr!=null) && (i<pos); i++)
    curr = curr.next();
}

public void setValue(Object it) // Set current Object's value
{ Assert.notFalse(isInList());  curr.next().setElement(it); }

public Object currValue() {   // Return value of current Object
  if (!isInList()) return null;
  return curr.next().element();
}

public boolean isEmpty()      // Return true if list is empty
{ return head.next() == null; }

public boolean isInList()     // True if curr is within list
  { return (curr != null) && (curr.next() != null); }

public void print() {         // Print out the list's elements
  if (isEmpty()) System.out.println("()");
  else {
    System.out.print("( ");
	for (setFirst(); isInList(); next())
	  System.out.print(currValue() + " ");
    System.out.println(")");
  }
}
} // class LList

