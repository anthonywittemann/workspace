/** 
 * @author Anthony Wittemann
 * Chapter 6 Program 5 Month
 * HW Due 3/11/14 & 3/13/14
 */
public class AWMonth {
	private int monthNumber;
	
	public AWMonth(){
		monthNumber = 1;
		
	}
	
	public AWMonth(int mI){
		if(mI > 0 && mI < 13){
			monthNumber = mI;
		}
		else{
			monthNumber = 1;
		}
		
	}
	
	public AWMonth(String mS){
		if(mS.equals("Januray")){
			monthNumber = 1;
		}
		else if(mS.equals("February")){
			monthNumber = 2;
		}
		else if(mS.equals("March")){
			monthNumber = 3;
		}
		else if(mS.equals("April")){
			monthNumber = 4;
		}
		else if(mS.equals("May")){
			monthNumber = 5;
		}
		else if(mS.equals("June")){
			monthNumber = 6;
		}
		else if(mS.equals("July")){
			monthNumber = 7;
		}
		else if(mS.equals("August")){
			monthNumber = 8;
		}
		else if(mS.equals("September")){
			monthNumber = 9;
		}
		else if(mS.equals("October")){
			monthNumber = 10;
		}
		else if(mS.equals("Novemebr")){
			monthNumber = 11;
		}
		else if(mS.equals("December")){
			monthNumber = 12;
		}
		else{
			monthNumber = 1;
		}
		
	}
	
	/**
	 * Copy Constructor
	 * @param m instance of another month class
	 */
	public AWMonth(AWMonth m){
		this(m.getMonthNumber());
	}
	
	public void setMonthNumber(int nM){
		monthNumber = (nM < 13 && nM > 0) ? nM: 1;
	}
	
	public int getMonthNumber(){
		return monthNumber;
	}
	
	public String getMonthName(){
		if(monthNumber == 1){
			return "Janurary";
		}
		else if(monthNumber == 2){
			return "February";
		}
		else if(monthNumber == 3){
			return "March";
		}
		else if(monthNumber == 4){
			return "April";
		}
		else if(monthNumber == 5){
			return "May";
		}
		else if(monthNumber == 6){
			return "June";
		}
		else if(monthNumber == 7){
			return "July";
		}
		else if(monthNumber == 8){
			return "August";
		}
		else if(monthNumber == 9){
			return "September";
		}
		else if(monthNumber == 10){
			return "October";
		}
		else if(monthNumber == 11){
			return "November";
		}
		else if(monthNumber == 12){
			return "December";
		}
		return "January";
	}
	
	public String toString(){
		return getMonthName();
	}
	
	public boolean equals(Object month2){
		return ((AWMonth) month2).getMonthNumber() == monthNumber;
	}

}
