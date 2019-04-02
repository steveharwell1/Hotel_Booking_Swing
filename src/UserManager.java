import java.util.ArrayList;

public class UserManager extends ModelManager {
	ArrayList<User> users = new ArrayList<User>();

	public UserManager() {
		users.add(new User(this, "Dave"));
		users.add(new User(this, "Jane"));
		users.add(new User(this, "Sally"));
		users.add(new User(this, "Craig"));
	}

	public User getUser(int i) {
		return users.get(i);
	}

	public UserOptional createUser(String name) {
		boolean found = false;
		for (User user : users) {
			if (user.getPrimaryKey().equals(name)) {
				found = true;
			}
		}
		if (found)
			return new UserOptional("User name exists");
		else {
			User user = new User(this, name);
			users.add(user);
			return new UserOptional(user);
		}

	}
	
	public void save() {};
}
