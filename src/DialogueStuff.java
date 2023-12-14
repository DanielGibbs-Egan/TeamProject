import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Okay so this file is sorta... odd and I may not even be entirely sure what's going on in it BUT
//We can still utilize it
public class DialogueStuff {
	//So what we are going to do is store EVERY line of dialogue in a Hashmap
	//The key for each piece of dialogue is the same as its line is labeled in eclipse when you
    //open the text file!!.
	//This means that all NPC dialogue should be stored in a text file (You can just paste it into DialogueTest, replacing what
	// I put in there to test, OR change the fire directory down below
	private static Map <Integer, String> npcDialogue = new HashMap<>();
	
	public void dialogueMapFillPartTwo(Scanner file) {
		int i = 1;
		while(file.hasNextLine()) {
			String newDialogue = file.nextLine();
			dialogueMap().put(i, newDialogue);
			i++;
		}
		
	}
	public void dialogueMapFill() {
		
		//CHANGE THIS IF YOU WANT TO HAVE IT READ A DIFFERENT FILE
		//CHANGE THIS IF YOU WANT TO HAVE IT READ A DIFFERENT FILE
		File file = new File("DialogueTest.txt");
		//CHANGE THIS IF YOU WANT TO HAVE IT READ A DIFFERENT FILE
		//CHANGE THIS IF YOU WANT TO HAVE IT READ A DIFFERENT FILE
		
		try {
			Scanner s = new Scanner(file);
			dialogueMapFillPartTwo(s);
		}
		catch(FileNotFoundException e){
			System.out.println("Make sure the file directory is pointing at the right file! (Include the .txt)");
		}
	}
	//Auto generated getter
	public Map <Integer, String> dialogueMap() {
		return npcDialogue;
}

	public static void main(String[] args) {
		DialogueStuff a = new DialogueStuff();
		a.dialogueMapFill();
		for(int i = 0; i < a.dialogueMap().size(); i++) {
		System.out.println(a.dialogueMap().get(i));
		}
	}
}
