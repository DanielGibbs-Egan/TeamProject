import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Daniel Gibbs-Egan
 *
 * Window() creates a window for visualizing 
 * the game.
 *
 * Helper Classes
 *
 * Variables
 *
 * Constructors
 *
 */

public class Window extends JFrame {
	
	// add a serialVersionUID to supress warnings
	private static final long serialVersionUID = 1L;

	/* Helper Classes */
	
	public class ButtonInfo {
		public boolean isLeftMouseDown;
		public boolean leftMouseClicked;
		public boolean canLeftMouseClick;
		
		public ButtonInfo() {
			isLeftMouseDown = false;
			leftMouseClicked = false;
			canLeftMouseClick = false;
		}
	}
	
	public class WindowUpdates implements ComponentListener {

		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentResized(ComponentEvent e) {
			currentPage.update(window);
		}
		public void componentShown(ComponentEvent e) {}
		
	}
	
	private class MouseUpdates implements MouseListener {

		Timer clickTimer = new Timer();
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			// if the button pressed is the left mouse button 
			if (e.getButton() == 1) {
					buttonInfo.isLeftMouseDown = true; // update the variable
					
					buttonInfo.canLeftMouseClick = true;
					clickTimer.schedule(new TimerTask() {
						@Override
						public void run() {
							buttonInfo.canLeftMouseClick = false;
						}
					}, 220);
					
			}
		}
		public void mouseReleased(MouseEvent e) {
		
			// if the left mouse button is down and the 
			// button released was the left mouse button
			if (buttonInfo.isLeftMouseDown && e.getButton() == 1) {
				if (buttonInfo.canLeftMouseClick) {
					buttonInfo.leftMouseClicked = true;
					clickTimer.schedule(new TimerTask() {
						@Override
						public void run() {
							buttonInfo.leftMouseClicked = false;
						}
					}, 10);
					
				} 
				buttonInfo.isLeftMouseDown = false; // The left mouse button has been released
				//updateBarColor(e);
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	
	private class MouseMovementUpdates implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			currentPage.mouseMovement(e);
		}
		
	}
	
	/* Variables */
	
	ButtonInfo buttonInfo = new ButtonInfo(); // stores info about the mouse
	
	Window window; // this object
	
	Page currentPage; // the currently open page
	
	Page mainMenu; // the game page

	Page gamePage; // the game page
	DialogueBox dialogueBox; // the text box

	/* Methods */
	
	public void delay(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch (InterruptedException e) {
			//delay was impossible
		}
	}
	
	public boolean getLeftMouseDown() {
		return buttonInfo.isLeftMouseDown;
	}
	
	public void setCurrentPage(Page page) {
		
		Page oldPage = currentPage;
		if (oldPage != null) {
			oldPage.getParent().remove(oldPage);;
			oldPage.update(window);
		}
		this.currentPage = page;
		window.add(currentPage);
		currentPage.update(window);
		window.repaint();
		System.out.println("currentPage: "+currentPage);
		
		
	}
	
	/* Contructors */
	
	public Window() {
		
		window = this;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		
		mainMenu = new Page(this);
		mainMenu.setOpaque(true);
		mainMenu.setBackground(Color.BLACK);
		mainMenu.update(window);
		currentPage = mainMenu;
		window.add(mainMenu);
		
		gamePage = new Page(this);
		gamePage.setOpaque(true);
		gamePage.setBackground(Color.GRAY);
		gamePage.update(window);
		
		JButton b = new JButton();
		mainMenu.add(b);
		b.setBounds(0, 0, 100, 100);
		b.setText("Start");
		b.addMouseListener(new MouseListener() {
			
			boolean isDown = false;
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (isDown) {
					setCurrentPage(gamePage);
				}
				isDown = false;
			}
			public void mousePressed(MouseEvent e) {
				isDown = true;
			}
			public void mouseExited(MouseEvent e) {
				isDown = false;
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		
		JLabel hoverBar = new JLabel();
		hoverBar.setBackground(Color.WHITE);
		hoverBar.setOpaque(true);
		hoverBar.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
		
		dialogueBox = new DialogueBox(this, gamePage, hoverBar);
		
		gamePage.setBackground(Color.GRAY);
		
		Layout layout = new Layout(true);
		layout.add(dialogueBox, new Rectangle2D.Double(0,0,1,.5), new Rectangle(0,0,0,0));
		layout.add(hoverBar, new Rectangle2D.Double(0,0,1,0), new Rectangle(0,0,0,25));
		
		gamePage.layout = layout;

		gamePage.add(dialogueBox);
		gamePage.add(hoverBar);

		Thread t = new Thread(
			new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					window.addComponentListener(new WindowUpdates());
					window.addMouseListener(new MouseUpdates());
					window.addMouseMotionListener(new MouseMovementUpdates());
				}
			}
		);
		t.start();
		setCurrentPage(mainMenu);
		
	}
	
}
