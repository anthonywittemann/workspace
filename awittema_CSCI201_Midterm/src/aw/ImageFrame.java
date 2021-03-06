package aw;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
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
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFrame extends ParentGUI{
	private JFileChooser fileChooser;
	private File imageFile;
	private JPanel mainPanel;
	private JLabel imageLbl;
	
	
	
	public ImageFrame(){
		super("CSCI 201 Midterm - Image Editor"); // match the title
		toFront();
		createMenuBar();
		createGUI();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exists in the parent class, but the rubric wants it on both I guess
	}



	@Override
	protected void createMenuBar() {
		/*** MENUBAR & OPENING IMAGE FILE ****** MENUBAR & OPENING IMAGE FILE ****** MENUBAR & OPENING IMAGE FILE ******/
		JMenuBar menuBar = new JMenuBar();
		JMenu openMenu = new JMenu("Image");
		JMenuItem openFileItem = new JMenuItem("Open Image");
		openMenu.setMnemonic('o');
		openFileItem.setMnemonic('o');
		openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openFileItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				fileChooser = new JFileChooser();
				fileChooser.setVisible(true);
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "jpeg");
				fileChooser.setFileFilter(imageFilter);
				int returnVal = fileChooser.showOpenDialog(ImageFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					imageLbl.removeAll();
					//TODO replace image when another is selected
					//TODO fix the box that doesn't have the image when resizing
					System.out.println("Remove");
					imageFile = fileChooser.getSelectedFile();
//					System.out.println("You chose to open this file: " + imageFile.getName());
					ImageIcon picIcon = new ImageIcon(imageFile.getAbsolutePath());
					imageLbl = new JLabel("", picIcon, SwingConstants.CENTER);
					imageLbl.setIconTextGap(5);
					mainPanel.add(imageLbl);
					repaint();
				}
//				System.out.println("DEBUG: Open file clicked");

			}

		});
		
		openMenu.add(openFileItem);
		menuBar.add(openMenu);
		setJMenuBar(menuBar);
		
	}



	@Override
	protected JPanel createGUI() {
		// TODO Auto-generated method stub
		
		mainPanel = new JPanel();
		imageLbl = new JLabel();
		
		
		
		
		
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		mainPanel.add(imageLbl);
		
		
		add(mainPanel);
		setVisible(true);
		return mainPanel;
	}
}
