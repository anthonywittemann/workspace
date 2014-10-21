/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 1 - Rainfall
 * HW Due 3/26/14
 */
public class AWRainfall {
	private double[] rainfall;
	
	public AWRainfall(){
		rainfall = new double[12];
	}
	
	public AWRainfall(double[] r){
		rainfall = r;
	}
	
	public double getTotalRainfall(){
		double totalAnnualRainfall = 0;
		for(int i = 0; i < rainfall.length; i++){
			totalAnnualRainfall += rainfall[i];
		}
		return totalAnnualRainfall;
	}
	
	public double getAverageMonthlyRainfall(){
		return getTotalRainfall()/12.0;
	}
	
	public int getMonthWithMostRain(){
		double max = Double.NEGATIVE_INFINITY;
		int index = -1;
		for(int i = 0; i < rainfall.length; i++){
			if(max < rainfall[i]){
				index = i;
				max = rainfall[i];
			}
		}
		
		return index + 1;	
	}
	
	public int getMonthWithLeastRain(){
		double min = Double.POSITIVE_INFINITY;
		int index = -1;
		for(int i = 0; i < rainfall.length; i++){
			if(min > rainfall[i]){
				index = i;
				min = rainfall[i];
			}
		}
		
		return index + 1;
		
	}
	
}
