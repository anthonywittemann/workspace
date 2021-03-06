package aw;

import java.awt.Color;

public class Car {
	private Color color;
	private double speed; //given in squares/second
	private int xLocation;
	private char yLocation;
	private int carNumber;
	private short numBlinks; //keeps track of the current number of blinks before moving
	private static final short MAX_BLINKS = 3;
	private boolean blink;
	
	private ParentAI carAI;
	
	private short tileType; //can be 1: blank, 2: I, 3: L, 4: T, 5: +
	private int tileRotation; //can be 0, 90, 180, 270
	
	public Car(String color, String ai, String speed, String xLocation, String yLocation, int carNumber, short tileType, int rotation){
		this.color = mapColor(color);
		this.speed = Double.parseDouble(speed);
		this.xLocation = Integer.parseInt(xLocation);
		this.yLocation = yLocation.charAt(0);
		this.carNumber = carNumber;
		blink = false;
		numBlinks = 0;
		//just giving these default values, will be updated in setTileAndRotation
		this.tileType = tileType;
		this.tileRotation = rotation;
		
		//instantiate car AIs with proper data
		short sAI = Short.parseShort(ai);
		if(sAI == 1){
			carAI = new AI1(this);
		}
		else if(sAI == 2){
			carAI = new AI2(this);
		}
		else if(sAI == 3){
			carAI = new AI3(this);
		}
		else if(sAI == 4){
			carAI = new AI4(this);
		}
	}
	
	
	
	private Color mapColor(String c){
		if(c.equalsIgnoreCase("red")){
			return Color.RED;
		}
		else if(c.equalsIgnoreCase("orange")){
			return Color.ORANGE;
		}
		else if(c.equalsIgnoreCase("yellow")){
			return Color.YELLOW;
		}
		else if(c.equalsIgnoreCase("green")){
			return Color.GREEN;
		}
		else if(c.equalsIgnoreCase("blue")){
			return Color.BLUE;
		}
		else if(c.equalsIgnoreCase("white")){
			return Color.WHITE;
		}
		else if(c.equalsIgnoreCase("black")){
			return Color.BLACK;
		}
		else if(c.equalsIgnoreCase("cyan")){
			return Color.CYAN;
		}
		else if(c.equalsIgnoreCase("magenta")){
			return Color.MAGENTA;
		}
		else if(c.equalsIgnoreCase("dark gray") || c.equalsIgnoreCase("dark_gray")){
			return Color.DARK_GRAY;
		}
		else if(c.equalsIgnoreCase("light gray") || c.equalsIgnoreCase("light_gray")){
			return Color.LIGHT_GRAY;
		}
		else if(c.equalsIgnoreCase("gray")){
			return Color.GRAY;
		}
		else if(c.equalsIgnoreCase("pink")){
			return Color.PINK;
		}
		return Color.WHITE;
		
	}
	
	
	public boolean getBlink(){ 
		return blink;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public ParentAI getAI(){
		return this.carAI;
	}
	
	public double getSpeed(){
		return this.speed;
	}

	public int getXLocation(){
		return this.xLocation;
	}
	
	public char getYLocation(){
		return this.yLocation;
	}
	
	public int getCarNumber(){
		return this.carNumber;
	}
	
	public short getTileType(){
		return this.tileType;
	}
	
	public int getTileRotation(){
		return this.tileRotation;
	}
	
	//used to update location when car moves
	public void setTileAndRotation(short tileType, int rotation){
		this.tileType = tileType;
		this.tileRotation = rotation;
		carAI.setTileType(tileType);
		carAI.setTileRotation(rotation);
	}
	
	//moves after max number of blink/box
	public void toggleBlink(){
		
		if(numBlinks == MAX_BLINKS){
//			System.out.println("DEBUG: Car " + carNumber + " MOVE!!!!");
			carAI.findValidMoveDirections();
			carAI.move(); 
			
			numBlinks = 0; //reset blink counter
		}
		numBlinks++;
		blink = !blink;
	}
	
	public void setColor(Color nC){
		this.color = nC;
	}
	
	public void setAI(ParentAI nAI){
		this.carAI = nAI;
	}
	
	public void setSpeed(double nS){
		this.speed = nS;
	}
	
	public void setXLocation(int nXL){
		this.xLocation = nXL;
	}
	
	public void setYLocation(char nYL){
		this.yLocation = nYL;
	}
	
	public void setCarNumber(int nCN){
		this.carNumber = nCN;
	}
	
	public String toString(){
		return "Car Number: " + carNumber + "   Color: " + color + "   Row: " + xLocation + "   Column: " + yLocation + "   Speed: " + speed + "   AI: " + carAI.toString();
	}
}
