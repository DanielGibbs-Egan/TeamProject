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
		Bachelor One = new Bachelor("Alex", Bachelor.randomNum(0,3), Bachelor.randomNum(0, 3));
		names[0] = One.getName();
		Bachelor Two = new Bachelor("Chase", Bachelor.randomNum(0,3), Bachelor.randomNum(0,3));
		names[1] = Two.getName();
		Bachelor Three = new Bachelor("Sirius", Bachelor.randomNum(0,3), Bachelor.randomNum(0,3));
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
		JLabel title = new JLabel("Lovers or Not?");
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

		dialogueBox.addDialogue("Welcome! Will you be able to find love?");	

		//dialogueBox.addDialogue("Hello World!, Dialogue Text Box Testing! Hello World!, Dialogue Text Box Testing!");	


		// ask the player for a string input
		String input = dialogueBox.askQuestion("What is your name?", gamePage.layout);
		
		// respond by clarifiying the player's input
		dialogueBox.addDialogue("Nice to meet you, " + input + ".");
		// create a new array of questions
		String[] questions0 = {"Alex","Chase","Sirius"};
		// ask a question and give an array of possible answers
		Integer selection0 = dialogueBox.addQuestionaire(input +", choose your path in the game. "
				+ "Whom do you want to pursue a relationship with?", gamePage.layout, questions0);
		
		int randomCandidate = selection0;
		String date = names[randomCandidate];
		String likedLetter = null;
		String dislikedLetter = null;
		String goodPresent = null;
		String badPresent = null;
		String goodDate = null;
		String badDate = null;
		if(randomCandidate == 0) {
			if(One.getTraitOne() == 0) {
				likedLetter = "coffee";
				goodPresent = "a pack of Colombian Arabica coffee";
				goodDate = "Starbucks roastery";
			}
			if(One.getTraitOne() == 1) {
				likedLetter = "animals";
				goodPresent = "a kitten";
				goodDate = "aquarium";
			}
			if(One.getTraitOne() == 2) {
				likedLetter = "playing sports";
				goodPresent = "a ticket to a baseball game";
				goodDate = "ice skating rink";
			}
		}

		if(randomCandidate == 1) {
			if(Two.getTraitOne() == 0) {
				likedLetter = "coffee";
				goodPresent = "a pack of Colombian Arabica coffee";
				goodDate = "Starbucks roastery";
			}
			if(Two.getTraitOne() == 1) {
				likedLetter = "animals";
				goodPresent = "a kitten";
				goodDate = "aquarium";
			}
			if(Two.getTraitOne() == 2) {
				likedLetter = "playing sports";
				goodPresent = "a ticket to a baseball game";
				goodDate = "ice skating rink";
			}
		}
		
		if(randomCandidate == 2) {
			if(Three.getTraitOne() == 0) {
				likedLetter = "coffee";
				goodPresent = "a pack of Colombian Arabica coffee";
				goodDate = "Starbucks roastery";
			}
			if(Three.getTraitOne() == 1) {
				likedLetter = "animals";
				goodPresent = "a kitten";
				goodDate = "aquarium";
			}
			if(Three.getTraitOne() == 2) {
				likedLetter = "playing sports";
				goodPresent = "a ticket to a baseball game";
				goodDate = "ice skating rink";
			}
		}
		if(likedLetter == "coffee") {
			if(Bachelor.randomNum(0, 2) == 0) {
				dislikedLetter = "animals";
				badPresent = "a kitten";
				badDate = "aquarium";
			}
			else {
				dislikedLetter = "playing sports";
				badPresent = "a ticket to a baseball game";
				badDate = "ice skating rink";
			}
		}
		if(likedLetter == "animals") {
			if(Bachelor.randomNum(0, 2) == 0) {
				dislikedLetter = "playing sports";
				badPresent = "a ticket to a baseball game";
				badDate = "ice skating rink";
			}
			else {
				dislikedLetter = "coffee";
				badPresent = "a pack of Colombian Arabica coffee";
				badDate = "Starbucks roastery";
			}
		}
		if(likedLetter == "playing sports") {
			if(Bachelor.randomNum(0, 2) == 0) {
				dislikedLetter = "coffee";
				badPresent = "a pack of Colombian Arabica coffee";
				badDate = "Starbucks roastery";
			}
			else {
				dislikedLetter = "animals";
				badPresent = "a kitten";
				badDate = "aquarium";
			}
		}
		
		
		dialogueBox.addDialogue( input + ", you will be dating " + date + ". After the two of you met and "
				+ "chatted, you learn that he loves " + likedLetter + " but dislikes " + dislikedLetter + ".");
		
		dialogueBox.addDialogue("He is very funny and outgoing, so the two of you quickly "
				+ "became friends. As luck would have it, " + date + " lives near your place, so you "
				+ "and him often hang out after work.");	
		
		// create a new array of questions
		String[] questions = {"a pack of Colombian Arabica coffee","a kitten","a ticket to a baseball game","a new pair of socks"};
		// ask a question and give an array of possible answers
		Integer selection = dialogueBox.addQuestionaire("Months flew by, and today it is " + date + "'s birthday. You want to give him something special."
				+ " What should you pick to be his present?", gamePage.layout, questions);

		// respond by clarifiying the player's input by obtaining the 
		// information stored at the selections index in the array
		dialogueBox.addDialogue("You decided to give " + date + " " + questions[selection] + ".");
		if(questions[selection] == goodPresent) {
			dialogueBox.addDialogue(date + " was very happy. He loves spending time with you.");
			a.newEvent(names[randomCandidate], 0, 1);
		}
		if(questions[selection] == badPresent) {
			dialogueBox.addDialogue("Uh oh, " + date + " didn't seem to like your present. Better luck next time.");
			a.newEvent(names[randomCandidate], 0, -2);
		}
		else {
			a.newEvent(names[0], 1, 1);
		}

		// create a new array of questions
		String[] questions2 = {"ice skating rink", "aquarium", "Starbucks roastery","cinema"};
		// ask a question and give an array of possible answers
		Integer selection2 = dialogueBox.addQuestionaire("Not long after, you propose to " + date + " that the two of you go on a date. "
				+ "Where will your date be?", gamePage.layout, questions2);

		// respond by clarifiying the player's input by obtaining the 
		// information stored at the selections index in the array
		dialogueBox.addDialogue("You and " + date + " went to the " + questions2[selection2] + ".");
		if(questions2[selection2] == goodDate ) {

			dialogueBox.addDialogue("The two of you had a blast. " + date + " can't wait to go on another date with you.");
			a.newEvent(names[randomCandidate], 0, 1);
		}
		if(questions2[selection2] == badDate) {
			dialogueBox.addDialogue(date + " didn't enjoy the date. It was very awkward back there.");
			a.newEvent(names[randomCandidate], 0, -1);
		}
		else {
			a.newEvent(names[randomCandidate], 1, 1);
		}
		
		if(a.hasReachedScore(names[randomCandidate], 0, 2)) {
			dialogueBox.addDialogue("You and " + date + " are now officially lovers!");
			dialogueBox.addDialogue(b.dialogueMap().get(1));
			dialogueBox.addDialogue(b.dialogueMap().get(Bachelor.randomNum(11,32)));
		}
		else if(a.hasReachedScore(names[randomCandidate], 0, -1)) {
			dialogueBox.addDialogue(date + " rejects your proposal to be lovers. Better luck next time.");
		}
		else {
			dialogueBox.addDialogue(date + " doesn't have any romantic feelings for you, but the two of you are good friends.");
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
