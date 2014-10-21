/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 10 Parking Ticket Simulator
 * In class 3/13/14
 */
public class AWParkingMeter {
	private int minsPurchased;
	
	
	public AWParkingMeter(int mp){
		minsPurchased = mp;
	}
	
	/**
	 * Copy Constructor
	 * @param pm instance of another parking meter
	 */
	public AWParkingMeter(AWParkingMeter pm){
		this(pm.getMinsPurchased());
	}
	
	public int getMinsPurchased(){
		return minsPurchased;
	}
	
	public void setMinsPurchased(int nMP){
		minsPurchased = nMP;
	}
	
	public String toString(){
		return "Minutes of Parking Time Purchased: " + minsPurchased;
	}
}
