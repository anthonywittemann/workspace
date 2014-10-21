import java.util.Scanner;

//Anthony Wittemann
//9/3/14
//Lab 2 binary search, brute force


public class Lab2 {
	
	private int[] array; //is ordered in ascending order
	
	
	public Lab2(int arraySize){
		array = new int[arraySize];
		for(int i = 0; i < arraySize; i++){
			array[i] = i;
		}
	}
	
	/**
	 * goes through the array one by one to find the index of the int
	 * @param unknownInt the number to be found in the array
	 * @return the index of unknownInt, -1 if not found in array
	 */
	public int bruteSearch(int unknownInt){
		for(int i = 0; i < array.length; i++){
			if(unknownInt == array[i]){
				System.out.println("Brute Search number found: " + unknownInt + "   index of number: " + i + "   iterations: " + i);
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * recursively implements binary search to find int
	 * @param unknownInt the number to be found in the array
	 * @param low the lower index
	 * @param high the upper index
	 * @return the index of unknownInt, -1 if not found in array
	 */
	public int binarySearch(int unknownInt, int low, int high, int iterationCount){
		int midIndex = (low + high)/2;
		//base case
		if(unknownInt == array[midIndex]){
			iterationCount ++;
			System.out.println("Binary Search number found: " + unknownInt + "   index of number: " + midIndex + "   iterations: " + iterationCount);
			return midIndex;
		}
		//recursive case
		else if(unknownInt > array[midIndex]){
			iterationCount ++;
			return binarySearch(unknownInt, midIndex, high, iterationCount);
		}
		//recursive case
		else if(unknownInt < array[midIndex]){
			iterationCount ++;
			return binarySearch(unknownInt, low, midIndex, iterationCount);
		}
		//base case
		else if(midIndex == low || midIndex == high){
			return -1;
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		System.out.print("Enter the size of the array (preferrably from 20-20000) ");
		Scanner scan = new Scanner(System.in);
		int arraySize = scan.nextInt();
		
		Lab2 test = new Lab2(arraySize);
		int index = -1;
		long bruteTime = 0;
		long binaryTime = 0;
		long time1 = 0;
		long time2 = 0;
		time1 = System.nanoTime();
//		for(int i = 0; i < 100; i++){
//			index = test.bruteSearch((int) (Math.random() * arraySize));
//		}
		test.bruteSearch((int) (Math.random() * arraySize));
		time2 = System.nanoTime();
		bruteTime = time2 - time1;
		System.out.println("Time searching using brute force: " + bruteTime + " nanoSeconds");
		
		System.out.println();
		
		time1 = System.nanoTime();
//		for(int i = 0; i < 100; i++){
//			index = test.binarySearch((int) (Math.random() * arraySize), 0, arraySize, 0);
//		}
		test.binarySearch((int) (Math.random() * arraySize), 0, arraySize, 0);
		time2 = System.nanoTime();
		System.out.println("Time searching using binary search: " + binaryTime + " nanoSeconds");
	}

}
