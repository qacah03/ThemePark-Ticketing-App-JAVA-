package model;

public class WaterPark extends ThemePark{
	private int rider;
	private int surfer;
	
	public WaterPark()
	{}
	
	public WaterPark(String name, String identificationNo, boolean membership, Date date, Customer customer,Staff staff, int rider, int surfer)
	{
		super(name, identificationNo, membership, date, customer, staff);
		this.rider = rider;
		this.surfer = surfer;
	}
	
	public int getRider()
	{
		return rider;
	}
	
	public int getSurfer()
	{
		return surfer;
	}
	
	public void setNum(int rider, int surfer)
	{
		this.rider = rider;
		this.surfer = surfer;
	}
	
	public String toString()
	{
		return("\nName: "+super.getName() + "\nIdentification Number: " + super.getIdentificationNo() + "\nMembership Status: " + super.getMembership() + "\nThemePark: WaterPark" + "\nBooking Date: " + super.getDate() + "\nNumber of Rider: " + rider + "\nNumber of Surfer: " + surfer);
	}
	
	public double calcFee(double price)
	{
		double cost=0;
		//= 35 * rider + 20 * surfer;
		
		
		if(super.getMembership()==true) 
		{
			System.out.println("HI");
			cost = price*0.8;
		}
		else
		{
			cost = price;
		}
		
		return cost;
	}

	@Override
	public double calcFee() {
		// TODO Auto-generated method stub
		return 0;
	}

}
