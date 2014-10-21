package Lab5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

//Anthony Wittemann
//Lab Section W at 10am
//Lab5 Due 9/24/14

public class Lab5Main extends JFrame{
	
	public Lab5Main(){
		super("Lab5");
		setSize(500,500);
		setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
		/***** PANEL1 ***** PANEL1 ***** PANEL1 ***** PANEL1 ***** PANEL1 *****/
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		
		//top panel
		JPanel topButtonPanel = new JPanel();

		//button1 create and add to panel
		topButtonPanel.add(new JButton("Button1"));
		
		//button2 - create, add and set background color to red
		JButton button2 = new JButton("Button2");
		button2.setBackground(Color.RED);
		//added vvvvvvv b/c on my mac background outside of button would be red, not button itself
		button2.setBorderPainted(false);
		//feel free to eliminate ^^^^^^^ on a pc
		button2.setOpaque(true);
		topButtonPanel.add(button2);
		
		//create black line border around top panel
		topButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel1.add(topButtonPanel, BorderLayout.NORTH);
		
		
		
		//bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		
		JButton southButton = new JButton("This is the south quadrant");
		southButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		bottomPanel.add(new JLabel("West"), BorderLayout.WEST);
		bottomPanel.add(southButton, BorderLayout.CENTER);
		bottomPanel.add(new JLabel("East"), BorderLayout.EAST);
		
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		panel1.add(bottomPanel, BorderLayout.SOUTH);
		
		
		
		
		/***** PANEL2 ***** PANEL2 ***** PANEL2 ***** PANEL2 ***** PANEL2 *****/
		JPanel panel2 = new JPanel();
		
		//find the image
		java.net.URL imageURL = Lab5Main.class.getResource("blackLab.jpg");
		ImageIcon labPic = null;
		if (imageURL != null) {
		   labPic = new ImageIcon(imageURL);
		}
		else{
			System.out.println("Image not found");
		}
		
		//scale the image
		ImageIcon scaledLabPicIcon = scaleIcon(labPic.getImage(), .7);//NEED TO FIDDLE WITH TO FIT FRAME
		
		JLabel imageLabel = new JLabel(scaledLabPicIcon);
		panel2.add(imageLabel, BorderLayout.CENTER);
		

		
		//add panels to tabbedPane
		tabbedPane.add("First", panel1);
		tabbedPane.add("Doge", panel2);
		
		//add tabbedPane to frame, finish up and display
		add(tabbedPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private ImageIcon scaleIcon(Image img, double scale) {
        int w = (int)(scale*img.getWidth(this));
        int h = (int)(scale*img.getHeight(this));
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(img, 0, 0, w, h, this);
        g2d.dispose();
        return new ImageIcon(bi);
    }

	public static void main(String[] args) {
		new Lab5Main();

	}

}

