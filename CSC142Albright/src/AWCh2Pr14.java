// Anthony Wittemann
// Chapter 2 Program 14 Stock Commission
// Due 2/4/14
public class AWCh2Pr14 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int sharesBought = 600;
		float pricePerShare = (float) 21.77;
		float commissionPercentage = (float) .02;
		
		float amntPaid = sharesBought * pricePerShare;
		float commission = commissionPercentage * amntPaid;
		float totalPaid =  amntPaid + commission;
		
		System.out.printf("Amount Paid for stocks: $ " + "%1$.2f", amntPaid);
		System.out.println();
		System.out.printf("Amount Paid for commission: $ " + "%1$.2f", commission);
		System.out.println();
		System.out.printf("Total Amount Paid: $ " + "%1$.2f", totalPaid);
		System.out.println();
		
		
	}

}
