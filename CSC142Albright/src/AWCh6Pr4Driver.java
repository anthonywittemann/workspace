import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 4 Land Tract
 * HW Due 3/11/14 
 */
public class AWCh6Pr4Driver {

	public static void main(String[] args) {
		AWLandTract tract1, tract2;
		int l1, l2, w1, w2;
		
		l1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the length of the first tract of land."));
		w1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the width of the first tract of land."));
		l2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the length of the second tract of land."));
		w2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the width of the second tract of land."));
		
		tract1 = new AWLandTract(l1, w1);
		tract2 = new AWLandTract(l2, w2);
		
		JOptionPane.showMessageDialog(null, "The area of the first tract of land is " + tract1.getArea() +
				"\nThe area of the second tract of land is " + tract2.getArea() +
				"\nThe two tracts are equal in size: " + tract1.equals(tract2));

	}

}
