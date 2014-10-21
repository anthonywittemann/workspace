import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 11 - Number Analysis
 * HW Due 4/3/14
 */
public class AWNumberAnalysis {
	private float[] fArray;
	
	public AWNumberAnalysis(File file){
		Scanner scanner;
		Scanner scanner2;
		try {
			scanner = new Scanner(file);
			int len = 0;
			while(scanner.hasNextFloat()){
				len++;
				scanner.nextFloat();
			}
			scanner.close();
			fArray = new float[len];
			scanner2 = new Scanner(file);
			int i = 0;
			while(scanner2.hasNextFloat()){
			   fArray[i++] = scanner2.nextFloat();
			}
			scanner2.close();
			System.out.println("Got here");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		displayData();
	}
	
	private void displayData() {
		System.out.println("Highest element in file: " + getHighest(fArray));
		System.out.println("Lowest element in file: " + getLowest(fArray));
		System.out.println("Total of elements in file: " + getTotal(fArray));
		System.out.println("Average of elements in file: " + getAverage(fArray));
	}

	public float getTotal(float[] array){
		float total = 0;
		for(int index = 0; index < array.length; index ++){
			total += array[index];
		}
		return total;
	}
	
	public float getAverage(float[] array){
		return (float)(getTotal(array) * 1.0/array.length);
	}
	
	public float getHighest(float[] array){
		float max = -1 * Float.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(max < array[index]){
				max = array[index];
			}
		}
		return max;
	}
	
	public float getLowest(float[] array){
		float min = Float.MAX_VALUE;
		for(int index = 0; index < array.length; index ++){
			if(min > array[index]){
				min = array[index];
			}
		}
		return min;
	}

}
