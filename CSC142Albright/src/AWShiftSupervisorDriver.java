/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 2 - Shift Supervisor Classes
 * In class 4/10/14
 */
public class AWShiftSupervisorDriver {
	
	public static void main(String[] args){
		AWShiftSupervisor supervisor = new AWShiftSupervisor("Karl", 1032000, "001-B", 50000, 5000);
		supervisor.setAnnualSalary(55000);
		supervisor.setAnnualBonus(3000.50);
		supervisor.setEmpNum("002-C");
		supervisor.setHireDate(1042000);
		supervisor.setName("Marx");
		System.out.println("Name: " + supervisor.getName() + "\n" + supervisor);
	}
	
}
