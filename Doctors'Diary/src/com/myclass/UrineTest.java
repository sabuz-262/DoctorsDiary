package com.myclass;

public class UrineTest 
{
	public int id;
	public int patient_id;
	public int visit_id;
	public String Date;
	public int Glucose;
	public int Red_Blood_Cells;
	public int White_Blood_Cells;
	public int Hemoglobin;
	public int pH;
	
	public UrineTest()
	{
		
	}
	
	public UrineTest(int id)
	{
		this.id=id;
	}
	
	public UrineTest(int p_id,int v_id,String date,int gl,int red,int white,int hem,int ph)
	{
		this.patient_id=p_id;
		this.Glucose=gl;
		this.visit_id=v_id;
		this.Date=date;
		this.Red_Blood_Cells=red;
		this.White_Blood_Cells=white;
		this.Hemoglobin=hem;
		this.pH=ph;
		
	}
	
	public UrineTest(int id,int p_id,int v_id,String date,int gl,int red,int white,int hem,int ph)
	{
		this.id=id;
		this.patient_id=p_id;
		this.Glucose=gl;
		this.visit_id=v_id;
		this.Date=date;
		this.Red_Blood_Cells=red;
		this.White_Blood_Cells=white;
		this.Hemoglobin=hem;
		this.pH=ph;
		
	}
}
