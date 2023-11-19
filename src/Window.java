import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Window extends JFrame {

	private int bottomOffset = 60;

	boolean isLeftMouseDown = false;
	boolean leftMouseClicked = false;

	long lastLeftMouseDown = System.currentTimeMillis();
	long lastLeftMouseClick = System.currentTimeMillis();
	
	private class MouseUpdates implements MouseListener {
	

		@Override
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			// if the button pressed is the left mouse button 
			if (e.getButton() == 1) {
				lastLeftMouseDown = System.currentTimeMillis();
				if( e.getPoint().y > textbox.getLocation().y+bottomOffset/2) {
					isLeftMouseDown = true; // update the variable
					bar.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
		public void mouseReleased(MouseEvent e) {
		
			// if the left mouse button is down and the 
			// button released was the left mouse button
			if (isLeftMouseDown && e.getButton() == 1) {
				//System.out.println(e.getPoint());
				//System.out.println("click!"); // print "click!"
				if (lastLeftMouseDown  + 250 > System.currentTimeMillis()) {
					leftMouseClicked = true;
					lastLeftMouseClick = System.currentTimeMillis();
				}
				isLeftMouseDown = false; // The left mouse button has been released
				updateBarColor(e);
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	
	private void updateBarColor(MouseEvent e) {
		if (e.getPoint().y > textbox.getLocation().y+bottomOffset/2) {
			bar.setBackground(Color.WHITE);
		} else {
			bar.setBackground(Color.BLACK);
		}
	}
	
	private class MouseMoveUpdates implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			updateBarColor(e);
		}
		
	}
	
	private class WindowUpdates implements ComponentListener{

		@Override
		public void componentResized(ComponentEvent e) {
			textbox.setBounds(window.getBounds());
			
			Dimension p = window.getSize();
			//bottomBarHeight = 3*window.getWidth()/50;
			textbox.setBounds(0, p.height*5/9-bottomOffset, p.width, p.height*4/9); // set the labels size
			bar.setBounds(0, p.height-bottomOffset-1, p.width-bottomOffset/4, bottomOffset/3+2); // set the labels size
		}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		public void componentHidden(ComponentEvent e) {}
		
	}
	
	private Window window = this;
	private JLabel frame;
	private JLabel textbox;
	JLabel bar;
	
	//private Queue<String> queue = new qu<>();

	public void delay(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch (InterruptedException e) {
			//delay was impossible
		}
	}
	
	public void addDialogue(String text, int timeBetweenLettersMS, int timeAfterFinishedMS) {
		
		String[] words = text.split(" ");
		text = "";
		
		int length = 0;
		for (String word: words) {
			if (length + word.length() < 68/1000*window.getWidth()) {
				text += " " + word;
				length += word.length();
			} else {
				text += " " + word;
				length = 0;
			}
		}
		
		for (int i = 0; i <= text.length(); i++) {
			textbox.setText("<html>" + text.substring(0, i) + "</html>");
			delay(timeBetweenLettersMS/((isLeftMouseDown)? 10:1));
		}
		while (!(leftMouseClicked && lastLeftMouseClick + 100 > System.currentTimeMillis())) {
			delay(1);
		}
		leftMouseClicked = false;
	}
	
	public void addDialogue(String text) {
		addDialogue(text, 100, 300);
	}
	
	public void setBackgroundColor(Color c) {
		frame.setBackground(c);
	}
	
	public Window() {
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1000,1000);
		this.setVisible(true);
		
		
		// create the main frame for the window
		frame = new JLabel();
		frame.setBackground(Color.WHITE);
		frame.setOpaque(true);
		
		this.add(frame);
		
		// add the main text box
		textbox = new JLabel();
		textbox.setBackground(Color.BLACK);
		textbox.setOpaque(true);
		
		//textbox.setText("Hello World!");
		textbox.setFont(new Font("Monospaced", 1, 24));
		textbox.setForeground(Color.WHITE);
		
		textbox.setVerticalAlignment(1);
		textbox.setBorder(BorderFactory.createMatteBorder( 10, 15, 15, 15, Color.BLACK));
		
		// interaction bar
		bar = new JLabel();
		bar.setBackground(Color.BLACK);
		bar.setOpaque(true);
		bar.setBorder(BorderFactory.createLineBorder(Color.BLACK, bottomOffset*3/21));
		
		
		Dimension p = this.getSize();
		textbox.setBounds(0, p.height*2/3-bottomOffset, p.width, p.height/3); // set the labels size
		bar.setBounds(0, p.height-bottomOffset*2, p.width-bottomOffset/5, bottomOffset*4/5); // set the labels size
		
		frame.add(textbox);
		frame.add(bar);
		

		// create a mouse listener for the window
		MouseListener mouseListener = new MouseUpdates();
		this.addMouseListener(mouseListener);
		
		WindowUpdates windowListener = new WindowUpdates();
		this.addComponentListener(windowListener);
		
		MouseMoveUpdates mouseMovementListener = new MouseMoveUpdates();
		this.addMouseMotionListener(mouseMovementListener);
		
	}
	
}