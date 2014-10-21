/** 
 * @author Anthony Wittemann
 * Chapter 4 Program 3 Test Scores
 * HW Due 2/18/14
 */
public class AWTestScores {
	private int testScore1;
	private int testScore2;
	private int testScore3;
	
	public AWTestScores(int ts1, int ts2, int ts3){
		testScore1 = ts1;
		testScore2 = ts2;
		testScore3 = ts3;
	}
	
	public int getTestScore1(){
		return testScore1;
	}
	
	public int getTestScore2(){
		return testScore2;
	}
	
	public int getTestScore3(){
		return testScore3;
	}
	
	public int getAverage(){
		int avg = (int) ((testScore1 + testScore2 + testScore3) / 3.0);
		return avg;
	}
	
	public String getGrade(){
		int av = getAverage();
		String grade;
		
		if (av < 60){
			grade = "F";
		}
		else if (av < 70){
			grade = "D";
		}
		else if (av < 80){
			grade = "C";
		}
		else if (av < 90){
			grade = "B";
		}
		else{
			grade = "A";
		}
		
		return grade;
	}
}
