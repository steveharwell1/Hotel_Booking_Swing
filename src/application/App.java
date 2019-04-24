package application;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import models.RoomManager;
import models.SettingsManager;
import models.TransactionManager;
import models.UserManager;
import views.CheckinView;
import views.CustomerMainView;
import views.LoginView;
import views.PricingView;
import views.RedirectListener;
import views.ReportView;
import views.ReservationView;
import views.RevenueReportView;
import views.TmpEmpMainView;
import views.TransactionsView;
import views.UpdateAccountView;
import views.VacantView;

/**
 * Main entry point to program sets up the JFrame GUI on a worker thread Then
 * creates each JPanel subclass and connects resources required by each panel.
 * 
 * @author Group B
 *
 */
public class App implements RedirectListener {

	public JFrame frame;

	/**
	 * Launch the application.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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

		// initialize managers
		UserManager userManager = new UserManager("users.csv");
		RoomManager roomManager = new RoomManager();
		TransactionManager transactionManager = new TransactionManager("transactions.csv");
		SettingsManager settingsManager = new SettingsManager("setting.csv");

		// Initialize views
		LoginView loginView = new LoginView(this);
		loginView.setUserManager(userManager);
		userManager.addLoginListener(loginView);
		frame.add(loginView, "Login");

		UpdateAccountView updateAccountView = new UpdateAccountView(this);
		userManager.addLoginListener(updateAccountView);
		frame.add(updateAccountView, "UpdateAccountView");

		ReservationView reservationView = new ReservationView(this, transactionManager);
		reservationView.addRoomManager(roomManager);
		reservationView.addSettingManager(settingsManager);
		userManager.addLoginListener(reservationView);
		frame.add(reservationView, "ReservationView");

		// My edits
		CustomerMainView custMainView = new CustomerMainView(this);
		userManager.addLoginListener(custMainView);
		frame.add(custMainView, "CustMainView");

		CheckinView checkinView = new CheckinView(this);
		checkinView.setTransactionManager(transactionManager);
		userManager.addLoginListener(checkinView);
		frame.add(checkinView, "CheckinView");

		TmpEmpMainView empMainView = new TmpEmpMainView(this);
		userManager.addLoginListener(empMainView);
		frame.add(empMainView, "EmpMainView");

		ReportView reportView = new ReportView(this);
		userManager.addLoginListener(reportView);
		frame.add(reportView, "ReportsView");

		VacantView vacantView = new VacantView(this);
		userManager.addLoginListener(vacantView);
		vacantView.addTransactionManager(transactionManager);
		vacantView.addRoomManager(roomManager);
		frame.add(vacantView, "Vacant Rooms");

		TransactionsView transactionsView = new TransactionsView(this);
		userManager.addLoginListener(transactionsView);
		transactionManager.addTransactionListener(transactionsView);
		transactionsView.addTransactionManager(transactionManager);
		frame.add(transactionsView, "TransactionsView");

		RevenueReportView revReport = new RevenueReportView(this);
		userManager.addLoginListener(revReport);
		revReport.addTransactionManager(transactionManager);
		frame.add(revReport, "Revenue Report View");

		PricingView pricingView = new PricingView(this);
		userManager.addLoginListener(pricingView);
		pricingView.addSettingsManager(settingsManager);
		frame.add(pricingView, "PricingView");

		// Set window properties
		frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void redirect(String viewName) {
		CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
		cl.show(frame.getContentPane(), viewName);
		System.out.println("Screen has been redirected to " + viewName);
	}

}
