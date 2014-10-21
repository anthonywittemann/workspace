/** 
 * @author anthonywittemann
 * Chapter 10 Pr 5
 * In class 4/24/14
 */
public class AWInvalidIDNException extends Exception{

	public AWInvalidIDNException(){
		super("Error: Invalid ID Number");
	}
	
	public AWInvalidIDNException(String mess){
		super("Error: Invalid ID Number. " + mess);
	}
	
}
