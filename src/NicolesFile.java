import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NicolesFile {
	
	public static void main(String[] args) {
		String[] names = new String[3];
		Bachelor One = new Bachelor("Homer", Bachelor.randomNum(0,3), Bachelor.randomNum(0, 3));
		names[0] = One.getName();
		Bachelor Two = new Bachelor("Hank", 2, 1);
		names[1] = Two.getName();
		Bachelor Three = new Bachelor("Peter", 0, 0);
		names[2] = Three.getName();
		System.out.println(CharacterGenerator.namesToString(names));
		System.out.println("Nicole Moriarity");
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