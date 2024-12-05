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

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblCustomer = new JLabel("CUSTOMER");
		lblCustomer.setForeground(new Color(0, 0, 64));
		lblCustomer.setFont(new Font("Tw Cen MT", Font.BOLD, 50));
		lblCustomer.setBounds(230, 183, 247, 106);
		contentPane.add(lblCustomer);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogin.setBounds(230, 271, 118, 36);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register frame = new Register();
				frame.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(359, 271, 118, 36);
		contentPane.add(btnRegister);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STAFFxCUSTOMER frame = new STAFFxCUSTOMER();
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(311, 333, 85, 21);
		contentPane.add(btnBack);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\Home-1.png"));
		lblNewLabel.setBounds(-285, -124, 1197, 734);
		contentPane.add(lblNewLabel);

		
	}
}
