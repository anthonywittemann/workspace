//Anthony Wittemann
//CSCI 201 Due 9/7/14

package com.USCInc;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Assignment1 {
	
	//array representing the grid of letters
	private static char[][] lettersGrid;
	
	//populates the 2D array of letters
	public static void populateGrid(int rows, int columns, char[] letters){
		int letter = 0;
		lettersGrid = new char[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				lettersGrid[i][j] = letters[letter];
				//System.out.println("Row: " + i + "   Column: " + j + "   Letter: " + lettersGrid[i][j]);
				letter++;
			}
		}
	}

	
	//returns the result from the grid and outputs the steps if admin
	public static String getResult(boolean isAdmin){
		char[] resultArray = new char[lettersGrid[0].length];
		short column = 0;
		short row = 0;
		boolean increasingRow = true;
		while(column < lettersGrid[0].length){
			resultArray[column] = lettersGrid[row][column];
			//print out the result, step by step if Admin
			if(isAdmin){
				System.out.println("Result Row: " + row + "   Result Column: " + column + "   Result Letter: " + resultArray[column]);
			}
			
			//move up or down the grid
			if(row == 0 && increasingRow == false){ //bumped into the top, then go down
				row++;
				increasingRow = true;
				//System.out.println("DEBUG: Bump top");
			}
			else if(row == lettersGrid.length - 1 && row > 0){ //bumped into the bottom, then go up
				increasingRow = false;
				row--;
				//System.out.println("DEBUG: Bump bottom");
			}
			else if(increasingRow == true && row < lettersGrid.length - 1){
				row++;
				//System.out.println("DEBUG: Row up");
			}
			else if(increasingRow == false && row > 0){
				row--;
				//System.out.println("DEBUG: Row down");
			}
			else{
				//System.out.println("DEBUG: stay in current row");
			}
			
			column ++;
			//check to see if I've reached the right wall of the grid
			if(column == lettersGrid[row].length){
				break;
			}
		}
			
		
		String result = new String(resultArray);
		return result;
	}
	
	
	public static void outputAdmin(){
		
		// print out the grid
		System.out.println("Here's the grid, Administrator: ");
		for(int i = 0; i < lettersGrid.length; i++){
			for(int j = 0; j < lettersGrid[i].length; j++){
				System.out.print(lettersGrid[i][j] + " ");
			}
			System.out.println();
		}
		
		
		//print out the final result
		System.out.println("Result: " + getResult(true));
		System.out.println("Have a nice day, most benelovent Administrator");
	}
	
	
	public static void outputGuest(){
		System.out.println("Result: " + getResult(false));
	}
	
	
	public static void main(String[] args) {
		boolean isAdmin = false;
		Scanner scan = new Scanner(System.in);
		
		//Intro and Admin
		System.out.println("Welcome to the Maze Game!");
		System.out.print("Are you an Admin?");
		String adminInput = scan.next();
		if(adminInput.charAt(0) == 'A'){
			isAdmin = true;
		}
		
		
		//Get the number of rows
		short rows = -1;
		while(rows < 1){
			try{
				System.out.print("How many rows are in the grid?");
				rows = scan.nextShort();
				if(rows < 1){
					throw new Exception();
				}
			}
			catch(Exception e){
				System.out.println("Invalid input, please enter an integer > 0.");
			}
		}
		
		
		//Get the number of columns
		short columns = -1;
		while(columns < 1){
			try{
				System.out.print("How many columns are in the grid?");
				columns = scan.nextShort();
				if(columns < 1){
					throw new Exception();
				}
			}
			catch(Exception e){
				System.out.println("Invalid input, please enter an integer > 0.");
			}
		}
		
		scan = new Scanner(System.in);
		//Get all the letters for the grid and make sure the input is valid
		boolean isValid = false;
		short index = 0;
		String[] lettersArray = new String[rows * columns];
		while(isValid == false){
			try{
				//use StringTokenizer to break up letters
				System.out.println("Please enter " + columns * rows + " letters seperated by spaces ");
				String line = scan.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				
				//put the letters into an array
				while(st.hasMoreTokens()){
					lettersArray[index] = st.nextToken();
					index++;
				}
				if(index < rows * columns){
					line = null;
					throw new Exception("Not enough letters entered");
				}
				
				
				if(index > rows * columns){
					line = null;
					throw new Exception("Too many letters enetered");
				}
				
				//check if the format is letter space letter space etc.
				for(int i = 0; i < lettersArray.length; i++){
					if(lettersArray[i].length() != 1){
						line = null;
						throw new Exception("More than one character without space");
					}
				}
				isValid = true;
			}
			catch(Exception e){
				System.out.println("Your input was invalid. " + e.getMessage());
				//reset the lettersArray values to null
				index = 0;
				for(int i = 0; i < lettersArray.length; i++){
					lettersArray[i] = null;
				}
			}
		}
			
		
		//convert string array to char array and populate the grid
		char[] letters = new char[lettersArray.length];
		for(int j = 0; j < lettersArray.length; j++){
			letters[j] = lettersArray[j].charAt(0);
		}
		
		populateGrid(rows, columns, letters);
		
		
		if(isAdmin){
			outputAdmin();
		}
		else{
			outputGuest();
		}
		
		
		scan.close();
	}
	
}

