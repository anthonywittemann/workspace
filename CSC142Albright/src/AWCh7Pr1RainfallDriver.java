import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 1 - Rainfall
 * HW Due 3/26/14
 */
public class AWCh7Pr1RainfallDriver {

	public static void main(String[] args) {
		double[] rainfall = new double[12];
		for(int i = 1; i <= 12; i ++){
			double input = -1;
			do{
				input = Double.parseDouble(JOptionPane.showInputDialog("Enter the rainfall for month " + i));
			} while(input < 0);
			rainfall[i - 1] = input;
		}
		
		AWRainfall rain = new AWRainfall(rainfall);
		JOptionPane.showMessageDialog(null, "The average monthly rainfall is "+ rain.getAverageMonthlyRainfall() + "\n" + 
				"The month with the most rain is " + rain.getMonthWithMostRain() + "\n" +
				"The month with the least rain is " + rain.getMonthWithLeastRain() + "\n" +
				"The total rainfall is " + rain.getTotalRainfall() + "\n");

	}

}
