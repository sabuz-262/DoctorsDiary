package com.myclass;

import java.util.ArrayList;

public class AddMedicine 
{
	public int patient_id;
	public int visit_id;
	public String Date;
	public ArrayList<String> medicinelist;
	
	public AddMedicine()
	{
		
	}
	public AddMedicine(int p_id,int v_id,String date,ArrayList<String>list)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		medicinelist=list;
		this.Date=date;
	}
	

}
