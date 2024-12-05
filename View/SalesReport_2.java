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
import com.itextpdf.text.DocumentException;
import controller.UserController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SalesReport_2 extends JFrame {

    private static final long serialVersionUID = 1L;
	private static final JTable Jtable = null;
    private JPanel contentPane;
    private static int staffid;
    private JTable table;
    private DefaultTableModel model;
    private static int custID;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SalesReport_2 frame = new SalesReport_2(custID);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SalesReport_2(final int custID) {
        this.custID = custID;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 721, 459);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSalesReport = new JLabel("SALES REPORT");
        lblSalesReport.setForeground(new Color(0, 0, 64));
        lblSalesReport.setFont(new Font("Tw Cen MT", Font.BOLD, 55));
        lblSalesReport.setBounds(181, -27, 347, 90);
        contentPane.add(lblSalesReport);

        // Create the JTable and DefaultTableModel
     // Create the JTable and DefaultTableModel
        String[] columnNames = {"Category Name", "Category Sales"}; // Replace with your column names
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the cells non-editable
            }
        };

        table = new JTable(model);
        table.setRowHeight(50);
        // Increase the font size for the JTable
        Font tableFont = new Font("Arial", Font.PLAIN, 25); // You can adjust the font size here
        table.setFont(tableFont);

        // Create a JScrollPane and add the JTable to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(49, 71, 609, 237); // Adjust the position and size as needed
        contentPane.add(scrollPane);

        // Make the column headers bold
        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Arial", Font.BOLD, 25); // You can adjust the font size and style here
        header.setFont(headerFont);

        // Example: Add sample data to the table
        Object[] row = new Object[2]; // Replace with your data
        int[] sales = null;
        String catName = null;
        try {
            UserController userController = new UserController();
            sales = userController.getSales();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 4; i++)
        {
            if(i == 0)
            {
                catName = "Amusement Park - Adult";
            }
            else if(i == 1)
            {
                catName = "Amusement Park - Child";
            }
            else if(i == 2)
            {
                catName = "Waterpark - Rider";
            }
            else
            {
                catName = "Waterpark - Surfer";
            }
            row[0] = catName;
            row[1] = sales[i];
            model.addRow(row);
        }

        

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double cost = 0;
                StaffMenu frame = new StaffMenu(staffid, cost);
                frame.setVisible(true);
                dispose();
            }
        });
        btnBack.setBounds(163, 344, 85, 21);
        contentPane.add(btnBack);

        JButton btnUpdate = new JButton("PIE CHART");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReportSales frame = new ReportSales("TICKETS' CATEGORY SALES COMPARISON");
                frame.setVisible(true);
                dispose();
            }
        });
        btnUpdate.setBounds(281, 344, 106, 21);
        contentPane.add(btnUpdate);

        JButton btnGetPDF = new JButton("Get PDF");
        btnGetPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserController userController = new UserController();
                try {
                    userController.generateAndSaveSalesReport();
                } catch (IOException | DocumentException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnGetPDF.setBounds(426, 344, 85, 21);
        contentPane.add(btnGetPDF);

        JLabel lblBackGround = new JLabel("BACKGROUND");
        lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
        lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\PAYMENT-1.png"));
        lblBackGround.setBounds(-301, -154, 1197, 734);
        contentPane.add(lblBackGround);
    }
}
