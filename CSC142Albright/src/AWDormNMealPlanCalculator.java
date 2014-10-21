import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** 
 * @author anthonywittemann
 * Chapter 12 Pr 3 Dorm and Meal Plan Calculator
 * HW Due 5/8/14
 */
public class AWDormNMealPlanCalculator extends JFrame{
	private JPanel masterPanel;
	private JComboBox dormsCB;
	private JComboBox mealPlansCB;
	private double totalCharges;
	private boolean dormSelected;
	private boolean mealPlanSelected;
	private String[] mealPlans = {"Select a Meal Plan", "7 meals/week: $560/semester", "14 meals/week: $1,095/semester", "unlimited: $1,500/semester"};
	private String[] dorms = {"Select a Dorm", "Allen Hall: $1,500/semester", "Pike Hall: $1,600/semester", 
			"Farthing Hall: $1,200/semester", "University Suites: $1,800/semester "};

	public AWDormNMealPlanCalculator(){
		super("AW Dorm and Meal Plan Calculator");
		setSize(300, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dormsCB = new JComboBox(dorms);
		dormsCB.addActionListener(new DormsListener());
		mealPlansCB = new JComboBox(mealPlans);
		mealPlansCB.addActionListener(new MealPlansListener());
		
		masterPanel = new JPanel();
		masterPanel.add(dormsCB);
		masterPanel.add(mealPlansCB);
		
		add(masterPanel);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void calculateTotalCost(){
		totalCharges = 0;
		if(mealPlansCB.getSelectedIndex() == 0){
			//should not happen
		}
		else if(mealPlansCB.getSelectedIndex() == 1){
			totalCharges += 560;
		}
		else if(mealPlansCB.getSelectedIndex() == 2){
			totalCharges += 1095;
		}
		else if(mealPlansCB.getSelectedIndex() == 3){
			totalCharges += 1500;
		}
		
		if(dormsCB.getSelectedIndex() == 0){
			//should not happen
		}
		else if(dormsCB.getSelectedIndex() == 1){
			totalCharges += 1500;
		}
		else if(dormsCB.getSelectedIndex() == 2){
			totalCharges += 1600;
		}
		else if(dormsCB.getSelectedIndex() == 3){
			totalCharges += 1200;
		}
		else if(dormsCB.getSelectedIndex() == 4){
			totalCharges += 1800;
		}
	}
	
	private class DormsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(dormsCB.getSelectedIndex() != 0){
				dormSelected = true;
				if(mealPlanSelected){
					calculateTotalCost();
					JOptionPane.showMessageDialog(null, "Your total residential and dining charges per semester are: $" + totalCharges);
				}
			}
			else{
				dormSelected = false;
			}
		}
	}
	
	private class MealPlansListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(mealPlansCB.getSelectedIndex() != 0){
				mealPlanSelected = true;
				if(dormSelected){
					calculateTotalCost();
					JOptionPane.showMessageDialog(null, "Your total residential and dining charges per semester are: $" + totalCharges);
				}
			}
			else{
				mealPlanSelected = false;
			}
		}
	}
	
	
	public static void main(String[] args){
		new AWDormNMealPlanCalculator();
	}
}
