import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomerFactory extends Thread {

	private Vector<CustomerThread> customerThreadVector = new Vector<CustomerThread>();
	private Hostess hostessThread;
	
	public CustomerFactory(Hostess hostessThread) {
		this.hostessThread = hostessThread;;
		this.start();
	}
	
	public void run() {
		try {
			int customerNumber = 0;
			while (true) {
				CustomerThread ct = new CustomerThread(customerNumber++, hostessThread);
				customerThreadVector.add(ct);
				Thread.sleep(1000 * (int)(Math.random() * 5)); // customers come in between 0 and 5 seconds apart
			}
		} catch (InterruptedException ie) {
			System.out.println("CustomerFactory.run(): InterruptedException: " + ie.getMessage());
			for (CustomerThread ct : customerThreadVector) {
				ct.interrupt();
			}
		}
	}
}

class CustomerThread extends Thread {
	private int customerNumber;
	private Hostess hostessThread;
	private Table table;
	private Lock orderLock = new ReentrantLock();
	private Condition orderReady = orderLock.newCondition();
	private boolean orderIsReady = false;
	
	public CustomerThread(int customerNumber, Hostess hostessThread) {
		this.customerNumber = customerNumber;
		this.hostessThread = hostessThread;
		this.start();
	}
	
	public int getCustomerNumber() {
		return this.customerNumber;
	}
	
	public Table getTable() {
		return this.table;
	}
	
	public void orderIsNowReady(){
		orderIsReady = true;
		orderReady.signal();
	}
	
	
	public void run() {
		try {
			table = hostessThread.seatCustomer(this);
			
			//wait for the waiter to arrive
			WaiterThread waiter = Restaurant.getWaiterFactory().getWaiter();     //blocking
			
			try{
				orderLock.lock();
				int order = (int) (3 * Math.random()); //random order from 0-2
				//pass value to waiter
				waiter.takeOrder(table, order, this.customerNumber);
				
				// Print a message, to the regular message window after the Waiter has a Customer order.
				//The message is to say: “Waiter X has taken an order for Y from table Z.”.
				String message = "Waiter " + waiter.getWaiterNumber() + 
						" has taken an order for " + order + " from table " + 
						this.getTable().getTableNumber();
				Restaurant.addMessage(message);
				System.out.println(message);
				
				//wait for order ---use conditions---
				while(orderIsReady == false){
					orderReady.await();
				}
				
				
				 //When Customers are given their food (they wake up), 
				//they are also to print a message: “Customer X has received order Y at table Z.”. 
				//Customers do their typical random delay to simulate eating, then they leave.
				Restaurant.addMessage("Customer " + this.customerNumber + 
						" has received order " + order + " at table " + this.table.getTableNumber());
				System.out.println("Customer " + this.customerNumber + 
						" has received order " + order + " at table " + this.table.getTableNumber());
				
			} finally{
				orderLock.unlock();
			}
			
			
			
			//eat meal
			Thread.sleep(1000 * (int)(Math.random() * 10)); // sleep for between 0 and 10 seconds
			if(hostessThread != null && this != null){
				hostessThread.customerLeaving(this);
			}
		} catch (InterruptedException ie) {
			System.out.println("CustomerThread.run(): InterruptedException: " + ie.getMessage());
		}
	}
}