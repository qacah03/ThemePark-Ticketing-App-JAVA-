package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import controller.UserController;
import model.Staff;

import javax.swing.JTextField;

public class ProfileStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String password;
	private JTextArea txtProfile;
	private static int staffid;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileStaff frame = new ProfileStaff(staffid);
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
	 public ProfileStaff(final int staffid) {
	    this.staffid = staffid; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProfile = new JLabel("PROFILE STAFF");
		lblProfile.setForeground(new Color(0, 0, 64));
		lblProfile.setFont(new Font("Tw Cen MT", Font.BOLD, 50));
		lblProfile.setBounds(10, 0, 348, 90);
		contentPane.add(lblProfile);
	
		JTextArea txtProfile = new JTextArea();
		txtProfile.setFont(new Font("Arial Black", Font.PLAIN, 12)); // Increased font size for readability
		
		//MASIH CUSTOMER PUNYA
		UserController userController = new UserController();
		
        try {
            // Use the stored custID to fetch the profile information
            String profileInfo = userController.viewProfileStaff(staffid);
            txtProfile.setText(profileInfo);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle the exception appropriately
            txtProfile.setText("Error fetching profile information.");
            e.printStackTrace();
        }
		

		txtProfile.setBackground(new Color(128, 255, 255));
		txtProfile.setBounds(10, 87, 343, 269);
		contentPane.add(txtProfile);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cost = 0;
				StaffMenu frame = new StaffMenu(staffid,cost);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(75, 368, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UpdateProfileStaff frame = new UpdateProfileStaff(staffid);
				frame.setVisible(true);
				dispose();
			}
		});
		btnUpdate.setBounds(180, 368, 85, 21);
		contentPane.add(btnUpdate);
		
		
		JLabel lblBackGround = new JLabel("BACKGROUND");
		lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
		lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1.png"));
		lblBackGround.setBounds(-301, -154, 1197, 734);
		contentPane.add(lblBackGround);
		


	}

	protected void setPassword(String password2) {
		// TODO Auto-generated method stub
		
	}
}


