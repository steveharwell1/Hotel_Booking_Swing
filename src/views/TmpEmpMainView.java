package views;

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



public class TmpEmpMainView extends JPanel implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	
	//Add component variables
	//JButton create = new JButton("Create Account");
	JButton closeRoom = new JButton("Close Room");
	JButton checkins = new JButton("Checkin/out Customers");
	JButton reports = new JButton("View Reports");
	JButton changePrice = new JButton("Change Weekend Price");
	JButton changePassword = new JButton("Change Password");
	JButton logout = new JButton("Logout");
	
	User activeUser;

	public TmpEmpMainView(RedirectListener viewChanger) {
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
		this.add(closeRoom, con);
		con.gridx = 1;
		this.add(checkins, con);
		con.gridx = 2;
		this.add(reports, con);
		
		con.gridy = 1;
		con.gridx = 0;
		this.add(changePrice, con);
		con.gridx = 1;
		this.add(changePassword, con);
		con.gridx = 2;
		this.add(logout, con);
		
//		Add Controllers to components
//		create.addActionListener(e -> {
//			System.out.println(username.getText());
//			System.out.println(password.getPassword());
//			viewChanger.redirect("CreateCustomer");
//		});
		
		
		logout.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});
		
		
		checkins.addActionListener(e -> {
			viewChanger.redirect("CheckinView");
		});
		
		reports.addActionListener(e -> {
			viewChanger.redirect("ReportsView");
		});
		
		changePrice.addActionListener(e -> {
			viewChanger.redirect("PricingView");
		});
		
		changePassword.addActionListener(e -> {
			viewChanger.redirect("UpdateAccountView");
		});
		
		
	}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();
		if(!activeUser.hasPermission("manager")) {
			changePrice.setEnabled(false);
			closeRoom.setEnabled(false);
		} else if (activeUser.hasPermission("manager")) {
			changePrice.setEnabled(true);
			closeRoom.setEnabled(true);
		}
		
	}

	@Override
	public void logoutOccurred() {
		this.activeUser = null;
		
	}
}
