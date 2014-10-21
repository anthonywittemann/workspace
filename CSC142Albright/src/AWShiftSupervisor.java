/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 2 - Shift Supervisor Classes
 * In class 4/10/14
 */
public class AWShiftSupervisor extends AWEmployee{
	private double annualSalary;
	private double annualBonus;

	public AWShiftSupervisor(){
		super();
		annualSalary = 0;
		annualBonus = 0;
	}
	
	public AWShiftSupervisor(String n, int hd, String en){
		super(n, hd, en);
		annualSalary = 0;
		annualBonus = 0;
	}
	
	public AWShiftSupervisor(String n, int hd, String en, double aS, double aB){
		super(n, hd, en);
		annualSalary = aS;
		annualBonus = aB;
	}
	
	public double getAnnualSalary(){
		return annualSalary;
	}
	
	public double getAnnualBonus(){
		return annualBonus;
	}
	
	public void setAnnualSalary(double nAS){
		annualSalary = nAS;
	}
	
	public void setAnnualBonus(double nAB){
		annualBonus = nAB;
	}
	
	public String toString(){
		return "Annual Salary: $" + annualSalary + ", Annual Bonus: $" + annualBonus;
	}
	
}
