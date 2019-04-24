package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlers.LoginListener;
import controlers.UserEvent;
import models.User;
import models.UserManager;
import utilities.Optional;

/**
 * 
 * @author Group B
 *
 */
public class LoginView extends View implements LoginListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RedirectListener viewChanger;
	private JLabel errorLabel = new JLabel("");
	private JTextField username = new JTextField(20);
	private JPasswordField password = new JPasswordField(20);
	private JButton login = new JButton("Login");
	private JButton create = new JButton("Create Account");
	private UserManager userManager;

	public LoginView(RedirectListener viewChanger) {
		super();
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();

		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridwidth = GridBagConstraints.REMAINDER;

		con.gridx = 0;
		con.gridy = 0;
		JLabel marquee = new JLabel("TAMUC Inn");
		marquee.setFont(jumboFont);
		this.add(marquee, con);
		con.gridx = 0;
		con.gridy++;
		errorLabel.setFont(labelFont);
		this.add(errorLabel, con);
		con.gridwidth = 1;
		con.gridx = 0;
		con.gridy++;
		JLabel userLabel = new JLabel("User Name");
		userLabel.setFont(labelFont);
		this.add(userLabel, con);
		con.gridx = 1;
		add(username, con);
		con.gridx = 0;
		con.gridy++;
		JLabel passLabel = new JLabel("Password");
		passLabel.setFont(labelFont);
		this.add(passLabel, con);
		con.gridx = 1;
		this.add(password, con);
		con.gridx = 0;
		con.gridy++;
		this.add(login, con);
		con.gridx = 1;
		this.add(create, con);

		create.addActionListener(e -> {
			// This is insecure, side channels possible
			Optional<User> result = userManager.createUser(username.getText(), new String(password.getPassword()),
					"customer", true);
			if (!result.success()) {
				errorLabel.setForeground(Color.RED);
				errorLabel.setText(result.getErrMsg());
			} else {
				errorLabel.setForeground(Color.BLUE);
				errorLabel.setText("Account created successfully! You may now login.");
				userManager.save();
			}
		});

		login.addActionListener(e -> {
			// System.out.println(userManager);
			Optional<User> result = userManager.login(username.getText(), new String(password.getPassword()));
			if (!result.success()) {
				errorLabel.setForeground(Color.RED);
				errorLabel.setText("Incorrect user name or password");
			} else {

				if (result.get().hasPermission("customer")) {
					viewChanger.redirect("CustMainView");
				} else if (result.get().hasPermission("employee")) {
					viewChanger.redirect("EmpMainView");

				}

			}
		});
	}

	@Override
	public void loginOccurred(UserEvent e) {
		resetFields();
		errorLabel.setText("");
	}

	@Override
	public void logoutOccurred() {
		// TODO Auto-generated method stub

	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
