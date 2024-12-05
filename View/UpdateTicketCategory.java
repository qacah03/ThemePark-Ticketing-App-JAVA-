package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateTicketCategory extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static int staffid;
    private static double cost;
    private JTextField textEnterCat;
    private JTextField textInsertNewStock;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateTicketCategory frame = new UpdateTicketCategory(staffid);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UpdateTicketCategory(final int staffid) {
        this.staffid = staffid;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 721, 459);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTicketCategory = new JLabel("UPDATE TICKET CATEGORY");
        lblTicketCategory.setForeground(new Color(0, 0, 64));
        lblTicketCategory.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
        lblTicketCategory.setBounds(117, 0, 468, 78);
        contentPane.add(lblTicketCategory);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffMenu frame = new StaffMenu(staffid, cost);
                frame.setVisible(true);
                dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.setBounds(219, 391, 85, 21);
        contentPane.add(btnBack);

        // Create the JTable and DefaultTableModel
        String[] columnNames = {"Category ID", "Category Name", "Category Stocks"}; // Replace with your column names
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = table.getSelectedRow();
				textInsertNewStock.setText(model.getValueAt(i,2).toString());
				textEnterCat.setText(model.getValueAt(i,0).toString());

        	}
        });

        // Create a JScrollPane and add the JTable to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(53, 76, 600, 183); // Adjust the position and size as needed
        contentPane.add(scrollPane);
        table.setRowHeight(40);
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, header.getFont().getSize()));

        // Example: Add sample data to the table
        //Object[] row = {"Category ID", "Category Name", "Category Stocks"}; // Replace with your data
        Object[] row = new Object[3];

        String catName = null;
		int catid = 0;
		UserController userController = new UserController();
		/*Customer cust = new Customer();
		UserController userController = new UserController();
		try {
			cust = userController.biodataCust(custID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int[] stock = null;
		try {
			stock = userController.getStocks();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<4 ; i++)
		{
			if(i == 0)
			{
				catName = "Amusement Park - Child";
				catid = 3001;
			}
			else if(i == 1)
			{
				catName = "Waterpark - Rider";
				catid = 3002;
			}
			else if(i == 2)
			{
				catName = "Waterpark - Surfer";
				catid = 3003;
			}
			else
			{
				catName = "Amusement Park - Adult";
				catid = 3004;
			}
			row[0] = catid;
			row[1] = catName;			
			row[2] = stock[i];
			model.addRow(row);
		}

        //model.addRow(row);

        JLabel lblEnterCategory = new JLabel("Enter Category");
        lblEnterCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEnterCategory.setBounds(302, 261, 102, 50);
        contentPane.add(lblEnterCategory);

        textEnterCat = new JTextField();
        textEnterCat.setColumns(10);
        textEnterCat.setBounds(219, 298, 268, 21);
        contentPane.add(textEnterCat);

        JLabel lblInsertNewStock = new JLabel("Insert New Stock");
        lblInsertNewStock.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblInsertNewStock.setBounds(290, 321, 127, 50);
        contentPane.add(lblInsertNewStock);

        textInsertNewStock = new JTextField();
        textInsertNewStock.setColumns(10);
        textInsertNewStock.setBounds(219, 357, 268, 21);
        contentPane.add(textInsertNewStock);

        JButton btnConfirm = new JButton("Update");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int i = table.getSelectedRow();
				model.setValueAt(textInsertNewStock.getText(), i, 2);
				model.setValueAt(textEnterCat.getText(), i, 0);
                UserController userController = new UserController();
                String newStock = textInsertNewStock.getText();
                int NewStock = Integer.parseInt(newStock);
                String catid = textEnterCat.getText();
                int CatId = Integer.parseInt(catid);
                try {
                    userController.updateTicket(CatId, NewStock);
                    JOptionPane.showMessageDialog(null, "The ticket's category has been updated");
                    //StaffMenu frame = new StaffMenu(staffid, cost);
                    //frame.setVisible(true);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnConfirm.setBounds(402, 391, 85, 21);
        contentPane.add(btnConfirm);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textInsertNewStock.setText(null);
                textEnterCat.setText(null);
            }
        });
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReset.setBounds(311, 391, 85, 21);
        contentPane.add(btnReset);

        JLabel lblBackGround = new JLabel("BACKGROUND");
        lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1-1.png"));
        lblBackGround.setBounds(-280, -199, 1197, 734);
        contentPane.add(lblBackGround);
    }
}
