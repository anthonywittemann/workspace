/** 
 * @author Anthony Wittemann
 * Chapter 3 Program 9 Pet
 * In class 2/6/14
 */
public class AWPet {
	private String name;
	private int age;
	private String type;
	
	public AWPet(){
		
	}
	
	/** 
	 * 
	 * @param n - Name of the Pet
	 * @param a - Age of the Pet
	 * @param t - Type (breed) of the Pet
	 */
	public AWPet(String n, int a, String t){
		name = n;
		age = a;
		type = t;
	}
	
	
	/**
	 * 
	 * @param newName  - changes the name of the Pet
	 */
	public void setName(String newName){
		name = newName;
	}
	
	/**
	 * 
	 * @param newAge  - changes the Age of the Pet
	 */
	public void setAge(int newAge){
		age = newAge;
	}
	
	/**
	 * 
	 * @param newType - changes the type of the Pet
	 */
	public void settype(String newType){
		type = newType;
	}
	
	
	
	/**
	 * 
	 * @return returns the name of the Pet
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return returns the age of the Pet
	 */
	public int getAge(){
		return age;
	}

	/**
	 * 
	 * @return returns the type of the Pet
	 */
	public String getType(){
		return type;
	}

}
