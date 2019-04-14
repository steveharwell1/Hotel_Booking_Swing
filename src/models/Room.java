/**
 * 
 */
package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Group B
 *
 */
public class Room implements Model {
	Map<String, String> map = new HashMap<String, String>();
	private RoomManager manager;
	private final static String [] columns = {"roomNumber", "style", "wheelChair", "floor", "rate"};
	
	
	public Room(RoomManager manager, String roomNumber, String style, String wheelChair, String floor, String rate) {
		//System.out.println("Creating roomNumer " + roomNumber);
		this.manager = manager;
		map.put("roomNumber", roomNumber);
		map.put("style", style);
		map.put("wheelChair", wheelChair);
		map.put("floor", floor);
		map.put("rate", rate);
	}

	/* (non-Javadoc)
	 * @see models.Model#getPrimaryKey()
	 */
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return map.get("roomNumber");
	}

	/* (non-Javadoc)
	 * @see models.Model#asMap()
	 */
	@Override
	public Map<String, String> asMap() {
		return map;
	}

	public static String[] getColumns() {
		return columns;
	}

	public double getPrice() {
		return Double.parseDouble(map.get("rate"));
	}
	
	public String toString() {
		return "Room " + map.get("roomNumber") + " is a " + map.get("style") +
				", and costs $" + map.get("rate") + " for a weekday";
	}
	
}
