/**
 * 
 */
package examples;

import java.awt.CardLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @deprecated replaced by ViewTemplate
 * @author Group B
 *
 */
@Deprecated
public class ExamplePanel2 extends JPanel {
	PanelTester window;
	JButton back;
	JLabel backNotice;

	public ExamplePanel2(PanelTester window) {
		this.window = window;
		initialize();
	}

	public ExamplePanel2(PanelTester window, LayoutManager layout) {
		super(layout);
		this.window = window;
		initialize();
	}

	private void initialize() {
		this.setBounds(0, 0, 500, 500);
		back = new JButton("Back");
		backNotice = new JLabel("Please Click Back");

		back.addActionListener(e -> {
			CardLayout cl = (CardLayout) window.frame.getContentPane().getLayout();
			cl.show(window.frame.getContentPane(), "Login");
		});

		this.add(backNotice);
		this.add(back);

	}

}