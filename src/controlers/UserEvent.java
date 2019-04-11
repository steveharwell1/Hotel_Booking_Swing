/**
 * 
 */
package controlers;

import models.User;

/**
 * @author Group B
 *
 */
public class UserEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User activeUser;
	/**
	 * @param source
	 */
	public UserEvent(User activeUser) {
		this.activeUser = activeUser;
	}
	
	public User getActiveUser() {
		return activeUser;
	}

}
