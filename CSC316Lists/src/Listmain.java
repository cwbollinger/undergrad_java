// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis"
// by Clifford A. Shaffer, Prentice Hall, 1998.
// Copyright 1998 by Clifford A. Shaffer

import java.io.*;

// Driver class for list implementations
class Listmain {

// "find" is a demonstration for using the list ADT
// Starting at current position,
// find Elem with the next occurrence of key value K in List L
public static Elem find(List L, int K) {
  while (L.isInList())
    if (((Elem)L.currValue()).key() == K)
      return (Elem)L.currValue();
    else L.next();
  return null;
}

  // Main routine for array-based list driver class
  public static void main(String args[]) throws IOException {
    List L1 = new AList();    // Declare some sample lists
    List L2 = new AList(15);  // Set the size
    List L3 = new AList();    // Default size

    // Now test a bunch of list operations
    L2.append(new IElem(10));
    L2.print();
    L2.append(new IElem(20));
    L2.append(new IElem(15));
    L2.print();
    L1.setFirst();
    L1.insert(new IElem(39));
    L1.next();
    L1.insert(new IElem(12));
    L1.append(new IElem(5));
    L1.append(new IElem(4));
    L1.append(new IElem(3));
    L1.append(new IElem(2));
    L1.append(new IElem(1));
    L1.print();
    L2.setFirst();
    System.out.println("L2 curr: " + L2.currValue());
    System.out.print("L1: "); L1.print();
    System.out.println("Size: " + L1.length());
    System.out.print("L2: "); L2.print();
    System.out.print("L3: "); L3.print();
    L2.setFirst();
    L2.next();
    L1.setFirst();
    System.out.println(L1.currValue());
    L1.setValue(new IElem(42));
    L1.print();
    L1.setFirst();
    System.out.println(find(L1, 4));
    L2.setFirst();
    System.out.print("Removing: " + L2.remove() + "  ");
    System.out.print("Removing: " + L2.remove() + "  ");
    L2.print();
    System.out.println("Size: " + L2.length());
    L2.clear(); L2.print();
    System.out.println("Size: " + L2.length());
    L2.append(new IElem(5)); L2.print();
    System.out.println("Start a new round.");
    L2.clear();
    L2.print();
    L2.setFirst();
    L2.insert(new IElem(1));
    L2.print();
    L2.insert(new IElem(2));
    L2.print();
    L2.setPos(2);
    L2.insert(new IElem(3));
    L2.print();
    System.out.println("That is all.");
    System.in.read();
  }
}
