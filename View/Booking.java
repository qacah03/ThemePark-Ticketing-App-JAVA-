package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.UserController;
import model.Customer;
import model.Date;

public class Booking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDay;
	private static int custID;
	private JTextField textMonth;
	private JTextField textYear;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking frame = new Booking(custID);
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
	public Booking(final int custID) {
		this.custID = custID;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 721, 459);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPayment = new JLabel("BOOKING SYSTEM");
        lblPayment.setForeground(new Color(0, 0, 64));
        lblPayment.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
        lblPayment.setBounds(194, 0, 319, 78);
        contentPane.add(lblPayment);
        
        JLabel lblNewLabel = new JLabel("Enter Date");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(314, 113, 78, 13);
		contentPane.add(lblNewLabel);
		
        dateChooser = new JDateChooser(); // Initialize JDateChooser
        dateChooser.setBounds(231, 136, 240, 24); // Set position and size
        contentPane.add(dateChooser);

        JLabel lblNewLabel_1 = new JLabel("Which themepark do you want to go?");
        lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
        lblNewLabel_1.setBackground(new Color(0, 0, 64));
        lblNewLabel_1.setBounds(219, 269, 269, 42);
        contentPane.add(lblNewLabel_1);

        final JCheckBox chckbxMembership = new JCheckBox("Do you have membership?");
        chckbxMembership.setBackground(new Color(255, 128, 128));
        chckbxMembership.setFont(new Font("Tahoma", Font.PLAIN, 14));
        chckbxMembership.setBounds(245, 195, 216, 50);
        contentPane.add(chckbxMembership);
        
        JButton btnConfirm_1 = new JButton("Waterpark");
        btnConfirm_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		java.util.Date selectedDate = dateChooser.getDate();
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(selectedDate);

		        // Extract day, month, and year as integers
		        int Day = calendar.get(Calendar.DAY_OF_MONTH);
		        int Month = calendar.get(Calendar.MONTH) + 1; // Adding 1 because months are zero-based
		        int Year = calendar.get(Calendar.YEAR);
				boolean member;
				UserController userController = new UserController();
				Customer cust = new Customer();
				Date date = new Date(Day,Month,Year);
				//String park = textThemepark.getText();
				if(chckbxMembership.isSelected())
				{
					member = true;
				}
				else
				{
					member = false;
				}
        		try {						
					cust = userController.getCustomer(custID);
					Waterpark frame = new Waterpark(custID,cust,date,member);
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
        	}
        });
		btnConfirm_1.setBounds(360, 332, 111, 21);
		contentPane.add(btnConfirm_1);
		
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu frame = new MainMenu(custID, 0);
                frame.setVisible(true);
            }
        });
        btnBack.setBounds(307, 391, 85, 21);
        contentPane.add(btnBack);
		
		JButton btnConfirm = new JButton("Amusement Park");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//kena buat if else dekat sini
				java.util.Date selectedDate = dateChooser.getDate();
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(selectedDate);

		        // Extract day, month, and year as integers
		        int Day = calendar.get(Calendar.DAY_OF_MONTH);
		        int Month = calendar.get(Calendar.MONTH) + 1; // Adding 1 because months are zero-based
		        int Year = calendar.get(Calendar.YEAR);
				boolean member;
				UserController userController = new UserController();
				Customer cust = new Customer();
				String themeid;
				String theme;
				/*String day = textDay.getText();
				int Day = Integer.parseInt(day);
				String month = textMonth.getText();
				int Month = Integer.parseInt(month);
				String year = textYear.getText();
				int Year = Integer.parseInt(year);*/
				Date date = new Date(Day,Month,Year);
				//String park = textThemepark.getText();
				if(chckbxMembership.isSelected())
				{
					member = true;
				}
				else
				{
					member = false;
				}
				try {
					cust = userController.getCustomer(custID);
					userController.getCustomer(custID);
					Amusementpark frame = new Amusementpark(custID,cust,date,member);
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//JOptionPane.showMessageDialog(null,park);
				//String park1 = "Waterpark";
				//String park2 = "Amusement Park";
			}
		});
		btnConfirm.setBounds(219, 332, 111, 21);
		contentPane.add(btnConfirm);
		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\PAYMENT-1-1.png"));
		lblBackGround.setBounds(-324, -181, 1197, 734);
		contentPane.add(lblBackGround);
		
		
		
		
	}
}
