import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SwingGrossPay extends JFrame
{
	private Container contentPane;
	private JTextField hoursField,
	                   payRateField,
							 grossPayField;
	private JButton calcButton;
	
	public SwingGrossPay()
	{
		// Call the base class constructor.
		super("Swing Gross Pay");
		
		// Specify what happens when the close button is clicked.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a panel for the hours worked.
		JPanel hoursPanel = new JPanel();
		hoursPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel label1 = new JLabel("Enter the number of hours worked: ");
		hoursField = new JTextField(10);
		hoursPanel.add(label1);
		hoursPanel.add(hoursField);
		
		// Create a panel for the pay rate.
		JPanel ratePanel = new JPanel();
		ratePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		JLabel label2 = new JLabel("Enter your hourly pay rate: ");
		payRateField = new JTextField(10);
		ratePanel.add(label2);
		ratePanel.add(payRateField);
		
		// Create a panel for the gross pay.
		JPanel grossPayPanel = new JPanel();
		grossPayPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel label3 = new JLabel("Gross pay: ");
		grossPayField = new JTextField(10);
		grossPayField.setEditable(false);
		grossPayPanel.add(label3);
		grossPayPanel.add(grossPayField);

		// Create a panel for the button.
		JPanel buttonPanel = new JPanel();
		JButton calcButton = new JButton("Calculate");
		calcButton.addActionListener(new ButtonListener());
		buttonPanel.add(calcButton);
		
		// Get the content pane and add the panels to it.
		contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(4, 1));
		contentPane.add(hoursPanel);
		contentPane.add(ratePanel);
		contentPane.add(grossPayPanel);
		contentPane.add(buttonPanel);
		
		// Pack and display the window.
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingGrossPay sgp = new SwingGrossPay();
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			double grossPay,
			       hours,  
				    payRate;
			DecimalFormat dollar = new DecimalFormat("#,##0.00");
			
			hours = Double.parseDouble(hoursField.getText());
			payRate = Double.parseDouble(payRateField.getText());
			grossPay = hours * payRate;
			grossPayField.setText("$" + dollar.format(grossPay));
		}
	}
}
