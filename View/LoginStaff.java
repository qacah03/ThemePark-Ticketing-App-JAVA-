package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class LoginStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
    JPasswordField txtPassword = new JPasswordField();
	//private JTextField txtPassword;

	private String username;
	public String password;
	private int staffid;
	
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginStaff frame = new LoginStaff();
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
	public LoginStaff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN STAFF");
		lblNewLabel_1.setForeground(new Color(0, 0, 64));
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		lblNewLabel_1.setBounds(26, 10, 384, 106);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(26, 139, 130, 50);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(26, 178, 193, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(26, 228, 142, 50);
		contentPane.add(lblPassword);
		
		//txtPassword = (JPasswordField) new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(26, 267, 193, 30);
		contentPane.add(txtPassword);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeStaff frame = new HomeStaff();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(26, 326, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserController userController = new UserController();
		        boolean success;
		        double cost = 0;

		        username = txtUsername.getText();
		        // Get the password securely from the JPasswordField
		        password = new String(txtPassword.getPassword());
				
				//ini masih customer punya login
				try {
					success = userController.loginStaff(username, password);
					if (success == true)
					{
						Staff staff = new Staff();
						int ids = userController.getStaffId(username);
						setid(ids);
						JOptionPane.showMessageDialog(null,"Login Successfull");
						JOptionPane.showMessageDialog(null,staffid);
						StaffMenu frame = new StaffMenu(staffid,cost);
						frame.setVisible(true);
						dispose();
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid Username or Password");
						txtUsername.setText(null);
						txtPassword.setText(null);
					}
					//Member = true;
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnConfirm.setBounds(134, 326, 85, 21);
		contentPane.add(btnConfirm);
		
		JLabel lblNewLabel = new JLabel("BACKGROUND");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\LOGIN-1.png"));
		lblNewLabel.setBounds(-347, -163, 1197, 734);
		contentPane.add(lblNewLabel);
		
		
		

		
		
		

	}
	public void setid(int ids)
	{
		staffid = ids;
	}
	
	public int getid()
	{
		return staffid;
	}

}