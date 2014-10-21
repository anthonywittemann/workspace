// Anthony Wittemann
// Chapter 2 Program 10 Test Average
// Due 2/4/14
public class AWCh2Pr10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 75, 83, 87 as the three test scores
		short testScore0 = 75;
		short testScore1 = 83;
		short testScore2 = 87;
		
		float averageTestScore = (float) ((float) (testScore0 + testScore1 + testScore2) / 3.0);
		System.out.println("Test Score 1: " + testScore0 + "   Test Score 2: " +  testScore1 +
				" Test Score 3: " +  testScore2);
		System.out.printf("Test Score Average: " + "%1$.2f", averageTestScore);
	}

}
