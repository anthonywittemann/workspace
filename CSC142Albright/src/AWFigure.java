/** 
 * @author Anthony Wittemann
 * Figure Abstract Class
 * HW Due 4/22/14
 */
public abstract class AWFigure {
	protected double dim1;
	protected double dim2;
	
	protected abstract void area();
	
	protected void setDim1(double nD1){
		dim1 = nD1;
	}
	
	protected void setDim2(double nD2){
		dim2 = nD2;
	}
	
	public double getDim1(){
		return dim1;
	}
	
	public double getDim2(){
		return dim2;
	}
	
	public abstract String toString();
}
