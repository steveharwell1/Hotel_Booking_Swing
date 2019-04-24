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
 * RoomManager handles reading and writing room data to disk additionally
 * aggregate room methods
 * 
 * @author Group B
 *
 */
public class RoomManager extends ModelManager {
	private static final String filename = "rooms.csv";
	private ArrayList<Room> rooms = new ArrayList<Room>();

	/**
	 * Load a file and create rooms in memory or display a fatal error
	 */
	public RoomManager() {
		try (CSVReader reader = new CSVReader(filename);) {
			while (reader.hasNext()) {
				// Create room objects
				Map<String, String> map = reader.next();
				rooms.add(new Room(map.get("roomNumber"), map.get("style"), map.get("wheelChair"), map.get("floor"),
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

	/**
	 * createRoom creates a room in memory
	 * 
	 * @param map the value to be given to a room
	 * @return Optional of type room
	 */
	public Optional<Room> createRoom(Map<String, String> map) {
		Room room = new Room(map.get("roomNumber"), map.get("style"), map.get("wheelChair"), map.get("floor"),
				map.get("rate"));
		rooms.add(room);
		return new Optional<Room>(room);
	}

	/**
	 * getRoom
	 * 
	 * Searches through all rooms for a room matching the primary key roomNumber
	 * 
	 * @param roomNumber the room number of the room
	 * @return the room or error message
	 */
	public Optional<Room> getRoom(String roomNumber) {
		for (Room room : rooms) {
			if (room.getPrimaryKey().equals(roomNumber)) {
				return new Optional<Room>(room);
			}
		}
		return new Optional<Room>("Room number " + roomNumber + " not found");
	}

	/**
	 * getRooms gives all rooms
	 * 
	 * @return arrayList of rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}

}
