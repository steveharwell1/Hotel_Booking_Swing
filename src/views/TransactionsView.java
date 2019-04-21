package views;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import controlers.LoginListener;
import controlers.TransactionListener;
import controlers.UserEvent;
import models.Room;
import models.Transaction;
import models.TransactionManager;
import models.User;
import views.RedirectListener;

//Add Components imports
//import javax.swing.JButton;

public class TransactionsView extends JPanel implements LoginListener, TransactionListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	User activeUser;
	// Add component variables
	// JButton create = new JButton("Create Account");

	JList<Transaction> transactionList = new JList<Transaction>();
	JButton backButton = new JButton("back");
	JButton logoutButton = new JButton("Logout");
	private TransactionManager transactionManager;

	public TransactionsView(RedirectListener viewChanger) {
		super();
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();

//		Add components to layout and save inputs to fields
//		con.gridx = 1;
//		con.gridy = 2;
//		this.add(create, con);

		con.gridy = 0;
		con.gridx = 0;
		con.gridwidth = 2;
		this.add(transactionList, con);

		con.gridy = 1;
		con.gridwidth = 1;
		this.add(backButton, con);
		con.gridx = 1;
		this.add(logoutButton, con);

//		Add Controllers to components
//		create.addActionListener(e -> {
//			System.out.println(username.getText());
//			System.out.println(password.getPassword());
//			viewChanger.redirect("CreateCustomer");
//		});

		logoutButton.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});

		backButton.addActionListener(e -> {
			viewChanger.redirect("CustMainView");
		});
	}

	private void updateList() {
		DefaultListModel<Transaction> listModel = new DefaultListModel<Transaction>();
		for (Transaction tran : transactionManager.getUserTransactions(activeUser)) {
			listModel.addElement(tran);
		}
		transactionList.setModel(listModel);
	}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();

		updateList();
	}

	@Override
	public void logoutOccurred() {
		this.activeUser = null;
	}

	public void addTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void transactionsUpdated() {
		updateList();

	}
}
