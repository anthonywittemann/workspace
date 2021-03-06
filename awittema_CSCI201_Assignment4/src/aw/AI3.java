package aw;

public class AI3 extends ParentAI{
	
	private boolean goingEast;
	
	public AI3(Car c){
		super(c);
		goingEast = true;
	}

	@Override
	public void move() {
		// WORKS!!!!!!!!!!!!!!!!!!!!!
		// The car always goes east given the opportunity until it hits the east most possible road.
		// The car then always goes west given the opportunity until it hits the west most possible road.
		// If the car cannot move its favored direction, it randomly picks a direction. (This includes
		// going east if target is west and vice versa).
		
		if(goingEast){
			if(super.canMoveEast){
				super.moveEast();
			}
			else{ //randomly choose direction
				double randomDec = Math.random();
				
				if(canMoveNorth && canMoveWest && canMoveSouth){ //if 3 other options
					if(randomDec < .3333333333){
						moveNorth();
					}
					else if(randomDec < .666666666666){
						moveWest();
					}
					else{
						moveSouth();
					}
				}
				
				else if(canMoveNorth && canMoveWest && !canMoveSouth){ //can't move south
					if(randomDec < .5){
						moveNorth();
					}
					else{
						moveWest();
					}
				}
				
				else if(canMoveNorth && !canMoveWest && canMoveSouth){ //can't move west
					if(randomDec < .5){
						moveNorth();
					}
					else{
						moveSouth();
					}
				}
				
				else if(!canMoveNorth && canMoveWest && canMoveSouth){ //can't move north
					if(randomDec < .5){
						moveSouth();
					}
					else{
						moveWest();
					}
				}
				
				//can only move 1 direction
				else if(canMoveNorth){
					moveNorth();
				}
				
				else if(canMoveSouth){
					moveSouth();
				}
				
				else if(canMoveWest){
					moveWest();
				}
				
				goingEast = false; //toggle direction
			}
		}
		else{ //going West
			if(super.canMoveWest){
				super.moveWest();
			}
			else{ //randomly choose direction
				double randomDec = Math.random();
				
				if(canMoveNorth && canMoveEast && canMoveSouth){ //if 3 other options
					if(randomDec < .3333333333){
						moveNorth();
					}
					else if(randomDec < .666666666666){
						moveEast();
					}
					else{
						moveSouth();
					}
				}
				
				else if(canMoveNorth && canMoveEast && !canMoveSouth){ //can't move south
					if(randomDec < .5){
						moveNorth();
					}
					else{
						moveEast();
					}
				}
				
				else if(canMoveNorth && !canMoveEast && canMoveSouth){ //can't move East
					if(randomDec < .5){
						moveNorth();
					}
					else{
						moveSouth();
					}
				}
				
				else if(!canMoveNorth && canMoveEast && canMoveSouth){ //can't move north
					if(randomDec < .5){
						moveSouth();
					}
					else{
						moveEast();
					}
				}
				
				//can only move 1 direction
				else if(canMoveNorth){
					moveNorth();
				}
				
				else if(canMoveSouth){
					moveSouth();
				}
				
				else if(canMoveEast){
					moveEast();
				}
				
				goingEast = true; //toggle direction
			}
		}
		
	}

}
