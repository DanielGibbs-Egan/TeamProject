import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RelationshipTracker {

	private static Map<String, Integer> relationshipScores = new TreeMap<>();
	//static String dudes = "Peter,Homer,Hank";
	//static String[] bachelorNames = dudes.split(",");
	
	public void fillOutMap(String[] names) {
		for(String dudesName: names) {
			relationshipScores.put(dudesName + " Romantic", 0);
			relationshipScores.put(dudesName + " Friendship", 0);
			
		}
	}
	public void newEvent(String target, int type, int score) {
		if(type == 0) {
			int currentScore = relationshipScores.get(target + " Romantic");
			int newScore = currentScore + score;
			relationshipScores.put(target + " Romantic", newScore);
		}
		else if(type == 1) {
			int currentScore = relationshipScores.get(target + " Friendship");
			int newScore = currentScore + score;
			relationshipScores.put(target + " Friendship", newScore);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
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
	
	
	public static void main(String[] args) {
		CharacterGenerator e = new CharacterGenerator();
		RelationshipTracker a = new RelationshipTracker();
		String[] namesLocal = e.names;
		a.fillOutMap(namesLocal);
		a.newEvent(namesLocal[0], 0, 3);
		System.out.println(a);
		a.newEvent(namesLocal[0], 0, 9);
		System.out.println(a.getScore(namesLocal[0], 0));

	}

}
