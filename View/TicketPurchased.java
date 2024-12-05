package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.UserController;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TicketPurchased extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID; // Add a variable to store the customer ID 
	private static double cost;
	private JTextArea txtTicketPurchased;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketPurchased frame = new TicketPurchased(custID, cost);
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
	public TicketPurchased(final int custID, final double cost) {
		TicketPurchased.custID = custID; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTicketPurchased = new JLabel("TICKET PURCHASED");
		lblTicketPurchased.setForeground(new Color(0, 0, 64));
		lblTicketPurchased.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblTicketPurchased.setBounds(10, 10, 343, 90);
		contentPane.add(lblTicketPurchased);
		
		JTextArea txtTicketPurchased = new JTextArea();
		String ticket = null;
		UserController userController = new UserController();
		try {
			int bookid = userController.getBookingID();
			String catName = userController.getCatName(bookid);
			if(catName.equals("Waterpark -Surfer")|| catName.equals("Waterpark -Rider"))
			{
				ticket = userController.viewTicketWaterpark(bookid, cost);
			}
			else
			{
				ticket = userController.viewTicketAmusementPark(bookid, cost);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTicketPurchased.setText(ticket);
		txtTicketPurchased.setFont(new Font("Arial Black", Font.PLAIN, 12));
		txtTicketPurchased.setBackground(new Color(128, 255, 255));
		txtTicketPurchased.setBounds(10, 77, 343, 289);
		contentPane.add(txtTicketPurchased);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu frame = new MainMenu(custID, cost);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(130, 376, 85, 21);
		contentPane.add(btnBack);
		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1.png"));
		lblBackGround.setBounds(-301, -154, 1197, 734);
		contentPane.add(lblBackGround);
		

		

	}
}
