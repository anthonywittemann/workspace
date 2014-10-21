package csci201;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Anthony Wittemann
//Lecture 11 GUI Components - Font Changer
//10/1/14

public class Lecture11 extends JFrame{
	
	private JLabel fontNameLabel, fontSizeLabel;
	private JComboBox fontNameCombo, fontSizeCombo;
	private JTextField textField;
	private JCheckBox centerCheckBox, boldCheckBox, italicCheckBox;
	
	private boolean isBold = false, isItalic = false;
	private String fontName = "Courier";
	private int fontSize = 12;
	
	public Lecture11(){
		super("Font Changer");
		setSize(400,250);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instantiateComponents();
		configureGUI();
		addActions();
		
		setLayout(new BorderLayout());
		
		
	}
	
	
	private void addActions(){
		fontNameCombo.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent ie) {
				fontName = (String)ie.getItem();
				
				if(isBold && isItalic){
					textField.setFont(new Font(fontName, Font.BOLD | Font.ITALIC, fontSize));
				}
				else if(isItalic){
					textField.setFont(new Font(fontName, Font.ITALIC, fontSize));
				}
				else if(isBold){
					textField.setFont(new Font(fontName, Font.BOLD, fontSize));
				}
				else{
					textField.setFont(new Font(fontName, Font.PLAIN, fontSize));
				}
			}
		});
		
		boldCheckBox.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent ce) {
				if(boldCheckBox.isSelected()){
					isBold = true;
					textField.setFont(new Font(fontName, Font.BOLD, fontSize));
				}
				else{
					isBold = false;
				}
				
			}
			
		});
		
		fontSizeCombo.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	private void configureGUI(){
		JPanel northPanel = new JPanel();
		northPanel.add(fontNameLabel);
		northPanel.add(fontNameCombo);
		northPanel.add(fontSizeLabel);
		northPanel.add(fontSizeCombo);
		add(northPanel, BorderLayout.NORTH);
		
		add(textField, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.add(centerCheckBox);
		southPanel.add(boldCheckBox);
		southPanel.add(italicCheckBox);
		add(southPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	private void instantiateComponents(){
		fontNameLabel = new JLabel("Font Name");
		fontSizeLabel = new JLabel("Font Size");
		
		Object[] fontNames = {"Courier", "Arial", "Helvetica", "Times New Roman"};
		Object[] fontSizes = {"8", "12", "16", "24", "36"};
		
		fontNameCombo = new JComboBox(fontNames);
		fontSizeCombo = new JComboBox(fontSizes);
		
		textField = new JTextField("Java is cool!", 25);
		
		centerCheckBox = new JCheckBox("Center");
		boldCheckBox = new JCheckBox("Bold");
		italicCheckBox = new JCheckBox("Italic");
	}
	
	public static void main(String[] args) {
		new Lecture11();

	}

}
