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

public class StaffMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int staffid;
	private static double cost;
	private static int custID; // Add a variable to store the customer ID 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				String username = null;
				try {
					StaffMenu frame = new StaffMenu(staffid,cost);
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
	public StaffMenu(final int staffid, final double cost) {
		this.staffid = staffid;
		this.cost = cost;
		System.out.println(staffid);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMainMenu = new JLabel("STAFF MENU");
		lblMainMenu.setForeground(new Color(0, 0, 64));
		lblMainMenu.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblMainMenu.setBounds(242, 23, 223, 78);
		contentPane.add(lblMainMenu);
		
		JButton btnViewProfile = new JButton("Profile");
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileStaff frame = new ProfileStaff(staffid);
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewProfile.setBackground(new Color(255, 255, 0));
		btnViewProfile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewProfile.setBounds(311, 111, 85, 21);
		contentPane.add(btnViewProfile);
		
		JButton btnUpdateTicketCat = new JButton("Update Ticket Category");
		btnUpdateTicketCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTicketCategory frame = new UpdateTicketCategory(staffid);
				frame.setVisible(true);
				dispose();
			}
		});
		btnUpdateTicketCat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdateTicketCat.setBackground(new Color(255, 255, 0));
		btnUpdateTicketCat.setBounds(242, 192, 232, 37);
		contentPane.add(btnUpdateTicketCat);
		
		JButton btnViewSalesReport = new JButton("View Sales Report");
		btnViewSalesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ni jtable
				//ReportSales window = new ReportSales();
				//window.frame.setVisible(true);
				
				//NI YANG TABLE BIASA
				SalesReport_2 frame = new SalesReport_2(custID);
				frame.setVisible(true);
				dispose();
			}
		});
		
		btnViewSalesReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewSalesReport.setBackground(new Color(255, 255, 0));
		btnViewSalesReport.setBounds(242, 239, 232, 37);
		contentPane.add(btnViewSalesReport);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBackground(new Color(255, 255, 0));
		btnExit.setBounds(311, 333, 85, 21);
		contentPane.add(btnExit);
	
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1-1.png"));
		lblBackGround.setBounds(-280, -199, 1197, 734);
		contentPane.add(lblBackGround);
		


	}
}
