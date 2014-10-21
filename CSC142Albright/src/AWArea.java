/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 1 Area
 * HW Due 3/06/14 
 */
public class AWArea {
	
	/**
	 * 
	 * @param r radius of the circle
	 * @return the area of the circle
	 */
	public double getArea(double r){
		return Math.PI * r * r;
	}
	
	/**
	 * 
	 * @param l length of the rectangle
	 * @param w width of the rectangle
	 * @return the area of the rectangle
	 */
	public double getArea(double l, double w){
		return l * w;
	}
	/**
	 * 
	 * @param r radius of the cylinder
	 * @param h height of the cylinder
	 * @param b used to differentiate from method that return Rectangle area
	 *          can be any boolean value
	 * @return the surface area of the cylinder 
	 * which is 2*pi*r^2 + 2*pi*r*h NOT h*pi*r^2
	 */
	public double getArea(double r, double h, boolean b){
		return 2 * Math.PI * r * r + 2 * Math.PI * r* h;
	}
}
