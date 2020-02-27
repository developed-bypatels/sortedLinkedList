
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookWordSearch;

/**
 *
 * @author I am Prerak Patel and my student number is 000736376 and I haven't shared my program
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This code was taken from http://www.thecshandbook.com/Hash_Set
 * Modified by M. Yendt to use Generics
 * @param <BookWord>
 */
public class SimpleHashSet<BookWord> {

   public ArrayList<BookWord>[] buckets;
   public int numberOfBuckets = 10;
   public int size = 0;
   public static final double AVERAGE_BUCKET_SIZE = 3;

   public SimpleHashSet() {
      // Create buckets.
      buckets = new ArrayList[numberOfBuckets];
      for (int i = 0; i < numberOfBuckets; i++) {
         buckets[i] = new ArrayList<>();
      }
      size = 0;
   }

   public int getHash(BookWord x, int hashSize) {
      // Use modulus as hash function.
      return Math.abs(x.hashCode() % hashSize);
   }

   public void resize() {
      // Double number of buckets.
      int newBucketsSize = numberOfBuckets * 2;
      ArrayList<BookWord>[] newBuckets = new ArrayList[newBucketsSize];

      // Create new buckets.
      for (int i = 0; i < newBucketsSize; i++) {
         newBuckets[i] = new ArrayList<>();
      }

      // Copy elements over and use new hashes.
      for (int i = 0; i < numberOfBuckets; i++) {
         for (BookWord y : buckets[i]) {
            int hash = getHash(y, newBucketsSize);
            newBuckets[hash].add(y);
         }
      }

      // Set new buckets.
      buckets = newBuckets;
      numberOfBuckets = newBucketsSize;
   }

   public boolean insert(BookWord x) {
      // Get hash of x.
      int hash = Math.abs(getHash(x, numberOfBuckets));

      // Get current bucket from hash.
      ArrayList<BookWord> curBucket = buckets[hash];

      // Stop, if current bucket already has x.
      if (curBucket.contains(x)) {
         return false;
      }

      // Otherwise, add x to the bucket.
      curBucket.add(x);

      // Resize if the collision chance is higher than threshold.
      if ((float) size / numberOfBuckets > AVERAGE_BUCKET_SIZE) {
         resize();
      }
      size++;
      return true;
   }

   public boolean contains(BookWord x) {
      // Get hash of x.
      int hash = getHash(x, numberOfBuckets);

      // Get current bucket from hash.
      ArrayList<BookWord> curBucket = buckets[hash];

      // Return if bucket contains x.
      return curBucket.contains(x);
   }

   public boolean remove(BookWord x) {
      // Get hash of x.
      int hash = getHash(x, numberOfBuckets);

      // Get bucket from hash.
      ArrayList<BookWord> curBucket = buckets[hash];

      // Remove x from bucket and return if operation successful.
      return curBucket.remove(x);
   }
   
   /**
    * Utility method that will return the current number of buckets 
    * @return 
    */
   
   public int getNumberofBuckets()
   {
      return numberOfBuckets;
   }

   public int getNumberofEmptyBuckets()
   {
      int empty = 0;
      for (ArrayList bucket : buckets)
         if (bucket.isEmpty())
            empty++;
      return empty;
   }
   
   public int size() 
   {
      return size;
   }
   
   public int getLargestBucketSize()
   {
      int maximumSize = 0;
      for (ArrayList bucket : buckets)
         if (bucket.size() > maximumSize)
            maximumSize = bucket.size();
      return maximumSize;
   }
}
