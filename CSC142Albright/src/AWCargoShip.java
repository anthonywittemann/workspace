/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 10 - Ship, Cruise Ship & Cargo Ship
 * HW Due 4/17/14
 */
public class AWCargoShip extends AWShip{
	private int maxCap; //in tons
	
	public AWCargoShip(){
		 super();
		 maxCap = 0;
	}
	
	public AWCargoShip(String n, String yb){
		super(n, yb);
		maxCap = 0;
	}
	
	public AWCargoShip(String n, String yb, int mc){
		super(n, yb);
		maxCap = mc;
	}
	
	public int getMaxCap(){
		return maxCap;
	}
	
	public void setMaxCap(int nMP){
		maxCap = nMP;
	}
	
	public String toString(){
		return "Name: " + super.getName() + "\tMaximum Cargo Capacity (in tons): " + maxCap;
	}
}
