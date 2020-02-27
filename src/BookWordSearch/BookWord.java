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
public class BookWord implements Comparable<BookWord>
{
    // class variables
    private String wordCharacters;
    private int count = 1;
    
    // Constructor with one argument accepting String word
    public BookWord(String word)
    {
        // Stores the argument value into the class variable
        wordCharacters = word;        
    }
    
    public String getWord()
    {
        // returns string word
        return wordCharacters;
    }
    
    public int getCount()
    {
        // returns int count
        return count;
    }
    
    public void incrementCount()
    {
        // returns count of word
        count++;
    }
    
    // Overrides toString method
    @Override
    public String toString()
    {
        return wordCharacters + "\t\t" + count;
    }

    // Overrides equals method
    @Override
    public boolean equals(Object obj) {
        
        // Conditions checks if the object is null and object is instance of BookWord
        if (obj != null && obj instanceof BookWord) 
        {
            // Type casts the object with BookWord
            BookWord letters = (BookWord) obj;
            // Condition checks word of this object is the same aas the object passed in the class
            if(this.wordCharacters.equals(letters.wordCharacters))
            {
                return true;
            }
        }
        return false;
    }
    
    // Overrides hashcode method
    @Override
    public int hashCode()
    {
        return this.wordCharacters.hashCode();
    }

    // Overrides compareTo method from Collection interface
    @Override
    public int compareTo(BookWord o) {
        
        return this.getWord().compareTo(o.getWord()); 
    }
}
