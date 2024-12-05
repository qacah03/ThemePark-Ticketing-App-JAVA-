package model;

public class Staff {
	private int staffid;
	private String icNo;
	private String name;
	private String phoneNo;
	private String username;
	private String password;
	private String staff_type;
	
	public Staff()
	{
		
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public String getIcNo() {
		return icNo;
	}

	public void setIcNo(String icNo) {
		this.icNo = icNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaff_type() {
		return staff_type;
	}

	public void setStaff_type(String staff_type) {
		this.staff_type = staff_type;
	}
	
	
}
