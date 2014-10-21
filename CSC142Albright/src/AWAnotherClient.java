/** 
 * @author anthonywittemann
 * Call-back Interface
 * HW Due 4/22/14
 */
public class AWAnotherClient implements AWCallback{

	@Override
	public void callback(int i) {
		System.out.println("Squared Value: " + i*i);
		
	}

}