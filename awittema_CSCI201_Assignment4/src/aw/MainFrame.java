package aw;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class MainFrame extends JFrame{

	private JFileChooser fileChooser;
	private File xmlFile;
	private Object[][] rowData;
	private static final Object[] columnNames  = {"Car #", "X", "Y"};
	private JPanel drawingPanel;
	private JTable carLocationTable;
	private DefaultTableModel carTableModel;
	private JPanel carTablePanel;
	private JPanel mainPanel;
	private int xGridCenter = 300;
	private int yGridCenter = 300;
	private ArrayList<Tile> gridTiles = new ArrayList<Tile>();
	private ArrayList<Car> cars = new ArrayList<Car>();

	//TODO Part 5 - beginning to parse XML: Currently, car objects being created but not tile objects

	public MainFrame(){
		//set up the window and the drawing panel
		setSize(800,600);
		this.setMinimumSize(new Dimension(800, 600));//TODO play around with the minimum size
		//TODO fix the grid that initially comes up centered at 0,0 or no grid at all
		setTitle("Roadway Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout()); //use GridLayout to separate Table


		drawingPanel = new DrawingPanel();
		drawingPanel.setBackground(Color.LIGHT_GRAY);
		drawingPanel.setPreferredSize(new Dimension(mainPanel.getWidth() - 200, mainPanel.getHeight()));
		xGridCenter = drawingPanel.getWidth()/2;
		yGridCenter = drawingPanel.getHeight()/2;
		drawingPanel.repaint();



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
					System.out.println("You chose to open this file: " + xmlFile.getName());
				}
				System.out.println("DEBUG: Open file clicked");

			}

		});
		menuBar.add(openFileItem);
		setJMenuBar(menuBar);



		/*** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ****** TABLE ON RIGHT ***/
		rowData = new Object[1][3];
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

			}
		});

		add(mainPanel);
		setVisible(true);


		/****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ****** PARSE XML ******/
		parseCarsXML();




	}


	public void parseCarsXML(){
		try {
			File carsXmlFile = new File("cars.xml"); //TODO change this to whatever file is selected by user
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(carsXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes(), null, null, null, null);

			}
			for(Tile t: gridTiles){
				System.out.println(t.toString());
			}
			System.out.println("\n\n\n");
			for(Car c: cars){
				System.out.println(c.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//TODO instead of printNode, loadNodes into Tiles and Tiles into ArrayList, car and cars into ArrayList
	//includes variables from parent nodes that hold importants information for creating tile/car object
	//TODO works creating car objects, but @ least one of the tile attributes is null & therefore not being added to gridTiles
	private void printNote(NodeList nodeList, String pRow, String pAi, String pColor, String pSpeed) {

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
				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				
//				if(tempNode.getNodeName().equals("row")){
//					row = tempNode.getNodeValue();
//				}

				if (tempNode.hasAttributes()) {

					// get attributes names
					NamedNodeMap nodeMap = tempNode.getAttributes();
					
					//loop through all attributes and store values in variables to be used for car/tile object
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						String nodeName = node.getNodeName();
						System.out.println("attr name : " + nodeName);
						
						if(nodeName.equals("label")){
							row = node.getNodeValue();
						}
						
						
						if(nodeName.equals("Type")){
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
					System.out.println("*******************CHILD NODES********************");
					// loop again if has child nodes
					printNote(tempNode.getChildNodes(), row, ai, color, speed);
					System.out.println("\\\\\\\\\\\\\\\\\\CHILD NODES//////////////////////");

				}

				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
				//add new tile or car to arraylist
				if(type != null && row != null && column != null && rotation != null){
					gridTiles.add(new Tile(type, row, column, rotation));
				}
				else if(ai != null && color != null && speed != null && xLoc != null && yLoc != null){
					cars.add(new Car(color, ai, speed, xLoc, yLoc));
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

	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawGrid(g);
			testTiles(g);
			drawTiles(g);
			drawCars(g);
		}

		public void drawGrid(Graphics g){
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
		}
		
		public void testTiles(Graphics g){
			int xC = xGridCenter - 225;
			int yC = yGridCenter - 225;
//			int rot = t.getRotation();
			
			//draw the green rectangle for the tile
			g.setColor(Color.GREEN);
			g.fillRect(xC, yC, 50, 50);
			
			//draw the black rectangles based on rotation
			g.setColor(Color.BLACK);
//			else if(t.getType().equals("i")){			//I configuration I I I I I I I
//				if(rot == 0 || rot == 180){
//					g.fillRect(xC+12, yC, 25, 50);
//					xC+=50; yC+=50;
					//GOOD
//				}
//				else if(rot == 90 || rot == 270){
//					g.fillRect(xC, yC+12, 50, 25);
//					xC+=50; yC+=50;
					//GOOD
//				}
//			}
//			else if(t.getType().equals("l")){ 			//L configuration L L L L L L L 
//				if(rot == 0){ 
//					g.fillRect(xC+12, yC, 25, 32);
//					g.fillRect(xC+37, yC+18, 12, 14);
//					xC+=50; yC+=50;
					//GOOD
//				}
//				else if(rot == 90){
//					g.fillRect(xC, yC+12, 32, 25);
//					g.fillRect(xC+18, yC, 14, 12);
//					xC+=50; yC+=50;
					//GOOD
//				}
//				else if(rot == 180){
//					g.fillRect(xC+12, yC+18, 25, 32);
//					g.fillRect(xC, yC+18, 12, 14);
//					xC+=50; yC+=50;
					//GOOD
//				}
//				else if(rot == 270){
					//TODO finish L configuration for 270 rotation
//				}
		}
		
		//TODO part 6 draw the tiles and cars on the grid
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
						g.fillRect(xC+12, yC, 25, 50);
					}
					else if(rot == 90 || rot == 270){
						g.fillRect(xC, yC+12, 50, 25);
					}
				}
				else if(t.getType().equals("l")){ 			//L configuration L L L L L L L 
					if(rot == 0){
						g.fillRect(xC+12, yC, 25, 32);
						g.fillRect(xC+37, yC+18, 12, 14);
					}
					else if(rot == 90){
						g.fillRect(xC, yC+12, 32, 25);
						g.fillRect(xC+18, yC, 14, 12);
					}
					else if(rot == 180){
						g.fillRect(xC+12, yC+18, 25, 32);
						g.fillRect(xC, yC+18, 12, 14);
					}
					else if(rot == 270){
						
					}
				}
				else if(t.getType().equals("t")){			//T configuration T T T T T T T
					//get the rotation and draw green square with two black rectangles
				}
				else if(t.getType().equals("+")){
					//get the rotation and draw green square with two black rectangles
					//TODO find out if rotation matters for cross on piazza
				}
			}
			
		}
		
		public void drawCars(Graphics g){
			
		}
	}



	public static void main(String[] args) {
		new MainFrame();

	}

}
