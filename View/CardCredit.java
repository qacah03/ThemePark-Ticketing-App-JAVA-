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

public class CardCredit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID;
	private JTextField textNoCard;
	private JTextField textExpiredDate;
	//private JTextField textCCV;
	JPasswordField textCCV = new JPasswordField();
	private static double cost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardCredit frame = new CardCredit(custID,cost);
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
	public CardCredit(final int custID, final double cost) {
		CardCredit.custID = custID; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCardCredit = new JLabel("CARD CREDIT");
		lblCardCredit.setForeground(new Color(0, 0, 64));
		lblCardCredit.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		lblCardCredit.setBounds(10, 10, 361, 106);
		contentPane.add(lblCardCredit);
		
		JLabel lblNoCard = new JLabel("No. Card");
		lblNoCard.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoCard.setBounds(20, 124, 130, 50);
		contentPane.add(lblNoCard);
		
		textNoCard = new JTextField();
		textNoCard.setColumns(10);
		textNoCard.setBounds(20, 158, 268, 21);
		contentPane.add(textNoCard);
		
		JLabel lblExpiredDate = new JLabel("Expired Date");
		lblExpiredDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblExpiredDate.setBounds(20, 171, 142, 50);
		contentPane.add(lblExpiredDate);
		
		textExpiredDate = new JTextField();
		textExpiredDate.setColumns(10);
		textExpiredDate.setBounds(20, 204, 268, 21);
		contentPane.add(textExpiredDate);
		
		JLabel lblccv = new JLabel("CCV");
		lblccv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblccv.setBounds(20, 217, 130, 50);
		contentPane.add(lblccv);
		
		//textCCV = new JTextField();
		textCCV.setColumns(10);
		textCCV.setBounds(20, 254, 268, 21);
		contentPane.add(textCCV);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment frame = new Payment(custID, cost);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(20, 308, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNoCard.setText(null);
				textExpiredDate.setText(null);
				textCCV.setText(null);
			}
		});
		btnReset.setBounds(112, 308, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bookid = 0;
				String method = "Card Credit";				
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
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\CARD-CREDIT-1.png"));
		lblBackGround.setBounds(-269, -144, 1197, 734);
		contentPane.add(lblBackGround);
		
		
		
	}

}
