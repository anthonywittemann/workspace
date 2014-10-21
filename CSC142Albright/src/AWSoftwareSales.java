/** 
 * @author Anthony Wittemann
 * Chapter 4 Program 4 Software Sales
 * HW Due 2/13/14
 */
public class AWSoftwareSales {
	private final int PACKAGE_PRICE = 99;
	private int unitsSold;
	
	public AWSoftwareSales(int nus){
		unitsSold = nus;
	}
	
	public void modifyUnitsSold(int newUnitsSold){
		unitsSold += newUnitsSold;
	}
	
	public int getUnitsSold(){
		return unitsSold;
	}
	
	public int getPackagePrice(){
		return PACKAGE_PRICE;
	}
	
	public double getPurchaseCost(){
		if(unitsSold < 10){
			return unitsSold * PACKAGE_PRICE;
		}
		else if(unitsSold < 20){
			return unitsSold * PACKAGE_PRICE * .80; 
		}
		else if(unitsSold < 50){
			return unitsSold * PACKAGE_PRICE * .70; 
		}
		else if(unitsSold < 100){
			return unitsSold * PACKAGE_PRICE * .60; 
		}
		return unitsSold * PACKAGE_PRICE * .50; 
	}
	
}
