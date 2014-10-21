/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 7 - Grade Book
 * In class 3/27/14
 */
public class AWGradeBook {
	private String[] names = new String[5];
	private char[] grades = new char[5];
	private double[][] scores = new double[5][4];
	
	public AWGradeBook(){
		
		
		
	}
	
	private void calculateGrades(){
		
	}
	
	public String getStudentName(int index){
		return names[index];
	}
	
	public double getAvgTestScore(int studentIndex){
		double totalScore = 0;
		for(int index = 0; index < scores[studentIndex].length; index++){
			totalScore += scores[studentIndex][index];
		}
		return (totalScore/scores[studentIndex].length);
	}
	
	public char getGrade(int studentIndex){
		return grades[studentIndex];
	}

}
