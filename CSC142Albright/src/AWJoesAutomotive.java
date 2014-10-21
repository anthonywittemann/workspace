import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** 
 * @author anthonywittemann
 * Chapter 11 Pr6 - Joe's Automotive
 * HW Due 5/6/14
 */
public class AWJoesAutomotive extends JFrame{
	private JButton calcTotalB;
	private JButton exitB;
	//TODO implement exit button, finish layout stuff to match
	private JTextField hrsLaborTF;
	private JLabel hrsLaborLbl;
	private double hrsLabor;
	private JTextField otherChargesTF;
	private JLabel otherChargesLbl;
	private double otherCharges;
	private JCheckBox oilChange;
	private JCheckBox lubeJob;
	private JCheckBox radiatorFlush;
	private JCheckBox transmissionFlush;
	private JCheckBox inspection;
	private JCheckBox mufflerReplacement;
	private JCheckBox tireRotation;
	private double checkBoxTotal;
	private double totalCost;
	private JPanel routineServicesPanel;
	private JPanel nonroutineServicesPanel;
	private JPanel nrSPanel1;
	private JPanel nrSPanel2;
	private JPanel buttonsPanel;
	private JPanel masterPanel;

	public AWJoesAutomotive(){
		super("Joe's Automotive AW");
		setSize(280,380);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		calcTotalB = new JButton("Calculate Total");	
		calcTotalB.addActionListener(new CalcTotalListener());
		exitB = new JButton("Exit");
		exitB.addActionListener(new ExitListener());
		hrsLaborTF = new JTextField("0                         ");
		hrsLaborTF.addActionListener(new HrsLaborListener());
		otherChargesTF = new JTextField("0                         ");
		otherChargesTF.addActionListener(new OtherChargesListener());
		hrsLaborLbl = new JLabel("Hours of Labor:   ");
		otherChargesLbl = new JLabel("Parts Charges:   ");
		oilChange = new JCheckBox("Oil Change ($26.00)");
		lubeJob = new JCheckBox("Lube Job ($18.00)");
		radiatorFlush = new JCheckBox("Radiator Flush ($30.00)");
		transmissionFlush = new JCheckBox("Transmission Flush ($80.00)");
		inspection = new JCheckBox("Inspection ($15.00)");
		mufflerReplacement = new JCheckBox("Muffler Replacement ($100.00)");
		tireRotation = new JCheckBox("Tire Rotation ($20.00)");
		
		//top routine services panel
		routineServicesPanel = new JPanel();
		routineServicesPanel.setBorder(BorderFactory.createTitledBorder("Routine Services"));
		routineServicesPanel.setPreferredSize(new Dimension(270, 200));
		routineServicesPanel.setLayout(new BoxLayout(routineServicesPanel, BoxLayout.Y_AXIS));
		routineServicesPanel.add(oilChange);
		routineServicesPanel.add(lubeJob);
		routineServicesPanel.add(radiatorFlush);
		routineServicesPanel.add(transmissionFlush);
		routineServicesPanel.add(inspection);
		routineServicesPanel.add(mufflerReplacement);
		routineServicesPanel.add(tireRotation);
		
		//middle nonroutine services panel with 2 internal panels
		nonroutineServicesPanel = new JPanel();
		nonroutineServicesPanel.setBorder(BorderFactory.createTitledBorder("Nonroutine Services"));
		nonroutineServicesPanel.setPreferredSize(new Dimension(270, 90));
		nonroutineServicesPanel.setLayout(new BorderLayout());
		
		
		nrSPanel1 = new JPanel();
		nrSPanel1.setLayout(new BorderLayout());
		nrSPanel1.add(otherChargesLbl, BorderLayout.WEST);
		nrSPanel1.add(otherChargesTF, BorderLayout.EAST);
		nrSPanel1.setPreferredSize(new Dimension(270, 30));
		
		nrSPanel2 = new JPanel();
		nrSPanel2.setLayout(new BorderLayout());
		nrSPanel2.add(hrsLaborLbl, BorderLayout.WEST);
		nrSPanel2.add(hrsLaborTF, BorderLayout.EAST);
		nrSPanel2.setPreferredSize(new Dimension(270, 30));
		
		nonroutineServicesPanel.add(nrSPanel1, BorderLayout.NORTH);
		nonroutineServicesPanel.add(nrSPanel2, BorderLayout.SOUTH);
		
		//bottom buttons panel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(calcTotalB, BorderLayout.WEST);
		buttonsPanel.add(exitB, BorderLayout.EAST);
		
		
		masterPanel = new JPanel();
		masterPanel.add(routineServicesPanel);
		masterPanel.add(nonroutineServicesPanel);
		masterPanel.add(buttonsPanel);
		
		
		add(masterPanel);
//		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void calculateTotal() {
		calculateCheckBoxTotal();
		totalCost = hrsLabor * 20 + otherCharges + checkBoxTotal;
	}
	
	private void calculateCheckBoxTotal(){
		checkBoxTotal = 0;
		if(oilChange.isSelected())
			checkBoxTotal += 26;
		if(lubeJob.isSelected())
			checkBoxTotal += 18;
		if(radiatorFlush.isSelected())
			checkBoxTotal += 30;
		if(transmissionFlush.isSelected())
			checkBoxTotal += 80;
		if(inspection.isSelected())
			checkBoxTotal += 15;
		if(mufflerReplacement.isSelected())
			checkBoxTotal += 100;
		if(tireRotation.isSelected())
			checkBoxTotal += 20;
	}
	
	private class HrsLaborListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			hrsLabor = Double.parseDouble(hrsLaborTF.getText());
		}
	}
	
	private class OtherChargesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			otherCharges = Double.parseDouble(otherChargesTF.getText());
		}
	}
	
	private class CalcTotalListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			calculateTotal();
			JOptionPane.showMessageDialog(null, "The total for Joe's services are: $" + totalCost);
		}
	}
	
	private class ExitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);	
		}
	}
	
	
	public static void main(String[] args){
		AWJoesAutomotive ja = new AWJoesAutomotive();
	}
	
}
