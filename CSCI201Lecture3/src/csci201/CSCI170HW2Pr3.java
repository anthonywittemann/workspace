package csci201;

import java.util.ArrayList;
//Anthony Wittemann

public class CSCI170HW2Pr3 {

	public static void main(String[] args) {
		//n is the number of coordinates in the array,
		//t depends on the number of comparisons to see whether a unique elements has been added and 
		
		//O(n) = 2*(n^2+n)+1 (n^2 from the while loop within the for loop, +n from finding the largest in the tally)
		//theta(n) = n^2
		
		//Assuming coordinate inputs are only integers
	}
	
	public int getMaxPointsOnLine(int[][] coordinates){
		//split up coordinates into x and y values
		int[] xCoors = new int[coordinates.length];
		int[] yCoors = new int[coordinates.length];
		for(int i = 0; i < coordinates.length; i++){
			xCoors[i] = coordinates[i][0];
			yCoors[i] = coordinates[i][1];
		}
		
		//get the maximum number of points on the horizontal and vertical lines
		int mpoh = maxPointsOnHorizontal(xCoors);
		int mpov = maxPointsOnVertical(yCoors);
		
		//return the larger of the 2 (1 comparison)
		if(mpoh > mpov){
			return mpoh;
		}
		else{
			return mpov;
		}
	}
	
	public int maxPointsOnHorizontal(int[] horizontalCoordinates){
		int maxPoints = 0;
		//arrayList that holds the unique xValues
		ArrayList<Integer> xValues = new ArrayList<Integer>();
		//arrayList that holds a tally of the unique xValues
		ArrayList<Integer> xTally = new ArrayList<Integer>();
		
		
		xValues.add(horizontalCoordinates[0]);
		xTally.add(1);
		//loop through the array of coordinates to make an ArrayList of unique xValues
		//and tallying the number of times a certain value repeats
		for(int i = 1; i < horizontalCoordinates.length; i++){ //takes n time
			boolean alreadyAdded = false;
			int j = 0;
			while(!alreadyAdded && j < xValues.size()){ //takes n time
				if(xValues.get(j) == horizontalCoordinates[i]){
					alreadyAdded = true;
					//increment the tally
					xTally.add(j, xTally.get(j) + 1);
				}
				j++;
			}
			if(!alreadyAdded){
				xValues.add(horizontalCoordinates[i]);
				xTally.add(1);
			}
		}
		
		//find the greatest tally
		for(int i = 0; i < xTally.size(); i++){ //takes n time
			if(maxPoints < xTally.get(i)){
				maxPoints = xTally.get(i);
			}
		}
		
		
		return maxPoints;
	}
	
	//repeat process for y coordinates
	public int maxPointsOnVertical(int[] verticalCoordinates){
		int maxPoints = 0;
		ArrayList<Integer> yTally = new ArrayList<Integer>();
		
		return maxPoints;
	}

}
