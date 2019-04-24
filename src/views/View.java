/**
 * 
 */
package views;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Group B
 *
 */
public abstract class View extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 466771445561215780L;
	private Image img;
	protected static final Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
	protected static final Font jumboFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);

	/**
	 * 
	 */
	public View() {
		super();
		img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/background.jpg"));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			g.drawImage(img, 0, 0, this);
		} else {
			System.out.println("image was null");
		}
	}

	protected void resetFields() {
		Component[] components = this.getComponents();
		for (Component c : components) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
	}

}
