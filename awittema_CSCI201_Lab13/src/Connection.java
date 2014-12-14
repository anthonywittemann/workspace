import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class Connection implements Runnable{
	private static NumberServer numberServer;
//	private static List<String> list = new ArrayList<String>();
	public static List<String> syncList = Collections.synchronizedList(new ArrayList<String>());;
	public static Semaphore semaphore = new Semaphore(5);
	
	public Connection(NumberServer ns){
		numberServer = ns;
//		syncList = Collections.synchronizedList(list);
	}
	
	public Connection(){
//		syncList = Collections.synchronizedList(list);
	}
	
	
	public static void setNumberServer(NumberServer nNS){
		numberServer = nNS;
	}
	
	public static NumberServer getNumberServer(){
		return numberServer;
	}

	@Override
	public void run() {
		//System.out.println("Next number: " + numberServer.getNumber());
		try {
			semaphore.acquire();
			syncList.add("" + numberServer.getNumber());
			//System.out.println("Next Number: " + numberServer.getNumber());

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			semaphore.release();
		}
		
	}
	

	
	public static void main(String[] args){
		ExecutorService executor = Executors.newCachedThreadPool(); //Executors.newFixedThreadPool(10); 
		
		Connection.setNumberServer(new NumberServer());
		
		for(int i = 0; i < 10; i++){
			Connection c = new Connection();
			executor.execute(c);
		}
		executor.shutdown();
		while(!executor.isTerminated())
		{
			
		}

		//System.out.println("BEFORE loop");
		for(String s: syncList){
			System.out.println("Next Number from Synced List: " + s);
		}
		System.out.println("synclist.size() = " + syncList.size());
		//System.out.println("AFTER loop");
	}

}
