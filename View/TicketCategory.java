package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;

public class TicketCategory extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static int custID;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TicketCategory frame = new TicketCategory(custID);
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
    public TicketCategory(final int custID) {
    	this.custID = custID;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 721, 459);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(199, 10, 319, 78);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblTicketCategory = new JLabel("TICKET CATEGORY");
        lblTicketCategory.setBounds(0, 10, 319, 78);
        panel_2.add(lblTicketCategory);
        lblTicketCategory.setForeground(new Color(0, 0, 64));
        lblTicketCategory.setFont(new Font("Tw Cen MT", Font.BOLD, 40));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double cost = 0;
                MainMenu frame = new MainMenu(custID, cost);
                frame.setVisible(true);
                dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.setBounds(322, 391, 85, 21);
        contentPane.add(btnBack);

        tableModel = new DefaultTableModel(new Object[]{"CATEGORY ID", "CATEGORY NAME\t\t\t\t", "PRICE"}, 0);

        // Add rows to the table
        tableModel.addRow(new Object[]{"CATEGORY 3001", "1 Baby [0-4 YEARS OLD]", "RM0"});
        tableModel.addRow(new Object[]{"CATEGORY 3002", "1 CHILD [5-11 YEARS OLD]", "RM50"});
        tableModel.addRow(new Object[]{"CATEGORY 3003", "1 ADULT [12-59 YEARS OLD]", "RM80"});
        tableModel.addRow(new Object[]{"CATEGORY 3004", "1 SENIOR [60 - 99 YEARS OLD]", "RM40"});
        tableModel.addRow(new Object[]{"CATEGORY 3005", "COMBO! 2 ADULTS 3 CHILDS 1 SENIORS", "RM315 (SAVE UP 10%)"});
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 97, 687, 224);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(-6, -15, 699, 229);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane_1.setBounds(10, 32, 687, 189);
        panel_1.add(scrollPane_1);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial Black", Font.PLAIN, 10));
        table.setRowHeight(30);

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane_1.setViewportView(scrollPane);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, header.getFont().getSize()));

        JLabel lblBackGround = new JLabel("BACKGROUND");
        lblBackGround.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\Telegram Desktop\\REGISTER-1-1.png"));
        lblBackGround.setBounds(-280, -199, 1197, 734);
        contentPane.add(lblBackGround);
    }
}
