/**
 * 
 */
package aw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * @author anthonywittemann
 *
 */
public class FactoryFrame extends JFrame{
	
	private JFileChooser fileChooser;
	private String factoryRCPFileDirectory;
	private JScrollPane scrollPane;
	private static JTextArea taskBarTA;
	private String taskBarText;
	private JPanel taskBarPanel; //contains scrollPane, taskbar label
	private JLabel taskBarLbl;
	private File factoryFileName;
	
	private static JPanel drawingPanel;
	public static final int TIMER_INTERVAL = 50; //time in milliseconds between each tick
	private static Timer timer;
	
	private static long totalTime = 0;
	
	//used to update inventory labels
	//get these values from .factory file
	private static int totalWorkers;
	private static int totalHammers;
	private static int totalScrewdrivers;
	private static int totalPlyers;
	private static int totalScissors;
	private static int totalPaintbrushes;
	
	private static Vector<Worker> workers = new Vector<Worker>();
	
	private static int numAvailableWorkers;
	private static int numAvailableHammers;
	private static int numAvailableScrewdrivers;
	private static int numAvailablePlyers;
	private static int numAvailableScissors;
	private static int numAvailablePaintbrushes;
	//implement Semaphores for multiple keys on tools locks
	public static Semaphore hammersAvailable;
	public static Semaphore screwdriversAvailable;
	public static Semaphore plyersAvailable;
	public static Semaphore scissorsAvailable;
	public static Semaphore paintbrushesAvailable;
	
	
	//get these from .rcp files
	private static int numWood = 0;
	private static int numMetal = 0;
	private static int numPlastic = 0;
	public static Semaphore woodAvailable;
	public static Semaphore metalAvailable;
	public static Semaphore plasticAvailable;
	private static ArrayList<File> rcpFiles = new ArrayList<File>();
	
	private static Vector<Product> products = new Vector<Product>();
	private static short productKeyCode = 0; //used to identify which worker has the current product
	
	public static final int NUM_ANVILS = 2;
	public static final int NUM_WORKBENCHES = 3;
	public static final int NUM_FURNACES = 2;
	public static final int NUM_TABLESAWS = 3;
	public static final int NUM_PAINTING_STATIONS = 4;
	public static final int NUM_PRESSES = 1;
	
	//TASKBOARD
	public static final int TASK_BOARD_X = 600;
	//MATERIALS
	public static final int MATERIALS_Y = 30;
	public static final int WOOD_X = 140;
	public static final int METAL_X = 280;
	public static final int PLASTIC_X = 420;
	//TOOLS
	public static final int TOOLS_X = 20;
	public static final int TOOLS_BEGIN_Y = 140;
	public static final int TOOLS_END_Y = 600;
	//WORKBENCHES
	public static final int WORKBENCHES_AND_ANVILS_Y = 150;
	public static final int ANVIL1_X = 170;
	public static final int ANVIL2_X = 240;
	public static final int WORKBENCH1_X = 310;
	public static final int WORKBENCH2_X = 380;
	public static final int WORKBENCH3_X = 450;
	
	public static final int SAWS_AND_FURNACES_Y = 310;
	public static final int FURNACE1_X = 170;
	public static final int FURNACE2_X = 240;
	public static final int SAW1_X = 310;
	public static final int SAW2_X = 380;
	public static final int SAW3_X = 450;
	
	public static final int PAITNING_STATIONS_AND_PRESS_Y = 470;
	public static final int PAINTING_STATION1_X = 170;
	public static final int PAINTING_STATION2_X = 240;
	public static final int PAINTING_STATION3_X = 310;
	public static final int PAINTING_STATION4_X = 380;
	public static final int PRESS_X = 450;
	
	
	//used for updating the work area labels
	public static boolean anvil1Occupied = false;
	public static boolean anvil2Occupied = false;
	public static boolean workbench1Occupied = false;
	public static boolean workbench2Occupied = false;
	public static boolean workbench3Occupied = false;
	public static boolean furnace1Occupied = false;
	public static boolean furnace2Occupied = false;
	public static boolean tablesaw1Occupied = false;
	public static boolean tablesaw2Occupied = false;
	public static boolean tablesaw3Occupied = false;
	public static boolean paintingStation1Occupied = false;
	public static boolean paintingStation2Occupied = false;
	public static boolean paintingStation3Occupied = false;
	public static boolean paintingStation4Occupied = false;
	public static boolean press1Occupied = false;
	
	
	public static int anvil1TimeRemaining = 0;
	public static int anvil2TimeRemaining = 0;
	public static int workbench1TimeRemaining = 0;
	public static int workbench2TimeRemaining = 0;
	public static int workbench3TimeRemaining = 0;
	public static int furnace1TimeRemaining = 0;
	public static int furnace2TimeRemaining = 0;
	public static int tablesaw1TimeRemaining = 0;
	public static int tablesaw2TimeRemaining = 0;
	public static int tablesaw3TimeRemaining = 0;
	public static int paintingStation1TimeRemaining = 0;
	public static int paintingStation2TimeRemaining = 0;
	public static int paintingStation3TimeRemaining = 0;
	public static int paintingStation4TimeRemaining = 0;
	public static int press1TimeRemaining = 0;
	
	//implement Locks for each workstation
	public static Lock anvil1Lock = new ReentrantLock();
	public static Lock anvil2Lock = new ReentrantLock();
	public static Lock workbench1Lock = new ReentrantLock();
	public static Lock workbench2Lock = new ReentrantLock();
	public static Lock workbench3Lock = new ReentrantLock();
	public static Lock furnace1Lock = new ReentrantLock();
	public static Lock furnace2Lock = new ReentrantLock();
	public static Lock tablesaw1Lock = new ReentrantLock();
	public static Lock tablesaw2Lock = new ReentrantLock();
	public static Lock tablesaw3Lock = new ReentrantLock();
	public static Lock paintingStation1Lock = new ReentrantLock();
	public static Lock paintingStation2Lock = new ReentrantLock();
	public static Lock paintingStation3Lock = new ReentrantLock();
	public static Lock paintingStation4Lock = new ReentrantLock();
	public static Lock press1Lock = new ReentrantLock();
	
	public FactoryFrame(){
		super("Factory");
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/*** MENUBAR & OPENING FILE ****** MENUBAR & OPENING FILE ****** MENUBAR & OPENING FILE ****** MENUBAR & OPENING FILE ***/
		JMenuBar menuBar = new JMenuBar();
		JMenuItem openFileItem = new JMenuItem("Open Folder...");


		openFileItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				fileChooser = new JFileChooser();
				fileChooser.setVisible(true);
				//FileNameExtensionFilter factoryRCPFilter = new FileNameExtensionFilter("Factory or RCP files", "factory", "rcp"); //only .factory, .rcp
				//fileChooser.setFileFilter(factoryRCPFilter);
				
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(FactoryFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					factoryRCPFileDirectory = fileChooser.getSelectedFile().getPath();
					//add text to TA from file and parse file
					taskBarText = parseRCPFile(factoryRCPFileDirectory);
					repaint();
					taskBarTA.setText(taskBarText);
					timer.start();
				}
			}
		});
		menuBar.add(openFileItem);
		setJMenuBar(menuBar);
		
		
		
		/*** Scrollable Taskbar ****** Scrollable Taskbar ****** Scrollable Taskbar ****** Scrollable Taskbar ***/
		taskBarTA = new JTextArea(); 
		taskBarTA.setEditable(false);
		taskBarTA.setBounds(600, 50, 200, 550);
		scrollPane = new JScrollPane(taskBarTA);	
		taskBarLbl = new JLabel("TASKBOARD");
		taskBarPanel = new JPanel();
		taskBarPanel.setLayout(new BorderLayout());
		taskBarPanel.add(taskBarLbl, BorderLayout.NORTH);
		taskBarPanel.add(scrollPane, BorderLayout.CENTER);
		
		add(taskBarPanel, BorderLayout.EAST);
		
		taskBarPanel.setPreferredSize(new Dimension(200, taskBarPanel.getParent().getHeight()));
		taskBarTA.setPreferredSize(new Dimension(200, taskBarPanel.getHeight()-taskBarLbl.getHeight()));
		
		
		
		/*** Drawing Panel ****** Drawing Panel ****** Drawing Panel ****** Drawing Panel ****** Drawing Panel ***/
		drawingPanel = new DrawingPanel();
		drawingPanel.setPreferredSize(new Dimension(600, 600));
		add(drawingPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		
		/*** TIMER ***** TIMER ***** TIMER ***** TIMER ***** TIMER ***** TIMER ***** TIMER ***** TIMER ***/
		timer = new Timer(TIMER_INTERVAL, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
//				System.out.println("TICK");
				if(workers.size() > 0){
					for(Worker w: workers){
						w.move();
					}
				}
				drawingPanel.repaint();
				totalTime += TIMER_INTERVAL;
			}
		});
	}
	
	//updates the number of materials from worker threads obtaining permits
	public static void updateNumMaterialsRemaining(int numMaterialsTaken, short material){
		//increment number taken
		if(material == Recipe.PLASTIC){
			numPlastic -= numMaterialsTaken;
		}
		else if(material == Recipe.METAL){
			numMetal -= numMaterialsTaken;
		}
		else if(material == Recipe.WOOD){
			numWood -= numMaterialsTaken;
		}
		else{
			System.out.println("I done fucked up which material constant to use");
		}
	}
	
	//updates the number of materials from worker threads obtaining permits
	public static void updateNumToolsRemaining(int numToolsTaken, short tool){
		//increment number taken
		if(tool == Recipe.SCISSOR){
			numAvailableScissors -= numToolsTaken;
		}
		else if(tool == Recipe.SCREWDRIVER){
			numAvailableScrewdrivers -= numToolsTaken;
		}
		else if(tool == Recipe.PAINTBRUSH){
			numAvailablePaintbrushes -= numToolsTaken;
		}
		else if(tool == Recipe.HAMMER){
			numAvailableHammers -= numToolsTaken;
		}
		else if(tool == Recipe.PLIER){
			numAvailablePlyers -= numToolsTaken;
		}
		else{
			System.out.println("I done fucked up which tool constant to use");
		}
	}
	
	private File findFactoryFileInFolder(final File folder) {
	    for(final File fileEntry: folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            return findFactoryFileInFolder(fileEntry);
	        } 
	        System.out.println(fileEntry.getName());
	        String extension = "";
	        int i = fileEntry.getName().lastIndexOf('.');
	        if (i > 0) {
	            extension = fileEntry.getName().substring(i+1);
	        }
	        if(extension.equalsIgnoreCase("factory")){
        		return fileEntry;
        	}
	        
	    }
	    return null;
	}
	
	
	private void findRCPFilesInFolder(final File folder) {
	    for(final File fileEntry: folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            findRCPFilesInFolder(fileEntry);
	        } 
	        System.out.println(fileEntry.getName());
	        String extension = "";
	        int i = fileEntry.getName().lastIndexOf('.');
	        if (i > 0) {
	            extension = fileEntry.getName().substring(i+1);
	        }
	        if(extension.equalsIgnoreCase("rcp")){
        		rcpFiles.add(fileEntry);
        	}
	        
	    }
	}
	
	//also parses .factory file
	public String parseRCPFile(String path) {
		String ret = ""; //what will go in the text area
		
		final File folder = new File(path);
		factoryFileName = findFactoryFileInFolder(folder);
		if(factoryFileName == null){
			System.out.println("No .factory file found in selected directory");
		}
		else{
			try {
				Scanner scan = new Scanner(factoryFileName);
				// parse the .factory file and update num tools
				while(scan.hasNext()){
					String line = scan.nextLine();
					System.out.println("LINE: " + line);
					int numTools = Integer.parseInt(line.substring(line.indexOf(':')+1, line.indexOf(']')));
					char c1 = line.charAt(1);
					if(c1 == 'W'){
						totalWorkers = numTools;
					}
					else if(c1 == 'H'){
						totalHammers = numTools;
					}
					else if(c1 == 'S'){
						if(line.charAt(3) == 'r'){
							totalScrewdrivers = numTools;
						}
						else{
							totalScissors = numTools;
						}
					}
					else if(c1 == 'P'){
						if(line.charAt(2) == 'l'){
							totalPlyers = numTools;
						}
						else{
							totalPaintbrushes = numTools;
						}
					}
				}
				
				
			} catch (FileNotFoundException e) {
				System.out.println("FNFE: " + e.getMessage());
			}
		}
		
		
		numAvailableWorkers = totalWorkers;
		numAvailableHammers = totalHammers;
		numAvailableScrewdrivers = totalScrewdrivers;
		numAvailablePlyers = totalPlyers;
		numAvailableScissors = totalScissors;
		numAvailablePaintbrushes = totalPaintbrushes;
		
		//instantiate arrayList<Worker> and start workers
		for(int i = 0; i < totalWorkers; i++){
			workers.add(new Worker());
		}
		
		findRCPFilesInFolder(folder);
		if(rcpFiles.size() == 0){
			System.out.println("NO RCP FILES FOUND IN SELECTED DIRECTORY");
		}
		else{
			for(File rcpF: rcpFiles){
				String productName = "";
				int numProducts = 0;
				int numWoodNeeded = 0;
				int numMetalNeeded = 0;
				int numPlasticNeeded = 0;
				int lineNum = 0;
				ArrayList<Recipe> instructions = new ArrayList<Recipe>();
				try {
					//parse fist line for how many things need to be built
					//parse the .rcp file for [Metal, Wood, Plastic]
					//parse the .rcp file for [Hammers, Pliers, Screwdrivers, Paintbrushes, Scissors]
					Scanner scan = new Scanner(rcpF);
					while(scan.hasNext()){
						String line = scan.nextLine();
						//System.out.println("LINE: " + line);
						
						if(lineNum == 0){
							productName += line.substring(line.indexOf('[') + 1, line.indexOf(']'));
							System.out.println("Product Name: " + productName);
							if(line.indexOf('x') == -1){
								numProducts = 1;
							}
							else{
								numProducts = (int) Character.getNumericValue(line.charAt(line.indexOf('x') + 1));
							}
						}
						else{
							if(line.charAt(1) == 'M'){
								numMetalNeeded = Integer.parseInt(line.substring(line.indexOf(':') + 1, line.indexOf(']')));
							}
							else if(line.charAt(1) == 'W'){
								numWoodNeeded = Integer.parseInt(line.substring(line.indexOf(':') + 1, line.indexOf(']')));
							}
							else if(line.charAt(1) == 'P'){
								numPlasticNeeded = Integer.parseInt(line.substring(line.indexOf(':') + 1, line.indexOf(']')));
							}
							else{
								line = line.replaceAll("\\s", ""); //get rid of all whitespace
								short hammers = 0;
								short screwdrivers = 0;
								short plyers = 0;
								short scissors = 0;
								short paintbrushes = 0;
								short totalTime = 0;
								short station = -1;
								/**
								 * "and" means there are more than just the first tool listed.
									"at" gives us a location.
									"for" gives us a duration of time.
								 */
								//parse all the tools being used and # of each
								String[] tools = line.split("and");
								for(String t: tools){
									int xAt = t.indexOf('x');
									if(xAt != -1){
										int numTools =  Integer.parseInt(t.substring(xAt - 1, xAt));
										if(t.charAt(xAt+1) == 'H'){
											hammers += numTools;
										}
										else if(t.charAt(xAt+1) == 'S'){
											if(t.charAt(xAt+3) == 'i'){
												scissors += numTools;
											}
											else if(t.charAt(xAt+3) == 'r'){
												screwdrivers += numTools;
											}
										}
										else if(t.charAt(xAt+1) == 'P'){
											if(t.charAt(xAt+2) == 'a'){
												paintbrushes += numTools;
											}
											else if(t.charAt(xAt+2) == 'l'){
												plyers += numTools;
											}
										}
									}
								}
								
								//parse the time at workstation
								String timeStr = line.substring(line.lastIndexOf('r')+1, line.lastIndexOf('s'));
								totalTime = Short.parseShort(timeStr);
								
								//parse which workstation the task is performed at
								String stationStr = "";
								int forIndex = line.indexOf("for");
								//simple case (use saw for 2s)
								if(line.indexOf("at") == -1){
									int useEndIndex = line.indexOf("Use") + 3;
									stationStr = line.substring(useEndIndex, forIndex);
								}
								//complex case (use 1x hammer at anvil for 2s)
								else{
									int atEndIndex = line.indexOf("at") + 2;
									stationStr = line.substring(atEndIndex, forIndex);
								}
								
								if(stationStr.equalsIgnoreCase("Anvil")){
									station = Recipe.ANVIL;
								}
								else if(stationStr.equalsIgnoreCase("Workbench")){
									station = Recipe.WORKBENCH;
								}
								else if(stationStr.equalsIgnoreCase("Painting Station") || stationStr.equalsIgnoreCase("PaintingStation")){
									station = Recipe.PAINTING_STATION;
								}
								else if(stationStr.equalsIgnoreCase("Press")){
									station = Recipe.PRESS;
								}
								else if(stationStr.equalsIgnoreCase("Saw")){
									station = Recipe.SAW;
								}
								else if(stationStr.equalsIgnoreCase("Furnace")){
									station = Recipe.FURNACE;
								}
								Recipe rec = new Recipe(hammers, plyers, screwdrivers, paintbrushes, scissors, totalTime, station);
								instructions.add(rec);
								System.out.println(rec);
							}
						}
						
						
						
						lineNum++;
					}
					numWood += numWoodNeeded;
					numMetal += numMetalNeeded;
					numPlastic += numPlasticNeeded;
					
					
					for(int i = 0; i < numProducts; i++){
						products.add(new Product(productName, numWoodNeeded, numMetalNeeded, numPlasticNeeded, instructions));
					}
					
				} catch (FileNotFoundException e) {
					System.out.println("FNFE: " + e.getMessage());
				}
			}
		}
		
		//add to ret which will be displayed in TA
		for(Product p: products){
			ret += p.getProductName() + "... " + p.getBuildStatusString() + "\n";
		}
		
		//create materials semaphores
		woodAvailable = new Semaphore(numWood, true);
		metalAvailable = new Semaphore(numMetal, true);
		plasticAvailable = new Semaphore(numPlastic, true);
		
		//create tools semaphores
		hammersAvailable = new Semaphore(totalHammers, true);
		screwdriversAvailable = new Semaphore(totalScrewdrivers, true);
		plyersAvailable = new Semaphore(totalPlyers, true);
		scissorsAvailable = new Semaphore(totalScissors, true);
		paintbrushesAvailable = new Semaphore(totalPaintbrushes, true);
		
		return ret;
	}

	
	public static Product getTask(){
		for(Product p: products){
			if(p.getBuildStatus() == Product.NOT_BUILT){
				p.changeBuildStatus(Product.IN_PROGRESS);
				p.setKeyCode(productKeyCode);
				productKeyCode++;
				updateTATaskbar();
				return p;
			}
		}
		return null;
	}
	
	private static void updateTATaskbar() {
		String taText = "";
		for(Product p: products){
			taText += p.getProductName() + "... " + p.getBuildStatusString() + "\n";
		}
		taskBarTA.setText(taText);
	}

	public static void productFinished(Product finishedProduct){
		for(Product p: products){
			if(finishedProduct.getKeyCode() == p.getKeyCode()){
				p.changeBuildStatus(Product.COMPLETE);
			}
		}
		updateTATaskbar();
		
		//check to see if all tasks are completed
		boolean allDone = true;
		for(Product p: products){
			if(p.getBuildStatus() != Product.COMPLETE){
				allDone = false;
			}
		}
		if(allDone){ 
			/**
			 * Once all recipes have been made, remove the workers from the map.
				Create a pop-up that displays the time the simulation took, 
				and prompt the user to choose a new directory, or exit the program.
				If they choose a new directory, the simulation will restart.
				Otherwise, the program quits. 
			 */
			timer.stop();
			workers.removeAll(workers);
			drawingPanel.repaint();
			long totalTimeInSeconds = totalTime / 1000;
			Object[] options = {"New Simulation", "Quit"};
			int n = JOptionPane.showOptionDialog(null,
					"The simulation took " + totalTimeInSeconds + " seconds. " +
							"\nWould you like to start a new simulation or quit?",
					"Quit or New Simulation",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[1]); //default button title
			if(n == 0){
				//restart simulation & pick user picks new directory
				resetAllValues();
			}
			else{
				System.exit(0);
			}
		}
	}
	
	
 	private static void resetAllValues() {
		// user opens up filechooser box
		numWood = 0;
		numMetal = 0;
		numPlastic = 0;
		
		rcpFiles.removeAll(rcpFiles);
		products.removeAll(products);
		workers.removeAll(workers);
		
		productKeyCode = 0;
		
		totalTime = 0;
		totalWorkers = 0;
		totalHammers = 0;
		totalScissors = 0;
		totalPaintbrushes = 0;
		totalPlyers = 0;
		totalScrewdrivers = 0;
		
		taskBarTA.setText("");
	}


	private class DrawingPanel extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			createMaterialContainers(g);
			createWorkAreas(g);
			updateWorkers(g);
		}
		
		
		private void updateWorkers(Graphics g){
			//each worker is 40x40
			for(Worker w: workers){
				int x = w.getXLocation();
				int y = w.getYLocation();
				g.setColor(Color.WHITE);
				g.fillRect(x, y, 40, 40);
				g.setColor(Color.BLACK);
				g.fillRect(x+10, y+5, 14, 14);
				g.fillRect(x+15, y+19, 4, 14);
				g.fillRect(x+3, y+22, 28, 6);
				g.fillRect(x+5, y+32, 10, 5);
				g.fillRect(x+19, y+32, 10, 5);
			}
		}
		
		private void createWorkAreas(Graphics g){
			//each row spaced 70 apart
			//each column spaced 160 apart
			
			
			//anvil pictures -- black, white, gray, black
			g.setColor(Color.BLACK);			//framing
			g.fillRect(170, 150, 40, 40);
			g.fillRect(240, 150, 40, 40);
			g.setColor(Color.WHITE);
			g.fillRect(173, 153, 34, 34);
			g.fillRect(243, 153, 34, 34);
			g.setColor(Color.GRAY);
			g.fillRect(176, 156, 28, 28);
			g.fillRect(246, 156, 28, 28);
			g.setColor(Color.BLACK);			//anvil
			g.fillRect(176, 170, 28, 8);
			g.fillRect(184, 178, 12, 6);
			g.fillRect(246, 170, 28, 8);
			g.fillRect(254, 178, 12, 6);
			
			//workbench pictures -- black, gray, white, brown
			g.setColor(Color.BLACK);			//framing
			g.fillRect(310, 150, 40, 40);
			g.fillRect(380, 150, 40, 40);
			g.fillRect(450, 150, 40, 40);
			g.setColor(Color.GRAY);
			g.fillRect(313, 153, 34, 34);
			g.fillRect(383, 153, 34, 34);
			g.fillRect(453, 153, 34, 34);
			g.setColor(Color.WHITE);
			g.fillRect(316, 156, 28, 28);
			g.fillRect(386, 156, 28, 28);
			g.fillRect(456, 156, 28, 28);
			g.setColor(new Color(205, 133, 63)); //workbench
			g.fillRect(316, 170, 28, 8);
			g.fillRect(386, 170, 28, 8);
			g.fillRect(456, 170, 28, 8);
			g.fillRect(318, 178, 4, 6);
			g.fillRect(388, 178, 4, 6);
			g.fillRect(458, 178, 4, 6);
			g.fillRect(338, 178, 4, 6);
			g.fillRect(408, 178, 4, 6);
			g.fillRect(478, 178, 4, 6);
			
			//furnace pictures -- black, white, gray, black, red
			g.setColor(Color.BLACK);			//frame
			g.fillRect(170, 310, 40, 40);
			g.fillRect(240, 310, 40, 40);
			g.setColor(Color.WHITE);
			g.fillRect(173, 313, 34, 34);
			g.fillRect(243, 313, 34, 34);
			g.setColor(Color.GRAY);
			g.fillRect(176, 316, 28, 28);
			g.fillRect(246, 316, 28, 28);
			g.setColor(Color.BLACK);			//furnace
			g.fillRect(176, 336, 28, 8);
			g.fillRect(246, 336, 28, 8);
			g.fillArc(178, 316, 22, 40, 0, 180);
			g.fillArc(248, 316, 22, 40, 0, 180);
			g.setColor(new Color(255,165,0));
			g.fillRect(181, 328, 16, 3);
			g.fillRect(251, 328, 16, 3);
			g.setColor(new Color(204,51,51));
			g.fillRect(187, 326, 4, 4);
			g.fillRect(257, 326, 4, 4);
			
			//tablesaw pictures -- workbench plus gray saw on table
			g.setColor(Color.BLACK);			//frame
			g.fillRect(310, 310, 40, 40);
			g.fillRect(380, 310, 40, 40);
			g.fillRect(450, 310, 40, 40);
			g.setColor(Color.GRAY);
			g.fillRect(313, 313, 34, 34);
			g.fillRect(383, 313, 34, 34);
			g.fillRect(453, 313, 34, 34);
			g.setColor(Color.WHITE);
			g.fillRect(316, 316, 28, 28);
			g.fillRect(386, 316, 28, 28);
			g.fillRect(456, 316, 28, 28);
			g.setColor(new Color(205, 133, 63)); //workbench
			g.fillRect(316, 330, 28, 8);
			g.fillRect(386, 330, 28, 8);
			g.fillRect(456, 330, 28, 8);
			g.fillRect(318, 338, 4, 6);
			g.fillRect(388, 338, 4, 6);
			g.fillRect(458, 338, 4, 6);
			g.fillRect(338, 338, 4, 6);
			g.fillRect(408, 338, 4, 6);
			g.fillRect(478, 338, 4, 6);
			g.setColor(new Color(192,192,192));   //saw
			g.fillRect(318, 326, 8, 4);
			g.fillRect(320, 324, 2, 2);
			g.fillRect(388, 326, 8, 4);
			g.fillRect(390, 324, 2, 2);
			g.fillRect(458, 326, 8, 4);
			g.fillRect(460, 324, 2, 2);
			
			//painting station pictures -- black, white, gray, white, colors
			g.setColor(Color.BLACK);
			g.fillRect(170, 470, 40, 40);
			g.fillRect(240, 470, 40, 40);
			g.fillRect(310, 470, 40, 40);
			g.fillRect(380, 470, 40, 40);
			g.setColor(Color.WHITE);
			g.fillRect(173, 473, 34, 34);
			g.fillRect(243, 473, 34, 34);
			g.fillRect(313, 473, 34, 34);
			g.fillRect(383, 473, 34, 34);
			g.setColor(Color.GRAY);
			g.fillRect(176, 476, 28, 28);
			g.fillRect(246, 476, 28, 28);
			g.fillRect(316, 476, 28, 28);
			g.fillRect(386, 476, 28, 28);
			g.setColor(Color.WHITE);
			g.fillOval(179, 479, 22, 22);
			g.fillOval(194, 479, 7, 7);
			g.fillOval(249, 479, 22, 22);
			g.fillOval(264, 479, 7, 7);
			g.fillOval(319, 479, 22, 22);
			g.fillOval(334, 479, 7, 7);
			g.fillOval(389, 479, 22, 22);
			g.fillOval(404, 479, 7, 7);
			g.setColor(Color.RED);
			g.fillOval(181, 490, 5, 5);
			g.fillOval(251, 490, 5, 5);
			g.fillOval(321, 490, 5, 5);
			g.fillOval(391, 490, 5, 5);
			g.setColor(Color.CYAN);
			g.fillOval(185, 484, 5, 5);
			g.fillOval(255, 484, 5, 5);
			g.fillOval(325, 484, 5, 5);
			g.fillOval(395, 484, 5, 5);
			g.setColor(Color.BLACK);
			g.fillOval(192, 480, 5, 5);
			g.fillOval(262, 480, 5, 5);
			g.fillOval(332, 480, 5, 5);
			g.fillOval(402, 480, 5, 5);
			g.setColor(Color.GREEN);
			g.fillOval(185, 495, 5, 5);
			g.fillOval(255, 495, 5, 5);
			g.fillOval(325, 495, 5, 5);
			g.fillOval(395, 495, 5, 5);
			g.setColor(new Color(240,128,128));
			g.fillOval(192, 495, 5, 5);
			g.fillOval(262, 495, 5, 5);
			g.fillOval(332, 495, 5, 5);
			g.fillOval(402, 495, 5, 5);
			g.setColor(new Color(255,20,147));
			g.fillOval(194, 488, 5, 5);
			g.fillOval(264, 488, 5, 5);
			g.fillOval(334, 488, 5, 5);
			g.fillOval(404, 488, 5, 5);
			
			//press pictures -- black, gray, white, black
			g.setColor(Color.BLACK);
			g.fillRect(450, 470, 40, 40);
			g.setColor(Color.GRAY);
			g.fillRect(453, 473, 34, 34);
			g.setColor(Color.WHITE);
			g.fillRect(456, 476, 28, 28);
			g.setColor(Color.BLACK);
			g.fillRect(461, 491, 18, 6);
			g.fillRect(466, 476, 8, 20);
			
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			//create labels below work areas
			g.drawString("Anvils", 200, 210);
			g.drawString("Work Benches", 325, 210);
			g.drawString("Furnaces", 190, 370);
			g.drawString("Table Saws", 340, 370);
			g.drawString("Painting Stations", 220, 530);
			g.drawString("Press", 445, 530);
			
			//update work area labels above work areas
			
			//anvils
			if(anvil1Occupied){
				g.setColor(Color.RED);
				g.drawString(anvil1TimeRemaining + "s", 170, 140);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 170, 140);
			}
			if(anvil2Occupied){
				g.setColor(Color.RED);
				g.drawString(anvil2TimeRemaining + "s", 240, 140);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 240, 140);
			}
			
			//workbenches
			if(workbench1Occupied){
				g.setColor(Color.RED);
				g.drawString(workbench1TimeRemaining + "s", 310, 140);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 310, 140);
			}
			if(workbench2Occupied){
				g.setColor(Color.RED);
				g.drawString(workbench2TimeRemaining + "s", 380, 140);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 380, 140);
			}
			if(workbench3Occupied){
				g.setColor(Color.RED);
				g.drawString(workbench3TimeRemaining + "s", 450, 140);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 450, 140);
			}
			
			
			//furnaces
			if(furnace1Occupied){
				g.setColor(Color.RED);
				g.drawString(furnace1TimeRemaining + "s", 170, 300);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 170, 300);
			}
			if(furnace2Occupied){
				g.setColor(Color.RED);
				g.drawString(furnace2TimeRemaining + "s", 240, 300);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 240, 300);
			}
			
			
			//tablesaws
			if(tablesaw1Occupied){
				g.setColor(Color.RED);
				g.drawString(tablesaw1TimeRemaining + "s", 310, 300);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 310, 300);
			}
			if(tablesaw2Occupied){
				g.setColor(Color.RED);
				g.drawString(tablesaw2TimeRemaining + "s", 380, 300);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 380, 300);
			}
			if(tablesaw3Occupied){
				g.setColor(Color.RED);
				g.drawString(tablesaw3TimeRemaining + "s", 450, 300);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 450, 300);
			}
			
			
			//painting stations
			if(paintingStation1Occupied){
				g.setColor(Color.RED);
				g.drawString(paintingStation1TimeRemaining + "s", 170, 460);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 170, 460);
			}
			if(paintingStation2Occupied){
				g.setColor(Color.RED);
				g.drawString(paintingStation2TimeRemaining + "s", 240, 460);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 240, 460);
			}
			if(paintingStation3Occupied){
				g.setColor(Color.RED);
				g.drawString(paintingStation3TimeRemaining + "s", 310, 460);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 310, 460);
			}
			if(paintingStation4Occupied){
				g.setColor(Color.RED);
				g.drawString(paintingStation4TimeRemaining + "s", 380, 460);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 380, 460);
			}
			
			
			//presses
			if(press1Occupied){
				g.setColor(Color.RED);
				g.drawString(press1TimeRemaining + "s", 450, 460);
			}
			else{
				g.setColor(Color.GREEN);
				g.drawString("Open", 450, 460);
			}
		}

		private void createMaterialContainers(Graphics g) {
			// draw 3 black boxes in row with 3 gray smaller ones on top, 
			//with orange, purple, white: wood, metal, plastic
			g.setColor(Color.BLACK);
			g.fillRect(140, 30, 40, 40);
			g.fillRect(280, 30, 40, 40);
			g.fillRect(420, 30, 40, 40);
			
			g.setColor(new Color(230,160,25));
			g.fillRect(142, 32, 36, 36);
			g.setColor(new Color(86,75,148));
			g.fillRect(282, 32, 36, 36);
			g.setColor(Color.WHITE);
			g.fillRect(422, 32, 36, 36);
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(145, 35, 30, 30);
			g.fillRect(285, 35, 30, 30);
			g.fillRect(425, 35, 30, 30);
			
			//drawstring for the wood, metal and plastic lbls
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Wood", 135, 25);
			g.drawString("Metal", 276, 25);
			g.drawString("Plastic", 412, 25);
			
			g.drawString("" + numWood, 155, 55);
			g.drawString("" + numMetal, 295, 55);
			g.drawString("" + numPlastic, 435, 55);
			

			// draw 5 black boxes in row with 5 gray smaller ones on top, 
			//with green, orange, red, purple, blue: screwdriver, hammer, paintbrush, plyers, scissors 
			g.setColor(Color.BLACK);
			g.fillRect(20, 140, 40, 40);
			g.fillRect(20, 220, 40, 40);
			g.fillRect(20, 300, 40, 40);
			g.fillRect(20, 380, 40, 40);
			g.fillRect(20, 460, 40, 40);

			g.setColor(Color.GREEN);
			g.fillRect(22, 142, 36, 36);
			g.setColor(Color.ORANGE);
			g.fillRect(22, 222, 36, 36);
			g.setColor(Color.RED);
			g.fillRect(22, 302, 36, 36);
			g.setColor(new Color(75,30,190));
			g.fillRect(22, 382, 36, 36);
			g.setColor(Color.CYAN);
			g.fillRect(22, 462, 36, 36);

			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(25, 145, 30, 30);
			g.fillRect(25, 225, 30, 30);
			g.fillRect(25, 305, 30, 30);
			g.fillRect(25, 385, 30, 30);
			g.fillRect(25, 465, 30, 30);

			//drawstring for the screwdriver, hammer, paintbrush, plyers, scissors lbls
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Screwdrivers", 10, 130);
			g.drawString("Hammers", 10, 210);
			g.drawString("Paintbrushes", 10, 290);
			g.drawString("Plyers", 10, 370);
			g.drawString("Scissors", 10, 450);
			
			//display the number of each tool
			g.drawString(numAvailableScrewdrivers + "/" + totalScrewdrivers, 25, 165);
			g.drawString(numAvailableHammers + "/" + totalHammers, 25, 245);
			g.drawString(numAvailablePaintbrushes + "/" + totalPaintbrushes, 25, 325);
			g.drawString(numAvailablePlyers + "/" + totalPlyers, 25, 405);
			g.drawString(numAvailableScissors + "/" + totalScissors, 25, 485);
		}
	}


	public static void main(String[] args) {
		new FactoryFrame();

	}
}


