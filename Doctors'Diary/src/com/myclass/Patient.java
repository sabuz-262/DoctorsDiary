package com.myclass;

import java.io.Serializable;

public class Patient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int Patient_id;
	public String Name;
	public String Email;
	public String Mobile;
	public int Age;
	public String birthdate;
	public String Sex;
	public String Blood_Group;
	public int Dur_month;
	public int Dur_year;
	public int visit_count;
	public String Added_Date;
	
	
	
	public Patient(int id)
	{
		this.Patient_id=id;
		
	}
	
	public Patient()
	{
		
	}
	
	public Patient(int v_count,String name,String email,String mobile,int age,String birthdate,String sex,String blood_group,int dur_month,int Dur_year,String added_date)
	{
		this.visit_count=v_count;
		this.Name=name;
		this.Email=email;
		this.Mobile=mobile;
		this.Age=age;
		this.birthdate=birthdate;
		this.Sex=sex;
		this.Blood_Group=blood_group;
		this.Dur_month=dur_month;
		this.Dur_year=Dur_year;
		this.Added_Date=added_date;
		
		
	}
	
	public Patient(int id,int v_count,String name,String email,String mobile,int age,String birthdate,String sex,String blood_group,int dur_month,int Dur_year,String added_date)
	{
		this.Patient_id=id;
		this.visit_count=v_count;
		this.Name=name;
		this.Email=email;
		this.Mobile=mobile;
		this.Age=age;
		this.birthdate=birthdate;
		this.Sex=sex;
		this.Blood_Group=blood_group;
		this.Dur_month=dur_month;
		this.Dur_year=Dur_year;
		this.Added_Date=added_date;
		
		
	}

}
