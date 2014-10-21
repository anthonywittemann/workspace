/** 
 * @author Anthony Wittemann
 * Test 2 Locate Largest Program
 * In class test 4/8/14
 */
public class AWTest2Program {

	public static int[] locateLargest(int[][] a){
		int largest = -1 * Integer.MAX_VALUE;
		int[] largestLoc = {0,0};
		for(int row = 0; row < a.length; row++){
			for(int col = 0; col < a[row].length; col++){
				if(a[row][col] > largest){
					largest = a[row][col];
					largestLoc[0] = row;
					largestLoc[1] = col;
				}
			}
		}
		return largestLoc;
	}
	
}
