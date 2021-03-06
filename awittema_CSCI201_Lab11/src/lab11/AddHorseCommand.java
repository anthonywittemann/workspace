package lab11;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class AddHorseCommand extends SQLCommand {

	public AddHorseCommand(ReentrantLock queryLock) {
		super(queryLock);
		
	}

	
	/**
	 * 2.1. Select 2 random words from the words table (hints - Order by RAND() and limit)
	 * 2.2. Create a new record in the horse table with name firstWord + “ “ + secondWord
	 */
	@Override
	public boolean execute() {
		// TODO 
		try{
			connection = DriverManager.getConnection(DB_ADDRESS + DB_NAME, USER, PASSWORD);
			Statement stmt = connection.createStatement();
			String sqlStatement2 = "SELECT word FROM words\n" + "ORDER BY RAND()\n" + "LIMIT 2;";
			ResultSet resultSet = stmt.executeQuery(sqlStatement2);
			
			String firstN = "";
			String lastN = "";
			while(resultSet.next()){
				firstN += resultSet.getString("word");
			}
			
			
			String horse_name = firstN + lastN;
			String sqlStatement = "INSERT INTO horse(name) VALUES(?)";
			
			PreparedStatement preparedStatement = null;
			
			//add new record based on random horse names
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, firstN);
			preparedStatement.execute();
		} catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Failed to add horse");
			return false;
		}
		
		
		return true;
	}

}
