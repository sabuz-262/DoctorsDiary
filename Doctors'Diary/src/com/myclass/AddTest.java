package com.myclass;

import java.util.ArrayList;

public class AddTest 
{
	
	public int patient_id;
	public int visit_id;
	public String Date;
	public ArrayList<String> testnamelist;
	
	public AddTest()
	{
		
	}
	public AddTest(int p_id,int v_id,String date,ArrayList<String>list)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		testnamelist=list;
		this.Date=date;
		
	}
	
	public AddTest(int id,int p_id,int v_id,String date,ArrayList<String>list)
	{
		this.Date=date;
		this.patient_id=p_id;
		this.visit_id=v_id;
		testnamelist=list;
		
	}
}
