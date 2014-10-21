/** 
 * @author Anthony Wittemann
 * Chapter 5 Program 6 Population
 * HW Due 2/25/14
 */
public class AWPopulation {
	private int initialPopulation;
	private double avgDailyIncrease;
	private int numDays;
	
	public AWPopulation(int IP, double ADI, int ND){
		initialPopulation = IP;
		avgDailyIncrease = ADI;
		numDays = ND;
	}
	
	public int getInitialPopulation(){
		return initialPopulation;
	}
	
	public double getAvgDailyIncrease(){
		return avgDailyIncrease;
	}
	
	public int getNumDays(){
		return numDays;
	}

	public void setInitialPopulation(int ip){
		initialPopulation = ip;
	}
	
	public void setAvgDailyIncrease(double adi){
		avgDailyIncrease = adi;
	}
	
	public void setNumDays(int nd){
		numDays = nd;
	}
	
	public void displayPopulationGrowth(){
		int d = numDays;
		double a = avgDailyIncrease;
		int ip = initialPopulation;
		double nPop = ip;
		System.out.println("Day \t\t   Population");
		System.out.println("-------------------------------");
		for(int i = 0; i <= d; i++){
			nPop = (nPop + nPop * a / 100.0);
			System.out.println(i + "\t\t\t" + (int) nPop);
		}
	}
	
}
