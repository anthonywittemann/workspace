package aw;

public class Worker extends Thread{
	public static final short WAITING_HOME = 0;
	public static final short MOVING_TO_TASK_BOARD = 1;
	public static final short AT_TASK_BOARD = 2;
	public static final short MOVING_TO_MATERIAL_CONTAINER = 3;
	public static final short WAITING_FOR_MATERIAL = 4;
	public static final short MOVING_TO_TOOL_SHED = 5;
	public static final short WAITING_FOR_TOOL = 6;
	public static final short MOVING_TO_WORK_AREA = 7;
	public static final short WAITING_FOR_WORK_AREA = 8;
	public static final short DONE_WITH_CURRENT_PRODUCT = 9;
	public static final short RETURNING_TO_TASK_BOARD = 10;
	public static final short WORKING_AT_WORK_AREA = 11;
	public static final short RETURN_TO_TOOL_SHED = 12;
	
	public static final short ANVIL_1 = 100;
	public static final short ANVIL_2 = 101;
	public static final short WORKBENCH_1 = 102;
	public static final short WORKBENCH_2 = 103;
	public static final short WORKBENCH_3 = 104;
	public static final short SAW_1 = 105;
	public static final short SAW_2 = 106;
	public static final short SAW_3 = 107;
	public static final short FURNACE_1 = 108;
	public static final short FURNACE_2 = 109;
	public static final short PAINTING_STATION_1 = 110;
	public static final short PAINTING_STATION_2 = 111;
	public static final short PAINTING_STATION_3 = 112;
	public static final short PAINTING_STATION_4 = 113;
	public static final short PRESS = 114;
	
	private short currentWorkstation = -1;
	
	public static final int WORKER_H_N_W = 40; //Worker height and width
	
	
	public static final short MOVING_UP = 0;
	public static final short MOVING_RIGHT = 1;
	public static final short MOVING_DOWN = 2;
	public static final short MOVING_LEFT = 3;
	
	private short currentDirection;
	private short currentState;
	private int currentXLoc;
	private int currentYLoc;
	
	private Product currentProduct;
	private short currentProductKey;
	//used for both whether or not originally needed and whether already obtained
	private boolean plasticNeeded = false; 
	private boolean metalNeeded = false;
	private boolean woodNeeded = false;
	
	private Recipe currentRecipe;
	private boolean screwdriverNeeded = false;
	private boolean scissorsNeeded = false;
	private boolean hammerNeeded = false;
	private boolean paintbrushNeeded = false;
	private boolean plyersNeeded = false;
	
	private boolean screwdriverObtained = false;
	private boolean scissorsObtained = false;
	private boolean hammerObtained = false;
	private boolean paintbrushObtained = false;
	private boolean plyersObtained = false;
	
	private boolean screwdriverReturned = true;
	private boolean scissorsReturned = true;
	private boolean hammerReturned = true;
	private boolean paintbrushReturned = true;
	private boolean plyersReturned = true;
	
	public static final int SPEED = 10; //10 pixels either up, down, left or right
	
	private int timeLeftAtStation = -1;
	
	public Worker(){
		
		currentXLoc = 0;
		currentYLoc = 0;
		this.currentState = MOVING_TO_TASK_BOARD;
		this.currentDirection = MOVING_RIGHT;
	}
	
	
	public void run(){
		move();
	}
	
	private void discernRequiredMaterials(){
		if(currentProduct.getNumPlasticNeeded() > 0){
			plasticNeeded = true;
		}
		if(currentProduct.getNumMetalNeeded() > 0){
			metalNeeded = true;
		}
		if(currentProduct.getNumWoodNeeded() > 0){
			woodNeeded = true;
		}
	}
	
	//this logic is OK
	private void discernRequiredTools(){
		if(currentRecipe.usesPlyers() && !plyersObtained){
			plyersNeeded = true;
			//System.out.println("Plyers needed");
		}
		else{
			plyersNeeded = false;
		}
		if(currentRecipe.usesScissors() && !scissorsObtained){
			scissorsNeeded = true;
			//System.out.println("Scissors needed");
		}
		else{
			scissorsNeeded = false;
		}
		if(currentRecipe.usesPaintbrush() && !paintbrushObtained){
			paintbrushNeeded = true;
			//System.out.println("Paintbrush needed");
		}
		else{
			paintbrushNeeded = false;
		}
		if(currentRecipe.usesHammer() && !hammerObtained){
			hammerNeeded = true;
			//System.out.println("Hammer needed");
		}
		else{
			hammerNeeded = false;
		}
		if(currentRecipe.usesScrewdriver() && !screwdriverObtained){
			screwdriverNeeded = true;
			//System.out.println("Screwdriver needed");
		}
		else{
			screwdriverNeeded = false;
		}
	}
	
	//called every time the timer ticks
	//avoid obstacles and figure out where worker is in factory, 
	//TODO figure out where to go next, how to follow Recipe, etc.
	public void move(){
//		System.out.println("MOVE");
		
		if(currentState == MOVING_TO_TASK_BOARD){
			moveToTaskBoard();

		}
		else if(currentState == AT_TASK_BOARD){
			//get a task & change product state
			if(currentProduct != null){ //if it's not the first time coming to the task board
				FactoryFrame.productFinished(currentProduct);
			}
			currentProduct = FactoryFrame.getTask();
			if(currentProduct != null){
				currentProductKey = currentProduct.getKeyCode();
				//start the task--get required materials
				discernRequiredMaterials();
				currentState = MOVING_TO_MATERIAL_CONTAINER;
				currentXLoc -= SPEED;
				currentDirection = MOVING_LEFT;
			}
			else{
				//Keep waiting for a product
			}
			
		}
		else if(currentState == MOVING_TO_MATERIAL_CONTAINER){ //called after AT_TASK_BOARD
			//figure out which material needed from product,
			//navigate to correct containers
			if(plasticNeeded){
				getPlastic();
			}
			else if(metalNeeded){
				getMetal();
			}
			else if(woodNeeded){
				getWood();
			}
			else{
				currentState = MOVING_TO_TOOL_SHED;
			}
			
		}
		//implement this to get materials and then move to tool shed or next material
		else if(currentState == WAITING_FOR_MATERIAL){ //don't think this is necessary
			if(plasticNeeded){
				getPlastic();
			}
			else if(metalNeeded){
				getMetal();
			}
			else if(woodNeeded){
				getWood();
			}
			else{
				currentState = MOVING_TO_TOOL_SHED;
				//grab tools necessary for only 1 step at a time
				currentRecipe = currentProduct.getNextInstruction();
			}
		}
		else if(currentState == MOVING_TO_TOOL_SHED){
			//TODO freezes when trying to acquire more tools than are available
			if(currentRecipe != null){
				//follow recipe
				discernRequiredTools();  //discern tools is causing the tools needed to be set to true
				if(screwdriverNeeded){
					getScrewdriver();
					System.out.println("Getting Screwdriver");
				}
				else if(hammerNeeded){
					getHammers();
					System.out.println("Getting hammers");
				}
				else if(paintbrushNeeded){
					getPaintbrushes();
					System.out.println("Getting Paintbrushes");
				}
				else if(plyersNeeded){
					getPlyers();
					System.out.println("Getting Plyers");
				}
				else if(scissorsNeeded){
					getScissors();
					System.out.println("Getting Scissors");
				}
				else{
					currentState = MOVING_TO_WORK_AREA;
					currentDirection = MOVING_RIGHT;
					System.out.println("All tools acquired");
					return;
				}
			}
			else{
				currentState = MOVING_TO_WORK_AREA;
				currentDirection = MOVING_RIGHT;
				System.out.println("All tools acquired");
			}
		}
		else if(currentState == WAITING_FOR_TOOL){
			//make sure to recognize semaphores for obtaining tools
			System.out.println("Waiting for tool");
			
		}
		else if(currentState == MOVING_TO_WORK_AREA){
			if(currentRecipe.getStation() == Recipe.ANVIL){
				getToAnvils();
				System.out.println("Moving to Anvils");
			}
			else if(currentRecipe.getStation() == Recipe.FURNACE){
				getToFurnaces();
				System.out.println("Moving to Furnaces");
			}
			else if(currentRecipe.getStation() == Recipe.PAINTING_STATION){
				getToPaintingStations();
				System.out.println("Moving to Painting Stations");
			}
			else if(currentRecipe.getStation() == Recipe.WORKBENCH){
				getToWorkBenches();
				System.out.println("Moving to Workbenches");
			}
			else if(currentRecipe.getStation() == Recipe.SAW){
				getToSaws();
				System.out.println("Moving to Painting Saws");
			}
			else if(currentRecipe.getStation() == Recipe.PRESS){
				getToPress();
				System.out.println("Moving to Painting Press");
			}
			else{
				System.out.println("Damn it, wrong station code");
			}	
			
		}
		else if(currentState == WAITING_FOR_WORK_AREA){
			//TODO obtain lock for Work Area
			
		}
		else if(currentState == WORKING_AT_WORK_AREA){
			//manage countdown and if done, return tools, go to next task
			System.out.println("Time Remaining: " + timeLeftAtStation);
			if(timeLeftAtStation <= 0){
				currentState = DONE_WITH_CURRENT_PRODUCT;
			}
			else{
				timeLeftAtStation -= FactoryFrame.TIMER_INTERVAL;
				updateTimeLeftLbls();
			}
			
		}
		else if(currentState == DONE_WITH_CURRENT_PRODUCT){
			
			//return lock on work area
			if(currentWorkstation == ANVIL_1){
				FactoryFrame.anvil1Lock.unlock();
				FactoryFrame.anvil1Occupied = false;
			}
			else if(currentWorkstation == ANVIL_2){
				FactoryFrame.anvil2Lock.unlock();
				FactoryFrame.anvil2Occupied = false;
			}
			else if(currentWorkstation == WORKBENCH_1){
				FactoryFrame.workbench1Lock.unlock();
				FactoryFrame.workbench1Occupied = false;
			}
			else if(currentWorkstation == WORKBENCH_2){
				FactoryFrame.workbench2Lock.unlock();
				FactoryFrame.workbench2Occupied = false;
			}
			else if(currentWorkstation == WORKBENCH_3){
				FactoryFrame.workbench3Lock.unlock();
				FactoryFrame.workbench3Occupied = false;
			}
			else if(currentWorkstation == SAW_1){
				FactoryFrame.tablesaw1Lock.unlock();
				FactoryFrame.tablesaw1Occupied = false;
			}
			else if(currentWorkstation == SAW_2){
				FactoryFrame.tablesaw2Lock.unlock();
				FactoryFrame.tablesaw2Occupied = false;
			}
			else if(currentWorkstation == SAW_3){
				FactoryFrame.tablesaw3Lock.unlock();
				FactoryFrame.tablesaw3Occupied = false;
			}
			else if(currentWorkstation == FURNACE_1){
				FactoryFrame.furnace1Lock.unlock();
				FactoryFrame.furnace1Occupied = false;
			}
			else if(currentWorkstation == FURNACE_2){
				FactoryFrame.furnace2Lock.unlock();
				FactoryFrame.furnace2Occupied = false;
			}
			else if(currentWorkstation == PAINTING_STATION_1){
				FactoryFrame.paintingStation1Lock.unlock();
				FactoryFrame.paintingStation1Occupied = false;
			}
			else if(currentWorkstation == PAINTING_STATION_2){
				FactoryFrame.paintingStation2Lock.unlock();
				FactoryFrame.paintingStation2Occupied = false;
			}
			else if(currentWorkstation == PAINTING_STATION_3){
				FactoryFrame.paintingStation3Lock.unlock();
				FactoryFrame.paintingStation3Occupied = false;
			}
			else if(currentWorkstation == PAINTING_STATION_4){
				FactoryFrame.paintingStation4Lock.unlock();
				FactoryFrame.paintingStation4Occupied = false;
			}
			else if(currentWorkstation == PRESS){
				FactoryFrame.press1Lock.unlock();
				FactoryFrame.press1Occupied = false;
			}
			
			currentWorkstation = -1;
			
			
			//return tools to toolbox
			currentState = RETURN_TO_TOOL_SHED;
		}
		else if(currentState == RETURN_TO_TOOL_SHED){
			if(scissorsReturned && hammerReturned && paintbrushReturned &&
					plyersReturned && screwdriverReturned){		
				
				currentState = RETURNING_TO_TASK_BOARD;	//if everything is returned, return to Task Board
			}
			//check if returning or checking out tools in get methods
			else if(!scissorsReturned){
				getScissors();
			}
			else if(!plyersReturned){
				getPlyers();
			}
			else if(!paintbrushReturned){
				getPlyers();
			}
			else if(!hammerReturned){
				getHammers();
			}
			else if(!screwdriverReturned){
				getScrewdriver();
			}
		}
		else if(currentState == RETURNING_TO_TASK_BOARD){
			moveToTaskBoard();
		}
		
	}
	
	
	private void updateTimeLeftLbls(){
		if(currentWorkstation == ANVIL_1){
			FactoryFrame.anvil1TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.anvil1Occupied = true;
		}
		else if(currentWorkstation == ANVIL_2){
			FactoryFrame.anvil2TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.anvil2Occupied = true;
		}
		else if(currentWorkstation == WORKBENCH_1){
			FactoryFrame.workbench1TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.workbench1Occupied = true;
		}
		else if(currentWorkstation == WORKBENCH_2){
			FactoryFrame.workbench2TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.workbench2Occupied = true;
		}
		else if(currentWorkstation == WORKBENCH_3){
			FactoryFrame.workbench3TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.workbench3Occupied = true;
		}
		else if(currentWorkstation == SAW_1){
			FactoryFrame.tablesaw1TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.tablesaw1Occupied= true;
		}
		else if(currentWorkstation == SAW_2){
			FactoryFrame.tablesaw2TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.tablesaw2Occupied= true;
		}
		else if(currentWorkstation == SAW_3){
			FactoryFrame.tablesaw3TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.tablesaw3Occupied= true;
		}
		else if(currentWorkstation == FURNACE_1){
			FactoryFrame.furnace1TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.furnace1Occupied = true;
		}
		else if(currentWorkstation == FURNACE_2){
			FactoryFrame.furnace2TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.furnace2Occupied = true;
		}
		else if(currentWorkstation == PAINTING_STATION_1){
			FactoryFrame.paintingStation1TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.paintingStation1Occupied = true;
		}
		else if(currentWorkstation == PAINTING_STATION_2){
			FactoryFrame.paintingStation2TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.paintingStation2Occupied = true;
		}
		else if(currentWorkstation == PAINTING_STATION_3){
			FactoryFrame.paintingStation3TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.paintingStation3Occupied = true;
		}
		else if(currentWorkstation == PAINTING_STATION_4){
			FactoryFrame.paintingStation4TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.paintingStation4Occupied = true;
		}
		else if(currentWorkstation == PRESS){
			FactoryFrame.press1TimeRemaining = (int) (timeLeftAtStation / 1000);
			FactoryFrame.press1Occupied = true;
		}
	}
	
	
	private void getToPress() {
		if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PRESS_X && 			//move on top of PRESS 1 if there
				currentXLoc - SPEED <= FactoryFrame.PRESS_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y && 
				currentYLoc - SPEED <= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.press1Lock.tryLock()){
				FactoryFrame.press1Lock.lock();
				currentXLoc = FactoryFrame.PRESS_X;
				currentYLoc = FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = PRESS;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			System.out.println("Got to PRESS 1");
			currentWorkstation = PRESS;
		}
		else{
			if(currentXLoc + WORKER_H_N_W < FactoryFrame.PRESS_X && canMoveRight()){ 
				currentXLoc += SPEED;					//try moving right if you're left of PRESSs
				currentDirection = MOVING_RIGHT;
			}
			else if(currentXLoc > FactoryFrame.PRESS_X + WORKER_H_N_W && canMoveLeft()){
				currentXLoc -= SPEED;					//try moving left if you're right of PRESSs
				currentDirection = MOVING_LEFT;
			}
			else if(currentYLoc > FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W && canMoveUp()){
				currentYLoc -= SPEED;
				currentDirection = MOVING_UP;
			}
			else if(currentYLoc + WORKER_H_N_W < FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y && canMoveDown()){
				currentYLoc += SPEED;
				currentDirection = MOVING_DOWN;
			}
			else{
				System.out.println("Oh dear, not supposed to be here: trying to get to PRESSes");
			}
		}
		
	}


	private void getToSaws() {
		if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAW1_X && 			//move on top of SAW 1 if there
				currentXLoc - SPEED <= FactoryFrame.SAW1_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAWS_AND_FURNACES_Y && 
				currentYLoc - SPEED <= FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.tablesaw1Lock.tryLock()){
				FactoryFrame.tablesaw1Lock.lock();
				currentXLoc = FactoryFrame.SAW1_X;
				currentYLoc = FactoryFrame.SAWS_AND_FURNACES_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = SAW_1;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc += SPEED;
				currentDirection = MOVING_RIGHT;
			}
			System.out.println("Got to SAW 1");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAW2_X && 			//move on top of SAW 2 if there
				currentXLoc - SPEED <= FactoryFrame.SAW2_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAWS_AND_FURNACES_Y && 
				currentYLoc - SPEED <= FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.tablesaw2Lock.tryLock()){
				FactoryFrame.tablesaw2Lock.lock();
				currentXLoc = FactoryFrame.SAW2_X;
				currentYLoc = FactoryFrame.SAWS_AND_FURNACES_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = SAW_2;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				if(FactoryFrame.tablesaw3Lock.tryLock()){ //move right if saw 3 is open
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
			}
			System.out.println("Got to SAW 2");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAW3_X && 			//move on top of SAW 3 if there
				currentXLoc - SPEED <= FactoryFrame.SAW3_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAWS_AND_FURNACES_Y && 
				currentYLoc - SPEED <= FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.tablesaw3Lock.tryLock()){
				FactoryFrame.tablesaw3Lock.lock();
				currentXLoc = FactoryFrame.SAW3_X;
				currentYLoc = FactoryFrame.SAWS_AND_FURNACES_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = SAW_3;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc -= SPEED;
				currentDirection = MOVING_LEFT;
			}
			System.out.println("Got to SAW 3");
		}
		else{
			if(currentXLoc + WORKER_H_N_W < FactoryFrame.SAW1_X && canMoveRight()){ 
				currentXLoc += SPEED;					//try moving right if you're left of SAWs
				currentDirection = MOVING_RIGHT;
			}
			else if(currentXLoc > FactoryFrame.SAW3_X + WORKER_H_N_W && canMoveLeft()){
				currentXLoc -= SPEED;					//try moving left if you're right of SAWs
				currentDirection = MOVING_LEFT;
			}
			else if(currentYLoc > FactoryFrame.SAWS_AND_FURNACES_Y+ WORKER_H_N_W && canMoveUp()){
				currentYLoc -= SPEED;
				currentDirection = MOVING_UP;
			}
			else if(currentYLoc + WORKER_H_N_W < FactoryFrame.SAWS_AND_FURNACES_Y && canMoveDown()){
				currentYLoc += SPEED;
				currentDirection = MOVING_DOWN;
			}
			else{
				System.out.println("Oh dear, not supposed to be here: trying to get to SAWes");
			}
		}
		
	}


	private void getToWorkBenches() {
		if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCH1_X && 			//move on top of WORKBENCH 1 if there
				currentXLoc - SPEED <= FactoryFrame.WORKBENCH1_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCHES_AND_ANVILS_Y && 
				currentYLoc - SPEED <= FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.workbench1Lock.tryLock()){
				FactoryFrame.workbench1Lock.lock();
				currentXLoc = FactoryFrame.WORKBENCH1_X;
				currentYLoc = FactoryFrame.WORKBENCHES_AND_ANVILS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = WORKBENCH_1;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc += SPEED;
				currentDirection = MOVING_RIGHT;
			}
			System.out.println("Got to WORKBENCH 1");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCH2_X && 			//move on top of WORKBENCH 2 if there
				currentXLoc - SPEED <= FactoryFrame.WORKBENCH2_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCHES_AND_ANVILS_Y && 
				currentYLoc - SPEED <= FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.workbench2Lock.tryLock()){
				FactoryFrame.workbench2Lock.lock();
				currentXLoc = FactoryFrame.WORKBENCH2_X;
				currentYLoc = FactoryFrame.WORKBENCHES_AND_ANVILS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = WORKBENCH_2;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				if(FactoryFrame.workbench1Lock.tryLock()){
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
				else{
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				
			}
			System.out.println("Got to WORKBENCH 2");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCH3_X && 			//move on top of WORKBENCH 3 if there
				currentXLoc - SPEED <= FactoryFrame.WORKBENCH3_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCHES_AND_ANVILS_Y && 
				currentYLoc - SPEED <= FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.workbench3Lock.tryLock()){
				FactoryFrame.workbench3Lock.lock();
				currentXLoc = FactoryFrame.WORKBENCH3_X;
				currentYLoc = FactoryFrame.WORKBENCHES_AND_ANVILS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = WORKBENCH_3;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc -= SPEED;
				currentDirection = MOVING_LEFT;
			}
			System.out.println("Got to WORKBENCH 3");
		}
		else if(currentXLoc + WORKER_H_N_W < FactoryFrame.WORKBENCH1_X && canMoveRight()){ 
			currentXLoc += SPEED;					//try moving right if you're left of WORKBENCHs
			currentDirection = MOVING_RIGHT;
		}
		else if(currentXLoc > FactoryFrame.WORKBENCH3_X + WORKER_H_N_W && canMoveLeft()){
			currentXLoc -= SPEED;					//try moving left if you're right of WORKBENCHs
			currentDirection = MOVING_LEFT;
		}
		else if(currentYLoc > FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W && canMoveUp()){
			currentYLoc -= SPEED;
			currentDirection = MOVING_UP;
		}
		else if(currentYLoc + WORKER_H_N_W < FactoryFrame.WORKBENCHES_AND_ANVILS_Y && canMoveDown()){
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
		else{
			System.out.println("Oh dear, not supposed to be here: trying to get to WORKBENCHes");
		}
	}


	private void getToPaintingStations() {
		if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAINTING_STATION1_X && 			//move on top of PAINTING_STATION 1 if there
				currentXLoc - SPEED <= FactoryFrame.PAINTING_STATION1_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y && 
				currentYLoc - SPEED <= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.paintingStation1Lock.tryLock()){
				FactoryFrame.paintingStation1Lock.lock();
				currentXLoc = FactoryFrame.PAINTING_STATION1_X;
				currentYLoc = FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = PAINTING_STATION_1;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc += SPEED;
				currentDirection = MOVING_RIGHT;
			}
			System.out.println("Got to PAINTING_STATION 1");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAINTING_STATION2_X && 			//move on top of PAINTING_STATION 2 if there
				currentXLoc - SPEED <= FactoryFrame.PAINTING_STATION2_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y && 
				currentYLoc - SPEED <= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.paintingStation2Lock.tryLock()){
				FactoryFrame.paintingStation2Lock.lock();
				currentXLoc = FactoryFrame.PAINTING_STATION2_X;
				currentYLoc = FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = PAINTING_STATION_2;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				if(FactoryFrame.paintingStation1Lock.tryLock()){
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
				else{
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				
			}
			System.out.println("Got to PAINTING_STATION 2");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAINTING_STATION3_X && 			//move on top of PAINTING_STATION 3 if there
				currentXLoc - SPEED <= FactoryFrame.PAINTING_STATION3_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y && 
				currentYLoc - SPEED <= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.paintingStation3Lock.tryLock()){
				FactoryFrame.paintingStation3Lock.lock();
				currentXLoc = FactoryFrame.PAINTING_STATION3_X;
				currentYLoc = FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = PAINTING_STATION_3;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				if(FactoryFrame.paintingStation4Lock.tryLock()){
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
				
			}
			System.out.println("Got to PAINTING_STATION 3");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAINTING_STATION4_X && 			//move on top of PAINTING_STATION 4 if there
				currentXLoc - SPEED <= FactoryFrame.PAINTING_STATION4_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y && 
				currentYLoc - SPEED <= FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.paintingStation4Lock.tryLock()){
				FactoryFrame.paintingStation4Lock.lock();
				currentXLoc = FactoryFrame.PAINTING_STATION4_X;
				currentYLoc = FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = PAINTING_STATION_4;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc -= SPEED;
				currentDirection = MOVING_LEFT;
			}
			System.out.println("Got to PAINTING_STATION 4");
		}
		else if(currentXLoc + WORKER_H_N_W < FactoryFrame.PAINTING_STATION1_X && canMoveRight()){ 
			currentXLoc += SPEED;					//try moving right if you're left of PAINTING_STATIONs
			currentDirection = MOVING_RIGHT;
		}
		else if(currentXLoc > FactoryFrame.PAINTING_STATION4_X + WORKER_H_N_W && canMoveLeft()){
			currentXLoc -= SPEED;					//try moving left if you're right of PAINTING_STATIONs
			currentDirection = MOVING_LEFT;
		}
		else if(currentYLoc > FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W && canMoveUp()){
			currentYLoc -= SPEED;
			currentDirection = MOVING_UP;
		}
		else if(currentYLoc + WORKER_H_N_W < FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y && canMoveDown()){
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
		else{
			System.out.println("Oh dear, not supposed to be here: trying to get to PAINTING_STATIONes");
		}
	}


	private void getToFurnaces() {
		if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.FURNACE1_X && 			//move on top of FURNACE 1 if there
				currentXLoc - SPEED <= FactoryFrame.FURNACE1_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAWS_AND_FURNACES_Y && 
				currentYLoc - SPEED <= FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.furnace1Lock.tryLock()){
				FactoryFrame.furnace1Lock.lock();
				currentXLoc = FactoryFrame.FURNACE1_X;
				currentYLoc = FactoryFrame.SAWS_AND_FURNACES_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = FURNACE_1;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc += SPEED;
				currentDirection = MOVING_RIGHT;
			}
			System.out.println("Got to FURNACE 1");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.FURNACE2_X && 			//move on top of FURNACE 2 if there
				currentXLoc - SPEED <= FactoryFrame.FURNACE2_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.SAWS_AND_FURNACES_Y && 
				currentYLoc - SPEED <= FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W){
			if(FactoryFrame.furnace2Lock.tryLock()){
				FactoryFrame.furnace2Lock.lock();
				currentXLoc = FactoryFrame.FURNACE2_X;
				currentYLoc = FactoryFrame.SAWS_AND_FURNACES_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = FURNACE_2;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc -= SPEED;
				currentDirection = MOVING_LEFT;
			}
			System.out.println("Got to FURNACE 2");
		}
		else if(currentXLoc + WORKER_H_N_W < FactoryFrame.FURNACE1_X && canMoveRight()){ 
			currentXLoc += SPEED;					//try moving right if you're left of FURNACEs
			currentDirection = MOVING_RIGHT;
		}
		else if(currentXLoc > FactoryFrame.FURNACE2_X + WORKER_H_N_W && canMoveLeft()){
			currentXLoc -= SPEED;					//try moving left if you're right of FURNACEs
			currentDirection = MOVING_LEFT;
		}
		else if(currentYLoc > FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W && canMoveUp()){
			currentYLoc -= SPEED;
			currentDirection = MOVING_UP;
		}
		else if(currentYLoc + WORKER_H_N_W < FactoryFrame.SAWS_AND_FURNACES_Y && canMoveDown()){
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
		else{
			System.out.println("Oh dear, not supposed to be here: trying to get to FURNACEs");
		}
	}


	private void getToAnvils() {
		if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.ANVIL1_X && 			//move on top of Anvil 1 if there
				currentXLoc - SPEED <= FactoryFrame.ANVIL1_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCHES_AND_ANVILS_Y && 
				currentYLoc - SPEED <= FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.anvil1Lock.tryLock()){
				FactoryFrame.anvil1Lock.lock();
				currentXLoc = FactoryFrame.ANVIL1_X;
				currentYLoc = FactoryFrame.WORKBENCHES_AND_ANVILS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = ANVIL_1;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc += SPEED;
				currentDirection = MOVING_RIGHT;
			}
			System.out.println("Got to Anvil 1");
		}
		else if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.ANVIL2_X && 			//move on top of Anvil 2 if there
				currentXLoc - SPEED <= FactoryFrame.ANVIL2_X + WORKER_H_N_W &&
				currentYLoc + SPEED + WORKER_H_N_W >= FactoryFrame.WORKBENCHES_AND_ANVILS_Y && 
				currentYLoc - SPEED <= FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W){
			//acquireLock, move on top if lock acquired and start timer
			if(FactoryFrame.anvil2Lock.tryLock()){
				FactoryFrame.anvil2Lock.lock();
				currentXLoc = FactoryFrame.ANVIL2_X;
				currentYLoc = FactoryFrame.WORKBENCHES_AND_ANVILS_Y;
				currentState = WORKING_AT_WORK_AREA;
				currentWorkstation = ANVIL_2;
				timeLeftAtStation = currentRecipe.getTotalTime();
			}
			else{			//try moving to another station if this one's occupied
				currentXLoc -= SPEED;
				currentDirection = MOVING_LEFT;
			}
			System.out.println("Got to Anvil 2");
		}
		else if(currentXLoc + WORKER_H_N_W < FactoryFrame.ANVIL1_X && canMoveRight()){ 
			currentXLoc += SPEED;					//try moving right if you're left of anvils
			currentDirection = MOVING_RIGHT;
		}
		else if(currentXLoc > FactoryFrame.ANVIL2_X + WORKER_H_N_W && canMoveLeft()){
			currentXLoc -= SPEED;					//try moving left if you're right of anvils
			currentDirection = MOVING_LEFT;
		}
		else if(currentYLoc > FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W && canMoveUp()){
			currentYLoc -= SPEED;
			currentDirection = MOVING_UP;
		}
		else if(currentYLoc + WORKER_H_N_W < FactoryFrame.WORKBENCHES_AND_ANVILS_Y && canMoveDown()){
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
		else{
			System.out.println("Oh dear, not supposed to be here: trying to get to anvils");
		}
		
	}


	private void moveToTaskBoard() {
		if(currentXLoc + SPEED + WORKER_H_N_W >= FactoryFrame.TASK_BOARD_X){ //move to taskboard if almost there
			currentState = AT_TASK_BOARD;
			currentXLoc += SPEED;
			currentDirection = MOVING_LEFT;						//change direction to head to next destination
		}
		else if(MOVING_RIGHT == currentDirection){ 
			if(canMoveRight()){							//continue right towards taskboard
				currentXLoc += SPEED;
			}
			else{
				if(canMoveDown()){						//move down if can't move right
					currentYLoc += SPEED;
					currentDirection = MOVING_DOWN;
				}
				else{									//move up if can't move right or down
					currentYLoc -= SPEED;
					currentDirection = MOVING_UP;
				}
			}
			
		}
		else if(MOVING_DOWN == currentDirection){
			if(canMoveRight()){			//try to move right
				currentState = MOVING_RIGHT;
				currentXLoc += SPEED;
			}
			else{						//keep moving down
				currentYLoc += SPEED;
			}
		}
		else if(MOVING_UP == currentDirection){
			if(canMoveRight()){			//try to move right
				currentState = MOVING_RIGHT;
				currentXLoc += SPEED;
			}
			else{						//keep moving up
				currentYLoc -= SPEED;
			}
		}
		else{
			System.out.println("This ain't supposed to happen: \n" +
					"Should be able to move right, up or down if going to task board");
		}
		
	}
	
	private void obtainPlastic(){
		try {
			FactoryFrame.plasticAvailable.acquire(currentProduct.getNumPlasticNeeded());
			//update numPlastic in FactoryFrame so label displays correctly
			FactoryFrame.updateNumMaterialsRemaining(currentProduct.getNumPlasticNeeded(), Recipe.PLASTIC);
			plasticNeeded = false;
			currentDirection = MOVING_LEFT;
			currentYLoc = FactoryFrame.MATERIALS_Y + WORKER_H_N_W + 10;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainMetal(){
		try {
			FactoryFrame.metalAvailable.acquire(currentProduct.getNumMetalNeeded());
			//update nummetal in FactoryFrame so label displays correctly
			FactoryFrame.updateNumMaterialsRemaining(currentProduct.getNumMetalNeeded(), Recipe.METAL);
			metalNeeded = false;
			currentDirection = MOVING_LEFT;
			currentYLoc = FactoryFrame.MATERIALS_Y + WORKER_H_N_W + 10;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainWood(){
		try {
			FactoryFrame.woodAvailable.acquire(currentProduct.getNumWoodNeeded());
			//update numwood in FactoryFrame so label displays correctly
			FactoryFrame.updateNumMaterialsRemaining(currentProduct.getNumWoodNeeded(), Recipe.WOOD);
			woodNeeded = false;
			currentDirection = MOVING_LEFT;
			currentYLoc = FactoryFrame.MATERIALS_Y + WORKER_H_N_W + 10;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	
	//used both for obtaining and releasing semaphores on tools
	private void obtainScrewdrivers(){
		try {
			if(screwdriverReturned){
				FactoryFrame.screwdriversAvailable.acquire(currentRecipe.getNumScrewdrivers()); //blocking
				//update numscrewdrivers in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumScrewdrivers(), Recipe.SCREWDRIVER);
				screwdriverObtained = true;
				screwdriverReturned = false;
				System.out.println("Screwdriver Acquired");
			}
			else{
				FactoryFrame.screwdriversAvailable.release(currentRecipe.getNumScrewdrivers());
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumScrewdrivers(), Recipe.SCREWDRIVER);
				screwdriverObtained = false;
				screwdriverReturned = true;
				System.out.println("Screwdriver Returned");
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainScissors(){
		try {
			if(scissorsReturned){
				FactoryFrame.scissorsAvailable.acquire(currentRecipe.getNumScissors());
				//update numscissors in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumScissors(), Recipe.SCISSOR);
				scissorsObtained = true;
				scissorsReturned = false;
				System.out.println("Scissors Acquired");
			}
			else{
				FactoryFrame.scissorsAvailable.release(currentRecipe.getNumScrewdrivers());
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumScissors(), Recipe.SCISSOR);
				scissorsObtained = false;
				scissorsReturned = true;
				System.out.println("Scissors Returned");
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainPlyers(){
		try {
			if(plyersReturned){
				FactoryFrame.plyersAvailable.acquire(currentRecipe.getNumPliers());
				//update numplyers in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumPliers(), Recipe.PLIER);
				plyersObtained = true;
				plyersReturned = false;
				System.out.println("Plyers Acquired");
			}
			else{
				FactoryFrame.plyersAvailable.release(currentRecipe.getNumPliers());
				//update numplyers in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumPliers(), Recipe.PLIER);
				plyersObtained = false;
				plyersReturned = true;
				System.out.println("Plyers Released");
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainPaintbrushes(){
		try {
			if(paintbrushReturned){
				FactoryFrame.paintbrushesAvailable.acquire(currentRecipe.getNumPaintbrushes());
				//update numpaintbrushes in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumPaintbrushes(), Recipe.PAINTBRUSH);
				paintbrushObtained = true;
				paintbrushReturned = false;
				System.out.println("Paintbrushes Acquired");
			}
			else{
				FactoryFrame.paintbrushesAvailable.release(currentRecipe.getNumPaintbrushes());
				//update numpaintbrushes in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumPaintbrushes(), Recipe.PAINTBRUSH);
				paintbrushObtained = false;
				paintbrushReturned = true;
				System.out.println("Paintbrushes Released");
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainHammers(){
		try {
			if(hammerReturned){
				FactoryFrame.hammersAvailable.acquire(currentRecipe.getNumHammers());
				//update numhammers in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumHammers(), Recipe.HAMMER);
				hammerObtained = true;
				hammerReturned = false;
				System.out.println("Hammers Acquired");
			}
			else{
				FactoryFrame.hammersAvailable.release(currentRecipe.getNumHammers());
				//update numhammers in FactoryFrame so label displays correctly
				FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumHammers(), Recipe.HAMMER);
				hammerObtained = false;
				hammerReturned = true;
				System.out.println("Hammers Released");
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	
	
	
	private void getScrewdriver(){
		//get to screwdriver toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 40){ //if next to the toolbox
			obtainScrewdrivers();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
		}
	}
	
	private void getHammers(){
		//get to scissors toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 120 &&
				currentYLoc - SPEED > FactoryFrame.TOOLS_BEGIN_Y + 40){//if next to the toolbox
			obtainHammers();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 120 && canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
			currentDirection = MOVING_UP;
		}
		else{
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
	}
	
	private void getPaintbrushes(){
		//get to plyers toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 200 &&
				currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 120){//if next to the toolbox
			obtainPaintbrushes();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 200 && canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
			currentDirection = MOVING_UP;
		}
		else{
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
	}
	
	private void getPlyers(){
		//get to plyers toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 280 &&
				currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 200){ //if next to the toolbox
			obtainPlyers();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 280 && canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
			currentDirection = MOVING_UP;
		}
		else{
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
	}
	
	private void getScissors(){
		//get to plyers toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 360 &&
				currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 280){ //if next to the toolbox
			obtainScissors();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(canMoveDown()){							//move up if you can't move left
			currentYLoc += SPEED;
			currentDirection = MOVING_DOWN;
		}
	}
	
	private void getPlastic(){
		if(currentXLoc >= FactoryFrame.PLASTIC_X + WORKER_H_N_W  &&
				currentXLoc - SPEED < FactoryFrame.PLASTIC_X + WORKER_H_N_W &&
				currentYLoc + SPEED > FactoryFrame.MATERIALS_Y + WORKER_H_N_W){ //move to Plastic if almost there
			
			currentState = WAITING_FOR_MATERIAL;
			currentXLoc = FactoryFrame.PLASTIC_X + WORKER_H_N_W;
			currentYLoc = FactoryFrame.MATERIALS_Y;
			currentDirection = MOVING_DOWN;						//change direction to head to next destination
			obtainPlastic();
		}
		else if(MOVING_UP == currentDirection){
			if(canMoveUp()){ 				//try moving up
				currentYLoc -= SPEED;
			}
			else{							//otherwise move right if left of the plastic shed
				if(currentXLoc < FactoryFrame.PLASTIC_X + WORKER_H_N_W && canMoveRight()){
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
			}
		}
		else if(MOVING_RIGHT == currentDirection){ 
			if(canMoveUp()){							//continue up towards plastic
				currentXLoc += SPEED;
			}
			else{							//otherwise move right if left of the plastic shed
				if(currentXLoc < FactoryFrame.PLASTIC_X + WORKER_H_N_W && canMoveRight()) {						
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{									//move left if can't move right or up
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
			}
			
		}
		else if(MOVING_LEFT == currentDirection){
			if(canMoveUp()){						//try to move up
				currentDirection = MOVING_UP;
				currentYLoc -= SPEED;
			}
			else{
				currentXLoc -= SPEED;
			}
		}
		else{
			System.out.println("This ain't supposed to happen: \n" +
					"Should be able to move right, up if going to plastic board");
		}
	}
	
	private void getMetal(){
		if(currentXLoc >= FactoryFrame.METAL_X + WORKER_H_N_W  &&
				currentXLoc - SPEED < FactoryFrame.METAL_X + WORKER_H_N_W &&
				currentYLoc + SPEED > FactoryFrame.MATERIALS_Y + WORKER_H_N_W){ //move to METAL if almost there
			
			currentState = WAITING_FOR_MATERIAL;
			currentXLoc = FactoryFrame.METAL_X + WORKER_H_N_W;
			currentYLoc = FactoryFrame.MATERIALS_Y;
			currentDirection = MOVING_DOWN;						//change direction to head to next destination
			obtainMetal();
		}
		else if(MOVING_UP == currentDirection){
			if(canMoveUp()){ 				//try moving up
				currentYLoc -= SPEED;
			}
			else{							//otherwise move right if left of the METAL shed
				if(currentXLoc < FactoryFrame.METAL_X + WORKER_H_N_W && canMoveRight()){
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
			}
		}
		else if(MOVING_RIGHT == currentDirection){ 
			if(canMoveUp()){							//continue up towards METAL
				currentXLoc += SPEED;
			}
			else{							//otherwise move right if left of the METAL shed
				if(currentXLoc < FactoryFrame.METAL_X + WORKER_H_N_W && canMoveRight()) {						
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{									//move left if can't move right or up
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
			}
			
		}
		else if(MOVING_LEFT == currentDirection){
			if(canMoveUp()){						//try to move up
				currentDirection = MOVING_UP;
				currentYLoc -= SPEED;
			}
			else{
				currentXLoc -= SPEED;
			}
		}
		else{
			System.out.println("This ain't supposed to happen: \n" +
					"Should be able to move right, up if going to METAL board");
		}
	}
	
	private void getWood(){
		if(currentXLoc >= FactoryFrame.WOOD_X + WORKER_H_N_W  &&
				currentXLoc - SPEED < FactoryFrame.WOOD_X + WORKER_H_N_W &&
				currentYLoc + SPEED > FactoryFrame.MATERIALS_Y + WORKER_H_N_W){ //move to WOOD if almost there
			
			currentState = WAITING_FOR_MATERIAL;
			currentXLoc = FactoryFrame.WOOD_X + WORKER_H_N_W;
			currentYLoc = FactoryFrame.MATERIALS_Y;
			currentDirection = MOVING_DOWN;						//change direction to head to next destination
			obtainWood();
		}
		else if(MOVING_UP == currentDirection){
			if(canMoveUp()){ 				//try moving up
				currentYLoc -= SPEED;
			}
			else{							//otherwise move right if left of the WOOD shed
				if(currentXLoc < FactoryFrame.WOOD_X + WORKER_H_N_W && canMoveRight()){
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
			}
		}
		else if(MOVING_RIGHT == currentDirection){ 
			if(canMoveUp()){							//continue up towards WOOD
				currentXLoc += SPEED;
			}
			else{							//otherwise move right if left of the WOOD shed
				if(currentXLoc < FactoryFrame.WOOD_X + WORKER_H_N_W && canMoveRight()) {						
					currentXLoc += SPEED;
					currentDirection = MOVING_RIGHT;
				}
				else{									//move left if can't move right or up
					currentXLoc -= SPEED;
					currentDirection = MOVING_LEFT;
				}
			}
			
		}
		else if(MOVING_LEFT == currentDirection){
			if(canMoveUp()){						//try to move up
				currentDirection = MOVING_UP;
				currentYLoc -= SPEED;
			}
			else{
				currentXLoc -= SPEED;
			}
		}
		else{
			System.out.println("This ain't supposed to happen: \n" +
					"Should be able to move right, up if going to WOOD board");
		}
	}
	
	
	
	private boolean canMoveUp(){
		//can't move higher than materials, don't hit rows
		if(currentYLoc - SPEED < FactoryFrame.MATERIALS_Y + WORKER_H_N_W){ //can't move higher than materials
//			System.out.println("Can't move up b/c materials crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentYLoc - SPEED < FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W) && //don't hit row 1
				(currentXLoc + WORKER_H_N_W < FactoryFrame.ANVIL1_X) &&
				(currentXLoc > FactoryFrame.WORKBENCH3_X + WORKER_H_N_W)){
//			System.out.println("Can't move up b/c row 1 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentYLoc - SPEED < FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W) && //don't hit row 2
				(currentXLoc + WORKER_H_N_W < FactoryFrame.ANVIL1_X) &&
				(currentXLoc > FactoryFrame.WORKBENCH3_X + WORKER_H_N_W)){
//			System.out.println("Can't move up b/c row 2 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentYLoc - SPEED < FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W) && //don't hit row 3
				(currentXLoc + WORKER_H_N_W < FactoryFrame.ANVIL1_X) &&
				(currentXLoc > FactoryFrame.WORKBENCH3_X + WORKER_H_N_W)){
//			System.out.println("Can't move up b/c row 3 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		
		return true;
	}
	
	private boolean canMoveDown(){
		//can't move lower than rows
		if((currentYLoc + SPEED + WORKER_H_N_W > FactoryFrame.WORKBENCHES_AND_ANVILS_Y ) && //don't hit row 1
				(currentXLoc + WORKER_H_N_W < FactoryFrame.ANVIL1_X) &&
				(currentXLoc > FactoryFrame.WORKBENCH3_X + WORKER_H_N_W)){
//			System.out.println("Can't move down b/c row 1 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentYLoc + SPEED + WORKER_H_N_W > FactoryFrame.SAWS_AND_FURNACES_Y) && //don't hit row 2
				(currentXLoc + WORKER_H_N_W < FactoryFrame.ANVIL1_X) &&
				(currentXLoc > FactoryFrame.WORKBENCH3_X + WORKER_H_N_W)){
//			System.out.println("Can't move down b/c row 2 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentYLoc + SPEED + WORKER_H_N_W > FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y) && //don't hit row 3
				(currentXLoc + WORKER_H_N_W < FactoryFrame.ANVIL1_X) &&
				(currentXLoc > FactoryFrame.WORKBENCH3_X + WORKER_H_N_W)){
//			System.out.println("Can't move down b/c row 3 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		
		return true;
	}
	
	private boolean canMoveRight(){
		if((currentXLoc + SPEED + WORKER_H_N_W > FactoryFrame.WOOD_X) &&
				(currentYLoc < FactoryFrame.MATERIALS_Y + WORKER_H_N_W)){ 			//don't crash into materials
//			System.out.println("Can't move right b/c materials crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc + SPEED + WORKER_H_N_W > FactoryFrame.ANVIL1_X) &&		//don't crash into 1st row
				((currentYLoc + WORKER_H_N_W > FactoryFrame.WORKBENCHES_AND_ANVILS_Y) &&   //from top of row
				(currentYLoc < FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W))){    //from bottom of row
//			System.out.println("Can't move right b/c row 1 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc + SPEED + WORKER_H_N_W > FactoryFrame.FURNACE1_X) &&		//don't crash into 2nd row
				((currentYLoc + WORKER_H_N_W > FactoryFrame.SAWS_AND_FURNACES_Y) &&   //from top of row
				(currentYLoc < FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W))){    //from bottom of row
//			System.out.println("Can't move right b/c row 2 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc + SPEED + WORKER_H_N_W > FactoryFrame.PAINTING_STATION1_X) &&		//don't crash into 3rd row
				((currentYLoc + WORKER_H_N_W > FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y) &&   //from top of row
				(currentYLoc < FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W))){    //from bottom of row
//			System.out.println("Can't move right b/c row 3 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc + WORKER_H_N_W + SPEED > 600)){		//don't crash into Taskboard
			return false;
		}
		//free to move right!!!
		return true;
	}
	
	private boolean canMoveLeft(){
		if((currentXLoc - SPEED < FactoryFrame.PLASTIC_X + WORKER_H_N_W) &&
				(currentYLoc < FactoryFrame.MATERIALS_Y + WORKER_H_N_W)){ 			//don't crash into materials
//			System.out.println("Can't move left b/c materials crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc - SPEED < FactoryFrame.WORKBENCH3_X + WORKER_H_N_W) &&		//don't crash into 1st row
				((currentYLoc + WORKER_H_N_W > FactoryFrame.WORKBENCHES_AND_ANVILS_Y) &&   //from top of row
				(currentYLoc < FactoryFrame.WORKBENCHES_AND_ANVILS_Y + WORKER_H_N_W))){    //from bottom of row
//			System.out.println("Can't move left b/c row 1 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc - SPEED < FactoryFrame.FURNACE1_X + WORKER_H_N_W) &&		//don't crash into 2nd row
				((currentYLoc + WORKER_H_N_W > FactoryFrame.SAWS_AND_FURNACES_Y) &&   //from top of row
				(currentYLoc < FactoryFrame.SAWS_AND_FURNACES_Y + WORKER_H_N_W))){    //from bottom of row
//			System.out.println("Can't move left b/c row 2 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc - SPEED < FactoryFrame.PAINTING_STATION1_X + WORKER_H_N_W) &&		//don't crash into 3rd row
				((currentYLoc + WORKER_H_N_W > FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y) &&   //from top of row
				(currentYLoc < FactoryFrame.PAITNING_STATIONS_AND_PRESS_Y + WORKER_H_N_W))){    //from bottom of row
//			System.out.println("Can't move left b/c row 3 crash: X=" + currentXLoc + "  Y=" + currentYLoc);
			return false;
		}
		else if((currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W)){			//don't crash into tools on left
			return false;
		}
		//free to move left!!!
		return true;
	}


	public int getXLocation(){
		return this.currentXLoc;
	}
	
	public int getYLocation(){
		return this.currentYLoc;
	}
	
	public void setX(int nX){
		this.currentXLoc = nX;
	}
	
	public void setY(int nY){
		this.currentYLoc = nY;
	}
	
	
	public short getCurrentState(){
		return this.currentState;
	}
	
	public void changeState(short newState){
		this.currentState = newState;
	}

}
