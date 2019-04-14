/**
 * 
 */
package models;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import utilities.CSVReader;
import utilities.CSVWriter;
import utilities.Optional;

/**
 * @author Group B
 *
 */
public class RoomManager extends ModelManager {
	private static final String filename = "rooms.csv";
	private ArrayList<Room> rooms = new ArrayList<Room>();

	/**
	 * 
	 */
	public RoomManager() {
		try (CSVReader reader = new CSVReader(filename);) {
			while (reader.hasNext()) {
				// Create room objects
				Map<String, String> map = reader.next();
				rooms.add(new Room(this, map.get("roomNumber"), map.get("style"), map.get("wheelChair"), map.get("floor"),
						map.get("rate")));
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Please place the room file in the program folder and name it \"rooms.csv\"", "Fatal Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.ModelManager#save()
	 */
	@Override
	public void save() {
		CSVWriter writer = new CSVWriter(filename, Room.getColumns());
		for (Model room : rooms) {
			writer.addRow(room.asMap());
		}
		writer.save();
	}

	public Optional<Room> createRoom(Map<String, String> map) {
		Room room = new Room(this, map.get("roomNumber"), map.get("style"), map.get("wheelChair"), map.get("floor"),
				map.get("rate"));
		rooms.add(room);
		return new Optional<Room>(room);
	}
	
	public Optional<Room> getRoom(String roomNumber)
	{
		for(Room room : rooms) {
			if(room.getPrimaryKey().equals(roomNumber))
			{
				return new Optional<Room>(room);
			}
		}
		return new Optional<Room>("Room number " + roomNumber + " not found");
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}

}
