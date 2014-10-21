import java.util.ArrayList;

/** 
 * @author anthonywittemann
 * Chapter 8 Pr1, Pr2, Pr3
 * HW Due 5/1/14
 */
public class AWCh8Methods {

	public static void backwardsString(String str){
		char[] chars = str.toCharArray();
		for(int index = chars.length - 1; index >= 0; index--){
			System.out.print(chars[index]);
		}
	}
	
	public static int wordCounter(String str){
		return str.split(" ").length;
	}
	
	public static String sentenceCapitalizer(String str){
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		char[] chrArray = str.toCharArray();
		for(int x = 0; x < chrArray.length - 2; x ++){
			if(chrArray[x] == '.' || chrArray[x] == '?' || chrArray[x] == '!'){
				//I assume there's a space after a . or ? or !
				indexes.add(x+2);
			}
		}
		
		String ret;
		//capitalize the 1st letter. I assume the 1st char is the beginning of a sentence
		ret = str.substring(0, 1).toUpperCase() + str.substring(1, indexes.get(0));
		System.out.println("Ret: " + ret);
		for(int i = 0; i < indexes.size() - 1; i ++){
			int ind = indexes.get(i);
			ret = ret + str.substring(ind, ind + 1).toUpperCase() + str.substring(ind + 1, indexes.get(i + 1));
		}
		int lastInd = indexes.get(indexes.size() - 1);
		ret = ret + str.substring(lastInd, lastInd + 1).toUpperCase()+ str.substring(lastInd + 1, str.length());
		return ret;
	}	
}
