package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import controlers.LoginListener;
import controlers.UserEvent;
import models.Room;
import models.RoomManager;
import models.TransactionManager;
import models.User;

/**
 * 
 * @author Group B
 *
 */
public class VacantView extends JPanel implements LoginListener {

	private static final long serialVersionUID = 1L;
	RedirectListener viewChanger;
	User activeUser;

	JList<Room> vacantroom = new JList<Room>();
	JButton empmainview = new JButton("Employment main veiw");
	JButton logout = new JButton("Logout");
	private RoomManager roomManager;
	private TransactionManager transactionManager;

	public VacantView(RedirectListener viewChanger) {
		super();
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();

		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		this.add(vacantroom, con);
		con.gridwidth = 1;
		con.gridx = 0;
		con.gridy = 1;
		this.add(empmainview, con);
		con.gridx = 1;
		con.gridy = 1;
		this.add(logout, con);

		empmainview.addActionListener(e -> {
			viewChanger.redirect("EmpMainView");
		});

		logout.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});

	}

	@Override
	public void loginOccurred(UserEvent e) {
		activeUser = e.getActiveUser();
		DefaultListModel<Room> listModel = new DefaultListModel<Room>();

		for (Room room : roomManager.getRooms()) {
			if (transactionManager.isAvailable(room, LocalDate.now(), LocalDate.now().plusDays(1))) {
				listModel.addElement(room);
			}
		}
		vacantroom.setModel(listModel);
	}

	@Override
	public void logoutOccurred() {
		activeUser = null;
	}

	public void addRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	public void addTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
