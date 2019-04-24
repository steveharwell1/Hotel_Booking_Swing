/**
 * 
 */
package application;

import java.time.LocalTime;

import utilities.Optional;

/**
 * Fake credit card verifier Uses the singleton design pattern to simulate an
 * online resource
 * 
 * @author Group B
 *
 */
public class CCVerifier {
	private static CCVerifier instance;

	/**
	 * Singleton design pattern means the constructor is private
	 */
	private CCVerifier() {
		instance = this;
	}

	/**
	 * @return the one and only copy of CCVerifier
	 */
	public static CCVerifier getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new CCVerifier();
		}
	}

	/**
	 * Very simple verification of a Credit Card Number
	 * 
	 * @param ccNumbers the credentials to be verified
	 * @return Optional or type Long Returns a value or error message
	 */
	public Optional<Long> verify(String ccNumbers) {
		if (ccNumbers.length() < 1) {
			return new Optional<Long>("Credit Card numbers need to be longer than 0");
		} else {
			return new Optional<Long>(LocalTime.now().toNanoOfDay());
		}
	}

}
