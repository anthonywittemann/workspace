package aw;

/**
 * 
 * @author anthonywittemann
 *
 */

public class Recipe {
	//TOOLS
	public static final short HAMMER = 0;
	public static final short PLIER = 1;
	public static final short SCREWDRIVER = 2;
	public static final short PAINTBRUSH = 3;
	public static final short SCISSOR = 4;
	
	private short numHammer;
	private short numPlier;
	private short numScrewdriver;
	private short numPaintbrush;
	private short numScissor;
	
	//STATIONS
	public static final short SAW = 10;
	public static final short PRESS = 11;
	public static final short WORKBENCH = 12;
	public static final short PAINTING_STATION = 13;
	public static final short ANVIL = 14;
	public static final short FURNACE = 15;
	
	public static final short WOOD = 100;
	public static final short METAL = 101;
	public static final short PLASTIC = 102;
	
	
	private short totalTimeAtStation = 0; 
	private short station = -1;
	
	private boolean isComplete = false;
	
	private short keyCode;
	
	public Recipe(short hammers, short pliers, short screwdrivers, short paintbrushes, short scissors, short totalTime, short station){
		this.numHammer = hammers;
		this.numPlier = pliers;
		this.numScrewdriver = screwdrivers;
		this.numScissor = scissors;
		this.numPaintbrush = paintbrushes;
		
		this.totalTimeAtStation = (short) (totalTime * 1000); //is passed in seconds, needs to be converted to timer ticks
		this.station = station;
	}
	
	
	public void complete(){
		this.isComplete = true;
	}
	
	public boolean isComplete(){
		return this.isComplete;
	}
	
	public short getNumHammers(){
		return this.numHammer;
	}
	
	public short getNumPliers(){
		return this.numPlier;
	}
	
	public short getNumScrewdrivers(){
		return this.numScrewdriver;
	}
	public short getNumPaintbrushes(){
		return this.numPaintbrush;
	}
	public short getNumScissors(){
		return this.numScissor;
	}
	
	public boolean usesHammer(){
		return (this.numHammer > 0);
	}
	
	public boolean usesScissors(){
		return (this.numScissor > 0);
	}
	
	public boolean usesScrewdriver(){
		return (this.numScrewdriver > 0);
	}
	
	public boolean usesPaintbrush(){
		return (this.numPaintbrush > 0);
	}
	
	public boolean usesPlyers(){
		return (this.numPlier > 0);
	}
	
	public short getTotalTime(){
		return this.totalTimeAtStation;
	}
	
	public short getStation(){
		return this.station;
	}
	
	public String getStationString(){
		if(station == SAW){
			return "saw";
		}
		else if(station == PRESS){
			return "press";
		}
		else if(station == WORKBENCH){
			return "workbench";
		}
		else if(station == PAINTING_STATION){
			return "painting station";
		}
		else if(station == ANVIL){
			return "anvil";
		}
		else if(station == FURNACE){
			return "furnace";
		}
		return "Shit, dis ain't da right station";
	}
	
	public void setKeyCode(short kc){
		this.keyCode = kc;
	}
	
	public short getKeyCode(){
		return this.keyCode;
	}
	
	public String toString(){
		return "# Hammers: " + this.numHammer + "\t# Pliers: " + this.numPlier + 
				"\t# Screwdrivers: " + this.numScrewdriver + "\t# Paintbrushes: " + this.numPaintbrush + 
				"\t# Scissors: " + this.numScissor + "\tTotal Time: " + this.totalTimeAtStation + 
				"\tStation: " + this.getStationString();
	}

}
