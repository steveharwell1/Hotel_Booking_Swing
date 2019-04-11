/**
 * 
 */
package models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author steve
 *
 */
public class Room implements Model {
	Map<String, String> map = new HashMap<String, String>();
	private final static String [] columns = {"roomNumber", "style", "wheelChair", "floor", "rate"};
	
	
	public Room(String roomNumber, String style, String wheelChair, String floor, String rate) {
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

}
