import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

/*
 *	Daniel Gibbs-Egan
 *
 *	Window() creates a window for visualizing 
 *	the game.
 *
 *	helper classes :
 *		
 *	variables :
 *
 *	constructors :
 *
 */

public class Window extends JFrame {
	
	// add a serialVersionUID to supress warnings
	private static final long serialVersionUID = 1L;

	/* Helper Classes */
	
	/**
	 * 	<style>
	 * tab{
	 *		margin-left: 30px;
	 * }
	 * </style>
	 * 	ButtonInfo: stores information about the pc's mouse<br>
	 *	<br>
	 *	variables:<br> 	
	 *	<tab> isDown: the state of the left mouse button <br> 	
	 *	<tab> clicked: if the left mouse button has recently been clicked <br>	
	 *	<tab> canClick: if the left mouse button is able to click
	 *
	 */
	public class ButtonInfo {
		
		public boolean isDown = false;
		public boolean clicked = false;
		public boolean canClick = false;
		
	}
	
	/**
	 * WindowUpdates: a component listener for the application
	 */
	public class WindowUpdates implements ComponentListener {

		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentResized(ComponentEvent e) {
			// do not proceed if the current page is null
			if (currentPage == null) return; 
			// update the current page
			currentPage.update(window);
		}
		public void componentShown(ComponentEvent e) {}
		
	}
	
	/**
	 * MouseUpdates: a mouse listener for the application
	 */
	private class MouseUpdates implements MouseListener {
		// a timer to add delayed deactivation
		Timer clickTimer = new Timer(); 
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			// if the button pressed is the left mouse button 
			if (e.getButton() == 1) {
				
					leftMouseInfo.isDown = true; // update the buttons variable
					
					leftMouseInfo.canClick = true; // let the mouse click
					
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
	 * MouseMovementUpdates: a mouse movement listener for the application
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
	
	/* Variables */
	
	ButtonInfo leftMouseInfo = new ButtonInfo(); // stores info about the mouse
	
	Window window = this; // this object
	
	Page currentPage; // the currently open page

	/* Methods */
	
	/**
	 * delay(int timeMS) : delays the current thread for the given milliseconds
	 * 
	 * @param timeMS : the amount of time to delay in milliseconds
	 */
	public void delay(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch (InterruptedException e) {
			//delay was impossible
		}
	}

	/**
	 * createPage() : creates a new (Page)
	 * 
	 * @return a new (Page) with default settings
	 */
	public Page createPage() {
		Page newPage = new Page(); // create a new page
			newPage.setOpaque(true); // make the page opaque
			// default the pages background color to black
			newPage.setBackground(Color.BLACK); 
			newPage.update(window); // update the page
			newPage.paint(getGraphics()); // render the page
		return newPage; // return the new page
	}
	
	/**
	 * setCurrentPage(Page page) : changes the current page to the given page
	 * 
	 * @param page : the page to replace the current page with
	 */
	public void setCurrentPage(Page page) {
		
		Page oldPage = currentPage; // obtain the old page
		if (oldPage != null) { // if the old page exists
			window.remove(oldPage); // remove it from the window
		}
		currentPage = page; // set the current page to the new page
		if (currentPage == null) return; // end the method if the new page is null
		window.add(currentPage); // add the new page to the window
		currentPage.update(window); // update the page
		// make sure the page covers the window
		currentPage.setSize(window.getContentPane().getSize()); 
		window.repaint(); // update the visuals
		
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
