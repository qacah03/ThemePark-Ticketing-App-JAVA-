package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class HomeStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeStaff frame = new HomeStaff();
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
	public HomeStaff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblCustomer = new JLabel("STAFF");
		lblCustomer.setForeground(new Color(0, 0, 64));
		lblCustomer.setFont(new Font("Tw Cen MT", Font.BOLD, 50));
		lblCustomer.setBounds(280, 173, 137, 106);
		contentPane.add(lblCustomer);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginStaff frame = new LoginStaff();
				frame.setVisible(true);
				dispose();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STAFFxCUSTOMER frame = new STAFFxCUSTOMER();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(305, 315, 85, 21);
		contentPane.add(btnBack);

		btnLogin.setBounds(223, 261, 118, 36);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterStaff frame = new RegisterStaff();
				frame.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(362, 261, 118, 36);
		contentPane.add(btnRegister);
		
		JLabel lblSTAFF = new JLabel("New label");
		lblSTAFF.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\Home-1.png"));
		lblSTAFF.setBounds(-285, -124, 1197, 734);
		contentPane.add(lblSTAFF);
		

		
		
		
		
		
	}
}
