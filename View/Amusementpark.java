package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.AmusementPark;
import model.Customer;
import model.Date;
import model.Staff;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Amusementpark extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID;
	private JTextField textAdult;
	private JLabel lblChild;
	private JTextField textChild;
	private JLabel lblEnter;
	private static Customer cust;
	private static Date date;
	private static boolean member;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Amusementpark frame = new Amusementpark(custID,cust,date,member);
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
	public Amusementpark(final int custID,final Customer cust, final Date date, final boolean member) {
			this.custID = custID;
			this.cust = cust;
			this.date = date;
			this.member = member;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 721, 459);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEnter = new JLabel("Enter the number of tickets you want to buy");
		lblEnter.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblEnter.setBackground(new Color(255, 0, 0));
		lblEnter.setBounds(32, 101, 390, 42);
		contentPane.add(lblEnter);
		
		
		textAdult = new JTextField();
		textAdult.setColumns(10);
		textAdult.setBounds(244, 170, 47, 34);
		contentPane.add(textAdult);
		
		JLabel lblAdult = new JLabel("Number of Adult: ");
		lblAdult.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblAdult.setBackground(new Color(0, 0, 64));
		lblAdult.setBounds(32, 166, 220, 42);
		contentPane.add(lblAdult);
		
		lblChild = new JLabel("Number of Child: ");
		lblChild.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblChild.setBackground(new Color(0, 0, 64));
		lblChild.setBounds(32, 233, 220, 42);
		contentPane.add(lblChild);
		
		textChild = new JTextField();
		textChild.setColumns(10);
		textChild.setBounds(244, 237, 47, 34);
		contentPane.add(textChild);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking frame = new Booking(custID);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(32, 310, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAdult.setText(null);
				textChild.setText(null);

			}
		});
		btnReset.setBounds(124, 310, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int CatAvailability = 0;
				int CatAvailability1 = 0;
				 UserController userController = new UserController();
	                String name = "Amusement Park";
	                String Adult = textAdult.getText();
	                int adult = Integer.parseInt(Adult);
	                try {
						CatAvailability = userController.checkAvailability(3004);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                if(CatAvailability<adult)
	                {
	                	JOptionPane.showMessageDialog(null,"We're sorry, the ticket's category are currently not available!");
	                	adult = 0;
	                	textAdult.setText("0");
	                }
	                else
	                {
	                	try {
							userController.updateTicketAvailability(3004, adult);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	                String Child = textChild.getText();
	                int child = Integer.parseInt(Child);
	                try {
	                	CatAvailability1 = userController.checkAvailability(3001);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                
	                if(CatAvailability1<child)
	                {
	                	JOptionPane.showMessageDialog(null,"We're sorry, the ticket's category are currently not available!");
	                	child = 0;
	                	textChild.setText("0");
	                }
	                else
	                {
	                	try {
							userController.updateTicketAvailability(3001, child);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	                int total = child + adult;
	                double priceChild = 0;
	                double priceAdult = 0;
	                double cost = 0;
	                double totalPrice = 0;
	                Staff staff = new Staff();
	                AmusementPark amusementPark = new AmusementPark(name, "AP1", member, date, cust, staff, adult, child);

	                try {
	                    userController.insertBooking(date, total, cust);
	                    int bookid = userController.getBookingID();
	                    if (child != 0) {
	                        userController.insertBookCat(bookid, 3001, child);
	                        priceChild = userController.getPrice(3001);
	                    }
	                    if (adult != 0) {
	                        userController.insertBookCat(bookid, 3004, adult);
	                        priceAdult = userController.getPrice(3004);
	                    }
	                    totalPrice = (priceChild*child) + (priceAdult*adult);
	                    cost = amusementPark.calcFee(totalPrice);
	                    JOptionPane.showMessageDialog(null,"Your booking is successful");
	                } catch (ClassNotFoundException | SQLException e1) {
	                    e1.printStackTrace();
	                }

	                
	                MainMenu frame = new MainMenu(custID, cost);
	                frame.setVisible(true);
			}
		});
		btnConfirm.setBounds(215, 310, 85, 21);
		contentPane.add(btnConfirm);
		

		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\AMUSEMENT-PARK-1.png"));
		lblBackGround.setBounds(-329, -193, 1347, 800);
		contentPane.add(lblBackGround);


		
	}
}
