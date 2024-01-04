package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead{
	// Fields
	//declarations
	private BufferedReader in;//used to manipulate lines of text
	private String file;//used to store the file name
	private boolean isOpen; // a boolean which obviously tells if the file is open or not
	private ArrayList<String> data; // used to store and later manupulate lines of text which you wish to read
	private int cursor; // a counter similar to i in for loops
	
	public FileRead(String filename){
		data = new ArrayList<String>(); //initializes data which is an array list
		file = filename;// initializes the string file to store file name which is a perameter for the EZFileRead method
		isOpen = false;// initializes the boolean isOpen as false since the file is not open
		openRead();// calls the open read method
		String t = readLine();// creates a string t and calls the read line method to store the text which will be read in the file
		while(t.equals("NULL") == false){ // while t is not null add t to the array list and set t = to the read line method which reads the next line, this will stop when there is no more information to read
			data.add(t);
			t = readLine();
		}
		close();//calls the close method
		cursor = 0; // initializes cursor as 0
	}
	
	/* Open a file for reading, if it exists */
	private void openRead(){
		if(isOpen)		return; // if the file isOpen==true return
		if(doesFileExist(file) == false)	return;// if the file doesnt exist return
		try {
			in = new BufferedReader(new FileReader(file));// checks to see if there is a file of that name if not isOpen=false
		} catch (FileNotFoundException e) {
			isOpen = false;
			return;
		}
		isOpen = true;// if the file exists isOpen=true
	}
	
	/* Read in a line from file to a string */
	String readLine(){
		if(isOpen == false)		return "NULL";// if the file is closed returns null
		String ret = "NULL";// declares and initializes string ret
		try {
            ret = in.readLine();// sets ret = to the line stored in the buffered reader
        } catch (IOException ex) {
            return "NULL";// if there is no line to read returns null
        }
		if(ret == null)		ret = "NULL";// if ret is null set ret equal to the text "NULL" and return ret
		return ret;
	}
	
	/* Closes a file */
	private void close(){
		if(isOpen == false)	return;// if the file is not open returns nothing since its already closed
		try {
			in.close();// closes the file
		} catch (IOException e) {}
	}
	
	/* Get size of n */
	public int getNumLines(){
		return data.size();// returns the amount of indexes stored in the array list data
	}
	
	/* Get line from file */
	public String getLine(int index){
		if(index >= data.size())		return "NOT VALID INDEX!";// if the index of the array list data stored as the perameter index is greater than the amount of indexes in data obviously it is not a valid index
		return data.get(index);// returns the desired index of the array list data
	}
	
	/* Get next line (based off of cursor) */
	public String getNextLine(){
		if(cursor >= data.size())		return "END OF FILE"; // if cursor is greater than the amount of indexes stored in the array list data you are at the end of the file
		String ret = data.get(cursor);// declares and innitializes String ret as a specific index (index number stored as cursor which is a counter similar to i in for loops) of the array list datat
		cursor++;// increases the counter similar to i in for loops
		return ret;// returns the index of data which is now stored as the string ret
		// the counter here works really well as it starts at 0 and goes up until the size of the array list data has been met. Since get doesnt disrupt the amount of indexes in data this works perfectly
	}
	
	/* Check to see if a filename exists...*/
    public static boolean doesFileExist(String filename){
    	BufferedReader in2;//declares a new buffered reader
    	try{
    		in2 = new BufferedReader(new FileReader(filename)); //creates buffered reader object with given file object 
    	}catch(IOException e){
    		return false;// if there is not a file which exists of the name stored as a perameter returns false thus file does not exist
    	}
    	// attempts to close the file
    	try {
			in2.close();
		} catch (IOException e) {}
    	return true;// can only possibly return true if the name stored as a perameter is the name of a file allowing the code to get this far
    }
}