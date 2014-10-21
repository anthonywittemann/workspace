/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 10 Parking Ticket Simulator
 * In class 3/13/14
 */
public class AWParkingTicket {
	private AWParkedCar illegallyParkedCar;
	private AWPoliceOfficer cop;
	private double fine;
	private int minsOver;
	
	
	public AWParkingTicket(){
		
	}
	
	public AWParkingTicket(AWParkedCar ipc, AWPoliceOfficer c, int mo){
		illegallyParkedCar = new AWParkedCar(ipc);
		cop = new AWPoliceOfficer(c);
		minsOver = mo;
		calculateFine();
	}
	
	/**
	 * Copy Constructor
	 * @param pt instance of another parking ticket
	 */
	public AWParkingTicket(AWParkingTicket pt){
		this(pt.getIllegallyParkedCar(), pt.getCop(), pt.getMinsOver());
	}
	
	private AWPoliceOfficer getCop() {
		// TODO Auto-generated method stub
		return null;
	}

	private int getMinsOver() {
		return minsOver;
	}

	private AWParkedCar getIllegallyParkedCar() {
		return illegallyParkedCar;
	}

	public void calculateFine(){
		if(minsOver <= 60 && minsOver > 0){
			fine = 25;
		}
		else if(minsOver > 60){
			fine =  25 + (minsOver/60) * 10;
		}
		else{
			fine = 0;
		}
	}
	
	public double getFine(){
		return fine;
	}
	
	private int getOfficerBadgeNum() {
		return cop.getOfficerBadgeNum();
	}

	private String getOfficerName() {
		return cop.getOfficerName();
	}
	
	private int getLicenseNumber() {
		return illegallyParkedCar.getLicenseNumber();
	}

	private String getColor() {
		return illegallyParkedCar.getColor();
	}

	private String getModel() {
		return illegallyParkedCar.getModel();
	}

	private String getMake() {
		return illegallyParkedCar.getMake();
	}
	
	public void setMake(String nMa){
		illegallyParkedCar.setMake(nMa);
	}
	
	public void setModel(String nMo){
		illegallyParkedCar.setModel(nMo);
	}
	
	public void setColor(String nCo){
		illegallyParkedCar.setColor(nCo);;
	}
	
	public void setLicenseNumber(int nLN){
		illegallyParkedCar.setLicenseNumber(nLN);;
	}
	
	public void setOfficerName(String nON){
		cop.setOfficerName(nON);
	}
	
	public void setOfficerBadgeNum(int nOBN){
		cop.setOfficerBadgeNum(nOBN);
	}
	
	public String toString(){
		return "Make: " + getMake() + "  Model: " + getModel() + "  Color: " + getColor() + 
				"  LicenseNumber " + getLicenseNumber() + "  Officer's name issuing ticket: " +
				getOfficerName() + "  Officer's Badge Number: " + getOfficerBadgeNum() +
				"  Fine: $ " + fine + "  Minutes Over: + " + minsOver;
	}
}
