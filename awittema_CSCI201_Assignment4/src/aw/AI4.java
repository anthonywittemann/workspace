package aw;

import java.util.ArrayList;
import java.util.List;

public class AI4 extends ParentAI{
	
	private ArrayList<String> previousLocations; //holds previous locations in form 1A, read by using charAt
	private boolean hasTraveledN;
	private boolean hasTraveledE;
	private boolean hasTraveledS;
	private boolean hasTraveledW;
	
	public AI4(Car c){
		super(c);
		previousLocations = new ArrayList<String>();
		hasTraveledN = false;
		hasTraveledE = false;
		hasTraveledS = false;
		hasTraveledW = false;
	}

	@Override
	public void move() {
		// TODO FUCK THIS AI!!!!!!
		// This car attempts to go in the direction it hasn't traveled in the longest.
		// At the start, the car may pick random directions out of those it has never traveled.
		// If it cannot move the direction that it hasn't traveled in the longest, it picks the second
		// longest, and so on.
		findInvalidMovesBasedOnPreviousLocations();
		
	}
	
	
	//override these method in order to add the previous moves to the list of previous moves
	//add last location to list of previous locations
	public void moveNorth(){
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
		super.moveNorth();
	}
	
	public void moveEast(){
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
		super.moveEast();
	}
	
	public void moveSouth(){
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
		super.moveSouth();
	}
	
	public void moveWest(){
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
		super.moveWest();
	}
	
	
	//finds out where the path traversed longest ago is based on the list of previous moves
	//parse list and seeing if any matches found between 4 possible moves 
	//from here call move methods
	private void findInvalidMovesBasedOnPreviousLocations(){
		//get the tiles immediately N, S, E, W
		int cX = super.currentX;
		char cY = super.currentY;
		String northTile, eastTile, southTile, westTile;
		
		eastTile = new String(String.valueOf(cX + 1) + cY);
		westTile = new String(String.valueOf(cX - 1) + cY);
		
		char nYL = 'A';
		if(currentY == 'I'){
			nYL = 'H';
		}
		else if(currentY == 'H'){
			nYL = 'G';
		}
		else if(currentY == 'G'){
			nYL = 'F';
		}
		else if(currentY == 'F'){
			nYL = 'E';
		}
		else if(currentY == 'E'){
			nYL = 'D';
		}
		else if(currentY == 'D'){
			nYL = 'C';
		}
		else if(currentY == 'C'){
			nYL = 'B';
		}
		northTile = new String(String.valueOf(cX) + nYL);
		
		char nY = 'I';
		if(currentY == 'A'){
			nY = 'B';
		}
		else if(currentY == 'B'){
			nY = 'C';
		}
		else if(currentY == 'C'){
			nY = 'D';
		}
		else if(currentY == 'D'){
			nY = 'E';
		}
		else if(currentY == 'E'){
			nY = 'F';
		}
		else if(currentY == 'F'){
			nY = 'G';
		}
		else if(currentY == 'G'){
			nY = 'H';
		}
		southTile = new String(String.valueOf(cX) + nY);
		
		ArrayList<Short> previousPaths = new ArrayList<Short>(); // can be 1: N, 2: E, 3: S, 4: W
		
		//loop through previous locations to find matches with adjacent locations
		for(String s: previousLocations){
			if(s.equalsIgnoreCase(northTile)){
				previousPaths.add((short) 1);
				hasTraveledN = true;
			}
			else if(s.equalsIgnoreCase(eastTile)){
				previousPaths.add((short) 2);
				hasTraveledE = true;
			}
			else if(s.equalsIgnoreCase(southTile)){
				previousPaths.add((short) 3);
				hasTraveledS = true;
			}
			else if(s.equalsIgnoreCase(westTile)){
				previousPaths.add((short) 4);
				hasTraveledW = true;
			}
		}
		
		//perhaps employ recursion
		//move on the least recent path
//		System.out.println("Finding invalid paths...");
		makeMove(super.canMoveNorth, hasTraveledN, super.canMoveEast, hasTraveledE, super.canMoveSouth, hasTraveledS, super.canMoveWest, hasTraveledW, previousPaths);
	}
	
	public void makeMove(boolean cN, boolean hN, boolean cE, boolean hE, boolean cS, boolean hS, boolean cW, boolean hW, List<Short> previousPaths){
//		System.out.println("Making Moves...");
		if(previousPaths.size() > 0){
			short bestDirection = previousPaths.get(0);
//			System.out.println("Best Path: " + bestDirection);
			
			if(hN && hE && hS && hW){ //if traveled all 4 directions
				
				if(bestDirection == 1){ 
					if(cN){ //base case, move N
						System.out.println("Moving North...");
						moveNorth();
					}
					else{ //recursive case, try again without 1st path
						System.out.println("DEBUG: recursive");
						makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
					}
				}
				
				else if(bestDirection == 2){ 
					if(cE){ //base case, move E
						System.out.println("Moving East...");
						moveEast();
					}
					else{ //recursive case, try again without 1st path
						System.out.println("DEBUG: recursive");
						makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
					}
				}
				
				else if(bestDirection == 3){ 
					if(cS){ //base case, move S
						moveSouth();
						System.out.println("Moving South...");
					}
					else{ //recursive case, try again without 1st path
						System.out.println("DEBUG: recursive");
						makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
					}
				}
				
				else if(bestDirection == 4){ 
					if(cW){ //base case, move W
						System.out.println("Moving West...");
						moveWest();
					}
					else{ //recursive case, try again without 1st path
						System.out.println("DEBUG: recursive");
						makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
					}
				}	
			}
			
			
			else if(hN && hE && hS && !hW){ //if all but W
				if(cW){ //base case, can travel west
					System.out.println("Moving West...");
					moveWest();
				}
				else{
					if(bestDirection == 1){ 
						if(cN){ //base case, move N
							System.out.println("Moving North...");
							moveNorth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 2){ 
						if(cE){ //base case, move E
							System.out.println("Moving East...");
							moveEast();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 3){ 
						if(cS){ //base case, move S
							System.out.println("Moving South...");
							moveSouth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			
			else if(hN && hE && !hS && hW){ //if all but S
				if(cS){ //base case, can travel south
					System.out.println("Moving South...");
					moveSouth();
				}
				else{
					if(bestDirection == 1){ 
						if(cN){ //base case, move N
							System.out.println("Moving North...");
							moveNorth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 2){ 
						if(cE){ //base case, move E
							System.out.println("Moving East...");
							moveEast();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 4){ 
						if(cW){ //base case, move W
							System.out.println("Moving West...");
							moveWest();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			
			
			else if(hN && !hE && hS && hW){ //if all but E
				if(cE){ //base case, can travel east
					System.out.println("Moving East...");
					moveEast();
				}
				else{
					if(bestDirection == 1){ 
						if(cN){ //base case, move N
							System.out.println("Moving North...");
							moveNorth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 4){ 
						if(cW){ //base case, move W
							System.out.println("Moving West...");
							moveWest();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 3){ 
						if(cS){ //base case, move S
							System.out.println("Moving South...");
							moveSouth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			
			else if(!hN && hE && hS && hW){ //if all but N
				if(cN){ //base case, can travel north
					System.out.println("Moving North...");
					moveNorth();
				}
				else{
					if(bestDirection == 4){ 
						if(cW){ //base case, move W
							System.out.println("Moving West...");
							moveWest();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 2){ 
						if(cE){ //base case, move E
							System.out.println("Moving East...");
							moveEast();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 3){ 
						if(cS){ //base case, move S
							System.out.println("Moving South...");
							moveSouth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			
			else if(hN && hE && !hS && !hW){ //if all but S, W
				if(cW && cS){ //base case, can travel south, west
					if(Math.random() < .5){
						System.out.println("Moving West...");
						moveWest();
					}
					else{
						System.out.println("Moving South...");
						moveSouth();
					}
				}
				else if(cW){ //base case, move west
					System.out.println("Moving West...");
					moveWest();
				}
				else if(cS){ //base case, move south
					System.out.println("Moving South...");
					moveSouth();
				}
				else{
					if(bestDirection == 1){ 
						if(cN){ //base case, move N
							System.out.println("Moving North...");
							moveNorth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 2){ 
						if(cE){ //base case, move E
							System.out.println("Moving East...");
							moveEast();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			
			
			else if(hN && !hE && hS && !hW){ //if all but E, W
				if(cW && cE){ //base case, can travel east, west
					if(Math.random() < .5){
						System.out.println("Moving West...");
						moveWest();
					}
					else{
						System.out.println("Moving East...");
						moveEast();
					}
				}
				else if(cW){ //base case, move west
					System.out.println("Moving West...");
					moveWest();
				}
				else if(cE){ //base case, move east
					System.out.println("Moving East...");
					moveEast();
				}
				else{
					if(bestDirection == 1){ 
						if(cN){ //base case, move N
							System.out.println("Moving North...");
							moveNorth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 3){ 
						if(cS){ //base case, move S
							System.out.println("Moving South...");
							moveSouth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			
			else if(!hN && hE && hS && !hW){ //if all but N, W
				if(cW && cN){ //base case, can travel north, west
					if(Math.random() < .5){
						System.out.println("Moving West...");
						moveWest();
					}
					else{
						System.out.println("Moving North...");
						moveNorth();
					}
				}
				else if(cW){ //base case, move west
					System.out.println("Moving West...");
					moveWest();
				}
				else if(cN){ //base case, move north
					System.out.println("Moving North...");
					moveNorth();
				}
				else{
					if(bestDirection == 2){ 
						if(cE){ //base case, move E
							System.out.println("Moving East...");
							moveEast();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 3){ 
						if(cS){ //base case, move S
							System.out.println("Moving South...");
							moveSouth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			else if(!hN && !hE && hS && hW){ //if all but N, E
				if(cE && cN){ //base case, can travel north, east
					if(Math.random() < .5){
						System.out.println("Moving East...");
						moveEast();
					}
					else{
						System.out.println("Moving North...");
						moveNorth();
					}
				}
				else if(cE){ //base case, move east
					System.out.println("Moving East...");
					moveEast();
				}
				else if(cN){ //base case, move north
					System.out.println("Moving North...");
					moveNorth();
				}
				else{
					if(bestDirection == 4){ 
						if(cW){ //base case, move W
							System.out.println("Moving West...");
							moveWest();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 3){ 
						if(cS){ //base case, move S
							System.out.println("Moving South...");
							moveSouth();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
				
				
			}
			
			else if(!hN && hE && !hS && hW){ //if all but N, S
				if(cS && cN){ //base case, can travel north, south
					if(Math.random() < .5){
						System.out.println("Moving South...");
						moveSouth();
					}
					else{
						System.out.println("Moving North...");
						moveNorth();
					}
				}
				else if(cS){ //base case, move south
					System.out.println("Moving South...");
					moveSouth();
				}
				else if(cN){ //base case, move north
					System.out.println("Moving North...");
					moveNorth();
				}
				else{
					if(bestDirection == 2){ 
						if(cE){ //base case, move E
							System.out.println("Moving East...");
							moveEast();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 4){ 
						if(cW){ //base case, move W
							System.out.println("Moving West...");
							moveWest();
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
			
			else if(hN && !hE && !hS && hW){ //if all but S, E
				if(cS && cE){ //base case, can travel south, east
					if(Math.random() < .5){
						System.out.println("Moving East...");
						moveEast();
					}
					else{
						System.out.println("Moving South...");
						moveSouth();
					}
				}
				else if(cS){ //base case, move south
					System.out.println("Moving South...");
					moveSouth();
				}
				else if(cE){ //base case, move east
					System.out.println("Moving East...");
					moveEast();
				}
				else{
					if(bestDirection == 1){ 
						if(cN){ //base case, move N
							moveNorth();
							System.out.println("Moving North...");
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
					
					else if(bestDirection == 4){ 
						if(cW){ //base case, move W
							moveWest();
							System.out.println("Moving West...");
						}
						else{ //recursive case, try again without 1st path
							System.out.println("DEBUG: recursive");
							makeMove(cN, hN, cE, hE, cS, hS, cW, hW, previousPaths.subList(1, previousPaths.size() - 1));
						}
					}
				}
			}
			
		}
		
		else if(hN){ //only has moved north
			generateNextRandomMove(hN, cE, cS, cW);
		}
		
		
		else if(hE){ //only has moved east
			generateNextRandomMove(cN, hE, cS, cW);
		}
		
		
		else if(hS){ //only has moved south
			generateNextRandomMove(cN, cE, hS, cW);
		}
		
		
		else if(hW){ //only has moved west
			generateNextRandomMove(cN, cE, cS, hW);
		}
		
		
		
		else{ //base case: make a random valid move
			generateNextRandomMove(cN, cE, cS, cW);
		}
		
	}
	
	
	
	private void generateNextRandomMove(boolean yesN, boolean yesE, boolean yesS, boolean yesW){
		double randomDecimal = Math.random(); //generate random decimal used to divide up in equal parts to determine which move
		System.out.println("Generating next random move...");
		
		if(yesN && yesE && yesS && yesW){ //if all 4 directions are valid
			if(randomDecimal < .25){ //Move north
				moveNorth();
			}
			else if(randomDecimal < .5){ //Move east
				moveEast();
			}
			else if(randomDecimal < .75){ //Move South
				moveSouth();
			}
			else{ //move West
				moveWest();
			}
		}
		
		else if(yesN && yesE && yesS && !yesW){ //all except W
			if(randomDecimal < .333333333){
				moveNorth();
			}
			else if(randomDecimal < .666666666){
				moveEast();
			}
			else{
				moveSouth();
			}
		}
		
		else if(yesN && yesE && !yesS && yesW){ //all except S
			if(randomDecimal < .333333333){
				moveNorth();
			}
			else if(randomDecimal < .666666666){
				moveEast();
			}
			else{
				moveWest();
			}
		}
		
		else if(yesN && !yesE && yesS && yesW){ //all except E
			if(randomDecimal < .333333333){
				moveNorth();
			}
			else if(randomDecimal < .666666666){
				moveWest();
			}
			else{
				moveSouth();
			}
		}
		
		else if(!yesN && yesE && yesS && yesW){ //all except N
			if(randomDecimal < .333333333){
				moveWest();
			}
			else if(randomDecimal < .666666666){
				moveEast();
			}
			else{
				moveSouth();
			}
		}
		
		
		else if(yesN && yesE && !yesS && !yesW){ //yes N, E
			if(randomDecimal < .5){
				moveNorth();
			}
			else{
				moveEast();
			}
		}
		
		else if(yesN && !yesE && yesS && !yesW){ //yes N, S
			if(randomDecimal < .5){
				moveNorth();
			}
			else{
				moveSouth();
			}
		}
		
		else if(yesN && !yesE && !yesS && yesW){ //yes N, W
			if(randomDecimal < .5){
				moveNorth();
			}
			else{
				moveWest();
			}
		}
		
		else if(!yesN && yesE && yesS && !yesW){ //yes S, E
			if(randomDecimal < .5){
				moveSouth();
			}
			else{
				moveEast();
			}
		}
		
		else if(!yesN && yesE && !yesS && yesW){ //yes W, E
			if(randomDecimal < .5){
				moveWest();
			}
			else{
				moveEast();
			}
		}
		
		else if(!yesN && !yesE && yesS && yesW){ //yes S, W
			if(randomDecimal < .5){
				moveSouth();
			}
			else{
				moveWest();
			}
		}
		
		else if(yesN && !yesE && !yesS && !yesW){ //yes N
			moveNorth();
		}
		
		else if(!yesN && yesE && !yesS && !yesW){ //yes E
			moveEast();
		}
		
		else if(!yesN && !yesE && yesS && !yesW){ //yes S
			moveSouth();
		}
		
		else if(!yesN && !yesE && !yesS && yesW){ //yes W
			moveWest();
		}
		
		
	}

}
