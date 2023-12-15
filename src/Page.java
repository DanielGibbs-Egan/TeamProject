import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JLabel;

/**
 * 	<style> 
 *  tab{ margin-left: 30px; } 
 *  p{ margin-left: 30px; }
 *  </style>
 *  
 *	<center><b>Daniel Gibbs-Egan</b></center><br>
 *
 *	<b>Page</b> : a component for selectively visualizing <br>
 *	the components of the window<br>
 *	<br>
 *	intefaces :
 *		<p>
 *		EventRunnable: <br><tab>
 *				methods:    <br><tab><tab>
 *					run(MouseEvent e)
 *		</p>
 *	variables :
 *		<p>
 *		Layout <b>layout</b> : the layout for the page  <br>
 *		LinkedList<> <b>movementEvents</b> : runnables to  <br><tab>
 *			call when the mouse moves 
 *		</p>
 *	methods :
 *		<p>
 *		void <b>mouseMovementEvent(MouseEvent e)</b> : call all <br><tab>
 *			EventRunnables stored in the movementEvents <br><tab>
 *			LinkedList<> with the given mouse event <br>
 *		void <b>update(Window window)</b> : call the <br><tab>
 *			update method of the page's layout <br>
 *		</p>
 */

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
