/** 
 * @author anthonywittemann
 * Chapter 10 Pr 3
 * In class 4/24/14
 */
public class NegativeUnitsException extends Exception{
	
	public NegativeUnitsException(){
		super("Negative Units");
	}
	
	public NegativeUnitsException(String mess){
		super(mess);
	}
	
}
