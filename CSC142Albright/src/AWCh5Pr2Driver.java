import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Chapter 5 Program 2 Distance Traveled
 * HW Due 2/25/14
 */

public class AWCh5Pr2Driver {


	public static void main(String[] args) {
		boolean isNotValidInput = true;
		int hrs, speed;
		
		do{
			hrs = Integer.parseInt(JOptionPane.showInputDialog("How many hours has your vehicle traveled?"));
			speed = Integer.parseInt(JOptionPane.showInputDialog("What was the average speed of the vehicle?"));
			if(hrs > 0 && speed > 0){
				isNotValidInput = false;
			}
		} while(isNotValidInput);
		
		AWDistanceTraveled dist = new AWDistanceTraveled();
		System.out.println("Hour \t\t Distance Traveled");
		System.out.println("---------------------------------------");
		for(int i = 0; i <= hrs; i ++){
			dist.setHoursTraveled(i);
			dist.setSpeed(speed);
			System.out.println(i + "\t\t\t" + dist.getDistance());
		}
		

	}

}
