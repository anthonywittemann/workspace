import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Test 1 Average Commuting Time
 * In class 2/27/14
 */
public class AvgCommutingTime {

	public static void main(String[] args) {
		int numWeeks;
		int totalDaysCommuting = 0;
		int totalHoursCommuting = 0;
		boolean isBadInput = true;
		do{
			numWeeks = Integer.parseInt(JOptionPane.showInputDialog("How many weeks? (Enter as an integer no greater than 4)"));
			if(numWeeks <= 4 && numWeeks >= 1){
				isBadInput = false;
			}
		}while(isBadInput);
		
		int hrsCommuting;
		for(int w = 0; w < numWeeks; w++){
			for(int d = 0; d < 3; d++){
				totalDaysCommuting ++;
				
				boolean isBadInp = true;
				do{
					hrsCommuting = Integer.parseInt(JOptionPane.showInputDialog("How many hours did your commute take today?"));
					if(hrsCommuting > -1 && hrsCommuting < 25){
						isBadInp = false;
					}
				} while(isBadInp);
				
				totalHoursCommuting += hrsCommuting;
				
			}
		}
		
		double avgDailyCommutingTime = ((1.0 * totalHoursCommuting) / (1.0* totalDaysCommuting));
		JOptionPane.showMessageDialog(null, "Average daily commuting time: " + avgDailyCommutingTime + " hours\n" +
				"Total Days Commuting: "  + totalDaysCommuting +
				"\nTotal Hours Commuting: " + totalHoursCommuting);

	}

}
