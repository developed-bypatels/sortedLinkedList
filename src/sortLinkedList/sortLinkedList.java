/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*  @ Discussion - Answering Questions

o Do you notice any significant performance difference between the
SortedLinkedList<T> and the ArrayList<T> classes in terms of adding items?

    - Yes, there was the vast difference came to light SortedLinkedList<T> added the elements effectively and with less time
      while, ArrayList<T> took a lot time for performing its inbuilt add method to perform
    - SortedLinkedList added item by keeping one element as a reference and remaning list as successor link which was accessed by 
      another Node object.
    - Whereas, ArrayList is adding item at the end in the same order as they are inserted

o Do you notice any significant performance difference between the classes in
terms of removing items?

    - Even there was the apparent difference in runtime between SortedLinkedList and ArrayList where again SortedLinkedList did
      it fast
    - SortedLinkedList removed item as they were added by refreeing one element and successor link, looping through Linked List
      once the element is found succesor link is refreed to the predecessor of the element known as head
    - Whereas, in ArrayList elements are removed and all the elements following that that element are stepdown which means physical
      address of all the elements is changed

o When would you choose to use a LinkedList over an ArrayList based on the
results in this lab?

    - As far as my preference, I will choose LinkedList which will be fast enough for the larger file size like we have one in this
      lab, because of its logic of adding and removing elements making it faster to implement and taking less memory
      
*/


package sortLinkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class sortLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // TODO code application logic here

    final int NUMBER_OF_NAMES = 18756;                          // total number of names in the file
    final int NUMBER_OF_INTS = 1000;                            // total number of integers in the file
    final String filename = "src/sortLinkedList/bnames.txt";              // path for file
    final String file = "src/sortLinkedList/ints.txt";
    final String[] names = new String[NUMBER_OF_NAMES];         // String array for the name
    final int[] ints = new int[NUMBER_OF_INTS];                 // Integer array for the integer
    
     // May be useful for selecting random words to remove
    Random random = new Random(); 
    
    // Read in all of the names 
    try {
      Scanner fin = new Scanner(new File(filename));
      for (int i=0; i<NUMBER_OF_NAMES; i++)
          names[i] = fin.next();
      fin.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
      System.exit(-1);
    }
 
    // Read in all of the integers
    try {
      Scanner fin = new Scanner(new File(file));
      for (int i=0; i<NUMBER_OF_INTS; i++)
          ints[i] = Integer.parseInt(fin.next());
      fin.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
      System.exit(-1);
    }
    
    // Use the system to build the linked List
    
    // 1. Create the linkedList and add all items in Array
    SortedLinkedList<String> linkedList_String = new SortedLinkedList<>();
    long start = System.nanoTime();
    for (int i=0; i<NUMBER_OF_NAMES;i++)
        linkedList_String.add(names[i]);
    System.out.printf("The time required to add %d elements to a Linked List = %d us\n\n", linkedList_String.size(), (System.nanoTime() - start) / 1000);
    
    // 2. Remove half the items and time the code.
    long startRemove = System.nanoTime();
    for (int i=0; i<(NUMBER_OF_NAMES)/2;i++)
        linkedList_String.remove(names[random.nextInt(18756)]);
    System.out.printf("The time required to remove 9378 elements to a Linked List = %d us\n\n", (System.nanoTime() - startRemove) / 1000);
    
    // 3. Create a SortedLinkedList of another data type and demonstrate
    SortedLinkedList<Integer> linkedList_Integer = new SortedLinkedList<>();
    long startInteger = System.nanoTime();
    for (int i=0; i<NUMBER_OF_INTS;i++)
        linkedList_Integer.add(ints[i]);
    System.out.printf("The time required to add %d integer elements to a Linked List = %d us\n\n", linkedList_Integer.size(), (System.nanoTime() - startInteger) / 1000);
    
    // 4. Build a standard ArrayList of String - Remember to sort list after each element is added
    //    Time this code.
    //    Use this timing data to compare add against SortedLinkedList in discussion
    //    Remove the half the elements and time again.  
    //    Use this timing data to compare remove against SortedLinkedList in discussion

    ArrayList<String> arrayList_String = new ArrayList<>();
    long startArrayList = System.nanoTime();
    for (int i=0; i<NUMBER_OF_NAMES;i++)
    {   
        // adding names to the arrayList
        arrayList_String.add(names[i]);
        
        // sorting arrayList with Collections interface
        Collections.sort(arrayList_String);
    }
    System.out.printf("The time required to add %d elements to a Array List = %d us\n\n", arrayList_String.size(), (System.nanoTime() - startArrayList) / 1000);
    
    // Removing elements  from the ArrayList
    long startArrayListRemove = System.nanoTime();
    for (int i=0; i<(NUMBER_OF_NAMES)/2;i++)
    {
        arrayList_String.remove(names[random.nextInt(18756)]);
        Collections.sort(arrayList_String);
    }
    System.out.printf("The time required to remove 9378 elements to a Array List = %d us\n\n", (System.nanoTime() - startArrayListRemove) / 1000);
  }   
}
