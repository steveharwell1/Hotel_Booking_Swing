/**
 * 
 */
package tests;

import models.User;
import models.UserManager;

/**
 * @author steve
 *
 */
public class TestUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserManager manager = new UserManager("UserTest.csv");
		String ps = "password";
		User user = new User(manager, "steve", ps, "customer", true);
		
		
		System.out.println(user.getPasswordHash());
		System.out.println(User.hash(ps));
		
		System.out.println(user.validatePassword(ps));
	}

}
