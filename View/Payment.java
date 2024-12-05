package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.Customer;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Payment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID;
	private static double cost;
	private JTextField textPaymentMethod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment(custID, cost);
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
	public Payment(final int custID, final double cost) {
		Payment.custID = custID; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblPastBooking = new JLabel("PAYMENT TICKET");
		lblPastBooking.setForeground(new Color(0, 0, 64));
		lblPastBooking.setFont(new Font("Tw Cen MT", Font.BOLD, 53));
		lblPastBooking.setBounds(10, 10, 406, 90);
		contentPane.add(lblPastBooking);
		
		JTextArea txtProfile = new JTextArea();
		UserController userController = new UserController();
		Customer cust = new Customer();
		String info = null;
		int bookid = 0;
		try {
			cust = userController.getCustomer(custID);
			bookid = userController.getBookingID();
			info = userController.viewPayment(cust, bookid, cost);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtProfile.setText(info);
		txtProfile.setFont(new Font("Arial Black", Font.PLAIN, 12));
		txtProfile.setBackground(new Color(128, 255, 255));
		txtProfile.setBounds(10, 82, 343, 105);
		contentPane.add(txtProfile);

		JLabel lblChoosePayment = new JLabel("Choose Payment Method: ");
		lblChoosePayment.setFont(new Font("Calibri", Font.BOLD, 16));
		lblChoosePayment.setBackground(new Color(0, 0, 64));
		lblChoosePayment.setBounds(10, 188, 193, 32);
		contentPane.add(lblChoosePayment);
		
		JLabel lblA_1_1 = new JLabel("(c) Touch N GO");
		lblA_1_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblA_1_1.setBackground(new Color(0, 0, 64));
		lblA_1_1.setBounds(10, 262, 328, 39);
		contentPane.add(lblA_1_1);
		
		JLabel lblA_1 = new JLabel("(b) Online Payment");
		lblA_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblA_1.setBackground(new Color(0, 0, 64));
		lblA_1.setBounds(10, 238, 328, 39);
		contentPane.add(lblA_1);
		
		JLabel lblA = new JLabel("(a) Card Credit");
		lblA.setFont(new Font("Calibri", Font.BOLD, 16));
		lblA.setBackground(new Color(0, 0, 64));
		lblA.setBounds(10, 213, 328, 39);
		contentPane.add(lblA);

		
		textPaymentMethod = new JTextField();
		textPaymentMethod.setColumns(10);
		textPaymentMethod.setBounds(10, 299, 304, 24);
		contentPane.add(textPaymentMethod);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cost = 0;
				MainMenu frame = new MainMenu(custID, cost);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(29, 354, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPaymentMethod.setText(null);
			}
		});
		btnReset.setBounds(121, 354, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = "a";
				String b = "b";
				String c = "c";
				if(textPaymentMethod.getText().equals(a))
				{
					CardCredit frame = new CardCredit(custID,cost);
					frame.setVisible(true);
					dispose();
					}
				else if(textPaymentMethod.getText().equals(b))
				{					
						OnlinePayment frame = new OnlinePayment(custID,cost);
						frame.setVisible(true);
						dispose();
				}
				else if(textPaymentMethod.getText().equals(c))
				{
						TNG frame = new TNG(custID,cost);
						frame.setVisible(true);
						dispose();		
				}
				else {
					//JOptionPane.showMessageDialog(null,"Invalid input bodo");
				}

			}
			
		});
		btnConfirm.setBounds(212, 354, 85, 21);
		contentPane.add(btnConfirm);
		


		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\UPDATE-PROFILE.png"));
		lblBackGround.setBounds(-320, -149, 1197, 734);
		contentPane.add(lblBackGround);
		


		
	}
	
}
