/** 
 * @author anthonywittemann
 * Chapter 10 Pr 5
 * In class 4/24/14
 */
public class AWPayroll2 {
	private String name;
	private int idNum;
	private int hrsWorked;
	private int hrlyPay;
	
	public AWPayroll2(){
		
	}
	
	public AWPayroll2(String n, int idn, int hrsW, int hrP) throws AWInvalidHrsWorkedException, 
	AWEmptyStringException, AWInvalidIDNException, AWInvalidPayRateException{
		setName(n);
		setIDN(idn);
		setHrsWorked(hrsW);
		setHrlyPay(hrP);
	}
	
	public String getName(){
		return name;
	}
	
	public int getIDN(){
		return idNum;
	}
	
	public int getHrsWorked(){
		return hrsWorked;
	}
	
	public int gethrlyPay(){
		return hrlyPay;
	}
	
	public void setName(String nN) throws AWEmptyStringException{
		if(nN.length() == 0){
			throw new AWEmptyStringException();
		}
		name = nN;
	}
	
	public void setIDN(int nIDN) throws AWInvalidIDNException{
		if(nIDN < 0){
			throw new AWInvalidIDNException("ID Number cannot be < 0");
		}
		idNum = nIDN;
	}
	
	public void setHrsWorked(int nHW) throws AWInvalidHrsWorkedException{
		if(nHW < 0 || nHW > 84){
			throw new AWInvalidHrsWorkedException("Hours worked cannot be > 84 or < 0");
		}
		hrsWorked = nHW;
	}
	
	public void setHrlyPay(int nHP) throws AWInvalidPayRateException{
		if(nHP < 0 || nHP > 25){
			throw new AWInvalidPayRateException("Hourly Pay Rate cannot be < 0 or > 25");
		}
		
	}

}
