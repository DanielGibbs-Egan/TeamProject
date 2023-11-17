
public class DanielsFile {
	
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
	}

}
