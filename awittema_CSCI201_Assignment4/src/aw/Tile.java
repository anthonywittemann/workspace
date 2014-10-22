package aw;

public class Tile {
	
	private char type; //can be B (Blank), I, L, T, P (Plus)
	private char row; //location in the 9x9 grid
	private int column;
	private int rotation; //from 0-360
	
	public Tile(char type, char row, int column, int rotation){
		this.type = type;
		this.row = row;
		this.column = column;
		this.rotation = rotation;
	}
	
	
	public void setType(char nType){
		this.type = nType;
	}
	
	public void setRow(char nR){
		this.row = nR;
	}
	
	public void setColumn(int nC){
		this.column = nC;
	}
	
	public void setRotation(int nRotation){
		this.rotation = nRotation;
	}
	
	
	public char getType(){
		return this.type;
	}
	
	public char getRow(){
		return this.row;
	}
	
	public int getColumn(){
		return this.column;
	}
	
	public int getRotation(){
		return this.rotation;
	}
}
