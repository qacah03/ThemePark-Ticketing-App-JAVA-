package model;

public class AmusementPark extends ThemePark{
	private int adult;
	private int child;
	
	public AmusementPark()
	{}
	
	public AmusementPark(String name, String identificationNo, boolean membership, Date date, Customer customer, Staff staff, int adult, int child)
	{
		super(name, identificationNo, membership, date, customer, staff);
		this.adult = adult;
		this.child = child;
	}	
	
	public int getAdult()
	{
		return adult;
	}
	
	public int child()
	{
		return child;
	}
	
	public void setNum(int adult, int child)
	{
		this.adult = adult;
		this.child = child;
	}
	
	public String toString()
	{
		return("\nName: " +super.getName() + "\nIdentification Number: " + super.getIdentificationNo() + "\nMembership Status: " + super.getMembership() + "\nThemePark: AmusementPark" + "\nBooking Date: " + super.getDate() + "\nNumber of Adult: " + adult + "\nNumber of Child: " + child);
	}
	
	public double calcFee(double totalPrice)
	{
		double cost=0;
		//= 35 * rider + 20 * surfer;
		
		
		if(super.getMembership()==true) 
		{
			System.out.println("HI");
			cost = totalPrice*0.8;
		}
		else
		{
			cost = totalPrice;
		}
		
		return cost;
	}

	@Override
	public double calcFee() {
		// TODO Auto-generated method stub
		return 0;
	}

}
