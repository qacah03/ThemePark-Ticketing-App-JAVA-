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
import javax.swing.JTextField;

public class Profile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String password;
	private static int custID; // Add a variable to store the customer ID 
	private JTextArea txtProfile;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile(custID);
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
	 public Profile(final int custID) {
	        this.custID = custID; // Store the customer ID obtained from the login
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProfile = new JLabel("PROFILE");
		lblProfile.setForeground(new Color(0, 0, 64));
		lblProfile.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		lblProfile.setBounds(10, 0, 268, 90);
		contentPane.add(lblProfile);
		
	
		JTextArea txtProfile = new JTextArea();
		txtProfile.setFont(new Font("Arial Black", Font.PLAIN, 12)); // Increased font size for readability
		UserController userController = new UserController();

        try {
            // Use the stored custID to fetch the profile information
            String profileInfo = userController.viewProfile(custID);
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
				MainMenu frame = new MainMenu(custID,cost);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(75, 368, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateProfile frame = new UpdateProfile(custID);
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


