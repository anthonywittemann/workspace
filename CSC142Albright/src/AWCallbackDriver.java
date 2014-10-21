/** 
 * @author anthonywittemann
 * Call-back Interface
 * HW Due 4/22/14
 */
public class AWCallbackDriver {

	public static void main(String[] args) {
		AWClient client1 = new AWClient();
		client1.callback(100);
		AWAnotherClient client2 = new AWAnotherClient();
		client2.callback(9);
		
		
	}
	


}
