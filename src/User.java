
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
	
	public String getPrimaryKey() {
		return getName();
	}
}
