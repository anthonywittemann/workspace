package aw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
import java.util.ArrayList;

public class MainFrame extends JFrame{

	private JFileChooser fileChooser;
	private File xmlFile;
	private Object[][] rowData;
	private static final Object[] columnNames  = {"Car #", "X", "Y"};
	protected JPanel drawingPanel; //protected so that subclasses and inner classes can paint/update GUI
	private JTable carLocationTable;
	private DefaultTableModel carTableModel;
	private JPanel carTablePanel;
	private JPanel mainPanel;
	private int xGridCenter = 300;
	private int yGridCenter = 300;
	private ArrayList<Tile> gridTiles = new ArrayList<Tile>();
	private ArrayList<Car> cars = new ArrayList<Car>();

	//TODO 
	// 	9) MOVE CARS, CREATE CAR AI CLASSES

	public MainFrame(){
		//set up the window and the drawing panel
		setSize(800,600);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(800, 600));
		setTitle("Roadway Simulator with Limited Edition CHRISTMAS LIGHTS!!!!!!!!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout()); //use GridLayout to separate Table

		drawingPanel = new DrawingPanel();
		drawingPanel.setBackground(Color.LIGHT_GRAY);
		drawingPanel.setPreferredSize(new Dimension(600, 600));




		/*** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ***/
		JMenuBar menuBar = new JMenuBar();
		JMenuItem openFileItem = new JMenuItem("Open File...");


		openFileItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				fileChooser = new JFileChooser();
				fileChooser.setVisible(true);
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
				fileChooser.setFileFilter(xmlFilter);
				int returnVal = fileChooser.showOpenDialog(MainFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					xmlFile = fileChooser.getSelectedFile();
//					System.out.println("You chose to open this file: " + xmlFile.getName());
					//update GUI and start timer
					parseCarsXML();
					drawingPanel.revalidate();
					drawingPanel.repaint();
				}

			}

		});
		menuBar.add(openFileItem);
		setJMenuBar(menuBar);



		/*** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ***/
		rowData = new Object[51][3]; //I'm assuming no more than 50 cars will be added
		rowData[0][0] = "Car #"; rowData[0][1] = "X"; rowData[0][2] = "Y";

		carTableModel = new DefaultTableModel(rowData, columnNames){ //allows for double clicking
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};

		carLocationTable = new JTable(carTableModel);
		carLocationTable.setPreferredSize(new Dimension(200, mainPanel.getHeight()));
		carLocationTable.setGridColor(Color.BLACK);
		carLocationTable.setPreferredScrollableViewportSize(new Dimension(200, mainPanel.getHeight())); 
		carTablePanel = new JPanel();
		carTablePanel.setPreferredSize(new Dimension(200, mainPanel.getHeight()));




		/****** ADD COMPONENTS TO JPANELS AND GUI ****** ADD COMPONENTS TO JPANELS AND GUI ****** ADD COMPONENTS TO JPANELS AND GUI ******/
		menuBar.add(openFileItem);
		setJMenuBar(menuBar);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		Dimension drawingPanelDimensions = drawingPanel.getPreferredSize();
		drawingPanel.setBounds(0, 0, drawingPanelDimensions.width, drawingPanelDimensions.height);
		mainPanel.add(drawingPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		carTablePanel.add(carLocationTable);
		mainPanel.add(carTablePanel, gbc);



		/****** RESIZE COMPONENTS WHEN WINDOW RESIZED ****** RESIZE COMPONENTS WHEN WINDOW RESIZED ****** RESIZE COMPONENTS WHEN WINDOW RESIZED ******/
		mainPanel.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent ce){
				carLocationTable.setPreferredSize(new Dimension(200,mainPanel.getHeight()));
				carTablePanel.setPreferredSize(new Dimension(200, mainPanel.getHeight()));
				drawingPanel.setPreferredSize(new Dimension(mainPanel.getWidth() - 200, mainPanel.getHeight()));
				//update center point here
				xGridCenter = drawingPanel.getWidth()/2;
				yGridCenter = drawingPanel.getHeight()/2;
			
				//refresh the GUI
				mainPanel.revalidate();
				mainPanel.repaint();
				
			}
		});

		add(mainPanel);
		pack();
		setVisible(true);
		
	}

	
	
	/****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ******/
	public void parseCarsXML(){
		try {
			if(xmlFile != null){
				File carsXmlFile = new File(xmlFile.getAbsolutePath());
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(carsXmlFile);

				doc.getDocumentElement().normalize();

//				System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
				if (doc.hasChildNodes()) {

					loadNodes(doc.getChildNodes(), null, null, null, null);

				}
//				for(Tile t: gridTiles){
//					System.out.println(t.toString());
//				}
//				System.out.println("\n\n\n");
//				for(Car c: cars){
//					System.out.println(c.toString());
//				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//loadNodes into Tiles and Tiles into ArrayList, car and cars into ArrayList
	//includes variables from parent nodes that hold important information for creating tile/car object
	private void loadNodes(NodeList nodeList, String pRow, String pAi, String pColor, String pSpeed) {

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);

			//create variables for data attributes to go into tile, car objects
			//fill variables from higher nodes if called recursively
			String row = null;
			if(pRow != null){
				row = pRow;
			}
			
			String type = null;	
			String column = null;
			String rotation = null;
			
			String ai = null;
			if(pAi != null){
				ai = pAi;
			}
			String color = null;
			if(pColor != null){
				color = pColor;
			}
			String speed = null;
			if(pSpeed != null){
				speed = pSpeed;
			}
			
			String xLoc = null;
			String yLoc = null;
			
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name
				//Nodes can be grid, tiles, rows, car, location, roadway, cars
//				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				
//				if(tempNode.getNodeName().equals("row")){
//					row = tempNode.getAttributes().item(0).getNodeValue();
//				}

				if (tempNode.hasAttributes()) {

					// get attributes names
					NamedNodeMap nodeMap = tempNode.getAttributes();
					
					//loop through all attributes and store values in variables to be used for car/tile object
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						String nodeName = node.getNodeName();
//						System.out.println("attr name : " + nodeName);
						
						if(nodeName.equals("label")){
							row = node.getNodeValue();
						}
						
						
						if(nodeName.equals("type")){
							type = node.getNodeValue();
						}
						else if(nodeName.equals("degree")){
							rotation = node.getNodeValue();
						}
						else if(nodeName.equals("column")){
							column = node.getNodeValue();
						}
						
						else if(nodeName.equals("ai")){
							ai = node.getNodeValue();
						}
						else if(nodeName.equals("color")){
							color = node.getNodeValue();
						}
						else if(nodeName.equals("speed")){
							speed = node.getNodeValue();
						}
						
						else if(nodeName.equals("x")){
							xLoc = node.getNodeValue();
						}
						else if(nodeName.equals("y")){
							yLoc = node.getNodeValue();
						}
					}
					
					
					

				}

				if (tempNode.hasChildNodes()) {
//					System.out.println("*******************CHILD NODES********************");
					// loop again if has child nodes
					loadNodes(tempNode.getChildNodes(), row, ai, color, speed);
//					System.out.println("\\\\\\\\\\\\\\\\\\CHILD NODES//////////////////////");

				}

//				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
				//add new tile or car to arraylist or thread
				if(type != null && row != null && column != null && rotation != null){
					gridTiles.add(new Tile(type, row, column, rotation));
				}
				else if(ai != null && color != null && speed != null && xLoc != null && yLoc != null){
					int carNumber = cars.size() + 1;
					Tile currentTile = getCurrentTile(Integer.valueOf(xLoc), yLoc.charAt(0));
					Car newCar = new Car(color, ai, speed, xLoc, yLoc, carNumber, currentTile.getTileType(), currentTile.getRotation());
					cars.add(newCar);
					
					
					
					//load data into table
					carTableModel.setValueAt(xLoc, carNumber, 1);
					carTableModel.setValueAt(yLoc, carNumber, 2);
					carTableModel.setValueAt(carNumber, carNumber, 0);
					
					CarThread carThread = new CarThread(newCar);
					carThread.start();
				}
			}

		}
	}
	
	
	
	//returns the x coordinate of the top left corner of the tile
	private int getXCor(int yCor){
		if(yCor == 1){
			return xGridCenter - 225;
		}
		else if(yCor == 2){
			return xGridCenter - 175;
		}
		else if(yCor == 3){
			return xGridCenter - 125;
		}
		else if(yCor == 4){
			return xGridCenter - 75;
		}
		else if(yCor == 5){
			return xGridCenter - 25;
		}
		else if(yCor == 6){
			return xGridCenter + 25;
		}
		else if(yCor == 7){
			return xGridCenter + 75;
		}
		else if(yCor == 8){
			return xGridCenter + 125;
		}
		else if(yCor == 9){
			return xGridCenter + 175;
		}
		return -1;
	}
	//returns the y coordinate of the top left corner of the tile
	private int getYCor(char row){
		if(row == 'A'){
			return yGridCenter - 225;
		}
		else if(row == 'B'){
			return yGridCenter - 175;
		}
		else if(row == 'C'){
			return yGridCenter - 125;
		}
		else if(row == 'D'){
			return yGridCenter - 75;
		}
		else if(row == 'E'){
			return yGridCenter - 25;
		}
		else if(row == 'F'){
			return yGridCenter + 25;
		}
		else if(row == 'G'){
			return yGridCenter + 75;
		}
		else if(row == 'H'){
			return yGridCenter + 125;
		}
		else if(row == 'I'){
			return yGridCenter + 175;
		}
		return -1;
	}

	//matches the car with the tile it is on based on location
	private Tile getCurrentTile(int xLoc, char yLoc){
		for(Tile t: gridTiles){
			if(t.getRow() == yLoc && t.getColumn() == xLoc){
				return t;
			}
		}
		return null;
	}
	
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawTiles(g);
			drawGrid(g); //also draws grid labels
		}

		public void drawGrid(Graphics g){
			g.setColor(Color.BLACK);
			
			g.drawLine(xGridCenter - 225, yGridCenter - 225, xGridCenter + 225, yGridCenter - 225); //top horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter - 175, xGridCenter + 225, yGridCenter - 175); //top-1 horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter - 125, xGridCenter + 225, yGridCenter - 125); //top-2 horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter - 75, xGridCenter + 225, yGridCenter - 75); //top-3 horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter - 25, xGridCenter + 225, yGridCenter - 25); //top-4 horizontal line

			g.drawLine(xGridCenter - 225, yGridCenter + 225, xGridCenter + 225, yGridCenter + 225); //bottom horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter + 175, xGridCenter + 225, yGridCenter + 175); //bottom-1 horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter + 125, xGridCenter + 225, yGridCenter + 125); //bottom-2 horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter + 75, xGridCenter + 225, yGridCenter + 75); //bottom-3 horizontal line
			g.drawLine(xGridCenter - 225, yGridCenter + 25, xGridCenter + 225, yGridCenter + 25); //bottom-4 horizontal line

			g.drawLine(xGridCenter - 225, yGridCenter - 225, xGridCenter - 225, yGridCenter + 225); //left vertical line
			g.drawLine(xGridCenter - 175, yGridCenter - 225, xGridCenter - 175, yGridCenter + 225); //left+1 vertical line
			g.drawLine(xGridCenter - 125, yGridCenter - 225, xGridCenter - 125, yGridCenter + 225); //left+2 vertical line
			g.drawLine(xGridCenter - 75, yGridCenter - 225, xGridCenter - 75, yGridCenter + 225); //left+3 vertical line
			g.drawLine(xGridCenter - 25, yGridCenter - 225, xGridCenter - 25, yGridCenter + 225); //left+4 vertical line

			g.drawLine(xGridCenter + 225, yGridCenter - 225, xGridCenter + 225, yGridCenter + 225); //right vertical line
			g.drawLine(xGridCenter + 175, yGridCenter - 225, xGridCenter + 175, yGridCenter + 225); //right-1 vertical line
			g.drawLine(xGridCenter + 125, yGridCenter - 225, xGridCenter + 125, yGridCenter + 225); //right-2 vertical line
			g.drawLine(xGridCenter + 75, yGridCenter - 225, xGridCenter + 75, yGridCenter + 225); //right-3 vertical line
			g.drawLine(xGridCenter + 25, yGridCenter - 225, xGridCenter + 25, yGridCenter + 225); //right-4 vertical line
			
			//grid labels
			g.drawString("A", xGridCenter - 250, yGridCenter - 200);
			g.drawString("B", xGridCenter - 250, yGridCenter - 150);
			g.drawString("C", xGridCenter - 250, yGridCenter - 100);
			g.drawString("D", xGridCenter - 250, yGridCenter - 50);
			g.drawString("E", xGridCenter - 250, yGridCenter);
			g.drawString("F", xGridCenter - 250, yGridCenter + 50);
			g.drawString("G", xGridCenter - 250, yGridCenter + 100);
			g.drawString("H", xGridCenter - 250, yGridCenter + 150);
			g.drawString("I", xGridCenter - 250, yGridCenter + 200);
			
			g.drawString("1", xGridCenter - 200, yGridCenter - 250);
			g.drawString("2", xGridCenter - 150, yGridCenter - 250);
			g.drawString("3", xGridCenter - 100, yGridCenter - 250);
			g.drawString("4", xGridCenter - 50, yGridCenter - 250);
			g.drawString("5", xGridCenter, yGridCenter - 250);
			g.drawString("6", xGridCenter + 50, yGridCenter - 250);
			g.drawString("7", xGridCenter + 100, yGridCenter - 250);
			g.drawString("8", xGridCenter + 150, yGridCenter - 250);
			g.drawString("9", xGridCenter + 200, yGridCenter - 250);
		}
		
		
		public void drawTiles(Graphics g){
			for(Tile t: gridTiles){
				//get the location of the top left corner, rotation of the tile
				int xC = getXCor(t.getColumn());
				int yC = getYCor(t.getRow());
				int rot = t.getRotation();
				
				//draw the green rectangle for the tile
				g.setColor(Color.GREEN);
				g.fillRect(xC, yC, 50, 50);
				
				//draw the black rectangles based on rotation
				g.setColor(Color.BLACK);
				if(t.getType().equals("blank")){ 
					//draw a green square, rotation doesn't matter
				}
				else if(t.getType().equals("i")){			//I configuration I I I I I I I
					if(rot == 0 || rot == 180){
						g.fillRect(xC+16, yC, 18, 50);
					}
					else if(rot == 90 || rot == 270){
						g.fillRect(xC, yC+16, 50, 18);
					}
				}
				else if(t.getType().equals("l")){ 			//L configuration L L L L L L L 
					if(rot == 0){
						g.fillRect(xC+16, yC, 18, 34);
						g.fillRect(xC+34, yC+16, 16, 18);
					}
					else if(rot == 90){
						g.fillRect(xC, yC+16, 34, 18);
						g.fillRect(xC+16, yC, 18, 16);
					}
					else if(rot == 180){
						g.fillRect(xC+16, yC+16, 18, 34);
						g.fillRect(xC, yC+16, 16, 18);
					}
					else if(rot == 270){
						g.fillRect(xC+16, yC+16, 34, 18);
						g.fillRect(xC+16, yC+34, 18, 16);
					}
				}
				else if(t.getType().equals("t")){			//T configuration T T T T T T T
					//get the rotation and draw green square with two black rectangles
					if(rot == 0){
						g.fillRect(xC, yC+16, 50, 18);
						g.fillRect(xC+16, yC+32, 18, 18);
					}
					else if(rot == 90){
						g.fillRect(xC+16, yC, 18, 50);
						g.fillRect(xC+32, yC+16, 18, 18);
					}
					else if(rot == 180){
						g.fillRect(xC, yC+16, 50, 18);
						g.fillRect(xC+16, yC, 18, 18);
					}
					else if(rot == 270){
						g.fillRect(xC+16, yC, 18, 50);
						g.fillRect(xC, yC+16, 18, 18);
					}
				}
				else if(t.getType().equals("+")){
					g.fillRect(xC+16, yC, 18, 50);
					g.fillRect(xC, yC+16, 50, 18);
				}
			}
			
		}

	}
	
	
	
	
	class CarThread extends Thread{
		Car c;
		
		public CarThread(Car c){
			this.c = c;
		}
		
		public void run(){
			while(true){
				try {
					//refresh GUI which also calls the cars blink and move methods
					
					drawCar(drawingPanel.getGraphics());
//					drawingPanel.revalidate();
//					drawingPanel.repaint();
					Tile currentTile = getCurrentTile(c.getXLocation(), c.getYLocation()); //updates current tile based on car's location 
					c.setTileAndRotation(currentTile.getTileType(), currentTile.getRotation());
//					if(c.getCarNumber() == 1){
//						System.out.println("Car 1 Tile Type: " + c.getTileType() + "   Rotation: " + c.getTileRotation() + 
//								"   xCor: " + c.getXLocation() + "   yCor: " + c.getYLocation());
//					}
					
					//update table
					int carNumber = c.getCarNumber();
					carTableModel.setValueAt(c.getXLocation(), carNumber, 1);
					carTableModel.setValueAt(c.getYLocation(), carNumber, 2);
					carTableModel.setValueAt(carNumber, carNumber, 0);
					

					//sleep based on speed of car
					double sleepTime = (1/(c.getSpeed() * 3)) * 1000;
					sleep((new Double(sleepTime)).longValue());
//					System.out.println("Car " + carNumber + " with speed: " + c.getSpeed() + " SPS sleep for: " + sleepTime + " milliseconds");
					//I'm assuming the time taken to go from the ready state to the running state is negligible
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void drawCar(Graphics g){
			//get the location of the top left corner, rotation of the tile
			int xC = getXCor(c.getXLocation());
			int yC = getYCor(c.getYLocation());
			
			// blink 3xs in each square using blink boolean and wait in the thread
			if(c.getBlink()){
				g.setColor(new Color(255,255,255,255)); //blinking cars
				g.fillOval(xC+10, yC+10, 30, 30);
			}
			else{
				//draw the circle
				g.setColor(c.getColor());
				g.fillOval(xC+10, yC+10, 30, 30);
				
				//draw the number
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", Font.BOLD, 16));
				g.drawString(String.valueOf(c.getCarNumber()), xC+20, yC+30);
			}
			
			c.toggleBlink(); //change the blink boolean value

			//TODO figure out a way to get rid of past blinking cars after a car has moved
			//repaint works, but everything is very flashy--need happy medium
			//when repaint is turned off, window is repainted properly when resized
			//I tried figuring out what the repaint the ComponentResizeListener calls when window is resized
			//that didn't work out too well, so I just left the repaints commented out
			
			//tested all these collectively and individually to no avail
			//drawingPanel.revalidate(); 
			//drawingPanel.repaint();
			//mainPanel.getParent().repaint();
			//mainPanel.getParent();
			//repaint();
		}
		
	}



	public static void main(String[] args) {
		new MainFrame();

	}

}
