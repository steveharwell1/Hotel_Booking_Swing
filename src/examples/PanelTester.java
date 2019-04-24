package examples;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @deprecated replaced by AppTemplate
 * @author Group B
 *
 */
@Deprecated
public class PanelTester {

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
					PanelTester window = new PanelTester();
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
	public PanelTester() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Application");
		frame.getContentPane().setLayout(new CardLayout());

		// frame.add(new CheckinView(this), "CheckinView");
		frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
