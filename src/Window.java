import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private static class mouseUpdates implements MouseListener {
	
				// Declare Variables //
		
		// isLeftMouseDown stores if the left button has been pressed
		boolean isLeftMouseDown = false; 
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			// if the button pressed is the left mouse button 
			if (e.getButton() == 1) {
				isLeftMouseDown = true; // update the variable
			}
		}
		public void mouseReleased(MouseEvent e) {
		
			// if the left mouse button is down and the 
			// button released was the left mouse button
			if (isLeftMouseDown && e.getButton() == 1) {
				//System.out.println(e.getPoint());
				System.out.println("click!"); // print "click!"
				isLeftMouseDown = false; // The left mouse button has been released
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}
	
	private JLabel textbox;
	
	//private Queue<String> queue = new qu<>();

	public void delay(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch (InterruptedException e) {
			//delay was impossible
		}
	}
	
	public void addDialogue(String text, int timeBetweenLettersMS, int timeAfterFinishedMS) {
		for (int i = 0; i <= text.length(); i++) {
			textbox.setText(text.substring(0, i));
			delay(timeBetweenLettersMS);
		}
		delay(timeAfterFinishedMS);
	}
	
	public void addDialogue(String text) {
		addDialogue(text, 100, 300);
	}
	
	public Window() {
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1000,1000);
		this.setVisible(true);
		
		// create a mouse listener for the window
		MouseListener mouseListener = new mouseUpdates();
		this.addMouseListener(mouseListener);
		
		// create the main frame for the window
		JLabel frame = new JLabel();
		frame.setBackground(Color.WHITE);
		frame.setOpaque(true);
		
		this.add(frame);
		
		// add the main text box
		textbox = new JLabel();
		textbox.setBackground(Color.BLACK);
		textbox.setOpaque(true);
		
		//textbox.setText("Hello World!");
		textbox.setFont(new Font("Monospaced",1,24));
		textbox.setForeground(Color.WHITE);
		
		textbox.setVerticalAlignment(1);
		textbox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		
		Dimension p = this.getSize();
		textbox.setBounds(0, p.height*2/3, p.width, p.height/3); // set the labels size
		frame.add(textbox);
		
		
	}
	
}