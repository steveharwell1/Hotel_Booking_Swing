/**
 * 
 */
package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Settings Representation of system settings
 * 
 * @author Group B
 *
 */
public class Settings implements Model {
	private Map<String, String> map = new HashMap<String, String>();
	private static final String[] columns = { "weekendMarkup" };

	SettingsManager manager;

	/**
	 * Settings Create a setting from map
	 * 
	 * @param manager reference to the manager for saving
	 * @param map     the values to make a setting from
	 */
	protected Settings(SettingsManager manager, Map<String, String> map) {
		this.manager = manager;
		this.map.put(columns[0], map.get(columns[0]));
	}

	/**
	 * 
	 * @return the weekend markup
	 */
	public double getWeekendRate() {
		return Double.parseDouble(map.get("weekendMarkup"));
	}

	/**
	 * 
	 * @param rate the multiplier to mark up a room on the weekend
	 */
	public void setWeekendRate(Double rate) {
		this.map.put("weekendMarkup", rate.toString());
		this.manager.save();
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return map.get("weekendMarkup");
	}

	@Override
	public Map<String, String> asMap() {
		return map;
	}

	/**
	 * returns the columns used to save data to a csv file.
	 * 
	 * @return the columns
	 */
	public static String[] getColumns() {
		return columns;
	}

}
