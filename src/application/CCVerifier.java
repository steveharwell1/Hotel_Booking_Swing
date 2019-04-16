/**
 * 
 */
package application;

import java.time.LocalTime;

import utilities.Optional;

/**
 * @author steve
 *
 */
public class CCVerifier {
	private static CCVerifier instance;
	
	private CCVerifier() {
		instance = this;
	}

	public static CCVerifier getInstance() {
		if(instance != null) {
			return instance;
		} else {
			return new CCVerifier();
		}
	}
	
	public Optional<Long> verify(String ccNumbers){
		if(ccNumbers.length() < 1) {
			return new Optional<Long>("Credit Card numbers need to be longer than 0");
		}
		else {
			return new Optional<Long>(LocalTime.now().toNanoOfDay());
		}
	}

}
