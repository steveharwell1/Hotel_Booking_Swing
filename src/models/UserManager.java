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
 * #UserManager The database manager for users
 * 
 * @author Team B
 * @since 2019-04-02
 *
 */
public class UserManager extends ModelManager {
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<LoginListener> listeners = new ArrayList<LoginListener>();
	String filename;

	/**
	 * reads users from file or creates a file with default users
	 * 
	 * @param filename the file to read from
	 */
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

	/**
	 * create a user from values
	 * 
	 * @param next values for a user
	 */
	private void createUser(Map<String, String> next) {
		createUser(next.get("name"), next.get("passwordHash"), next.get("permission"), false);
	}

	/**
	 * createUser attempts to create a user and returns a new user or an error
	 * message
	 * 
	 * @param name        the users name
	 * @param password    the users password
	 * @param permission  the users permission class
	 * @param rawPassword if the password is a hashed value or a string of the
	 *                    original password
	 * @return a user or error message
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

	/**
	 * Return back a user based on name and password or an error message
	 * 
	 * @param name     the name of the user
	 * @param password the user password
	 * @return user instance or error message
	 */
	public Optional<User> login(String name, String password) {
		for (User user : users) {
			if (user.getPrimaryKey().equals(name) && user.validatePassword(password)) {
				notifyLogin(user);
				return new Optional<User>(user);

			}
		}
		return new Optional<User>("Incorrect user name and password");
	}

	/**
	 * add a LoginListener to internal list of listeners
	 * 
	 * @param listener the listener to be added
	 */
	public void addLoginListener(LoginListener listener) {
		listeners.add(listener);
	}

	/**
	 * Notify all subscribed listeners of a user login
	 * 
	 * @param user the newly logged in user
	 */
	private void notifyLogin(User user) {
		for (LoginListener listener : listeners) {
			listener.loginOccurred(new UserEvent(user));
		}

	}

	/**
	 * Notify all subscribed listeners that the current user has logged out
	 */
	public void notifyLogout() {
		for (LoginListener listener : listeners) {
			listener.logoutOccurred();
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
