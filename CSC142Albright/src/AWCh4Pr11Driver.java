/** 
 * @author Anthony Wittemann
 * Chapter 4 Program 11 Internet Service Provider
 * HW Due 2/20/14 
 */

import javax.swing.JOptionPane;

public class AWCh4Pr11Driver {

	public static void main(String[] args) {
		AWInternetServiceProvider isp;
		double bill = -1;
		
		boolean isBadInput = true;
		
		do{
			String packg = JOptionPane.showInputDialog("Enter the letter of the internet package you have. ");
			char pkg = packg.charAt(0);
			int hoursOfUse = Integer.parseInt(JOptionPane.showInputDialog("Enter the total number of hours you used the internet this month."));
			isp = new AWInternetServiceProvider(pkg, hoursOfUse);
			bill = isp.getTotalMonthlyCharges();
			
			if(bill != -1){
				isBadInput = false;
				break;
			}
			else{
				JOptionPane.showMessageDialog(null, "Please enter a valid package letter.");
				
			}
		}
		while(isBadInput);
		
		
		String msg = "Your total monthly ISP bill is: $" + bill; 
		JOptionPane.showMessageDialog(null, msg);
		
		return;
	}

}
