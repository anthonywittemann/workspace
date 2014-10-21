/** 
 * @author anthonywittemann
 * Chapter 8 Pr5
 * HW Due 5/1/14
 */
public class AWPasswordVerifier {
	private String password;
	
	public AWPasswordVerifier(String pw){
		password = pw;
	}
	
	public void setPassword(String nPW){
		password = nPW;
	}
	
	public String getPassword(){
		return password;
	}
	
	public boolean isValid(){
		boolean hasNufChars = false, hasUpper = false, hasLower = false, hasDigit = false;
		if(password.length() >= 6){
			hasNufChars = true;
			for(int i = 0; i < password.length(); i++){
				try{  
				    Double.parseDouble(password.substring(i, i+1));  
				    hasDigit = true;
				}  catch(NumberFormatException nfe){
					
				}
				if(password.substring(i, i+1).compareTo(password.substring(i, i+1).toLowerCase()) == 0){
					hasLower = true;
				}
				else if(password.substring(i, i+1).compareTo(password.substring(i, i+1).toUpperCase()) == 0){
					hasUpper = true;
				} 
			}
		}
		
		return hasNufChars && hasUpper && hasLower && hasDigit;
	}
	
	
}
