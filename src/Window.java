import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

/**
 *  <style> 
 *  tab{ margin-left: 30px; } 
 *  p{ margin-left: 30px; }
 *  </style>
 *  
 *	<center><b>Daniel Gibbs-Egan</b></center><br>
 *
 *	class <b>Window</b> : a class for visualizing the game <br><tab>
 *  	on a defined but editable area of the screen <br><tab>
 *	    the game.<br>
 *	<br>
 *	helper classes :
 *  <p>
 *		class <b>ButtonInfo</b> : stores information about a button <br>
 *		class <b>ButtonListener</b> : a class that listens to the <br>
 *  	<tab> interactions of the pc's mouse with a JComponent <br>
 *		class <b>MouseMovementUpdates</b> : a mouse movement listener for <br>
 *		<tab> updating the current pages mouse movement events</tab> <br>
 *		class <b>WindowUpdates</b> : a generic component listener that <br>
 *		<tab> calls update() on the current page </tab> <br>
 *		class <b>MouseUpdates</b> : a mouse listener designed to update a <br>
 *		<tab> ButtonInfo Object with the left mouse button's <br>
 *		<tab> information </tab> <br>
 *  </p>
 *  
 *	variables :
 *  <p>
 *  	Page <b>currentPage</b> : the current page the application is showing <br>
 *  	ButtonInfo <b>leftMouseInfo</b> : stores information about the left mouse button <br>
 *  	Window <b>window</b> : the application window <br>
 *  </p>
 *  
 *	methods :
 *	<p>
 * 		Page <b>createPage()</b> : creates a new Page Object <br>
 * 		void <b>delay(int timeMS)</b> : delays the current thread for <br><tab>
 * 			the given amount of milliseconds <br>
 * 		void <b>setCurrentPage(Page page)</b> : changes the current page <br><tab>
 * 			to the given page<br>
 * 		void <b>updateColor(Component component, int selection)</b> : <br><tab> 
 *			colors a components background based on a selection<br>
 *  </p>
 *  
 *  constructors :
 *	<p>
 *		Window <b>Window()</b> : creates a new Window Object
 *	</p>
 */

public class Window extends JFrame {
	
	// add a serialVersionUID to supress warnings
	private static final long serialVersionUID = 1L;

	/* Helper Classes */
	
	/**
	 * 	<style>
	 *		tab{ margin-left: 30px; }
	 * 	</style>
	 * 	class <b>ButtonInfo</b> : stores information about the pc's mouse<br>
	 *	<br>
	 *	variables :<br> 	
	 *	<tab> boolean <b>isDown</b> : the state of the left mouse button <br> 	
	 *	<tab> boolean <b>clicked</b> : if the left mouse button has recently been clicked <br>	
	 *	<tab> boolean <b>canClick</b> : if the left mouse button is able to click <br>
	 *
	 */
	public class ButtonInfo {
		// is the button down
		public boolean isDown = false;
		// has the button been clicked
		public boolean clicked = false;
		// can the button be clicked
		public boolean canClick = false;
	}
	
	/**
	 * 	class <b>ButtonListener</b> : a class that listens to the <br>
	 *  interactions of the pc's mouse with a JComponent <br>
	 *	<br>
	 *	when the mouse clicks calls the runnable <br>
	 */
	public static class ButtonListener implements MouseListener {
		// whether the mouse is pressed
		boolean down = false; 
		// the runnable to call when the mouse is clicked
		Runnable runnable; 
		
		/* Constructors */
		
		/**
		 * ButtonListener(Runnable runnable) : Creates a new ButtonListener<br><br>
		 * 
		 * @param runnable : the runnable to call when the mouse is clicked
		 */
		public ButtonListener(Runnable runnable) {
			this.runnable = runnable; 
		}
		
		/* Methods */
		public void mouseReleased(MouseEvent e) {
			// if the button pressed was not the 
			// left mouse button end the call
			if (e.getButton() != MouseEvent.BUTTON1) return;
			// if the mouse was pressed
			if (down) { 
				// run the function
				runnable.run();
			}
			// the mouse button is no longer down
			down = false;
			// update the buttons color
			updateColor(e.getComponent(), 1);
		}
		public void mousePressed(MouseEvent e) {
			// if the button pressed was not the 
			// left mouse button end the call
			if (e.getButton() != MouseEvent.BUTTON1) return;
			// the mouse button has been pressed
			down = true;
			// update the buttons color
			updateColor(e.getComponent(), 3);
		}
		public void mouseExited(MouseEvent e) {
			// the mouse left the button
			down = false;
			// update the buttons color
			updateColor(e.getComponent(), 1);
		}
		public void mouseEntered(MouseEvent e) {
			// update the buttons color
			updateColor(e.getComponent(), 2);
		}
		public void mouseClicked(MouseEvent e) {}
	}
	
	/**
	 *  <style> tab{ margin-left: 30px; } </style>
	 * 	class <b>MouseMovementUpdates</b> : a mouse movement listener <br>
	 * 	<tab>for updating the current pages mouse movement events <br>
	 */
	private class MouseMovementUpdates implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			// if the current page exists
			if (currentPage == null) return;
			// call the current page's mouseMovementEvent()
			currentPage.mouseMovementEvent(e);
		}
		
	}

	/**
	 * 	<style> tab{ margin-left: 30px; } </style>
	 * 	class <b>MouseUpdates</b> : a mouse listener designed to update a <br>
	 * 	<tab> ButtonInfo Object with teh left mouse button's information <br>
	 */
	private class MouseUpdates implements MouseListener {
		// a timer to add delayed deactivation
		Timer clickTimer = new Timer(); 
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			// if the button pressed is the left mouse button 
			if (e.getButton() == 1) {
					// update the buttons variable
					leftMouseInfo.isDown = true; 
					// let the mouse click
					leftMouseInfo.canClick = true; 
					
					// set canClick to false after 220 ms
					clickTimer.schedule(
						new TimerTask() {
							public void run() {
								// set canClick to false
								leftMouseInfo.canClick = false;
							}
						}, 
						220
					);
					
			}
		}
		public void mouseReleased(MouseEvent e) {
		
			// if the left mouse button is down and the 
			// released button was the left mouse button
			if (leftMouseInfo.isDown && e.getButton() == 1) {
				// check if the button is able to click
				if (leftMouseInfo.canClick) {
					// set clicked to true
					leftMouseInfo.clicked = true;
					// set clicked to false after 10 ms
					clickTimer.schedule(new TimerTask() {
						public void run() {
							// set clicked to false
							leftMouseInfo.clicked = false;
						}
					}, 10);
					
				} 
				// the left mouse button was released
				leftMouseInfo.isDown = false; 
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	
	/**
	 * <style> tab{ margin-left: 30px; } </style>
	 * 	class <b>WindowUpdates</b>: a component listener that<br>
	 *	<tab> calls update() on the current page<br>
	 */
	public class WindowUpdates implements ComponentListener {

		public void componentResized(ComponentEvent e) {
			// do not proceed if the current page is null
			if (currentPage == null) return; 
			// update the current page
			currentPage.update(window);
		}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		
	}
	
	/* Variables */

	// the currently open page
	Page currentPage; 
	// stores info about the mouse
	ButtonInfo leftMouseInfo = new ButtonInfo(); 
	// this object
	Window window = this; 

	/* Methods */
	
	/**
	 * createPage() : creates a new (Page)
	 * 
	 * @return a new (Page) with default settings
	 */
	public Page createPage() {
		// create a new page
		Page newPage = new Page(); 
			// make the page opaque
			newPage.setOpaque(true); 
			// default the pages background color to black
			newPage.setBackground(Color.BLACK); 
			// update the page
			newPage.update(window); 
			// render the page
			newPage.paint(getGraphics()); 
		// return the new page
		return newPage; 
	}
	
	/**
	 * delay(int timeMS) : delays the current thread for 
	 * the given amount of milliseconds
	 * 
	 * @param timeMS : the amount of time to delay the 
	 * current thread by in milliseconds
	 */
	public void delay(int timeMS) {
		try {
			// put the thread to sleep for timeMS milliseconds
			Thread.sleep(timeMS); 
		} catch (InterruptedException e) {
			//delay was impossible
		}
	}
	
	/**
	 * setCurrentPage(Page page) : changes the current page to the given page
	 * 
	 * @param page : the page to replace the current page with
	 */
	public void setCurrentPage(Page page) {
		// obtain the old page
		Page oldPage = currentPage; 
		// if the old page exists
		if (oldPage != null) { 
			// remove it from the window
			window.remove(oldPage); 
		}
		// set the current page to the new page
		currentPage = page; 
		// end the method if the new page is null
		if (currentPage == null) return; 
		// add the new page to the window
		window.add(currentPage); 
		// update the page
		currentPage.update(window); 
		// make sure the page covers the window
		currentPage.setSize(window.getContentPane().getSize()); 
		// update the visuals
		window.repaint(); 
		
	}
	
	/**
	 * 	updateColor(Component component, int selection) : colors a components
	 *	 background based on a selection
	 * 
	 * 	@param component : the component to color
	 * 	@param selection : what color to set the component to
	 */
	public static void updateColor(Component component, int selection) {
		if (selection == 1) {
			// set the background color to black
			component.setBackground(Color.BLACK);
		} else if (selection == 2) {
			// set the background color to an extremely dark gray
			component.setBackground(Color.getHSBColor(0, 0, 0.07f));
		} else { 
			// set the background color to an very dark gray
			component.setBackground(Color.getHSBColor(0, 0, 0.14f));
		}
	}
	
	/* Contructors */
	
	/**
	 * Window() : creates a new default game window
	 */
	public Window() {
		
		// end the program when the window is closed
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// make the window visible
		window.setVisible(true);
		// create a default size for the window
		window.setSize(500,500);
		// center the window on the screen
		window.setLocationRelativeTo(null);

		// create a new thread for the mouse and window listeners
		Thread listenersThread = new Thread(
			new Runnable() {
				public void run() {
					// listen for window updates
					window.addComponentListener(new WindowUpdates()); 
					// listen for mouse inputs
					window.addMouseListener(new MouseUpdates());
					// listen for mouse movement
					window.addMouseMotionListener(new MouseMovementUpdates());
				}
			}
		);
		listenersThread.start(); // start the listener thread
		
	}
	
}
