/** 
 * @author Anthony Wittemann
 * Chapter 7 Program 2 - Payroll
 * HW Due 3/26/14
 */
public class AWPayroll {
	private int[] employeeID;
	private int[] hrsWorked;
	private double[] hrlyPayrate;
	private double[] grossWages;
	
	public AWPayroll(int[] hW, double[] hP){
		employeeID = new int[7];
		employeeID[0] = 5658845; employeeID[1] = 4520125; employeeID[2] = 7895122; employeeID[3] = 8777541;
		employeeID[4] = 8451277; employeeID[5] = 1302850; employeeID[6] = 7580489;
		hrsWorked = hW;
		hrlyPayrate = hP;
		grossWages = new double[7];	
		calculateGrossWages();
	}
	
	private void calculateGrossWages(){
		for(int i = 0; i < employeeID.length; i ++){
			grossWages[i] = hrlyPayrate[i] * hrsWorked[i];
		}
	}
	
	public int getHrsWorked(int empIDN){
		return hrsWorked[getEmpIndex(empIDN)];
	}
	
	/**
	 * 
	 * @param index
	 * @return the employee ID
	 */
	public int getEmployeeID(int index){
		return employeeID[index];
	}
	
	public double getHrlyPayrate(int empIDN){
		return hrlyPayrate[getEmpIndex(empIDN)];
	}
	
	public double getGrossWages(int empIDN){
		return grossWages[getEmpIndex(empIDN)];
	}
	
	
	public void setHrsWorked(int empIDN, int nHW){
		hrsWorked[getEmpIndex(empIDN)] = nHW;
		calculateGrossWages();
	}
	
	public void setEmployeeID(int empIDN, int nEID){
		employeeID[getEmpIndex(empIDN)] = nEID;
	}
	
	public void setHrlyPayrate(int empIDN, double nHP){
		hrlyPayrate[getEmpIndex(empIDN)] = nHP;
		calculateGrossWages();
	}
	
	private int getEmpIndex(int idn){
		int empNum = -1;
		for(int i = 0; i < employeeID.length; i ++){
			if(idn == employeeID[i]){
				empNum = i;
			}
		}
		return empNum;
	}
	
	
}
