package View;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.UserController;
import model.Customer;

public class BiodataCust {
	public static void main(String[] args)
	{
		JTable table = new JTable();
		Object[] columns = {"Name", "Phone Number", "Username", "Address", "Email"};
		DefaultTableModel model = new DefaultTableModel();
		
		JFrame frame = new JFrame("WINDOW");
		frame.getContentPane().setBackground(new Color(0,0,0));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100,100,717,577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.red);
		table.setGridColor(Color.red);
		table.setSelectionForeground(Color.white);
		table.setFont(new Font("Tahoma", Font.PLAIN,17));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,683,402);
		frame.getContentPane().add(pane);
		
		Object[] row = new Object[5];
		int custID = 228;
		Customer cust = new Customer();
		UserController userController = new UserController();
		try {
			cust = userController.biodataCust(custID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		row[0] = cust.getName();
		row[1] = cust.getPhonenumber();
		row[2] = cust.getUsername();
		row[3] = cust.getAddress();
		row[4] = cust.getEmail();
		model.addRow(row);
		
		frame.setVisible(true);
	}

}
