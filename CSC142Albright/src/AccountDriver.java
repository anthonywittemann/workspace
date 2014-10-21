import java.io.IOException;

/** 
 * @author Anthony Wittemann
 * Test 1 Account Class
 * In class 2/27/14
 */
public class AccountDriver {

	public static void main(String[] args) {
		Account account = new Account(1122, 20000.00, 4.5);
		account.withdraw(2500.00);
		account.deposit(3000.00);
		System.out.println("Account Balance: " + account.getBalance() + 
				"\nAccount Monthly Interest Rate: " + account.getMonthlyInterestRate() + "%" + 
				"\nMonthly Interest: $" + account.getMonthlyInterestRate() * account.getBalance());

	}

}
