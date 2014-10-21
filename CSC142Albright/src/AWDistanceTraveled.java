/** 
 * @author Anthony Wittemann
 * Chapter 5 Program 2 Distance Traveled
 * HW Due 2/25/14
 */
public class AWDistanceTraveled {
	private int speed; //speed in mph
	private int hoursTraveled;

	public AWDistanceTraveled(int s, int h){
		speed = s;
		hoursTraveled = h;
	}
	
	public AWDistanceTraveled(){
		
	}

	public int getSpeed(){
		return speed;
	}
	
	public int getHoursTraveled(){
		return hoursTraveled;
	}
	
	public void setSpeed(int nSpeed){
		speed = nSpeed;
	}
	
	public void setHoursTraveled(int nHrsTraveled){
		hoursTraveled = nHrsTraveled;
	}
	
	/**
	 * 
	 * @return the distance traveled in mile
	 */
	public int getDistance(){
		return speed * hoursTraveled;
	}
}
