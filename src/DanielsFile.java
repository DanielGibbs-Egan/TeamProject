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
		window.dialogueBox.addDialogue("Hello World!, Dialogue Text Box Testing!");	
		
		String[] questions = {"A","B","C"};
		Integer selection = window.dialogueBox.addQuestionaire("A, B, or C", window.gamePage.layout, questions);
		
		window.dialogueBox.addDialogue("You Chose: " + questions[selection]);
		
		System.out.println(window.dialogueBox.addQuestionaire("a,b,c, or d", window.gamePage.layout, "a", "b", "c", "d"));;
		
		window.dialogueBox.addDialogue("Goodbye!");
		
		window.setCurrentPage(window.mainMenu);
	}

	
}
