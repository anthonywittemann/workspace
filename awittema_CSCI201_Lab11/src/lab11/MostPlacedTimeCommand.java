package lab11;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class MostPlacedTimeCommand extends SQLCommand {

	//Create a constructor that takes both the lock and an integer representing which place to get statistics for
	public MostPlacedTimeCommand(ReentrantLock queryLock, int statsPlace) {
		super(queryLock);
		
	}

	
	/**
	 * The goal of this step is to see which horse, by name, placed 1st, 2nd, 3rd, etc all the way to 8th the most times.
	 * 
	 * 3.1. Write a query that gets the information described above for 1 place (ie 1st place)
	 * 3.2. Print that information in the form: <horse name> finished number <place> the most
	 *      times at <number of finishes> times
	 */
	@Override
	public boolean execute() {
		// TODO 
		try{
			connection = DriverManager.getConnection(DB_ADDRESS + DB_NAME, USER, PASSWORD);
			
			for(int i = 1; i < 9; i++){
				Statement stmt = connection.createStatement();
				ResultSet places = stmt.executeQuery("SELECT * FROM race_result WHERE place = " + i);
				String place = "";
				while(places.next()){
					place += places.getInt("place");
				}
				ResultSet horseNumber = stmt.executeQuery("SELECT * FROM race_result WHERE horse_id\n");
				String horse = "";
				while(horseNumber.next()){
					horse = horseNumber.getString("horse_id");
				}
				ResultSet numberOfFinishes = stmt.executeQuery("SELECT * FROM race_result WHERE race_number;");
				while(numberOfFinishes.next()){
					
				}
				String printOutput = horse + " finishes number " + i;
						//horseNumber.getString("horse_id") + " finishes number " + i + 
						//"the most times at " + numberOfFinishes.getInt("race_number") + "times";
				System.out.println(printOutput);
			}
			
		
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Failed to get most placed time");
			return false;
		}
		
		
		return true;
	}

}
