package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import application.CCVerifier;
import controlers.LoginListener;
import controlers.UserEvent;
import models.Room;
import models.RoomManager;
import models.SettingsManager;
import models.Transaction;
import models.TransactionManager;
import models.User;
import utilities.Optional;

public class ReservationView extends JPanel implements LoginListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RedirectListener viewChanger;
	private User activeUser;
	private RoomManager roomManager;
	private SettingsManager settingsManager;
	private TransactionManager transactionManager;
	private Transaction workingTransaction;
	private int resdays;
	private int resweekends;
	
	/////////////////first row/////////////////
	
	
	private JLabel errorLabel = new JLabel("");
	
	/////////////////next row/////////////////
	
	private JLabel greeting = new JLabel("Please Choose Reservation Date then Room");

	
	/////////////////next row/////////////////
	

	private static final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	private JComboBox<String> startMonth;
	private JComboBox<Integer> startDay;
	private JComboBox<Integer> startYear;
	private JComboBox<String> endMonth;
	private JComboBox<Integer> endDay;
	private JComboBox<Integer> endYear;
	
	/////////////////next row/////////////////
	
	private JList<Room> roomList = new JList<Room>();
		private JButton backButton = new JButton("Back");
	private JButton logoutButton = new JButton("Logout");
	private JButton search = new JButton("Search");
	private JButton complete = new JButton("Ready to Pay");
	
	/////////////////next row/////////////////
	
	private JLabel ccMessage = new JLabel("");
	private JTextField creditCard = new JTextField(20);
	private JButton submit = new JButton("Submit Payment");



	public ReservationView(RedirectListener viewChanger, TransactionManager transactionManager) {
		super();
		this.transactionManager = transactionManager;
		this.viewChanger = viewChanger;
		setLayout(new GridBagLayout());
		initialize();
	}

	private void initialize() {
		GridBagConstraints con = new GridBagConstraints();
		startMonth = new JComboBox<>(months);
		Integer[] days = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
				26, 27, 28, 29, 30, 31 };
		startDay = new JComboBox<>(days);
		endDay = new JComboBox<>(days);
		endMonth = new JComboBox<String>(months);

		Integer[] year = { LocalDate.now().getYear(), LocalDate.now().getYear() + 1 };
		startYear = new JComboBox<>(year);
		endYear = new JComboBox<>(year);
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		errorLabel.setForeground(Color.RED);

		////////////// First Row /////////////////
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 7;
		con.anchor = GridBagConstraints.LINE_START;
		this.add(greeting, con);

		////////////// Next Row /////////////////
		con.gridy++;

		con.gridx = 0;
		this.add(errorLabel, con);

		////////////// Next Row /////////////////
		con.gridy++;
		con.gridwidth = 3;

		con.gridx = 0;
		this.add(new JLabel("Check in Date"), con);

		con.gridx = 4;
		this.add(new JLabel("Check out Date"), con);

		////////////// Next Row /////////////////
		con.gridy++;
		con.gridwidth = 1;
		con.fill = GridBagConstraints.HORIZONTAL;

		con.gridx = 0;
		this.add(startMonth, con);

		con.gridx = 1;
		this.add(startDay, con);

		con.gridx = 2;
		this.add(startYear, con);

		con.gridx = 4;
		this.add(endMonth, con);

		con.gridx = 5;
		this.add(endDay, con);

		con.gridx = 6;
		this.add(endYear, con);

		////////////// Next Row /////////////////
		con.gridy++;
		con.gridx = 0;
		con.gridwidth = 7;
		this.add(roomList, con);
		con.gridwidth = 1;
		////////////// Next Row /////////////////
		con.gridy++;


		con.gridx = 0;
		this.add(search, con);

		con.gridx = 1;
		this.add(logoutButton,con);

		con.gridx = 2;
		this.add(backButton, con);
		
		con.gridx = 3;
		this.add(complete, con);
		
		////////////// Next Row /////////////////
		con.gridy++;
		
		con.gridx = 0;
		con.gridwidth = GridBagConstraints.REMAINDER;
		this.add(ccMessage, con);
		ccMessage.setVisible(false);
		
		////////////// Next Row /////////////////
		con.gridy++;
		
		
		con.gridx = 0;
		con.gridwidth = 2;
		this.add(creditCard, con);
		creditCard.setVisible(false);
		
		con.gridx = 2;
		this.add(submit, con);
		submit.setVisible(false);

		logoutButton.addActionListener(e -> {
			activeUser.logout();
			viewChanger.redirect("Login");
		});
		
		backButton.addActionListener(e -> {
			viewChanger.redirect("CustMainView");
		});

		
		//handle a search by date
		search.addActionListener(e -> {
			errorLabel.setForeground(Color.RED);
			errorLabel.setText("");
			ccMessage.setVisible(false);
			creditCard.setVisible(false);
			submit.setVisible(false);
			try {
				LocalDate begin = LocalDate.of((int) startYear.getSelectedItem(), startMonth.getSelectedIndex() + 1,
						(int) startDay.getSelectedItem());
				LocalDate end = LocalDate.of((int) endYear.getSelectedItem(), endMonth.getSelectedIndex() + 1,
						(int) endDay.getSelectedItem());
				if (!begin.isBefore(end)) {
					errorLabel.setText("The begin date must be at least one day before the end date");
					return;
				} else if (begin.isBefore(LocalDate.now())) {
					errorLabel.setText("The reservation must be in the future");
					return;
				}
				workingTransaction = new Transaction(transactionManager, activeUser);
				workingTransaction.setStart(begin.format(DateTimeFormatter.ISO_LOCAL_DATE));
				workingTransaction.setEnd(end.format(DateTimeFormatter.ISO_LOCAL_DATE));

				resdays = 0;
				resweekends = 0;

				
				DefaultListModel<Room> listModel = new DefaultListModel<Room>();
				
				for (Room room : roomManager.getRooms()) {
					if (transactionManager.isAvailable(room, begin, end)) {
						System.out.printf("Room %s is available%n", room.asMap().get("roomNumber"));
						listModel.addElement(room);
					} else {
						System.out.printf("Room %s is available%n", room.asMap().get("roomNumber"));
					}
				}
				
				roomList.setModel(listModel);
				
				while (begin.isBefore(end)) {
					resdays++;
					if (begin.get(ChronoField.DAY_OF_WEEK) == 5 || begin.get(ChronoField.DAY_OF_WEEK) == 6) {
						resweekends++;
					}
					begin = begin.plusDays(1);
				}
				System.out.printf("Your stay will be %d day(s) long, and %d of those days will be on the weekend.%n",
						resdays, resweekends);
			} catch (DateTimeException exception) {
				errorLabel.setText(exception.getMessage());
				throw exception;
			}
		});
		
		//Handle a selected room
		complete.addActionListener(e -> {
			Room selectedRoom = roomList.getSelectedValue();
			if(selectedRoom == null) {
				errorLabel.setText("You must select a room");
				return;
			}
			double price = selectedRoom.getPrice();
			double total = settingsManager.getSettings().getWeekendRate() * resweekends * price;
			total += price * resdays;
			workingTransaction.setRoom(selectedRoom.getPrimaryKey());
			
			workingTransaction.setPrice(total);
			
			ccMessage.setForeground(Color.BLUE);
			ccMessage.setText(String.format("Please enter you credit card number to pay %.2f", total));
			ccMessage.setVisible(true);
			creditCard.setVisible(true);
			submit.setVisible(true);
		});
		
		submit.addActionListener(e -> {
			Optional<Long> result = CCVerifier.getInstance().verify(creditCard.getText());
			if(result.success()) {
				workingTransaction.setConfirmationCode(result.get().toString());
				workingTransaction.finalize();
				JOptionPane.showMessageDialog(null, "Your reservation was successful " + workingTransaction);
				resetFields();
			} else {
				errorLabel.setText(result.getErrMsg());
			}
		});

	}

	void resetFields() {
		Component[] components = this.getComponents();
		for (Component c : components) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
		
		ccMessage.setVisible(false);
		creditCard.setVisible(false);
		submit.setVisible(false);
		errorLabel.setText("");
		
		roomList.setModel(new DefaultListModel<Room>());

		this.workingTransaction = null;
	}

	@Override
	public void loginOccurred(UserEvent e) {
		this.activeUser = e.getActiveUser();
		// System.out.println("Login occurred for " + activeUser.getName());
	}

	@Override
	public void logoutOccurred() {
		resetFields();
		this.activeUser = null;
	}

	public void addRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;

	}

	public void addSettingManager(SettingsManager settingsManager) {
		this.settingsManager = settingsManager;
	}
}
