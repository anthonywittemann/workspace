
public class Hostess {
	private Tables tables;
	
	public Hostess(int numTables) {
		this.tables = new Tables(numTables);
	}
	
	public Table seatCustomer(CustomerThread customerThread) {
		Table table = null;
		try {
			Restaurant.addMessage("Hostess is trying to seat customer " + customerThread.getCustomerNumber());
			table = tables.getTable(); //wait for lock on the table
			WaiterThread waiter = Restaurant.getWaiterFactory().getWaiter();
			waiter.setTable(table); //wait for lock on the waiter
			table.seatTable(customerThread, waiter);
			Restaurant.addWaiterMessage("Customer " + customerThread.getCustomerNumber() + " is seated at table " + table.getTableNumber(), waiter.getWaiterNumber());
			Restaurant.addMessage("Hostess seated customer " + customerThread.getCustomerNumber() + " at table " + table.getTableNumber() + " with waiter " + waiter.getWaiterNumber());
			//TODO let table JLabels know table is now occupied
			Restaurant.tableOccupied(table.getTableNumber());
		} catch (InterruptedException ie) {
			System.out.println("HostessThread.seatCustomer():InterruptedException: " + ie.getMessage());
		}
		return table;
	}
	
	public void customerLeaving(CustomerThread customerThread) {
		Restaurant.addWaiterMessage("Customer " + customerThread.getCustomerNumber() + " is done eating and is leaving.", customerThread.getTable().getWaiterThread().getWaiterNumber());
		Restaurant.addMessage("Customer " + customerThread.getCustomerNumber() + " is done eating and is leaving.");
		customerThread.getTable().getWaiterThread().returnTable(customerThread.getTable());
		tables.returnTable(customerThread.getTable());
		//TODO let table JLabels know table is now open
		Restaurant.tableUnoccupied(customerThread.getTable().getTableNumber());
	}

}