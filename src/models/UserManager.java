package models;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import controlers.LoginListener;
import controlers.UserEvent;
import utilities.CSVReader;
import utilities.CSVWriter;
import utilities.Optional;

/**
 * #UserManager
 * 
 * @author Team B
 * @since 2019-04-02
 *
 */
public class UserManager extends ModelManager {
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<LoginListener> listeners = new ArrayList<LoginListener>();
	String filename;

	public UserManager(String filename) {
		this.filename = filename;

		try (CSVReader reader = new CSVReader(filename);) {
			while (reader.hasNext()) {
				this.createUser(reader.next());
			}
		} catch (FileNotFoundException e) {
			this.createUser("admin", "admin", "manager", true);
			this.createUser("employee1", "admin", "employee", true);
			this.save();
			return;
		}
	}

	private void createUser(Map<String, String> next) {
		createUser(next.get("name"), next.get("passwordHash"), next.get("permission"), false);
	}

	/**
	 * createUser attempts to create a user and returns a new user or an error
	 * message
	 * 
	 * @param name the name of the new user
	 * @return a unique new user
	 */
	public Optional<User> createUser(String name, String password, String permission, boolean rawPassword) {
		boolean found = false;
		for (User user : users) {
			if (user.getPrimaryKey().equals(name)) {
				found = true;
			}
		}
		if (found)
			return new Optional<User>("User name exists");
		else {
			User user = new User(this, name, password, permission, rawPassword);
			users.add(user);
			return new Optional<User>(user);
		}

	}

	public Optional<User> login(String name, String password) {
		for (User user : users) {
			if (user.getPrimaryKey().equals(name) && user.validatePassword(password)) {
				notifyLogin(user);
				return new Optional<User>(user);

			}
		}
		return new Optional<User>("Incorrect user name and password");
	}

	public void addLoginListener(LoginListener listener) {
		listeners.add(listener);
	}

	private void notifyLogin(User user) {
		for (LoginListener listener : listeners) {
			listener.loginOccured(new UserEvent(user));
		}

	}

	public void notifyLogout() {
		for (LoginListener listener : listeners) {
			listener.logoutOccured();
		}
	}

	/**
	 * save does nothing
	 * 
	 * required to be implemented by the abstract class, but this class only holds
	 * data during execution so there is no need to save.
	 */
	@Override
	public void save() {
		CSVWriter writer = new CSVWriter(filename, User.getColumns());
		for (Model user : users) {
			writer.addRow(user.asMap());
		}
		writer.save();
	}
}
