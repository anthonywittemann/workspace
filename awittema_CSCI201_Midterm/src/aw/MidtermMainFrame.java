package aw;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


//ONLY IMPORTNAT PART IS MAIN AT BOTTOM, THE REST IS JUST REFERENCE
public class MidtermMainFrame extends JFrame{
	
	private JTabbedPane tabbedPane;
	private JFileChooser fileChooser;
	private File xmlFile;
	private JPanel tabbedPanePanel1;
	private JPanel gbLPanel; //JPanel for GBL
	private JPanel flowLPanel; //JPanel for flowLayout
	private JPanel gridLPanel; //JPanel for gridLayout
	private JPanel boxLPanel; //JPanel for boxLayout
	private JPanel groupLPanel; //JPanel for groupLayout
	private GroupLayout groupLayout; 
	private JPanel nullLPanel; //JPanel for nullLayout


	public MidtermMainFrame(){
		super("Anthony Wittemann Programming Midterm"); //TODO match the title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400); // TODO match size
		
		/*** LAYOUTS *** LAYOUTS *** LAYOUTS *** LAYOUTS *** LAYOUTS *** LAYOUTS *** LAYOUTS ***/
		gbLPanel = new JPanel();
		gbLPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START; // set anchor
		gbc.fill = GridBagConstraints.BOTH; // set the fill
		gbc.gridheight = 2; // number of grid spaces
		gbc.gridx = 0; //set the location in the grid
		gbc.insets = new Insets(20,20,20,20); // set the external padding, 20 px on each side
		gbc.ipady = 20; // 20 pixels internal padding
		gbc.weightx = .5; // set the weight of x and y: used to determine how to distribute space among columns and rows, 0.0-1.0
		
		
		//FLOW LAYOUT
		flowLPanel = new JPanel();
		flowLPanel.setLayout(new FlowLayout());
		
		
		//GRID LAYOUT
		gridLPanel = new JPanel();
		gridLPanel.setLayout(new GridLayout(3,3)); //3x3 grid that takes up entire panel
		
		
		
		//BOX LAYOUT
		boxLPanel = new JPanel();
		boxLPanel.setLayout(new BoxLayout(boxLPanel, BoxLayout.Y_AXIS));
		boxLPanel.add(Box.createGlue()); //add glue between components
		
		
		
		//GROUP LAYOUT
		groupLPanel = new JPanel();
		groupLayout = new GroupLayout(getContentPane());
		groupLPanel.setLayout(groupLayout);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
		JLabel label = new JLabel("LABEL");
		
		SequentialGroup horizontal = groupLayout.createSequentialGroup();
		horizontal.addComponent(label);
		
		JTextField textField = new JTextField();
		ParallelGroup searchBoxGroup = groupLayout.createParallelGroup();
		searchBoxGroup.addComponent(textField);
		horizontal.addGroup(searchBoxGroup);
		
		
		//NULL LAYOUT
		nullLPanel = new JPanel();
		nullLPanel.setLayout(null);
		JButton button1 = new JButton("One");
		Dimension button1Dimensions = button1.getPreferredSize();
		button1.setBounds(25, 25, button1Dimensions.width, button1Dimensions.height);
		nullLPanel.add(button1);
		
		
		
		
		/*** TABBED PANE *** TABBED PANE *** TABBED PANE *** TABBED PANE *** TABBED PANE ***/
		tabbedPane = new JTabbedPane();
		tabbedPanePanel1 = new JPanel();
		tabbedPane.add("First", tabbedPanePanel1);
		
		
		
		/*** TOOLBARS *** TOOLBARS *** TOOLBARS *** TOOLBARS *** TOOLBARS *** TOOLBARS *** TOOLBARS ***/
		
		
		
		
		/*** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ***/
		JMenuBar menuBar = new JMenuBar();
		JMenu openMenu = new JMenu("Open File...");
		JMenuItem openFileItem = new JMenuItem("Open File...");
		openMenu.setMnemonic('o');
		openFileItem.setMnemonic('o');
		ImageIcon ii = new ImageIcon("uscshield.png");
		openFileItem.setIcon(ii);
		openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openFileItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				fileChooser = new JFileChooser();
				fileChooser.setVisible(true);
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
				fileChooser.setFileFilter(xmlFilter);
				int returnVal = fileChooser.showOpenDialog(MidtermMainFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					xmlFile = fileChooser.getSelectedFile();
					System.out.println("You chose to open this file: " + xmlFile.getName());
				}
				System.out.println("DEBUG: Open file clicked");

			}

		});
		openMenu.add(openFileItem);
		menuBar.add(openMenu);
		setJMenuBar(menuBar);
		
		
		
		
		add(gbLPanel); //TODO add appropriate JPanel with LayoutManager
		setVisible(true);
	}
	
	
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
		}
		
	}	
	
	
	/*** ONLY PART THAT'S USED, ABOVE IS JUST REFERENCE ***/
	public static void main(String[] args) {
		boolean isValidInput = false;
		while(!isValidInput){
			System.out.println("Image Editor [image] \nTelephone [phone]\nWhich application would you like to run?");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			ParentGUI window; //USING POLYMORPHISM
			//NOTE TO GRADER: tried requestFocusInWindow(), toFront() for the windows, but neither work...
			if(input.equalsIgnoreCase("phone")){
				window  = new PhoneFrame();
				isValidInput = true;
			}
			else if(input.equalsIgnoreCase("image")){
				window = new ImageFrame();
				isValidInput = true;
			}
			else{
				System.out.println("Please Enter a Valid Selection\n");
			}
		}

	}

}
