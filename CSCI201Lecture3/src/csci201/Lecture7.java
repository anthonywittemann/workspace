package csci201;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Lecture7 extends JFrame{
	
	public Lecture7(){
		super("CSCI 201 Window");
		setSize(500,100);
		setLocation(400,300);
		
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		JLabel nameL = new JLabel("Name");
		JLabel emailL = new JLabel("Email");
		
		JTextField nameTF = new JTextField(25);
		JTextField emailTF = new JTextField(25);
		
		JButton verifyB = new JButton("Verify");
		JButton submitB = new JButton("Sumbit");
		
		northPanel.add(nameL);  northPanel.add(nameTF);   northPanel.add(verifyB);
		southPanel.add(emailL);  southPanel.add(emailTF);   southPanel.add(submitB);
		
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {
		new Lecture7();

	}

}
