/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 5 - Course grades
 * HW Due 4/22/14
 */
public class AWCourseGrades implements AWAnalyzable{
	private GradedActivity[] grades;
	
	public AWCourseGrades(){
		grades = new GradedActivity[4];
		grades[0] = new GradedActivity();
		grades[1] = new PassFailExam(10, 0, 70);
		grades[2] = new GradedActivity();
		grades[3] = new FinalExam(50,50);
	}
	
	public AWCourseGrades(GradedActivity lab, PassFailExam pfe, GradedActivity essay, FinalExam fe){
		grades = new GradedActivity[4];
		grades[0] = lab;
		grades[1] = pfe;
		grades[2] = essay;
		grades[3] = fe;
	}

	@Override
	public double getAverage() {
		double total = 0;
		for(GradedActivity ga: grades){
			total += ga.getScore();
		}
		return total/(grades.length);
	}

	@Override
	public GradedActivity getHighest() {
		int highestElement = 0;
		double highestScore = grades[0].getScore();
		for(int i = 0; i < grades.length; i++){
			if(highestScore < grades[i].getScore()){
				highestElement = i;
				highestScore = grades[i].getScore();
			}
		}
		return grades[highestElement];
	}

	@Override
	public GradedActivity getLowest() {
		int lowestElement = 0;
		double lowestScore = grades[0].getScore();
		for(int i = 0; i < grades.length; i++){
			if(lowestScore > grades[i].getScore()){
				lowestElement = i;
				lowestScore = grades[i].getScore();
			}
		}
		return grades[lowestElement];
	}
	
	public void setLab(GradedActivity lab){
		grades[0] = lab;
	}
	
	public void setPassFailExam(PassFailExam pfe){
		grades[1] = pfe;
	}
	
	public void setEssay(GradedActivity essay){
		grades[2] = essay;
	}
	
	public void setFinalExam(FinalExam fe){
		grades[3] = fe;
	}
	
	public String toString(){
		return "Lab grade: " + grades[0].getGrade() + "\tLab Score: " + grades[0].getScore() + "\n" +
				"Pass/Fail Exam grade: " + grades[1].getGrade() + "\tPass/Fail Score: " + grades[1].getScore() + "\n" +
				"Essay grade: " + grades[2].getGrade() + "\tEssay Score: " + grades[2].getScore() + "\n" +
				"Final Exam grade: " + grades[3].getGrade() + "\tFinal Exam Score: " + grades[3].getScore();
	}

}
