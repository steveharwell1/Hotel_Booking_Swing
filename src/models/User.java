/**
 * #User
 * @author Team B
 * @since 2019-04-02
 *
 */
public class User extends Model {
	String name;
	UserManager manager;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * Constructor that takes the model manager to be used and the name of the user.
	 * 
	 * @param manager the manager that the user object will live in
	 * @param name the name of the user
	 */
	public User(UserManager manager, String name) {
		setName(name);
		setManager(manager);
	}

	/**
	 * @param manager the manager to set
	 */
	private void setManager(UserManager manager) {
		this.manager = manager;
	}
	
	/**
	 * 
	 */
	public String getPrimaryKey() {
		return getName();
	}
}
