// Anthony Wittemann
// Chapter 2 Program 15 Energy Drink Consumption
// In class 2/6/14
public class AWCh2Pr15 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int customersSurveyed = 12467;
		int oneOrMorePerWeek = (int) (customersSurveyed * .14);
		int preferCitrus = (int) (oneOrMorePerWeek * .64);
		System.out.println("Approximate number of customers surveyed who " +
				"drink one or more energy drinks per week: " + oneOrMorePerWeek);
		System.out.println("Approximate number of customers surveyed who " +
				"prefer citrus-flavored energy drinks: " + preferCitrus);
	}

}
