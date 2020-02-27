/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookWordSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BookWordSearch {

    /**
     * Dictionary lookup comparison discussion for ArrayList (linear search, binary search) and HashSet look-up
     * 
     * For very small collections of data difference between search and look-up is very negligible but for bunch of data
     * it will start lagging. Because, in linear and binary search key is matched with every object in the arrayList whereas,
     * look-up takes place with hashes and it makes this process faster.
     * 
     * Secondly, it sometimes become unreliable to go with look-up if it is not producing good hashes because it will result in
     * more number of collisions and empty buckets keeping the program lagging and not giving right output.
     * @param args
     */
  public static void main(String[] args) 
  {
    // File is stored in a resources folder in the project
    final String filename = "src/BookWordSearch/MobyDick.txt";
    final String dictionaryFile = "src/BookWordSearch/US.txt";
    
    // Variable declared and intialized
    
    // Counts the total number of words in the MobyDick.txt file
    int wordCount = 0;
    // Counts the total  number of unique words found in the file
    int uniqueWordCount = 0;
    // Counts the number of words not found in the dictionary by binary search
    int wordsNotFound = 0;
    // Counts the number of words not found in the dictionary by hashSet search
    int hashWordsNotFound = 0;
    // Declared for keeping track on time for distingushing the exeecution time for binary search and hashSet search
    long startTime1, stopTime1,startTime2, stopTime2;
    
    // Declaraion of ArrayList
    
    // ArrayList for storing BookWord objects of unique words from the file
    ArrayList<BookWord> wordList = new ArrayList<>();
    // ArrayList for storing BookWord objects of dictionary words from US.txt file
    ArrayList<BookWord> dictionaryWords = new ArrayList<>();
    // ArrayList for storing BookWord objects which appears exactly 36 times in the file
    ArrayList<BookWord> specialCount = new ArrayList<>();
    
    // Class implementation
    
    // SimpleHashSet Class implemented which inherits all the properties of HashSet
    SimpleHashSet hashSet = new SimpleHashSet();
    
    
    // Read in the dictionary 
    try {
      Scanner fin = new Scanner(new File(dictionaryFile), "UTF-8");
      while (fin.hasNext()) {
        String w = fin.next();
        // TODO : Add code to store dictionary words into an appropriate data structure
        
        // BookWord Object is created to store all the dictionary words as a object
        BookWord words = new BookWord(w);
        // words object created is invoked for storing the word as a object
        dictionaryWords.add(words);
        
        // hashset object created is invoked for inserting words object for further uses
        hashSet.insert(words);
      }
      // File scanner opened should must be close so that it is never altered
      fin.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
    }
    
   
    // Read in the text file 
    try {
      Scanner fin = new Scanner(new File(filename));
      fin.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE 
      while (fin.hasNext()) {
        String fileWord = fin.next().toLowerCase();
        if (fileWord.length() > 0)
        {
            // TODO : Need to create an instance of a BookWord object here and add to ArrayList
        
             // BookWord Class is implemented which has all the properties of the word 
            BookWord word = new BookWord(fileWord);
            
            // Condition checks that whether the word object created above is already in the wordList or not
            if(wordList.contains(word))
            {
                // get method of wordList is invoked inside which word object of specific index is called using that count of the word is incremented
                wordList.get(wordList.indexOf(word)).incrementCount();
            }
            else
            {
                // unique word object is added to the ArrayList - wordList
                wordList.add(word);
                // counter of unique word is incremented
                uniqueWordCount++;
            }
            // counter of words in the file is incremented
            wordCount++;
        }
      }
      // File scanner opened should must be close so that it is never altered
      fin.close();
    } catch (FileNotFoundException e) {
      System.out.println("Exception caught: " + e.getMessage());
    }
    

    // TODO: Analyze and report code here based on lab requirements
    
/* Printing the String answering total number of words in the file */
    System.out.printf("• Total %d of words in file\n\n", wordCount);
    
/* Printing the String answering total number of unique words in the file*/
    System.out.printf("• Total %d of unique words in file\n\n", uniqueWordCount);
    
/* Printing the heading*/
    System.out.printf("• The list of the 12 most frequent words and counts\n");
    
    // ArrayList - wordList is sorted by anonymous comparator interface of Collection interface ( descending order - count of words)
   // Collections.sort(wordList, (Object o1, Object o2) -> 
     //       ((BookWord)o1).getCount()>((BookWord)o2).getCount()?-1:((BookWord)o1).getCount()<((BookWord)o2).getCount()?1:0);
    
    Collections.sort(wordList, new Comparator() {

        @Override
        public int compare(Object t, Object t1) {
            if(((BookWord)t).getCount()>((BookWord)t1).getCount())
            {
                return -1;
            }
            else if(((BookWord)t).getCount()<((BookWord)t1).getCount())
            {
                return 1;
            }
            else
            {
                return 0;
            }
    }
    });
    
    // looping through sorted ArrayList - wordList to print 12 most frequent words and counts
    for(int i = 0; i < 12 ; i++)
    {
        // Printing 12 most frequent words and counts
        System.out.println("\t"+wordList.get(i).toString());
    }
    
/* Printing the words that occured exactly 36 times in the file*/
    System.out.printf("\n• The list of words that occur exactly 36 times in the file listed in alphabetical order\n");
    
        
    // ArrayList - wordList  is sorted by compareTo method of comparable interface ( ascending order - alphabetically )
    Collections.sort(wordList);
    // looping through ArrayList - wordList to check counts of all words
    for(BookWord w : wordList)
    {
        // invoking getCount method for each BookWord object from ArrayList - wordList to check the counter is equals to 36
        if(w.getCount() == 36)
        {
            // adding all the BookWord objects to ArrayList - specialCount
 
            specialCount.add(w);
        }
    }
    
    // Looping through ArrayList - specialCount
    for(BookWord spWords : specialCount)
    {
        // Printing all BookWord objects in ArrayList - specialCount
        System.out.println("\t"+spWords.toString());
    }
   
    
/*  Number of Words not contained in the dictionary - get the value by ArrayList Search and HashSet Look-up */
    
    
    // Implementing the ArrayList Search (Binary Search)
    
    // Sorting the ArrayList - dictionaryWords by compareTo method ( ascending order - alphabetically )
    Collections.sort(dictionaryWords);
    
    // Starting the timer for keeping track of execution speed
    startTime1 = System.nanoTime();
    // looping through ArrayList - wordList
    for(BookWord keys: wordList )
    {
        // Condition does binary search which matches BookWord object keys from ArrayList - wordList in sorted ArrayList - dictionaryWords 
        if(Collections.binarySearch(dictionaryWords, keys)>= 0)
        {
            // words not found in the file by ArrayList Search is incremented
            wordsNotFound++;
        }
    }
    // Stops the timer
    stopTime1 = System.nanoTime();
    
    
    // Implementing the HashSet Look-up
    
    // Starts the timer
    startTime2 = System.nanoTime();
    // looping through ArrayList - wordList
    for(BookWord keys : wordList)
    {
        // HashSet Look-up is implemented by invoking contains method 
        if(hashSet.contains(keys))
        {
            // words not found in the file by Hash-Set Search is incremented
            hashWordsNotFound++;
        }
    }
    // Stops the timer
    stopTime2 = System.nanoTime();

    
    System.out.printf("\n• The %d words that are not contained in the dictionary calculated by ArrayList Search",(uniqueWordCount - wordsNotFound)); 
    System.out.printf("\n\n• The %d words that are not contained in the dictionary calculated by HashSet Look-up Search", (uniqueWordCount - hashWordsNotFound));
    
    // Prints the String and calculates the execution speed in pico time
    System.out.print("\n\n• Time took for the the arrayList = " + (stopTime1 - startTime1)/1000);
    
    // Prints the string and calculates the execution speed in pico time
    System.out.println("\n\n• Time took for the the hashset = " + (stopTime2 - startTime2)/1000);
  }
}
