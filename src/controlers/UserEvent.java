/**
 * 
 */
package controlers;

import models.User;

/**
 * UserEvent simply carries the user that has loggin in to LoginListeners
 * 
 * @author Group B
 *
 */
public class UserEvent {
	User activeUser;

	/**
	 * @param activeUser the user that is now active
	 */
	public UserEvent(User activeUser) {
		this.activeUser = activeUser;
	}

	/**
	 * 
	 * @return the user that is not active
	 */
	public User getActiveUser() {
		return activeUser;
	}

}
