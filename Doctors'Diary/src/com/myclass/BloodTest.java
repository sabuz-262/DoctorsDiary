package com.myclass;

public class BloodTest {
	public int id;
	public int patient_id;
	public int visit_id;
	public String date;
	public int Hemoglobin;
	public int ESR;
	public int WBC;
	public int Neutrophilis;
	public int Lymphocytes;
	public int Monocytes;
	public int ASO;
	public int CRP;
	
	public BloodTest()
	{
		
	}
	
	public BloodTest(int _id)
	{
		this.id=_id;
	}
	
	public BloodTest(int p_id,int v_id,String date,int hem,int esr,int wbc,int neu,int lym,int mono,int aso,int crp)
	{
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.date=date;
		this.Hemoglobin=hem;
		this.ESR=esr;
		this.WBC=wbc;
		this.Neutrophilis=neu;
		this.Lymphocytes=lym;
		this.Monocytes=mono;
		this.ASO=aso;
		this.CRP=crp;
		
	}
	
	public BloodTest(int id,int p_id,int v_id,String date,int hem,int esr,int wbc,int neu,int lym,int mono,int aso,int crp)
	{
		this.id=id;
		this.patient_id=p_id;
		this.visit_id=v_id;
		this.date=date;
		this.Hemoglobin=hem;
		this.ESR=esr;
		this.WBC=wbc;
		this.Neutrophilis=neu;
		this.Lymphocytes=lym;
		this.Monocytes=mono;
		this.ASO=aso;
		this.CRP=crp;
		
	}
	
	

}
