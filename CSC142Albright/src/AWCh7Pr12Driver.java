/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 12 - 2D Array Operation
 * HW Due 4/3/14
 */
public class AWCh7Pr12Driver {

	public static void main(String[] args) {
		int[][] array0 = {{3, 4, 5, 6}, {2, 12, 24, 1}, {9, 11, 21, 4}};
		float[][] array1 = {{3, 4, 5, 6}, {2, 12, 24, 1}, {9, 11, 21, 4}};
		double[][] array2 = {{3, 4, 5, 6}, {2, 12, 24, 1}, {9, 11, 21, 4}};
		long[][] array3 = {{3, 4, 5, 6}, {2, 12, 24, 1}, {9, 11, 21, 4}};
		
		System.out.println("Highest element in Int array row 1: " + AW2DArrayOperations.getHighestInRow(array0, 0));
		System.out.println("Highest element in Float array row 2: " + AW2DArrayOperations.getHighestInRow(array1, 1));
		System.out.println("Highest element in Double array row 3: " + AW2DArrayOperations.getHighestInRow(array2, 2));
		System.out.println("Highest element in Long array row 1: " + AW2DArrayOperations.getHighestInRow(array3, 0) + "\n");
		
		System.out.println("Lowest element in Int array row 1: " + AW2DArrayOperations.getLowestInRow(array0, 0));
		System.out.println("Lowest element in Float array row 2: " + AW2DArrayOperations.getLowestInRow(array1, 1));
		System.out.println("Lowest element in Double array row 3: " + AW2DArrayOperations.getLowestInRow(array2, 2));
		System.out.println("Lowest element in Long array row 1: " + AW2DArrayOperations.getLowestInRow(array3, 0) + "\n");
		
		System.out.println("Total of elements in Int array column 1: " + AW2DArrayOperations.getColumnTotal(array0, 0));
		System.out.println("Total of elements in Float array column 2: " + AW2DArrayOperations.getColumnTotal(array1, 1));
		System.out.println("Total of elements in Double array column 3: " + AW2DArrayOperations.getColumnTotal(array2, 2));
		System.out.println("Total of elements in Long array column 4: " + AW2DArrayOperations.getColumnTotal(array3, 3) + "\n");
		
		System.out.println("Total of elements in Int array row 1: " + AW2DArrayOperations.getRowTotal(array0, 0));
		System.out.println("Total of elements in Float array row 2: " + AW2DArrayOperations.getRowTotal(array1, 1));
		System.out.println("Total of elements in Double array row 3: " + AW2DArrayOperations.getRowTotal(array2, 2));
		System.out.println("Total of elements in Long array row 1: " + AW2DArrayOperations.getRowTotal(array3, 0) + "\n");
		
		System.out.println("Average of elements in Int array: " + AW2DArrayOperations.getAverage(array0));
		System.out.println("Average of elements in Float array: " + AW2DArrayOperations.getAverage(array1));
		System.out.println("Average of elements in Double array: " + AW2DArrayOperations.getAverage(array2));
		System.out.println("Average of elements in Long array: " + AW2DArrayOperations.getAverage(array3) + "\n");
		
		System.out.println("Total of elements in Int array: " + AW2DArrayOperations.getTotal(array0));
		System.out.println("Total of elements in Float array: " + AW2DArrayOperations.getTotal(array1));
		System.out.println("Total of elements in Double array: " + AW2DArrayOperations.getTotal(array2));
		System.out.println("Total of elements in Long array: " + AW2DArrayOperations.getTotal(array3));

	}

}
