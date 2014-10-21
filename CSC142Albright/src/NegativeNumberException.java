/** 
 * @author anthonywittemann
 * Chapter 10 Algorithm Workbench 5 
 * HW Due 4/24/14
 */
public class NegativeNumberException extends Exception{
	private String message;
	
	public NegativeNumberException(){
		super("Negative Number");
	}
	
	public NegativeNumberException(String mess){
		super(mess);
		message = mess;
	}
	
	public String toString(){
		if(message != null){
			return message;
		}
		return "Negative Number";
	}

}


