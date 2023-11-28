import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JLabel;

public class Page extends JLabel {

	// add a serialVersionUID to supress warnings
	private static final long serialVersionUID = 1L;
	
	/* Helper Classes */
	
	public static class EventRunnable implements Runnable{
		@Override
		public void run() {}
		public void run(MouseEvent e) {}
	}
	
	/* Variables */
	
	static int pages = 1;
	int pageIndex;
	
	Layout layout;
	
	public LinkedList<EventRunnable> hoverEvents = new LinkedList<>();
	
	/* Methods */
	
	public void mouseMovement(MouseEvent e) {
		for (EventRunnable runner: hoverEvents) {
			runner.run(e);
		}
	}
	
	public String toString() {
		return ""+pageIndex;
	}
	
	public void update(Window window) {
		if (layout == null) return;
		layout.update(window);
	}
	
	/* Constructors */
	
	public Page(Window window){
		pageIndex = Page.pages++;
		this.setVisible(true);
	}
	
}
