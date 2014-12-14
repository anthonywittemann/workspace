package written_final_test3;

public class Problem4 {
	
	public static void main(String[] args ) {
		System.out.println("First line"); 
		try {
			for (int i=0; i < 10; i++) { 
				MyThread t = new MyThread(i); 
				t.start();
				t.join();
			}		
		} catch (InterruptedException ie) {
			System.out.println("IE: " + ie.getMessage()); }
			System.out.println("Last line"); 
		}
	}

class MyThread extends Thread { private int num;
	public MyThread(int num) {
		this.num = num; 
	}
	public void run() {
		for (char c='A'; c <= 'Z'; c++) {
			System.out.print(num + "" + c + " "); 
		}
		System.out.println(""); 
	}
}
