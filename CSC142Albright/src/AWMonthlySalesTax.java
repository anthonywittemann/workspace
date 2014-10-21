import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/** 
 * @author anthonywittemann
 * Chapter 10 Pr 2 - monthly sales tax
 * in class 5/1/14
 */
public class AWMonthlySalesTax extends JFrame{
	private final double COUNTY_SALES_TAX = .02;
	private final double STATE_SALES_TAX = .04;
	private double totalMonthlySales;
	private JTextField totalSalesTF;
	private double countySalesTax;
	private JLabel cntyTxLbl;
	private double stateSalesTax;
	private JLabel stTxLbl;
	private double totalSalesTax;
	private JLabel totalTxLbl;
	private JPanel masterPanel;
	
	public AWMonthlySalesTax(){
		super("Monthly Sales Tax");
		
		setSize(400,400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		totalSalesTF = new JTextField("Enter your total monthly sales here");
		totalSalesTF.addActionListener(new TotalSalesListener());
		
		
		cntyTxLbl = new JLabel("County sales tax will display here");
		stTxLbl = new JLabel("State sales tax will display here");
		totalTxLbl = new JLabel("Total sales tax will display here");
		
		
		masterPanel = new JPanel();
		masterPanel.add(totalSalesTF);
		masterPanel.add(cntyTxLbl);
		masterPanel.add(stTxLbl);
		masterPanel.add(totalTxLbl);
		
		add(masterPanel);
		
		setVisible(true);
	}
	
	public void calculateTaxes(){
		countySalesTax = totalMonthlySales * COUNTY_SALES_TAX;
		stateSalesTax = totalMonthlySales * STATE_SALES_TAX;
		totalSalesTax = countySalesTax + stateSalesTax;
	}
	
	public void displayTaxes(){
		cntyTxLbl.setText("County Sales Tax: $" + countySalesTax);
		stTxLbl.setText("State Sales Tax: $" + stateSalesTax);
		totalTxLbl.setText("Total Sales Tax: $" + totalSalesTax);
	}
	
	private class TotalSalesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			totalMonthlySales = Double.parseDouble(totalSalesTF.getText());
			calculateTaxes();
			displayTaxes();
		}
	}
	

	public static void main(String[] args) {
		AWMonthlySalesTax mst = new AWMonthlySalesTax();

	}

}
