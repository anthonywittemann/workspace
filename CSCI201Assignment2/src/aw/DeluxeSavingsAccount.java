package aw;

// Anthony Wittemann
// MW 8:30 Dr. Miller
// Assignment 2 9/21/14

// If the combined balance of all of his accounts is greater than $100,000.00, he will have a Deluxe savings account.
// 5% annual interest rate

public class DeluxeSavingsAccount extends SavingsAccount{

	public DeluxeSavingsAccount(double balance) {
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
		return "Deluxe Savings";
	}

}
