package frosty.auto.roboman;

import java.awt.Robot;
import java.awt.event.InputEvent;

import com.frosty.error.reporting.ErrorReport;

public class RobomanClicker {

	private Robot roboman;
	
	public RobomanClicker() {
		try {
			roboman = new Robot();
		} catch (Exception err) {
			new ErrorReport(err);
			err.printStackTrace();
		}
	}
	
	public void autoClick(int x, int y) {
		roboman.mouseMove(x, y);
        roboman.mousePress(InputEvent.BUTTON1_MASK);
        roboman.mouseRelease(InputEvent.BUTTON1_MASK);		
	}

}
