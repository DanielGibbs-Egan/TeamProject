import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 *	Daniel Gibbs-Egan
 *
 *	DialogueBox() creates a dialogue box for outputing information to the player
 *	and letting the player input information as well
 *
 *	helper classes :
 *		
 *	variables :
 *
 *	constructors :
 *
 */

public class DialogueBox extends JLabel {

	// add a serialVersionUID to supress warnings
	private static final long serialVersionUID = 1L;
	
	/* Variables */
	
	// the dialogue box
	private DialogueBox main = this; 
	// the bar at the bottom of the dialogue box
	private JLabel hoverBar;
	// the application window
	private Window window; 

	int indexOfChoice = -1;
	
	// is the application waiting for an answer
	boolean askingQuestion = false; 

	// the players input to answer a question
	String playerAnswer = null;
	
	// an event listener for mouse interactions
	Page.EventRunnable hoverEdits = new Page.EventRunnable() {
		
		public void run(MouseEvent e) {
			// default the color to black
			Color color = Color.BLACK;
			// store the mouses location
			Point location = e.getPoint();
			// if the dialogue box is visible
			if (main.isVisible()) {
				// check if the mouse is hovering the box
				if (isLocationInBounds(location)) {
					// if the mouse is hovering set the color to white
					color = Color.WHITE;
				}
			}
			// change the hover bars color
			hoverBar.setBackground(color);
		}
	};
	
	/* Methods */

	/**
	 * stylizeComponent(JComponent component, boolean hasBottom) :
	 * sets the components colors to black and white, adds an outline and 
	 * sets the font to monospaced
	 * 
	 * @param component : the component to stylize
	 * @param hasBottom : whether to add a bottom to the outline
	 * @return component : the same component as inputed
	 */
	public static JComponent stylizeComponent(JComponent component, boolean hasBottom) {
		// Make the component's background visible
		component.setOpaque(true); 
		// set the component's background & foreground colors
		component.setForeground(Color.WHITE); 
		component.setBackground(Color.BLACK);
		// create a border for the component
		component.setBorder(BorderFactory.createMatteBorder(2, 2, (hasBottom)? 2: 0, 2, Color.WHITE));
		// set the component's font
		component.setFont(new Font("Monospaced", 1, 18));
		// return the component
		return component;
	}
	
	/**
	 * addDialogue(String text, int delayMS) : adds dialogue to the screen
	 * with a typewriter effect
	 * 
	 * @param text : the dialogue to add
	 * @param delayMS : the delay between the adding of each letter to the screen
	 */
	public void addDialogue(String text, int delayMS) {
		// check if the dialogue box is on the application
		while (this.getParent().getParent() == null) delay(10);
		// store the mouses position
		Point mousePosition = window.getMousePosition();
		// loop through each character of the text
		for (int i = 0; i <= text.length(); i++) {
			// update the text to show the new character
			String newText = "<html><br><br><br><center>" + text.substring(0, i) + "</center></html>";
			setText(newText);
			// add a delay between the next character addition, speed up if the mouse is pressed
			delay(delayMS/((window.leftMouseInfo.isDown)? 10:1));
		}
		// if the program is not asking a question
		if (!askingQuestion)
		// repeat while the window does not exist or the player has not clicked the dialogue box
		while (window == null || !(window.leftMouseInfo.clicked && isLocationInBounds(mousePosition))) {
			// update the mouse position
			mousePosition = window.getMousePosition();
			// delay for 10ms
			delay(10); 
		}
		System.out.println(2);
	}
	
	/**
	 * addDialogue(String text) : adds dialogue to the screen
	 * with a typewriter effect spaced by 100ms
	 * 
	 * @param text : the dialogue to add
	 */
	public void addDialogue(String text) {
		// call addDialogue(text, delayMS)
		addDialogue(text, 100);
	}

	/**
	 * askQuestion(String question, Layout layout) : asks the player a question
	 * and returns there response as a string
	 * 
	 * @param question : the dialogue to add before the question is asked
	 * @param layout : the pages layout
	 * @return new String() : the players input
	 */
	public String askQuestion(String question, Layout layout) {

		// a question is being asked
		askingQuestion = true;
		
		// add the question to the box
		addDialogue(question);

		// create an input field
		JTextField questionBox = new JTextField();
		// center the input text
		questionBox.setHorizontalAlignment(CENTER);
		// format the text field
		stylizeComponent(questionBox, false);
		
		// create a submition button
		JButton submitButton = new JButton("Submit");
		// format the submition button
		stylizeComponent(submitButton, true);

		// when the button is clicked
		submitButton.addMouseListener(
			new Window.ButtonListener(
				new Runnable() {
					public void run() {
						// set the player's answer to the text in the text field
						playerAnswer = questionBox.getText();
					}
				}
			)
		);
		
		// add scaling information
		layout.add(questionBox, new Rectangle2D.Double(0,0,1,.1), new Rectangle(0,0,0,0));
		layout.add(questionBox, 24);
		layout.add(submitButton, new Rectangle2D.Double(0,0,1,.075), new Rectangle(0,0,0,0));
		layout.add(submitButton, 18);
		
		// add the text field and button to the page
		this.getParent().add(questionBox);
		this.getParent().add(submitButton);
		
		// update the layout
		layout.update(window);
		
		// wait for the players answer
		while (playerAnswer == null) {
			delay(10); // delay for 10 ms
		}
		
		// store the players answer
		String input = playerAnswer;
		// reset the playerAnswer variable
		playerAnswer = null;

		// remove the text field and button from the page
		questionBox.getParent().remove(questionBox);
		submitButton.getParent().remove(submitButton);
		
		// remove the text field and button from the layout
		layout.remove(questionBox);
		layout.remove(submitButton);

		// update the layout
		layout.update(window);
		
		// a question is no longer being asked
		askingQuestion = false;
		
		// return the players input
		return input;
		
	}
	
	/**
	 * askQuestion(String question, Layout layout) : asks the player a question
	 * and returns there response as a string
	 * 
	 * @param question : the dialogue to add before the question is asked
	 * @param layout : the pages layout
	 * @param choices : an arbitrary amount of answers to the question
	 * @return <b>int</b> : the index of the players choice
	 */
	public int addQuestionaire(String question, Layout layout, String... choices) {

		// a question is being asked
		askingQuestion = true;

		// add the question to the box
		addDialogue(question);

		// a list of all the choice buttons
		Queue<JLabel> choiceList = new ConcurrentLinkedQueue<>();
		
		// loop through the choices
		for (int index = 0; index < choices.length; index++) {
			
			// store the choice text
			String choice = choices[index];
			
			// create a new button
			JLabel choiceButton = new JLabel(choice);
			// center the new buttons text
			choiceButton.setHorizontalAlignment(CENTER);
			// format the new button
			stylizeComponent(choiceButton, (index == choices.length-1));
			
			// store the index to a final variable for access from a runnable
			final int finalIndex = index;
			// update button for mouse events
			choiceButton.addMouseListener( 
				new Window.ButtonListener(
					new Runnable() {
						// if the mouse clicks 
						public void run() {
							//set the index to the choices index
							indexOfChoice = finalIndex;
						}
					}
				)
			);
			
			// add the button to the choice list
			choiceList.add(choiceButton);
			
			// add the button and its text to the pages layout
			layout.add(choiceButton, new Rectangle2D.Double(0,0,1,.05), new Rectangle(0,0,0,0));
			layout.add(choiceButton, 18);
			
			// add the button to the page
			this.getParent().add(choiceButton);
		}
		
		// update the layout
		layout.update(window);
		
		// delay the thread until the chosen index is a valid integer
		while (indexOfChoice < 0) {
			delay(10);
		}
		
		// for each choice button in the choices list
		for (int i = 0; i < choices.length; i++) {
			// remove the button from the list
			JLabel choiceButton = choiceList.remove();
			// remove the mouse listener from the button
			choiceButton.removeMouseListener(choiceButton.getMouseListeners()[0]);
			// remove the button from the page
			choiceButton.getParent().remove(choiceButton);
			// remove the button from the layout
			layout.remove(choiceButton);
		}
		
		// update the layout
		layout.update(window);

		// a question is no longer being asked
		askingQuestion = false;

		// store the choices index
		int localIndexReturn = indexOfChoice;
		// reset the indexOfChoice variable
		indexOfChoice = -1;
		
		//return the chosen index
		return localIndexReturn;
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
	 * given a location relative to the window check if it is inside the dialogue box
	 * 
	 * @param location : a location relative to the window
	 * @return boolean : if the location was inside the dialogue box
	 */
	public boolean isLocationInBounds(Point location) {

		// if the location does not exist exit the method
		if (location == null) return false;
		// get the location of the dialogue box
		Point top = main.getLocation();
		
		// get the size of the hover bar
		Dimension sizeOfBar = hoverBar.getSize();
		// get the bottom of the dialogue box
		Point bottom = hoverBar.getLocation();
		// move the bottom over to the bottom right of the hoverbar
		bottom.translate((int)sizeOfBar.getWidth(),(int)sizeOfBar.getHeight()+30);
		
		// if the location is within the top and bottom positions
		if ((location.getX() > top.getX() && location.getY() > top.getY()) && 
				(location.getX() < bottom.getX() && location.getY() < bottom.getY())) {
				// the location is contained in the dialogue box
				return true;
		}
		// the location is not contained in the dialogue box
		return false;
	}
	
	/* Constructors */
	
	/**
	 * DialogueBox() : creates a new DialogueBox
	 */
	public DialogueBox() {

		// format the dialogue box
		stylizeComponent(this, true);
		// move the text to the top of the dialogue box
		//this.setVerticalAlignment(1);
		// inset the text from the edges of the box
		this.setBorder(BorderFactory.createMatteBorder( 25, 50, 25, 50, Color.BLACK));
	}
	
	/**
	 * DialogueBox() : creates a new DialogueBox and stores an application window 
	 */
	public DialogueBox(Window window) {
		
		// call the main constructor
		this();
		// update the window variable
		this.window = window;
	}
	
	/**
	 * DialogueBox() : creates a new DialogueBox, adds the dialogue box to 
	 * a page and stores a hover bar, and application window
	 */
	public DialogueBox(Window window, Page page, JLabel hoverBar) {

		// call the window constructor
		this(window);
		// store the hover bar
		this.hoverBar = hoverBar;
		// center the text both vertically and horizontally
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(TOP);
		// add the dialogue box to the pages movement events
		page.movementEvents.add((Page.EventRunnable) hoverEdits);
		// add the dialogue box to the page
		page.add(this);		
	}
}
