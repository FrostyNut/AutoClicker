package com.frosty.error.reporting;

import java.util.Date;					// For getting the date.
import java.io.PrintWriter;				// For getting exception text.
import java.io.StringWriter;			// For getting exception text.
import java.text.DateFormat;			// For formatting the date.
import java.text.SimpleDateFormat;		// For formatting the date.

import com.frosty.file.write.*;				// Contains file writing class.

/**
 * File: 				ErrorReport.java
 * Date Created: 		22August2015
 * Date Modified: 		30August2015
 * Description:			This class is used when there is a runtime error
 * and creates both an error report .log and .txt file.  The error report
 * contains the error message given by the JVM.
 * 
 * 
 * HOW TO USE:				To use this file, you need to create a 
 * few folder in your project.  Here are the directories:
 * 
 * Directory One:			ErrorReports/LogFiles/
 * Directory Two:			ErrorReports/TextFiles/
 * 
 * If you do not want to create those two directories in our project, then
 * you can modify lines 105 and 106.  Where it says ErrorReports/LogFiles/
 * one line 128, change that to whatever directory you want.  Where it 
 * says ErrorReport/TextFiles/ on line 130, change that to whatever you 
 * want.  Just remember to put a "/" (no quotes) before right before 
 * where it says "ErrorAt" (no quotes).
 * 
 * This class uses classes from the com.frosty.file.write package.
 * 
 * If you keep all settings the same and have the default directories, to
 * create an error report, just use 
 * <code>new ErrorReport(Exception variable)</code> or
 * <code>new ErrorReport(String parameter)</code>
 * 
 * @see com.frosty.file.write.WriteFile
 * 
 * @author Frosty Nut
 * 
 */
public class ErrorReport {

	
	/** Contains the error's message to be added to the error report.   */
	public static String 				errMessage;
	
	
	/** Contains the error reports file name with a .log extension. 	*/
	public static String				errReportLogFileName;
	
	
	/** Contains the error reports file name with a .txt extension.		*/
	public static String				errReportTxtFileName;
	
	
	/** Contains the date and time of the error.						*/
	public static String				errDateAndTime;
	
	
	/**
	 * CreateErrorReport constructor method accepts a string parameter.
	 * 
	 * @param cause contains the Exception that was thrown.
	 */
	public ErrorReport(Exception cause) {
		
		System.out.println("Error report being created...");
		
		/*
		 * Use the string writer class and the print writer class to get
		 * the error stack trace and store it in the String errMessage by
		 * calling the toString method on the String Writer.
		 */
		StringWriter sw = new StringWriter();
		cause.printStackTrace(new PrintWriter(sw));
		errMessage = sw.toString();
		
		/*
		 * Make a date format and instantiate it as a new simple date
		 * format.  
		 * 
		 * The date format here uses this following format:
		 * <code>"dd_MM_yyyy--HH_MM_ss"</code>
		 * 
		 * In English that would be:
		 * "Days_Months_Years--Hours_Minutes_Seconds"
		 * 
		 * Here is an example of a formatted date:
		 * 22_08_2015--16_08_02
		 */
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss");
		
		/*
		 * Get the date and time of the operating system and store it
		 * in <code>date</code>. 
		 */		
		Date date = new Date();
		
		/*
		 * Format the date using the date format that was created earlier.
		 * 
		 * Store it in the variable <code>errDateAndTime</code>
		 */
		errDateAndTime = df.format(date);
		
		/*
		 * Error log files and error report text files are the same thing 
		 * except for the file type.  Error report text files will end in
		 * .txt and error report log files will end in .log.  
		 * 
		 * All error report log files will be stored in the directory:
		 * "ErrorReports/LogFiles/" and will have the name format of 
		 * <code>"ErrorAt"+errDateAndTime+".log"</code>
		 * (or if you modified where they would be stored, they would be
		 * stored in your directory)
		 * 
		 * All error report text files will be stored in the directory:
		 * "ErrorReports/TextFiles/" and will have the name format of
		 * <code>"ErrorAt"+errDateAndTime+".txt"</code>.
		 * (or if you modified where they would be stored, they would be
		 * stored in your directory)
		 * 
		 */
		errReportLogFileName = "ErrorReports/LogFiles/ErrorAt"+
										errDateAndTime+".log";
		errReportTxtFileName = "ErrorReports/TextFiles/ErrorAt"+
										errDateAndTime+".txt";
		
		/*
		 * Call the methods that will actually create the error .log files
		 * and the error .txt files.
		 */
		createErrorReportLog(errMessage,errReportLogFileName);
		createErrorReportText(errMessage,errReportTxtFileName);
		
	}	// CreateErrorReport Constructor Method.

	
	/**
	 * Same thing as constructor method above this one except you pass
	 * it the message you want to be in the error report.  Look at the
	 * constructor method above for commenting stuff cause I'm not 
	 * commenting this. 
	 * 
	 * @param msg The message you want to be in the error report.
	 */
	public ErrorReport(String msg) {
		
		DateFormat df 	= new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss");
		Date date 		= new Date();
		errDateAndTime 	= df.format(date);
		
		errReportLogFileName = "ErrorReports/LogFiles/ErrorAt"+
										errDateAndTime+".log";
		errReportTxtFileName = "ErrorReports/TextFiles/ErrorAt"+
										errDateAndTime+".txt";
		
	}	// Error Report Constructor method with String parameter.
	
	
	/**
	 * Creates an error report .txt (text) file using the WriteFile class.
	 * 
	 * @see com.frosty.file.write.WriteFile
	 * @param s The error message.
	 * @param fileName The file name of the error report.
	 */
	private void createErrorReportText(String s, String fileName) {
		WriteFile wf = new WriteFile(fileName,s);
		wf.saveFile();
		
	}	// createErrorReportText(String s, String fileName);

	/**
	 * Creates an error report .log (log) file using the WriteFile class.
	 * 
	 * @see com.frosty.file.write.WriteFile
	 * @param s The error message
	 * @param fileName The file name of the error report.
	 */
	private void createErrorReportLog(String s, String fileName) {
		WriteFile wf = new WriteFile(fileName,s);
		wf.saveFile();
		
	}	// createErrorReportLog(String s, String fileName);
	
}	// End of ErrorReport class.
