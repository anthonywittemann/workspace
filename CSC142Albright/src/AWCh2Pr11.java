// Anthony Wittemann
// Chapter 2 Program 11 Circuit Board Profit
// Due 2/6/14

import java.util.Scanner;

public class AWCh2Pr11 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("What is the retail price of the circuit board? ");
		Scanner sc = new Scanner(System.in);
		double retailPrice = sc.nextDouble();
		double profit = retailPrice * 0.40;
		System.out.printf("The profit is $"+ "%1$.2f", profit);
		
	}

}
