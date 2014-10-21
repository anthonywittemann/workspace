/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 10 - Ship, Cruise Ship & Cargo Ship
 * HW Due 4/17/14
 */
public class AWCruiseShip extends AWShip{
	private int maxPassengers;

	public AWCruiseShip(){
		 super();
		 maxPassengers = 0;
	}
	
	public AWCruiseShip(String n, String yb){
		super(n, yb);
		maxPassengers = 0;
	}
	
	public AWCruiseShip(String n, String yb, int mp){
		super(n, yb);
		maxPassengers = mp;
	}
	
	public int getMaxPassengers(){
		return maxPassengers;
	}
	
	public void setMaxPassengers(int nMP){
		maxPassengers = nMP;
	}
	
	public String toString(){
		return "Name: " + super.getName() + "\tMaximum Passengers: " + maxPassengers;
	}
	
}
