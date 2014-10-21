/** 
 * @author Anthony Wittemann
 * Chapter 3 Program 2 Car Class
 * HW Due 2/11/14
 */
public class AWCar {
	private int yearModel;
	private String make;
	private int speed;
	
	/**
	 * 
	 * @param ym - car’s year model
	 * @param m - make of the car
	 */
	public AWCar(int ym, String m){
		yearModel = ym;
		make = m;
		speed = 0;
	}
	
	public AWCar(){
		
	}
	
	public int getYearModel(){
		return yearModel;
	}
	
	public String getMake(){
		return make;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	
	public void accelerate(){
		speed += 5;
	}
	
	public void brake(){
		speed -= 5;
	}
}
