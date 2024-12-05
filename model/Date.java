package model;

public class Date {
	private int day; 
	  private int month; 
	  private int year; 
	   
	  public Date() { 
	     
	    this.day = 0; 
	    this.month = 0; 
	    this.year = 0; 
	  } 
	   
	  public Date(int day, int month, int year) { 
	     
	    this.day = day; 
	    this.month = month; 
	    this.year = year; 
	  } 
	   
	  public int getDay() { 
	     
	    return day; 
	  } 
	   
	  public int getMonth() { 
	     
	    return month; 
	  } 
	   
	  public int getYear() { 
	     
	    return year; 
	  } 
	   
	  public String toString() { 
	     
	    return String.format("%d/%d/%d", day, month, year); 
	  } 

}
