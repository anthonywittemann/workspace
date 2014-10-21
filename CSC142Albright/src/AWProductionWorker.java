/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 1 - Employee and Production Worker Classes
 * Chapter 10 Program 11 - Exception Project
 * In class 4/10/14, In class 4/24/14
 */
public class AWProductionWorker extends AWEmployee{
	private int shift; //1 for day, 2 for night
	private double hrlyPayRate;
	
	public AWProductionWorker(){
		super();
		shift = 0;
		hrlyPayRate = 0;
	}
	
	public AWProductionWorker(String n, int hd, String en, int s, double hpr) throws AWShiftException, AWInvalidPayRateException{
		super(n, hd, en);
		setShift(s);
		setHrlyPayRate(hpr);
	}
	
	public AWProductionWorker(String n, int hd, String en){
		super(n, hd, en);
		shift = 0;
		hrlyPayRate = 0;
	}
	
	
	public int getShift(){
		return shift;
	}
	
	public double getHrlyPayRate(){
		return hrlyPayRate;
	}
	
	public void setShift(int nS) throws AWShiftException{
		if(nS < 1 || nS > 2){
			throw new AWShiftException("Shift should be either 1 for day or 2 for night");
		}
		shift = nS;
	}
	
	public void setHrlyPayRate(double nHPR) throws AWInvalidPayRateException{
		if(nHPR < 0){
			throw new AWInvalidPayRateException("Pay Rate cannot be < 0");
		}
		hrlyPayRate = nHPR;
	}
	
	public String toString(){
		String s;
		if(shift == 1){
			s = "day";
		}
		else if(shift == 2){
			s = "night";
		}
		else{
			s = "none";
		}
		return "Shift: " + s + "\nHourly Pay Rate: $" + hrlyPayRate;
	}
	
}
