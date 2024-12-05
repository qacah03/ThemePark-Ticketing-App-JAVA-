package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.Customer;
import model.WaterPark;
import model.Date;
import model.Staff;
import model.ThemePark;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Waterpark extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int custID;
	private JTextField textRider;
	private JTextField textSurfer;
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
					Waterpark frame = new Waterpark(custID,cust,date,member);
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
	public Waterpark(final int custID,final Customer cust,final Date date,final boolean member) {
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
		
		JLabel lblEnter = new JLabel("Enter the number of tickets you want to buy");
		lblEnter.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblEnter.setBackground(Color.RED);
		lblEnter.setBounds(25, 92, 390, 42);
		contentPane.add(lblEnter);
		
		JLabel lblRider = new JLabel("Number of Rider: ");
		lblRider.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblRider.setBackground(new Color(0, 0, 64));
		lblRider.setBounds(25, 157, 220, 42);
		contentPane.add(lblRider);
		
		textRider = new JTextField();
		textRider.setColumns(10);
		textRider.setBounds(237, 161, 47, 34);
		contentPane.add(textRider);
		
		JLabel lblSurfer = new JLabel("Number of Surfer: ");
		lblSurfer.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblSurfer.setBackground(new Color(0, 0, 64));
		lblSurfer.setBounds(25, 224, 220, 42);
		contentPane.add(lblSurfer);
		
		textSurfer = new JTextField();
		textSurfer.setColumns(10);
		textSurfer.setBounds(237, 228, 47, 34);
		contentPane.add(textSurfer);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booking frame = new Booking(custID);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(25, 301, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textRider.setText(null);
				textSurfer.setText(null);
			}
		});
		btnReset.setBounds(117, 301, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int CatAvailability = 0;
				int CatAvailability1 = 0;
				UserController userController = new UserController();
				double priceSurfer = 0;
				double priceRider = 0;
				double totalPrice;
				String Rider = textRider.getText();
				int rider = Integer.parseInt(Rider);
				try {
					CatAvailability = userController.checkAvailability(3002);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if(CatAvailability<rider)
                {
                	JOptionPane.showMessageDialog(null,"We're sorry, the ticket's category are currently not available!");
                	rider = 0;
                	textRider.setText("0");
                }
                else
                {
                	try {
						userController.updateTicketAvailability(3002, rider);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
				String Surfer = textSurfer.getText();
				int surfer = Integer.parseInt(Surfer);
				try {
                	CatAvailability1 = userController.checkAvailability(3003);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                if(CatAvailability1<surfer)
                {
                	JOptionPane.showMessageDialog(null,"We're sorry, the ticket's category are currently not available!");
                	surfer = 0;
                	textSurfer.setText("0");
                }
                else
                {
                	try {
						userController.updateTicketAvailability(3003, surfer);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
				int total = surfer+rider;
				String name = "Waterpark";
				double cost = 0;
				Staff staff = new Staff();
				WaterPark waterpark = new WaterPark(name, "WP1", member, date, cust, staff, rider, surfer);
				try {
					userController.insertBooking(date, total, cust);
					int bookid = userController.getBookingID();
					if(surfer!=0)
					{
						userController.insertBookCat(bookid, 3003, surfer);
						priceSurfer = userController.getPrice(3003);
					}
					if(rider!=0)
					{
						userController.insertBookCat(bookid, 3002, rider);
						priceRider = userController.getPrice(3002);
					}
					totalPrice = (priceSurfer*surfer) + (priceRider*rider);
					cost = waterpark.calcFee(totalPrice);
					JOptionPane.showMessageDialog(null,"Your booking is successful");
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				MainMenu frame = new MainMenu(custID,cost);
				frame.setVisible(true);
			}
		});
		btnConfirm.setBounds(208, 301, 85, 21);
		contentPane.add(btnConfirm);

		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\WATERPARK-1.png"));
		lblBackGround.setBounds(-329, -193, 1347, 800);
		contentPane.add(lblBackGround);
		
		
	}

}
