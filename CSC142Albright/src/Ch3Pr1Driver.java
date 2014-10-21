/** 
 * @author Anthony Wittemann
 * Chapter 3 Algorithm Workbench 1
 * In class 2/6/14
 */
public class Ch3Pr1Driver {

	/**
	 * @param args
	 * 
	 * Creates 3 Employees and then displays the data of those 3 Employees out to the console
	 * @throws AWEmployeeIDNException 
	 */
	public static void main(String[] args) throws AWEmployeeIDNException {
		AWEmployee emp1 = new AWEmployee();
		AWEmployee emp2 = new AWEmployee();
		AWEmployee emp3 = new AWEmployee();

		emp1.setDepartment("Engineering");
		emp2.setDepartment("Research and Development");
		emp3.setDepartment("Finance");
		
		emp1.setIDNumber(10001);
		emp2.setIDNumber(10002);
		emp3.setIDNumber(10003);
		
		emp1.setName("Bob");
		emp2.setName("Ann");
		emp3.setName("Juan");
		
		emp1.setPosition("Mechanical Engineer");
		emp2.setPosition("Lab Technician");
		emp3.setPosition("Accountant");
		
		// or using a constructor
		/*
		 *  Employee emp1 = new Employee("Bob", 10001, "Engineering", "Mechanical Engineer");
		 *  Employee emp2 = new Employee("Ann", 10002, "Research and Development", "Lab Technician");
		 *  Employee emp3 = new Employee("Juan", 10003, "Finance", "Accountant"); 
		 */
		
		System.out.println("Information for Employee 1: \nIDNumber: " + emp1.getIDNumber() + 
				"\nName: " + emp1.getName() + "\nDepartment: " + emp1.getDepartment() + 
				"\nPosition: " + emp1.getPosition());
		System.out.println();
		System.out.println("Information for Employee 2: \nIDNumber: " + emp2.getIDNumber() + 
				"\nName: " + emp2.getName() + "\nDepartment: " + emp2.getDepartment() + 
				"\nPosition: " + emp2.getPosition());
		System.out.println();
		System.out.println("Information for Employee 3: \nIDNumber: " + emp3.getIDNumber() + 
				"\nName: " + emp3.getName() + "\nDepartment: " + emp3.getDepartment() + 
				"\nPosition: " + emp3.getPosition());
		
	}

}
