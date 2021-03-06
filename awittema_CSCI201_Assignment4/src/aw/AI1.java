package aw;

public class AI1 extends ParentAI{
	
	private short moveCount;
	private short previousTile; //can be 0: no previous moves, 1: North, 2: East, 3: South, 4: West
	
	public AI1(Car c){
		super(c);
		moveCount = 0;
		previousTile = 0;
	}

	@Override
	public void move() {
//		System.out.println("		Previous Move: " + previousTile);
		
		if(moveCount == 0){ //attempt to move left on first move
			if(super.canMoveWest){
				super.moveWest();
				previousTile = 2;
			}
		}
		//WORKS!!!!!!!!!!!!!!!
		//On all subsequent moves, the car tries to move -90 degrees from its previous move.
		//attempt to turn right
		//If the desired direction isn't a valid move, the car attempts to move in the next direction clockwise.
		//The car cannot move back to the tile it was previously on.
		else{
			if(previousTile == 1){ //last move was north
				if(canMoveEast){ //attempt to move east
					super.moveEast();
					previousTile = 4;
				}
				else if(canMoveSouth){ //next try south, the next right after east
					super.moveSouth();
					previousTile = 1;
				}
				else{ //must move west b/c can't go back previous way (north)
					super.moveWest();
					previousTile = 2;
				}
			}


			else if(previousTile == 2){ //last move was east
				if(canMoveSouth){ 
					super.moveSouth();
					previousTile = 1;
				}
				else if(canMoveWest){ 
					super.moveWest();
					previousTile = 2;
				}
				else{
					super.moveNorth();
					previousTile = 3;
				}
			}


			else if(previousTile == 3){ //last move was south
				if(canMoveWest){ 
					super.moveWest();
					previousTile = 2;
				}
				else if(canMoveNorth){
					super.moveNorth();
					previousTile = 3;
				}
				else{
					super.moveEast();
					previousTile = 4;
				}
			}


			else if(previousTile == 4){ //last move was west
				if(canMoveNorth){
					super.moveNorth();
					previousTile = 3;
				}
				else if(canMoveEast){
					super.moveEast();
					previousTile = 4;
				}
				else{
					super.moveSouth();
					previousTile = 1;
				}
			}
			
			else{ //no previous moves
				if(canMoveNorth){
					super.moveNorth();
					previousTile = 3;
				}
				else if(canMoveEast){
					super.moveEast();
					previousTile = 4;
				}
				else{
					super.moveSouth();
					previousTile = 1;
				}
			}
		}
//		System.out.println("Car 1: Tile: " + previousTile);
		moveCount++;
	}

}
