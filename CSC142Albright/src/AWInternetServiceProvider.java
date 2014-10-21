/** 
 * @author Anthony Wittemann
 * Chapter 4 Program 11 Internet Service Provider
 * HW Due 2/20/14 
 */
public class AWInternetServiceProvider {
	private double totalMonthlyCharges;
	private char packageLetter;
	private int numHoursUsed;
	
	/**
	 * 
	 * @param pac - Internet package 'A', 'B', or 'C'
	 * @param NHU - the total hours the user was on the Internet within the past month
	 */
	public AWInternetServiceProvider(char pac, int NHU){
		packageLetter = pac;
		numHoursUsed = NHU;
		
		calculateMonthlyBill();
	}
	
	
	
	public void setPackage(char nP){
		packageLetter = nP;
		calculateMonthlyBill();
	}
	
	public void setNumHoursUsed(int nNHU){
		numHoursUsed = nNHU;
		calculateMonthlyBill();
	}
	
	public char getPackage(){
		return packageLetter;
	}
	
	public int getNumHoursUsed(){
		return numHoursUsed;
	}
	
	/**
	 * calculates the total monthly charges of the Internet user
	 * based on the package the user has (A, B, or C)
	 * 
	 * If the package is not A, B, or C, the monthly bill will be set to -1
	 */
	public void calculateMonthlyBill(){
		char p = packageLetter;
		int n = numHoursUsed;
		
		if(p == 'A' || p == 'a'){
			if(n <= 10){
				totalMonthlyCharges = 9.95;
			}
			else{
				totalMonthlyCharges = 9.95 + 2.00 * (numHoursUsed - 10);
			}
		}
		else if(p == 'B' || p == 'b'){
			if(n <= 20){
				totalMonthlyCharges = 14.95;
			}
			else{
				totalMonthlyCharges = 14.95 + 1.00 * (numHoursUsed - 20);
			}
		}
		else if (p == 'C' || p == 'c'){
			totalMonthlyCharges = 19.95;
		}
		else{
			totalMonthlyCharges = -1;
		}
		
	}
	
	public double getTotalMonthlyCharges(){
		return totalMonthlyCharges;
	}
	
}
