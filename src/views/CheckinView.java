package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

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
public class CheckinView extends JPanel implements LoginListener {
	private static final long serialVersionUID = 1L;
	private RedirectListener viewChanger;
	private User activeUser;
	// Add component variables
	// JButton create = new JButton("Create Account");
	private JList<Transaction> roomList = new JList<Transaction>();
	private JLabel errorLabel = new JLabel("");
	private JButton back = new JButton("Back");
	private JButton logout = new JButton("Logout");
	private JButton check = new JButton("Checkin/out");
	private JCheckBox discount = new JCheckBox("Apply discount at check in");
	private TransactionManager transactionManager;

	public CheckinView(RedirectListener viewChanger) {
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

		//////////////// First Row/////////////////////
		con.gridy = 0;
		con.gridwidth = GridBagConstraints.REMAINDER;

		this.add(roomList, con);
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//////////////// Next Row/////////////////////
		con.gridy++;
		con.gridwidth = 3;

		con.gridx = 0;
		this.add(errorLabel, con);

		//////////////// Next Row/////////////////////
		con.gridy++;

		con.gridx = 0;
		con.gridwidth = 1;
		this.add(check, con);

		con.gridx = 1;
		this.add(back, con);

		con.gridx = 2;
		this.add(logout, con);

		// right hand column
		con.gridx = 3;
		con.gridy = 1;
		con.gridheight = GridBagConstraints.REMAINDER;
		this.add(discount, con);

//		Add Controllers to components
//		create.addActionListener(e -> {
//			System.out.println(username.getText());
//			System.out.println(password.getPassword());
//			viewChanger.redirect("CreateCustomer");
//		});

		back.addActionListener(e -> {
			errorLabel.setText("");
			viewChanger.redirect("EmpMainView");
		});

		logout.addActionListener(e -> {
			errorLabel.setText("");
			activeUser.logout();
			viewChanger.redirect("Login");
		});

		check.addActionListener(e -> {
			errorLabel.setText("");
			Transaction selectedTran = roomList.getSelectedValue();
			if (selectedTran == null) {
				errorLabel.setForeground(Color.RED);
				errorLabel.setText("You must select a room");
				return;
			}

			if (selectedTran.getStart().equals(LocalDate.now())) {
				if (discount.isSelected() && selectedTran.asMap().get("checkedin") == null) {
					selectedTran.setPrice(selectedTran.getTotalPrice() * .9);
					selectedTran.save();
					discount.setSelected(false);
				}
				selectedTran.toggleCheckedin();
				errorLabel.setForeground(Color.BLUE);
				errorLabel.setText("Room " + selectedTran.getRoom() + " check in status updated");
			}
			if (selectedTran.getEnd().equals(LocalDate.now())) {
				selectedTran.toggleCheckedout();
				errorLabel.setForeground(Color.BLUE);
				errorLabel.setText("Room " + selectedTran.getRoom() + " check out status updated");
			}
			roomList.clearSelection();
		});

	}

	@Override
	public void loginOccurred(UserEvent e) {
		errorLabel.setText("");
		activeUser = e.getActiveUser();

		DefaultListModel<Transaction> listModel = new DefaultListModel<Transaction>();

		for (Transaction tran : transactionManager.getCheckToday()) {
			listModel.addElement(tran);
		}

		roomList.setModel(listModel);

	}

	@Override
	public void logoutOccurred() {
		errorLabel.setText("");
		activeUser = null;
		roomList.setModel(new DefaultListModel<Transaction>());
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
