package tests;

import models.User;
import models.UserManager;
import utilities.Optional;

/**
 * #TestModelManager test the UserManager and User classes
 * 
 * @author Team B
 * @since 2019-04-02
 *
 */
public class TestModelManager {
	/**
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		UserManager userManager = new UserManager("test_users.csv");
//		userManager.createUser("Dave", "password1", true);
//		userManager.createUser("Jane", "password2", true);
//		userManager.createUser("Sally", "password3", true);
//		userManager.createUser("Craig", "password4", true);

		Optional<User> result = userManager.createUser("Dave", "password", "customer", true);
		if (result.success()) {
			System.out.println("The user's name is " + result.get().getName());
		} else {
			System.out.println(result.getErrMsg());
		}

		userManager.save();
	}

}
