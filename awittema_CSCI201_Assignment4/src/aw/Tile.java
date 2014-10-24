package aw;

public class Tile {
	
	private String type; //can be B (Blank), I, L, T, P (Plus)
	private char row; //location in the 9x9 grid
	private int column;
	private int rotation; //from 0-360
	
	public Tile(String type, String row, String column, String rotation){
		this.type = type;
		this.row = row.charAt(0);
		this.column = Integer.parseInt(column);
		this.rotation = Integer.parseInt(rotation);
	}
	
	
	public void setType(String nType){
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
	
	
	public String getType(){
		return this.type;
	}
	//row is the y coordinate
	public char getRow(){
		return this.row;
	}
	//column is the x coordinate
	public int getColumn(){
		return this.column;
	}
	
	public int getRotation(){
		return this.rotation;
	}
	
	public String toString(){
		return "Type: " + type + "   Row: " + row + "   Column: " + column + "   Rotation: " + rotation;
	}
}
