import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginPanel() {
		initialize();
	}

	public LoginPanel(LayoutManager layout) {
		super(layout);
		initialize();
	}

	public LoginPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initialize();
	}

	public LoginPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initialize();
	}
	
	private void initialize() {
		this.setBounds(0,  0, 500, 500);
		this.setBackground(Color.BLUE);
		this.add(new JLabel("Text1"));
		//this.setVisible(true);
	}

}
