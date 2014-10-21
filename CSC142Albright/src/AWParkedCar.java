/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 10 Parking Ticket Simulator
 * In class 3/13/14
 */
public class AWParkedCar {
	private int numMinParked;
	private int licenseNumber;
	private String make;
	private String model;
	private String color;
	
	public AWParkedCar(){
		
	}
	
	public AWParkedCar(String ma, String mo, String co, int ln, int nmp){
		make = ma;
		model = mo;
		color = co;
		licenseNumber = ln;
		numMinParked = nmp;
	}
	
	/**
	 * Copy Constructor
	 * @param pc instance of another parked car class
	 */
	public AWParkedCar(AWParkedCar pc){
		this(pc.getMake(), pc.getModel(), pc.getColor(), pc.getLicenseNumber(), pc.getNumMinsParked());
	}
	
	public String getMake(){
		return make;
	}

	public String getModel(){
		return model;
	}
	
	public String getColor(){
		return color;
	}
	
	public int getLicenseNumber(){
		return licenseNumber;
	}
	
	public int getNumMinsParked(){
		return numMinParked;
	}
	
	public void setMake(String nMa){
		make = nMa;
	}
	
	public void setModel(String nMo){
		model = nMo;
	}
	
	public void setColor(String nCo){
		color = nCo;
	}
	
	public void setLicenseNumber(int ln){
		licenseNumber = ln;
	}
	
	public void setNumMinsParked(int nmp){
		numMinParked = nmp;
	}
	
	public String toString(){
		return "Make: " + make + "  Model: " + model + "  Color: " + color + 
				"  Minutes Parked: " + numMinParked + "  License Number: " + licenseNumber;
	}
}
