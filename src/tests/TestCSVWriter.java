package tests;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import utilities.CSVReader;
import utilities.CSVWriter;

public class TestCSVWriter {

	public static void main(String[] args) {
		CSVWriter writer = new CSVWriter("Test.csv", "Username", "Password");
		Map<String, String> map = new HashMap<String, String>();
		map.put("Username", "bob");
		map.put("Password", "pass");
		
		writer.addRow(map);
		
		map.put("Username", "c,raig/");
		
		writer.addRow(map);
		
		writer.save();
		
		String str = "Hello, world";
		System.out.println(CSVWriter.escape(str));
		
		try(CSVReader reader = new CSVReader("Test.csv");) {
			while(reader.hasNext()) {
				System.out.println(reader.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
