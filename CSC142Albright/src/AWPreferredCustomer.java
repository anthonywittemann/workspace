/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 8 - Preferred Customer
 * HW Due 4/17/14
 */
public class AWPreferredCustomer extends AWCustomer{
	private double totalPurchaseAmnt;
	private double discountRate;

	public AWPreferredCustomer(){
		super();
		totalPurchaseAmnt = 0;
		discountRate = 0;
	}
	
	public AWPreferredCustomer(String n, String a, long t){
		super(n,a,t);
		totalPurchaseAmnt = 0;
		discountRate = 0;
	}
	
	public AWPreferredCustomer(String n, String a, long t, int cn, boolean oml){
		super(n,a,t, cn, oml);
		totalPurchaseAmnt = 0;
		discountRate = 0;
	}
	
	public AWPreferredCustomer(String n, String a, long t, int cn, boolean oml, double tpa){
		super(n,a,t, cn, oml);
		totalPurchaseAmnt = tpa;
		calculateDiscountRate();
	}
	
	public double getDiscountRate(){
		return discountRate;
	}
	
	public double getTotalPurchaseAmnt(){
		return totalPurchaseAmnt;
	}
	
	public void setDiscountRate(double nDR){
		discountRate = nDR;
	}
	
	public void setTotalPurchaseAmnt(double nTPA){
		totalPurchaseAmnt = nTPA;
		calculateDiscountRate();
	}
	
	public void calculateDiscountRate(){
		discountRate = 0;
		double tpa = totalPurchaseAmnt;
		if(tpa >= 2000){
			discountRate = .1;
		}
		else if(tpa >= 1500){
			discountRate = .07;
		}
		else if(tpa >= 1000){
			discountRate = .06;
		}
		else if(tpa >= 500){
			discountRate = .05;
		}
	}
	
	public String toString(){
		double perc = discountRate * 100;
		return "Total Amount Purchased: $" + totalPurchaseAmnt + "\nDiscount Rate: %" + perc;
	}
	
}
