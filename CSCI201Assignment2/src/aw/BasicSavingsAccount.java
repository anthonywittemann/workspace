package aw;

// Anthony Wittemann
// MW 8:30 Dr. Miller
// Assignment 2 9/21/14

// If the combined balance of all of a user’s accounts is less than $1000.00, he will have a Basic savings account.
// 0.1% annual interest rate

public class BasicSavingsAccount extends SavingsAccount{

	public BasicSavingsAccount(double balance) {
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
		return "Basic Savings";
	}

}
