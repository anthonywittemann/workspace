// Anthony Wittemann
// Chapter 2 Program 12 String Manipulator
// Due 2/4/14
public class AWCh2Pr12 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// "Philadelphia" as the favorite city value
		String favCity = "Philadelphia";
		
		int strLength = favCity.length();
		String favCityUpper = favCity.toUpperCase();
		String favCityLower = favCity.toLowerCase();
		char firstLetter = favCity.charAt(0);
		
		System.out.println("Number of characters in the city name: " + strLength);
		System.out.println("Name of the city in all uppercase letters: " + favCityUpper);
		System.out.println("Name of the city in all lowercase letters: " + favCityLower);
		System.out.println("First character in the name of the city: " + firstLetter);

	}

}
