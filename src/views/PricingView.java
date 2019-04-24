package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlers.LoginListener;
import controlers.UserEvent;
import models.SettingsManager;
import models.User;

//Add Components imports
//import javax.swing.JButton;

/**
 * 
 * @author Group B
 *
 */
public class PricingView extends View implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	// Add component variables
	// JButton create = new JButton("Create Account");
	JTextField inputField = new JTextField(20);
	JLabel errorLabel = new JLabel("");
	JButton setButton = new JButton("Set Markup");
	JButton backButton = new JButton("back");
	JButton logoutButton = new JButton("logout");
	private SettingsManager settingsManager;
	private User activeUser;

	public PricingView(RedirectListener viewChanger) {
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
		con.gridwidth = GridBagConstraints.REMAINDER;

		JLabel marquee = new JLabel("Weekend Rate");
		marquee.setFont(jumboFont);
		this.add(marquee, con);

		con.gridy++;

		JLabel instruction = new JLabel("Please enter a number for weekend markup(1.00 is 100% markup)");
		instruction.setFont(labelFont);
		this.add(instruction, con);

		con.gridx = 0;
		con.gridy++;
		errorLabel.setFont(labelFont);
		this.add(errorLabel, con);

		con.gridx = 0;
		con.gridy++;

		this.add(inputField, con);
		con.gridwidth = 1;
		con.gridx = 0;
		con.gridy++;
		con.fill = GridBagConstraints.HORIZONTAL;
		this.add(setButton, con);

		con.gridx = 1;
		this.add(backButton, con);

		con.gridx = 2;
		this.add(logoutButton, con);

//		Add Controllers to components
//		create.addActionListener(e -> {
//			System.out.println(username.getText());
//			System.out.println(password.getPassword());
//			viewChanger.redirect("CreateCustomer");
//		});

		setButton.addActionListener(e -> {
			double markup = 0.0;
			try {
				markup = Double.parseDouble(inputField.getText());
			} catch (NumberFormatException exception) {
				errorLabel.setForeground(Color.RED);
				errorLabel.setText("You must enter a valid decimal number greater than or equal to zero");
				return;
			}
			if (markup < 0 || markup > 1.0) {
				errorLabel.setForeground(Color.RED);
				errorLabel.setText("Markup must be in the range [0.00, 1.00]");
				return;
			}
			settingsManager.getSettings().setWeekendRate(markup);
			errorLabel.setForeground(Color.blue);
			errorLabel.setText(String.format("Markup set to %.2f", markup));
		});

		backButton.addActionListener(e -> {
			viewChanger.redirect("EmpMainView");
		});

		logoutButton.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});

	}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();
		inputField.setText("" + settingsManager.getSettings().getWeekendRate());
	}

	@Override
	public void logoutOccurred() {
		this.activeUser = null;
		errorLabel.setText("");
	}

	public void addSettingsManager(SettingsManager settingsManager) {
		this.settingsManager = settingsManager;
	}
}
