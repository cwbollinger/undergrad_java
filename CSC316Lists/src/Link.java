// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis"
// by Clifford A. Shaffer, Prentice Hall, 1998.
// Copyright 1998 by Clifford A. Shaffer

class Link {                // A singly linked list node
  private Object element;   // Object for this node
  private Link next;        // Pointer to next node in list
  Link(Object it, Link nextval)          // Constructor 1
    { element = it;  next = nextval; }   //  Given Object
  Link(Link nextval) { next = nextval; } // Constructor 2
  Link next() { return next; }
  Link setNext(Link nextval) { return next = nextval; }
  Object element() { return element; }
  Object setElement(Object it) { return element = it; }
} // class Link

