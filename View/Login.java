package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.UserController;

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


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	//private JTextField txtPassword;
	JPasswordField txtPassword = new JPasswordField();
	private String username;
	public String password;
	private int custid;
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	public int getID()
	{
		return custid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setForeground(new Color(0, 0, 64));
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		lblNewLabel_1.setBounds(26, 10, 268, 106);
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
		
		//txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(26, 267, 193, 30);
		contentPane.add(txtPassword);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frame = new Home();
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
				
				try {
					success = userController.login(username, password);
					if (success == true)
					{
						//custid = success;
						int ids = userController.getCustId(username);
						setid(ids);
						JOptionPane.showMessageDialog(null,"Login Successfull");
						setUsername(username);
						// Create a Profile object and pass the custid to its constructor
		                //Profile profileFrame = new Profile(custid);
		               // profileFrame.setVisible(true);
						MainMenu frame = new MainMenu(custid,cost);
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
		custid = ids;
	}
	
	public int getid()
	{
		return custid;
	}

}


