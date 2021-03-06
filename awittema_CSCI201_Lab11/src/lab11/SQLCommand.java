package lab11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class SQLCommand implements Runnable{
	
	public static final String DB_ADDRESS = "jdbc:mysql://localhost/";
	public static final String DB_NAME = "lab11";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	protected ReentrantLock queryLock;
	protected Connection connection = null;
	
	public SQLCommand(ReentrantLock queryLock){
		this.queryLock = queryLock;

	}
	
	@Override
	public void run() {
		//obtain the lock
		this.queryLock.lock();
		
		System.out.print("Executing... ");
		execute();
		System.out.println("Done");
		
		//give it back
		this.queryLock.unlock();
	}
	
	/**
	 * The execute function will contain the “SQLCommand” which you are going to execute. 
	 * If you successfully limit the program so that only one thread’s execute runs at a time, 
	 * you will always see “Executing... Done” for each command. 
	 * If done incorrectly, you will see things like “Executing... Executing” 
	 * because of threads being put to sleep during execution.
	 * @return true if successfully executed, false if not
	 */
	public abstract boolean execute();
}
