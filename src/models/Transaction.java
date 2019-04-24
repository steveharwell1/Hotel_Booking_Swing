package models;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Transaction
 * 
 * Represents a successful reservation made by a customer for a hotel room.
 * 
 * @author Group B
 *
 */
public class Transaction implements Model {
	Map<String, String> map = new HashMap<String, String>();
	TransactionManager manager;
	private final static String[] columns = { "customer", "roomNumber", "checkin", "checkedin", "checkout",
			"checkedout", "price", "transactionCode" };
	private ChronoLocalDate start;
	private ChronoLocalDate end;

	/**
	 * Transaction create a new transaction unlike other models in this package,
	 * when a transaction is created it is not automatically added to the managers
	 * list of transactions only completed transactions are added to the list.
	 * 
	 * @param manager ref to the manager
	 * @param user    the user initiating the transaction
	 */
	public Transaction(TransactionManager manager, User user) {
		this.manager = manager;
		map.put("customer", user.getPrimaryKey());
	}

	/**
	 * Transaction constructor to be used be transaction manager with values from
	 * file.
	 * 
	 * @param manager ref to the manager
	 * @param map     values for transaction from file
	 */
	protected Transaction(TransactionManager manager, Map<String, String> map) {
		this.manager = manager;
		for (String key : columns) {
			this.map.put(key, map.get(key));
		}

		setStart(this.map.get("checkin"));
		setEnd(this.map.get("checkout"));
	}

	/**
	 * setEnd sets the ending date of the reservation
	 * 
	 * @param string the ending date
	 */
	public void setEnd(String string) {
		this.end = LocalDate.parse(string);
		this.map.put("checkout", this.end.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

	/**
	 * setStart sets the starting date of the reservation
	 * 
	 * @param string the start date in string form
	 */
	public void setStart(String string) {
		this.start = LocalDate.parse(string);
		this.map.put("checkin", this.start.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

	/**
	 * finalize
	 * 
	 * saves a constructed transaction to memory and file when completed.
	 */
	@Override
	public void finalize() {
		manager.addTransaction(this);
		manager.save();
	}

	@Override
	public String getPrimaryKey() {
		return "" + map.get("customer") + map.get("roomNumer") + map.get("checkin");
	}

	@Override
	public Map<String, String> asMap() {
		return map;
	}

	/**
	 * getColumns returns the names of the values to be saved to file
	 * 
	 * @return the columns
	 */
	public static String[] getColumns() {
		return columns;
	}

	/**
	 * Save all transactions to file
	 */
	public void save() {
		manager.save();
	}

	public ChronoLocalDate getStart() {
		// TODO Auto-generated method stub
		return start;
	}

	/**
	 * 
	 * @return the room number of this transaction
	 */
	public String getRoom() {
		return map.get("roomNumber");
	}

	/**
	 * Set the room number of this transaction
	 * 
	 * @param roomNumber the room on the reservation
	 */
	public void setRoom(String roomNumber) {
		this.map.put("roomNumber", roomNumber);
	}

	/**
	 * 
	 * @return the ending date of the reservation
	 */
	public ChronoLocalDate getEnd() {
		// TODO Auto-generated method stub
		return end;
	}

	/**
	 * 
	 * @param total the total calculated price of the reservation
	 */
	public void setPrice(Double total) {
		this.map.put("price", total.toString());
	}

	/**
	 * 
	 * @param code the transaction code later used to charge the customer
	 */
	public void setConfirmationCode(String code) {
		this.map.put("transactionCode", code);
	}

	@Override
	public String toString() {
		String s = "Rm: " + map.get("roomNumber") + " Gst: " + map.get("customer") + " Cnfrm: "
				+ map.get("transactionCode");
		s = s + " In: " + (map.get("checkedin") == null ? "N " : "Y ");
		s = s + " Out: " + (map.get("checkedout") == null ? "N " : "Y ");
		s = s + "$" + String.format("%.2f", getTotalPrice());
		return s;
	}

	/**
	 * label a transaction checked in or not
	 */
	public void toggleCheckedin() {
		if (map.get("checkedin") == null) {
			map.put("checkedin", "true");
		} else {
			map.put("checkedin", null);
		}
		save();
	}

	/**
	 * label a transaction checkout out or not
	 */
	public void toggleCheckedout() {
		if (map.get("checkedout") == null) {
			map.put("checkedout", "true");
		} else {
			map.put("checkedout", null);
		}
		save();
	}

	/**
	 * 
	 * @return the primary key for the customer in this transaction
	 */
	public String getUserKey() {
		return map.get("customer");
	}

	/**
	 * 
	 * @return the total price of the transaction
	 */
	public double getTotalPrice() {
		return Double.parseDouble(map.get("price"));
	}
}
