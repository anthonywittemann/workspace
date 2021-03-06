package lab11;

import java.util.concurrent.locks.ReentrantLock;

public class Lab11Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Create 8 AddHorseCommand threads and start them.
		// Decide if you need a new ReentrantLock for each thread or one common lock. - ONLY ONE
		// Run your code and make sure that your locks are working (ie that you see lines that read “Executing... Done” only)
		// Verify (through MySQL Workbench) that there are now 8 horses with appropriate names
		
		ReentrantLock rLock = new ReentrantLock();
		for(int i = 0; i < 8; i++){
			Thread ahcThread = new Thread(new AddHorseCommand(rLock));
			ahcThread.start();
		}
		
		for(int j = 0; j < 3; j++){
			Thread ahcThread = new Thread(new RaceCommand(rLock));
			ahcThread.start();
		}
		
		for(int k = 0; k < 8; k++){
			Thread ahcThread = new Thread(new MostPlacedTimeCommand(rLock, k+1));
			ahcThread.start();
		}
	}

}
