import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BusboyFactory extends Thread {
	
	private Vector<BusboyThread> busboyThreadVector = new Vector<BusboyThread>();
	private Hostess hostess;
	private int numBusboys;
	private int numBusboysPerTable;
	private Lock lock = new ReentrantLock();
	private Condition busboyAvailable = lock.newCondition();
	
	public BusboyFactory(Hostess hostess, int numBusboys, int numBusboysPerTable) {
		this.hostess = hostess;
		this.numBusboys = numBusboys;
		this.numBusboysPerTable = numBusboysPerTable;
		for (int i=0; i < numBusboys; i++) {
			busboyThreadVector.add(new BusboyThread(hostess, i, this, numBusboysPerTable));
		}
	}
	
	public void returnBusboy(BusboyThread bt) {
		lock.lock();
		bt.returnTable(bt.getTable(0));
		busboyAvailable.signal();
		lock.unlock();

	}
	
	public BusboyThread getBusboy() {
		BusboyThread bt = null;
		try {
			lock.lock();
			while (bt == null) {
				int i;
				for (i=0; i < busboyThreadVector.size(); i++) {
					bt = busboyThreadVector.get(i);
					// this will only allow one table per busboy
					// use a semaphore to allow more than one table per busboy
					//TODO  make sure that there are enough busboys per table
					if (bt.getNumAvailableBusboys() > 0) {
						break;
					}
				}
				if (i == busboyThreadVector.size()) {
					// if i get here, i haven't secured a busboy yet
					busboyAvailable.await();
				}
			}
		} catch(InterruptedException ie) { 
			System.out.println("BusboyFactory.getbusboy(): IE : " + ie.getMessage());
		} finally {
			lock.unlock();
		}
		return bt;
	}
	
	
	private class BusboyThread extends Thread{
		private Hostess hostess;
		private Vector<Table> tables;
		private int busboyNumber;
		private BusboyFactory busboyFactory;
		private Semaphore numBusboysSemaphore;
		private Lock busboyLock = new ReentrantLock();
		private Condition tableAssignedCondition = busboyLock.newCondition();
		
		
		public BusboyThread(Hostess hostess, int busboyNumber, BusboyFactory busboyFactory, int numBusboysPerTable) {
			this.hostess = hostess;
			this.busboyNumber = busboyNumber;
			this.busboyFactory = busboyFactory;
			this.numBusboysSemaphore = new Semaphore(numBusboysPerTable);
			tables = new Vector<Table>(numBusboysPerTable);
			this.start();
		}
		
		public int getNumAvailableBusboys() {
			int numPermitsAvailable = numBusboysSemaphore.availablePermits();
			return numPermitsAvailable;
		}
		public Hostess getHostess() {
			return this.hostess;
		}

		public void returnTable(Table table) {
			tables.remove(table);
			numBusboysSemaphore.release();
			
		}

		public void cleanTable(Table table) {
			try {
				numBusboysSemaphore.acquire();
				tables.add(table);
				this.busboyLock.lock();
				this.tableAssignedCondition.signalAll();
				this.busboyLock.unlock();
			} catch (InterruptedException ie) {
				System.out.println("busboyFactory.cleanTable(" + table.getTableNumber() + ") IE: " + ie.getMessage());
			}
		}
		
		public Table getTable(int i) {
			if (tables.size() > i) {
				return tables.elementAt(i);			
			}
			return null;
		}

		public int getBusboyNumber() {
			return this.busboyNumber;
		}

		public void run(){
			try {
				while (true) {
					this.busboyLock.lock();
					this.tableAssignedCondition.await();
					this.busboyLock.unlock();
					Thread.sleep(10 * (int)(Math.random() + 100)); // sleep for between .1 and 1 seconds
					if (getTable(0) != null) {
						getTable(0).getLock().lock();
						getTable(0).getReadyCondition().signal();
						getTable(0).getLock().unlock();
					}
				}
			} catch (InterruptedException ie) {
				System.out.println("CustomerThread.run(): InterruptedException: " + ie.getMessage());
			}
		}
	}
	
	
}


