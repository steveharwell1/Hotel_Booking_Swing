
package models;

import java.util.Map;

/**
 * Model root interface for all data models
 * 
 * @author Group B
 *
 */
public interface Model {
	public String getPrimaryKey();

	public Map<String, String> asMap();
}
