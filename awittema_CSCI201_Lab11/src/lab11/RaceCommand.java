package lab11;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class RaceCommand extends SQLCommand{

	public RaceCommand(ReentrantLock queryLock) {
		super(queryLock);
		
	}

	/**
	 * 2.1. Obtain the highest race number so far, add one and use that as this race’s number 
	 * (hint - sql max function, you may also need to make use of SQL aliases)
	 * 2.2. Select 8 horse_id’s at random and insert the 8 sets of race_number, horse_id, and place into race_result
	 */
	@Override
	public boolean execute() {
		try{
			connection = DriverManager.getConnection(DB_ADDRESS + DB_NAME, USER, PASSWORD);
			
			Statement stmt = connection.createStatement();
			String sqlStatement = "SELECT Max(race_number) FROM race_result;";
			ResultSet resultSet = stmt.executeQuery(sqlStatement);
			resultSet.next();
			int highestRace = resultSet.getInt(1) + 1;
			
			String sqlStatement2 = "SELECT horse_id FROM horse\n" + "ORDER BY RAND()\n" + "LIMIT 8;";
			ResultSet resultSet2 = stmt.executeQuery(sqlStatement2);
			String sqlStatement3 = "INSERT INTO race_result (race_number, horse_id, place)\n" + "VALUES(?,?,?);";
			PreparedStatement query = connection.prepareStatement(sqlStatement3);
			
			int place = 1;
			while(resultSet2.next()){
				query.setInt(1, highestRace);
				query.setInt(2, resultSet2.getInt("horse_id"));	
				query.setInt(3, place);
				query.execute();
				place++;
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Failed to obtain highest race number");
			return false;
		}
		
		
		return true;
	}

}
