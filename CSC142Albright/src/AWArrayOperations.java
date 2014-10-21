/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 10 - Array Operation
 * HW Due 4/01/14
 */
public class AWArrayOperations {
//	 Demonstrate the class in a complete program with test data stored in arrays of various 
//	 data types. 
	
	public static int getTotal(int[] array){
		int total = 0;
		for(int index = 0; index < array.length; index ++){
			total += array[index];
		}
		return total;
	}
	
	public static float getTotal(float[] array){
		float total = 0;
		for(int index = 0; index < array.length; index ++){
			total += array[index];
		}
		return total;
	}
	
	public static double getTotal(double[] array){
		double total = 0;
		for(int index = 0; index < array.length; index ++){
			total += array[index];
		}
		return total;
	}
	
	public static long getTotal(long[] array){
		long total = 0;
		for(int index = 0; index < array.length; index ++){
			total += array[index];
		}
		return total;
	}
	
	
	public static int getAverage(int[] array){
		return (int)(getTotal(array) * 1.0/array.length);
	}
	
	public static float getAverage(float[] array){
		return getTotal(array)/array.length;
	}
	
	public static double getAverage(double[] array){
		return getTotal(array)/array.length;
	}
	
	public static long getAverage(long[] array){
		return getTotal(array)/array.length;
	}
	
	
	public static int getHighest(int[] array){
		int max = -1 * Integer.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(max < array[index]){
				max = array[index];
			}
		}
		return max;
	}
	
	public static float getHighest(float[] array){
		float max = -1 * Float.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(max < array[index]){
				max = array[index];
			}
		}
		return max;
	}
	
	public static double getHighest(double[] array){
		double max = Double.NEGATIVE_INFINITY;
		for(int index = 0; index < array.length; index ++){
			if(max < array[index]){
				max = array[index];
			}
		}
		return max;
	}
	
	public static long getHighest(long[] array){
		long max = -1 * Long.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(max < array[index]){
				max = array[index];
			}
		}
		return max;
	}
	
	
	public static int getLowest(int[] array){
		int min = Integer.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(min > array[index]){
				min = array[index];
			}
		}
		return min;
	}
	
	public static float getLowest(float[] array){
		float min = Float.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(min > array[index]){
				min = array[index];
			}
		}
		return min;
	}
	
	public static double getLowest(double[] array){
		double min = Double.POSITIVE_INFINITY;
		for(int index = 0; index < array.length; index ++){
			if(min > array[index]){
				min = array[index];
			}
		}
		return min;
	}
	
	public static long getLowest(long[] array){
		long min = Long.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(min > array[index]){
				min = array[index];
			}
		}
		return min;
	}
}
