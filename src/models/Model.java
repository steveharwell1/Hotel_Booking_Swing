/**
 * #Model
 * @author Team B
 * @since 2019-04-02
 *
 */
package models;

import java.util.Map;

public interface Model {
	public abstract String getPrimaryKey();
	public Map<String, String> asMap();
}
