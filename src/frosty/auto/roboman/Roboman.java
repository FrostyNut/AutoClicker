package frosty.auto.roboman;

import java.awt.event.*;

import javax.swing.*;

import frosty.auto.gui.ClickerGUI;


/**
 * The Roboman class is the "settings" and "control" class for the 
 * auto clicker.  It will hold the user's specified settings for the
 * auto clicking operation and will control the auto clicking operation.
 * <p>
 * By "control the auto clicking operation", that means that it will tell
 * the actual class that moves the mouse and clicks it (RobomanClicker)
 * when to click and will also stop the clicking when it is done.
 * <p>
 * This class uses a timer to control when there are clicks.  Each time
 * the timer goes off, there will be a click, and this class will use
 * the user's settings to specify when to stop the timer, which will 
 * then stop the clicks.  
 * 
 * @see frosty.auto.roboman.RobomanClicker
 * 
 * @date	October 12, 2015
 * @author	Frosty Nut
 *
 */
public class Roboman implements ActionListener {

	
	/** By default, the x location is -1 and it will throw an error. */
	private int xLocation 				= -1;
	
	
	/** By default, the y location is -1 and it will throw an error. */
	private int yLocation 				= -1;
	
	
	/** By default, the delay is 250 which is 1/4 of a second. */
	private int delay					= 250;
	
	
	/** By default the amount of clicks is -1 and will throw an error. */
	private int amountOfClicks			= -1;
	
	
	/** The counter variable counts the amount of clicks. */
	private int counter					= 0;

	
	/** Is set to true when the xLocation is set. */
	private boolean xLocSet 			= false;
	
	
	/** Is set to true when the yLocation is set. */
	private boolean yLocSet 			= false;
	
	
	/** Is set to true when the delay is set. */
	private boolean delaySet 			= false;
	
	
	/** Is set to true when the amount of clicks is set. */
	private boolean amountOfClicksSet 	= false; 
	
	
	/** The timer that will say when to click. */
	private Timer				clickTimer;
	
	
	/** The class that will actually click the mouse. */
	private RobomanClicker		robomanClicker;
	
	
	/**
	 * Default class constructor.
	 */
	public Roboman() {}
	
	
	/**
	 * Class constructor that allows x and y parameters to be specified.
	 * 
	 * @param x		the x location of the click.
	 * @param y		the y location of the click.
	 */
	public Roboman(int x, int y) {
		this.xLocation = x;
		this.yLocation = y;
		
		this.xLocSet = true;
		this.yLocSet = true;
		
	}	// Class constructor for x,y coordinates.
	
	
	/**
	 * Class constructor that allows all parameters to be specified.
	 * 
	 * @param x				the x location of the click.
	 * @param y				the y location of the click.
	 * @param delay			the delay between clicks.
	 * @param numClicks		the number of clicks.
	 */
	public Roboman(int x, int y, int delay, int numClicks) {
		this.xLocation			= x;
		this.yLocation			= y;
		this.delay				= delay;
		this.amountOfClicks 	= numClicks;
		
		this.xLocSet			= true;
		this.yLocSet			= true;
		this.delaySet			= true;
		this.amountOfClicksSet	= true;
		
	}	// Roboman constructor with specifications.
	
	
	/**
	 * Makes sure none of the settings are below zero.
	 * 
	 * @return	<code>true</code> if the settings are all above or equal
	 * 			to zero; <code>false</code> otherwise.
	 */
	public boolean checkSettings() {
		if(this.xLocation < 0) {
			return false;
		} else if(this.yLocation < 0) {
			return false;
		} else if(this.delay < 0) {
			return false;
		} else if(this.amountOfClicks < 0) {
			return false;
		}
		return true;
		
	}	// checkSettings()
	
	
	/**
	 * Uses the RobomanClicker class to move and click the mouse.
	 * <p>
	 * If the settings the user has specified pass the tests, then 
	 * instantiate the clickTimer.  Set buttons to not be enabled,
	 * and then start the clicking. 
	 */
	public void autoClick() {
		
		/* Checks if the settings have been set. */
		if(xLocSet && yLocSet && delaySet && amountOfClicksSet) {
			
			/* Checks if the settings are allowed. */
			if(checkSettings()) {
				clickTimer = new Timer(this.delay,this);
				
				robomanClicker = new RobomanClicker();
				
				ClickerGUI.btnStart.setEnabled(false);
				ClickerGUI.btnSetClickLocation.setEnabled(false);
				
				clickTimer.start();
				
			} else {
				JOptionPane.showMessageDialog(null,
					"Cannot start auto clicker... Check error report...");
			}
		} else {
			JOptionPane.showMessageDialog(null,
				"Can not start auto clicker... Check error report...");
		}
		
	}	// autoClick()
	
	
	/**
	 * The "x" location of the click.
	 * 
	 * @param x		the x location of the click.
	 */
	public void setXLocation(int x) {
		this.xLocation	= x;
		this.xLocSet	= true;
		
	}	// setXLocation(int x)
	
	
	/**
	 * Sets the "y" location of the click.
	 * 
	 * @param y		you always lyin?
	 */
	public void setYLocation(int y) {
		this.yLocation	= y;
		this.yLocSet	= true;
		
	}
	
	
	/**
	 * Sets the delay between clicks.
	 * 
	 * @param delay		the delay between clicks.
	 */
	public void setDelay(int delay) {
		this.delay		= delay;
		this.delaySet	= true;
		
	}	// setDelay(int delay)
	
	
	/**
	 * Sets the number of clicks to carry out.
	 * 
	 * @param numClicks		the number of clicks.
	 */
	public void setAmountOfClicks(int numClicks) {
		this.amountOfClicks		= numClicks;
		this.amountOfClicksSet	= true;
		
	}	// setAmountOfClicks(int numClicks)

	
	/**
	 * Used to manage when the clicking should stop.  Each timer
	 * the timer fires an event, it will click the mouse and increase the
	 * counter by one.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.counter == amountOfClicks) {
			clickTimer.stop();
			this.counter = 0;
			
			JOptionPane.showMessageDialog(null, "Finished");
			ClickerGUI.btnStart.setEnabled(true);
			ClickerGUI.btnSetClickLocation.setEnabled(true);
		} else {
			robomanClicker.autoClick(xLocation, yLocation);
			this.counter++;
			
		}
		
	}	// actionPerformed(ActionEvent e)
	
}	// End of class.
