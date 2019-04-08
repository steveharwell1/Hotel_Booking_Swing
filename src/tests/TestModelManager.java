package tests;

import models.User;
import models.UserManager;
import utilities.Optional;

/**
 * #TestModelManager
 * @author Team B
 * @since 2019-04-02
 *
 */
public class TestModelManager {

	public static void main(String[] args) {
		UserManager userManager = new UserManager();
		userManager.createUser("Dave");
		userManager.createUser("Jane");
		userManager.createUser("Sally");
		userManager.createUser("Craig");
		
		
		Optional<User> result = userManager.createUser("Dave");
		if(result.success()) {
			System.out.println("The user's name is " + result.get().getName());
		}
		else {
			System.out.println(result.getErrMsg());
		}
	}

}
