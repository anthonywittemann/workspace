/** 
 * @author Anthony Wittemann
 * Chapter 4 Program 5 Bank Charges
 * HW Due 2/13/14
 */
public class AWBankCharges {
	private double balance;
	private int checksWritten;
	
	public AWBankCharges(double b, int cw){
		balance = b;
		checksWritten = cw;
	}
	
	
	public int getChecksWritten(){
		return checksWritten;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void modifyChecksWritten(int newChecksWritten){
		checksWritten += newChecksWritten;
	}
	
	public void modifyBalance(double newCharges, double newCredits){
		balance -= newCharges;
		balance += newCredits;
	}
	
	public void chargeMonthlyServiceFees(){
		balance -= getMonthlyServiceFees();
	}
	
	public double getMonthlyServiceFees(){
		double msf = 0;
		
		if(checksWritten < 20){
			msf =  10.0 + .10 * checksWritten;
		}
		else if(checksWritten < 40){
			msf = 10.0 + .08 * checksWritten;
		}
		else if(checksWritten < 60){
			msf = 10.0 + .06 * checksWritten;
		}
		else{
			msf = 10.0 + .04 * checksWritten;
		}
		
		if(balance < 400){
			msf += 15;
		}
		
		return msf;
	}

}
