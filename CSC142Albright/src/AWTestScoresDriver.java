/** 
 * @author anthonywittemann
 * Chapter 10 Pr 1 - Test Scores
 * HW Due 4/24/14
 */
public class AWTestScoresDriver {

	public static void main(String[] args) {
		int[] a = {0, -1, 101, 50};
		AWTestScores2 ts;
		try{
			ts = new AWTestScores2(a);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		

	}

}
