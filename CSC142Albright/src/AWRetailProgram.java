/** 
 * @author Anthony Wittemann
 * Chapter 3 Program 4 AWRetail Items
 * Chapter 10 Program 3
 * HW Due 2/11/14, In class 4/24/14
 */
public class AWRetailProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//negative units
			AWRetailItem item3 = new AWRetailItem("Shirt", 24.95, -20);
		}
		catch(NegativePriceException e){
			System.out.println(e.getMessage());
		}
		catch(NegativeUnitsException e){
			System.out.println(e.getMessage());
		}
		
		try {
			//negative price
			AWRetailItem item1 = new AWRetailItem("Jacket", -59.95, 12);
		} catch (NegativePriceException e) {
			System.out.println(e.getMessage());
		} catch (NegativeUnitsException e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			//no errors
			AWRetailItem item2 = new AWRetailItem("Designer Jeans", 34.95, 40);
		} catch (NegativePriceException e) {
			System.out.println(e.getMessage());
		} catch (NegativeUnitsException e) {
			System.out.println(e.getMessage());
		}

	}

}
