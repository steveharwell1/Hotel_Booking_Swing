package controlers;

/**
 * LoginListener allows object to be notified when a use logs in or out.
 * 
 * @author Group B
 *
 */
public interface LoginListener {
	public void loginOccurred(UserEvent e);

	public void logoutOccurred();
}
