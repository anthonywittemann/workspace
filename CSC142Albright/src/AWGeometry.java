/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 11 Geometry
 * HW Due 3/13/14
 */
public class AWGeometry {
	
	/**
	 * 
	 * @param r radius of the circle
	 * @return the area of that circle
	 */
	public static double getCircleArea(double r){
		if(r < 0){
			displayError();
		}
		return Math.PI * r * r;
	}
	
	/**
	 * 
	 * @param l the length of the rectangle
	 * @param w the width of the rectangle
	 * @return the area of that rectangle
	 */
	public static double getRectangleArea(double l, double w){
		if(l < 0 || w < 0){
			displayError();
		}
		return l * w;
	}
	
	/**
	 * 
	 * @param b the base length of the triangle
	 * @param h the height of the triangle
	 * @return the area of that triangle
	 */
	public static double getTriangleArea(double b, double h){
		if(b < 0 || h < 0){
			displayError();
		}
		return b * h * .5;
	}
	
	public static void displayError(){
		System.out.println("Please enter positive values only");
	}
}
