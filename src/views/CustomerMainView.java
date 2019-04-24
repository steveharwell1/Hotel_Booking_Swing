package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

//Add Components imports
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlers.LoginListener;
import controlers.UserEvent;
import models.User;

public class CustomerMainView extends JPanel implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	private User activeUser;

	// Add component variables
	JButton makeReservation = new JButton("Make Reservation");
	JButton viewReservation = new JButton("View Reservations");
	JButton logout = new JButton("Log out");
	JButton changePassword = new JButton("Change Password");
	JLabel welcomeMessage = new JLabel("You are not logged in!");

	public CustomerMainView(RedirectListener viewChanger) {
		super();
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();

//		Add components to layout and save inputs to fields

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = GridBagConstraints.REMAINDER;
		this.add(welcomeMessage, con);

		con.gridx = 0;
		con.gridy = 2;
		con.gridwidth = 1;
		this.add(makeReservation, con);

		con.gridx = 1;
		con.gridy = 2;
		this.add(viewReservation, con);

		con.gridx = 3;
		con.gridy = 2;
		this.add(logout, con);

		con.gridx = 2;
		con.gridy = 2;
		this.add(changePassword, con);

//		Add Controllers to components
		makeReservation.addActionListener(e -> {
			// System.out.println(username.getText());
			// System.out.println(password.getPassword());
			System.out.println("I see you are trying to make a reservation!");
			viewChanger.redirect("ReservationView");
		});
		viewReservation.addActionListener(e -> {
			// System.out.println(username.getText());
			// System.out.println(password.getPassword());
			System.out.println(
					"Our reservation system is currently offline so you couldn't possibly have any transaction history!!");
			viewChanger.redirect("TransactionsView");
		});
		logout.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});
		changePassword.addActionListener(e -> {
			viewChanger.redirect("UpdateAccountView");
		});

	}

	@Override
	public void loginOccurred(UserEvent e) {
		// TODO Auto-generated method stub
		this.activeUser = e.getActiveUser();
		//
		java.awt.Font f = welcomeMessage.getFont().deriveFont(16f);
		welcomeMessage.setFont(f);
		welcomeMessage.setText("Welcome to the Swing Hotels page, " + activeUser.getName() + "!");

	}

	@Override
	public void logoutOccurred() {
		this.activeUser = null;

	}
}
