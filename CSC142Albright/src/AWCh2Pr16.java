// Anthony Wittemann
// Chapter 2 Program 16 Word Game
// In class 2/6/14

import javax.swing.JOptionPane;

public class AWCh2Pr16 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("Enter your name");
		int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age in years"));
		String city = JOptionPane.showInputDialog("Enter a city");
		String college = JOptionPane.showInputDialog("Enter a college");
		String profession = JOptionPane.showInputDialog("Enter a profession");
		String animal = JOptionPane.showInputDialog("Enter a type of animal");
		String petsName = JOptionPane.showInputDialog("Enter a pet's name");

		JOptionPane.showMessageDialog(null, "There once was a person named " + name + "\n" + 
		"who lived in " + city + ". At the age of " + age + ",\n" + name + " went to college at " + college + ".\n" +
		name + " graduated and went to work as a " + profession + ".\nThen, " + name + " adopted a(n) " + animal + 
		" named " + petsName + ".\nThey both lived happily ever after!");
	}

}
