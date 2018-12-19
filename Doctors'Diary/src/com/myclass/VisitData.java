package com.myclass;

public class VisitData {
	public int id;
	public int patient_id;
	public int visit_id;
	public String purpose_visit;
	public String Date;
	public String temperature;
	public int weight;
	public int bp_high;
	public int bp_low;
	public int heart_rate_min;
	public int heart_rate_max;
	
	public VisitData()
	{
		
	}
	
	public VisitData(int v_id)
	{
		this.visit_id=v_id;
		
	}
	
	public VisitData(int p_id,int v_id,String p_visit,String date,String temp,int w,int bph,int bpl,int hrmin,int hrmax)
	{
		
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.purpose_visit=p_visit;
		this.Date=date;
		this.temperature=temp;
		this.weight=w;
		this.bp_high=bph;
		this.bp_low=bpl;
		this.heart_rate_max=hrmax;
		this.heart_rate_min=hrmin;
		
	}
	public VisitData(int _id,int p_id,int v_id,String p_visit,String date,String temp,int w,int bph,int bpl,int hrmin,int hrmax)
	{
		this.id=_id;
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.purpose_visit=p_visit;
		this.Date=date;
		this.temperature=temp;
		this.weight=w;
		this.bp_high=bph;
		this.bp_low=bpl;
		this.heart_rate_max=hrmax;
		this.heart_rate_min=hrmin;
		
	}

}
