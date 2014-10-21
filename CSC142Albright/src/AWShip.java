/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 10 - Ship, Cruise Ship & Cargo Ship
 * HW Due 4/17/14
 */
public class AWShip {
	private String name;
	private String yrBuilt;

	public AWShip(){
		name = "";
		yrBuilt = "";
	}
	
	public AWShip(String n, String yb){
		name = n;
		yrBuilt = yb;
	}
	
	public String getName(){
		return name;
	}
	
	public String getYearBuilt(){
		return yrBuilt;
	}
	
	public void setName(String nN){
		name = nN;
	}
	
	public void setYearBuilt(String nYB){
		yrBuilt = nYB;
	}
	
	public String toString(){
		return "Name: " + name + "\tYear Built: " + yrBuilt;
	}
	
}
