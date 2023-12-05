
public class Bachelor {
	private String name;
	private int traitOne;
	private int traitTwo;
	
	public Bachelor(String name, int traitOne, int traitTwo) {
    	if(name == (null)) {
    		throw new IllegalArgumentException("A bachelor's name cannot be null!");
    	}
    	if(traitOne < 0 || traitOne > 2) {
    		throw new IllegalArgumentException("A bachelor can only have traits that exist");
    	}
    	this.name = name;
    	this.traitOne = traitOne;
    	this.traitTwo = traitTwo;
	}
	
	public static int random = 1;
	public static int randomNum(int low, int high) {
		int result = (int)(Math.random()*(high-low)) + low;
		return result;
	}

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

	public static void main(String[] args) {
		System.out.println(randomNum(0,3));

	}

}
