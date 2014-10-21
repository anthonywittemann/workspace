import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Chapter 5 Program 1 Sum of Numbers
 * HW Due 2/25/14
 */

public class AWSumOfNumbers {

	public static void main(String[] args) {
		int usrInt = Integer.parseInt(JOptionPane.showInputDialog("Enter an integer > 0."));
		int theSum = 0;
		for(int i = 0; i <= usrInt; i ++){
			theSum += i;
		}
		JOptionPane.showMessageDialog(null, "The sum of all integers up to \nand including " + usrInt  +
				" is " + theSum);
	}

}
