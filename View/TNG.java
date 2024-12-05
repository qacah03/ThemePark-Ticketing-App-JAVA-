package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.Date;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class TNG extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID;
	private JTextField textPhoneNo;
	//private JTextField textPassword;
	JPasswordField textPassword = new JPasswordField();
	private static double cost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TNG frame = new TNG(custID,cost);
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
	public TNG(final int custID, final double cost) {
		TNG.custID = custID; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTNG = new JLabel("TOUCH N GO");
		lblTNG.setForeground(new Color(0, 0, 64));
		lblTNG.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		lblTNG.setBounds(10, 21, 361, 106);
		contentPane.add(lblTNG);
		
		JLabel lblPhoneNo = new JLabel("Phone Number");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhoneNo.setBounds(20, 135, 130, 50);
		contentPane.add(lblPhoneNo);
		
		textPhoneNo = new JTextField();
		textPhoneNo.setColumns(10);
		textPhoneNo.setBounds(20, 169, 268, 21);
		contentPane.add(textPhoneNo);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(20, 210, 142, 50);
		contentPane.add(lblPassword);
		
		//textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(20, 243, 268, 21);
		contentPane.add(textPassword);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cost = 0;
				Payment frame = new Payment(custID, cost);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(20, 319, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPhoneNo.setText(null);
				textPassword.setText(null);
			}
		});
		btnReset.setBounds(112, 319, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bookid = 0;
				String method = "TNG EWallet";				
				UserController userController = new UserController();
				// Get the current date
		        LocalDate currentDate = LocalDate.now();

		        // Extract days, months, and years
		        int day = currentDate.getDayOfMonth();
		        int month = currentDate.getMonthValue();
		        int year = currentDate.getYear();
		        Date date = new Date(day,month,year);
				try {
					bookid = userController.getBookingID();
					userController.insertPayment(bookid, method, cost, date);
					JOptionPane.showMessageDialog(null,"Your payment has been completed!");
					userController.ticket();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				MainMenu frame = new MainMenu(custID,cost);
				frame.setVisible(true);
				dispose();
			}
		});
		btnConfirm.setBounds(203, 319, 85, 21);
		contentPane.add(btnConfirm);
		
		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\TNG-1.png"));
		lblBackGround.setBounds(-272, -149, 1197, 734);
		contentPane.add(lblBackGround);
		

	}

}
