import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class Fibonacci extends RecursiveTask<Integer>{
	private int n; //nth Fibonacci number

	public Fibonacci(int n){
		this.n = n;
	}
	
	
	public Integer compute(){
		if(n == 0 || n == 1){
			return n;
		}
		Fibonacci f1 = new Fibonacci(n-1);
		Fibonacci f2 = new Fibonacci(n-2);
		f1.fork(); //go to another core to compute f1
		f2.fork(); //go to another core to compute f2
		return f2.join() + f1.join();
	}
	
	
	public static void main(String[] args){
		int index = 30;
		int poolSize = Runtime.getRuntime().availableProcessors(); //returns number of cores in computer
		for(int i = poolSize; i >= 1; i--){
			ForkJoinPool pool = new ForkJoinPool(poolSize);
			System.out.println("Num cores: " + poolSize);
			long beforeTime = System.nanoTime();
			pool.invoke(new Fibonacci(index));
			
			
//			Fibonacci fib = new Fibonacci(index); nonparallel way of computing
//			fib.compute();
			
			long afterTime = System.nanoTime();
			System.out.println("time with " + i + " cores = " + (afterTime - beforeTime));
		}
		
	}
	
}
