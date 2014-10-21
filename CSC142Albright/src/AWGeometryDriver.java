import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 11 Geometry
 * HW Due 3/13/14
 */
public class AWGeometryDriver {

	public static void main(String[] args) {
		boolean isInvalidInput = true;
		int choice = 0;
		String dialog = "Geometry Calcular \n" +
				"1. Calculate the area of a Circle\n" +
				"2. Calculate the area of a Rectangle\n" +
				"3. Calculate the area of a Triangle\n" +
				"4. Quit\n\nEnter your choice (1-4): ";
		do{
			choice = Integer.parseInt(JOptionPane.showInputDialog(dialog));
			if(choice >= 1 && choice <= 4){
				isInvalidInput = false;
			}
			else{
				JOptionPane.showMessageDialog(null, "Please enter a valid choice of an integer 1-4");
			}
		}while(isInvalidInput);
		
		if(choice == 1){
			double radius = Double.parseDouble(JOptionPane.showInputDialog("Enter the radius of a Circle"));
			double area = AWGeometry.getCircleArea(radius);
			JOptionPane.showMessageDialog(null, "The area of the circle is " + area);
		}
		else if(choice == 2){
			int length = Integer.parseInt(JOptionPane.showInputDialog("Enter the length of a Rectangle"));
			int width = Integer.parseInt(JOptionPane.showInputDialog("Enter the width of a Rectangle"));
			double area = AWGeometry.getRectangleArea(length, width);
			JOptionPane.showMessageDialog(null, "The area of the rectangle is " + area);
		}
		else if(choice == 3){
			int base = Integer.parseInt(JOptionPane.showInputDialog("Enter the base length of a Triangle"));
			int height = Integer.parseInt(JOptionPane.showInputDialog("Enter the height of a Triangle"));
			double area = AWGeometry.getRectangleArea(base, height);
			JOptionPane.showMessageDialog(null, "The area of the triangle is " + area);
		}
	}

}
