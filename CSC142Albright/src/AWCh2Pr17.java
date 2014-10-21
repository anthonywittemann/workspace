// Anthony Wittemann
// Chapter 2 Program 17 Stock Transaction Program
// In class 2/6/14

public class AWCh2Pr17 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int sharesPurchased = 1000;
		double purchaseSharePrice = 32.87;
		double purchasePrice = purchaseSharePrice * sharesPurchased;
		double buyCommission = .02 * purchasePrice;
		
		int sharesSold = sharesPurchased;
		double sellingSharePrice = 33.92;
		double sellingPrice = sellingSharePrice * sharesSold;
		double saleCommission = .02 * sellingPrice;
		
		double profit = sellingPrice - purchasePrice - buyCommission - saleCommission;
		
		System.out.printf("Amount paid for stocks: $" + "%1$.2f", purchasePrice);
		System.out.printf("\nAmount paid in Commission for purchase: $" + "%1$.2f", buyCommission);
		System.out.printf("\nAmount stocks sold for: $" + "%1$.2f", sellingPrice);
		System.out.printf("\nAmount paid in Commission for sale: $" + "%1$.2f", saleCommission);
		System.out.printf("\nProfit: $" + "%1$.2f", profit);
	}

}
