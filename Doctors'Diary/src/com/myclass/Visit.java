package com.myclass;


public class Visit {
	public int id;
	public int visit_id;
	public int patient_id;
	
	public String date;
	
	
	public Visit()
	{
		
	}
	public Visit(int id,int v_id,int p_id,String date)
	{
		this.id=id;
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.date=date;
		
	}

	public Visit(int v_id,int p_id,String date)
	{
		
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.date=date;
		
	}

}
