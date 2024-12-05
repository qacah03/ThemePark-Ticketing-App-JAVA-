package model;

public abstract class ThemePark {
	private String name;
	private String identificationNo;
	private boolean membership;
	private double totalPrice;
	private Date date;
	private Customer customer;
	private Staff staff;
	
	public ThemePark()
	{}
	
	public ThemePark(String name, String identificationNo, boolean membership, Date date, Customer customer, Staff staff)
	{
		this.name = name;
		this.identificationNo = identificationNo;
		this.membership = membership;
		this.date = date;
		this.customer = customer;
		this.staff = staff;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getIdentificationNo()
	{
		return identificationNo;
	}
	
	public boolean getMembership()
	{
		return membership;
	}
	
	public double getTotalPrice()
	{
		return totalPrice;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	/*public void setDate(Date date)
	{
		this.date = date;
	}*/
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public Staff getStaff()
	{
		return staff;
	}
	
	/*public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}*/
	
	public abstract String toString();
	
	public abstract double calcFee();

}
