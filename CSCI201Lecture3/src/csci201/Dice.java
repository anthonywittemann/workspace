package csci201;

import java.util.Scanner;

//Anthony Wittemann
//In class 9/3/14

public class Dice {
	private int[] faceOccurences;
	private int numRolls;
	public static final int DICE_FACES = 6;
	
	public Dice(int numRolls){
		this.numRolls = numRolls;
		this.faceOccurences = new int[DICE_FACES];	
	}
	
	private void roll(){
		for(int i = 0; i < numRolls; i++){
			int roll = (int) (Math.random() * 6); //randomly generates a number from 1-6
			faceOccurences[roll]++;
		}
	}
	
	private void printStatistics(){
		for(int i = 0; i < DICE_FACES; i++){
			System.out.print("The number " + (i+1) + " occurred " + faceOccurences[i] + " times.");
			double percentage = (100 * ((double) faceOccurences[i])/(double) numRolls);
			System.out.println("(" + percentage + "%)");
		}
	}


	public static void main(String[] args) {
		System.out.println("How many rolls?");
		Scanner scan = new Scanner(System.in);
		int numRolls = scan.nextInt();
		Dice d = new Dice(numRolls);
		d.roll();
		d.printStatistics();
	}

}
