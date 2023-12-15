import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NicolesCopyOfDanielsFile {
	
	public static int a = 1;
	public static int randomInt(int low, int high) {
		int random = (int)(Math.random()*(high-low));
		return random + low;
		
	}
	
	public static void main(String[] args) {
		DialogueStuff b = new DialogueStuff();
		b.dialogueMapFill();
		String[] names = new String[3];
		Bachelor One = new Bachelor("Homer", Bachelor.randomNum(0,3), Bachelor.randomNum(0, 3));
		names[0] = One.getName();
		Bachelor Two = new Bachelor("Hank", Bachelor.randomNum(0,3), Bachelor.randomNum(0,3));
		names[1] = Two.getName();
		Bachelor Three = new Bachelor("Peter", Bachelor.randomNum(0,3), Bachelor.randomNum(0,3));
		names[2] = Three.getName();
		RelationshipTracker a = new RelationshipTracker();
		a.fillOutMap(names);
		// Hello World!
		// Prints my Name!
		//System.out.println("Daniel Gibbs-Egan");
		//System.out.println(randomInt(0,10));
		
		// create a new window
		Window window = new Window();
		
		// create the main game page
		Page gamePage = window.createPage();

		// create the end game page
		Page endPage = window.createPage();

		// create the main menu
		Page mainMenu = window.createPage();
		
		// set the current page to the main menu
		window.setCurrentPage(mainMenu);
		
		/* Main Menu */
		
		// create a label to show the title of the game
		JLabel title = new JLabel("Potato Dating");
		// set the font to monospaced
		title.setFont(new Font("Monospaced", 1, 36));
		// set the text color to white
		title.setForeground(Color.WHITE);
		// center the text in the label
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		// add the title label to the main menu
		mainMenu.add(title);
		// add the title label to the main menu's layout
		mainMenu.layout.add(title, new Rectangle2D.Double(0,0.5,1,0.1), new Rectangle(0,0,0,0));
		mainMenu.layout.add(title, 36);
		
		// create a button to start the game
		JLabel startButton = new JLabel("Start");
		// center the text in the button
		startButton.setHorizontalAlignment(SwingConstants.CENTER);

		// Stylize the start button
		DialogueBox.stylizeComponent(startButton, true);
		// Make the border larger
		startButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.WHITE));
		
		// listen for mouse events
		startButton.addMouseListener( 
			new Window.ButtonListener(
				new Runnable() {
					// if the button is clicked
					public void run() {
						// set the current page to the game page 
						 window.setCurrentPage(gamePage);
					}
				}
			)
		);
		
		// add the start button to the main menu
		mainMenu.add(startButton);
		// add the start button to the main menu's layout
		mainMenu.layout.add(startButton, new Rectangle2D.Double(0.475,0.475,0.05,0.05), new Rectangle(-75,0,150,25));
		// add the start button's text to the main menu's layout
		mainMenu.layout.add(startButton, 24);

		/* Game Page */
		
		// create the interaction indicator bar that 
		// appears at the bottom of the dialogue box
		JLabel hoverBar = new JLabel();
		hoverBar.setOpaque(true);
		// surround the bar with a border to disconnect it from the sides
		hoverBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		
		// create the box to show the text in
		DialogueBox dialogueBox = new DialogueBox(window, gamePage, hoverBar);

		// add the dialogue box and hover bar to the game page
		gamePage.add(dialogueBox);
		gamePage.add(hoverBar);

		// add the dialogue box's text to the game page's layout
		gamePage.layout.add(dialogueBox, 24);

		// add the dialogue box and the hover bar to the game page's layout
		gamePage.layout.add(dialogueBox, new Rectangle2D.Double(0,0,1,1), new Rectangle(0,0,0,0));
		gamePage.layout.add(hoverBar, new Rectangle2D.Double(0,0,1,0), new Rectangle(0,0,0,25));
		
		/* End Game Page*/
		
		// create a text pane to display end game information
		JLabel displayText = new JLabel();
		
		// stylize the component
		DialogueBox.stylizeComponent(displayText, true);
		// remove the border
		displayText.setBorder(null);
		// center the text
		displayText.setHorizontalAlignment(SwingConstants.CENTER);
	
		// add the text pane to the end game page
		endPage.add(displayText);
		
		// add the text pane and its text to the end game page's layout
		endPage.layout.add(displayText, new Rectangle2D.Double(0,0,1,1), new Rectangle(0,0,0,0));
		endPage.layout.add(displayText, 36);

		// add dialogue to the box

		dialogueBox.addDialogue("Welcome to Couch Potatoes' Dating Simulator");	

		//dialogueBox.addDialogue("Hello World!, Dialogue Text Box Testing! Hello World!, Dialogue Text Box Testing!");	


		// ask the player for a string input
		String input = dialogueBox.askQuestion("What is your name?", gamePage.layout);
		
		// respond by clarifiying the player's input
		dialogueBox.addDialogue("Your Name Is: " + input);
		// create a new array of questions
		String[] questions0 = {"Homer","Hank","Peter"};
		// ask a question and give an array of possible answers
		Integer selection0 = dialogueBox.addQuestionaire(input +", who do you want to date?", gamePage.layout, questions0);
		
		int randomCanidate = selection0;
		String date = names[randomCanidate];
		String likedLetter = null;
		String dislikedLetter = null;
		if(randomCanidate == 0) {
			if(One.getTraitOne() == 0) {
				likedLetter = "A";
			}
			if(One.getTraitOne() == 1) {
				likedLetter = "B";
			}
			if(One.getTraitOne() == 2) {
				likedLetter = "C";
			}
		}

		if(randomCanidate == 1) {
			if(Two.getTraitOne() == 0) {
				likedLetter = "A";
			}
			if(Two.getTraitOne() == 1) {
				likedLetter = "B";
			}
			if(Two.getTraitOne() == 2) {
				likedLetter = "C";
			}
		}
		
		if(randomCanidate == 2) {
			if(Three.getTraitOne() == 0) {
				likedLetter = "A";
			}
			if(Three.getTraitOne() == 1) {
				likedLetter = "B";
			}
			if(Three.getTraitOne() == 2) {
				likedLetter = "C";
			}
		}
		if(likedLetter == "A") {
			if(Bachelor.randomNum(0, 2) == 0) {
				dislikedLetter = "B";
			}
			else {
				dislikedLetter = "C";
			}
		}
		if(likedLetter == "B") {
			if(Bachelor.randomNum(0, 2) == 0) {
				dislikedLetter = "C";
			}
			else {
				dislikedLetter = "A";
			}
		}
		if(likedLetter == "C") {
			if(Bachelor.randomNum(0, 2) == 0) {
				dislikedLetter = "A";
			}
			else {
				dislikedLetter = "B";
			}
		}
		
		
		dialogueBox.addDialogue( input + ", You will be dating " + date + " (hint: he loves the letter " + likedLetter + " but HATES the letter " + dislikedLetter + ")");
		
		// create a new array of questions
		String[] questions = {"A","B","C"};
		// ask a question and give an array of possible answers
		Integer selection = dialogueBox.addQuestionaire("A, B, or C", gamePage.layout, questions);

		// respond by clarifiying the player's input by obtaining the 
		// information stored at the selections index in the array
		dialogueBox.addDialogue("You Chose: " + questions[selection]);
		if(questions[selection] == likedLetter) {

			dialogueBox.addDialogue(date + " liked that. Good choice");
			a.newEvent(names[randomCanidate], 0, 1);
		}
		if(questions[selection] == dislikedLetter) {
			dialogueBox.addDialogue("Uh oh, " + date + " didn't like that");
			a.newEvent(names[randomCanidate], 0, -2);
		}
		else {
			a.newEvent(names[0], 1, 1);
		}

		// create a new array of questions
		String[] questions2 = {"A", "B", "C", "D"};
		// ask a question and give an array of possible answers
		Integer selection2 = dialogueBox.addQuestionaire("A,B,C, or D", gamePage.layout, questions2);

		// respond by clarifiying the player's input by obtaining the 
		// information stored at the selections index in the array
		dialogueBox.addDialogue("You Chose: " + questions2[selection2]);
		if(questions2[selection2] == likedLetter ) {

			dialogueBox.addDialogue(date + " liked that. Good choice");
			a.newEvent(names[randomCanidate], 0, 1);
		}
		if(questions2[selection2] == dislikedLetter) {
			dialogueBox.addDialogue("Uh oh, " + date + " didn't like that");
			a.newEvent(names[randomCanidate], 0, -2);
		}
		else {
			a.newEvent(names[randomCanidate], 1, 1);
		}
		
		if(a.hasReachedScore(names[randomCanidate], 0, 2)) {
			dialogueBox.addDialogue("You have romanced " + date);
			dialogueBox.addDialogue(b.dialogueMap().get(1));
			dialogueBox.addDialogue(b.dialogueMap().get(Bachelor.randomNum(11,32)));
		}
		else if(a.hasReachedScore(names[randomCanidate], 0, -1)) {
			dialogueBox.addDialogue(date + " hates you");
		}
		else {
			dialogueBox.addDialogue(date + " likes you better as a friend");
		}
		
		
		// display text on the end game page
		displayText.setText(
				"<html><center>"
				+ "Thank You<br>"
				+ " For Playing!"
//				+ input + 
//				", " + questions[selection] + 
//				", " + questions2[selection2] + 
				+ "</center></html>"
				);
		
		// set the page to the end game page
		window.setCurrentPage(endPage);
		
		
	}

	
}
