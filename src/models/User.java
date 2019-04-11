/**
 * #User
 * @author Team B
 * @since 2019-04-02
 *
 */
package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

public class User implements Model {
	String name;
	String passwordHash;
	String permission;
	static String[] columns = {"name", "passwordHash", "permission"};
	Set<String> permissions = new HashSet<String>();
	
	UserManager manager;
	
	/**
	 * Constructor that takes the model manager to be used and the name of the user.
	 * 
	 * @param manager the manager that the user object will live in
	 * @param name the name of the user
	 */
	public User(UserManager manager, String name, String password, String permission, Boolean rawPassword) {
		setName(name);
		setManager(manager);
		this.permission = permission;
		if(rawPassword) {
			setPasswordHash(password);
		} else {
			this.passwordHash = password;
		}
		
		switch(permission)
		{
		case "customer":
			permissions.add("customer");
			break;
		case "manager":
			permissions.add("manager");
		case "employee":
			permissions.add("employee");
			break;
		}
		
	}
	
	public static String hash(String string) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		md.update(string.getBytes());
		
	    byte[] digest = md.digest();
	    String hash = DatatypeConverter.printHexBinary(digest);
	    
		return hash.substring(0, Math.min(hash.length(), 2));
	}
	
	public boolean hasPermission(String permission) {
		return permissions.contains(permission);
	}
	
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

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = hash(passwordHash);
	}
	
	public boolean validatePassword(String password) {
		return this.getPasswordHash().equals(hash(password));
	}

	@Override
	public Map<String, String> asMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("passwordHash", passwordHash);
		map.put("name", name);
		map.put("permission", permission);
		return map;
	}

	public static String[] getColumns() {
		return columns;
	}
	
	public void save() {
		manager.save();
	}
	
	public void logout() {
		manager.notifyLogout();
	}
}
