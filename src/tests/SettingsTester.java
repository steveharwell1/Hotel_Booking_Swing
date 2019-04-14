/**
 * 
 */
package tests;


import models.Settings;
import models.SettingsManager;

/**
 * @author steve
 *
 */
public class SettingsTester {

	/**
	 * 
	 */
	public SettingsTester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SettingsManager manager = new SettingsManager("test.csv");
		Settings setting = manager.getSettings();
		System.out.println(setting.getWeekendRate());
	}

}
