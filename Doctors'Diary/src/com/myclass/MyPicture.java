package com.myclass;


public class MyPicture {
	public int id;
	public int patient_id;
	public int visit_id;
	public String Date;
	public String comment;
	public String filename;
	
	public MyPicture()
	{
		
	}
	public MyPicture(int p_id,int v_id,String date,String comment,String filename)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.Date=date;
		this.comment=comment;
		this.filename=filename;
	}
	
	public MyPicture(int id,int p_id,int v_id,String date,String comment,String filename)
	{
		this.id=id;
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.Date=date;
		this.comment=comment;
		this.filename=filename;
	}

}
