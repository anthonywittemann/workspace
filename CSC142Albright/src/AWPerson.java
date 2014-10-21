/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 7 - Person and Customer
 * HW Due 4/15/14
 */
public class AWPerson {
	private String name;
	private String address;
	private long teleNum; //10 digits long
	
	public AWPerson(){
		name = "";
		address = "";
		teleNum = 0;
	}
	
	public AWPerson(String n, String a, long t){
		name = n;
		address = a;
		teleNum = t;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public long getTeleNum(){
		return teleNum;
	}
	
	public void setName(String nN){
		name = nN;
	}
	
	public void setAddress(String nA){
		address = nA;
	}
	
	public void setTeleNum(long nTN){
		teleNum = nTN;
	}
	
	public String toString(){
		return "Name: " + name + "\nAddress: " + address + "Telephone Number: " + teleNum;
		
	}
}
