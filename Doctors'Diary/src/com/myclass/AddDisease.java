package com.myclass;

import java.util.ArrayList;

public class AddDisease {
	public int patient_id;
	public int visit_id;
	public String date;
	public String diseasename;
	public ArrayList<String> diseaselist;
	
	public AddDisease()
	{
		
	}
	public AddDisease(int p_id,int v_id,String date,ArrayList<String>dlist)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.date=date;
		this.diseaselist=dlist;
	}
	
	
	public AddDisease(int p_id,int v_id,String date,String dname)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.date=date;
		this.diseasename=dname;
	}

}
