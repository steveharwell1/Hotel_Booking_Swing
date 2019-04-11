/**
 * 
 */
package tests;

import java.util.HashMap;
import java.util.Map;

import models.RoomManager;

/**
 * @author steve
 *
 */
public class TestRoomManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RoomManager roomManager = new RoomManager();
		int roomNumber = 1;
		Map<String, String> map = new HashMap<String, String>();
		map.put("roomNumber", "" + roomNumber++);
		map.put("style", "suite");
		map.put("wheelChair", "true");
		map.put("floor", "" + 1);
		map.put("rate", "" + 300.00);
		
		roomManager.createRoom(map);
		
		map.put("roomNumber", "" + roomNumber++);
		map.put("style", "suite");
		map.put("wheelChair", "true");
		map.put("floor", "" + 1);
		map.put("rate", "" + 300.00);
		
		roomManager.createRoom(map);
		
		map.put("roomNumber", "" + roomNumber++);
		map.put("style", "single bed");
		map.put("wheelChair", "false");
		map.put("floor", "" + 1);
		map.put("rate", "" + 100.00);
		
		roomManager.createRoom(map);
		
		map.put("roomNumber", "" + roomNumber++);
		map.put("style", "single bed");
		map.put("wheelChair", "false");
		map.put("floor", "" + 1);
		map.put("rate", "" + 100.00);
		
		roomManager.createRoom(map);
		
		map.put("roomNumber", "" + roomNumber++);
		map.put("style", "two bed");
		map.put("wheelChair", "false");
		map.put("floor", "" + 1);
		map.put("rate", "" + 200.00);
		
		roomManager.createRoom(map);
		roomManager.save();
	}

}
