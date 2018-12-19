package com.datashow;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test3.R;
import com.myclass.Message;
import com.myclass.Patient;

import database.com.DatabaseHelper;







public class MessageListofPatientFragment extends Fragment {
	
	ListView markList;
	
  DatabaseHelper database;
	Patient p;
   
    

   ArrayList<Message>messagelist;
    
    
    
    private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;

	
	
	
	class RowData {
		
		protected String send_time;
		protected String send_date;
		protected String message;
		

		RowData(String name_, String mobile_, String time_) {
			
			send_time = name_;
			send_date = mobile_;
			message =time_;
			
		}

		@Override
		public String toString() {
			return send_time + " " + send_date + " " + message;
		}
	}
	
	
	
	class CustomAdapter extends ArrayAdapter<RowData> {

		public CustomAdapter(Context context, int resource, int textViewResourceId,
				List<RowData> objects) {

			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			TextView name = null;
			TextView mobile = null;
			TextView time = null;
			RowData rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.messagedetails, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			name = holder.getname();
			name.setText(rowData.send_time);
			mobile = holder.getmobile();
			mobile.setText(rowData.send_date);
			
             time=holder.getTime();
             time.setText(rowData.message);
			
			return convertView;
		}
	}
	
	
	
	
	

		 class ViewHolder {
			private View mRow;
			private TextView send_time = null;
			private TextView send_date = null;
			private TextView message = null;

			public ViewHolder(View row) {
				mRow = row;
			}

			public TextView getname() {
				if (null == send_time) {
					send_time = (TextView) mRow.findViewById(R.id.timetextview);
				}
				return send_time;
			}

			public TextView getmobile() {
				if (null == send_date) {
					send_date = (TextView) mRow.findViewById(R.id.datetextview);
				}
				return send_date;
			}

			public TextView getTime() {
				if (null == message) {
					message = (TextView) mRow.findViewById(R.id.messagetextview);
				}
				return message;
			}
		}
	
	
	
	
	
	
	
    
   

    public MessageListofPatientFragment() {
    }
    
    
    
    public void Print_Message(String msg)
	{
		Log.d("MSG MLP", msg);
		
	}
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            
       
        p =(Patient) getArguments().getSerializable("Patient");
        messagelist=new ArrayList<Message>();
        
        database=new DatabaseHelper(getActivity());
        Print_Message(p.Name+" "+p.Patient_id);
        
    }
    
    

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
         final View rootView = inflater.inflate(R.layout.messagelist, container, false);
         
         
         TextView patientname=(TextView)rootView.findViewById(R.id.patientname);
         patientname.setText(p.Name+" "+"Message List");
         
         Button backbutton=(Button)rootView.findViewById(R.id.backbutton);
         backbutton.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				
 				 MessageFragment fragment = new MessageFragment();
                 getFragmentManager().beginTransaction().replace(R.id.item_detail_container,fragment)
                         .commit();  
 			}
         });
         
         Context con=getActivity().getBaseContext();
         
         mInflater = (LayoutInflater) con.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
 		 data = new Vector<RowData>();
 		
 		
 		
 		
 		
 		
 		 Cursor c=database.getMessagetListByPatientId(p.Patient_id);
		   
		   getActivity().startManagingCursor(c);
		   if (c != null) 
		   {
			   c.moveToFirst(); 
			   while (!c.isAfterLast()) {
				   int id=c.getInt(0);
				  
				   int p_id=c.getInt(1);
				   	     	   
				   String time = c.getString(2);
				   
				   String date = c.getString(3);
				   
				   String message = c.getString(4);
				   Print_Message(message);
				   
				   Message M=new Message(id,p_id, message, time, date);
				   
				   
				   	   messagelist.add(M);
				   
				   
				   try {
		 				rd = new RowData(time, date,message);
		 			} catch (ParseException e) {
		 				e.printStackTrace();
		 			}
		 			data.add(rd);
				
				   c.moveToNext();
			   
			   }
			   
		   
		   }
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		

 		CustomAdapter adapter = new CustomAdapter(getActivity().getBaseContext(), R.layout.messagedetails,
 				R.id.timetextview, data);
         
         
         markList = (ListView)rootView.findViewById(R.id.msglist);
         markList.setAdapter(adapter);
         
         
         
        
  		
         
         
         
       
         
         
         
         
        return rootView;
    	
    	
  
    }
    
    public void Show()
    {
    	
    	
    	Log.d("MSG","In show Funtion");
    	
    }
    
    
   
    
   

}
