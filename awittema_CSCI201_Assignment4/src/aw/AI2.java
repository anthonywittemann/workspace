package aw;

import java.util.ArrayList;

public class AI2 extends ParentAI{
	//WORKS!!!!!!!!!!!!!!!!!!!
	
	private ArrayList<String> previousLocations; //holds previous locations in form 1A, read by using charAt
	
	public AI2(Car c){
		super(c);
		previousLocations = new ArrayList<String>();
	}

	@Override
	public void move() {
		// The car holds a list of locations of where it has been.
		// It will move a random direction to a space that it hasn't been to yet.
		
		findInvalidMovesBasedOnPreviousLocations();
		
		// If there is not any valid move, the car wipes the list clean and starts over.
		if(!super.canMoveEast && !super.canMoveNorth && !super.canMoveSouth && !super.canMoveWest){
			//wipe memory of previous locations clean
			while(previousLocations.size() > 0){
				previousLocations.remove(previousLocations.size() - 1);
			}
//			System.out.println("DEBUG: RESET LIST");
			generateNextRandomMove(super.canMoveNorth, super.canMoveEast, super.canMoveSouth, super.canMoveWest);
		}
		else if(super.canMoveEast && super.canMoveNorth && super.canMoveSouth && super.canMoveWest){ //all good
			generateNextRandomMove(true, true, true, true);
		}
		
		else if(super.canMoveNorth && super.canMoveEast && super.canMoveSouth && !super.canMoveWest){ //all good but W
			generateNextRandomMove(true, true, true, false);
		}
		else if(super.canMoveNorth && super.canMoveEast && !super.canMoveSouth && super.canMoveWest){ //all good but S
			generateNextRandomMove(true, true, false, true);
		}
		else if(super.canMoveNorth && !super.canMoveEast && super.canMoveSouth && super.canMoveWest){ //all good but E
			generateNextRandomMove(true, false, true, true);
		}
		else if(!super.canMoveNorth && super.canMoveEast && super.canMoveSouth && super.canMoveWest){ //all good but N
			generateNextRandomMove(false, true, true, false);
		}
		
		else if(super.canMoveNorth && super.canMoveEast && !super.canMoveSouth && !super.canMoveWest){ //all good but S,W
			generateNextRandomMove(true, true, false, false);
		}
		else if(super.canMoveNorth && !super.canMoveEast && super.canMoveSouth && !super.canMoveWest){ //all good but E,W
			generateNextRandomMove(true, false, true, false);
		}
		else if(super.canMoveNorth && !super.canMoveEast && !super.canMoveSouth && super.canMoveWest){ //all good but S,E
			generateNextRandomMove(true, false, false, true);
		}
		else if(!super.canMoveNorth && super.canMoveEast && super.canMoveSouth && !super.canMoveWest){ //all good but N, W
			generateNextRandomMove(false, true, true, false);
		}
		else if(!super.canMoveNorth && !super.canMoveEast && super.canMoveSouth && super.canMoveWest){ //all good but N, E
			generateNextRandomMove(false, false, true, true);
		}
		else if(!super.canMoveNorth && super.canMoveEast && !super.canMoveSouth && super.canMoveWest){ //all good but N, S
			generateNextRandomMove(false, true, false, true);
		}
		
		else if(!super.canMoveNorth && !super.canMoveEast && !super.canMoveSouth && super.canMoveWest){ //only W
			generateNextRandomMove(false, false, false, true);
		}
		else if(!super.canMoveNorth && !super.canMoveEast && super.canMoveSouth && !super.canMoveWest){ //only S
			generateNextRandomMove(false, false, true, false);
		}
		else if(!super.canMoveNorth && super.canMoveEast && !super.canMoveSouth && !super.canMoveWest){ //only E
			generateNextRandomMove(false, true, false, false);
		}
		else if(super.canMoveNorth && !super.canMoveEast && !super.canMoveSouth && !super.canMoveWest){ //only N
			generateNextRandomMove(true, false, false, false);
		}
		
	}
	
	private void generateNextRandomMove(boolean yesN, boolean yesE, boolean yesS, boolean yesW){
		double randomDecimal = Math.random(); //generate random decimal used to divide up in equal parts to determine which move
		
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
	
	//override these method in order to add the previous moves to the list of previous moves
	//add last location to list of previous locations
	public void moveNorth(){
		super.moveNorth();
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
	}
	
	public void moveEast(){
		super.moveEast();
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
	}
	
	public void moveSouth(){
		super.moveSouth();
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
	}
	
	public void moveWest(){
		super.moveWest();
		int cX = super.currentX;
		char cY = super.currentY;
		previousLocations.add(new String(String.valueOf(cX) + cY));
	}
	
	//finds out where invalid move directions are based on the list of previous moves
	//parse list and seeing if any matches found between 4 possible moves 
	//and then setting canMove booleans from superclass to false
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
		
		//loop through previous locations to look for matches
		for(String s: previousLocations){
//			System.out.print("  " + s.charAt(0) + s.charAt(1));
			if(s.equalsIgnoreCase(northTile)){
				super.canMoveNorth = false;
			}
			else if(s.equalsIgnoreCase(eastTile)){
				super.canMoveEast = false;
			}
			else if(s.equalsIgnoreCase(southTile)){
				super.canMoveSouth = false;
			}
			else if(s.equalsIgnoreCase(westTile)){
				super.canMoveWest = false;
			}
		}
//		System.out.println();
	}

}
