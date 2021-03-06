package written_final_test2;

public class Problem5 implements Runnable {
	private static int num = 5; 
	private char c; 
	
	Problem5(char c) {
		this.c = c; 
	}
	
	public void run() { 
		changeNum(c);
	}
	
	private static synchronized void changeNum(char ch) { 
		System.out.println(ch + "1 = " + num);
		int num1 = num + 2;
		Thread.yield();
		num = num1;
		System.out.println(ch + "2 = " + num); 
	}

  public static void main(String [] args){
	  Thread t1 = new Thread(new Problem5('a')); 
	  Thread t2 = new Thread(new Problem5('b')); 
	  Thread t3 = new Thread(new Problem5('c'));
	  t1.start(); t2.start(); t3.start(); 
	  //only used when changeNum is not Synchronized
	  try {
		  t1.join();
		  t2.join();
		  t3.join();
	  } catch (InterruptedException ie) { System.out.println("IE: " + ie.getMessage());
	  System.out.println("num = " + num); 
	  }
  }
}
