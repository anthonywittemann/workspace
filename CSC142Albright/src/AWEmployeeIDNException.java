/** 
 * @author anthonywittemann
 * Chapter 10 Pr 11
 * In class 4/24/14
 */
public class AWEmployeeIDNException extends Exception {
	
	public AWEmployeeIDNException(){
		super("Error: Invalid Employee ID Number");
	}
	
	public AWEmployeeIDNException(String mess){
		super("Error: Invalid Employee ID Number. " + mess);
	}

}
