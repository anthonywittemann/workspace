/** 
 * @author Anthony Wittemann
 * Chapter 3 Program 1 Employee Class
 * Chapter 6 Program 6 Employee Class modifications
 * Chapter 9 Program 1 - Employee and Production Worker Classes
 * HW Due 2/11/14 & HW Due 3/11/14 & HW Due 3/13/14 & In class 4/10/14
 */
public class AWEmployee {
	private String name;
	private int idNumber;
	private String empNum;
	private int hireDate; //format mmddyyyy ex 01011999 for January 1 1999
	private String department;
	private String position;
	
	public AWEmployee(){
		name = "";
		idNumber = 0;
		empNum = "000-A";
		hireDate = 1011999;
		department = "";
		position = "";
	}
	
	public AWEmployee(String n, int idn, String d, String p) throws AWEmployeeIDNException{
		name = n;
		setIDNumber(idn);
		department = d;
		position = p;
	}
	
	public AWEmployee(String n, int idn) throws AWEmployeeIDNException{
		name = n;
		setIDNumber(idn);
		department = "";
		position = "";
	}
	
	public AWEmployee(String n, int hd, String en){
		name = n;
		hireDate = hd;
		empNum = en;
	}
	
	/**
	 * Copy Constructor
	 * @param e instance of another employee class
	 */
	public AWEmployee(AWEmployee e) throws AWEmployeeIDNException{
		this(e.getName(), e.getIDNumber(), e.getDepartment(), e.getPosition());
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public void setEmpNum(String en){
		empNum = en;
	}
	
	public void setHireDate(int hd){
		hireDate = hd;
	}
	
	public void setIDNumber(int newIDN) throws AWEmployeeIDNException{
		if(newIDN < 0 || newIDN > 9999){
			throw new AWEmployeeIDNException("ID number cannot be > 9999 or < 0");
		}
		idNumber = newIDN;
	}
	
	public void setDepartment(String newDept){
		department = newDept;
	}
	
	public void setPosition(String newPos){
		position = newPos;
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getEmpNum(){
		return empNum;
	}
	
	public int getHireDate(){
		return hireDate;
	}
	
	public int getIDNumber(){
		return idNumber;
	}

	public String getDepartment(){
		return department;
	}
	
	public String getPosition(){
		return position;
	}
	
	public String toString(){
		return "\nName: " + name + "\nID Number: " + idNumber + "\nDeparment: " + department + 
				"\nPosition: " + position + "\nEmployee Number: " + empNum + "\nHire Date: " + hireDate;
	}
	
	public boolean equals(Object emp2){
		return (name.equalsIgnoreCase(((AWEmployee) emp2).getName()) && 
				idNumber == ((AWEmployee) emp2).getIDNumber() &&
				department.equalsIgnoreCase(((AWEmployee) emp2).getDepartment()) && 
				position.equalsIgnoreCase(((AWEmployee) emp2).getPosition()));
	}
	
	
}
