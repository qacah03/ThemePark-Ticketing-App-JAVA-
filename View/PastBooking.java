package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;

import java.awt.Color;
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

public class PastBooking extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static int custID;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PastBooking frame = new PastBooking(custID);
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
    public PastBooking(final int custID) {
        this.custID = custID; // Store the customer ID obtained from the login
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 721, 459);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPastBooking = new JLabel("PAST BOOKING");
        lblPastBooking.setForeground(new Color(0, 0, 64));
        lblPastBooking.setFont(new Font("Tw Cen MT", Font.BOLD, 53));
        lblPastBooking.setBounds(10, -11, 406, 90);
        contentPane.add(lblPastBooking);

        JLabel lblBookingID = new JLabel("Enter Booking ID to delete:");
        lblBookingID.setFont(new Font("Calibri", Font.BOLD, 16));
        lblBookingID.setBackground(new Color(0, 0, 64));
        lblBookingID.setBounds(10, 324, 260, 42);
        contentPane.add(lblBookingID);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 355, 260, 24);
        contentPane.add(textField);

        tableModel = new DefaultTableModel(new Object[]{"Booking ID", "Category Name", "Booking Date", "Tickets"}, 0);
        table = new JTable(tableModel);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = table.getSelectedRow();
        		textField.setText(tableModel.getValueAt(i,0).toString());
        	}
        });
        table.setFont(new Font("Arial Black", Font.PLAIN, 12)); // Adjust font size if needed

        // Fetch and add rows to the table from your data source (UserController)
        final UserController userController = new UserController();
        try {
            String[][] bookingInfo = userController.viewAllBooking(custID);
            for (String[] row : bookingInfo) {
                tableModel.addRow(row);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 61, 687, 253);
        contentPane.add(scrollPane);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, header.getFont().getSize()));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CancelBooking frame = new CancelBooking(custID);
                frame.setVisible(true);
            }
        });
        btnBack.setBounds(20, 389, 85, 21);
        contentPane.add(btnBack);

        JButton btnCancelBooking = new JButton("Cancel Booking");
        btnCancelBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int CustID = 0;
                int cost = 0;
                int i = table.getSelectedRow();
				tableModel.setValueAt(textField.getText(), i, 0);
                String bookid = textField.getText();
                if (!bookid.isEmpty()) {
                    int rowCount = tableModel.getRowCount();
                    boolean found = false;
                    for (int j = rowCount - 1; j >= 0; j--) {
                        if (bookid.equals(tableModel.getValueAt(j, 0).toString())) {
                            tableModel.removeRow(j);
                            found = true;
                        }
                    }

                    if (found) {
                        // You can add the logic to delete the data from your data source here
                        // For example, you might call a method to delete the data from the database using bookingID.
                        // Replace the following lines with your actual deletion logic.

                        // int bookingIDInt = Integer.parseInt(bookingID);
                        // UserController userController = new UserController();
                        // userController.deleteBooking(bookingIDInt);

                        textField.setText(""); // Clear the text field after deletion
                        JOptionPane.showMessageDialog(null, "Rows with Booking ID " + bookid + " deleted successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "No rows with Booking ID " + bookid + " found");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Booking ID to delete");
                }
                int BookID = Integer.parseInt(bookid);
                UserController userController = new UserController();
                try {
                    userController.updateDeletedTicket(BookID);
                    userController.deletePayment(BookID);
                    userController.deleteBookCat(BookID);
                    userController.deleteBooking(BookID);
                    JOptionPane.showMessageDialog(null, "Your booking has been cancelled");
                    /*MainMenu frame = new MainMenu(CustID, cost);
                    frame.setVisible(true);*/
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnCancelBooking.setBounds(117, 389, 143, 21);
        contentPane.add(btnCancelBooking);

        JLabel lblBackGround = new JLabel("BACKGROUND");
        lblBackGround.setFont(new Font("Arial Black", Font.PLAIN, 21));
        lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1.png"));
        lblBackGround.setBounds(-301, -154, 1197, 734);
        contentPane.add(lblBackGround);
    }
}
