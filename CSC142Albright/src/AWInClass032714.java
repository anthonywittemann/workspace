
public class AWInClass032714 {

	public static void main(String[] args) {
		// Alg Workbench 5 in Ch 7
		int[] empID = new int[10];
		double[] grossPay = new double[10];
		for(int i = 0; i < empID.length; i++){
			empID[i] = 10000 + i;
			grossPay[i] = 100000 * Math.random();
		}
		
		for(int j = 0; j < empID.length; j ++){
			System.out.println("Employee ID: " + empID[j] + "\tGrossPay: $" + grossPay[j]);
		}

		System.out.println();
		
		// Alg Workbench 6,7 in Ch7
		// each row represents a student,
		// each column represents the grades for an assignment
		int[][] grades = {{90, 69, 79, 85},
						  {84, 97, 77, 51},
						  {81, 94, 72, 71}};
		int totalTestScores = 0;
		for(int x = 0; x < grades.length; x++){
			for(int y = 0; y < grades[x].length; y ++){
				totalTestScores += grades[x][y];
			}
		}
		int avgTestScore = (int) (totalTestScores/(grades.length * 1.0 * grades[0].length));
		System.out.println("Average Test Score: " + avgTestScore);
		
	}

}
