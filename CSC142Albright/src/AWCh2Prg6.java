// Anthony Wittemann
// Chapter 2 Program 6 Sales Prediction
// In Lab 1/30/14
public class AWCh2Prg6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final double EAST_COAST_SALES_PERCENTAGE = .62;
		int totalSales = 4600000;
		double prediction = EAST_COAST_SALES_PERCENTAGE * totalSales;
		System.out.printf("Predicted Sales: $" + "%1$.2f", prediction);

	}

}
