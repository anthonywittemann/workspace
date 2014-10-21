import java.util.ArrayList;

/** 
 * @author Anthony Wittemann
 * Chapter 7 Workbench 9,10
 * HW Due 4/01/14
 */
public class AWWorkbench9n10 {

	public static void main(String[] args) {
		// Workbench 9
		double[][] values = new double[10][20];
		
		for(int i = 0; i < values.length; i ++){
			for(int j = 0; j < values[i].length; j ++){
				values[i][j] += (200 * Math.random() - 100);;
			}
		}
		
		double total = 0;
		for(int i = 0; i < values.length; i ++){
			for(int j = 0; j < values[i].length; j ++){
				total += values[i][j];
			}
		}
		System.out.println("The total of the elements stored in the values array is: " + total + "\n");
		
		// Workbench 10
		int[][] days = new int[29][5];
		
		for(int i = 0; i < days.length; i ++){
			for(int j = 0; j < days[i].length; j ++){
				days[i][j] += (int) (200 * Math.random() - 100);
			}
		}
		
		int[] rowsTotal = new int[29];
		int[] columnsTotal = new int[5];
		for(int i = 0; i < days.length; i ++){
			for(int j = 0; j < days[i].length; j ++){
				rowsTotal[i] += days[i][j];
				columnsTotal[j] += days[i][j];
			}
		}
		
		for(int row = 0; row < rowsTotal.length; row ++)
			System.out.println("The total of row " + (row + 1) + " is: " + rowsTotal[row]);
		
		System.out.println();
		for(int column = 0; column < columnsTotal.length; column ++)
			System.out.println("The total of column " + (column + 1) + " is: " + columnsTotal[column]);
	}

}
