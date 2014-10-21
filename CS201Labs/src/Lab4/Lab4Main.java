package Lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Anthony Wittemann
// 9/17/14

public class Lab4Main {
	//X goes first
	
	private static final String OUTPUT_FILE = "xoxOutput.txt";
	
	public static String getLine4(char[][] outputGrid){
		//check to see if top row is all Xs
		if(outputGrid[0][0] == 'X' && outputGrid[0][1] == 'X' && outputGrid[0][2] == 'X'){
			return "Player 1";
		}
		
		//check to see if middle row is all Xs
		if(outputGrid[1][0] == 'X' && outputGrid[1][1] == 'X' && outputGrid[1][2] == 'X'){
			return "Player 1";
		}
		
		//check to see if bottom row is all Xs
		if(outputGrid[2][0] == 'X' && outputGrid[2][1] == 'X' && outputGrid[2][2] == 'X'){
			return "Player 1";
		}
		
		//check to see if left column is all Xs
		if(outputGrid[0][0] == 'X' && outputGrid[1][0] == 'X' && outputGrid[2][0] == 'X'){
			return "Player 1";
		}
		
		//check to see if middle column is all Xs
		if(outputGrid[0][1] == 'X' && outputGrid[1][1] == 'X' && outputGrid[2][1] == 'X'){
			return "Player 1";
		}
		
		//check to see if right column is all Xs
		if(outputGrid[0][2] == 'X' && outputGrid[1][2] == 'X' && outputGrid[2][2] == 'X'){
			return "Player 1";
		}
		
		//check if l-->r, t-->b diagonal is all Xs
		if(outputGrid[0][0] == 'X' && outputGrid[1][1] == 'X' && outputGrid[2][2] == 'X'){
			return "Player 1";
		}
		
		//check if l-->r, b-->t diagonal is all Xs
		if(outputGrid[0][2] == 'X' && outputGrid[1][1] == 'X' && outputGrid[2][0] == 'X'){
			return "Player 1";
		}
		
		
		
		//check to see if top row is all Os
		if(outputGrid[0][0] == 'O' && outputGrid[0][1] == 'O' && outputGrid[0][2] == 'O'){
			return "Player 2";
		}
				
		//check to see if middle row is all Os
		if(outputGrid[1][0] == 'O' && outputGrid[1][1] == 'O' && outputGrid[1][2] == 'O'){
			return "Player 2";
		}
				
		//check to see if bottom row is all Os
		if(outputGrid[2][0] == 'O' && outputGrid[2][1] == 'O' && outputGrid[2][2] == 'O'){
			return "Player 2";
		}
				
		//check to see if left column is all Os
		if(outputGrid[0][0] == 'O' && outputGrid[1][0] == 'O' && outputGrid[2][0] == 'O'){
			return "Player 2";
		}
				
		//check to see if middle column is all Os
		if(outputGrid[0][1] == 'O' && outputGrid[1][1] == 'O' && outputGrid[2][1] == 'O'){
			return "Player 2";
		}
				
		//check to see if right column is all Os
		if(outputGrid[0][2] == 'O' && outputGrid[1][2] == 'O' && outputGrid[2][2] == 'O'){
			return "Player 2";
		}
				
		//check if l-->r, t-->b diagonal is all Os
		if(outputGrid[0][0] == 'O' && outputGrid[1][1] == 'O' && outputGrid[2][2] == 'O'){
			return "Player 2";
		}
				
		//check if l-->r, b-->t diagonal is all Os
		if(outputGrid[0][2] == 'O' && outputGrid[1][1] == 'O' && outputGrid[2][0] == 'O'){
			return "Player 2";
		}
		
		return "neither";
	}

	
	public static void main(String[] args) {
		boolean isValidFilename = false;
		char[][] xoInput = new char[10][4]; //I allow for only 10 inputs and no more than 4 characters/line input 
		
		
		while(!isValidFilename){
			Scanner scan = new Scanner(System.in);
			System.out.print("Please enter the filename: ");
			String filename = scan.nextLine();
			
			try{
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				isValidFilename = true;
				//read in from the text file, line by line, character by character
				String line = null;
				int lineNumber = 0;

				//loop line by line through file, read in line as a string
				while ((line = reader.readLine()) != null && lineNumber <= 10) { 
					//convert line to char array
					xoInput[lineNumber] = line.toCharArray();
					
					lineNumber++;
				}
				reader.close();
				for(int i = 0; i < 10; i++){
					System.out.println("DEBUG: xoInput: " + String.valueOf(xoInput[i]));
				}
				System.out.println();
			}catch(FileNotFoundException fnfe){
				//write invalid output to file
				System.out.println("DEBUG: FILE NOT FOUND");
				try{
					File xoxOutput = new File(OUTPUT_FILE);
					xoxOutput.createNewFile();
					FileWriter writer = new FileWriter(xoxOutput);
					writer.write("Input DNE" + "\n");
					writer.flush();
					writer.close();
				}catch(IOException ioe){
					System.out.println("DEBUG: CANNOT WRITE TO OUTPUT FILE");
				}
				
			}catch(IOException ioe){
				System.out.println("DEBUG: IO Exception");
			}
		}
			
		//create a blank output tic tac toe grid 
		char[][] outputGrid = new char[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				outputGrid[i][j] = '_';
			}
		}
		
		//TODO find out if I need to account for multiple errors at once ie out of bounds and repeated moves
		String line4 = null; //used for winner, repeated move or out of bounds
		
		//TODO fix this section ArrayIndexOutofBounds
		//map the current array of characters from input to a 3x3 output grid of X, O and _
		for(int i = 0; i < xoInput.length; i++){
			int xCor = Character.getNumericValue(xoInput[i][0]); //assuming the 1st character is a number
			int yCor = Character.getNumericValue(xoInput[i][3]); //assuming the 4th character is a number
			System.out.print("DEBUG: XCor: " + xCor + "    YCor: " + yCor + "\n");
			
			if(xCor > 2 || yCor > 2){ //check to see if the input is out of bounds
				line4 = "Out of bounds";
				System.out.println("DEBUG: Out of bounds, i=" + i);
			}
			else{
				if(xCor == -1 || yCor == -1){
					//no more input
				}
				else{
					if(outputGrid[xCor][yCor] != '_'){ //check for repeat moves
						line4 = "Repeated move";
						System.out.println("DEBUG: Repeated move, i=" + i);
					}
					else{
						if(i%2 == 0){ //even then X
							outputGrid[xCor][yCor] = 'X';
						}
						else{//odd then O
							outputGrid[xCor][yCor] = 'O';
						}
					}
				}
			}
			
			
		}
		
		//convert outputGrid into Strings
		String line1 = new String(outputGrid[0]);
		String line2 = new String(outputGrid[1]);
		String line3 = new String(outputGrid[2]);
		
		//TODO figure out who won 
		if(line4 == null){
			line4 = getLine4(outputGrid);
		}
		
		//write the output to the file
		try{
			File xoxOutput = new File(OUTPUT_FILE);
			xoxOutput.createNewFile();
			FileWriter writer = new FileWriter(xoxOutput);
			writer.write(line1 +"\n"+ line2 +"\n"+ line3 +"\n"+ line4);
			writer.flush();
			writer.close();
		}catch(IOException ioe){
			System.out.println("DEBUG: CANNOT WRITE TO OUTPUT FILE");
		}
	}

}
