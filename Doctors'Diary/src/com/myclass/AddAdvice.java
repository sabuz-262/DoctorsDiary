package com.myclass;

import java.util.ArrayList;

public class AddAdvice 
{
	public int patient_id;
	public int visit_id;
	public String Date;
	public ArrayList<String> advicelist;
	
	public AddAdvice()
	{
		
	}
	public AddAdvice(int p_id,int v_id,String date,ArrayList<String>list)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		advicelist=list;
		this.Date=date;
		
	}
	

}
