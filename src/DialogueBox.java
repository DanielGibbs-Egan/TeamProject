import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class DialogueBox extends JLabel {

	// add a serialVersionUID to supress warnings
	private static final long serialVersionUID = 1L;
	
	/* Helper Classes */
	
	// Question Button 
	public class Question extends JButton {
		
		// add a serialVersionUID to supress warnings
		private static final long serialVersionUID = 1L;

		public void destroy() {
			this.removeMouseListener(this.getMouseListeners()[0]);
			this.getParent().remove(this);
		}
		
		public Question(String s, int index) {
			
			super(s);

			this.removeMouseListener(this.getMouseListeners()[0]);

			this.addMouseListener(new ButtonListener(index));
			
			this.setOpaque(true); // Make the button background visible

			this.setForeground(Color.WHITE); 
			this.setBackground(Color.BLACK);
			
			// add a 
			this.setBorder(BorderFactory.createMatteBorder(2,4,2,4,Color.WHITE));
		}
		
	}
	
	private class ButtonListener implements MouseListener {
		
		boolean hovering = true;
		boolean down = false;
		
		int index;
		
		public ButtonListener(int index) {
			this.index = index;
		}
		
		public void updateColor(Component component) {
			if (down) {
				component.setBackground(Color.getHSBColor(0, 0, 0.14f));
			} else if (hovering) {
				component.setBackground(Color.getHSBColor(0, 0, 0.07f));
			} else { 
				component.setBackground(Color.getHSBColor(0, 0, 0));
			}
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (down) { 
				indexReturn = index;
			}
			down = false;
			updateColor(e.getComponent());
			
		}
		public void mousePressed(MouseEvent e) {
			down = true;
			updateColor(e.getComponent());
		}
		public void mouseExited(MouseEvent e) {
			down = false;
			hovering = false;
			updateColor(e.getComponent());
		}
		public void mouseEntered(MouseEvent e) {
			hovering = true;
			updateColor(e.getComponent());
		}
		public void mouseClicked(MouseEvent e) {}
	}

	/* Variables */
	
	double xScalar = 1, yScalar = 1/3d;

	private DialogueBox main;
	private JLabel hoverBar;
	private Window window;
	
	boolean askingQuestion = false;
	
	Page.EventRunnable hoverEdits = new Page.EventRunnable() {
		
		public void run(MouseEvent e) {
			
			Color color = Color.BLACK;
			
			Point location = e.getPoint();
			
			if (main.isVisible()) {
				if (isLocationInBounds(location)) {
					color = Color.WHITE;
				}
			}
			hoverBar.setBackground(color);
			
		}

	};
	
	public void addDialogue(String text, int timeBetweenLettersMS) {
		Point mousePosition = window.getMousePosition();
		for (int i = 0; i <= text.length(); i++) {
			setText("<html>" + text.substring(0, i) + "</html>");
			delay(timeBetweenLettersMS/((window.getLeftMouseDown())? 10:1));
		}
		if (!askingQuestion)
		while (window == null || !(window.buttonInfo.leftMouseClicked && isLocationInBounds(mousePosition))) {
			mousePosition = window.getMousePosition();
			delay(10);
		}
		
	}
	
	public void addDialogue(String text) {
		addDialogue(text, 100);
	}

	int indexReturn = -1;
	public int addQuestionaire(String question, Layout layout, String... choices) {
		
		askingQuestion = true;
		addDialogue(question);
		
		Queue<JButton> questionsList = new ConcurrentLinkedQueue<JButton>();
		
		for (int index = 0; index < choices.length; index++) {
			
			String choice = choices[index];
			
			JButton questionButton = new Question(choice, index);

			questionsList.add(questionButton);
			
			layout.add(questionButton, new Rectangle2D.Double(0,0,1,.05), new Rectangle(0,0,0,0));
			
			this.getParent().add(questionButton);
			
		}
		
		layout.update(window);
		
		while (indexReturn < 0) {
			delay(10);
		}
		
		for (int i = 0; i < choices.length; i++) {
			Question q = (Question) questionsList.remove();
			layout.remove(q);
			q.destroy();
		}
		
		layout.update(window);
		
		askingQuestion = false;

		int localIndexReturn = indexReturn;
		indexReturn = -1;
		
		return localIndexReturn;
		
		
	}
	
	public void delay(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch (InterruptedException e) {
			//delay was impossible
		}
	}
	
	public boolean isLocationInBounds(Point location) {

		if (location == null) return false;
		
		Point top = main.getLocation();
		top.translate(0, 30);
		
		Dimension sizeOfBar = hoverBar.getSize();
		Point bottom = hoverBar.getLocation();
		bottom.translate((int)sizeOfBar.getWidth(),(int)sizeOfBar.getHeight());
		
		if ((location.getX() > top.getX() && location.getY() > top.getY()) && 
				(location.getX() < bottom.getX() && location.getY() < bottom.getY())) {
				return true;
		}
		return false;
	}
	
	public DialogueBox() {

		main = this;
		
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
		
		this.setFont(new Font("Monospaced", 1, 24));
		this.setForeground(Color.WHITE);
		
		this.setVerticalAlignment(1);
		this.setBorder(BorderFactory.createMatteBorder( 10, 15, 15, 15, Color.BLACK));
		
	}
	
	public DialogueBox(Window window) {
		this();
		this.window = window;
	}
	
	public DialogueBox(Window window, Page page, JLabel hoverBar) {
		
		this(window);
		this.hoverBar = hoverBar;
		
		page.hoverEvents.add((Page.EventRunnable) hoverEdits);
		page.add(this);
		
	}
	
}
