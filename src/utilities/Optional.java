/**
 * #Optional
 * @author Team B
 * @since 2019-04-02
 *
 */
public class Optional<T> {
	private String errMsg;
	private T obj;
	private boolean success;
	
	/**
	 * Constructor for a generic type. This represent a successfully created object
	 * @param obj the successfully created object
	 */
	Optional(T obj){
		success = true;
		this.obj = obj;
	}
	
	/**
	 * This constructor represent a failure to create the object of type T
	 * 
	 * @param errMsg the error message indicating the failure.
	 */
	Optional(String errMsg){
		success = false;
		this.errMsg = errMsg;
	}
	
	/**
	 * Test if the object was successfully created.
	 * 
	 * @return boolean true = an object in the optional and false = error message in the optional
	 */
	boolean succeeded() {
		return success;
	}
	

	/**
	 * Return the error message
	 * 
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * Object of type T is returned
	 * 
	 * @return the object
	 */
	public T get() {
		return this.obj;
	}
}
