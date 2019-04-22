package examples;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import views.RedirectListener;

//Add Components imports
//import javax.swing.JButton;

/**
 * ViewTemplate The main template to be used when creating a new view for the
 * application
 * 
 * @author Group B
 *
 */
public class ViewTemplate extends JPanel {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;

	// Add component variables
	// JButton create = new JButton("Create Account");

	public ViewTemplate(RedirectListener viewChanger) {
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

//		Add Controllers to components
//		create.addActionListener(e -> {
//			System.out.println(username.getText());
//			System.out.println(password.getPassword());
//			viewChanger.redirect("CreateCustomer");
//		});

	}
}
