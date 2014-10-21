/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 3 - Charge Account Validation
 * In class 3/27/14
 */
public class AWChargeAccountValidation {
	private static int[] accounts = {5658845, 4520125, 7895122, 8777541, 8451277, 1302850, 8080152, 
								4562555, 5552012, 5050552, 7825877, 1250255, 1005231, 
								6545231, 3852085, 7576651, 7881200, 4581002};
	
	
	public static boolean isValid(int number){
		int index = 0;
		while(index < accounts.length){
			if(accounts[index] == number)
				return true;
		}
		return false;
	}
	
}
