package com.myclass;

import java.io.Serializable;

public class Appoinment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int Appoinment_id;
	public String Name;
	public String Email;
	public String Mobile;
	public String Time;
	public String Date;
	
	
	
	public Appoinment(int id)
	{
		this.Appoinment_id=id;
		
	}
	
	public Appoinment()
	{
		
	}
	
	public Appoinment(String name,String email,String mobile,String time,String date)
	{
		this.Name=name;
		this.Email=email;
		this.Mobile=mobile;
		this.Time=time;
		this.Date=date;
		
		
		
	}
	
	
	public Appoinment(int id,String name,String email,String mobile,String time,String date)
	{
		this.Appoinment_id=id;
		this.Name=name;
		this.Email=email;
		this.Mobile=mobile;
		this.Time=time;
		this.Date=date;
		
		
		
	}

}
