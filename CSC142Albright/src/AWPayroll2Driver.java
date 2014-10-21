/** 
 * @author anthonywittemann
 * Chapter 10 Pr 5
 * In class 4/24/14
 */
public class AWPayroll2Driver {

	public static void main(String[] args) {
		try {
			AWPayroll2 p1 = new AWPayroll2("", 123, 20, 15);
		} catch (AWInvalidHrsWorkedException e) {
			System.out.println(e.getMessage());
		} catch (AWEmptyStringException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidIDNException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidPayRateException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		
		try {
			AWPayroll2 p2 = new AWPayroll2("Bob", -123, 20, 15);
		} catch (AWInvalidHrsWorkedException e) {
			System.out.println(e.getMessage());
		} catch (AWEmptyStringException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidIDNException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidPayRateException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();

		try {
			AWPayroll2 p3 = new AWPayroll2("Bob", 123, -20, 15);
		} catch (AWInvalidHrsWorkedException e) {
			System.out.println(e.getMessage());
		} catch (AWEmptyStringException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidIDNException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidPayRateException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		
		try {
			AWPayroll2 p4 = new AWPayroll2("Bob", 123, 20, -15);
		} catch (AWInvalidHrsWorkedException e) {
			System.out.println(e.getMessage());
		} catch (AWEmptyStringException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidIDNException e) {
			System.out.println(e.getMessage());
		} catch (AWInvalidPayRateException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
