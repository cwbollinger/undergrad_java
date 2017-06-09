package edu.ncsu.csc.csc216.fsm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Counts the number of lines, words, and characters 
 * in the specified input file.  A word is defined as 
 * the maximal sequence of alphabetic characters.  Words
 * are separated by non-alphabetic characters including 
 * white-space.  A line is represented by a new line 
 * character.
 * 
 * The code is based on Figure 7 in "Finite-State Machines" by 
 * Suzanne Balik and Matthias Stallmann
 * 
 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
 */
public class WordCounter {
	
	/** Number of words in the file */
	private int wc = 0;
	/** Number of lines in the file */
	private int lc = 0;
	/** Number of characters in the file including special characters and whitespace */
	private int cc = 0;
	
	/**
	 * Processes the file represented by the specified
	 * BufferedReader.  A BufferedReader is used so that
	 * file input can be read in character by character.
	 * @param br a BufferedReader for a file
	 */
	public void processFile(BufferedReader br) {
		//Set up variable to keep track of the current
		//character and the current state
		char ch;
		int state = 0;
		int next;
		
		try {
			//Read in each character as an int...
			while ((next = br.read()) != -1) {
				//... and then cast to a char
				ch = (char)next;
				
				//How we process depends on the state
				//and then the new character
				switch(state) {
				    //In state 0 we are not in a word
					case 0:
						if (ch == '\n') { //new line
							++lc;
							++cc;
						} else if (Character.isLetter(ch)) { //start a word
							state = 1;
							++wc;
							++cc;
						} else { //other characters
							++cc;
						}
						break;
					//In state 1 we are in a word
					case 1:
						if (ch == '\n') { //new line and end of word
							state = 0;
							++lc;
							++cc;
						} else if (Character.isLetter(ch)) { //still in a word
							++cc;
						} else { //other character and end of word
							state = 0;
							++cc;
						}
						break;
					default: System.out.println("Invalid state " + state);
				}
			}
		} catch (IOException e) {
			System.out.println("File Read error");
			System.exit(1);
		}
	}
	
	/**
	 * Returns the word count
	 * @return word count
	 */
	public int getWc() {
		return wc;
	}
	
	/**
	 * Returns the line count
	 * @return line count
	 */
	public int getLc() {
		return lc;
	}
	
	/**
	 * Returns the character count
	 * @return character count
	 */
	public int getCc() {
		return cc;
	}
	
	/**
	 * Starts the program.  The user is required to 
	 * pass in a command line argument specifying the file
	 * to process.
	 * @param args
	 */
	public static void main(String [] args) {
		if (args.length != 1) { //The program isn't started correctly
			System.out.println("Usage: java WordCounter filename");
			System.exit(0);
		} else { //A file is given
			try {
				//Create a WordCounter object
				WordCounter counter = new WordCounter();
				//Create a BufferedReader from a FileReader.  A FileReader
				//may throw a FileNotFoundException
				BufferedReader reader = new BufferedReader(new FileReader(args[0]));
				counter.processFile(reader);
				
				//Print the results
				System.out.println("File " + args[0] + " contains " + 
						counter.getLc() + " lines, " + counter.getWc() + 
						" words, and " + counter.getCc() + " characters");
			} catch (FileNotFoundException e) {
				System.out.println("File error.");
				System.out.println("Usage: java WordCounter filename");
			}
		}
	}

}
