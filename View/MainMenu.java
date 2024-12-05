package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int CustID;
	private static double cost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				String username = null;
				try {
					MainMenu frame = new MainMenu(CustID,cost);
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
	public MainMenu(final int CustID, final double cost) {
		this.CustID = CustID;
		this.cost = cost;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMainMenu = new JLabel("MAIN MENU");
		lblMainMenu.setForeground(new Color(0, 0, 64));
		lblMainMenu.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblMainMenu.setBounds(243, 0, 221, 78);
		contentPane.add(lblMainMenu);
		
		JButton btnViewProfile = new JButton("Profile");
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile frame = new Profile(CustID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewProfile.setBackground(new Color(255, 255, 0));
		btnViewProfile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewProfile.setBounds(311, 73, 85, 21);
		contentPane.add(btnViewProfile);
		
		JButton btnViewTicketCategory = new JButton("Ticket Category");
		btnViewTicketCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketCategory frame = new TicketCategory(CustID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewTicketCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewTicketCategory.setBackground(new Color(255, 255, 0));
		btnViewTicketCategory.setBounds(237, 115, 232, 37);
		contentPane.add(btnViewTicketCategory);
		
		JButton btnPurchaseTicket = new JButton("Purchase Ticket");
		btnPurchaseTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking frame = new Booking(CustID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnPurchaseTicket.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPurchaseTicket.setBackground(new Color(255, 255, 0));
		btnPurchaseTicket.setBounds(237, 162, 232, 37);
		contentPane.add(btnPurchaseTicket);
		
		JButton btnViewTicketsPurchased = new JButton("View Ticket Purchased");
		btnViewTicketsPurchased.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketPurchased frame = new TicketPurchased(CustID, cost);
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewTicketsPurchased.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewTicketsPurchased.setBackground(new Color(255, 255, 0));
		btnViewTicketsPurchased.setBounds(237, 255, 232, 37);
		contentPane.add(btnViewTicketsPurchased);
		
		JButton btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelBooking frame = new CancelBooking(CustID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancelBooking.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancelBooking.setBackground(Color.YELLOW);
		btnCancelBooking.setBounds(237, 303, 232, 37);
		contentPane.add(btnCancelBooking);
		
		JButton btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment frame = new Payment(CustID, cost);
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnPayment.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPayment.setBackground(new Color(255, 255, 0));
		btnPayment.setBounds(237, 209, 232, 37);
		contentPane.add(btnPayment);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBackground(new Color(255, 255, 0));
		btnExit.setBounds(311, 363, 85, 21);
		contentPane.add(btnExit);
	
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1-1.png"));
		lblBackGround.setBounds(-280, -199, 1197, 734);
		contentPane.add(lblBackGround);
		


	}
}
