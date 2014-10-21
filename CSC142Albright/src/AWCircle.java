/** 
 * @author Anthony Wittemann
 * Chapter 6 Algorithm Workbench 1
 * HW Due 3/06/14 
 */
public class AWCircle {
	 private double radius; 
	 
	 public AWCircle(){
		 radius = 0;
	 }
	 
	 public AWCircle(double r){
		 radius = r;
	 }
	 
	 	//says void in book, but that makes no sense
	   private double getArea() 
	   { 
	      return Math.PI * radius * radius; 
	   } 
	   
	   private double getRadius() 
	   { 
	      return radius; 
	   } 
	   
	   public String toString(){
		   return "Radius: " + radius + "/tArea: " + getArea();
	   }
}
