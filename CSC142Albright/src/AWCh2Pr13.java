// Anthony Wittemann
// Chapter 2 Program 13 Restaurant Bill
// Due 2/6/14

import javax.swing.JOptionPane;

public class AWCh2Pr13 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double charge = 0;

        charge = Double.parseDouble(
        		JOptionPane.showInputDialog(null, "Please enter the charge for the meal: "));
        
        double tax = charge * .0675;
        double tip = .15 * (charge + tax);
        double totalBill = tip + tax + charge;
        
        charge = Math.round(charge * 100.0) / 100.0;
        tax = Math.round(tax * 100.0) / 100.0;
        tip = Math.round(tip * 100.0) / 100.0;
        totalBill = Math.round(totalBill * 100.0) / 100.0;
        
        JOptionPane.showMessageDialog(null, "Subtotal: $" + charge + "\n"
        		+ "Tax: $" + tax + "\n"
        		+ "Tip: $" + tip + "\n"
        		+ "Total Charge: $" + totalBill);

	}

}
