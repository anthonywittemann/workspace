package aw;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class ParentGUI extends JFrame{

	public ParentGUI(String title) {
		super(title);
		setSize(600, 400);
		setLocation(100, 100); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		JPanel jp = createGUI();
		add(jp, BorderLayout.CENTER);
	}
	protected abstract void createMenuBar(); 
	protected abstract JPanel createGUI();
}
