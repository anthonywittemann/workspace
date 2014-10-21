import java.util.Scanner;

/** 
 * @author anthonywittemann
 * Chapter 8 Pr3
 * HW Due 5/1/14
 */
public class AWCh8Pr3Driver {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Autocapitalized Sentence: " + AWCh8Methods.sentenceCapitalizer(keyboard.nextLine()));
	}

}
