/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 3 - Team Leader class
 * In class 4/10/14
 */
public class AWTeamLeader extends AWShiftSupervisor{
	private double monthlyBonus;
	private int hrsReqTraining;
	private int hrsTrainingAttended;
	
	public AWTeamLeader(){
		super();
		monthlyBonus = 0;
		hrsReqTraining = 0;
		hrsTrainingAttended = 0;
	}
	
	public AWTeamLeader(String n, int hd, String en){
		super(n, hd, en);
		monthlyBonus = 0;
		hrsReqTraining = 0;
		hrsTrainingAttended = 0;
	}
	
	public AWTeamLeader(String n, int hd, String en, double aS, double aB){
		super(n, hd, en, aS, aB);
		monthlyBonus = 0;
		hrsReqTraining = 0;
		hrsTrainingAttended = 0;
	}
	
	public AWTeamLeader(String n, int hd, String en, double aS, double aB, double mB, int hRT, int hTA){
		super(n, hd, en, aS, aB);
		monthlyBonus = mB;
		hrsReqTraining = hRT;
		hrsTrainingAttended = hTA;
	}
	
	public double getMonthlyBonus(){
		return monthlyBonus;
	}
	
	public int getHrsReqTraining(){
		return hrsReqTraining;
	}
	
	public int getHrsTrainingAttended(){
		return hrsTrainingAttended;
	}
	
	public void setMonthlyBonus(double nMB){
		monthlyBonus = nMB;
	}
	
	public void setHrsReqTraining(int nHRT){
		hrsReqTraining = nHRT;
	}
	
	public void setHrsTrainingAttended(int nHTA){
		hrsTrainingAttended = nHTA;
	}
	
	public String toString(){
		return "Monthly Bonus: $" + monthlyBonus + ", Hours Training Required: " + 
				hrsReqTraining + ", Hours Training Attended: " + hrsTrainingAttended;
	}
	
}
