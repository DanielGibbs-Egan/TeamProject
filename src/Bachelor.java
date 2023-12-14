
public class Bachelor {
	//These are the three things the bachelor class keeps track of
	//name - the character name
	//traitOne - the number identifier of their first trait
	//traitTwo - the number identifier of their second trait
	private String name;
	private int traitOne;
	private int traitTwo;
	
	public Bachelor(String name, int traitOne, int traitTwo) {
    	if(name == (null)) {
    		throw new IllegalArgumentException("A bachelor's name cannot be null!");
    	}
    	//This is irrelevant if the traits are being randomly generated
    	if(traitOne < 0 || traitOne > 2) {
    		throw new IllegalArgumentException("A bachelor can only have traits that exist");
    	}
    	this.name = name;
    	this.traitOne = traitOne;
    	this.traitTwo = traitTwo;
	}
	//I stole this method from Daniel. For the low for our cases we want 0, and the high should be the upper limit numerically. So if we have 3 choices it should be (0,3)
	//We use this method to give each created bachelor a random trait. If you do not wish to do so just manually input the number
	public static int random = 1;
	public static int randomNum(int low, int high) {
		int result = (int)(Math.random()*(high-low)) + low;
		return result;
	}
	//All of the rest of this is a bunch of getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTraitOne() {
		return traitOne;
	}

	public void setTraitOne(int traitOne) {
		this.traitOne = traitOne;
	}

	public int getTraitTwo() {
		return traitTwo;
	}

	public void setTraitTwo(int traitTwo) {
		this.traitTwo = traitTwo;
	}
	//This is to test the random number bit ignore it
	public static void main(String[] args) {
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));
		System.out.println(randomNum(0,3));

	}

}
