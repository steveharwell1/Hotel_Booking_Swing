package application;


import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import models.UserManager;
import views.*;

public class App implements RedirectListener{

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Application");
		frame.getContentPane().setLayout(new CardLayout());
		UserManager userManager = new UserManager("users.csv");
		
		LoginView loginView = new LoginView(this);
		loginView.setUserManager(userManager);
		userManager.addLoginListener(loginView);
		
		frame.add(loginView, "Login");
		frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void redirect(String viewName) {
		CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
		cl.show(frame.getContentPane(), viewName);
	}

}
