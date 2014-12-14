package written_final_test;

class P1 {
	protected int num;
	
	P1(int num) { 
		this.num = num; 
	}
	
	public void print() {
		System.out.println("P1: " + num); 
	}
}

public class Problem1 extends P1 { private int num;
	Problem1() { 
		super(5);
		this.num = 10; 
	}
	
	public void print() {
		System.out.println("Problem1: " + num); 
	}
	
	public static void main(String [] args) { 
		P1 p = new Problem1();
		p.print(); 
	}

}
