/**
 * 
 */
package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author steve
 *
 */
public class CreateCustomerView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	JButton back;
	JLabel backNotice;

	public CreateCustomerView(RedirectListener viewChanger) {
		this.viewChanger = viewChanger;
		initialize();
	}
	
	private void initialize() {
		back = new JButton("Back");
		backNotice = new JLabel("Please Click Back");
		
		back.addActionListener(e -> {
			viewChanger.redirect("Login");
			});
		
		this.add(backNotice);
		this.add(back);

		
	}

}