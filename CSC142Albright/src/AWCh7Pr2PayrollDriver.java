import javax.swing.JOptionPane;
/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 2 - Payroll
 * HW Due 3/26/14
 */
public class AWCh7Pr2PayrollDriver {

	public static void main(String[] args) {
		int[] empID = new int[7];
		empID[0] = 5658845; empID[1] = 4520125; empID[2] = 7895122; empID[3] = 8777541;
		empID[4] = 8451277; empID[5] = 1302850; empID[6] = 7580489;
		
		double[] payRate = new double[7];
		int[] hrsWorked = new int[7];
		
		for(int x = 1; x <= 7; x ++){
			System.out.println(x);
		}
		
		for(int i = 1; i <= empID.length; i++){
			double input = -1;
			do{
			input = Double.parseDouble(JOptionPane.showInputDialog("Enter the hourly pay rate for employee " + i));
			payRate[i-1] = input;
			} while(input < 6.00);
			
			int inp = -1;
			do{
			inp = Integer.parseInt(JOptionPane.showInputDialog("Enter the hours worked for employee " + i));
			hrsWorked[i-1] = inp;
			} while(inp < 0);
		}
		
		AWPayroll payroll = new AWPayroll(hrsWorked,payRate);
		for(int j = 0; j < empID.length; j++){
			int eID = payroll.getEmployeeID(j);
			JOptionPane.showMessageDialog(null, "The employee ID number for employee " + (j + 1) + " is " + payroll.getEmployeeID(j) + 
					"\nThe gross pay for employee " + eID + " is " + payroll.getGrossWages(eID));
		}
		

	}

}
