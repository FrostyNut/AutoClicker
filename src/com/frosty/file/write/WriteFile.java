package com.frosty.file.write;

import java.io.File;			// Import File class.
import java.io.FileWriter;		// Import FileWriter class.
import java.io.PrintWriter;		// Import PrintWriter class.


/**
 * File:				FileSaver.java
 * Date Created:		17June2015
 * Date Modified:		30August2015
 * Description:			This class allows the user to save a file.
 * 
 * 
 * HOW TO USE:
 * 
 * To make it short and sweet, If I want to save a file that says 
 * "Hello World!", this is what I would do.
 * 
 * Pick one of the constructor methods and follow what the parameters
 * say.
 * 
 * To create a directory, you will need to use the blank constructor
 * method.  Then call the createDirectory(String directoryPath) method 
 * and pass it a String path.
 *	
 * @author Frosty Nut
 * 
 */
public class WriteFile {

	
	/** Contains the save path.											*/
	private String				path;
	
	
	/** Contains the content to save in the file.						*/
	private String				content;
	
	
	/** Contains the value of whether or not to append text to file.	*/
	private boolean				append;
	
	
	/**
	 * Blank WriteFile constructor method.  Only use this if you are
	 * going to use the createDirectory method.
	 */
	public WriteFile() {}
	
	
	/**
	 * Default constructor method for the WriteFile class.  
	 * 
	 * If no boolean variable is passed to the WriteFile constructor,
	 * then <code>append</code> will be defaulted to false.
	 * 
	 * @param filePath		The filePath that for the file.
	 * @param content		The content to save in the file. 
	 */
	public WriteFile(String filePath, String content) {
		
		this.path 			= filePath;
		this.content 		= content;
		this.append 		= false;
		saveFile();
		
	}	// Write file constructor method.
	
	
	/**
	 * Same as constructor method above except you get to choose whether
	 * or not to append the content to the file.
	 * 
	 * @param filePath		The filePath that for the file.
	 * @param appendValue	The appendValue for the file.
	 * @param content		The content to save in the file.			
	 */
	public WriteFile(String filePath, boolean appendValue,
												String content) {
		
		this.path 				= filePath;
		this.append 			= appendValue;
		this.content 			= content;
		saveFile();
		
	}	// Write File constructor method.
	
	
	/**
	 * The saveFile() method writes the content you want to save to a file
	 * to the file.
	 * 
	 * Uses the FileWriter and the PrintWriter class provided by java.
	 * 
	 */
	public void saveFile() {
		
		try {
			FileWriter write = new FileWriter(path, append);
			PrintWriter pw = new PrintWriter(write);
			
			pw.println(content);
			
			pw.close();
		} catch (Exception err) {
			err.printStackTrace();			
		}
		
	}	// saveFile()
	
	
	/**
	 * Checks if the directory already exists and if it doesen't, then
	 * it will create the new directory.  If it does, it will throw a 
	 * RuntimeException.
	 *   
	 * @param directoryPath 		Directory to create's path.
	 */
	public void createDirectory(String directoryPath) {
		
		File file = new File(directoryPath);
		
		if(file.exists() && file.isDirectory()) {
			throw new RuntimeException("Directory Already Exists...");
		} else {
			file.mkdir();
		}
		
	}	// createDirectory(String directoryPath)
	
}	// End of WriteFile class.
