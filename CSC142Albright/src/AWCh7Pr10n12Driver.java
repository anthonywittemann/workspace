/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 10, 12 - Array Operation, 2D arrays
 * HW Due 4/01/14
 */
public class AWCh7Pr10n12Driver {

	public static void main(String[] args) {
		int[] array0 = {0,3,921,-231,389,9};
		float[] array1 = {0,3,921,-231,389,9};
		double[] array2 = {0.01,3.23,921.54,-231.23,389.87,9.05};
		long[] array3 = {0,3,921,-231,389,9};
		
		System.out.println("Highest element in Int array: " + AWArrayOperations.getHighest(array0));
		System.out.println("Highest element in Float array: " + AWArrayOperations.getHighest(array1));
		System.out.println("Highest element in Double array: " + AWArrayOperations.getHighest(array2));
		System.out.println("Highest element in Long array: " + AWArrayOperations.getHighest(array3) + "\n");
		
		System.out.println("Lowest element in Int array: " + AWArrayOperations.getLowest(array0));
		System.out.println("Lowest element in Float array: " + AWArrayOperations.getLowest(array1));
		System.out.println("Lowest element in Double array: " + AWArrayOperations.getLowest(array2));
		System.out.println("Lowest element in Long array: " + AWArrayOperations.getLowest(array3) + "\n");
		
		System.out.println("Average of elements in Int array: " + AWArrayOperations.getAverage(array0));
		System.out.println("Average of elements in Float array: " + AWArrayOperations.getAverage(array1));
		System.out.println("Average of elements in Double array: " + AWArrayOperations.getAverage(array2));
		System.out.println("Average of elements in Long array: " + AWArrayOperations.getAverage(array3) + "\n");
		
		System.out.println("Total of elements in Int array: " + AWArrayOperations.getTotal(array0));
		System.out.println("Total of elements in Float array: " + AWArrayOperations.getTotal(array1));
		System.out.println("Total of elements in Double array: " + AWArrayOperations.getTotal(array2));
		System.out.println("Total of elements in Long array: " + AWArrayOperations.getTotal(array3));
	}

}
