package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlers.LoginListener;
import controlers.UserEvent;
import models.User;
import models.UserManager;
import utilities.Optional;

public class UpdateAccountView extends JPanel implements LoginListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RedirectListener viewChanger;
	private User activeUser;
	private JLabel errorLabel = new JLabel("");
	private JLabel greeting = new JLabel("Hello");
	private JPasswordField password = new JPasswordField(20);
	private JButton backButton = new JButton("Back");
	private JButton logoutButton = new JButton("Logout");
	private JButton changePassword = new JButton("Change Password");

	public UpdateAccountView(RedirectListener viewChanger) {
		super();
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}
	
	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();
		
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 3;
		this.add(greeting, con);
		
		con.gridx = 0;
		con.gridy++;
		this.add(errorLabel, con);
		
		con.gridy++;
		this.add(password, con);
		
		con.gridwidth = 1;
		con.gridy++;
		this.add(changePassword, con);
		
		con.gridx = 1;
		this.add(logoutButton, con);
				
		con.gridx = 2;
		this.add(backButton, con);
		
		changePassword.addActionListener(e -> {
			errorLabel.setForeground(Color.BLUE);
			errorLabel.setText("Password Change Successful");
			activeUser.setPasswordHash(new String(password.getPassword()));
			activeUser.save();
		});
		
		logoutButton.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});
		
	}
	
	void resetFields() {
		Component[] components = this.getComponents();
		for(Component c : components) {
			if(c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
	}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();
		//System.out.println("Login occured for " + activeUser.getName());
		String greetingText = String.format(
				"Hello %s!%n%n Please change your password",
				activeUser.getName());
		this.greeting.setText(greetingText);
	}

	@Override
	public void logoutOccurred() {
		//System.out.println("Logout occurred for " + activeUser.getName());
		this.activeUser = null;
		this.password.setText("");
		this.greeting.setText("");
	}
}
