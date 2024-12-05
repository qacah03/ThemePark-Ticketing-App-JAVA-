package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import Database.MyDatabase;
import model.Customer;
import model.Date;
import model.Staff;


public class UserController {
	public String Username;
	private Staff staff;
	public int insertCustomer(Customer customer) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into CUSTOMER (CUST_PHONE, CUST_NAME, CUST_USERNAME, CUST_PASSWORD, CUST_ADDRESS, CUST_EMAIL) values (?,?,?,?,?,?)";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, customer.getPhonenumber());
		preparedStatement.setString(2, customer.getName());
		preparedStatement.setString(3, customer.getUsername());
		preparedStatement.setString(4, customer.getPassword());
		preparedStatement.setString(5, customer.getAddress());
		preparedStatement.setString(6, customer.getEmail());
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public int insertStaff(Staff staff) throws ClassNotFoundException, SQLException
	{
		String sql = "INSERT INTO STAFF (IDENTIFICATION_NUMBER,STAFF_NAME,PHONE_NUMBER,USERNAME,PASSWORD,STAFF_TYPE) VALUES (?,?,?,?,?,?)";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, staff.getIcNo());
		preparedStatement.setString(2, staff.getName());
		preparedStatement.setString(3, staff.getPhoneNo());
		preparedStatement.setString(4, staff.getUsername());
		preparedStatement.setString(5, staff.getPassword());
		preparedStatement.setString(6, staff.getStaff_type());
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public String UserName()
	{
		return Username;
	}
	
	public boolean login(String username, String password) throws ClassNotFoundException, SQLException
	{
		Username = username;
		boolean success = false;
		Customer cust = new Customer();
		int custid;
		String phone, name, Username, pass, add, email;
		
		String sql = "select CUST_ID, CUST_PHONE, CUST_NAME, CUST_USERNAME, CUST_PASSWORD, CUST_ADDRESS, CUST_EMAIL from CUSTOMER where CUST_USERNAME=? AND CUST_PASSWORD =?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
		{
			while(resultSet.next())
			{
				custid = resultSet.getInt(1);
				phone = resultSet.getString(2);
				name = resultSet.getString(3);
				Username = resultSet.getString(4);
				pass = resultSet.getString(5);
				add = resultSet.getString(6);
				email = resultSet.getString(7);
				
			}
			custid = cust.getId();
			conn.close();
			return true;
		}
		else
		{
			conn.close();
			return false;
		}
	}
	
	public boolean loginStaff(String username, String password) throws ClassNotFoundException, SQLException
	{
		boolean success = false;
		
		String sql = "select STAFF_ID, IDENTIFICATION_NUMBER, STAFF_NAME, PHONE_NUMBER, USERNAME, PASSWORD, STAFF_TYPE from STAFF where USERNAME=? AND PASSWORD =?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
		{
			while(resultSet.next())
			{
				staff.setStaffid(resultSet.getInt(1)); 
				staff.setIcNo(resultSet.getString(2));
				staff.setName(resultSet.getString(3));
				staff.setPhoneNo(resultSet.getString(4));
				staff.setUsername(resultSet.getString(5));
				staff.setPassword(resultSet.getString(6));
				staff.setStaff_type(resultSet.getString(7)); 				
			}
			conn.close();
			return true;
		}
		else
		{
			conn.close();
			return false;
		}
	}
	
	public void viewPromo() throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT CATEGORY_NAME,PROMOTIONS FROM CATEGORY WHERE PROMOTIONS != 1";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println("-------------------------------------------------");
		System.out.println("|\tCATEGORY NAME\t|\tDISCOUNT\t|");
		while(resultSet.next())
		{
			System.out.println("-------------------------------------------------");
			System.out.print("|\t" +resultSet.getString(1) + "\t|\t");
			double discount = 100 - (resultSet.getDouble(2) * 100);
			System.out.println(discount + "%\t\t|");
		}
		System.out.println("-------------------------------------------------");
		conn.close();
	}
	
	public String viewTicketWaterpark(int bookid, double cost) throws ClassNotFoundException, SQLException
	{
		String catName, bookDate = null, name = null;
		int total, totalSurfer = 0, totalRider = 0;
		String sql = "SELECT C.CATEGORY_NAME, B.BOOKING_DATE, BC.TOTAL, CU.CUST_NAME FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID JOIN CUSTOMER CU ON B.CUST_ID = CU.CUST_ID WHERE B.BOOKING_ID = ?;";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1,bookid);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			catName = resultSet.getString(1);
	        bookDate = resultSet.getString(2);
	        total = resultSet.getInt(3);
	        name = resultSet.getString(4);
	        if(catName.equals("Waterpark -Surfer"))
	        {
	        	totalSurfer = total;
	        }
	        else
	        {
	        	totalRider = total;
	        }
	        
		}
		conn.close();
		StringBuilder table = new StringBuilder();

        table.append("Name\t: ").append(name).append("\n");
        table.append("Theme Park\t: Waterpark\n");
        table.append("Rider\t: ").append(totalRider).append("\n");
        table.append("Surfer\t: ").append(totalSurfer).append("\n");
        table.append("Date\t: ").append(bookDate).append("\n");
        table.append("Price\t: RM").append(cost).append("\n");

        return table.toString();
		//return("Name\t: "+name+"\nTheme Park\t: Waterpark"+"\nRider\t: "+totalRider+"\nSurfer\t: "+totalSurfer+"\nDate\t: "+bookDate+"\nPrice\t: RM"+cost);
	}
	
	public String viewTicketAmusementPark(int bookid, double cost) throws ClassNotFoundException, SQLException
	{
		String catName, bookDate = null, name = null;
		int total, totalAdult = 0, totalChild = 0;
		String sql = "SELECT C.CATEGORY_NAME, B.BOOKING_DATE, BC.TOTAL, CU.CUST_NAME FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID JOIN CUSTOMER CU ON B.CUST_ID = CU.CUST_ID WHERE B.BOOKING_ID = ?;";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1,bookid);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			catName = resultSet.getString(1);
	        bookDate = resultSet.getString(2);
	        total = resultSet.getInt(3);
	        name = resultSet.getString(4);
	        if(catName.equals("Amusement Park -Adult\r\n"))
	        {
	        	totalAdult = total;
	        }
	        else
	        {
	        	totalChild = total;
	        }
	        
		}
		conn.close();
		StringBuilder table = new StringBuilder();

        table.append("Name\t: ").append(name).append("\n");
        table.append("Theme Park\t: Waterpark\n");
        table.append("Child\t: ").append(totalChild).append("\n");
        table.append("Adult\t: ").append(totalAdult).append("\n");
        table.append("Date\t: ").append(bookDate).append("\n");
        table.append("Price\t: RM").append(cost).append("\n");

        return table.toString();
		//return("Name\t: "+name+"\nTheme Park\t: Waterpark"+"\nRider\t: "+totalRider+"\nSurfer\t: "+totalSurfer+"\nDate\t: "+bookDate+"\nPrice\t: RM"+cost);
	}
	
	public String viewProfile(int custID) throws ClassNotFoundException, SQLException {
	    String phone = "";
	    String name = "";
	    String username = ""; // Changed from Username to username (follow Java naming conventions)
	    String add = "";
	    String email = "";
	    int custid = 0;

	    String sql = "SELECT CUST_ID, CUST_PHONE, CUST_NAME, CUST_USERNAME, CUST_ADDRESS, CUST_EMAIL FROM CUSTOMER WHERE CUST_ID = ?";
	    
	    Connection conn = MyDatabase.doConnection();
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1,custID); // Replace with the actual username or parameter

	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        custid = resultSet.getInt(1);
	        phone = resultSet.getString(2);
	        name = resultSet.getString(3);
	        username = resultSet.getString(4);
	        add = resultSet.getString(5);
	        email = resultSet.getString(6);
	    }
	    conn.close();
	    return ("Customer ID\t: " + custid + " \n\nPhone Number: " + phone + "\n\nName\t:" + name + "\n\nUsername\t:"
	            + username + "\n\nAddress\t:" + add + " \n\nEmail\t:" + email);
	}
	
	public String viewProfileStaff(int staffId) throws ClassNotFoundException, SQLException
	{
		int staffid = 10;
		String icNo = null;
		String name = null;
		String phoneNo = null;
		String username = null;
		String staffType = null;

	    String sql = "SELECT STAFF_ID, IDENTIFICATION_NUMBER, STAFF_NAME, PHONE_NUMBER, USERNAME, STAFF_TYPE FROM STAFF WHERE STAFF_ID = ?";
	    
	    Connection conn = MyDatabase.doConnection();
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1,staffId); // Replace with the actual username or parameter

	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        staffid = resultSet.getInt(1);
	        icNo = resultSet.getString(2);
	        name = resultSet.getString(3);
	        phoneNo = resultSet.getString(4);
	        username = resultSet.getString(5);
	        staffType = resultSet.getString(6);
	    }
	    conn.close();
	    return ("Staff ID\t: " + staffid + " \n\nIdentification Number: " + icNo + "\n\nName\t:" + name + "\n\nPhone Number:"
	            + phoneNo + "\n\nUsername\t:" + username + " \n\nStaff Type\t:" + staffType);
	}
	
	public int updateProfile(Customer cust) throws SQLException, ClassNotFoundException
	{
		String sql = "UPDATE CUSTOMER SET CUST_USERNAME=?, CUST_PASSWORD=?, CUST_NAME=?, CUST_ADDRESS=?, CUST_EMAIL=? WHERE CUST_ID=?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, cust.getUsername());		
		preparedStatement.setString(2, cust.getPassword());
		preparedStatement.setString(3, cust.getName());
		preparedStatement.setString(4, cust.getAddress());
		preparedStatement.setString(5, cust.getEmail());
		preparedStatement.setInt(6, cust.getId());
		
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public int updateProfileStaff(Staff staff) throws ClassNotFoundException, SQLException
	{
		String sql = "UPDATE STAFF SET PHONE_NUMBER=?, USERNAME=?, PASSWORD=?, STAFF_TYPE=? WHERE STAFF_ID=?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, staff.getPhoneNo());		
		preparedStatement.setString(2, staff.getUsername());
		preparedStatement.setString(3, staff.getPassword());
		preparedStatement.setString(4, staff.getStaff_type());
		preparedStatement.setInt(5, staff.getStaffid());
		
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public int getCustId(String userName) throws ClassNotFoundException, SQLException
	{
		int custid = 0;
	
		String sql = "select CUST_ID from CUSTOMER where CUST_USERNAME = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, userName);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			custid = resultSet.getInt(1);
		}
		conn.close();
		return custid;
	}
	
	public int getStaffId(String username) throws ClassNotFoundException, SQLException
	{
		int staffid = 0;
		
		String sql = "select STAFF_ID from STAFF where USERNAME = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, username);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			staffid = resultSet.getInt(1);
		}
		conn.close();
		return staffid;
	}
	
	public Customer getCustomer(int custid) throws SQLException, ClassNotFoundException
	{
		Customer cust = new Customer();
		String sql = "select CUST_ID, CUST_PHONE, CUST_NAME, CUST_USERNAME, CUST_ADDRESS, CUST_EMAIL FROM CUSTOMER WHERE CUST_ID = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, custid);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			int CustID = resultSet.getInt(1);
			cust.setId(CustID);
	        String phone = resultSet.getString(2);
	        cust.setPhonenumber(phone);
	        String name = resultSet.getString(3);
	        cust.setName(name);
	        String username = resultSet.getString(4);
	        cust.setUsername(username);
	        String add = resultSet.getString(5);
	        cust.setAddress(add);
	        String email = resultSet.getString(6);
	        cust.setEmail(email);
		}
		conn.close();
		return cust;
	}
	
	public int insertBooking(Date date, int total, Customer cust) throws ClassNotFoundException, SQLException
	{
		int staffid = getRandomStaffID();
		String BookDate = String.valueOf(date.getYear()) + "-" + String.valueOf(date.getMonth()) + "-" + String.valueOf(date.getDay());
		String sql = "insert into BOOKING (BOOKING_DATE, TOTAL_BOOKING, CUST_ID,STAFF_ID) values (?,?,?,?)";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, BookDate);
		preparedStatement.setInt(2, total);
		preparedStatement.setInt(3, cust.getId());
		preparedStatement.setInt(4, staffid);
		
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public int getRandomStaffID() throws ClassNotFoundException, SQLException
	{
		int staffid = 0;
		String sql = "SELECT STAFF_ID FROM STAFF WHERE STAFF_TYPE!='Admin' AND STAFF_TYPE!='Manager' ORDER BY RAND() LIMIT 1";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			staffid = resultSet.getInt(1);
		}
		return staffid;
	}
	
	public int insertBookCat(int bookid, int catid, int total) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into BOOKING_CATEGORY (BOOKING_ID, CATEGORY_ID, TOTAL) values (?,?,?)";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, bookid);
		preparedStatement.setInt(2, catid);
		preparedStatement.setInt(3, total);
		
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public int getBookingID() throws ClassNotFoundException, SQLException
	{
		int bookid = 0;
		String sql = "select MAX(BOOKING_ID) AS bookid FROM BOOKING";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			bookid = resultSet.getInt(1);
		}
		return bookid;
	}
	
	public double getPrice(int catid) throws ClassNotFoundException, SQLException
	{
		double price=0;
		String sql = "select CATEGORY_PRICE FROM CATEGORY WHERE CATEGORY_ID = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, catid);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			price = resultSet.getDouble(1);
		}
		return price;
	}
	
	public int insertPayment(int bookingid, String method, double amount, Date date) throws ClassNotFoundException, SQLException
	{
		String BookDate = String.valueOf(date.getYear()) + "-" + String.valueOf(date.getMonth()) + "-" + String.valueOf(date.getDay());
		String sql = "insert into PAYMENT (AMOUNT, PAYMENT_DATE, PAYMENT_METHOD, BOOKING_ID) values (?,?,?,?)";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setDouble(1, amount);
		preparedStatement.setString(2, BookDate);
		preparedStatement.setString(3, method);
		preparedStatement.setInt(4, bookingid);
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public String getCatName(int bookid) throws ClassNotFoundException, SQLException
	{
		String catName = null;
		String sql = "SELECT CATEGORY_NAME FROM CATEGORY WHERE CATEGORY_ID IN (SELECT CATEGORY_ID FROM BOOKING_CATEGORY WHERE BOOKING_ID IN (SELECT BOOKING_ID FROM BOOKING WHERE BOOKING_ID = ?))";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, bookid);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			catName = resultSet.getString(1);
		}
		return catName;
	}
	
	public String viewPayment(Customer cust, int bookid, double cost)
	{
		String name = cust.getName();
		
		return("Name\t: "+name+"\nBooking ID\t: "+bookid+"\nTotal Price\t: "+cost);
	}
	
	public int deletePayment(int bookid) throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE FROM PAYMENT WHERE BOOKING_ID = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, bookid);
		int success = preparedStatement.executeUpdate();
		return success;
	}
	
	public int deleteBookCat(int bookid) throws ClassNotFoundException, SQLException
	{
		String sql1 = "DELETE FROM BOOKING_CATEGORY WHERE BOOKING_ID = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql1);
		preparedStatement.setInt(1, bookid);
		int success = preparedStatement.executeUpdate();
		
		return success;
	}
	
	public int deleteBooking(int bookid) throws SQLException, ClassNotFoundException
	{
		String sql2 = "DELETE FROM BOOKING WHERE BOOKING_ID = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql2);
		preparedStatement.setInt(1, bookid);
		
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public String[][] viewAllBooking(int custid) throws ClassNotFoundException, SQLException {
	    List<String[]> bookingList = new ArrayList<>();

	    String sql = "SELECT B.BOOKING_ID, C.CATEGORY_NAME, B.BOOKING_DATE, BC.TOTAL FROM BOOKING B JOIN BOOKING_CATEGORY BC ON B.BOOKING_ID = BC.BOOKING_ID JOIN CATEGORY C ON BC.CATEGORY_ID = C.CATEGORY_ID WHERE B.CUST_ID = ? ORDER BY B.BOOKING_ID ASC";

	    Connection conn = MyDatabase.doConnection();
	    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	        preparedStatement.setInt(1, custid);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                int bookid = resultSet.getInt(1);
	                String catName = resultSet.getString(2);
	                String bookdate = resultSet.getString(3);
	                int total = resultSet.getInt(4);
	                String[] bookingInfo = new String[]{String.valueOf(bookid), catName, bookdate,String.valueOf(total)};
	                bookingList.add(bookingInfo);
	            }
	        }
	    } finally {
	        conn.close();
	    }

	    // Convert the List<String[]> to a 2D array
	    String[][] bookingArray = new String[bookingList.size()][3];
	    for (int i = 0; i < bookingList.size(); i++) {
	        bookingArray[i] = bookingList.get(i);
	    }

	    return bookingArray;
	}

	
	public String viewTicketAvailability() throws ClassNotFoundException, SQLException {
	    StringBuilder table = new StringBuilder();
	    table.append("+-----------------+----------------------+--------------------------+\n");
	    table.append("|  CATEGORY_ID    |  CATEGORY_NAME       | CATEGORY_AVAILABILITY   |\n");
	    table.append("+-----------------+----------------------+--------------------------+\n");

	    String sql = "SELECT CATEGORY_ID, CATEGORY_NAME, CATEGORY_AVAILABILITY FROM CATEGORY";
	    Connection conn = MyDatabase.doConnection();
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();

	    while (resultSet.next()) {
	        int catid = resultSet.getInt("CATEGORY_ID");
	        String catName = resultSet.getString("CATEGORY_NAME");
	        int catStock = resultSet.getInt("CATEGORY_AVAILABILITY");

	        table.append(String.format("|%-17d | %-22s | %-23d |\n", catid, catName, catStock));
	    }

	    table.append("+-----------------+----------------------+--------------------------+\n");

	    // Close resources (ResultSet, PreparedStatement, and Connection)
	    resultSet.close();
	    preparedStatement.close();
	    conn.close();

	    return table.toString();
	}



	
	public int updateTicket(int catid, int catAvailability) throws ClassNotFoundException, SQLException
	{
		String sql = "UPDATE  CATEGORY SET CATEGORY_AVAILABILITY=? WHERE CATEGORY_ID=?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, catAvailability);		
		preparedStatement.setInt(2, catid);
		
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	public String salesReportPDF() throws ClassNotFoundException, SQLException {
	    StringBuilder output = new StringBuilder();
	    double maximum = 0.00;
	    String maxMonth = null;
	    int tSale = 0;
	    double[] totalSales = new double[12];
	    String m;
	    
	    output.append("\n\n");
	    for (int i = 0; i < 12; i++) {
	    	if (i == 0)

            {
                m = "JANUARY";
            }
            else if (i == 1)
            {
                m = "FEBRUARY";
            }
            else if (i == 2)
            {
                m = "MARCH";
            }
            else if (i == 3)
            {
                m = "APRIL";
            }
            else if (i == 4)
            {
                m = "MAY";
            }
            else if (i == 5)
            {
                m = "JUNE";
            }
            else if (i == 6)
            {
                m = "JULY";
            }
            else if (i == 7)
            {
                m = "AUGUST";
            }
            else if (i == 8)
            {
                m = "SEPTEMBER";
            }
            else if (i == 9)
            {
                m = "OCTOBER";
            }
            else if (i == 10)
            {
                m = "NOVEMBER";
            }
            else
            {
                m = "DECEMBER";
            }
	    	String sql = "SELECT SUM(BC.TOTAL)*C.CATEGORY_PRICE AS TOTAL_SALES FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID WHERE MONTH(B.BOOKING_DATE)=?";
	        Connection conn = MyDatabase.doConnection();
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, i + 1);        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while(resultSet.next()) {
	            totalSales[i] = resultSet.getDouble(1);
	        }

	        output.append("Total sales in ").append(m).append(" is ").append(totalSales[i]).append("\n");

	        if (i > 0 && totalSales[i] != 0) {
	            if ((totalSales[i] - totalSales[i - 1]) > 0) {
	                double salesPercent = ((totalSales[i] - totalSales[i - 1]) / totalSales[i - 1]) * 100;
	                output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	                output.append(String.format("The total sales in %s increases with %.2f%% than the month before\n", m, salesPercent));
	                output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	            } else {
	                double salesPercent = (((totalSales[i - 1] - totalSales[i]) / totalSales[i]) * 100);
	                output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	                output.append(String.format("The total sales in %s decreases with %.2f%% than the month before\n", m, salesPercent));
	                output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	            }
	        }

	        if (totalSales[i] > maximum) {
	            maximum = totalSales[i];
	            maxMonth = m;
	        }

	        tSale += totalSales[i];
	    }

	    double average = tSale / 12;
	    double percentMax;

	    if (maximum > average) {
	        percentMax = ((maximum - average) / average) * 100;
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	        output.append(String.format("Average Sales : RM%.2f\n", average));
	        output.append(String.format("Highest Total Sales : RM%.2f which is in : %s\n", maximum, maxMonth));
	        output.append(String.format("The maximum sales is higher by %.2f%% than the average sales\n", percentMax));
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	    } else {
	        percentMax = ((average - maximum) / maximum) * 100;
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	        output.append(String.format("Average Sales       : RM%.2f\n", average));
	        output.append(String.format("Highest Total Sales : RM%.2f which is in %s\n", maximum, maxMonth));
	        output.append(String.format("The maximum sales is lower by %.2f%% than the average sales\n", percentMax));
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	    }
	    // Return the complete output string
	    return output.toString();
	}
	
	public String salesReport() throws ClassNotFoundException, SQLException {
		StringBuilder output = new StringBuilder();
        int maximum = 0;
        String maxMonth = null;
        int tSale = 0;
        int[] totalSales = new int[12];
        String m;

	    for (int i = 1; i < 13; i++) {
	    	if (i == 1)

            {
                m = "JANUARY";
            }
            else if (i == 2)
            {
                m = "FEBRUARY";
            }
            else if (i == 3)
            {
                m = "MARCH";
            }
            else if (i == 4)
            {
                m = "APRIL";
            }
            else if (i == 5)
            {
                m = "MAY";
            }
            else if (i == 6)
            {
                m = "JUNE";
            }
            else if (i == 7)
            {
                m = "JULY";
            }
            else if (i == 8)
            {
                m = "AUGUST";
            }
            else if (i == 9)
            {
                m = "SEPTEMBER";
            }
            else if (i == 10)
            {
                m = "OCTOBER";
            }
            else if (i == 11)
            {
                m = "NOVEMBER";
            }
            else
            {
                m = "DECEMBER";
            }
			String sql = "SELECT SUM(BC.TOTAL)*C.CATEGORY_PRICE AS TOTAL_SALES FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID WHERE MONTH(B.BOOKING_DATE)=?";
			Connection conn = MyDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, i);		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				totalSales[i] = resultSet.getInt(1);
			}


	        output.append("Total sales in ").append(m).append(" is ").append(totalSales[i]).append("\n");

	        if (i > 1 && totalSales[i] != 0) {
	            if ((totalSales[i] - totalSales[i - 1]) > 0) {
	                double salesPercent = ((totalSales[i] - totalSales[i - 1]) / totalSales[i - 1]) * 100;
	                //output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	                output.append("sales in ").append(m).append(" increases with ").append(salesPercent).append("% than the month before\n");
	                //output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	                salesPercent = 0.00;
	            } else {
	                double salesPercent = (((totalSales[i - 1] - totalSales[i]) / totalSales[i]) * 100);
	                //output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	                output.append("sales in ").append(m).append(" decreases with ").append(salesPercent).append("% than the month before\n");
	                //output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	                salesPercent = 0.00;
	            }
	        }

	        if (totalSales[i] > maximum) {
	            maximum = totalSales[i];
	            maxMonth = m;
	        }

	        tSale += totalSales[i];
	    }

	    double average = tSale / 12;
	    double percentMax;

	    if (maximum > average) {
	        percentMax = ((maximum - average) / average) * 100;
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	        output.append("Average Sales : RM").append(average).append("\n");
	        output.append("Highest Total Sales : RM").append(maximum).append(" which is in : ").append(maxMonth).append("\n");
	        output.append("The maximum sales is higher by ").append(percentMax).append("% than the average sales\n");
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	    } else {
	        percentMax = ((average - maximum) / maximum) * 100;
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	        output.append("Average Sales       : RM").append(average).append("\n");
	        output.append("Highest Total Sales : RM").append(maximum).append(" which is in ").append(maxMonth).append("\n");
	        output.append("The maximum sales is lower by ").append(percentMax).append("% than the average sales\n");
	        output.append("==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==\n");
	    }
	   //writeSalesReportToFile(output.toString());
	    // Return the complete output string
	    return output.toString();
	}
	
	public void generateAndSaveSalesReport() throws IOException, DocumentException, ClassNotFoundException, SQLException {
		String salesReport = null;
		int i = 0;
		int[] totalSales = new int[4];
		String[] TotalSales = new String[4];
		String[] catName = new String[4];
        try {
            salesReport = salesReportPDF();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Specify the output file path for the PDF
        String pdfFilePath = "SalesReport.pdf";

        // Create a Document
        Document document = new Document();

        // Create a PdfWriter to write to the specified file
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
	    writer.setPageEvent(new Watermark()); // Add the watermark

        // Open the Document for writing
        document.open();
        
        Image logoImage = null;
		try {
			logoImage = Image.getInstance("C:\\Users\\Acer\\Downloads\\LOGO-OOP.png");
		} catch (BadElementException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    logoImage.setAlignment(Image.ALIGN_CENTER); // Align the logo to the center
	    logoImage.scaleToFit(200, 100); // Adjust dimensions as needed
	    document.add(logoImage);
	    
        // Create a centered title with underline
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("MONTHLY SALES REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add a line below the title
        Chunk line = new Chunk(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2));
        document.add(line);
        
        PdfPTable table = new PdfPTable(2); // 2 columns
        table.setWidthPercentage(100); // Set table width to 100% of page width

        // Add table headers (optional)
        PdfPCell cell = new PdfPCell(new Phrase("Category Name"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Total Sales"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        String sql = "SELECT C.CATEGORY_NAME, SUM(BC.TOTAL)*C.CATEGORY_PRICE AS TOTAL_SALES FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID GROUP BY C.CATEGORY_NAME";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			table.addCell(resultSet.getString(1));  // Access column values directly
            table.addCell(String.valueOf(resultSet.getInt(2)));
		}
		/*for (int k = 0; k < 4; k++) {
		    TotalSales[k] = String.valueOf(totalSales[k]);
		}
        // Fill the table with data
        // Replace placeholder data with your actual data
        for (int j = 0; j < 4; j++) {
            table.addCell(catName[j] + (j + 1));
            table.addCell(TotalSales[j] + (j + 1));
        }*/

        // Add the table to the PDF document
        document.add(table);

        // Add the sales report content to the PDF
        document.add(new Paragraph(salesReport));

        // Close the Document
        document.close();

        System.out.println("Sales report PDF generated successfully: " + pdfFilePath);
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            // Get the path to your PDF file
            File file = new File(pdfFilePath);

            try {
                desktop.browse(file.toURI());
            } catch (IOException e) {
                // Handle errors gracefully, e.g., display an error message
                System.err.println("Error opening PDF file: " + e.getMessage());
            }
        } else {
            // Handle cases where Desktop is not supported
            System.err.println("Desktop is not supported on this system.");
        }
    }

	
	/*private void writeSalesReportToFile(String salesReport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SalesReport.txt"))) {
            writer.write(salesReport);
            convertTextToPdf("SalesReport.txt", "SalesReport.pdf");
            System.out.println("Sales report written to SalesReport.txt");
        } catch (IOException e) {
            System.err.println("Error writing sales report to file: " + e.getMessage());
        }
    }
	
	public static void convertTextToPdf(String inputTextFilePath, String outputPdfFilePath) {
        Document document = new Document();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputTextFilePath));
            PdfWriter.getInstance(document, new FileOutputStream(outputPdfFilePath));

            document.open();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                document.add(new Paragraph(line));
            }

            bufferedReader.close();
            document.close();

            System.out.println("Conversion successful. PDF file created at: " + outputPdfFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
	
	public int checkAvailability(int catid) throws ClassNotFoundException, SQLException
	{
		int catStocks = 0;
		String sql = "SELECT CATEGORY_AVAILABILITY FROM CATEGORY WHERE CATEGORY_ID = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, catid);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			catStocks = resultSet.getInt(1);
		}
		return catStocks;
	}
	
	public int updateTicketAvailability(int catid, int catPurchased) throws ClassNotFoundException, SQLException
	{
		int catAvailability = checkAvailability(catid) - catPurchased;
		String sql = "UPDATE  CATEGORY SET CATEGORY_AVAILABILITY=? WHERE CATEGORY_ID=?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, catAvailability);		
		preparedStatement.setInt(2, catid);
		
		int success = preparedStatement.executeUpdate();
		
		conn.close();
		
		return success;
	}
	
	
	public int updateDeletedTicket(int bookid) throws ClassNotFoundException, SQLException {
	    int catid = 0;
	    int totalcat = 0;
	    int catAvailability = 0;
	    int success = 0;

	    String sql = "SELECT CATEGORY_ID, TOTAL FROM BOOKING_CATEGORY WHERE BOOKING_ID = ?";
	    Connection conn = MyDatabase.doConnection();
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, bookid);
	    ResultSet resultSet = preparedStatement.executeQuery();

	    while (resultSet.next()) {
	        catid = resultSet.getInt(1);
	        totalcat = resultSet.getInt(2);
	        catAvailability = checkAvailability(catid) + totalcat;

	        String updateSql = "UPDATE CATEGORY SET CATEGORY_AVAILABILITY=? WHERE CATEGORY_ID=?";
	        PreparedStatement preparedStatement1 = conn.prepareStatement(updateSql);
	        preparedStatement1.setInt(1, catAvailability);
	        preparedStatement1.setInt(2, catid);

	        success += preparedStatement1.executeUpdate();  
	        preparedStatement1.close();  
	    }

	    conn.close();
	    return success;
	}
	
	public double[] getSalesPercentage() throws ClassNotFoundException, SQLException
	{
		double[] TotalSales = new double[4];
		double[] Sales = new double[4];
		String[] catname = new String[4];
		double sum = 0;
		int i = 0;
	
		String sql = "SELECT SUM(BC.TOTAL)*C.CATEGORY_PRICE AS TOTAL_SALES FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID GROUP BY C.CATEGORY_NAME";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
			
		while(resultSet.next())
		{
			TotalSales[i] = resultSet.getInt(1);
			sum += TotalSales[i];
			i++;
		}
		System.out.println(sum);
		for(int j = 0; j<4; j++)
		{
			Sales[j] = (TotalSales[j] / sum) * 100;
		}
		
		
		//System.out.println(TotalSales[i]);
		return Sales;
	}
	
	public void ticket() throws ClassNotFoundException, SQLException
	{
		StringBuilder output = new StringBuilder();
		String info = null;
		int bookid = getBookingID();
		String catName = null;
		String bookDate = null;
		int total = 0;
		int sum = 0;
		String custName = null;
		String sql = "SELECT C.CATEGORY_NAME, B.BOOKING_DATE, BC.TOTAL, CU.CUST_NAME FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID JOIN CUSTOMER CU ON B.CUST_ID = CU.CUST_ID WHERE B.BOOKING_ID = ?";
	    Connection conn = MyDatabase.doConnection();
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1, bookid);
	    ResultSet resultSet = preparedStatement.executeQuery();

	    while (resultSet.next()) {
	    	
	        catName = resultSet.getString(1);	   
	        bookDate = resultSet.getString(2);	        
	        total = resultSet.getInt(3);	        
	        custName = resultSet.getString(4);
	        output.append("\n\nCategory Name : ").append(catName);
	        output.append("\nBooking date : ").append(bookDate);
	        output.append("\nTotal : ").append(total);
	        output.append("\nCustomer's Name : ").append(custName);
	        
	    }
	    info = output.toString();
        try {
			generateTicketPDF(info);
		} catch (FileNotFoundException | ClassNotFoundException | DocumentException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void generateTicketPDF(String info) throws FileNotFoundException, DocumentException, ClassNotFoundException, SQLException
	{
		String pdfFilePath = null;
       	
		// Specify the output file path for the PDF
	    pdfFilePath = "ETicket.pdf";

	    // Create a Document
	    Document document = new Document();

	    // Create a PdfWriter to write to the specified file
	    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
	    writer.setPageEvent(new Watermark()); // Add the watermark
	    
	    // Open the Document for writing
	    document.open();
	    
        
	    /*Image backgroundImage = null;
		try {
			backgroundImage = Image.getInstance("C:\\Users\\Acer\Downloads\themepark-watermark.png");
		} catch (BadElementException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    backgroundImage.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight()); // Scale to fit the page size
	    document.setPageSize(PageSize.A4); // Set the page size to A4 (adjust as needed)
	    document.add(backgroundImage);*/
	    Image logoImage = null;
		try {
			logoImage = Image.getInstance("C:\\Users\\Acer\\Downloads\\LOGO-OOP.png");
		} catch (BadElementException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    logoImage.setAlignment(Image.ALIGN_CENTER); // Align the logo to the center
	    logoImage.scaleToFit(200, 100); // Adjust dimensions as needed
	    document.add(logoImage);
	    
	    // Create a centered title with underline
	    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
	    Paragraph title = new Paragraph("E-Ticket", titleFont);
	    title.setAlignment(Element.ALIGN_CENTER);
	    document.add(title);

	    // Add a line below the title
	    Chunk line = new Chunk(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2));
	    document.add(line);
	        
	    PdfPTable table = new PdfPTable(2); // 2 columns
	    table.setWidthPercentage(100); // Set table width to 100% of page width

	        // Add table headers (optional)
	        /*PdfPCell cell = new PdfPCell(new Phrase("Category Name"));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Total Sales"));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(cell);
	        
	        String sql = "SELECT C.CATEGORY_NAME, SUM(BC.TOTAL)*C.CATEGORY_PRICE AS TOTAL_SALES FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID GROUP BY C.CATEGORY_NAME";
			Connection conn = MyDatabase.doConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				table.addCell(resultSet.getString(1));  // Access column values directly
	            table.addCell(String.valueOf(resultSet.getInt(2)));
			}*/
			/*for (int k = 0; k < 4; k++) {
			    TotalSales[k] = String.valueOf(totalSales[k]);
			}
	        // Fill the table with data
	        // Replace placeholder data with your actual data
	        for (int j = 0; j < 4; j++) {
	            table.addCell(catName[j] + (j + 1));
	            table.addCell(TotalSales[j] + (j + 1));
	        }*/

	        // Add the table to the PDF document
	     document.add(table);

	     // Add the sales report content to the PDF
	     document.add(new Paragraph(info));

	     // Close the Document
	     document.close();

	     System.out.println("E-Ticket PDF generated successfully: " + pdfFilePath);
	     if (Desktop.isDesktopSupported()) {
	         Desktop desktop = Desktop.getDesktop();

	         // Get the path to your PDF file
	         File file = new File(pdfFilePath);

	         try {
	             desktop.browse(file.toURI());
	         } catch (IOException e) {
	             // Handle errors gracefully, e.g., display an error message
	             System.err.println("Error opening PDF file: " + e.getMessage());
	         }
	     } else {
	         // Handle cases where Desktop is not supported
	         System.err.println("Desktop is not supported on this system.");
	     }
	}
	
	public Customer biodataCust(int custID) throws ClassNotFoundException, SQLException
	{
	    Customer cust = new Customer();
	    String sql = "SELECT CUST_ID, CUST_PHONE, CUST_NAME, CUST_USERNAME, CUST_ADDRESS, CUST_EMAIL FROM CUSTOMER WHERE CUST_ID = ?";
	    
	    Connection conn = MyDatabase.doConnection();
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	    preparedStatement.setInt(1,custID); // Replace with the actual username or parameter

	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
	        cust.setId(resultSet.getInt(1));
	        cust.setPhonenumber(resultSet.getString(2));
	        cust.setName(resultSet.getString(3));
	        cust.setUsername(resultSet.getString(4));
	        cust.setAddress(resultSet.getString(5));
	        cust.setEmail(resultSet.getString(6));
	    }
	    conn.close();
	    
	    return cust;
	}
	
	public static int[] getStocks() throws ClassNotFoundException, SQLException
	{
		int i = 0;
		int[] catStock = new int[4];
		String sql = "SELECT CATEGORY_AVAILABILITY FROM CATEGORY";
	    Connection conn = MyDatabase.doConnection();
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	    ResultSet resultSet = preparedStatement.executeQuery();

	    while (resultSet.next()) {
	        catStock[i] = resultSet.getInt(1);
	        i++;
	    }

	    // Close resources (ResultSet, PreparedStatement, and Connection)
	    resultSet.close();
	    preparedStatement.close();
	    conn.close();
	    
	    return catStock;
	}
	
	public static int[] getSales() throws ClassNotFoundException, SQLException
	{
		int[] sales = new int[4];
		int i = 0;
		String sql = "SELECT SUM(BC.TOTAL)*C.CATEGORY_PRICE AS TOTAL_SALES FROM CATEGORY C JOIN BOOKING_CATEGORY BC ON C.CATEGORY_ID = BC.CATEGORY_ID JOIN BOOKING B ON BC.BOOKING_ID = B.BOOKING_ID GROUP BY C.CATEGORY_NAME";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			sales[i] = resultSet.getInt(1);
			i++;
		}
		
		return sales;
	}
        
}
	

