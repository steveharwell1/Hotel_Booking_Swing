package tests;

import models.Settings;
import models.SettingsManager;

/**
 * SettingsTester Tests the functionality of the Settings class and the
 * SettingsManager class
 * 
 * @author Group B
 *
 */
public class SettingsTester {

	/**
	 * @param args unused
	 */
	public static void main(String[] args) {
		SettingsManager manager = new SettingsManager("test.csv");
		Settings setting = manager.getSettings();
		System.out.println(setting.getWeekendRate());
	}

}
