package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSVReader implements AutoCloseable{
	private File file;
	private Scanner in;
	ArrayList<String> columns;
	public CSVReader(String filename) throws FileNotFoundException{
		this.file = new File(filename);
		this.in = new Scanner(file);
		this.columns = new ArrayList<String>();
		
		String[] strs = in.nextLine().split("[^/],");
		for(String str : strs) {
			columns.add(str.trim());
		}
		
		
		
	}
	
	public boolean hasNext() {
		return in.hasNextLine();
	}
	
	public Map<String, String> next() {
		Map<String, String> map = new HashMap<String, String>();
		String[] strs = in.nextLine().split("[^/],");
		for(int i = 0; i < columns.size(); ++i) {
			if(strs[i].trim().equals("null")) {
				map.put(columns.get(i), null);
			} else {
				map.put(columns.get(i), strs[i].trim());
			}
			
		}
		
		return map;
	}
	
	@Override
	public void close() {
		in.close();
	}
}
