import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** 
 * @author anthonywittemann
 * Chapter 10 Pr 1 - retail price calculator
 * in class 5/1/14
 */
public class AWRetailPriceCalculator extends JFrame{
	private JPanel masterPanel;
	private JButton retailPriceButton;
	private JLabel retailPriceLabel;
	private double retailPrice;
	private JTextField wholesaleTF;
	private double wholesalePrice;
	private JTextField markupTF;
	private double markup; //is a percentage
	
	public AWRetailPriceCalculator(){
		super("Retail Price Calculator AW");
		
		setSize(400,400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		retailPriceButton = new JButton("Display Retail Price");
		retailPriceButton.addActionListener(new RetailPriceButtonListener());
		
		wholesaleTF = new JTextField("Enter the wholesale price here");
		wholesaleTF.addActionListener(new WholesaleListener());
		
		markupTF = new JTextField("Enter the markup as a percentage here");
		markupTF.addActionListener(new MarkupListener());
		
		retailPriceLabel = new JLabel("Retail price will display here");
		
		
		
		masterPanel = new JPanel();
		masterPanel.add(wholesaleTF);
		masterPanel.add(markupTF);
		masterPanel.add(retailPriceButton);
		masterPanel.add(retailPriceLabel);
		
		add(masterPanel);
		
		setVisible(true);
		
	}
	
	public void setMarkup(double nM){
		markup = nM;
		calculateRetailPrice();
	}
	
	public void setWholesalePrice(double nWP){
		wholesalePrice = nWP;
		calculateRetailPrice();
	}
	
	public void calculateRetailPrice(){
		retailPrice = wholesalePrice * (1 + markup/100);
	}
	
	public double getWholesalePrice(){
		return wholesalePrice;
	}
	
	public double getMarkup(){
		return markup;
	}
	
	private class RetailPriceButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			calculateRetailPrice();
			retailPriceLabel.setText("Retail Price: $" + retailPrice);	
		}
	}
	
	private class WholesaleListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			wholesalePrice = Double.parseDouble(wholesaleTF.getText());
		}
	}
	
	private class MarkupListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			markup = Double.parseDouble(markupTF.getText());
		}
	}
	
	

	public static void main(String[] args) {
		new AWRetailPriceCalculator();

	}

}
