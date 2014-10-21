/** 
 * @author anthonywittemann
 * Chapter 10 Pr 1 - Test Scores
 * HW Due 4/24/14
 */
public class AWTestScores2 {
	private int[] testScores;
	
	public AWTestScores2(int[] ts){
		testScores = ts;
		for(int i = 0; i < testScores.length; i++){
			if(testScores[i] > 100 || testScores[i] < 0)
				throw new IllegalArgumentException("A Test Score is > 100 or < 0");
		}
	}

	public int getAvg(){
		int total = 0;
		for(int i = 0; i < testScores.length; i++){
			total += testScores[i];
		}
		return (int) (((double)total) / ((double)testScores.length));
	}
	
}
