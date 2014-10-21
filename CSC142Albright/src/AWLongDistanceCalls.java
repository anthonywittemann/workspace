import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/** 
 * @author anthonywittemann
 * Chapter 10 Pr 7 Long Distance Calls
 * in class 5/1/14
 */
public class AWLongDistanceCalls extends JFrame{
	private JRadioButton daytimeRB;
	private JRadioButton eveningRB;
	private JRadioButton nightRB;
	
	private final double DAY_RATE = .07;
	private final double EVENING_RATE = .12;
	private final double NIGHT_RATE = .05;
	
	private JTextField minsTalkedTF;
	private double minsTalked;
	private JLabel minsTalkedLbl;
	
	private JButton calcButton;
	private JButton exitButton;
	
	private double callCost;
	
	private JPanel masterPanel;
	private JPanel rbPanel;
	private JPanel minsTalkedPanel;
	private JPanel buttonsPanel;
	
	public AWLongDistanceCalls(){
		super("Long Distance Calls AW");
		
		setSize(270,200);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		daytimeRB = new JRadioButton(" Daytime ($0.07 per minute)           ", true);
		eveningRB = new JRadioButton(" Evening ($0.12 per minute)           ");
		nightRB = new JRadioButton(" Off-Peak ($0.05 per minute)          ");
		
		
		ButtonGroup rbg = new ButtonGroup();
		rbg.add(daytimeRB);
		rbg.add(eveningRB);
		rbg.add(nightRB);
		rbPanel = new JPanel();
		rbPanel.setLayout(new BorderLayout());
		rbPanel.setBorder(BorderFactory.createTitledBorder("Select a Rate Category"));
		rbPanel.add(daytimeRB, BorderLayout.NORTH);
		rbPanel.add(eveningRB, BorderLayout.CENTER);
		rbPanel.add(nightRB, BorderLayout.SOUTH);
		
		
		minsTalkedTF = new JTextField("                        ");
		minsTalkedTF.addActionListener(new MinsTalkedListener());
		minsTalkedLbl = new JLabel("Minutes: ");
		minsTalkedPanel = new JPanel();
		minsTalkedPanel.setLayout(new BorderLayout());
		minsTalkedPanel.setBorder(BorderFactory.createTitledBorder("Time of Call"));
		minsTalkedPanel.add(minsTalkedTF, BorderLayout.EAST);
		minsTalkedPanel.add(minsTalkedLbl, BorderLayout.WEST);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());
		calcButton = new JButton("Calculate Charges");
		calcButton.addActionListener(new MinsTalkedListener());
		buttonsPanel.add(calcButton, BorderLayout.WEST);
		buttonsPanel.add(exitButton, BorderLayout.EAST);
		
		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		masterPanel.add(rbPanel, BorderLayout.NORTH);
		masterPanel.add(minsTalkedPanel, BorderLayout.CENTER);
		masterPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		
		add(masterPanel);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	private void displayCallCost() {
		JOptionPane.showMessageDialog(null, "Call cost: $" + callCost, "Call Cost", JOptionPane.PLAIN_MESSAGE);
	}

	private void calculateCallCost() {
		if(daytimeRB.isSelected()){
			callCost = minsTalked * DAY_RATE;
		}
		else if(eveningRB.isSelected()){
			callCost = minsTalked * EVENING_RATE;
		}
		else if(nightRB.isSelected()){
			callCost = minsTalked * NIGHT_RATE;
		}
		
	}
	
	
	private class MinsTalkedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			minsTalked = Double.parseDouble(minsTalkedTF.getText());
			calculateCallCost();
			displayCallCost();
		}
	}
	
	private class ExitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);	
		}
	}
	
	
	public static void main(String[] args) {
		new AWLongDistanceCalls();

	}

}
