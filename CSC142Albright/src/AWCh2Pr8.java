// Anthony Wittemann
// Chapter 2 Program 8 Sales tax
// Due 2/4/14
public class AWCh2Pr8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//The amount for the purchase is $300
		float purchaseAmount = (float) 300.00;
		final float TOTAL_ST_RATE = (float) .06; 
		final float STATE_ST_RATE = (float) .04;
		final float LOCAL_ST_RATE = (float) .02;
		
		float totalST = (float) purchaseAmount * TOTAL_ST_RATE;
		float stateST = (float) purchaseAmount * STATE_ST_RATE;
		float localST = (float) purchaseAmount * LOCAL_ST_RATE;
		float totalCost = (float) purchaseAmount + totalST;

		System.out.printf("Subtotal: $" + "%1$.2f", purchaseAmount);
		System.out.println();
		System.out.printf("State Sales Tax: $" + "%1$.2f", stateST);
		System.out.println();
		System.out.printf("Local Sales Tax: $" + "%1$.2f", localST); 
		System.out.println();
		System.out.printf("Sales Tax Total: $" + "%1$.2f", totalST);
		System.out.println();
		System.out.printf("Sale Total: $" + "%1$.2f", totalCost);
		
	}

}
