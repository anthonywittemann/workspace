import java.util.Scanner;

/** 
 * @author Anthony Wittemann
 * Chapter 5 Algorithm Workbench 1-4
 * In class 2/20/14 
 */
public class AWCh5AlgWorkbench {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("\n*****Alg Work 1*****\n"); // Alg Work 1
		
		int product = 0;
		while(product < 100){
			System.out.print("Enter a number to be multiplied by 10: ");
			int num = keyboard.nextInt();
			product = num * 10;
		}
		
		
		
		System.out.println("\n*****Alg Work 2*****\n"); // Alg Work 2
		
		boolean keepGoing = true;
		do{
			System.out.print("Enter the first number to be added: ");
			int n1 = keyboard.nextInt();
			System.out.print("Enter the second number to be added: ");
			int n2 = keyboard.nextInt();
			int nSum = n1 + n2;
			System.out.print("The sum of the two numbers you entered is: " + nSum);
			System.out.print("\nEnter n to quit, anything else to continue: ");
			char usrDecision = keyboard.next().toLowerCase().charAt(0);
			if(usrDecision == 'n'){
				keepGoing = false;
				break;
			}
		}
		while(keepGoing);
		
		
		
		System.out.println("\n*****Alg Work 3*****\n"); // Alg Work 3
		
		for(int x = 0; x <= 1000; x += 10){
			System.out.print(x + " ");
		}
		
		
		
		System.out.println("\n*****Alg Work 4*****\n"); // Alg Work 4
		
		int total = 0;
		for(int i = 0; i < 10; i++){
			System.out.print("Enter a number: ");
			total += keyboard.nextInt();
		}
		System.out.println("\nThe total of the numbers you entered is: " + total);
		
		
		
		
		System.out.println("\n*****Alg Work 7*****\n"); // Alg Work 7
		int x = 1; 
		do{
			 System.out.print("Enter a number: "); 
			 x = keyboard.nextInt();  
		}
	    while (x > 0); 
		
		
		
		
		System.out.println("\n*****Alg Work 8*****\n"); //Alg Work 8
		 String input; 
		 char sure = '1'; 
		 while (sure != 'Y' && sure != 'N'){ 
		    System.out.print("Are you sure you want to quit? "); 
		    input = keyboard.next(); 
		    sure = input.charAt(0); 
		 }  
		
		
	}

}
