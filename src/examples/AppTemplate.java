package examples;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import views.CheckinView;
import views.RedirectListener;

/**
 * AppTemplate is meant to be used for programmers to test out newly designed
 * JPanels before adding them to the main project.
 * 
 * @author Group B
 *
 */
public class AppTemplate implements RedirectListener {

	public JFrame frame;

	/**
	 * Start the GUI
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppTemplate window = new AppTemplate();
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
	public AppTemplate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Application");
		frame.getContentPane().setLayout(new CardLayout());

		// Add views to application
//		frame.add(new ViewTemplate(this), "TestView");

		frame.add(new CheckinView(this), "CheckinView");

		frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void redirect(String viewName) {
		CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
		cl.show(frame.getContentPane(), viewName);
	}

}
