/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 6 Employee Class Modifications
 * HW Due 3/11/14 
 */
public class AWCh6Pr6Driver {

	public static void main(String[] args) throws AWEmployeeIDNException {
		AWEmployee emp1 = new AWEmployee();
		System.out.println(emp1.toString());
		
		AWEmployee emp2 = new AWEmployee("Bob", 123, "Clean-up", "Janitor");
		System.out.println(emp2.toString());
		
		AWEmployee emp3 = new AWEmployee("Alexa", 321);
		System.out.println(emp3.toString());

	}

}
