
public class UserOptional {
	private String errMsg;
	private User user;
	private boolean success;
	
	UserOptional(User user){
		success = true;
		this.user = user;
	}
	
	UserOptional(String errMsg){
		success = false;
		this.errMsg = errMsg;
	}
	boolean succeded() {
		return success;
	}
	

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
