import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DataBaseMain {

	public static void main(String[] args) {
		/****** STEP 3 ******* STEP 3 ******* STEP 3 ******* STEP 3 ******* STEP 3 ******* STEP 3 ******* STEP 3 *******/
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		Statement s = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/Schema1", "root", "oezPC@1B");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword");
//			s = connection.createStatement();
//			int result = s.executeUpdate("CREATE DATABASE databasename");
			//TODO SQLException: Access denied for user 'root'@'localhost' (using password: YES)
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//prompt user for first and last name and store in Strings
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter your first name: ");
		String firstName = scan.nextLine();
		System.out.print("\nPlease enter your last name: ");
		String lastName = scan.nextLine();
		
		
		
		/****** STEP 4 ******* STEP 4 ******* STEP 4 ******* STEP 4 ******* STEP 4 ******* STEP 4 ******* STEP 4 *******/
		String sqlStatement = "INSERT INTO User(\"first_name\", \"last_name\") VALUES(" + firstName + "," + lastName + ")";
		String sqlStatement2 = "INSERT INTO User(\"first_name\", \"last_name\") VALUES(?,?)";
		PreparedStatement preparedStatement = null;
		try {
			//add user based on console input
			preparedStatement = connection.prepareStatement(sqlStatement2);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.execute();
			
			//add a few more users
			preparedStatement.setString(1, "Bo");
			preparedStatement.setString(2, "Heyser");
			preparedStatement.execute();
			preparedStatement.setString(1, "Joe");
			preparedStatement.setString(2, "Long");
			preparedStatement.execute();
			preparedStatement.setString(1, "Briana");
			preparedStatement.setString(2, "Garber");
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		/****** STEP 5 ******* STEP 5 ******* STEP 5 ******* STEP 5 ******* STEP 5 ******* STEP 5 ******* STEP 5 *******/
		try {
			Statement stmt = connection.createStatement();
			String sqlStatement3 = "SELECT * FROM User";
			ResultSet resultSet = stmt.executeQuery(sqlStatement3);
			while(resultSet.next()){
				System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name") +
						" ID: " + resultSet.getString("idUser"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
