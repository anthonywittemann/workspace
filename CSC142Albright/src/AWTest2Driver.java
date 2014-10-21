import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Test 2 Locate Largest Program
 * In class test 4/8/14
 */
public class AWTest2Driver {
	
	public static void main(String[] args){
		int r1c1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the value for first row and first column of the 2 x 3 array: "));
		int r1c2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the value for first row and second column of the 2 x 3 array: "));
		int r1c3 = Integer.parseInt(JOptionPane.showInputDialog("Enter the value for first row and third column of the 2 x 3 array: "));
		
		int r2c1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the value for second row and first column of the 2 x 3 array: "));
		int r2c2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the value for second row and second column of the 2 x 3 array: "));
		int r2c3 = Integer.parseInt(JOptionPane.showInputDialog("Enter the value for second row and third column of the 2 x 3 array: "));
		
		int[][] a = {{r1c1, r1c2, r1c3}, {r2c1, r2c2, r2c3}};
		int[] loc = AWTest2Program.locateLargest(a);
		JOptionPane.showMessageDialog(null, "The location of the largest element is at (" + loc[0] + ", " + loc[1] + ")");
	}

}
