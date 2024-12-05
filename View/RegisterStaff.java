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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.Customer;
import model.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RegisterStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFullName;
	private JLabel lblAddress;
	private JTextField textIdentificationNumber;
	private JTextField textStaffName;
	private JTextField textPhoneNumber;
	private JTextField textAddress;
	private JTextField textEmail;
	private JTextField textStaffType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterStaff frame = new RegisterStaff();
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
	public RegisterStaff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("REGISTER STAFF");
		lblRegister.setForeground(new Color(0, 0, 64));
		lblRegister.setFont(new Font("Tw Cen MT", Font.BOLD, 50));
		lblRegister.setBounds(26, -15, 459, 106);
		contentPane.add(lblRegister);
		
		JLabel lblIC = new JLabel("Identification Number");
		lblIC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIC.setBounds(26, 57, 130, 50);
		contentPane.add(lblIC);
		
		textIdentificationNumber = new JTextField();
		textIdentificationNumber.setBounds(26, 91, 268, 21);
		contentPane.add(textIdentificationNumber);
		textIdentificationNumber.setColumns(10);
		
		
		JLabel lblStaffName = new JLabel("STAFF NAME");
		lblStaffName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStaffName.setBounds(26, 104, 142, 50);
		contentPane.add(lblStaffName);
		
		textStaffName = new JTextField();
		textStaffName.setColumns(10);
		textStaffName.setBounds(26, 137, 268, 21);
		contentPane.add(textStaffName);
		

		
		JLabel lblPhoneNumber;
		lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhoneNumber.setBounds(26, 150, 130, 50);
		contentPane.add(lblPhoneNumber);
		
		textPhoneNumber = new JTextField();
		textPhoneNumber.setColumns(10);
		textPhoneNumber.setBounds(26, 187, 268, 21);
		contentPane.add(textPhoneNumber);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(26, 250, 142, 50);
		contentPane.add(lblPassword);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(26, 285, 268, 21);
		contentPane.add(textEmail);
		
		JLabel lblUsername;
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(26, 204, 142, 50);
		contentPane.add(lblUsername);
		
		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(26, 238, 268, 21);
		contentPane.add(textAddress);
		

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeStaff frame = new HomeStaff();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(26, 379, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textIdentificationNumber.setText(null);
				textStaffName.setText(null);
				textPhoneNumber.setText(null);
				textEmail.setText(null);
				textAddress.setText(null);
			}
		});
		btnReset.setBounds(118, 379, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Staff staff = new Staff();
				// ni customer, kena tukar staff
				//Customer cust = new Customer();
				
				String icNo = textIdentificationNumber.getText();
				staff.setIcNo(icNo);
				String name = textStaffName.getText();
				staff.setName(name);
				String phone = textPhoneNumber.getText();
				staff.setPhoneNo(phone);
				String username = textAddress.getText();
				staff.setUsername(username);
				String password = textEmail.getText();
				staff.setPassword(password);
				String staffType = textStaffType.getText();
				staff.setStaff_type(staffType);
								
				
				UserController userController = new UserController();
				try {
					userController.insertStaff(staff);
					JOptionPane.showMessageDialog(null,"Your account has been successfully registered!!, Please log in to your account to continue");
					HomeStaff frame = new HomeStaff();
					frame.setVisible(true);
					dispose();
					//System.out.println("\nYour account has been successfully registered!!");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnConfirm.setBounds(209, 379, 85, 21);
		contentPane.add(btnConfirm);
		
		JLabel lblSTAFF_TYPE = new JLabel("STAFF_TYPE");
		lblSTAFF_TYPE.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSTAFF_TYPE.setBounds(26, 310, 142, 50);
		contentPane.add(lblSTAFF_TYPE);
		
		textStaffType = new JTextField();
		textStaffType.setColumns(10);
		textStaffType.setBounds(26, 345, 268, 21);
		contentPane.add(textStaffType);
		
		JLabel lblNewLabel = new JLabel("BACKGROUND");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1.png"));
		lblNewLabel.setBounds(-305, -139, 1197, 734);
		contentPane.add(lblNewLabel);
		

		
		
		
	}

}
