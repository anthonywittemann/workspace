/** 
 * @author Anthony Wittemann
 * Chapter 3 Program 9 Pet
 * In class 2/6/14
 */

import javax.swing.JOptionPane;

public class AWCh3Pr9PetDriver {

	/**
	 * @param args
	 * 
	 * Creates a pet based on user input
	 */
	public static void main(String[] args) {
		AWPet userPet;
		
		String petName = JOptionPane.showInputDialog("Enter the name of your Pet");
		int petAge = Integer.parseInt(JOptionPane.showInputDialog("Enter the age of your Pet in years"));
		String petType = JOptionPane.showInputDialog("Enter the type or breed of your Pet");
		
		userPet = new AWPet(petName, petAge, petType);
		
		JOptionPane.showMessageDialog(null, "The name of your Pet is " + userPet.getName() + 
				" that is " + userPet.getAge() + " years old and is a " + userPet.getType());

	}

}
