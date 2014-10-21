/** 
 * @author anthonywittemann
 * Chapter 10 Pr 11
 * In class 4/24/14
 */
public class AWShiftException extends Exception {
	
	public AWShiftException(){
		super("Error: Invalid Shift");
	}
	
	public AWShiftException(String mess){
		super("Error: Invalid Shift. " + mess);
	}

}
