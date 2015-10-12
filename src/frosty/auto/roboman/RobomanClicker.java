package frosty.auto.roboman;

import java.awt.Robot;
import java.awt.event.InputEvent;

import com.frosty.error.reporting.ErrorReport;


/**
 * This class is the class that will carry out any and all of the 
 * clicking functions (moving the mouse and clicking).  It is controlled
 * by the Roboman class.
 * 
 * @see frosty.auto.roboman.Roboman
 * 
 * @date	October 12, 2015
 * @author	Frosty Nut
 *
 */
public class RobomanClicker {

	
	/** This is the robot that will move and click the mouse. */
	private Robot roboman;
	
	
	/**
	 * Default class constructor and only class constructor.
	 * <p>
	 * Will instantiate the robot used in this class.
	 */
	public RobomanClicker() {
		try {
			roboman = new Robot();
		} catch (Exception err) {
			new ErrorReport(err);
			err.printStackTrace();
		}
	}	// Default class constructor. 
	
	
	/**
	 * Moves the mouse to it's specified (x,y) coordinates and clicks
	 * the left mouse button. 
	 * 
	 * @param x		the "x" location of the click.
	 * @param y		the "y" location of the click.
	 */
	public void autoClick(int x, int y) {
		roboman.mouseMove(x, y);
        roboman.mousePress(InputEvent.BUTTON1_MASK);
        roboman.mouseRelease(InputEvent.BUTTON1_MASK);		
	}	// autoClick(int x, int y)

}	// End of class.
