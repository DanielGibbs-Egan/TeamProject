import java.util.Map;
import java.util.TreeMap;

public class RelationshipTracker {
	//This is the map you should create that will keep track of your friendship and romance points with each canidate
	private static Map<String, Integer> relationshipScores = new TreeMap<>();
	
	//Calling this with the string array of names
	public void fillOutMap(String[] names) {
		for(String dudesName: names) {
			//Creating keys for both 'Personname Romantic' and 'Personname Friendship'
			//Really I could have named these keys 'Personname0' and 'Personname1' but it makes it easier to remember for me
			
			//There are two seperate scores for romantic and friendship, instead of having them on one scale and friendship being negative and romance being positive or something like that
			//This allows you to in theory have a romantic relationship with someone you are not friends with or vice versa
			relationshipScores.put(dudesName + " Romantic", 0);
			relationshipScores.put(dudesName + " Friendship", 0);
			
		}
	}
	//This is the system for creating a new score, like you saying something romantic to 'Personname' and gaining 3 points would be ('Personname', 0, 3)
	public void newEvent(String target, int type, int score) {
		//We don't have to worry about matching string inputs since you'll always be pulling from an array from those, the same array that created the map. See Nicoles File for that
		//Type 0 is romantic
		if(type == 0) {
			int currentScore = relationshipScores.get(target + " Romantic");
			//Score can be negative or positive! 
			//Scores must be integers though!
			int newScore = currentScore + score;
			relationshipScores.put(target + " Romantic", newScore);
		}
		//Type 1 is friendship
		else if(type == 1) {
			int currentScore = relationshipScores.get(target + " Friendship");
			int newScore = currentScore + score;
			relationshipScores.put(target + " Friendship", newScore);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	//This lets you check your relationship score with a specific person and what type of relationship]
	//This returns an integer that is the player's score
	public int getScore(String target, int type) {
		if(type == 0) {
			return relationshipScores.get(target + " Romantic");
		}
		else if(type == 1) {
			return relationshipScores.get(target + " Friendship");
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	//This method lets you check if you have reached or surpassed a target score with a bachelor
	public boolean hasReachedScore(String target, int type, int desiredScore) {
		int score = 0;
		if(type == 0) {
			score = relationshipScores.get(target + " Romantic");
		}
		else if(type == 1) {
			score = relationshipScores.get(target + " Friendship");
		}
		else {
			throw new IllegalArgumentException(type + " isn't a valid relationship type");
		}
		if(desiredScore >= 0) {
			if(score < desiredScore) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			if(score > desiredScore) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	//This method lets you print out all of the bachelor names that are stored in an array you feed in
	public static String namesToString(String[] names) {
		String nme = null;
		for(int i = 0; i < names.length; i++) {
			if(i == 0) {
				nme = names[i];
			}
			else if(i == names.length - 1) {
				nme = nme + ", & " + names[i] + "." ;
			}
			else{
				nme = nme + ", " + names[i];
			}
		}
		return nme;
	}
	
	
	public static void main(String[] args) {
	}

}
