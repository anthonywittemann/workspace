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
	
	public static final int SPEED = 10; //10 pixels either up, down, left or right
	
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
	
	
	private void discernRequiredTools(){
		if(currentRecipe.usesPlyers()){
			plyersNeeded = true;
		}
		if(currentRecipe.usesScissors()){
			scissorsNeeded = true;
		}
		if(currentRecipe.usesPaintbrush()){
			paintbrushNeeded = true;
		}
		if(currentRecipe.usesHammer()){
			hammerNeeded = true;
		}
		if(currentRecipe.usesScrewdriver()){
			screwdriverNeeded = true;
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
		//TODO implement this to get materials and then move to tool shed or next material
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
			}
		}
		else if(currentState == MOVING_TO_TOOL_SHED){
			//grab tools necessary for only 1 step at a time
			currentRecipe = currentProduct.getNextInstruction();
			if(currentRecipe != null){
				//TODO follow recipe
				discernRequiredTools();
				if(screwdriverNeeded){
					getScrewdriver();
				}
				else if(hammerNeeded){
					getHammers();
				}
				else if(paintbrushNeeded){
					getPaintbrushes();
				}
				else if(plyersNeeded){
					getPlyers();
				}
				else if(scissorsNeeded){
					getScissors();
				}
			}
			else{
				currentState = MOVING_TO_WORK_AREA;
				currentDirection = MOVING_LEFT;
			}
		}
		else if(currentState == WAITING_FOR_TOOL){
			//TODO make sure to recognize semaphores for obtaining tools
			
			
		}
		else if(currentState == MOVING_TO_WORK_AREA){
			//TODO create methods to navigate to work areas
			if(currentRecipe.getStation() == Recipe.ANVIL){
				getToAnvils();
			}
			else if(currentRecipe.getStation() == Recipe.FURNACE){
				getToFurnaces();
			}
			else if(currentRecipe.getStation() == Recipe.PAINTING_STATION){
				getToPaintingStations();
			}
			else if(currentRecipe.getStation() == Recipe.WORKBENCH){
				getToWorkBenches();
			}
			else if(currentRecipe.getStation() == Recipe.SAW){
				getToSaws();
			}
			else if(currentRecipe.getStation() == Recipe.PRESS){
				getToPress();
			}
			else{
				System.out.println("Damn it, wrong station code");
			}	
			
		}
		else if(currentState == WAITING_FOR_WORK_AREA){
			//TODO obtain lock for Work Area
			
		}
		else if(currentState == DONE_WITH_CURRENT_PRODUCT){
			//TODO return tools to toolbox
			//TODO return lock on work area
			
			currentState = MOVING_TO_TOOL_SHED;
		}
		else if(currentState == RETURNING_TO_TASK_BOARD){
			moveToTaskBoard();
		}
		
	}
	
	
	private void getToPress() {
		// TODO Auto-generated method stub
		
	}


	private void getToSaws() {
		// TODO Auto-generated method stub
		
	}


	private void getToWorkBenches() {
		// TODO Auto-generated method stub
		
	}


	private void getToPaintingStations() {
		// TODO Auto-generated method stub
		
	}


	private void getToFurnaces() {
		// TODO Auto-generated method stub
		
	}


	private void getToAnvils() {
		// TODO Auto-generated method stub
		
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
	
	
	
	private void obtainScrewdrivers(){
		try {
			FactoryFrame.screwdriversAvailable.acquire(currentRecipe.getNumScrewdrivers());
			//update numscrewdrivers in FactoryFrame so label displays correctly
			FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumScrewdrivers(), Recipe.SCREWDRIVER);
			screwdriverNeeded = false;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainScissors(){
		try {
			FactoryFrame.scissorsAvailable.acquire(currentRecipe.getNumScissors());
			//update numscissors in FactoryFrame so label displays correctly
			FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumScissors(), Recipe.SCISSOR);
			scissorsNeeded = false;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainPlyers(){
		try {
			FactoryFrame.plyersAvailable.acquire(currentRecipe.getNumPliers());
			//update numplyers in FactoryFrame so label displays correctly
			FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumPliers(), Recipe.PLIER);
			plyersNeeded = false;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainPaintbrushes(){
		try {
			FactoryFrame.paintbrushesAvailable.acquire(currentRecipe.getNumPaintbrushes());
			//update numpaintbrushes in FactoryFrame so label displays correctly
			FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumPaintbrushes(), Recipe.PAINTBRUSH);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private void obtainHammers(){
		try {
			FactoryFrame.hammersAvailable.acquire(currentRecipe.getNumHammers());
			//update numhammers in FactoryFrame so label displays correctly
			FactoryFrame.updateNumToolsRemaining(currentRecipe.getNumHammers(), Recipe.HAMMER);
			hammerNeeded = false;
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	
	
	
	private void getScrewdriver(){
		//get to screwdriver toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y){ //if next to the toolbox
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
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 80 &&
				currentYLoc - SPEED > FactoryFrame.TOOLS_BEGIN_Y + 40){//if next to the toolbox
			obtainHammers();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
		}
	}
	
	private void getPaintbrushes(){
		//get to plyers toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 160 &&
				currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 120){//if next to the toolbox
			obtainPaintbrushes();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
		}
	}
	
	private void getPlyers(){
		//get to plyers toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 240 &&
				currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 200){ //if next to the toolbox
			obtainPlyers();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
		}
	}
	
	private void getScissors(){
		//get to plyers toolbox from either materials or workstation
		if(currentXLoc - SPEED < FactoryFrame.TOOLS_X + WORKER_H_N_W &&
				currentYLoc - SPEED < FactoryFrame.TOOLS_BEGIN_Y + 320 &&
				currentYLoc > FactoryFrame.TOOLS_BEGIN_Y + 280){ //if next to the toolbox
			obtainScissors();
		}
		else if(canMoveLeft()){ 						//try to move left
			currentXLoc -= SPEED;
			currentDirection = MOVING_LEFT;
		}
		else if(canMoveUp()){							//move up if you can't move left
			currentYLoc -= SPEED;
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
