import java.util.ArrayList;


/**
 * #UserManager
 * @author Team B
 * @since 2019-04-02
 *
 */
public class UserManager extends ModelManager {
	ArrayList<User> users = new ArrayList<User>();

	/**
	 * getUser returns a user at index i this is private because it might only be useful internally
	 * 
	 * @param i index into the list of users
	 * @return user at index i
	 */
	private User getUser(int i) {
		return users.get(i);
	}

	/**
	 * createUser
	 * attempts to create a user and returns a new user or an error message
	 * 
	 * @param name
	 * @return a unique new user
	 */
	public Optional<User> createUser(String name) {
		boolean found = false;
		for (User user : users) {
			if (user.getPrimaryKey().equals(name)) {
				found = true;
			}
		}
		if (found)
			return new Optional<User>("User name exists");
		else {
			User user = new User(this, name);
			users.add(user);
			return new Optional<User>(user);
		}

	}
	/**
	 * save does nothing
	 * 
	 * required to be implemented by the abstract class, but this class only holds
	 * data during execution so there is no need to save.
	 */
	public void save() {};//empty
}
