/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 7 - Person and Customer
 * HW Due 4/15/14
 */
public class AWCustomer extends AWPerson{
	private int custNum;
	private boolean onMailingList;
	
	public AWCustomer(){
		super();
		custNum = -1;
		onMailingList = false;
	}
	
	public AWCustomer(String n, String a, long t){
		super(n,a,t);
		custNum = -1;
		onMailingList = false;
	}
	
	public AWCustomer(String n, String a, long t, int cn, boolean oml){
		super(n,a,t);
		custNum = cn;
		onMailingList = oml;
	}
	
	public int getCustNum(){
		return custNum;
	}
	
	public boolean isOnMailingList(){
		return onMailingList;
	}
	
	public void setCustNum(int nCN){
		custNum = nCN;
	}
	
	public void toggleOnMailingList(){
		onMailingList =! onMailingList;
	}
	
	public String toString(){
		if(onMailingList){
			return "Customer number: " + custNum + "\nOn Mailing List";
		}
		return "Customer number: " + custNum + "\nNot on Mailing List";
	}
}
