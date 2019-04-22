/**
 * 
 */
package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Room a data model that represents a room in a hotel
 * 
 * @author Group B
 *
 */
public class Room implements Model {
	Map<String, String> map = new HashMap<String, String>();
	private final static String[] columns = { "roomNumber", "style", "wheelChair", "floor", "rate" };

	/**
	 * Room
	 * <p>
	 * Constructor for a room model
	 * 
	 * @param roomNumber the room number
	 * @param style      the style of room
	 * @param wheelChair is this room wheelchair accessible
	 * @param floor      what floor is the room on
	 * @param rate       the weekday price of the room
	 */
	public Room(String roomNumber, String style, String wheelChair, String floor, String rate) {
		// System.out.println("Creating roomNumer " + roomNumber);
		map.put("roomNumber", roomNumber);
		map.put("style", style);
		map.put("wheelChair", wheelChair);
		map.put("floor", floor);
		map.put("rate", rate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.Model#getPrimaryKey()
	 */
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return map.get("roomNumber");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.Model#asMap()
	 */
	@Override
	public Map<String, String> asMap() {
		return map;
	}

	/**
	 * 
	 * @return the columns that must be saved to file
	 */
	public static String[] getColumns() {
		return columns;
	}

	/**
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return Double.parseDouble(map.get("rate"));
	}

	@Override
	public String toString() {
		return "Room " + map.get("roomNumber") + " is a " + map.get("style") + ", and costs $" + map.get("rate")
				+ " for a weekday";
	}

}
