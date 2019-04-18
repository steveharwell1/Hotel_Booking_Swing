/**
 * @author Group B
 */
package models;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Group B
 *
 */
public class Transaction implements Model {
	Map<String, String> map = new HashMap<String, String>();
	TransactionManager manager;
	private final static String[] columns = {
			"customer",
			"roomNumber",
			"checkin",
			"checkedin",
			"checkout",
			"checkedout",
			"price",
			"transactionCode" };
	private ChronoLocalDate start;
	private ChronoLocalDate end;
	
	public Transaction(TransactionManager manager, User user) {
		this.manager = manager;
		map.put("customer", user.getPrimaryKey());
	}
	
	protected Transaction(TransactionManager manager, Map<String, String> map) {
		this.manager = manager;
		for(String key : columns)
		{
			this.map.put(key, map.get(key));
		}
		
		setStart(this.map.get("checkin"));
		setEnd(this.map.get("checkout"));
	}
	
	public void setEnd(String string) {
		this.end = LocalDate.parse(string);
		this.map.put("checkout", this.end.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

	public void setStart(String string) {
		this.start = LocalDate.parse(string);
		this.map.put("checkin", this.start.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}
	
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
	
	public static String[] getColumns() {
		return columns;
	}
	
	public void save() { manager.save(); }

	public ChronoLocalDate getStart() {
		// TODO Auto-generated method stub
		return start;
	}

	public String getRoom() {
		return map.get("roomNumber");
	}
	public void setRoom(String roomNumber) {
		this.map.put("roomNumber", roomNumber);
	}

	public ChronoLocalDate getEnd() {
		// TODO Auto-generated method stub
		return end;
	}

	public void setPrice(Double total) {
		this.map.put("price", total.toString());
	}

	public void setConfirmationCode(String code) {
		this.map.put("transactionCode", code);	
	}
	
	@Override
	public String toString() {
		String s = "Room: " + map.get("roomNumber") + " Guest: " + map.get("customer") + " Confirm: " + map.get("transactionCode");
		s = s + " Checked in: " + (map.get("checkedin") == null? "No ": "Yes ");
		s = s + " Checked out: " + (map.get("checkedout") == null? "No ": "Yes ");
		return s;
	}

	public void toggleCheckedin() {
		if(map.get("checkedin") == null) {
			map.put("checkedin", "true");
		} else {
			map.put("checkedin", null);
		}
		save();
	}
	
	public void toggleCheckedout() {
		if(map.get("checkedout") == null) {
			map.put("checkedout", "true");
		} else {
			map.put("checkedout", null);
		}
		save();
	}
}
