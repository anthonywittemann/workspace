/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 1 Area
 * HW Due 3/06/14 
 */
public class AWCh6Pr1Driver {

	public static void main(String[] args) {
		AWArea shape = new AWArea();
		double circleArea = shape.getArea(4);
		double rectangleArea = shape.getArea(2,5);
		double cylinderArea = shape.getArea(2, 5, true);
		System.out.println("The area of a circle of radius 4 = " + circleArea + " units squared" + 
				"\nThe area of a rectangle of length 2 and width 5 = " + rectangleArea + " units squared" + 
				"\nThe area of a cylinder of radius 2 and height 5 = " + cylinderArea + " units squared");

	}

}
