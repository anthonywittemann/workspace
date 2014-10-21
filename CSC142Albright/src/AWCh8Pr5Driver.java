import java.util.Scanner;

/** 
 * @author anthonywittemann
 * Chapter 8 Pr5
 * HW Due 5/1/14
 */
public class AWCh8Pr5Driver {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		AWPasswordVerifier pv = new AWPasswordVerifier(keyboard.nextLine());
		if(pv.isValid()){
			System.out.println("Your password is valid");
		}
		else{
			System.out.println("Your password is invalid");
		}
	}

}

