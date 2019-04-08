package views;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	JTextField username = new JTextField(20);
	JPasswordField password = new JPasswordField(20);
	JButton login = new JButton("Login");
	JButton create = new JButton("Create Account");

	public LoginView(RedirectListener viewChanger) {
		super();
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}
	
	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 0;
		this.add(new JLabel("User Name"), con);
		con.gridx = 1;
		con.gridy = 0;
		add(username, con);
		con.gridx = 0;
		con.gridy = 1;
		this.add(new JLabel("Password"), con);
		con.gridx = 1;
		con.gridy = 1;
		this.add(password, con);
		con.gridx = 0;
		con.gridy = 2;
		this.add(login, con);
		con.gridx = 1;
		con.gridy = 2;
		this.add(create, con);
		
		create.addActionListener(e -> {
			System.out.println(username.getText());
			System.out.println(password.getPassword());
			resetFields();
			viewChanger.redirect("CreateCustomer");
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

}
