import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JLabel;

public class Page extends JLabel {

	// add a serialVersionUID to supress warnings
	private static final long serialVersionUID = 1L;
	
	/* Interfaces */
	
	// a runnable the takes a mouse event as a parameter
	public interface EventRunnable {
		public abstract void run(MouseEvent e);
	}
	
	/* Variables */
	
	// the layout object for the page
	Layout layout = new Layout(); 
	
	// the runnables to call when the mouse moves
	public LinkedList<EventRunnable> movementEvents = new LinkedList<>();
	
	/* Methods */
	
	// call mouse event runnables
	public void mouseMovementEvent(MouseEvent e) {
		// loop through all runnables
		for (EventRunnable runnable: movementEvents) {
			// call the runnable
			runnable.run(e);
		}
	}
	
	// update the pages layout if it exists
	public void update(Window window) {
		// if the layout is non existant do not proceed
		if (layout == null) return;
		// update the layout
		layout.update(window);
	}
	
}
