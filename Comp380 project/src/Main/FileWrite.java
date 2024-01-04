//reminder you said I could use this name on discord is Undercl0ck
package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWrite{
	// Fields
	// declarations
	private PrintWriter out; //used to write text to a file
	private String file;// String to store file name
	private ArrayList<String> data; //used to store and index lines of text which you wish to write
	private boolean isOpen;// boolean to tell weither or not the file is open
	
	public FileWrite(String filename){
		file = filename;// file being used to store the perameter filename
		data = new ArrayList<String>();//data being initialized as an array list
		isOpen = false;	//is open being initialized to false	
		close();//calls the close method
	}
	
	/* Write a line to the file buffer */
	public void writeLine(String line){
		data.add(line);// adds text to the array list to store it till saved text is stored the peramerter line
	}
	
	/* Get size of n */
	// same as ezfile read
	public int getNumLines(){
		return data.size();
	}
	
	/* Commit changes to file */
	public void saveFile(){
		if(isOpen)			return;// if the file is is still open returns
		openWrite();//calls the open write mehtod
		for(int i = 0; i < data.size(); i++)
			writeFileLine(data.get(i));// loops to write all indexes stored in array list data to the file
		close();//closes file
	}
	
	/* Helper method...*/
	private void writeFileLine(String line){
		if(!isOpen)			return;// if the file is closed return thus doing nothing
		out.println(line);//prints the line to the file
	}
	
	/* Helper method...*/
	private void openWrite(){
		try {
			out = new PrintWriter(new FileWriter(file,false));//constructs filewriter object given the file object boolean indicates wether or not to append the data written in this case its false so we dont append data
		} catch (IOException e) {isOpen = false; return;}//in the event of excpetion sets is open to false and returns
		isOpen = true;//sets is open to true
	}
	
	/* Closes a file */
	// same as ezfile read
	private void close(){
		if(!isOpen)			return;
		out.close();
		isOpen = false;
	}
	
	/* Check to see if a filename exists...*/
	// same as ezfile read
    public static boolean doesFileExist(String filename){
    	BufferedReader in2;
    	try{
    		in2 = new BufferedReader(new FileReader(filename));
    	}catch(IOException e){
    		return false;
    	}
    	// Close it
    	try {
			in2.close();
		} catch (IOException e) {}
    	return true;
    }
}