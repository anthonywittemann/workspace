import javax.swing.JOptionPane;

/** 
 * @author Anthony Wittemann
 * Chapter 5 Program 6 Population Growth
 * HW Due 2/25/14
 */
public class AWCh5Pr6Driver {

	public static void main(String[] args) {
		boolean isNotValidInput = true;
		int initPop, days;
		double growthFactor;
		
		do{
			initPop = Integer.parseInt(JOptionPane.showInputDialog("What is the initial number of the organism population?"));
			days = Integer.parseInt(JOptionPane.showInputDialog("How many days did the organisms multiply?"));
			growthFactor = Double.parseDouble(JOptionPane.showInputDialog("What is the Population's average daily increase? " +
					"\n Enter as a percentage."));
			if(initPop > 1 &&  growthFactor > 0 && days > 0){
				isNotValidInput = false;
			}
		} while(isNotValidInput);
		
		AWPopulation population = new AWPopulation(initPop, growthFactor, days);
		population.displayPopulationGrowth();

	}

}
