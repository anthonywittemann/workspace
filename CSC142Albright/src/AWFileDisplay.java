import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class AWFileDisplay {
	private File file;
	private String display;
	private Scanner scanner;
	private int lineNumber;

	public AWFileDisplay(String f){
		file = new File(f);
		lineNumber = 1;
	}
	
	
	/**
	 * This method should display the entire contents of the file, 
	 * the name of which was passed to the constructor. 
	 * @param fileName
	 */
//	public String displayFile(String fileName){
//		
//	}
	
	/**
	 * This method should display only the first five lines of the file’s contents. 
	 * If the file contains less than five lines, it will display the file’s entire contents. 
	 */
//	public String displayHead(){
//		
//	}
	
	/**
	 * This method should display the contents of the file, 
	 * the name of which was passed to the constructor. 
	 * Each line will be preceded with a line number followed by a colon. 
	 * The line numbering will start at 1. 
	 */
	public String displayWithLineNumbers(){
		display = "";
		try {
			scanner = new Scanner(file);
			while(scanner.hasNext()){
				display = display + lineNumber + ": " + scanner.nextLine() + "\n";
				lineNumber ++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return display;
	}
	
}
