

import java.util.Scanner;

public class Salary {


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("How many employees do you have? ");
		short numEmployees = scan.nextShort();
		Employee employees[] = new Employee[numEmployees];
		for(int i = 0; i < numEmployees; i++){
			System.out.print("Enter the name of employee #" + (i+1) + ": ");
			String name = scan.next();
			System.out.print("Enter the hourly rate of employee #" + (i+1) + ": $");
			double hourlyRate = scan.nextDouble();
			employees[i] = new Employee(name, hourlyRate);
		}
		for(int j = 0; j < numEmployees; j ++){
			System.out.println(employees[j].getName() + "'s Anual Salary: $" + employees[j].getSalary());
		}
		scan.close();
	}

}

class Employee {
	private String name;
	private double salary;
	
	public Employee(String name, double hourlyRate){
		this.name = name;
		this.salary = hourlyRate * 40 * 50;
	}
	
	public String getName(){
		return name;
	}
	
	public double getSalary(){
		return salary;
	}
	
}