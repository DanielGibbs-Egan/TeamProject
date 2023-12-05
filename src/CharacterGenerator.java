
public class CharacterGenerator {
	public static String[] names = new String[3];
	
	public static String namesToString(String[] names) {
		String nme = null;
		for(int i = 0; i < names.length; i++) {
			if(i == 0) {
				nme = names[i];
			}
			else{
				nme = nme + "," + names[i];
			}
		}
		return nme;
	}

	public static void main(String[] args) {
		Bachelor One = new Bachelor("Homer", Bachelor.randomNum(0,3), Bachelor.randomNum(0, 3));
		names[0] = One.getName();
		Bachelor Two = new Bachelor("Hank", 2, 1);
		names[1] = Two.getName();
		Bachelor Three = new Bachelor("Peter", 0, 0);
		names[2] = Three.getName();
		System.out.println(namesToString(names));
	}

}
