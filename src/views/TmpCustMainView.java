package views;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controlers.LoginListener;
import controlers.UserEvent;
import models.User;
import views.RedirectListener;

//Add Components imports
//import javax.swing.JButton;



public class TmpCustMainView extends JPanel implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	User activeUser;
	
	//Add component variables
	//JButton create = new JButton("Create Account");
	JButton logout = new JButton("logout");
	JButton reserve = new JButton("Make Reservation");
	JButton transButton = new JButton("Reservation History");
	JButton changePassword = new JButton("Change Password");
	

	public TmpCustMainView(RedirectListener viewChanger) {
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
		this.add(reserve, con);
		con.gridx = 1;
		this.add(transButton, con);
		con.gridy = 1;
		con.gridx = 0;
		this.add(changePassword, con);
		con.gridx = 1;
		this.add(logout, con);

//		Add Controllers to components
//		create.addActionListener(e -> {
//			System.out.println(username.getText());
//			System.out.println(password.getPassword());
//			viewChanger.redirect("CreateCustomer");
//		});
		
		reserve.addActionListener(e -> {
			viewChanger.redirect("ReservationView");
		});
		
		transButton.addActionListener(e -> {
			viewChanger.redirect("TransactionsView");
		});
		
		changePassword.addActionListener(e -> {
			viewChanger.redirect("UpdateAccountView");
		});
		
		logout.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});
	}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();
		
	}

	@Override
	public void logoutOccurred() {
		this.activeUser = null;
	}
}
