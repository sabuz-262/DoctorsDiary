package com.myclass;


public class AddConversation {
	public int id;
	public int patient_id;
	public int visit_id;
	public String Date;
	public String filename;
	
	public AddConversation()
	{
		
	}
	public AddConversation(int p_id,int v_id,String date,String filename)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.Date=date;
		this.filename=filename;
	}
	
	public AddConversation(int id,int p_id,int v_id,String date,String filename)
	{
		this.id=id;
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.Date=date;
		this.filename=filename;
	}

}
