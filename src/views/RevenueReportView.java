package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlers.LoginListener;
import controlers.UserEvent;
import models.Transaction;
import models.TransactionManager;
import models.User;

//Add Components imports
//import javax.swing.JButton;

/**
 * 
 * @author Group B
 *
 */
public class RevenueReportView extends JPanel implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	User activeUser;
	// Add component variables
	// JButton create = new JButton("Create Account");
	JButton backButton = new JButton("back");
	JButton logoutButton = new JButton("logout");
	JLabel dailyRev = new JLabel("This is where the report goes");
	private TransactionManager transactionManager;

	public RevenueReportView(RedirectListener viewChanger) {
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

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		this.add(dailyRev, con);

		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 1;
		this.add(backButton, con);

		con.gridx = 1;
		con.gridy = 1;
		this.add(logoutButton, con);

//		Add Controllers to components
//		create.addActionListener(e -> {
//			System.out.println(username.getText());
//			System.out.println(password.getPassword());
//			viewChanger.redirect("CreateCustomer");
//		});

		backButton.addActionListener(e -> {
			viewChanger.redirect("ReportsView");
		});

		logoutButton.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});
	}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();
		double total = 0;
		for (Transaction tran : transactionManager.getCheckToday()) {
			if (tran.getStart().equals(LocalDate.now())) {
				total += tran.getTotalPrice();
			}
		}
		dailyRev.setText(String.format("Today's revenue is $%.2f", total));
	}

	@Override
	public void logoutOccurred() {
		this.activeUser = null;

	}

	public void addTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
