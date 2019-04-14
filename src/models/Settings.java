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
public class Settings implements Model {
	private Map<String, String> map = new HashMap<String, String>();
	private static final String[] columns = {"weekendMarkup"};
	
	SettingsManager manager;
	/**
	 * 
	 */
	protected Settings(SettingsManager manager, Map<String, String> map) {
		this.manager = manager;
		this.map.put(columns[0], map.get(columns[0]));
	}
	
	public double getWeekendRate() {
		return Double.parseDouble(map.get("weekendMarkup"));
	}
	
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

	public static String[] getColumns() {
		return columns;
	}

}
