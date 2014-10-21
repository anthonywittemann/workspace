/** 
 * @author Anthony Wittemann
 * Chapter 4 Program 10 Freezing and Boiling Points
 * HW Due 2/18/14
 */

import javax.swing.JOptionPane;

public class AWFPDriverCh4Pr10 {

	public static void main(String[] args) {
		AWFreezingNBoilingPts FnB_Pts = new AWFreezingNBoilingPts();
		
		int temp = Integer.parseInt(JOptionPane.showInputDialog("Enter the temperature in degrees Fahrenheit"));
		
		FnB_Pts.setFahrTemp(temp);
		
		boolean iEB, iEF, iOB, iOF, iWB, iWF;
		iEB = FnB_Pts.isEthylBoiling();
		iEF = FnB_Pts.isEthylFreezing();
		iOB = FnB_Pts.isOxygenBoiling();
		iOF = FnB_Pts.isOxygenFreezing();
		iWB = FnB_Pts.isWaterBoiling();
		iWF = FnB_Pts.isWaterFreezing();
		
		if(iEB && iOB && iWB){
			JOptionPane.showMessageDialog(null, "Ethyl Alcohol, Oxygen, and Water are at " +
					"or above their boiling points at " + temp + " degrees Fahrenheit");
		}
		else if(iOB && iEB){
			JOptionPane.showMessageDialog(null, "Ethyl Alcohol and Oxygen are at " +
					"or above their boiling points at " + temp + " degrees Fahrenheit");
		}
		else if(iOB){
			JOptionPane.showMessageDialog(null, "Oxygen is at " +
					"or above its boiling point at " + temp + " degrees Fahrenheit");
		}
		else{
			JOptionPane.showMessageDialog(null, "Ethyl Alcohol, Oxygen, and Water are all " +
					"below their boiling points at " + temp + " degrees Fahrenheit");
		}
		
		if(iEF && iOF && iWF){
			JOptionPane.showMessageDialog(null, "Ethyl Alcohol, Oxygen, and Water are at " +
					"or below their freezing points at " + temp + " degrees Fahrenheit");
		}
		else if(iWF && iEF){
			JOptionPane.showMessageDialog(null, "Ethyl Alcohol and Water are at " +
					"or below their freezing points at " + temp + " degrees Fahrenheit");
		}
		else if(iWF){
			JOptionPane.showMessageDialog(null, "Water is at " +
					"or below its freezing point at " + temp + " degrees Fahrenheit");
		}
		else{
			JOptionPane.showMessageDialog(null, "Ethyl Alcohol, Oxygen, and Water are all " +
					"above their freezing points at " + temp + " degrees Fahrenheit");
		}
		
		return;
	}

}
