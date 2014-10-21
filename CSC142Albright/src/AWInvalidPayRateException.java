/** 
 * @author anthonywittemann
 * Chapter 10 Pr 5
 * In class 4/24/14
 */
public class AWInvalidPayRateException extends Exception{

	public AWInvalidPayRateException(){
		super("Error: Invalid Pay Rate");
	}
	
	public AWInvalidPayRateException(String mess){
		super("Error: Invalid Pay Rate. " + mess);
	}
	
}
