package views;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import javax.swing.JPanel;

import controlers.LoginListener;
import controlers.UserEvent;
import models.User;
import views.RedirectListener;

//Add Components imports
import javax.swing.JButton;
import javax.swing.JLabel;



public class CustomerMainView extends JPanel implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	private User activeUser;
	
	//Add component variables
	JButton makeReservation = new JButton("Make Reservation");
	JButton viewReservation = new JButton("View Reservations");
	JButton logout = new JButton("Log out");
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
		con.gridwidth = 2;
		this.add(welcomeMessage, con);
		
		con.gridx = 0;
		con.gridy = 2;
		con.gridwidth = 1;
		this.add(makeReservation, con);
		
		con.gridx = 1;
		con.gridy = 2;
		this.add(viewReservation, con);

		
		
		
		
		
		
//		Add Controllers to components
		makeReservation.addActionListener(e -> {
			// System.out.println(username.getText());
			// System.out.println(password.getPassword());
			System.out.println("I see you are trying to make a reservation!");
			viewChanger.redirect("DateSelectView");
		});
		viewReservation.addActionListener(e -> {
			// System.out.println(username.getText());
			// System.out.println(password.getPassword());
			System.out.println("Our reservation system is currently offline so you couldn't possibly have any transaction history!!");
			viewChanger.redirect("TransactionsView");
		});
		logout.addActionListener(e -> {
			System.out.println("Logging out...");
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void loginOccurred(UserEvent e) {
		// TODO Auto-generated method stub
		this.activeUser = e.getActiveUser();
		welcomeMessage.setText("Welcome to the Swing Hotels page, " + activeUser.getName() + "!");
		
	}

	@Override
	public void logoutOccurred() {
		// TODO Auto-generated method stub
		
	}
}
