package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.Customer;
import model.Staff;

public class UpdateProfileStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textFullname;
	private JTextField textPhoneNum;
	private JTextField textStaffType;
	private static int staffID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProfileStaff frame = new UpdateProfileStaff(staffID);
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
	public UpdateProfileStaff(final int staffID) {
		this.staffID = staffID;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUpdateProfile = new JLabel("UPDATE PROFILE STAFF");
		lblUpdateProfile.setForeground(new Color(0, 0, 64));
		lblUpdateProfile.setFont(new Font("Tw Cen MT", Font.BOLD, 45));
		lblUpdateProfile.setBounds(26, 10, 485, 106);
		contentPane.add(lblUpdateProfile);
		
		//Username
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(26, 109, 130, 50);
		contentPane.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setBounds(26, 140, 268, 19);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		//Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(26, 149, 142, 50);
		contentPane.add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(26, 180, 268, 19);
		contentPane.add(textPassword);
		
		//FullName
		JLabel lblFullName;
		lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFullName.setBounds(26, 195, 130, 50);
		contentPane.add(lblFullName);
		
		textFullname = new JTextField();
		textFullname.setColumns(10);
		textFullname.setBounds(26, 232, 268, 19);
		contentPane.add(textFullname);
		
		//Address
		JLabel lblPhone;
		lblPhone = new JLabel("Phone Number");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhone.setBounds(26, 249, 142, 50);
		contentPane.add(lblPhone);
		
		textPhoneNum = new JTextField();
		textPhoneNum.setColumns(10);
		textPhoneNum.setBounds(26, 280, 268, 19);
		contentPane.add(textPhoneNum);

		//email
		JLabel lblEmail = new JLabel("Staff Type");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(26, 295, 142, 50);
		contentPane.add(lblEmail);
		
		textStaffType = new JTextField();
		textStaffType.setColumns(10);
		textStaffType.setBounds(26, 326, 268, 19);
		contentPane.add(textStaffType);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileStaff frame = new ProfileStaff(staffID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(26, 379, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textUsername.setText(null);
				textPassword.setText(null);
				textFullname.setText(null);
				textPhoneNum.setText(null);
				textStaffType.setText(null);				
			}
		});
		btnReset.setBounds(118, 379, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//ni customer punya data
				double cost = 0;
				Staff staff = new Staff();
				staff.setStaffid(staffID);
				String username = textUsername.getText();
				staff.setUsername(username);
				String password = textPassword.getText();
				staff.setPassword(password);
				String fullname = textFullname.getText();
				staff.setName(fullname);
				String phone = textPhoneNum.getText();
				staff.setPhoneNo(phone);
				String staffType = textStaffType.getText();
				staff.setStaff_type(staffType);
				
				UserController userController = new UserController();
				try {
					userController.updateProfileStaff(staff);
					JOptionPane.showMessageDialog(null,"Your account's profile has been updated");
					StaffMenu frame = new StaffMenu(staffID,cost);
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnConfirm.setBounds(209, 379, 85, 21);
		contentPane.add(btnConfirm);
		
		JLabel lblNewLabel = new JLabel("BACKGROUND");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\UPDATE-PROFILE.png"));
		lblNewLabel.setBounds(-305, -139, 1197, 734);
		contentPane.add(lblNewLabel);
		


	}

}
