package frosty.auto.listener;

import java.awt.MouseInfo;
import java.awt.event.*;
import javax.swing.*;

import frosty.auto.gui.ClickerGUI;
import frosty.auto.roboman.Roboman;

import com.frosty.error.reporting.ErrorReport;

/**
 * The Listener class is the main listener for all classes (except for the
 * Roboman class) in the whole auto_click project.  This class will hold 
 * variables with values that determine the behavior of the Roboman class.
 * <p>
 * The values that determine the behavior of the Roboman class are user
 * specified.  This class will also show messages to the user telling them
 * what to do when they click on a button or anything else.  The only
 * components that use this class are in the ClickerGUI class.
 * <p>
 * Error reports created by this class use the ErrorReport class.
 * <p>
 * For all above mentioned classes, see annotations below for their 
 * locations.
 * 
 * @see frosty.auto.roboman.Roboman
 * @see frosty.auto.roboman.RobomanClicker
 * @see frosty.auto.gui.ClickerGUI
 * @see com.frosty.error.reporting.ErrorReport
 * 
 * @date	October 10, 2015
 * @author	Frosty Nut
 * 
 */
public class Listener implements ActionListener {
	
	
	/**
	 * A string that holds directions for what the user is to do when they
	 * click on the initialize button located in the ClickerGUI class.
	 * 
	 * @see frosty.auto.gui.ClickerGUI
	 */
	private String setLocationDirections = "Hover over what you want to "+
											"autoclick for five seconds.";
	
	
	/**
	 * A string that holds the start of a message sent to user.  When the
	 * string is used, it will say, "Click location set at: " and will
	 * contain the x and y coordinates of the click location at the end of
	 * the message.  Is used after the click location is set to show the
	 * user where the mouse will be clicking.
	 */
	private String positionSetMsg 		= "Click location set at: ";
	
	
	/**
	 * Just an error message that says to the user that the click location
	 * could not be set.
	 */
	private String positionNotSetMsg 	= "Click location could not be set.";
	
	
	/**
	 * Will control the robot and will contain the settings the user 
	 * specified when it is used.
	 */
	private Roboman robo;
	
	
	/** The x location of the mouse when the click location was set. */
	private int 	xLoc;
	
	
	/** The y location of the mouse when the click location was set. */
	private int 	yLoc;
	
	
	/** The amount of times the user wants the mouse to auto click. */
	private int		amountOfClicks;
	
	
	/** The delay between clicks.  The recommended delay is 10+ */
	private int 	delay;
	
	
	/**
	 * Class constructor.  Sets every variable in this class to -1 and
	 * instantiates the Roboman class.
	 */
	public Listener() {
		robo			= new Roboman();
		xLoc			= -1;
		yLoc 			= -1;
		amountOfClicks	= -1;
		delay 			= -1;
		
	}	// Class constructor.
	
	
	/**
	 * Action performed method.  Handles actions in the application.
	 * <p>
	 * Will first check if it was the set location button that triggered
	 * the event.  Then will check if it was the start button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ClickerGUI.btnSetClickLocation) {
			
			JOptionPane.showMessageDialog(null, setLocationDirections);
			
			/*
			 * Sets the auto click location and then tells the user where 
			 * it is.
			 */
			try {
				
				Thread.sleep(4750);
				
				xLoc = MouseInfo.getPointerInfo().getLocation().x;
				yLoc = MouseInfo.getPointerInfo().getLocation().y;
				
				robo.setXLocation(xLoc);
				robo.setYLocation(yLoc);
				
				JOptionPane.showMessageDialog(null,
						positionSetMsg+"("+xLoc+","+yLoc+")");
				
				ClickerGUI.clickLocation.setText(
						"Click Location: "+xLoc+","+yLoc);
				
			} catch (Exception err) {
				new ErrorReport(err);
				JOptionPane.showMessageDialog(null, positionNotSetMsg);
			}
			
		} else if(e.getSource() == ClickerGUI.btnStart) {
			
			/*
			 * Starts the auto click process.  Gets the amount of clicks
			 * to perform from the amount of clicks text field.  Gets the
			 * delay from the delay text field.  Sets those to values in
			 * the Roboman class.
			 */
			try {
				
				amountOfClicks = Integer.parseInt(
						ClickerGUI.clickAmount.getText());
				
				delay = Integer.parseInt(ClickerGUI.clickDelay.getText());
				
				robo.setAmountOfClicks(amountOfClicks);
				robo.setDelay(delay);
				
				robo.autoClick();
				
			} catch (Exception err) {
				new ErrorReport(err);				
				JOptionPane.showMessageDialog(null, 
						"Could not start due to error.  Check error report.");
				
			}	// Try catch block.
			
		}	// If statements.

	}	// End of actionPerformed(ActionEvent e).

}	// End of class.