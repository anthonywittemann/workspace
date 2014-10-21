/** 
 * @author Anthony Wittemann
 * Chapter 3 Program 2 Car Class
 * HW Due 2/11/14
 */
public class AWCarDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AWCar car1 = new AWCar(2000,"Honda");
		
		for(int i = 0; i < 5; i ++){
			car1.accelerate();
			System.out.println("Current speed: " + car1.getSpeed());
		}

		for(int j = 0; j < 5; j ++){
			car1.brake();
			System.out.println("Current speed: " + car1.getSpeed());
		}
	}

}
