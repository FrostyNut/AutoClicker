package frosty.auto.gui;

import java.awt.*;
import javax.swing.*;

import frosty.auto.listener.Listener;			// The listener class.
import com.frosty.error.reporting.ErrorReport;	// Error reporting class.


/**
 * The ClickerGUI class is the base class for all graphical components of
 * the auto click program.  
 * <p>
 * All of the graphical components that are in the project are in this
 * class.  Every component that requires an ActionListener in this class 
 * (buttons) will use the Listener class as their ActionListener.  See 
 * annotation below for location of listener class.
 * 
 * @see frosty.auto.listener.Listener
 * 
 * @date 	October 11, 2015
 * @author	Frosty Nut
 * 
 */
public class ClickerGUI {
	
	/** Window frame for the application. */
	private static JFrame		windowFrame;
	
	
	/**
	 * Window panel for the application.  Every component that is shown in
	 * this application (except for the window frame) is added to this 
	 * panel.  
	 * <p>
	 * This panel will have a GridLayout.
	 */
	public static JPanel		windowPanel;
	
	
	/** Tells the end user where they should enter their click amount */
	public static JLabel		clickCountLabel;
	
	
	/** Tells the user where their click location is. */
	public static JLabel		clickLocation;
	
	
	/** Tells the usre where to enter their click delay. */
	public static JLabel		clickDelayLabel;
	
	
	/** Allows the user to specify how many clicks they want. */
	public static JTextField	clickAmount;
	
	
	/** Allows the user to specify the delay they want between clicks. */
	public static JTextField	clickDelay;
	
	
	/** The button that starts the auto clicking. */
	public static JButton		btnStart;
	
	
	/** Used to set the user's click location. */
	public static JButton		btnSetClickLocation;
	
	
	/** The listener class for this application. */
	public static Listener		listener;
	
	
	/** 
	 * Class constructor method.
	 * <p>
	 * Sets the look and feel of the application and then calls other
	 * methods in the program that will create all of the graphical 
	 * components of the program.
	 * <p>
	 * Will also add all of those components to the panel, set default
	 * close operations, and then make the window visible.
	 */
	public ClickerGUI() {
		try {
			UIManager.setLookAndFeel(
		                UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception err) {
			new ErrorReport(err);
			err.printStackTrace();
		}
		
		windowFrame = new JFrame("Frosty Auto-Clicker");
		windowPanel = new JPanel();
		
		windowPanel.setLayout(new GridLayout(7,1));
		
		listener = new Listener();
		
		createButtons();
		createLabels();
		createTextFields();
		addContentToPanel();
		
		windowFrame.setContentPane(windowPanel);
		
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.pack();
		windowFrame.setVisible(true);
		
	}	// Class constructor.
	
	
	/**
	 * Creates the buttons used in the program.
	 */
	private void createButtons() {
		btnStart 		= new JButton("Start");
		btnSetClickLocation 	= new JButton("Set Click Location");
		
		btnStart.addActionListener(listener);
		btnSetClickLocation.addActionListener(listener);
		
	}	// createButtons()


	/**
	 * Creates the labels used in the programs.
	 */
	private void createLabels() {
		clickCountLabel 	= new JLabel("Amount Of Clicks:");
		clickLocation 		= new JLabel("Click Location: Not set...");
		clickDelayLabel		= new JLabel("Click Delay");
		
	}	// createLabels()
	
	
	/**
	 * Creates the text fields used in the program.
	 */
	private void createTextFields() {
		clickAmount = new JTextField(25);
		clickDelay 	= new JTextField(25);
		
	}	// createTextFields()
	
	
	/**
	 * Adds all of the created graphical components to the window panel.
	 */
	private void addContentToPanel() {
		windowPanel.add(clickCountLabel);		
		windowPanel.add(clickAmount);
		
		windowPanel.add(clickLocation);
		windowPanel.add(btnSetClickLocation);
		
		windowPanel.add(clickDelayLabel);
		windowPanel.add(clickDelay);
		
		windowPanel.add(btnStart);
		
	}	// addContentoToPanel()

}	// End of class.