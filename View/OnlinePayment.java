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

public class OnlinePayment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID;
	private JTextField textUsername;
	JPasswordField textPassword = new JPasswordField();
	//private JTextField textPassword;
	private static double cost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlinePayment frame = new OnlinePayment(custID, cost);
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
	public OnlinePayment(final int custID, final double cost) {
		OnlinePayment.custID = custID; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOnlineBanking = new JLabel("ONLINE BANKING");
		lblOnlineBanking.setForeground(new Color(0, 0, 64));
		lblOnlineBanking.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		lblOnlineBanking.setBounds(10, 10, 519, 106);
		contentPane.add(lblOnlineBanking);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(20, 124, 130, 50);
		contentPane.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(20, 158, 268, 21);
		contentPane.add(textUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(20, 199, 142, 50);
		contentPane.add(lblPassword);
		
		//textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(20, 232, 268, 21);
		contentPane.add(textPassword);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment frame = new Payment(custID,cost);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(20, 308, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText(null);
				textPassword.setText(null);
			}
		});
		btnReset.setBounds(112, 308, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bookid = 0;
				String method = "Online Payment";				
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
		btnConfirm.setBounds(203, 308, 85, 21);
		contentPane.add(btnConfirm);
		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\ONLINE-BACKING-1.png"));
		lblBackGround.setBounds(-272, -149, 1197, 734);
		contentPane.add(lblBackGround);
		
		
	}

}
