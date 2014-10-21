/** 
 * @author Anthony Wittemann
 * Test 1 Account Class
 * In class 2/27/14
 */
public class Account {
	private int id;
	private double balance;
	private double annualInterestRate;
	
	public Account(){
		id = 0;
		balance = 0;
		annualInterestRate = 0;
	}
	/**
	 * 
	 * @param i id number of the account
	 * @param b balance in dollars
	 * @param a annual Interest Rate as a percentage ie 3.0
	 */
	public Account(int i, double b, double a){
		id = i;
		balance = b;
		annualInterestRate = a;
	}
	
	public int getID(){
		return id;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public double getAnnualInterestRate(){
		return annualInterestRate;
	}
	
	public void setID(int nID){
		id = nID;
	}
	
	public void setBalance(double nBalance){
		balance = nBalance;
	}
	
	public void setAnnualInterestRate(double nAIR){
		annualInterestRate = nAIR;
	}
	
	public double getMonthlyInterestRate(){
		return (annualInterestRate / 12.0);
	}
	
	public void withdraw(double wAmount){
		balance -= wAmount;
	}
	
	public void deposit(double dAmount){
		balance += dAmount;
	}
}
