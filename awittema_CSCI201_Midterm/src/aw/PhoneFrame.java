package aw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PhoneFrame extends ParentGUI{
	private JPanel mainPanel;
	private JTextField phoneNumberTF;
	
	private JButton bStar;
	private JButton b0;
	private JButton bPound;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	
	private JButton[] phoneKeys;

	public PhoneFrame(){
		super("CSCI 201 Midterm -  Telephone"); // match the title
		toFront();
		createMenuBar();
		createGUI();
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exists in the parent class, but the rubric wants it on both I guess
	}

	@Override
	protected void createMenuBar() {
		/*** MENUBAR & CAllING ****** MENUBAR & CAllING ****** MENUBAR & CAllING ******/
		JMenuBar menuBar = new JMenuBar();
		JMenu phoneMenu = new JMenu("Phone");
		JMenuItem callItem = new JMenuItem("Call");
		callItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				//modify TF to say JK
				phoneNumberTF.setText("\t\t"); //CLEARING THE TF AS PER RUBRIC

			}

		});
		phoneMenu.add(callItem);
		menuBar.add(phoneMenu);
		setJMenuBar(menuBar);
		
		
		
	}

	@Override
	protected JPanel createGUI() {
		// add GRID LAYOUT for buttons and GRIDBAGLAYOUT FOR MAIN PANEL
		mainPanel = new JPanel();
		
		
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel dialPadPanel = new JPanel();
		dialPadPanel.setLayout(new GridLayout(4,3));
		
		//initialize JButtons
		bStar = new JButton("*"); b0 = new JButton("0"); bPound = new JButton("#");
		b1 = new JButton("1"); b2 = new JButton("2"); b3 = new JButton("3");
		b4 = new JButton("4"); b5 = new JButton("5"); b6 = new JButton("6");
		b7 = new JButton("7"); b8 = new JButton("8"); b9 = new JButton("9");
		
		phoneKeys = new JButton[12];
		
		
		
		//add them to the array
		phoneKeys[0] = b7; phoneKeys[1] = b8; phoneKeys[2] = b9;
		phoneKeys[3] = b4; phoneKeys[4] = b5; phoneKeys[5] = b6;
		phoneKeys[6] = b1; phoneKeys[7] = b2; phoneKeys[8] = b3;
		phoneKeys[9] = bStar; phoneKeys[10] = b0; phoneKeys[11] = bPound;
		
		//add actionListeners and add to dialPanel
		for(int i = 0; i < phoneKeys.length; i++){
			phoneKeys[i].addActionListener(new AL());
			dialPadPanel.add(phoneKeys[i]);
		}
		
		phoneNumberTF = new JTextField("\t\t");
		phoneNumberTF.setEditable(false);
		gbc.gridy = 0;
		mainPanel.add(phoneNumberTF, gbc);
		gbc.gridy = 1;
		mainPanel.add(dialPadPanel, gbc);
		
		
		add(mainPanel);
		setVisible(true);
		return mainPanel;
	}
	
	
	private class AL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			JButton source = (JButton) ae.getSource(); //SORRY, know I'm downcasting :(
			if(phoneNumberTF.getText().equals("\t\t")){
				phoneNumberTF.setText("");
			}
			phoneNumberTF.setText(phoneNumberTF.getText() + source.getText());
			
		}
		
	}
	
	
}
