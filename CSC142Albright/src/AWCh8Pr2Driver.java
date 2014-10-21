import java.util.Scanner;

/** 
 * @author anthonywittemann
 * Chapter 8 Pr2
 * HW Due 5/1/14
 */
public class AWCh8Pr2Driver {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Word Count: " + AWCh8Methods.wordCounter(keyboard.nextLine()));
	}

}