/**
 * 
 */
package models;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import utilities.CSVReader;
import utilities.CSVWriter;

/**
 * SettingsManager
 * 
 * Handles loading and saving setting to file.
 * 
 * @author Group B
 *
 */
public class SettingsManager extends ModelManager {

	private String filename;
	private Settings settings;

	/**
	 * SettingsManager Loads settings from file or starts a file with default
	 * settings
	 * 
	 * @param filename the name of the file to load the settings from
	 */
	public SettingsManager(String filename) {
		this.filename = filename;

		try (CSVReader reader = new CSVReader(filename);) {
			if (reader.hasNext()) {
				this.settings = new Settings(this, reader.next());
			}
		} catch (FileNotFoundException e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("weekendMarkup", "0.1");
			this.settings = new Settings(this, map);
			this.save();
			return;
		}
	}

	/*
	 * Settings is only to be a single instance
	 * 
	 * @return the settings instance
	 */
	public Settings getSettings() {
		return settings;
	}

	@Override
	public void save() {
		CSVWriter writer = new CSVWriter(filename, Settings.getColumns());
		writer.addRow(settings.asMap());
		writer.save();
	}

}
