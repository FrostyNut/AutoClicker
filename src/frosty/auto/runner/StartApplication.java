package frosty.auto.runner;

import java.io.*;

import frosty.auto.gui.ClickerGUI;

import com.frosty.file.write.WriteFile;


/**
 * Checks for error reporting directories and then starts the program.
 * <p>
 * To start from command line, use:
 * java StartApplication
 * 
 * @date	October 11, 2015
 * @author	Frosty Nut
 */
public class StartApplication {

	
	/**
	 * Main method to start the auto click program.  Will check if there
	 * are error reporting directories.  If there aren't any error 
	 * reporting directories, then create them.
	 * 
	 * @param args	is not used.
	 */
	public static void main(String[] args) {
		
		File errorReportsDirectory 		= new File("ErrorReports");
		File errorReportsLogDirectory	= new File("ErrorReports/LogFiles");
		File errorReportsTxtDirectory	= new File("ErrorReports/TextFiles");
		
		if(!errorReportsDirectory.exists()) {			
			new WriteFile().createDirectory("ErrorReports/");
		}
		
		if(!errorReportsLogDirectory.exists()) {
			new WriteFile().createDirectory("ErrorReports/LogFiles");
		}
		
		if(!errorReportsTxtDirectory.exists()) {
			new WriteFile().createDirectory("ErrorReports/TextFiles");
		}
		
		new ClickerGUI();

	}	// main method.

}	// End of class.