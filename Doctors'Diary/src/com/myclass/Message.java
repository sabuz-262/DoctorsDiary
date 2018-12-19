package com.myclass;

public class Message {
	public int message_id;
	public int pateint_id;
	public String time;
	public String date;
	public String text;
	
	public Message()
	{
		
	}
	
	public Message(int _id)
	{
		this.message_id=_id;
		
	}
	
	
	public Message(int _id,int p_id,String msg,String time,String date)
	{
		this.message_id=_id;
		this.pateint_id=p_id;
		this.text=msg;
		this.time=time;
		this.date=date;
		
	}
	public Message(int p_id,String msg,String time,String date)
	{
		
		this.pateint_id=p_id;
		this.text=msg;
		this.time=time;
		this.date=date;
		
	}

}
