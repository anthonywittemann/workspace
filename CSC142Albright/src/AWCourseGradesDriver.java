/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 5 - Course grades
 * HW Due 4/22/14
 */
public class AWCourseGradesDriver {

	public static void main(String[] args) {
		GradedActivity lab = new GradedActivity();
		lab.setScore(95.2);
		PassFailExam pfe = new PassFailExam(10, 0, 70);
		GradedActivity essay = new GradedActivity();
		essay.setScore(94.5);
		FinalExam fe = new FinalExam(50, 2);
		AWCourseGrades grades = new AWCourseGrades(lab, pfe, essay, fe);
		System.out.println(grades + "\n\n");
		System.out.println("Highest Score: " + grades.getHighest().getScore());
		System.out.println("Lowest Score: " + grades.getLowest().getScore());
		System.out.println("Average Score: " + grades.getAverage());
	}

}
