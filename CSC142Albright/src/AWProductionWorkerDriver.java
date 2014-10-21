/** 
 * @author Anthony Wittemann
 * Chapter 9 Program 1 - Employee and Production Worker Classes
 * In class 4/10/14
 */
public class AWProductionWorkerDriver {
	
	public static void main(String[] args) throws AWInvalidPayRateException, AWShiftException{
		AWProductionWorker worker = new AWProductionWorker("Karl", 1032000, "001-B", 0, 7.25);
		worker.setHrlyPayRate(10.10);
		worker.setEmpNum("002-C");
		worker.setHireDate(1042000);
		worker.setName("Marx");
		System.out.println("Name: " + worker.getName() + "\n" + worker);

	}

}
