/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 8 - Preferred prefCustomer
 * HW Due 4/17/14
 */
public class AWPreferredCustomerDriver {

	public static void main(String[] args) {
		AWPreferredCustomer prefCust = new AWPreferredCustomer("Aaron", "1600 Pennsyl Ave", 1018675309, 123, false, 522);
		prefCust.setCustNum(1234);
		prefCust.setAddress("101 5th Ave");
		prefCust.setTotalPurchaseAmnt(1023.98);
		System.out.println("Name: " + prefCust.getName() + "\nTelephone Number: " + prefCust.getTeleNum()+
				"\nAddress: " + prefCust.getAddress() + "\n" + prefCust);

	}

}
