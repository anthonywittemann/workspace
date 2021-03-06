package aw;

import java.util.ArrayList;

/**
 * 
 * @author anthonywittemann
 *
 */

public class Product {
	public static final short NOT_BUILT = 0;
	public static final short IN_PROGRESS = 1;
	public static final short COMPLETE = 2;
	
	private String productName;
	private int numWoodNeeded;
	private int numMetalNeeded;
	private int numPlasticNeeded;
	private ArrayList<Recipe> instructions;
	private short buildStatus;
	private short keyCode;
	
	private short recipeKeyCodeGenerator = 0;
	
	public Product(String productName, int numWoodNeeded, int numMetalNeeded, int numPlasticNeeded, ArrayList<Recipe> instructions){
		this.productName = productName;
		this.numWoodNeeded = numWoodNeeded;
		this.numMetalNeeded = numMetalNeeded;
		this.numPlasticNeeded = numPlasticNeeded;
		this.instructions = instructions;
		this.buildStatus = NOT_BUILT;
	}
	
	
	public Recipe getNextInstruction(){
		for(Recipe r: instructions){
			if(!r.isComplete()){
				r.setKeyCode(recipeKeyCodeGenerator);
				recipeKeyCodeGenerator++;
				return r;
			}
		}
		return null;
	}
	
	public Recipe instructionComplete(Recipe completeInstruction){
		for(Recipe r: instructions){
			if(completeInstruction.getKeyCode() == r.getKeyCode()){
				r.complete();
			}
		}
		//assign the next recipe/instruction
		for(Recipe r1: instructions){
			if(!r1.isComplete()){
				r1.setKeyCode(recipeKeyCodeGenerator);
				recipeKeyCodeGenerator++;
				return r1;
			}
		}
		//if all recipes are done let the main vector in FactoryFrame know so TA can be updated
		this.buildStatus = COMPLETE;
		FactoryFrame.productFinished(this);
		return null;
	}
	
	public int getNumWoodNeeded(){
		return this.numWoodNeeded;
	}
	
	public int getNumMetalNeeded(){
		return this.numMetalNeeded;
	}
	
	public int getNumPlasticNeeded(){
		return this.numPlasticNeeded;
	}
	
	public String getProductName(){
		return this.productName;
	}
	
	public short getBuildStatus(){
		return this.buildStatus;
	}
	
	public String getBuildStatusString(){
		if(this.buildStatus == NOT_BUILT){
			return "Not Built";
		}
		else if(this.buildStatus == IN_PROGRESS){
			return "In Progress";
		}
		else if(this.buildStatus == COMPLETE){
			return "Complete";
		}
		return "BROKEN!!!!";
	}
	
	public short getKeyCode(){
		return this.keyCode;
	}
	
	public void setKeyCode(short keyCode){
		this.keyCode = keyCode;
	}
	
	public void changeBuildStatus(short nBuildStatus){
		this.buildStatus = nBuildStatus;
	}

}
