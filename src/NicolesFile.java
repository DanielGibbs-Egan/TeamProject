
public class NicolesFile {
	//This whole main will be a sort of 'crash course' on how to utilize the three files I have created
	//Those files being Bachelor, CharacterNamesToString (NAME PENDING), and RelationshipTracker
	public static void main(String[] args) {
		//Creating an empty string array with spots for 3 character names. If we lower the canidate amount we should lower this, or just not utilize the other slots
		String[] names = new String[3];
		
		//Filling out the map of ALL dialogue in the game
		//See the DialogueStuff file for more info
		DialogueStuff b = new DialogueStuff();
		b.dialogueMapFill();
		
		//Creating our first bachelor. The first input is their name, which you should type out.
		//The second input is their first trait (see Bachelor for more info)
		//The third input is their second trait
		Bachelor One = new Bachelor("Homer", Bachelor.randomNum(0,3), Bachelor.randomNum(0, 3));
		//We then manually add bachelor "One"s name to the array in the first slot
		names[0] = One.getName();
		
		//Repeat for three more bachelors
		//Here you see you can make their traits static if you so desire
		Bachelor Two = new Bachelor("Hank", 2, 1);
		names[1] = Two.getName();
		Bachelor Three = new Bachelor("Peter", 0, 0);
		names[2] = Three.getName();
		
		//You could similarly do the same but with the names array typed out first
		String[] namesTwo = new String[3];
		namesTwo[0] = "Ned";
		namesTwo[1] = "Joe";
		namesTwo[2] = "Dale";
		@SuppressWarnings("unused")
		Bachelor Four = new Bachelor(namesTwo[0], 0,0);
		@SuppressWarnings("unused")
		Bachelor Five = new Bachelor(namesTwo[1],0,0);
		@SuppressWarnings("unused")
		Bachelor Six = new Bachelor(namesTwo[2],0,0);
		
		//This println is printing out the "namesToString" method in the RelationshipTracker class
		//Note in this case we are feeding in the "names" array we manually created
		System.out.println(RelationshipTracker.namesToString(names));
		System.out.println(RelationshipTracker.namesToString(namesTwo));
		
		//Making a new relationship tracker map for our bachelors
		RelationshipTracker a = new RelationshipTracker();
		//Calling the fill out map method on our list of bachelors
		a.fillOutMap(names);
		//Heck you can even throw in our three other guys in you really want to
		a.fillOutMap(namesTwo);
		
		//newEvent lets you add scores to the bachelors. See RelationshipTracker for more information
		a.newEvent(names[0], 0, 3);
		a.newEvent(namesTwo[1], 0, 20);
		a.newEvent(names[0], 0, 9);
		
		//Using getters from RelationshipTracker we can print out scores or grab them for use in other programs
		System.out.println(a.getScore(names[0], 0));
		System.out.println(a.getScore(namesTwo[1], 0));
		System.out.println(a.getScore(names[0], 0) + a.getScore(namesTwo[1], 0));
		
		//We can also use hasReachedScore to see if the player well... has reached that score
		System.out.println(a.hasReachedScore(names[0], 0, 6));
		System.out.println(a.hasReachedScore(names[0], 1, 6));
		
		//This is showing that we can access any dialogue stored in the text file by getting it
		for(int i = 1; i < b.dialogueMap().size(); i++) {
			if(i == 10) {
				continue;
			}
		System.out.println(b.dialogueMap().get(i));
		}
		System.out.println(b.dialogueMap().get(2));
		System.out.println(b.dialogueMap().get(10));
		}
	}
