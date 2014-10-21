package aw;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.*;

// Anthony Wittemann
// MW 8:30 Dr. Miller
// Assignment 2 9/21/14

// Deals with login, file I/O for login info, viewing account info,
// make deposits and withdraws, determine the balance after x years, log out



public class Main {
	
	private static final String USERNAME_FILE = "usernames.txt";
	private static final String PASSWORD_FILE = "passwords.txt";
	private static final String CHECKING_FILE = "checking.txt";
	private static final String BASIC_SAVINGS_FILE = "savings.txt";
	private static final String PREMIUM_SAVINGS_FILE = "p_savings.txt";
	private static final String DELUXE_SAVINGS_FILE = "d_savings.txt";
	
	private static CheckingAccount checkingAcnt;
	private static BasicSavingsAccount basicSAcnt;
	private static PremiumSavingsAccount premiumSAcnt;
	private static DeluxeSavingsAccount deluxeSAcnt;
	
	private static int lineNumber;//used to read and write info from files
	private static double totalInAllAccounts;//used to determine whether an upgraded account needs to be created
	
	/**
	 * should use line number to identify all of user's info
	 * @param target the String/Line in file to be found
	 * @param file should be one of the constant file names
	 * @return the line in the file in which the target is found, -1 if not found
	 * @throws IOException
	 */
	private static int locateLine(Scanner scan, String target, String file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		lineNumber = 0;
		
		while ((line = br.readLine()) != null) { //loop line by line through file
			//check to see if the line exists in the file
			if(line.equals(target)){
				System.out.println("DEBUG: Return: " + lineNumber);
				return lineNumber;
			}
			lineNumber++;
		}
		br.close();
		
		System.out.println("DEBUG: Return: -1");
		return -1;
	}
	
	//creates blank files if they're not found
	public static void createFiles() throws IOException{
		File usernamesFile = new File(USERNAME_FILE);
		usernamesFile.createNewFile();
		File passwordsFile = new File(PASSWORD_FILE);
		passwordsFile.createNewFile();
	}
	
	/**
	 * Writes/appends the given info to the end of the file
	 * @param fileName the name of the file to be written to
	 * @param infoToWrite the string of info to write to the file
	 * @throws IOException
	 */
	public static void writeInfo(String fileName, String infoToWrite) throws IOException{
		File usernamesFile = new File(fileName);
		usernamesFile.createNewFile();
		FileWriter writer = new FileWriter(usernamesFile, true);
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write(infoToWrite);
		bw.newLine();
		bw.flush();
		bw.close();
	}
	
	
	public static void replaceInfoOnFile(String file, BaseAccount account) {
	      String tempFileName = "temp.txt";

	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	         br = new BufferedReader(new FileReader(file));
	         bw = new BufferedWriter(new FileWriter(tempFileName));
	         String line;
	         int lnCounter = 0;
	         while ((line = br.readLine()) != null) {
	            if (lnCounter == lineNumber){
	            	line = String.valueOf(account.getBalance());
	            }
	            bw.write(line+"\n");
	            lnCounter++;
	         }
	         br.close();
	         bw.close();
	      } catch (IOException ioe) {
	         System.out.println(ioe.getMessage());
	      } 
	      //delete old file
	      File oldFile = new File(file);
	      oldFile.delete();

	      // rename temp file's name to old file name
	      File newFile = new File(tempFileName);
	      newFile.renameTo(oldFile);

	   }
	
	/**
	 * 
	 * @param userLine the line in the file used to id the user
	 * @param username
	 * @param scan reference to the scanner currently in use
	 */
	public static void displayMainMenu(int userLine, String username, Scanner scan){
		//Display Account Menu
		System.out.println("\nWelcome to your accounts, " + username);
		System.out.print("\t1: View Account Information\n\t2: Make a deposit\n" +
				"\t3: Make a withdrawal\n\t4: Determine Balance in x years\n" +
				"\t5: Logout\nWhat would you like to do?");
		
		//perform action based on choice
		String input = scan.nextLine();
		
		try{
			int choice = Integer.parseInt(input);
			
			/***** VIEW ACCOUNT INFO ***** VIEW ACCOUNT INFO ***** VIEW ACCOUNT INFO *****/
			if(choice == 1){// View account information
				// read information from classes
				
				System.out.println("\nYou have a checking account with a balance of $" + checkingAcnt.getBalance());
			
				//figure out which accounts the user has
				if(basicSAcnt.getBalance() > 0){
					System.out.println("You have a basic savings account with a balance of $" + basicSAcnt.getBalance());
				}
				if(premiumSAcnt.getBalance() > 0){
					System.out.println("You have a premium savings account with a balance of $" + premiumSAcnt.getBalance());
				}
				if(deluxeSAcnt.getBalance() > 0){
					System.out.println("You have a deluxe savings account with a balance of $" + deluxeSAcnt.getBalance());
				}
				
				
				
				displayMainMenu(userLine, username, scan);
			}
			
			
			/***** DEPOSIT  ***** DEPOSIT  ***** DEPOSIT  ***** DEPOSIT  ***** DEPOSIT  *****/
			else if(choice == 2){// make a deposit
				boolean isValidInput = false;
				while(!isValidInput){
					try{
						boolean hasPremiumAcnt = false;
						boolean hasDeluxeAcnt = false;
						//figure out which accounts the user has
						if(premiumSAcnt.getBalance() > 0){
							hasPremiumAcnt = true;
						}
						if(deluxeSAcnt.getBalance() > 0){
							hasDeluxeAcnt = true;
						}
						
						
						if(hasDeluxeAcnt && hasPremiumAcnt){
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account" +
									"\n\t3: Premium Savings Account \n\t4: Deluxe Savings Account");
						}
						else if(hasDeluxeAcnt){
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account" +
									"\n\t3: Deluxe Savings Account");
						}
						else if(hasPremiumAcnt){
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account" +
									"\n\t3: Premium Savings Account");
						}
						else{
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account");
						}
						System.out.print("Into which account would you like to make a deposit? ");
						
						String depositAcntInput = scan.nextLine();
						short depositAcnt = Short.parseShort(depositAcntInput);
						
						if(hasDeluxeAcnt && hasPremiumAcnt){
							if(depositAcnt > 4 || depositAcnt < 1){
								throw new NumberFormatException("Number entered > 4 or < 1");
							}
						}
						else if(hasDeluxeAcnt){
							if(depositAcnt > 3 || depositAcnt < 1){
								throw new NumberFormatException("Number entered > 3 or < 1");
							}
						}
						else if(hasPremiumAcnt){
							if(depositAcnt > 3 || depositAcnt < 1){
								throw new NumberFormatException("Number entered > 3 or < 1");
							}
						}
						else{
							if(depositAcnt > 2 || depositAcnt < 1){
								throw new NumberFormatException("Number entered > 2 or < 1");
							}
						}
						
						
						//make sure that a negative amount isn't deposited
						boolean isValidDepositAmnt = false;
						String amountInput = null;
						double amount = -1;
						while(!isValidDepositAmnt){
							try{
								System.out.print("How much would you like to deposit? $");
								amountInput = scan.nextLine();
								amount = Double.parseDouble(amountInput);
								if(amount < .01){
									throw new NumberFormatException("Please enter a value > $0.01");
								}
								isValidDepositAmnt = true;
							}catch(NumberFormatException nfe){
								System.out.println("\n" + nfe.getMessage());
							}
						}
						
						if(depositAcnt == 1){//deposit into checking
							checkingAcnt.deposit(amount);
							System.out.println("Deposit of $" + amount + " successfully made into checking account");
						}
						else if(depositAcnt == 2){//deposit into basic savings 
							basicSAcnt.deposit(amount);
							System.out.println("Deposit of $" + amount + " successfully made into basic savings account");
						}
						else if(depositAcnt == 3){//deposit into premium savings 
							premiumSAcnt.deposit(amount);
							System.out.println("Deposit of $" + amount + " successfully made into premium savings account");
						}
						else if(depositAcnt == 4){//deposit into deluxe savings
							deluxeSAcnt.deposit(amount);
							System.out.println("Deposit of $" + amount + " successfully made into deluxe savigns account");
						}
						
						

						//check if total value of all accounts exceeds threshold then create upgraded account and erase balance in old one
						totalInAllAccounts = checkingAcnt.getBalance() + basicSAcnt.getBalance()
								+ premiumSAcnt.getBalance() + deluxeSAcnt.getBalance();
						
						//upgrade to premium savings account and transfer balance from basic account
						if(totalInAllAccounts > 10000 && premiumSAcnt.getBalance() == 0 && deluxeSAcnt.getBalance() == 0){
							premiumSAcnt.setBalance(basicSAcnt.getBalance());
							basicSAcnt.setBalance(0);
						}
						
						//upgrade to deluxe savings account
						if(totalInAllAccounts > 100000 && deluxeSAcnt.getBalance() == 0){
							deluxeSAcnt.setBalance(premiumSAcnt.getBalance());
							deluxeSAcnt.setBalance(0);
						}
						
						isValidInput = true;
						
					}catch(NumberFormatException nfe){
						System.out.println("Please enter a valid integer corresponding to one of the account types you have");
					}
				}
				
				
				displayMainMenu(userLine, username, scan);
			}
			
			/***** WITHDRAW ***** WITHDRAW ***** WITHDRAW ***** WITHDRAW ***** WITHDRAW *****/
			else if(choice == 3){// make a withdrawal
				boolean isValidInput = false;
				while(!isValidInput){
					try{
						boolean hasPremiumAcnt = false;
						boolean hasDeluxeAcnt = false;
						//figure out which accounts the user has
						if(premiumSAcnt.getBalance() > 0){
							hasPremiumAcnt = true;
						}
						if(deluxeSAcnt.getBalance() > 0){
							hasDeluxeAcnt = true;
						}
						
						
						if(hasDeluxeAcnt && hasPremiumAcnt){
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account" +
									"\n\t3: Premium Savings Account \n\t4: Deluxe Savings Account");
						}
						else if(hasDeluxeAcnt){
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account" +
									"\n\t3: Deluxe Savings Account");
						}
						else if(hasPremiumAcnt){
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account" +
									"\n\t3: Premium Savings Account");
						}
						else{
							System.out.println("\t1: Checking Account \n\t2: Basic Savings Account");
						}
						System.out.print("From which account would you like to make a withdrawal? ");
						
						String withdrawAcntInput = scan.nextLine();
						short withdrawAcnt = Short.parseShort(withdrawAcntInput);
						
						if(hasDeluxeAcnt && hasPremiumAcnt){
							if(withdrawAcnt > 4 || withdrawAcnt < 1){
								throw new NumberFormatException("Number entered > 4 or < 1");
							}
						}
						else if(hasDeluxeAcnt){
							if(withdrawAcnt > 3 || withdrawAcnt < 1){
								throw new NumberFormatException("Number entered > 3 or < 1");
							}
						}
						else if(hasPremiumAcnt){
							if(withdrawAcnt > 3 || withdrawAcnt < 1){
								throw new NumberFormatException("Number entered > 3 or < 1");
							}
						}
						else{
							if(withdrawAcnt > 2 || withdrawAcnt < 1){
								throw new NumberFormatException("Number entered > 2 or < 1");
							}
						}
						
						
						//make sure that not too much is withdrawn
						boolean isValidWithdrawAmnt = false;
						String amountInput = null;
						double amount = -1;
						while(!isValidWithdrawAmnt){
							try{
								System.out.print("How much would you like to withdraw? $");
								amountInput = scan.nextLine();
								amount = Double.parseDouble(amountInput);
								if(amount < .01){
									throw new NumberFormatException("Please enter a value > $0.01");
								}
								else if(checkingAcnt.getBalance() - amount <= 0){
									throw new NumberFormatException("Insufficient Funds");
								}
								isValidWithdrawAmnt = true;
							}catch(NumberFormatException nfe){
								System.out.println("\n" + nfe.getMessage());
							}
						}
						
						
						
						if(withdrawAcnt == 1){//withdraw from checking
							checkingAcnt.withdraw(amount);
							System.out.println("$" + amount + " has been successfully withdrawn from your checking account");
						}
						else if(withdrawAcnt == 2){//withdraw from basic savings 
							basicSAcnt.withdraw(amount);
							System.out.println("$" + amount + " has been successfully withdrawn from your basic savings account");
						}
						else if(withdrawAcnt == 3){//withdraw from premium savings 
							premiumSAcnt.withdraw(amount);
							System.out.println("$" + amount + " has been successfully withdrawn from your premium savings account");
						}
						else if(withdrawAcnt == 4){//withdraw from deluxe savings
							deluxeSAcnt.withdraw(amount);
							System.out.println("$" + amount + " has been successfully withdrawn from your deluxe account");
						}
						
						isValidInput = true;
						
					}catch(NumberFormatException nfe){
						System.out.println("Please enter a valid integer corresponding to one of the account types you have");
					}
				}
				
				
				displayMainMenu(userLine, username, scan);
				
			}
			
			
			/***** DETERMINE BALANCE AFTER X YEARS ***** DETERMINE BALANCE AFTER X YEARS *****/
			else if(choice == 4){// determine balance in x years
				boolean isValidInput = false;
				int years = 0;
				while(!isValidInput){
					System.out.print("In how many years? ");
					String yearsInput = scan.nextLine();
					
					try{
						years = Integer.parseInt(yearsInput);
						if(years < 1){
							throw new NumberFormatException("Number entered was less than 1");
						}
						isValidInput = true;
					}catch(NumberFormatException nfe){
						System.out.println("\nPlease enter a positive integer value\n");
					}
				}
				
				
				short acntType = 0; //0 for Basic, 1 for premium, 2 for deluxe
				double finalBalance = 0;
				double totalOtherAcntBalance = checkingAcnt.getBalance() + // get this amount from classes
						basicSAcnt.getBalance() + premiumSAcnt.getBalance() + deluxeSAcnt.getBalance();
				double combinedBalance = finalBalance + totalOtherAcntBalance;
				
				//figure out which account is currently in use
				if(acntType == 0){
					finalBalance = basicSAcnt.getBalance();
				}
				else if(acntType == 1){
					finalBalance = premiumSAcnt.getBalance();
				}
				else if(acntType == 2){
					finalBalance = deluxeSAcnt.getBalance();
				}
				
				System.out.println("Year\tAmount\tInterest");
				System.out.println("____\t______\t________");
				
				//A = P (1 + r/n)^(n*t)
				//check if the amount goes over one of the thresholds
				DecimalFormat df = new DecimalFormat("#.##");
				int i = 0;
				if(acntType == 0){//Basic .1% interest
					for(; i < years; i++){
						double interest = finalBalance * .001;
						finalBalance += interest;
						System.out.println(i + "   \t$" + df.format(finalBalance) + "   \t$" + df.format(interest));
					}
					combinedBalance = finalBalance + totalOtherAcntBalance;
					if(combinedBalance > 10000){
						acntType = 1;
					}
				}
				else if(acntType == 1){//premium 1% interest
					for(; i <= years; i++){
						double interest = finalBalance * .01;
						finalBalance += interest;
						System.out.println(i + "   \t$" + df.format(finalBalance) + "   \t$" + df.format(interest));
					}
					combinedBalance = finalBalance + totalOtherAcntBalance;
					if(combinedBalance > 100000){
						acntType = 2;
					}
				}
				else if(acntType == 2){//deluxe 5% interest
					double interest = finalBalance * .05;
					finalBalance += interest;
					System.out.println(i + "   \t$" + df.format(finalBalance) + "   \t$" + df.format(interest));
				}
				
				
				displayMainMenu(userLine, username, scan);
			}
			
			/***** LOGOUT ***** LOGOUT ***** LOGOUT ***** LOGOUT ***** LOGOUT *****/
			
			else if(choice == 5){// logout
				System.out.println("\nThank you for coming to the bank!");
				//write all the data to files--copy from old file and replace with new at specific lines
				replaceInfoOnFile(CHECKING_FILE, checkingAcnt);
				replaceInfoOnFile(BASIC_SAVINGS_FILE, basicSAcnt);
				replaceInfoOnFile(PREMIUM_SAVINGS_FILE, premiumSAcnt);
				replaceInfoOnFile(DELUXE_SAVINGS_FILE, deluxeSAcnt);
				
				//data is saved only if user logs out properly
				System.exit(0);
			}
			
			
			else{// deal with improper integer input
				System.out.println("\nPlease enter an integer from 1-5");
				
				displayMainMenu(userLine, username, scan);
			}
			
			
		}catch(NumberFormatException nfe){// deal with improper input
			System.out.println("\nPlease enter an integer from 1-5");
			
			displayMainMenu(userLine, username, scan);
		}
		
	}

	
	public static void displayEntryMenu(Scanner scan, int usrLine){
		System.out.print("Do you have a login? (Enter Y if you do)");
		
		if(scan.nextLine().equalsIgnoreCase("Y")){
			boolean isValidUsername = false;
			boolean isValidPassword = false;
			String username = null;
			
			//get a valid username
			while(!isValidUsername){
				System.out.print("Username: ");
				String usernameInput = scan.nextLine();
				
				//open the file
				try{
					//user wishes to quit
					if(usernameInput.equalsIgnoreCase("Q") || usernameInput.equalsIgnoreCase("Q ")){
						displayEntryMenu(scan, -1);
					}
					//check to see if the username exists in the file
					else if(locateLine(scan, usernameInput, USERNAME_FILE) == -1){
						System.out.println("I’m sorry, but that username does not match any at our bank. " +
								" Please try again (or enter ‘q’ to return to the main menu).");
					}
					else{
						username = usernameInput;
						isValidUsername = true;
					}
				}catch(IOException ioe){
					System.out.println("IOException: " + ioe);
				}	
			}
			//get a valid password
			while(!isValidPassword){
				System.out.print("Password: ");
				String passwordInput = scan.nextLine();
				
				//open the file
				try{
					//check to see if the password exists in the file
					usrLine = locateLine(scan, passwordInput, PASSWORD_FILE);
					//user wishes to quit
					if(passwordInput.equalsIgnoreCase("Q") || passwordInput.equalsIgnoreCase("Q ")){
						displayEntryMenu(scan, -1);
					}
					if(usrLine == -1){
						System.out.println("I’m sorry, but that password does not match any at our bank. " +
								" Please try again (or enter ‘q’ to return to the main menu).");
					}
					else{
						isValidPassword = true;
						//load data from files into classes
						BufferedReader checkingR = new BufferedReader(new FileReader(CHECKING_FILE));
						double checkingBalance = Double.parseDouble(checkingR.readLine());
						for(int i = 1; i <= lineNumber; i++){
							checkingBalance = Double.parseDouble(checkingR.readLine());
						}
						
						checkingAcnt = new CheckingAccount(checkingBalance);
						checkingR.close();
						
						BufferedReader bSavingsR = new BufferedReader(new FileReader(BASIC_SAVINGS_FILE));
						double bSavingsBalance = Double.parseDouble(bSavingsR.readLine());
						for(int i = 1; i <= lineNumber; i++){
							bSavingsBalance = Double.parseDouble(bSavingsR.readLine());
						}
						basicSAcnt = new BasicSavingsAccount(bSavingsBalance);
						bSavingsR.close();
						
						BufferedReader pSavingsR = new BufferedReader(new FileReader(PREMIUM_SAVINGS_FILE));
						double pSavingsBalance = Double.parseDouble(pSavingsR.readLine());
						for(int i = 1; i <= lineNumber; i++){
							pSavingsBalance = Double.parseDouble(pSavingsR.readLine());
						}
						premiumSAcnt = new PremiumSavingsAccount(pSavingsBalance);
						pSavingsR.close();
						
						BufferedReader dSavingsR = new BufferedReader(new FileReader(DELUXE_SAVINGS_FILE));
						double dSavingsBalance = Double.parseDouble(dSavingsR.readLine());
						for(int i = 1; i <= lineNumber; i++){
							dSavingsBalance = Double.parseDouble(dSavingsR.readLine());
						}
						deluxeSAcnt = new DeluxeSavingsAccount(dSavingsBalance);
						dSavingsR.close();
						
						//initially set the total amount
						totalInAllAccounts = checkingAcnt.getBalance() + basicSAcnt.getBalance() +
								premiumSAcnt.getBalance() + deluxeSAcnt.getBalance();
						
						displayMainMenu(usrLine, username, scan);
					}
				}catch(IOException ioe){
					System.out.println("IOException: " + ioe);
				}	
				
				
			}
			
			
			
		}
		else{ 
			//prompt user to create a login
			String usernameInput = null;
			//see if username exists
			boolean isValidUsername = false;
			while(!isValidUsername){
				try{
					System.out.print("Username: ");
					usernameInput = scan.nextLine();
					//check to see if the Username exists in the file
					if(usernameInput.equalsIgnoreCase("Q") || usernameInput.equalsIgnoreCase("Q ")){
						displayEntryMenu(scan, -1);
					}
					else if(locateLine(scan, usernameInput, USERNAME_FILE) == -1){
						System.out.println("Your Username is Valid");
						isValidUsername = true;
					}
					else{
						System.out.println("That username is already in use. Please enter another one (or enter 'q' to return to presious menu)");
					}
				}catch(IOException ioe){
					System.out.println("IOException: " + ioe);
				}
			}	
			
			
			
			String passwordInput = null;
			//see if password already exists in file
			boolean isValidPassword = false;
			while(!isValidPassword){
				try{
					System.out.print("Password: ");
					passwordInput = scan.nextLine();
					//check to see if the password exists in the file
					if(usernameInput.equalsIgnoreCase("Q") || usernameInput.equalsIgnoreCase("Q ")){
						displayEntryMenu(scan, -1);
					}
					else if(locateLine(scan, passwordInput, PASSWORD_FILE) == -1){
						System.out.println("Your password is Valid");
						isValidPassword = true;
					}
					else{
						System.out.println("That password is already in use. Please enter another one (or enter 'q' to return to main menu)");
					}
				}catch(IOException ioe){
					System.out.println("IOException: " + ioe);
				}
			}	
			
			
			//write login info to files and append to end
			try{
				writeInfo(USERNAME_FILE, usernameInput);
				
				writeInfo(PASSWORD_FILE, passwordInput);
			}catch(IOException ioe){
				System.out.println("IOException: " + ioe);
			}
			
			//check if input amounts are valid, write the amounts to files
			double checkingDepositAmnt = 0;
			double savingDepositAmnt = 0;
			boolean isValidUsrPW = false;
			//while loop to check if input is valid
			while(!isValidUsrPW){
				try{
					//ask how much to deposit in checking and in savings
					System.out.println("How much would you like to deposit in checking? $");
					String cda = scan.nextLine();
					checkingDepositAmnt = Double.parseDouble(cda);
					if(checkingDepositAmnt < 0){ //make sure amount deposited isn't < 0
						throw new NumberFormatException("Amount entered was < 0");
					}
					
					System.out.println("How much would you like to deposit in savings? $");
					String sda = scan.nextLine();
					savingDepositAmnt = Double.parseDouble(sda);
					if(savingDepositAmnt < 0){ //make sure amount deposited isn't < 0
						throw new NumberFormatException("Amount entered was < 0");
					}
					isValidUsrPW = true;
				}catch (NumberFormatException nfe){
					System.out.println("Please enter a valid amount >= 0");
				}
			}
			
			//write checking and savings info to files and append to end
			try{
				writeInfo(CHECKING_FILE, String.valueOf(checkingDepositAmnt));
				writeInfo(BASIC_SAVINGS_FILE, String.valueOf(savingDepositAmnt));
				writeInfo(PREMIUM_SAVINGS_FILE, String.valueOf(0)); //creates premium savings file with 0 balance
				writeInfo(DELUXE_SAVINGS_FILE, String.valueOf(0)); //creates deluxe savings file with 0 balance
				
			}catch(IOException ioe){
				System.out.println("IOException: " + ioe);
			}
			System.out.println("Thank you for opening an account with us!");
			
			//load data from files into classes
			try{
				BufferedReader checkingR = new BufferedReader(new FileReader(CHECKING_FILE));
				double checkingBalance = Double.parseDouble(checkingR.readLine());
				for(int i = 1; i <= lineNumber; i++){
					checkingBalance = Double.parseDouble(checkingR.readLine());
				}
				
				checkingAcnt = new CheckingAccount(checkingBalance);
				checkingR.close();
				
				BufferedReader bSavingsR = new BufferedReader(new FileReader(BASIC_SAVINGS_FILE));
				double bSavingsBalance = Double.parseDouble(bSavingsR.readLine());
				for(int i = 1; i <= lineNumber; i++){
					bSavingsBalance = Double.parseDouble(bSavingsR.readLine());
				}
				basicSAcnt = new BasicSavingsAccount(bSavingsBalance);
				bSavingsR.close();
				
				BufferedReader pSavingsR = new BufferedReader(new FileReader(PREMIUM_SAVINGS_FILE));
				double pSavingsBalance = Double.parseDouble(pSavingsR.readLine());
				for(int i = 1; i <= lineNumber; i++){
					pSavingsBalance = Double.parseDouble(pSavingsR.readLine());
				}
				premiumSAcnt = new PremiumSavingsAccount(pSavingsBalance);
				pSavingsR.close();
				
				BufferedReader dSavingsR = new BufferedReader(new FileReader(DELUXE_SAVINGS_FILE));
				double dSavingsBalance = Double.parseDouble(dSavingsR.readLine());
				for(int i = 1; i <= lineNumber; i++){
					dSavingsBalance = Double.parseDouble(dSavingsR.readLine());
				}
				deluxeSAcnt = new DeluxeSavingsAccount(dSavingsBalance);
				
				//initially set the total amount
				totalInAllAccounts = checkingAcnt.getBalance() + basicSAcnt.getBalance() +
						premiumSAcnt.getBalance() + deluxeSAcnt.getBalance();
				
				dSavingsR.close();
			}catch(IOException ioe){
				
			}
			
			//go to main menu
			displayMainMenu(usrLine, usernameInput, scan);
		
		}
	}

	
	
	
	public static void main(String[] args) {
		//try to open the files and if not found, create the files
		int usrLine = -1;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(USERNAME_FILE));
			BufferedReader reader1 = new BufferedReader(new FileReader(PASSWORD_FILE));
			System.out.println("DEBUG: Files exist");
			
		}catch(FileNotFoundException fnfe){ //catch if file already exists
			System.out.println("DEBUG: One of the files does NOT exist");
			
			try{
				createFiles();
			}catch(IOException ioe){
				System.out.println("IOException: " + ioe);
			}
			
		}catch(IOException ioe){
			System.out.println("IOException: " + ioe);
		}
		
		Scanner scan = new Scanner(System.in);
		displayEntryMenu(scan, usrLine);
		scan.close();

	}

}
