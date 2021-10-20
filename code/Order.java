public class Order {

	private int orderID;
	private ArrayList<MenuItem> itemsOrdered;
	private Customer customer;
	private Table assignedTable;
	private Staff orderCreatedBy;
	private Boolean completedStatus;
	private time dateTimeCreated;

	public int getOrderID() {
		return this.orderID;
	}

	public Boolean isCompleted() {
		// TODO - implement Order.isCompleted
		throw new UnsupportedOperationException();
	}

	public Order() {
		// TODO - implement Order.Order
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param parameter
	 */
	public Order(int parameter) {
		// TODO - implement Order.Order
		throw new UnsupportedOperationException();
	}

	public void orderComplete() {
		// TODO - implement Order.orderComplete
		throw new UnsupportedOperationException();
	}

	public Invoice getInvoice() {
		// TODO - implement Order.getInvoice
		throw new UnsupportedOperationException();
	}

	public Table getTable() {
		// TODO - implement Order.getTable
		throw new UnsupportedOperationException();
	}

	public void assignTable() {
		// TODO - implement Order.assignTable
		throw new UnsupportedOperationException();
	}

}