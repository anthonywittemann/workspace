/** 
 * @author anthonywittemann
 * Chapter 10 Pr 3
 * In class 4/24/14
 */
public class NegativePriceException extends Exception{
	
	public NegativePriceException(){
		super("Negative Price");
	}
	
	public NegativePriceException(String mess){
		super(mess);
	}
	
}
