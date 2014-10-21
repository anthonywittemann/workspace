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
	//TODO make an ArrayList representing the tiles each containing an tile object with the type, location and rotation
	private ArrayList<Tile> gridTiles;
	
	//TODO Part 5 - beginning to parse XML
	
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

			File carsXmlFile = new File("cars.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(carsXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			//TODO modify this code to get the type, location and rotation of each tile 
			//and load each tile into the arrayList
			if (doc.hasChildNodes()) {
				 
				printNote(doc.getChildNodes());
		 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO instead of printNode, loadNodes into Tiles and Tiles into ArrayList
	private void printNote(NodeList nodeList) {
		 
	    for (int count = 0; count < nodeList.getLength(); count++) {
	 
		Node tempNode = nodeList.item(count);
	 
		// make sure it's element node.
		if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			// get node name and value
			System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			System.out.println("Node Value =" + tempNode.getTextContent());
	 
			if (tempNode.hasAttributes()) {
	 
				// get attributes names and values
				NamedNodeMap nodeMap = tempNode.getAttributes();
	 
				for (int i = 0; i < nodeMap.getLength(); i++) {
	 
					Node node = nodeMap.item(i);
					System.out.println("attr name : " + node.getNodeName());
					System.out.println("attr value : " + node.getNodeValue());
	 
				}
	 
			}
	 
			if (tempNode.hasChildNodes()) {
	 
				// loop again if has child nodes
				printNote(tempNode.getChildNodes());
	 
			}
	 
			System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
	 
		}
	 
	    }
	 
	  }
	
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawGrid(g);
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
	}
	
	
	
	public static void main(String[] args) {
		new MainFrame();

	}

}
