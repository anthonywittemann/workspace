package aw;

public abstract class ParentAI {
	
	//needs to know current location, type of tile it's currently on, current direction
	
	protected short currentDirection; //can be 1: North, 2: East, 3: South, 4: West
	protected int currentX;
	protected char currentY;
	protected int previousX;
	protected char previousY;
	
	protected boolean canMoveNorth;
	protected boolean canMoveEast;
	protected boolean canMoveSouth;
	protected boolean canMoveWest;
	
	//can be 1: blank, 2: I, 3: L, 4: T, 5: +, 0 if tile would be off grid
	protected short currentTile; //should never be 1 or 0 b/c roadways will always be valid
	protected int tileRotation;
	
	protected Car car; //reference of car used to get,set the xLocation and yLocation when moving, get rotation
	
	
	public ParentAI(Car car){
		this.car = car;
		this.currentX = car.getXLocation();
		this.currentY = car.getYLocation();
		this.currentTile = car.getTileType();
		this.tileRotation = car.getTileRotation();
		
		findValidMoveDirections();
	}
	
	public void setTileType(short nTT){
		currentTile = nTT;
	}
	
	public void setTileRotation(int nTR){
		tileRotation = nTR;
	}
	
	//based on the current tile, check to see which moves are valid
	public void findValidMoveDirections(){
		
		if(currentTile == 2 && (tileRotation == 0 || tileRotation == 180)){ // in vertical I configuration
			canMoveNorth = true; canMoveSouth = true;
			canMoveEast = false; canMoveWest = false;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,S: T    E,W: F");
		}
		else if(currentTile == 2 && (tileRotation == 90 || tileRotation == 270)){ // in horizontal I configuration
			canMoveNorth = false; canMoveSouth = false;
			canMoveEast = true; canMoveWest = true;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,S: F    E,W: T");
		}
		
		
		else if(currentTile == 3 && tileRotation == 0){ // L rotated 0 degrees
			canMoveNorth = true; canMoveSouth = false;
			canMoveEast = true; canMoveWest = false;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,E: T    S,W: F");
		}
		else if(currentTile == 3 && tileRotation == 90){ // L rotated 90 degrees
			canMoveNorth = true; canMoveSouth = false;
			canMoveEast = false; canMoveWest = true;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,W: T    S,E: F");
		}
		else if(currentTile == 3 && tileRotation == 180){ // L rotated 180 degrees
			canMoveNorth = false; canMoveSouth = true;
			canMoveEast = false; canMoveWest = true;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   S,W: T    N,E: F");
		}
		else if(currentTile == 3 && tileRotation == 270){ // L rotated 270 degrees
			canMoveNorth = false; canMoveSouth = true;
			canMoveEast = true; canMoveWest = false;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   S,E: T    N,W: F");
		}
		
		
		else if(currentTile == 4 && tileRotation == 0){ // T rotated 0 degrees
			canMoveNorth = false; canMoveSouth = true;
			canMoveEast = true; canMoveWest = true;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   S,W,E: T    N: F");
		}
		else if(currentTile == 4 && tileRotation == 90){ // T rotated 90 degrees
			canMoveNorth = true; canMoveSouth = true;
			canMoveEast = true; canMoveWest = false;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,S,E: T    W: F");
		}
		else if(currentTile == 4 && tileRotation == 180){ // T rotated 180 degrees
			canMoveNorth = true; canMoveSouth = false;
			canMoveEast = true; canMoveWest = true;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,W,E: T    S: F");
		}
		else if(currentTile == 4 && tileRotation == 270){ // T rotated 270 degrees
			canMoveNorth = true; canMoveSouth = true;
			canMoveEast = false; canMoveWest = true;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,S,W: T    E: F");
		}
		else if(currentTile == 5){ //in plus tile
			canMoveNorth = true; canMoveSouth = true;
			canMoveEast = true; canMoveWest = true;
//			System.out.println("Current Tile: " + currentTile + "   Rotatation: " + tileRotation +
//					"   N,S,W,E: T");
		}
		
	}
	
	
	protected void moveNorth(){
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
		
//		System.out.println("Moving North to " + nYL);
		car.setYLocation(nYL);
		currentY = nYL;
	}
	
	protected void moveEast(){
//		System.out.println("Moving East to " + (currentX+1));
		car.setXLocation(currentX + 1);
		currentX += 1;
	}
	
	protected void moveSouth(){
		char nYL = 'I';
		if(currentY == 'A'){
			nYL = 'B';
		}
		else if(currentY == 'B'){
			nYL = 'C';
		}
		else if(currentY == 'C'){
			nYL = 'D';
		}
		else if(currentY == 'D'){
			nYL = 'E';
		}
		else if(currentY == 'E'){
			nYL = 'F';
		}
		else if(currentY == 'F'){
			nYL = 'G';
		}
		else if(currentY == 'G'){
			nYL = 'H';
		}
		
//		System.out.println("Moving South to " + nYL);
		car.setYLocation(nYL);
		currentY = nYL;
	}
	
	protected void moveWest(){
//		System.out.println("Moving West to " + (currentX-1));
		car.setXLocation(currentX - 1);
		currentX -= 1;
	}
	
	
	public abstract void move();
	
	
}
