/** 
 * @author Anthony Wittemann
 * Figure Abstract Class
 * HW Due 4/22/14
 */
public class AWFigureDriver {

	public static void main(String[] args) {
		AWFigure figure1 = new AWRectangle(2.0, 4.0);
		AWFigure figure2 = new AWTriangle(2.0, 4.0);
		System.out.println("Figure 1: " + figure1);
		System.out.println("Figure 2: " + figure2);
	}

}
