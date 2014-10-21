package csci201;

import java.util.ArrayList;
import java.util.Scanner;

// Anthony Wittemann
// 9/15/14 Lecture 6: Generics Sorting

public class Lecture6<T> {
	
	
	public Lecture6(T arr[]){
		for(int i= 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		
		//finish sorting
		
	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("(i) Integer");
		System.out.println("(f) Float");
		System.out.println("(d) Double");
		System.out.print("What type of array would you like to sort?");
		String str = scan.nextLine();
		if(str.equalsIgnoreCase("i")){
			Integer arr[] = new Integer[10];
			for(int i = 0; i < arr.length; i++){
				arr[i] = (int) (Math.random() * 10);
			}
			Lecture6<Integer> l6 = new Lecture6<Integer>(arr);
		}
		else if(str.equalsIgnoreCase("f")){
			Float arr[] = new Float[10];
			for(int i = 0; i < arr.length; i++){
				arr[i] = (float) (Math.random() * 10);
			}
			Lecture6<Float> l6 = new Lecture6<Float>(arr);
		}
		else if(str.equalsIgnoreCase("d")){
			Double arr[] = new Double[10];
			for(int i = 0; i < arr.length; i++){
				arr[i] = (double) (Math.random() * 10);
			}
			Lecture6<Double> l6 = new Lecture6<Double>(arr);
		}
		else{
			System.out.println("You didn't enter an i, f, or d.");
		}
	}

}
