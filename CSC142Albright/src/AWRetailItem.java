/** 
 * @author Anthony Wittemann
 * Chapter 3 Program 4 Retail Items
 * HW Due 2/11/14
 */
public class AWRetailItem {
	private String description;
	private double price;
	private int unitsOnHand;
	
	public AWRetailItem(String descr, double pri, int UOH) throws NegativePriceException, NegativeUnitsException{
		description = descr;
		if(pri < 0)
			throw new NegativePriceException();
		else
			price = pri;		
		if(UOH < 0)
			throw new NegativeUnitsException();
		else
			unitsOnHand = UOH;
	}
	
	public void setDescription(String nDes){
		description = nDes;
	}
	
	public void setPrice(double nPri) throws NegativePriceException{
		if(nPri < 0)
			throw new NegativePriceException();
		else
			price = nPri;
	}
	
	public void setUnitsOnHand(int nUOH) throws NegativeUnitsException{
		if(nUOH < 0)
			throw new NegativeUnitsException();
		else
			unitsOnHand = nUOH;
	}
	
	public String getDescription(){
		return description;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getUnitsOnHand(){
		return unitsOnHand;
	}

	
}
