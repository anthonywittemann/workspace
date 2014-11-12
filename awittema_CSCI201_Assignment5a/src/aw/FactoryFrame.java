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
import java.io.File;

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
	private File factoryRCPFile;
	private JScrollPane scrollPane;
	private JTextArea taskBarTA;
	private JPanel taskBarPanel; //contains scrollPane, taskbar label
	private JLabel taskBarLbl;
	
	private JPanel drawingPanel;
	
	
	private int[] workers;
	private int[] hammers;
	private int[] screwdrivers;
	private int[] plyers;
	private int[] scissors;
	private int[] paintbrushes;
	
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
				FileNameExtensionFilter factoryRCPFilter = new FileNameExtensionFilter("Factory or RCP files", "factory", "rcp"); //only .factory, .rcp
				fileChooser.setFileFilter(factoryRCPFilter);
				int returnVal = fileChooser.showOpenDialog(FactoryFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					factoryRCPFile = fileChooser.getSelectedFile();
//					System.out.println("You chose to open this file: " + factoryRCPFile.getName());
					//TODO add text to TA from file and parse file
					parseRCPFile(factoryRCPFile.getPath());
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
		//drawingPanel.setBackground(Color.LIGHT_GRAY);
		drawingPanel.setPreferredSize(new Dimension(600, 600));
		add(drawingPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	
	public void parseRCPFile(String path) {
		// TODO Auto-generated method stub
		
	}


	public static void main(String[] args) {
		new FactoryFrame();

	}
	
	private class DrawingPanel extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			createMaterialContainers(g);
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
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.drawString("Wood", 140, 25);
			g.drawString("Metal", 280, 25);
			g.drawString("Plastic", 420, 25);
			
			
			

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
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.drawString("Screwdriver", 10, 130);
			g.drawString("Hammer", 10, 210);
			g.drawString("Paintbrush", 10, 290);
			g.drawString("Plyers", 10, 370);
			g.drawString("Scissors", 10, 450);
			
			//TODO figure out how to display the number of each tool, where to get that from
		}
	}

}


