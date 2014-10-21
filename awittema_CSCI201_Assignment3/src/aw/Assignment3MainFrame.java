package aw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


//TODO create, modify, delete events from Calendar (double click), format calendar in calendarPanel .75%

//TODO get Labels to display when created from eventManager button

public class Assignment3MainFrame extends JFrame {
	
	/****** CONSTANTS ****** CONSTANTS ****** CONSTANTS ******/
	
	private static final int JANUARY = 0;
	private static final int FEBRUARY = 1;
	private static final int MARCH = 2;
	private static final int APRIL = 3;
	private static final int MAY = 4;
	private static final int JUNE = 5;
	private static final int JULY = 6;
	private static final int AUGUST = 7;
	private static final int SEPTEMBER = 8;
	private static final int OCTOBER = 9;
	private static final int NOVEMBER = 10;
	private static final int DECEMBER = 11;
	
	private static final String JAN = "January";
	private static final String FEB = "February";
	private static final String MAR = "March";
	private static final String APR = "April";
	private static final String MA = "May";
	private static final String JUN = "June";
	private static final String JUL = "July";
	private static final String AUG = "August";
	private static final String SEP = "September";
	private static final String OCT = "October";
	private static final String NOV = "November";
	private static final String DEC = "December";
	
	
	
	/****** VARIABLES FOR WHAT'S CURRENT ****** VARIABLES FOR WHAT'S CURRENT ******/
	private GregorianCalendar calendar;
	private int currentMonthNumber;
	private String currentMonth;
	private int currentYear;
	private int currentMonthDay;
	private int currentWeekDay;
	private int currentWeekOfYear;
	private int currentWeekOfMonth;
	
	private int todayYear;
	private int todayMonth;
	private int todayDay;
	
	public int removeIndex; 
	
	
	public static ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>(); //implement events arrayList
	public int [][] dayHighlights = new int[6][7]; // implement this as a 6x7 array 
									//with either 0 (grayed/disabled/not in current month),
									//1 (no events), 2 (currentMonthDay/yellow), or 3 (has events/blue)
	public int [][] currentDates = new int[6][7]; //implement this array 
	//to keep track of which dates are in which rows and columns on the calendar grid
	
	private ArrayList<JLabel> todaysEventsLabels = new ArrayList<JLabel>(); //TODO implement these to display on events panel
	private ArrayList<CalendarEvent> todaysEvents = new ArrayList<CalendarEvent>();
	
	
	/****** VARIABLES FOR WHAT'S SELECTED ****** VARIABLES FOR WHAT'S SELECTED ******/
	//used for adding events
	private int selectedMonthNumber;
	private String selectedMonth;
	private int selectedYear;
	private int selectedMonthDay;
	private String selectedStartAM = "AM"; //default to AM
	private int selectedStartMin;
	private int selectedStartHour;
	private String selectedEndAM = "AM"; //default to AM
	private int selectedEndMin;
	private int selectedEndHour;
	private String selectedTitle;
	private String selectedLocation;
	
	public static CalendarEvent currentEvent; //implement so events can be modified
	public static int index; //holds the index of the currentEvent in the events ArrayList
	
	
	/****** GUI COMPONENTS ****** GUI COMPONENTS ****** GUI COMPONENTS ******/
	private JLabel monthLbl;
	private JLabel yearLbl;
	private JButton forwardMonthB;
	private JButton backMonthB;
	
	private JPanel calendarPanel;
	private DefaultTableModel mtblCalendar; //Table model
	private JTable tblCalendar;
	
	private JPanel dayLblPanel;
	private JPanel eventsPanel;
	
	private JComboBox dayCB;
	private JComboBox monthCB;
	private JComboBox yearCB;
	private JComboBox startHourCB;
	private JComboBox startMinCB;
	private JComboBox startAMPMCB;
	private JComboBox endHourCB;
	private JComboBox endMinCB;
	private JComboBox endAMPMCB;
	
	private JTextField locationTF;
	private JTextField titleTF;
	
	JFrame eventManagerWindow; 
	JFrame eventCreatorWindow;
	
	
	
	public Assignment3MainFrame(){
		super("Calendar");
		setSize(600,600);
		setResizable(false);
		
		
		// set up GUI
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		/*** TOOLBAR PANEL ****** TOOLBAR PANEL ****** TOOLBAR PANEL ****** TOOLBAR PANEL ***/
		JPanel toolBarPanel = new JPanel();
		JToolBar toolBar = new JToolBar();
		addButtons(toolBar);
		toolBarPanel.add(toolBar);
//		gbc.weighty = .1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		masterPanel.add(toolBarPanel, gbc);
		
		
		/*** TOP PANEL ****** TOP PANEL ****** TOP PANEL ****** TOP PANEL ****** TOP PANEL ****** TOP PANEL ***/
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		
		JPanel monthYearPanel = new JPanel();
		monthYearPanel.setLayout(new BorderLayout());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");;
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
//		System.out.println("DEBUG: " + dateFormat.format(cal.getTime()));
		
		currentMonthNumber = cal.get(Calendar.MONTH); // Jan = 0, Dec = 11		
		currentMonth = OCT;
		currentYear = cal.get(Calendar.YEAR);
		currentMonthDay = cal.get(Calendar.DAY_OF_MONTH);
		currentWeekDay = cal.get(Calendar.DAY_OF_WEEK);
		currentWeekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
		currentWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		
		todayMonth = currentMonthNumber;
		todayDay = currentMonthDay;
		todayYear = currentYear;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
		calendar = new GregorianCalendar(currentYear,currentMonthNumber,currentMonthDay,
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.HOUR),cal.get(Calendar.SECOND));
		
		
		
		monthLbl = new JLabel(currentMonth);
		yearLbl = new JLabel(String.valueOf(currentYear));
		forwardMonthB = new JButton(" > ");
		backMonthB = new JButton(" < ");
		
		//change text Size in JLabels
		monthLbl.setFont(monthLbl.getFont().deriveFont((float) 64.0));
		yearLbl.setFont(yearLbl.getFont().deriveFont((float) 16));
		
		monthYearPanel.add(monthLbl, BorderLayout.CENTER);
		monthYearPanel.add(yearLbl, BorderLayout.SOUTH);
		
		
		
		//add actionlisteners to buttons
		forwardMonthB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				if(currentMonthNumber == 11){
					currentMonthNumber = 0;
					currentYear++;
				}else{
					currentMonthNumber++;
				}
				updateTitleBar();
				updateCalendar();
			} 
		});
		
		backMonthB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				if(currentMonthNumber == 0){
					currentMonthNumber = 11;
					currentYear--;
				}else{
					currentMonthNumber--;
				}
				updateTitleBar();
				updateCalendar();
			} 
		});
		
		//add components to panel with GridBagConstraints
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.weightx = .1;
		gbc1.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		topPanel.add(backMonthB, gbc1);
		gbc1.weightx = .8;
		gbc1.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		topPanel.add(monthYearPanel, gbc1);
		gbc1.weightx = .1;
		gbc1.anchor = GridBagConstraints.LINE_END;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		topPanel.add(forwardMonthB, gbc1);
		
		gbc.weightx = 1;
		gbc.weighty = .2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		masterPanel.add(topPanel, gbc);
		
		
		/*** CALENDAR PANEL ****** CALENDAR PANEL ****** CALENDAR PANEL ****** CALENDAR PANEL ****** CALENDAR PANEL ****** CALENDAR PANEL ***/
		calendarPanel = new JPanel();
		
		//create Labels for 7 days of the weeks
		JLabel suLbl = new JLabel("Su  ");
		JLabel moLbl = new JLabel("Mo  ");
		JLabel tuLbl = new JLabel("Tu  ");
		JLabel weLbl = new JLabel("We  ");
		JLabel thLbl = new JLabel("Th  ");
		JLabel frLbl = new JLabel("Fr  ");
		JLabel saLbl = new JLabel("Sa  ");
		
		//increase font size of Labels
		suLbl.setFont(suLbl.getFont().deriveFont((float) 42.0));
		moLbl.setFont(moLbl.getFont().deriveFont((float) 42.0));
		tuLbl.setFont(tuLbl.getFont().deriveFont((float) 42.0));
		weLbl.setFont(weLbl.getFont().deriveFont((float) 42.0));
		thLbl.setFont(thLbl.getFont().deriveFont((float) 42.0));
		frLbl.setFont(frLbl.getFont().deriveFont((float) 42.0));
		saLbl.setFont(saLbl.getFont().deriveFont((float) 42.0));
		
		//add day labels to row panel with GBC with proper spacing
		dayLblPanel = new JPanel();
		gbc.weightx = 1/7;
		dayLblPanel.add(suLbl, gbc);
		dayLblPanel.add(moLbl, gbc);
		dayLblPanel.add(tuLbl, gbc);
		dayLblPanel.add(weLbl, gbc);
		dayLblPanel.add(thLbl, gbc);
		dayLblPanel.add(frLbl, gbc);
		dayLblPanel.add(saLbl, gbc);
		
		
		
		mtblCalendar = new DefaultTableModel(){ //allows for double clicking
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tblCalendar = new JTable(mtblCalendar);
		tblCalendar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				JTable target = (JTable)me.getSource();
			    int row = target.getSelectedRow();
			    int column = target.getSelectedColumn();
			    
			    if(dayHighlights[row][column] != 0){ //only recognize clicks in the current month
				    if(me.getClickCount() == 2){ //create new event on double click
				    	currentMonthDay = currentDates[row][column];
				    	System.out.println("DEGUB: Current Month Day: " + currentMonthDay);
				    	System.out.println("DEBUG: Current Month: " + currentMonth);
				    	System.out.println("DEBUG: Current Year " + currentYear);
				    	showEventCreatorWindow(currentMonthDay, currentMonthNumber, currentYear);
				    	System.out.println("DEBUG: double click in row: " + row + " column: " + column);
				    }
				    else if(me.getClickCount() == 1){ //view events for day, change currenMonthtDay to this day
				    	currentMonthDay = currentDates[row][column];
				    	System.out.println("DEGUB: Current Month Day: " + currentMonthDay);
				    	System.out.println("DEBUG: Current Month: " + currentMonth);
				    	System.out.println("DEBUG: Current Year " + currentYear);
				    	figureOutTodaysEvents();
				    	//TODO view events for this day
				    	//get the day based on the row and column selected from currentDates 2D array
				    	//put those events in the eventspanel
				    	System.out.println("DEBUG: click in row: " + row + " column: " + column);
				    }
			    }
			  }
		});
		//No resizing or reordering && allow only 1 column&row to be selected at one time
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);
		tblCalendar.setColumnSelectionAllowed(false);
		tblCalendar.setRowSelectionAllowed(false);
		tblCalendar.setRowHeight(40);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		
		//add rows to calendarPanel
		calendarPanel.setLayout(new BorderLayout());
		calendarPanel.add(dayLblPanel, BorderLayout.NORTH);
		calendarPanel.add(tblCalendar, BorderLayout.CENTER);
		
		//add calendarPanel to masterPanel
		gbc.weighty = .5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		masterPanel.add(calendarPanel, gbc);
		
		//TODO add functionality to eventsPanel
		/*** EVENTS PANEL ****** EVENTS PANEL ****** EVENTS PANEL ****** EVENTS PANEL ****** EVENTS PANEL ****** EVENTS PANEL ***/
		eventsPanel = new JPanel();
		eventsPanel.setLayout(new GridBagLayout());
		
		JScrollPane scrollPane = new JScrollPane(eventsPanel);
		gbc.weighty = .3;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		masterPanel.add(scrollPane, gbc);
		
//		System.out.println("DEBUG: current day" + currentMonthDay);
		updateCalendar();
		
		//add masterPanel to rootPane
		add(masterPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * finds out the events for the current/highlighted day and adds them to JLabels to be displayed on the eventsPanel
	 */
	private void figureOutTodaysEvents(){
		 //clear out events from previous selected day
		for(int i = todaysEvents.size() - 1; i > 0; i--){
			todaysEvents.remove(i);
		}
		for(int i = todaysEventsLabels.size() - 1; i > 0; i--){
			todaysEventsLabels.remove(i);
		}

		removeIndex = -1;
		for(index = 0; index < events.size(); index++){
			if(index == events.size()){
				break;
			}
			CalendarEvent event = events.get(index);
			currentEvent = event;
			if(event.getDay() == currentMonthDay && event.getMonth() == currentMonthNumber && event.getYear() == currentYear){
				System.out.println("DEBUG: \n" + currentMonthDay + " event: " + event);
				todaysEvents.add(event);
				JLabel eventLabel = new JLabel(event.getLabelInfo());
				todaysEventsLabels.add(eventLabel);
				//TODO figure out why event labels aren't showing up
				System.out.println("DEBUG: Event Label: " + eventLabel.getText());
				eventLabel.addMouseListener(new MouseAdapter(){
					@Override
					public void mousePressed(MouseEvent me) {
						int clickCount = me.getClickCount();
						if(clickCount == 2){
							Object[] options = {"Delete", "Edit", "Cancel"};
							int choice = JOptionPane.showOptionDialog(null, "Would you like to edit or delete this event?", 
									"Select an Option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
									null, options, options[2]);
							if(choice == 0){ //delete the event
								removeIndex = index;
							}
							else if(choice == 1){ //modify the event
								showEventManagerWindow(currentEvent);
							}
							System.out.println("DEBUG: current event: " + currentEvent);
						}
					}
					
				});
			}
		}
		if(removeIndex != -1){
			events.remove(removeIndex); //remove the event after the looping has finished to avoid error
		}
		
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return false if no events for given day, true if events
	 */
	private boolean figureOutDaysEvents(int year, int month, int day){
	    for(CalendarEvent date: events){
	    	if(date.getYear() == year && date.getMonth() == month && date.getDay() == day){
	    		return true;
	    	}
	    }
	    return false;
	}
	
	//add functionality to Buttons
	private void addButtons(JToolBar toolBar) {
	    JButton button = null;

	    //event manager button
	    button = new JButton("Event Manager");
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				//show event manager for creating a new event
				showEventManagerWindow(null);
			} 
		});
	    toolBar.add(button);

	    //export button
	    button = new JButton("Export");
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				//perform exporting
				exportToCSV();
			} 
		});
	    toolBar.add(button);

	    //about button
	    button = new JButton("About");
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				//show about popup
				showAboutWindow();
			} 
		});
	    toolBar.add(button);
	}
	
	
	private void updateCalendar(){
		//Clear from previous times
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				mtblCalendar.setValueAt(null, i, j);
			}
		}
		
		GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonthNumber, 1);
		int numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		int startOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);
		int currentBox = 1; //the current box of the 42 being filled for calendar days
		while(currentBox <= 42){
			dayHighlights = new int[6][7];
			currentDates = new int[6][7];
			int row = 0;
			int column = 0;
			//make the calendar for the month
			int i = 1;
			for (; i <= numberOfDays; i++){
				currentBox++;
			    row = new Integer((i+startOfMonth-2)/7);
			    column = (i+startOfMonth-2)%7;
			    boolean hasEventToday = false;
			    hasEventToday = figureOutDaysEvents(currentYear, currentMonthNumber, i);
			    if(hasEventToday){
			    	// mark if event falls on this day
			    	mtblCalendar.setValueAt(i, row, column);
			    	dayHighlights[row][column] = 3; //events today
			    }
			    else{
			    	mtblCalendar.setValueAt(i, row, column);
			    	dayHighlights[row][column] = 1; //no events
			    }
			    if(i == todayDay && currentMonthNumber == todayMonth && currentYear == todayYear){
			    	dayHighlights[row][column] = 2; //current day
			    }
			    currentDates[row][column] = i; //matches the current date with the grid box
			    
			}
			//fill in dates previous to the start of the month
			//make sure the first block is being filled in (0,0)
			int previousMonthYear = currentYear;
			int previousMonth = currentMonthNumber - 1;
			if(currentMonthNumber == Calendar.JANUARY){
				previousMonth = Calendar.DECEMBER;
				previousMonthYear--;
			}
			cal = new GregorianCalendar(previousMonthYear, previousMonth, 1);
			numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			cal = new GregorianCalendar(previousMonthYear, previousMonth, numberOfDays);
			int col = startOfMonth - 1;
			int ro = 0;
			for(int day = numberOfDays; col > 0; day--){
				col--;
				mtblCalendar.setValueAt(day, ro, col);
				currentBox++;
//				System.out.println("DEBUG: ROW: " + ro + " COLUMN: " + col + " DAY: " + day);
				
			}
//			System.out.println("DEBUG");
			
			int nextMonthYear = currentYear;
			int nextMonth = currentMonthNumber + 1;
			if(currentMonthNumber == Calendar.DECEMBER){
				nextMonth = Calendar.JANUARY;
				nextMonthYear++;
			}
			
			cal = new GregorianCalendar(nextMonthYear, nextMonth, 1);
			numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			startOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
			// fill in dates after to the end of the month
			for(int x = 1; currentBox <= 42;x++){
				currentBox++;
				if(column < 6){
					column++;
				}
				else{
					column = 0;
					if(row < 5){
						row++;
					}
					else{
						currentBox = 43;
						break;
					}
				}

//				System.out.println("DEBUG: ROW: " + row + " COLUMN: " + column + " DAY: " + x);
				mtblCalendar.setValueAt(x, row, column);
			}
//			System.out.println("DEBUG");
		}
		GridBagConstraints GBCs = new GridBagConstraints();
		figureOutTodaysEvents();
		//TODO get the events to show up on the panel
		//n
		GBCs.gridx = 0;
		GBCs.gridy = 0;
		eventsPanel.removeAll();
		eventsPanel.add(new JLabel("Events: "), GBCs);
		for(int i = 0; i < todaysEventsLabels.size(); i++){
			GBCs.gridy = i + 1;
			eventsPanel.add(todaysEventsLabels.get(i), GBCs);
		}
		//Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
		
	}
	
	/**
	 * shows my picture, section/class time, date program was completed
	 */
	private void showAboutWindow(){
		JFrame aboutWindow = new JFrame("About");
		aboutWindow.setSize(400, 400);
		aboutWindow.setLocation(100, 100);
		aboutWindow.setResizable(false);
		
		//add my name at top
		JPanel namePanel = new JPanel();
		JLabel nameLbl = new JLabel("Anthony Wittemann");
		namePanel.add(nameLbl);
		aboutWindow.add(namePanel, BorderLayout.NORTH);
		
		//add picture of me
		JPanel picPanel = new JPanel();
		
		//find the image
		java.net.URL imageURL = Assignment3MainFrame.class.getResource("MeInSchwarzwald.jpg");
		ImageIcon mePic = null;
		if (imageURL != null) {
		   mePic = new ImageIcon(imageURL);
		}
		else{
			System.out.println("Image not found");
		}
		
		//scale the image
		ImageIcon scaledLabPicIcon = scaleIcon(mePic.getImage(), .2);//NEED TO FIDDLE WITH TO FIT FRAME
		
		JLabel imageLabel = new JLabel(scaledLabPicIcon);
		picPanel.add(imageLabel, BorderLayout.CENTER);
		
		//add info
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		JLabel section = new JLabel("Section: MW 8:00 am, Dr. Miller");
		JLabel dateProgramCompleted = new JLabel("Date of Program Completion: 10/5/14");
		infoPanel.add(section, BorderLayout.NORTH);
		infoPanel.add(dateProgramCompleted, BorderLayout.SOUTH);
		
		JPanel picAndInfoPanel = new JPanel();
		picAndInfoPanel.add(picPanel);
		picAndInfoPanel.add(infoPanel);
		
		//add picture and info panels
		aboutWindow.add(picAndInfoPanel);
		
		aboutWindow.setVisible(true);
	}
	
	/**
	 * method used to scale image
	 */
	private ImageIcon scaleIcon(Image img, double scale) {
        int w = (int)(scale*img.getWidth(this));
        int h = (int)(scale*img.getHeight(this));
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(img, 0, 0, w, h, this);
        g2d.dispose();
        return new ImageIcon(bi);
    }

	//separate events by column
	private void exportToCSV(){
		try{
			OutputStream output = new FileOutputStream("events.csv");
			for(CalendarEvent event: events){
				String[] eventDetails = event.toString().split("\t");
				for(String s: eventDetails){
					output.write(s.getBytes("UTF-8"));
					output.write('\t');
				}
				output.write('\n');
			}
			output.close();
			JOptionPane.showMessageDialog(null, "Export completed sucessfully!");
		}catch(IOException ioe){
			System.out.println("IOEXCEPTION: " + ioe.getMessage());
		}
	}
	
	//creates a new event when the calendar is double clicked
	private void showEventCreatorWindow(int cDay, int cMonth, int cYear){
		eventCreatorWindow = new JFrame("Event Creator");
		eventCreatorWindow.setSize(300, 350);
		eventCreatorWindow.setLocation(100, 100);
		eventCreatorWindow.setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		JLabel titleLbl = new JLabel("Title:");
		JLabel locationLbl = new JLabel("Location:");
		JLabel startLbl = new JLabel("Start:");
		JLabel endLbl = new JLabel("End:");
		
		titleTF = new JTextField("", 15);
		locationTF = new JTextField("", 15);
		
		//add start hour, min, AMPM comboBoxes
		startHourCB = new JComboBox();
		for(int i = 1; i <= 12; i++){
			startHourCB.addItem(i);
		}
		startHourCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedStartHour = Integer.parseInt(ie.getItem().toString());
				//						System.out.println("DEBUG: Start Hour CB itemStateChanged");
			}
		});

		startMinCB = new JComboBox();
		for(int i = 0; i <= 59; i++){
			startMinCB.addItem(i);
		}
		startMinCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedStartMin = Integer.parseInt(ie.getItem().toString());
			}
		});

		startAMPMCB = new JComboBox();
		startAMPMCB.addItem("AM");	startAMPMCB.addItem("PM");
		startAMPMCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedStartAM = ie.getItem().toString();
				//						System.out.println("DEBUG: Start Item Selected: " + selectedStartAM);
			}
		});

		//dd end hour, min, AMPM comboBoxes
		endHourCB = new JComboBox();
		for(int i = 1; i <= 12; i++){
			endHourCB.addItem(i);
		}
		endHourCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedEndHour = Integer.parseInt(ie.getItem().toString());
			}
		});

		endMinCB = new JComboBox();
		for(int i = 0; i <= 59; i++){
			endMinCB.addItem(i);
		}
		endMinCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedEndMin = Integer.parseInt(ie.getItem().toString());
			}
		});

		endAMPMCB = new JComboBox();
		endAMPMCB.addItem("AM");	endAMPMCB.addItem("PM");
		endAMPMCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedEndAM = ie.getItem().toString();
				//						System.out.println("DEBUG: End Item Selected: " + selectedEndAM);
			}
		});
		JButton createB = new JButton("Create");
		JButton cancelB = new JButton("Cancel");
		//create a new event
		createB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				selectedLocation = locationTF.getText();
				selectedTitle = titleTF.getText();
				CalendarEvent newEvent = new CalendarEvent(currentYear, currentMonthNumber, currentMonthDay, 
						selectedTitle, selectedLocation, selectedStartHour, selectedStartMin,
						selectedStartAM, selectedEndHour, selectedEndMin, selectedEndAM);

				events.add(newEvent);

				System.out.println("DEBUG: \n" + newEvent);
				eventCreatorWindow.setVisible(false);
			}
		});

		cancelB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				eventCreatorWindow.setVisible(false);
			}
		});
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 3;
		JPanel row3Panel = new JPanel();
		row3Panel.add(titleLbl);
		row3Panel.add(titleTF);
		mainPanel.add(row3Panel, gbc);

		gbc.gridy = 4;
		JPanel row4Panel = new JPanel();
		row4Panel.add(locationLbl);
		row4Panel.add(locationTF);
		mainPanel.add(row4Panel, gbc);

		gbc.gridy = 5;
		JPanel row5Panel = new JPanel();
		row5Panel.add(startLbl);
		row5Panel.add(startHourCB);
		row5Panel.add(startMinCB);
		row5Panel.add(startAMPMCB);
		mainPanel.add(row5Panel, gbc);

		gbc.gridy = 6;
		JPanel row6Panel = new JPanel();
		row6Panel.add(endLbl);
		row6Panel.add(endHourCB);
		row6Panel.add(endMinCB);
		row6Panel.add(endAMPMCB);
		mainPanel.add(row6Panel, gbc);

		gbc.gridy = 7;
		JPanel row7Panel = new JPanel();
		row7Panel.add(createB);
		row7Panel.add(cancelB);
		mainPanel.add(row7Panel, gbc);
		
		eventCreatorWindow.add(mainPanel);
		eventCreatorWindow.setVisible(true);
		updateCalendar();
	}
	
	
	
	
	private void showEventManagerWindow(CalendarEvent event){ //null if new event
		//1: creating a new event from button in toolbar
		//2: Delte, edit, cancel JOptionPane --> modifying an event from eventLabel: save button at bottom 
		eventManagerWindow = new JFrame("Event Manager");
		eventManagerWindow.setSize(300, 350);
		eventManagerWindow.setLocation(100, 100);
		eventManagerWindow.setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		JLabel yearLbl = new JLabel("Year:");
		JLabel monthLbl = new JLabel("Month:");
		JLabel dayLbl = new JLabel("Day:");
		JLabel titleLbl = new JLabel("Title:");
		JLabel locationLbl = new JLabel("Location:");
		JLabel startLbl = new JLabel("Start:");
		JLabel endLbl = new JLabel("End:");
		
		titleTF = new JTextField("", 15);
		locationTF = new JTextField("", 15);
		
		
		//add items to dropdown ComboBoxes
		yearCB = new JComboBox();
		for(int i = 1900; i <= 2099; i++){
			yearCB.addItem(i);
		}
		yearCB.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent ie) {
				//System.out.println("DEBUG: Item: " + ie.getItem());
				selectedYear = Integer.parseInt(ie.getItem().toString());
				//System.out.println("DEBUG: Selected Year: " + selectedYear);
			}
		});
		
		monthCB = new JComboBox();
		monthCB.addItem(JAN); monthCB.addItem(FEB); monthCB.addItem(MAR);
		monthCB.addItem(APR); monthCB.addItem(MA); monthCB.addItem(JUN);
		monthCB.addItem(JUL); monthCB.addItem(AUG); monthCB.addItem(SEP);
		monthCB.addItem(OCT); monthCB.addItem(NOV); monthCB.addItem(DEC);
		monthCB.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent ie) {
				String itemSelected = (String) ie.getItem();
				selectedMonth = itemSelected;
				if(itemSelected.equals(JAN)){
					selectedMonthNumber = 0;
				}
				else if(itemSelected.equals(FEB)){
					selectedMonthNumber = 1;
				}
				else if(itemSelected.equals(MAR)){
					selectedMonthNumber = 2;
				}
				else if(itemSelected.equals(APR)){
					selectedMonthNumber = 3;
				}
				else if(itemSelected.equals(MA)){
					selectedMonthNumber = MAY;
				}
				else if(itemSelected.equals(JUN)){
					selectedMonthNumber = JUNE;
				}
				else if(itemSelected.equals(JUL)){
					selectedMonthNumber = JULY;
				}
				else if(itemSelected.equals(AUG)){
					selectedMonthNumber = AUGUST;
				}
				else if(itemSelected.equals(SEP)){
					selectedMonthNumber = SEPTEMBER;
				}
				else if(itemSelected.equals(OCT)){
					selectedMonthNumber = OCTOBER;
				}
				else if(itemSelected.equals(NOV)){
					selectedMonthNumber = NOVEMBER;
				}
				else if(itemSelected.equals(DEC)){
					selectedMonthNumber = DECEMBER;
				}
				
				fillDayComboBox(); //update how many days are in the month
			}
		});
		
		dayCB = new JComboBox();
		fillDayComboBox();
		dayCB.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent ie) {
				selectedMonthDay = Integer.parseInt(ie.getItem().toString());
			}
		});
		
		//add start hour, min, AMPM comboBoxes
		startHourCB = new JComboBox();
		for(int i = 1; i <= 12; i++){
			startHourCB.addItem(i);
		}
		startHourCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedStartHour = Integer.parseInt(ie.getItem().toString());
//				System.out.println("DEBUG: Start Hour CB itemStateChanged");
			}
		});
		
		startMinCB = new JComboBox();
		for(int i = 0; i <= 59; i++){
			startMinCB.addItem(i);
		}
		startMinCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedStartMin = Integer.parseInt(ie.getItem().toString());
			}
		});
		
		startAMPMCB = new JComboBox();
		startAMPMCB.addItem("AM");	startAMPMCB.addItem("PM");
		startAMPMCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedStartAM = ie.getItem().toString();
//				System.out.println("DEBUG: Start Item Selected: " + selectedStartAM);
			}
		});
		
		//dd end hour, min, AMPM comboBoxes
		endHourCB = new JComboBox();
		for(int i = 1; i <= 12; i++){
			endHourCB.addItem(i);
		}
		endHourCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedEndHour = Integer.parseInt(ie.getItem().toString());
			}
		});
		
		endMinCB = new JComboBox();
		for(int i = 0; i <= 59; i++){
			endMinCB.addItem(i);
		}
		endMinCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedEndMin = Integer.parseInt(ie.getItem().toString());
			}
		});
		
		endAMPMCB = new JComboBox();
		endAMPMCB.addItem("AM");	endAMPMCB.addItem("PM");
		endAMPMCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				selectedEndAM = ie.getItem().toString();
//				System.out.println("DEBUG: End Item Selected: " + selectedEndAM);
			}
		});
		JButton createB = new JButton("Create");
		JButton cancelB = new JButton("Cancel");
		JButton saveChangesB = new JButton("Save Changes");
		if(event == null){ //create a new event
			createB.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent ae) {
					selectedLocation = locationTF.getText();
					selectedTitle = titleTF.getText();
					CalendarEvent newEvent = new CalendarEvent(selectedYear, selectedMonthNumber, selectedMonthDay, 
							selectedTitle, selectedLocation, selectedStartHour, selectedStartMin,
							selectedStartAM, selectedEndHour, selectedEndMin, selectedEndAM);
					
					events.add(newEvent);
					
					System.out.println("DEBUG: \n" + newEvent);
					eventManagerWindow.setVisible(false);
				}
			});
			
			cancelB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					eventManagerWindow.setVisible(false);
				}
			});
		}
		else{ //modify an event
			saveChangesB.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent ae) {
					CalendarEvent event = new CalendarEvent();
					event.setEndAM(selectedEndAM);
					event.setStartAM(selectedStartAM);
					event.setEndHour(selectedEndHour);
					event.setStartHour(selectedStartHour);
					event.setEndMinute(selectedEndMin);
					event.setStartMinute(selectedStartMin);
					event.setDay(currentMonthDay);
					event.setMonth(currentMonthNumber);
					event.setYear(currentYear);
					event.setTitle(titleTF.getText());
					event.setLocation(locationTF.getText());
					Assignment3MainFrame.saveEventChanges(event);
					System.out.println("DEBUG: \n" + event);
					JOptionPane.showMessageDialog(null, "Changes saved!");
					eventManagerWindow.setVisible(false);
				}
			});
			updateCalendar();
		}
		
		
		//add components to mainPanel
		GridBagConstraints gbc = new GridBagConstraints();
		if(event == null){ //if creating a new event
			gbc.gridx = 0;
			gbc.gridy = 0;
			JPanel row0Panel = new JPanel();
			row0Panel.add(yearLbl);
			row0Panel.add(yearCB);
			mainPanel.add(row0Panel, gbc);
			
			gbc.gridy = 1;
			JPanel row1Panel = new JPanel();
			row1Panel.add(monthLbl);
			row1Panel.add(monthCB);
			mainPanel.add(row1Panel, gbc);
			
			gbc.gridy = 2;
			JPanel row2Panel = new JPanel();
			row2Panel.add(dayLbl);
			row2Panel.add(dayCB);
			mainPanel.add(row2Panel, gbc);
			
			gbc.gridy = 3;
			JPanel row3Panel = new JPanel();
			row3Panel.add(titleLbl);
			row3Panel.add(titleTF);
			mainPanel.add(row3Panel, gbc);
			
			gbc.gridy = 4;
			JPanel row4Panel = new JPanel();
			row4Panel.add(locationLbl);
			row4Panel.add(locationTF);
			mainPanel.add(row4Panel, gbc);
			
			gbc.gridy = 5;
			JPanel row5Panel = new JPanel();
			row5Panel.add(startLbl);
			row5Panel.add(startHourCB);
			row5Panel.add(startMinCB);
			row5Panel.add(startAMPMCB);
			mainPanel.add(row5Panel, gbc);
			
			gbc.gridy = 6;
			JPanel row6Panel = new JPanel();
			row6Panel.add(endLbl);
			row6Panel.add(endHourCB);
			row6Panel.add(endMinCB);
			row6Panel.add(endAMPMCB);
			mainPanel.add(row6Panel, gbc);
			
			gbc.gridy = 7;
			JPanel row7Panel = new JPanel();
			row7Panel.add(createB);
			row7Panel.add(cancelB);
			mainPanel.add(row7Panel, gbc);
		}
		else{ //modifying an event
			gbc.gridy = 0;
			JPanel row3Panel = new JPanel();
			row3Panel.add(titleLbl);
			row3Panel.add(titleTF);
			mainPanel.add(row3Panel, gbc);
			
			gbc.gridy = 1;
			JPanel row4Panel = new JPanel();
			row4Panel.add(locationLbl);
			row4Panel.add(locationTF);
			mainPanel.add(row4Panel, gbc);
			
			gbc.gridy = 2;
			JPanel row5Panel = new JPanel();
			row5Panel.add(startLbl);
			row5Panel.add(startHourCB);
			row5Panel.add(startMinCB);
			row5Panel.add(startAMPMCB);
			mainPanel.add(row5Panel, gbc);
			
			gbc.gridy = 3;
			JPanel row6Panel = new JPanel();
			row6Panel.add(endLbl);
			row6Panel.add(endHourCB);
			row6Panel.add(endMinCB);
			row6Panel.add(endAMPMCB);
			mainPanel.add(row6Panel, gbc);
			
			gbc.gridy = 4;
			JPanel row7Panel = new JPanel();
			row7Panel.add(saveChangesB);
			mainPanel.add(row7Panel, gbc);
		}
		
		
		
		eventManagerWindow.add(mainPanel);
		eventManagerWindow.setVisible(true);
		updateCalendar();
	}
	
	public static void saveEventChanges(CalendarEvent modifiedEvent){
		//TODO figure out how to modify the currentEvent
		currentEvent = new CalendarEvent(modifiedEvent); //use copy constructor to modify current event
		System.out.println("DEBUG:     Current Event " + currentEvent);
		if(events.size() > 0){
			events.remove(index);
		}
		events.add(currentEvent);
	}
	
	//figure out the days in the current month and add them to day comboBox
	private void fillDayComboBox(){
		dayCB.removeAllItems(); //clear out the items from previous month additions
		int daysInMonth = 31;
		if(selectedMonthNumber == SEPTEMBER || selectedMonthNumber == APRIL ||
				selectedMonthNumber == JUNE || selectedMonthNumber == NOVEMBER){
			daysInMonth = 30;
		}
		else if(selectedMonthNumber == FEBRUARY){
			if(calendar.isLeapYear(selectedYear)){
				daysInMonth = 29;
			}
			else{
				daysInMonth = 28;
			}
		}
		for(int i = 1; i <= daysInMonth; i++){
			dayCB.addItem(i);
		}
	}
	
	
	private void updateTitleBar(){
		if(currentMonthNumber == JANUARY){
			currentMonth = JAN;
		}
		else if(currentMonthNumber == FEBRUARY){
			currentMonth = FEB;
		}
		else if(currentMonthNumber == MARCH){
			currentMonth = MAR;
		}
		else if(currentMonthNumber == APRIL){
			currentMonth = APR;
		}
		else if(currentMonthNumber == MAY){
			currentMonth = MA;
		}
		else if(currentMonthNumber == JUNE){
			currentMonth = JUN;
		}
		else if(currentMonthNumber == JULY){
			currentMonth = JUL;
		}
		else if(currentMonthNumber == AUGUST){
			currentMonth = AUG;
		}
		else if(currentMonthNumber == SEPTEMBER){
			currentMonth = SEP;
		}
		else if(currentMonthNumber == OCTOBER){
			currentMonth = OCT;
		}
		else if(currentMonthNumber == NOVEMBER){
			currentMonth = NOV;
		}
		else if(currentMonthNumber == DECEMBER){
			currentMonth = DEC;
		}
		else{
			System.out.println("DEBUG: month #" + currentMonthNumber);
		}
		monthLbl.setText(currentMonth);
		yearLbl.setText(String.valueOf(currentYear));
	}
	
	
	private class tblCalendarRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			
			if(dayHighlights[row][column] == 0){ //gray out, not in current month
				setBackground(Color.GRAY);
			}
			else if(dayHighlights[row][column] == 1){ //white, in current month, no events
				setBackground(Color.WHITE);
			}
			else if(dayHighlights[row][column] == 2){ //yellow, current day
				setBackground(Color.YELLOW);
			}
			else if(dayHighlights[row][column] == 3){ //blue, in current month, has events
				setBackground(Color.BLUE);
			}
			
			setBorder(null);
			setForeground(Color.black);
			return this; 
		}
	}

	
	/******* GETTERS *********** GETTERS ************** GETTERS **********/
	
	public String getCurrentMonth(){
		return currentMonth;
	}
	
	public int getCurrentMonthNumber(){
		return currentMonthNumber;
	}
	
	public int getCurrentYear(){
		return currentYear;
	}
	
	public int getCurrentWeekDay(){
		return currentWeekDay;
	}
	
	public int getCurrentWeekOfYear(){
		return currentWeekOfYear;
	}
	
	public int getCurrentWeekOfMonth(){
		return currentWeekOfMonth;
	}
	
	
	
	/******* SETTERS *********** SETTERS ************** SETTERS **********/
	
	public void setCurrentMonth(String nM){
		currentMonth = nM;
	}
	
	public void setCurrentMonthNumber(int nMN){
		currentMonthNumber = nMN;
	}
	
	public void setCurrentYear(int nY){
		currentYear = nY;
	}
	
	public void setCurrentWeekDay(int nWD){
		currentWeekDay = nWD;
	}
	
	public void setCurrentWeekOfYear(int nWOY){
		currentWeekOfYear = nWOY;
	}
	
	public void setCurrentWeekOfMonth(int nWOM){
		currentWeekOfMonth = nWOM;
	}
	

	public static void main(String[] args) {
		new Assignment3MainFrame();

	}

}
