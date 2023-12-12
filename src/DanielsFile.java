import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		
		Page gamePage = window.createPage();
		gamePage.setBackground(Color.getHSBColor(0, 0, 0.05f));
		
		Page endPage = window.createPage();
		endPage.setBackground(Color.BLACK);

		Page mainMenu = window.createPage();
		mainMenu.setBackground(Color.BLACK);
		window.setCurrentPage(mainMenu);
		
		JLabel title = new JLabel("Game Title");
		title.setFont(new Font("Monospaced", 1, 36));
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		mainMenu.add(title);
		mainMenu.layout.add(title, new Rectangle2D.Double(0,.5,1,.1), new Rectangle(0,0,0,0));
		
		JButton startButton = new JButton("Start");
		
		startButton.setFont(new Font("Monospaced", 1, 24));
		//startButton.setOpaque(true); // Make the button background visible

		startButton.setForeground(Color.WHITE); 
		startButton.setBackground(Color.BLACK);
		
		startButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.WHITE));
		
		startButton.removeMouseListener(startButton.getMouseListeners()[0]);
		startButton.addMouseListener( 
			new Window.ButtonListener(
				new Runnable() {
					public void run() {
						 window.setCurrentPage(gamePage);
					}
				}
			)
		);

		mainMenu.add(startButton);
		mainMenu.layout.add(startButton, new Rectangle2D.Double(0.5,0.5,0,0), new Rectangle(-100,15,200,50));

		JLabel hoverBar = new JLabel();
		hoverBar.setBackground(Color.WHITE);
		hoverBar.setOpaque(true);
		hoverBar.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
		
		DialogueBox dialogueBox = new DialogueBox(window, gamePage, hoverBar);

		gamePage.add(dialogueBox);
		gamePage.add(hoverBar);

		gamePage.layout.add(dialogueBox, 24);
		
		gamePage.layout.add(dialogueBox, new Rectangle2D.Double(0,0,1,.5), new Rectangle(0,0,0,0));
		gamePage.layout.add(hoverBar, new Rectangle2D.Double(0,0,1,0), new Rectangle(0,0,0,25));
		
		JLabel gameOver = new JLabel();
		gameOver.setFont(new Font("Monospaced", 1, 36));
		gameOver.setForeground(Color.WHITE);
		gameOver.setHorizontalAlignment(SwingConstants.CENTER);
		endPage.add(gameOver);
		
		Layout endLayout = endPage.layout;
		endLayout.add(gameOver, new Rectangle2D.Double(0,.45,1,.1), new Rectangle(0,0,0,0));
		endLayout.add(gameOver, 36);
		//endLayout.update(window);

		dialogueBox.addDialogue("Hello World!, Dialogue Text Box Testing!");	
		
		String input = dialogueBox.askQuestion("What is your name?", gamePage.layout);
		
		dialogueBox.addDialogue("Your Name Is: " + input);
		
		String[] questions = {"A","B","C"};
		Integer selection = dialogueBox.addQuestionaire("A, B, or C", gamePage.layout, questions);
		
		dialogueBox.addDialogue("You Chose: " + questions[selection]);
		
		String[] questions2 = {"a", "b", "c", "d"};
		Integer selection2 = dialogueBox.addQuestionaire("a,b,c, or d", gamePage.layout, questions2);

		dialogueBox.addDialogue("You Chose: " + questions2[selection2]);
		
		dialogueBox.addDialogue("Goodbye!");
		
		gameOver.setText("You Win!");
		
		window.setCurrentPage(endPage);
		
		
	}

	
}
