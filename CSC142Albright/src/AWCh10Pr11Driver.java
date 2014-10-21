/** 
 * @author anthonywittemann
 * Chapter 10 Pr 11
 * In class 4/24/14
 */
public class AWCh10Pr11Driver {

	public static void main(String[] args) {
		try {
			AWEmployee emp = new AWEmployee("Bob", -2, "Management", "VP");
		} catch (AWEmployeeIDNException e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			AWProductionWorker pw = new AWProductionWorker("Bob", 1011999, "1234", -1, 15);
		} catch (AWShiftException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidPayRateException e) {
			System.out.println(e.getMessage());
		}

		try {
			AWProductionWorker pw2 = new AWProductionWorker("Bob", 1011999, "1234", 2, -15);
		} catch (AWShiftException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidPayRateException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
