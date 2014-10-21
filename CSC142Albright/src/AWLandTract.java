/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 4 Land Tract
 * HW Due 3/11/14 & HW Due 3/13/14
 */
public class AWLandTract {
	private int length;
	private int width;
	
	public AWLandTract(){
		length = 0;
		width = 0;
	}
	
	public AWLandTract(int l, int w){
		length = l;
		width = w;
	}
	
	/**
	 * Copy Constructor
	 * @param lt instance of another land tract class
	 */
	public AWLandTract(AWLandTract lt){
		this(lt.getLength(), lt.getWidth());
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setWidth(int nW){
		width = nW;
	}
	
	public void setLength(int nL){
		length = nL;
	}
	
	public int getArea(){
		return length * width;
	}
	
	public String toString(){
		return "The dimensions of this tract of land are " + length + " x " + width;
	}
	
	public boolean equals(Object tract2){
		return (((AWLandTract) tract2).getArea() == getArea());
	}
}
