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



public class ReportView extends JPanel implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	private User activeUser;
	
	//creates a JButton with the specific text as its label

	JButton create1 = new JButton("Vacant Rooms");
	JButton create2 = new JButton("Revenue Report View");
	JButton create3 = new JButton("Logout");
	JButton create4 = new JButton("Back");
	public ReportView(RedirectListener viewChanger) {
		super();
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}
	
	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();
		
//		Add components to layout and save inputs to fields
		
		con.gridx = 1;
		con.gridy = 0;
		this.add(create1, con);
		con.gridx = 2;
		con.gridy = 0;
		this.add(create2, con);
		con.gridx = 3;
		con.gridy = 0;
		this.add(create3, con);
		con.gridx = 4;
		con.gridy = 0;
		this.add(create4, con);
		
		//add controllers to components
		create1.addActionListener(e-> {
			
			viewChanger.redirect("Vacant Rooms");
		});
		
		create2.addActionListener(e-> {
			
			viewChanger.redirect("Revenue Report View");
		});	
		
		create3.addActionListener(e-> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});
		
		create4.addActionListener(e-> {
			
			viewChanger.redirect("EmpMainView");
		});
		
		
		
		}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();
		if(!activeUser.hasPermission("manager")) {
			create2.setEnabled(false);
		} else if (activeUser.hasPermission("manager")) {
			create2.setEnabled(true);
		}
		
	}

	@Override
	public void logoutOccurred() {
		this.activeUser = null;
	}
		
		
		
		
		
		
		

	}

