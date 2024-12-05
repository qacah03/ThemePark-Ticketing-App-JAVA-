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

public class STAFFxCUSTOMER extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					STAFFxCUSTOMER frame = new STAFFxCUSTOMER();
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
	public STAFFxCUSTOMER() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSTAFF = new JButton("STAFF");
		btnSTAFF.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		btnSTAFF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeStaff frame = new HomeStaff();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSTAFF.setBounds(292, 229, 118, 36);
		contentPane.add(btnSTAFF);
		
		JButton btnCustomer = new JButton("CUSTOMER");
		btnCustomer.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frame = new Home();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCustomer.setBounds(293, 281, 118, 36);
		contentPane.add(btnCustomer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\Home-1.png"));
		lblNewLabel.setBounds(-285, -124, 1197, 734);
		contentPane.add(lblNewLabel);

		
		
		
		
		
	}
}
