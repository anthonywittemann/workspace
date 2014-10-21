/** 
 * @author anthonywittemann
 * Chapter 10 Pr 5
 * In class 4/24/14
 */
public class AWInvalidHrsWorkedException extends Exception{
	
	public AWInvalidHrsWorkedException(){
		super("Error: Invalid Hours Worked");
	}
	
	public AWInvalidHrsWorkedException(String mess){
		super("Error: Invalid Hours Worked. " + mess);
	}
	

}
