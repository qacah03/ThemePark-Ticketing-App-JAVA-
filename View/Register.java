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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFullName;
	private JLabel lblAddress;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textFullName;
	private JTextField textAddress;
	private JTextField textEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setForeground(new Color(0, 0, 64));
		lblRegister.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		lblRegister.setBounds(26, 10, 268, 106);
		contentPane.add(lblRegister);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(26, 102, 130, 50);
		contentPane.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setBounds(26, 136, 268, 21);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(26, 149, 142, 50);
		contentPane.add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(26, 182, 268, 21);
		contentPane.add(textPassword);
		

		
		JLabel lblFullName;
		lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFullName.setBounds(26, 195, 130, 50);
		contentPane.add(lblFullName);
		
		textFullName = new JTextField();
		textFullName.setColumns(10);
		textFullName.setBounds(26, 232, 268, 21);
		contentPane.add(textFullName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(26, 295, 142, 50);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(26, 330, 268, 21);
		contentPane.add(textEmail);
		
		JLabel lblAddress;
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(26, 249, 142, 50);
		contentPane.add(lblAddress);
		
		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(26, 283, 268, 21);
		contentPane.add(textAddress);
		

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frame = new Home();
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
				textFullName.setText(null);
				textEmail.setText(null);
				textAddress.setText(null);
			}
		});
		btnReset.setBounds(118, 379, 85, 21);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer cust = new Customer();
				
				String username = textUsername.getText();
				cust.setUsername(username);
				String password = textPassword.getText();
				cust.setPassword(password);
				String fullname = textFullName.getText();
				cust.setName(fullname);
				String address = textAddress.getText();
				cust.setAddress(address);
				String email = textEmail.getText();
				cust.setEmail(email);
				cust.setPhonenumber("0134749115");
				
				UserController userController = new UserController();
				try {
					userController.insertCustomer(cust);
					JOptionPane.showMessageDialog(null,"Your account has been successfully registered!!, Please log in to your account to continue");
					//System.out.println("\nYour account has been successfully registered!!");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnConfirm.setBounds(209, 379, 85, 21);
		contentPane.add(btnConfirm);
		
		JLabel lblNewLabel = new JLabel("BACKGROUND");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1.png"));
		lblNewLabel.setBounds(-305, -139, 1197, 734);
		contentPane.add(lblNewLabel);
		
		
		
		
	}

}
