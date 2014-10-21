/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 10 Parking Ticket Simulator
 * In class 3/13/14
 */
public class AWPoliceOfficer {
	private int badgeNumber;
	private String officerName;
	
	public AWPoliceOfficer(int bn, String on){
		badgeNumber = bn;
		officerName = on;
	}

	
	/**
	 * Copy Constructor
	 * @param c instance of another police officer
	 */
	public AWPoliceOfficer(AWPoliceOfficer c) {
		this(c.getOfficerBadgeNum(), c.getOfficerName());
	}
	
	public AWParkingTicket parkingTicketPatrol(AWParkedCar pc, AWParkingMeter pm){
		AWParkingTicket ticket = null; 
		int minsOver = pc.getNumMinsParked() - pm.getMinsPurchased();
		if(minsOver > 0){
			ticket = new AWParkingTicket(pc, this, minsOver);
		}
		
		return ticket;
	}

	public int getOfficerBadgeNum() {
		return badgeNumber;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String nON) {
		officerName = nON;
	}

	public void setOfficerBadgeNum(int nOBN) {
		badgeNumber = nOBN;
	}

}
