/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 3 - Team Leader class
 * In class 4/10/14
 */
public class AWTeamLeaderDriver {
	
	public static void main(String[] args){
		AWTeamLeader leader = new AWTeamLeader("Karl", 1032000, "001-B", 50000, 5000, 500, 20, 22);
		leader.setAnnualSalary(55000);
		leader.setAnnualBonus(3000.50);
		leader.setEmpNum("002-C");
		leader.setHireDate(1042000);
		leader.setName("Marx");
		leader.setMonthlyBonus(550);
		leader.setHrsReqTraining(25);
		leader.setHrsTrainingAttended(28);
		System.out.println("Name: " + leader.getName() + "\nSalary: $" + leader.getAnnualSalary() +
				"\nBonus $" + leader.getAnnualBonus() + leader);
	}

}
