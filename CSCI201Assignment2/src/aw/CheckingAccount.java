package aw;

// Anthony Wittemann
// MW 8:30 Dr. Miller
// Assignment 2 9/21/14

// 0% interest rate

public class CheckingAccount extends BaseAccount{

	public CheckingAccount(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double getBalanceAfterNumYears(int numYears) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAccountType() {
		return "Checking";
	}

}
