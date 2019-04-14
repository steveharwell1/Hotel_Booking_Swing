/**
 * 
 */
package models;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import utilities.CSVReader;
import utilities.CSVWriter;

/**
 * @author steve
 *
 */
public class TransactionManager extends ModelManager {
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	String filename;
	/**
	 * 
	 */
	public TransactionManager(String filename) {
		this.filename = filename;

		try (CSVReader reader = new CSVReader(filename);) {
			while (reader.hasNext()) {
				this.transactions.add(this.createTransaction(reader.next()));
			}
		} catch (FileNotFoundException e) {
			this.save();
			return;
		}
	}

	private Transaction createTransaction(Map<String, String> next) {
		return new Transaction(this, next);
	}

	@Override
	public void save() {
		CSVWriter writer = new CSVWriter(filename, Transaction.getColumns());
		for (Model transaction: transactions) {
			writer.addRow(transaction.asMap());
		}
		writer.save();

	}

	public boolean isAvailable(Room room, LocalDate begin, LocalDate end) {
		for(Transaction trans : transactions) {
			if(trans.getRoom().equals(room.getPrimaryKey())) {
				continue;
			}
			if(end.isBefore(trans.getStart()) || end.isEqual(trans.getStart())) {
				continue;
			}
			if(begin.isAfter(trans.getEnd()) || begin.isEqual(trans.getEnd())) {
				continue;
			}
			return false;
		}
		return true;
		
	}

	protected void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

}
