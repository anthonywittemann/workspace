/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 7 - Person and Customer
 * HW Due 4/15/14
 */
public class AWCh9Pr7Driver {

	public static void main(String[] args) {
		AWCustomer cust = new AWCustomer("Aaron", "1600 Pennsyl Ave", 1018675309, 123, false);
		cust.setCustNum(1234);
		cust.setAddress("101 5th Ave");
		System.out.println("Name: " + cust.getName() + "\nTelephone Number: " + cust.getTeleNum()+
				"\nAddress: " + cust.getAddress() + "\n" + cust);

	}

}
