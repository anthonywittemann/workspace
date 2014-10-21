package aw;
//TODO fix start, end AM/PM
public class CalendarEvent {
	
	private int year;
	private int month;
	private int day;
	private String title;
	private String location;
	private int startHour;
	private int startMinute;
	private String startAM; //AM = true, PM = false
	private int endHour;
	private int endMinute;
	private String endAM; //AM = true, PM = false
	
	public CalendarEvent(int year, int month, int day, String title, 
			String location, int startHour, int startMinute, String startAM, 
			int endHour, int endMinute, String endAM){
		this.year = year;
		this.month = month;
		this.day = day;
		this.title = title;
		this.location = location;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.startAM = startAM;
		this.endHour = endHour;
		this.endMinute = endMinute;
		this.endAM = endAM;
		
	}
	
	//copy constructor
	 public CalendarEvent(CalendarEvent modifiedEvent) {
	    this(modifiedEvent.year, modifiedEvent.month, modifiedEvent.day, new String(modifiedEvent.title),
	    		new String(modifiedEvent.location), modifiedEvent.startHour, modifiedEvent.startMinute,
	    		new String(modifiedEvent.startAM), modifiedEvent.endHour, modifiedEvent.endMinute, new String(modifiedEvent.endAM));
	  }
	
	public CalendarEvent(){
		
	}
	
	
	/*** GETTERS ****** GETTERS ****** GETTERS ****** GETTERS ****** GETTERS ******/
	public int getYear(){
		return this.year;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getDay(){
		return this.day;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public int getStartHour(){
		return this.startHour;
	}
	
	public int getStartMinute(){
		return this.startMinute;
	}
	
	public String getStartAM(){
		return this.startAM;
	}
	
	public int getEndHour(){
		return this.endHour;
	}
	
	public int getEndMinute(){
		return this.endMinute;
	}
	
	public String getEndAM(){
		return this.endAM;
	}
	
	//returns what is displayed on the JLabel in the events section of the window
	public String getLabelInfo(){
		return "Title: " + title + " Location: " + location +
				" Start " + startHour + ":" + startMinute + " " + startAM +
				" End " + endHour + ":" + endMinute + " " + endAM;
	}
	
	
	
	/*** SETTERS ****** SETTERS ****** SETTERS ****** SETTERS ****** SETTERS ******/
	public void setYear(int nY){
		this.year = nY;
	}
	
	public void setMonth(int nM){
		this.month = nM;
	}
	
	public void setDay(int nD){
		this.day = nD;
	}
	
	public void setTitle(String nT){
		this.title = nT;
	}
	
	public void setLocation(String nL){
		this.location = nL;
	}
	
	public void setStartHour(int nSH){
		this.startHour = nSH;
	}
	
	public void setStartMinute(int nSM){
		this.startMinute = nSM;
	}
	
	public void setStartAM(String nSAM){
		this.startAM = nSAM;
	}
	
	public void setEndHour(int nEH){
		this.endHour = nEH;
	}
	
	public void setEndMinute(int nEM){
		this.endMinute = nEM;
	}
	
	public void setEndAM(String nEAM){
		this.endAM = nEAM;
	}
	
	
	public String toString(){
		return "Year: " + year + "\tMonth: " + month + "\tDay: " + day +
				"\tTitle: " + title + "\tLocation: " + location + "\t" +
				"Start Hour: " + startHour + "\tMinute: " + startMinute + " \t" + startAM +
				"\tEnd Hour: " + endHour + "\tMinute: " + endMinute + " \t" + endAM;
	}
	
	

}
