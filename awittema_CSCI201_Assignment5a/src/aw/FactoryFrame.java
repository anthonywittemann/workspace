/**
 * 
 */
package aw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author anthonywittemann
 *
 */
public class FactoryFrame extends JFrame{
	
	private JFileChooser fileChooser;
	private String factoryRCPFileDirectory;
	private JScrollPane scrollPane;
	private JTextArea taskBarTA;
	private String taskBarText;
	private JPanel taskBarPanel; //contains scrollPane, taskbar label
	private JLabel taskBarLbl;
	private File factoryFileName;
	
	private JPanel drawingPanel;
	
	//used to update inventory labels
	private int[] workers;
	private int[] hammers;
	private int[] screwdrivers;
	private int[] plyers;
	private int[] scissors;
	private int[] paintbrushes;
	//get these values from .factory file
	private int totalWorkers;
	private int totalHammers;
	private int totalScrewdrivers;
	private int totalPlyers;
	private int totalScissors;
	private int totalPaintbrushes;
	private int numWorkers;
	private int numHammers;
	private int numScrewdrivers;
	private int numPlyers;
	private int numScissors;
	private int numPaintbrushes;
	
	public static final int NUM_ANVILS = 2;
	public static final int NUM_WORKBENCHES = 3;
	public static final int NUM_FURNACES = 2;
	public static final int NUM_TABLESAWS = 3;
	public static final int NUM_PAINTING_STATIONS = 4;
	public static final int NUM_PRESSES = 1;
	
	
	//used for updating the work area labels
	private boolean anvil1Occupied = false;
	private boolean anvil2Occupied = false;
	private boolean workbench1Occupied = false;
	private boolean workbench2Occupied = false;
	private boolean workbench3Occupied = false;
	private boolean furnace1Occupied = false;
	private boolean furnace2Occupied = false;
	private boolean tablesaw1Occupied = false;
	private boolean tablesaw2Occupied = false;
	private boolean tablesaw3Occupied = false;
	private boolean paintingStation1Occupied = false;
	private boolean paintingStation2Occupied = false;
	private boolean paintingStation3Occupied = false;
	private boolean paintingStation4Occupied = false;
	private boolean press1Occupied = false;
	
	
	private int anvil1TimeRemaining = 0;
	private int anvil2TimeRemaining = 0;
	private int workbench1TimeRemaining = 0;
	private int workbench2TimeRemaining = 0;
	private int workbench3TimeRemaining = 0;
	private int furnace1TimeRemaining = 0;
	private int furnace2TimeRemaining = 0;
	private int tablesaw1TimeRemaining = 0;
	private int tablesaw2TimeRemaining = 0;
	private int tablesaw3TimeRemaining = 0;
	private int paintingStation1TimeRemaining = 0;
	private int paintingStation2TimeRemaining = 0;
	private int paintingStation3TimeRemaining = 0;
	private int paintingStation4TimeRemaining = 0;
	private int press1TimeRemaining = 0;
	
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
					//TODO add text to TA from file and parse file
					taskBarText = parseRCPFile(factoryRCPFileDirectory);
					repaint();
					taskBarTA.setText(taskBarText);
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
	
	//also parse .factory file
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
				//TODO parse the .factory file and update num tools
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
		
		
		numWorkers = totalWorkers;
		numHammers = totalHammers;
		numScrewdrivers = totalScrewdrivers;
		numPlyers = totalPlyers;
		numScissors = totalScissors;
		numPaintbrushes = totalPaintbrushes;
		
		//TODO add to ret which will be displayed in TA
		
		
		
		
		// TODO PARSE THE RCP FILES
		/** FOR PARSING
		 * In the .factory file you will have listed:
			Workers
			Hammers
			Screwdrivers
			Pliers
			Scissors
			Paintbrushes

			For .rcp files, materials will be
			Metal
			Plastic
			Wood

			Tools are:
			Hammer/Hammers
			Plier/Pliers
			Screwdriver/Screwdrivers
			Paintbrush/Paintbrushes
			Scissor/Scissors

			Stations are:
			Saw
			Press
			Workbench
			Painting Station
			Anvil
			Press
		 */
		
		return ret;
	}


	public static void main(String[] args) {
		new FactoryFrame();

	}
	
	private class DrawingPanel extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			createMaterialContainers(g);
			createWorkAreas(g);
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
			g.drawString(numScrewdrivers + "/" + totalScrewdrivers, 25, 165);
			g.drawString(numHammers + "/" + totalHammers, 25, 245);
			g.drawString(numPaintbrushes + "/" + totalPaintbrushes, 25, 325);
			g.drawString(numPlyers + "/" + totalPlyers, 25, 405);
			g.drawString(numScissors + "/" + totalScissors, 25, 485);
		}
	}

}


