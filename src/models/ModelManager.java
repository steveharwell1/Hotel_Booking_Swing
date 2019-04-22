package models;

/**
 * ModelManager the root interface for a manager of a model. The manager's
 * responsibility is to save and read data from long term storage to create
 * instances of data models
 * 
 * @author Group B
 *
 */
public abstract class ModelManager {
	public abstract void save();
}
