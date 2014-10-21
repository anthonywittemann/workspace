/** 
 * @author Anthony Wittemann
 * Figure Abstract Class
 * HW Due 4/22/14
 */
public class AWTriangle extends AWFigure{
	private double area;
	
	public AWTriangle() {
		super.dim1 = 0;
		super.dim2 = 0;
		area();
	}

	public AWTriangle(double d1, double d2) {
		super.dim1 = d1;
		super.dim2 = d2;
		area();
	}

	@Override
	protected void area() {
		area = .5 * super.dim1 * super.dim2;
	}
	
	public void setDim1(double nD1){
		super.setDim1(nD1);
		area();
	}
	
	public void setDim2(double nD2){
		super.setDim2(nD2);
		area();
	}
	
	public double getArea(){
		return area;
	}

	@Override
	public String toString() {
		return "Dimension 1: " + super.dim1 + "\tDimension 2: " + super.dim2 + "\tArea: " + area;
	}

}