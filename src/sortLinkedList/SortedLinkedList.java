package sortLinkedList;

/**
 * Generic Linked List class that always keeps the elements in order 
 * @author mark.yendt 
 * @author I am Prerak Patel and my student number is 000736376 and I haven't shared my program
 * @param <T>
 */
public class SortedLinkedList<T extends Comparable>
{
  /**
   * The Node class stores a list element and a reference to the next node.
   */
  private final class Node<T extends Comparable>
  {
    T value;
    Node next;

    /**
     * Constructor.
     * @param val The element to store in the node.
     * @param n The reference to the successor node.
     */
    Node(T val, Node n)
    {
      value = val;
      next = n;
    }

    /**
     * Constructor.
     *
     * @param val The element to store in the node.
     */
    Node(T val)
    {
      // Call the other (sister) constructor.
      this(val, null);
    }
  }
  private Node first;  // list head

  /**
   * Constructor.
   */
  public SortedLinkedList()
  {
    first = null;
  }

  /**
   * The isEmpty method checks to see if the list is empty.
   *
   * @return true if list is empty, false otherwise.
   */
  public boolean isEmpty()
  {
    // to check the element is first in Linked List
    return first == null;
  }

  /**
   * The size method returns the length of the list.
   * @return The number of elements in the list.
   */
  public int size()
  {
    int count = 0;
    Node p = first;
    while (p != null) {
      // There is an element at p
      count++;
      p = p.next;
    }
    return count;
  }


  /**
   * The add method adds an element at a position.
   * @param element The element to add to the list in sorted order.
   */
    public void add(T element)
    {
        // Calls isEmpty() mehtod which will check is the element pased in method  is first
        // And is it is the first elemetn then isEmpty will return true
        if (isEmpty()) 
        {   
            // creates first node with element passed as argument 
            first = new Node(element);
        }
        
        else
        {   
            // storing first position in sorting variable to loop through Linked List
            Node sorting = first;
            
            // creating pointer to loop through the Linked List with null value
            Node sortingPointer = null;
            
            // counter to check whether the element is entering the wjile loop or not
            int counter = 0;
          
            // looping through Linked List till end 
            // AND checking whether the value we are comparing is greater/lesser/equal to element passed
            while(sorting != null && sorting.value.compareTo(element) < 0)
            {
                // current position of pointer is stored in the sortingPointer
                sortingPointer = sorting;
                
                // pointer is moved ahead and stored in to the sorting
                sorting = sorting.next;
                
                // counter is increased
                counter++;
            }
          
            // if counter is 0
            if(counter == 0)
            {
                // new node is created at the very beginning og Linked List
                sortingPointer = new Node(element,first.next);
                
                // first variable is changed to newly created element
                first = sortingPointer;
            }
            
            // if the counter more than 0
            else
            {
                // new node is created at the sortingPointer
                sortingPointer.next = new Node(element,sortingPointer.next);
            }
        }
    }

   /**
   * The toString method computes the string representation of the list.
   * @return The string form of the list.
   */
  @Override
  public String toString()
    {
      String s = "[";
      
      // Use p to traverse the linked list
      Node p = first;
      while (p != null)
      {
         s += p.value; 
         if (p.next != null)
             s+= ", ";
         p = p.next;
      }      
      
      return s + "]";
    }

  /**
   * The remove method removes an element.
   * @param element The element to remove.
   * @return true if the remove succeeded, false otherwise.
   */
    public boolean remove(T element)
    { 
        // storing first position in removeElement variable to loop through Linked List
        Node removeElement = first;
        
        // if the element passed as argument is first element
        if (element.equals(removeElement.value))
        {
            // removing first element
            removeElement = removeElement.next;
            
            return true;
        }
      
        // looping through Linked list till end
        // AND if the NOT pointer element is same as element passed
        while(removeElement.next != null && !removeElement.value.equals(element))
        {
            // removing element
            removeElement = removeElement.next;
        }
    
        // if the element is not in the Linked list
        if (removeElement.next == null)
        {
            return false;
        }
        
        // if the element found in the Linked list
        else
        {
            // removing element from the Linked List
            removeElement.next = removeElement.next.next;    
            return true;
        }   
    }
}