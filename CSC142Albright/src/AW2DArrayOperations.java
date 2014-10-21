/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 12 - 2D Array Operation
 * HW Due 4/3/14
 */
public class AW2DArrayOperations {
	
//	  Demonstrate the class in a complete program with test data stored in two-dimensional 
//	  arrays of various data types. 

	
	public static int getRowTotal(int[][] array, int row){
		int total = 0;
		for(int i2 = 0; i2 < array[row].length; i2 ++)
				total += array[row][i2];
		return total;
	}
	
	public static float getRowTotal(float[][] array, int row){
		float total = 0;
		for(int i2 = 0; i2 < array[row].length; i2 ++)
			total += array[row][i2];
		return total;
	}
	
	public static double getRowTotal(double[][] array, int row){
		double total = 0;
		for(int i2 = 0; i2 < array[row].length; i2 ++)
			total += array[row][i2];
		return total;
	}
	
	public static long getRowTotal(long[][] array, int row){
		long total = 0;
		for(int i2 = 0; i2 < array[row].length; i2 ++)
			total += array[row][i2];
		return total;
	}
	
	
	public static int getColumnTotal(int[][] array, int column){
		int total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			total += array[i1][column];
		return total;
	}
	
	public static float getColumnTotal(float[][] array, int column){
		float total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			total += array[i1][column];
		return total;
	}
	
	public static double getColumnTotal(double[][] array, int column){
		double total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			total += array[i1][column];;
		return total;
	}
	
	public static long getColumnTotal(long[][] array, int column){
		long total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			total += array[i1][column];
		return total;
	}
	
	
	public static int getTotal(int[][] array){
		int total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			for(int i2 = 0; i2 < array[i1].length; i2 ++)
				total += array[i1][i2];
		return total;
	}
	
	public static float getTotal(float[][] array){
		float total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			for(int i2 = 0; i2 < array[i1].length; i2 ++)
				total += array[i1][i2];
		return total;
	}
	
	public static double getTotal(double[][] array){
		double total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			for(int i2 = 0; i2 < array[i1].length; i2 ++)
				total += array[i1][i2];
		return total;
	}
	
	public static long getTotal(long[][] array){
		long total = 0;
		for(int i1 = 0; i1 < array.length; i1 ++)
			for(int i2 = 0; i2 < array[i1].length; i2 ++)
				total += array[i1][i2];
		return total;
	}
	
	
	public static int getAverage(int[][] array){
		return (int)(getTotal(array) * 1.0/array.length);
	}
	
	public static float getAverage(float[][] array){
		return getTotal(array)/array.length;
	}
	
	public static double getAverage(double[][] array){
		return getTotal(array)/array.length;
	}
	
	public static long getAverage(long[][] array){
		return getTotal(array)/array.length;
	}
	
	
	public static int getHighestInRow(int[][] array, int row){
		int max = -1 * Integer.MAX_VALUE;
		for(int index = 0; index < array[row].length; index ++){
			if(max < array[row][index]){
				max = array[row][index];
			}
		}
		return max;
	}
	
	public static float getHighestInRow(float[][] array, int row){
		float max = -1 * Float.MAX_VALUE;
		for(int index = 0; index < array[row].length; index ++){
			if(max < array[row][index]){
				max = array[row][index];
			}
		}
		return max;
	}
	
	public static double getHighestInRow(double[][] array, int row){
		double max = Double.NEGATIVE_INFINITY;
		for(int index = 0; index < array[row].length; index ++){
			if(max < array[row][index]){
				max = array[row][index];
			}
		}
		return max;
	}
	
	public static long getHighestInRow(long[][] array, int row){
		long max = -1 * Long.MAX_VALUE;
		for(int index = 0; index < array[row].length; index ++){
			if(max < array[row][index]){
				max = array[row][index];
			}
		}
		return max;
	}
	
	
	public static int getLowestInRow(int[][] array, int row){
		int min = Integer.MAX_VALUE;
		for(int index = 0; index < array[row].length; index ++){
			if(min > array[row][index]){
				min = array[row][index];
			}
		}
		return min;
	}
	
	public static float getLowestInRow(float[][] array, int row){
		float min = Float.MAX_VALUE;
		for(int index = 0; index < array[row].length; index ++){
			if(min > array[row][index]){
				min = array[row][index];
			}
		}
		return min;
	}
	
	public static double getLowestInRow(double[][] array, int row){
		double min = Double.POSITIVE_INFINITY;
		for(int index = 0; index < array[row].length; index ++){
			if(min > array[row][index]){
				min = array[row][index];
			}
		}
		return min;
	}
	
	public static long getLowestInRow(long[][] array, int row){
		long min = Long.MAX_VALUE;
		for(int index = 0; index < array[row].length; index ++){
			if(min > array[row][index]){
				min = array[row][index];
			}
		}
		return min;
	}
}
