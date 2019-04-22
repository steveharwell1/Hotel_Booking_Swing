package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Write to a csv file.
 * 
 * @author Group B
 *
 */
public class CSVWriter {
	private ArrayList<String> columns = new ArrayList<String>();
	private ArrayList<Map<String, String>> table = new ArrayList<Map<String, String>>();
	File file;

	public CSVWriter(String fileName, String... columns) {
		this.file = new File(fileName);
		for (String column : columns) {
			addColumn(column);
		}
	}

	/**
	 * add a column value to be recorded in each row
	 * 
	 * @param column the column to be written
	 */
	private void addColumn(String column) {
		this.columns.add(column);
	}

	/**
	 * 
	 * @param row a record to be written to file
	 */
	public void addRow(Map<String, String> row) {
		HashMap<String, String> tmpRow = new HashMap<String, String>();
		for (String column : columns) {
			// String val = row.get(column);
			tmpRow.put(column, row.get(column));
		}
		table.add(tmpRow);
	}

	/**
	 * Save all records to file
	 */
	public void save() {
		try (PrintWriter out = new PrintWriter(file);) {

			///// Print the column names on the first row
			for (int i = 0; i < columns.size(); ++i) {
				out.print(escape(columns.get(i)));
				if (i != columns.size() - 1) {
					out.print(",");
				}
			}
			out.println();
			// print each row
			for (Map<String, String> row : table) {
				for (int i = 0; i < columns.size(); ++i) {
					out.print(escape(row.get(columns.get(i))));
					if (i != columns.size() - 1) {
						out.print(",");
					}
				}
				out.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param str the raw string to escape for a csv
	 * @return the formatted string
	 */
	public static String escape(String str) {
		if (str == null) {
			return "null ";
		}
		return str.replaceAll(",", "/,") + " ";
	}
}
