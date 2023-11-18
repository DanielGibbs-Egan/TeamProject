import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class DanielsFile {
	public static int a = 1;
	public static int randomInt(int low, int high) {
		                                 // 5 - 1       
		int random = (int)(Math.random()*(high-low)) + low;
		
		return random;
		
	}
	
	
	public static void main(String[] args) {
		// Hello World!
		// Prints my Name!
		System.out.println("Daniel Gibbs-Egan");
		System.out.println(randomInt(0,10));
		
		// create a new window
		Window window = new Window();
		window.setBackground(Color.WHITE);
		
		window.addDialogue("Hello World!, Dialogue Text Box Testing!");	
		window.addDialogue("Goodbye!");
	}

	
}
