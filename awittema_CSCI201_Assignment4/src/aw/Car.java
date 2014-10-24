package aw;

public class Car {
	//TODO figure out whether speed, x,y Location should be strings or ints/chars
	private String color;
	private String ai;
	private double speed;
	private int xLocation;
	private char yLocation;
	
	public Car(String color, String ai, String speed, String xLocation, String yLocation){
		this.color = color;
		this.ai = ai;
		this.speed = Double.parseDouble(speed);
		this.xLocation = Integer.parseInt(xLocation);
		this.yLocation = yLocation.charAt(0);
	}
	
	
	public String getColor(){
		return this.color;
	}
	
	public String getAI(){
		return this.ai;
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
	
	public void setColor(String nC){
		this.color = nC;
	}
	
	public void setAI(String nAI){
		this.ai = nAI;
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
	
	public String toString(){
		return "Car Color: " + color + "   Row: " + xLocation + "   Column: " + yLocation + "   Speed: " + speed + "   AI: " + ai;
	}
}
