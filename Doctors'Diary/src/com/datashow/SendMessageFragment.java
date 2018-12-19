package com.datashow;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test3.R;
import com.myclass.Message;
import com.myclass.Patient;

import database.com.DatabaseHelper;


public class SendMessageFragment extends Fragment {
	
	
	DatabaseHelper database;
    
	 ArrayList<Patient>patientlist;
	 
	 public void Print_Message(String msg)
		{
			Log.d("MSG SMF", msg);
			
		}
	 
	 void Toast_Show(String msg)
	    {
	    	Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG).show();
	    	
	    }

    public SendMessageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            
       
        database=new DatabaseHelper(this.getActivity());
        patientlist=new ArrayList<Patient>();
        
    }
    
    

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
         final View rootView = inflater.inflate(R.layout.message, container, false);
         
         
         final Spinner  patientlistspinner = (Spinner) rootView.findViewById(R.id.patientlistspinner);
 		
 		
 		
 		ArrayAdapter <CharSequence> adapter1 =
 				  new ArrayAdapter <CharSequence> (getActivity(), android.R.layout.simple_spinner_item );
 				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 				
 				adapter1.add("Select A Patient");
 				
 				Cursor c=database.getPatientList();
 	 		   
 	 		   getActivity().startManagingCursor(c);
 	 		   if (c != null) 
 	 		   {
 	 			    c.moveToFirst(); 
 	 			     while (!c.isAfterLast()) {
 	 				   int id=c.getInt(0);
 	 				  
 	 				   int visit_count=c.getInt(1);
 	 				   	     	   
 	 				   String name = c.getString(2);
 	 				   
 	 				   String email = c.getString(3);
 	 				   
 	 				   String mobile = c.getString(4);
 	 				   
 	 				   int age=c.getInt(5);   
 	 				   
 	 				 String birtdate = c.getString(6);
 					String sex = c.getString(7);

 					String blood_group = c.getString(8);

 					String date = c.getString(9);

 					int dur_month = c.getInt(10);
 					int dur_year = c.getInt(11);
 	 				   
 	 				   Patient p=new Patient(id, visit_count, name, email, mobile, age,birtdate, sex, blood_group, dur_month, dur_year,date);
 	 				    
 	 				   patientlist.add(p);
 	 				   	   
 	 				   adapter1.add(name+" "+mobile);
 	 				   
 	 				  
 	 				
 	 				   c.moveToNext();
 	 			   
 	 			   }
 	 			   
 	 		   
 	 		   }
 				
 				
 				patientlistspinner.setAdapter(adapter1);
         
         
 				Button sendbutton=(Button)rootView.findViewById(R.id.sendbutton);
 				
 				sendbutton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						int tempid= patientlistspinner.getSelectedItemPosition();
						if(tempid==0)
						{
							Toast_Show("Select A Patient");
							
						}
						else
						{
						Patient p=patientlist.get(tempid-1);
						int patient_id=patientlist.get(tempid-1).Patient_id;
						EditText msg=(EditText)rootView.findViewById(R.id.message_text);
						String message=msg.getText().toString();
						 if(message.equals(""))
						 {
							 Toast_Show("Write Message");
						 }
						 else
						 {
						Calendar c = Calendar.getInstance();

						SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
						String date = df.format(c.getTime());
						
						
						int hour=c.get(Calendar.HOUR);
						int min=c.get(Calendar.MINUTE);
						
						String time=String.valueOf(hour)+":"+String.valueOf(min);
						
					 Message msg_text = new Message(patient_id,message,time,date);
					 Send_Message(msg_text);
					 
					 Print_Message(message+" "+patient_id);
					 
					 
					 String phoneNo = p.Mobile.toString();
					  String sms = msg_text.text;
		 
					  try {
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(phoneNo, null, sms, null, null);
						Toast_Show("Successfully Send Message");
						
					  } catch (Exception e) {
						  Toast_Show("Can not  Send Message");
						e.printStackTrace();
					  }
					 
					 
						}
						}
					 
					 
					 
						
					}
				});
         
         
         
        return rootView;
    	
    	
  
    }
    
    public void Send_Message(Message m)
    {
    	database.addMessage(m);
    	
    }
    
    public void Show()
    {
    	
    	
    	Log.d("MSG","In show Funtion");
    	
    }
    
    
   
    
   

}
