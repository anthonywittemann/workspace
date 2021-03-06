import java.util.*;
import java.util.concurrent.locks.*;

public class CookFactory {
	private static Vector<Order> ordersToCook;

	public CookFactory(CookPanel cp, int numCooks ) {
		ordersToCook = new Vector<Order>();

		for ( int i=0; i<numCooks; i++ ) {
			CookThread c = new CookThread( i, cp, this );
			c.start();
		}

	}
	
	public static void addOrder(Order nOrder){
		ordersToCook.add(nOrder);
	}
	
	public Vector<Order> getOrdersToCook(){
		return ordersToCook;
	}
	
	public void cookOrder(){
		ordersToCook.remove(0);
	}
}


class CookThread extends Thread {
	private int cookNumber;
	private CookPanel cookPanel;
	private CookFactory cookFactory;
	private Lock cookinLock = new ReentrantLock();
	
	public CookThread( int n, CookPanel cp, CookFactory cf ) {
		cookNumber = n;
		cookPanel = cp;
		cookFactory = cf;		
	}
	
	public void run() {
		cookPanel.addCookMessage( "Cook" + cookNumber + " is ready to cook." );
		System.out.println("Cook" + cookNumber + " is ready to cook.");
		
		if(cookFactory.getOrdersToCook().size() == 0){
			try {
				Thread.sleep((long)(10000 * Math.random() + 10000)); //sleep for 10-20 seconds
			} catch (InterruptedException ie) {
				System.out.println("CookThread.run(): InterruptedException: " + ie.getMessage());
			}
		} else{
			//get what to cook and remove from cookin vector
			Order order = null;
			try{
				cookinLock.lock();
				order = cookFactory.getOrdersToCook().get(0);
				cookFactory.cookOrder(); //removes the first order in the vector
			} finally{
				cookinLock.unlock();
			}
			//do the cookin
			try {
				//print a message to the CookPanel that says: 
				//Cook X is cooking an order for Y for table Z.”.
				cookPanel.addCookMessage("Cook " + this.cookNumber + 
						" is cooking an order for " + order.getOrder() +
						" for table " + order.getTable().getTableNumber());
				System.out.println("Cook " + this.cookNumber + 
						" is cooking an order for " + order.getOrder() +
						" for table " + order.getTable().getTableNumber());
				Thread.sleep((long) (5000 * Math.random() + 5000)); //sleep (cook) for 5-10 seconds
			} catch (InterruptedException ie) {
				System.out.println("CookThread.run(): InterruptedException: " + ie.getMessage());
			} 
			//add the completed order to the vector of orders the waiter is to deliver
			order.getWaiter().addCompletedOrder(order);
			
			//The Cook is also to print a message to the CookPanel. 
			//The message is to say: “Cook X has completed an order for Y for table Z.”
			cookPanel.addCookMessage("Cook " + this.cookNumber + 
					" has completed an order for " + order.getOrder() +
					" for table " + order.getTable().getTableNumber());
		}
	}
}

