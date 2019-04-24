
package models;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import controlers.TransactionListener;
import utilities.CSVReader;
import utilities.CSVWriter;

/**
 * 
 * @author Group B
 *
 */
public class TransactionManager extends ModelManager {
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private ArrayList<TransactionListener> listeners = new ArrayList<TransactionListener>();
	String filename;

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
		for (Model transaction : transactions) {
			writer.addRow(transaction.asMap());
		}
		writer.save();

	}

	public boolean isAvailable(Room room, LocalDate begin, LocalDate end) {
		for (Transaction trans : transactions) {
			if (!trans.getRoom().equals(room.getPrimaryKey()) || begin.isEqual(trans.getEnd())
					|| end.isEqual(trans.getStart())) {
				// skip rooms that are not the one we are looking for
				continue;
			}
			// if search start date in a transaction
//			System.out.println(begin);
//			System.out.println(trans.getEnd());
//			System.out.println(trans.getStart());
			if (!(begin.isBefore(trans.getStart()) || begin.isAfter(trans.getEnd()))) {
				// transactions that start in another transaction
				return false;
			}
			if (!(end.isBefore(trans.getStart()) || end.isAfter(trans.getEnd()))) {
				// if search end date conflicts with another transaction
				return false;
			}
			if (end.isAfter(trans.getEnd()) && begin.isBefore(trans.getStart())) {
				return false;
			}

		}
		return true;

	}

	protected void addTransaction(Transaction transaction) {
		transactions.add(transaction);
		notifyListeners();
	}

	public ArrayList<Transaction> getCheckToday() {
		ArrayList<Transaction> att = new ArrayList<Transaction>();
		for (Transaction tran : transactions) {
			if (tran.getStart().equals(LocalDate.now()) || tran.getEnd().equals(LocalDate.now())) {
				att.add(tran);
			}
		}
		return att;
	}

	public ArrayList<Transaction> getUserTransactions(User user) {
		ArrayList<Transaction> att = new ArrayList<Transaction>();
		for (Transaction tran : transactions) {
			if (user.getPrimaryKey().equals(tran.getUserKey())) {
				att.add(tran);
			}
		}
		return att;
	}

	public void addTransactionListener(TransactionListener listener) {
		listeners.add(listener);
	}

	public void notifyListeners() {
		for (TransactionListener listener : listeners) {
			listener.transactionsUpdated();
		}
	}

}
