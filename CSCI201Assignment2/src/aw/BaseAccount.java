package aw;

// Anthony Wittemann
// MW 8:30 Dr. Miller
// Assignment 2 9/21/14

public abstract class BaseAccount {
	
	private double balance;
    public BaseAccount(double balance) {
          setBalance(balance);
    }
    public double getBalance() {
          return this.balance;
    }
    public void setBalance(double balance) {
          this.balance = balance;
}
//returns the balance after numYears has passed
//if the account has interest, this method will account for it 
    protected abstract double getBalanceAfterNumYears(int numYears);
    
    
//returns a string representing the type of account
//such as “Checking”, “Deluxe Savings”, etc.
public abstract String getAccountType();

    public boolean withdraw(double amount) {
    	balance -= amount;
    	if(balance > 0){
    		return true;
    	}
    	else{
    		return false;
    	}
          // TODO Is this an OK implementation?
}
    public boolean deposit(double amount) {
    	balance += amount;
    	if(balance > 0){
    		return true;
    	}
    	else{
    		return false;
    	}
          // TODO Is this an OK implementation?
}

}
