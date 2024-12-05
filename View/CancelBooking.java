package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.UserController;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CancelBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID; // Add a variable to store the customer ID 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelBooking frame = new CancelBooking(custID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CancelBooking(final int custID) {
		CancelBooking.custID = custID; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCancelBooking = new JLabel("CANCEL BOOKING");
		lblCancelBooking.setForeground(new Color(0, 0, 64));
		lblCancelBooking.setFont(new Font("Tw Cen MT", Font.BOLD, 44));
		lblCancelBooking.setBounds(170, 0, 366, 78);
		contentPane.add(lblCancelBooking);
		
		
		JLabel lblWhich = new JLabel("Please Choose : ");
		lblWhich.setFont(new Font("Calibri", Font.BOLD, 16));
		lblWhich.setBackground(new Color(0, 0, 64));
		lblWhich.setBounds(258, 66, 190, 42);
		contentPane.add(lblWhich);
		
		JButton btnCurrentBooking = new JButton("Current Booking");
		btnCurrentBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserController userController = new UserController();
				int bookid = 0;
				double cost = 0;
				try {
					bookid = userController.getBookingID();
					userController.updateDeletedTicket(bookid);
					userController.deletePayment(bookid);
					userController.deleteBookCat(bookid);
					userController.deleteBooking(bookid);
					JOptionPane.showMessageDialog(null,"Your booking has been cancelled!");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainMenu frame = new MainMenu(custID,cost);
				frame.setVisible(true);
				dispose();
			}
		});
		btnCurrentBooking.setBackground(new Color(0, 255, 0));
		btnCurrentBooking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCurrentBooking.setBounds(275, 118, 157, 42);
		contentPane.add(btnCurrentBooking);
		
		JButton btnPastBooking = new JButton("Past Booking");
		btnPastBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PastBooking frame = new PastBooking(custID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnPastBooking.setBackground(new Color(255, 128, 192));
		btnPastBooking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPastBooking.setBounds(275, 180, 157, 42);
		contentPane.add(btnPastBooking);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cost = 0;
				MainMenu frame = new MainMenu(custID, cost);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(313, 247, 85, 21);
		contentPane.add(btnBack);
		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\MAINMENU-1.png"));
		lblBackGround.setBounds(-300, -162, 1197, 734);
		contentPane.add(lblBackGround);

		

		
	}

}
