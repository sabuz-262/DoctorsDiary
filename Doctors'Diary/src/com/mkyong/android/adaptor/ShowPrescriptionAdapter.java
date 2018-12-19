package com.mkyong.android.adaptor;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.test3.R;
import com.myclass.Patient;
import com.myclass.Visit;

import database.com.DatabaseHelper;

public class ShowPrescriptionAdapter extends BaseExpandableListAdapter {
	 FragmentActivity Act;
	  
	private Context myContext;
	 
	private  String[] values;
	
/*	 private static final String[] dname = new String[] {
			"Select A desiese","Achondroplasia", "Anosmia", "Carbon monoxide poisoning", "Cholera", "Ear infection","Hepatitis A","Hepatitis B"
			,"Hepatitis C","Hepatitis D","HIV","Influenza","Diphtheria"
		};*/
		
	 Patient p;
	  
	 ArrayList<Visit>visitlist;
	  DatabaseHelper database;
	  
	  TextView diseasenametextview;
	  TextView medicinenametextview;
	  TextView testnametextview;
	  TextView advicetextview;
	  
  boolean flag=false;
	  
  
  public void Print_Message(String msg)
	{
		Log.d("MSG SPA", msg);
		
	}
  
   public void Show_Disease()
   {
	   String diseasename="";
	   if(visitlist.size()!=0)
	   {
	   int v_id=visitlist.get(visitlist.size()-1).visit_id;
		Cursor c=database.GetDiseaseListByPatientIdandVisitid(p.Patient_id, v_id);
		   
		   Act.startManagingCursor(c);
		   if (c != null) 
		   {
			   c.moveToFirst(); 
			   int i=1;
			   while (!c.isAfterLast()) {
				   
				   String name = c.getString(2);		 				   
				   
				  diseasename=diseasename+i+". "+name+"\n";
				   				   
				   i++;
				
				   c.moveToNext();
			   
			   }
			   
		   
		   }
	   }
	   
	   
		
	   diseasenametextview.setText(diseasename);
	   
   }
  
	  public class YourDiseaseDateItemSelectedListener implements OnItemSelectedListener {
				   

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(!flag)
		    	{
		    		flag=true;
		    	}
		    	else if(pos>=1)
		    	{
		    		String diseasename="";
		    		int v_id=visitlist.get(pos-1).visit_id;
		    		Cursor c=database.GetDiseaseListByPatientIdandVisitid(p.Patient_id, v_id);
			 		   
			 		   Act.startManagingCursor(c);
			 		   if (c != null) 
			 		   {
			 			   c.moveToFirst(); 
			 			   int i=1;
			 			   while (!c.isAfterLast()) {
			 				   
			 				   String name = c.getString(2);		 				   
			 				   
			 				  diseasename=diseasename+i+". "+name+"\n";
			 				   				   
			 				   i++;
			 				
			 				   c.moveToNext();
			 			   
			 			   }
			 			   
			 		   
			 		   }
			 		
					
		    		 diseasenametextview.setText(diseasename);

		    	}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		}
	    
	  public void Show_Medicine()
	  {
		  String diseasename="";
		  if(visitlist.size()!=0)
		  {
  		int v_id=visitlist.get(visitlist.size()-1).visit_id;
  		Cursor c=database.GetMedicineListByPatientIdandVisitid(p.Patient_id, v_id);
	 		   
	 		   Act.startManagingCursor(c);
	 		   if (c != null) 
	 		   {
	 			   c.moveToFirst(); 
	 			   int i=1;
	 			   while (!c.isAfterLast()) {
	 				   
	 				   String name = c.getString(2);		 				   
	 				   
	 				  diseasename=diseasename+i+". "+name+"\n";
	 				   				   
	 				   i++;
	 				
	 				   c.moveToNext();
	 			   
	 			   }
	 			   
	 		   
	 		   }
		  }
	 		
			
  		 medicinenametextview.setText(diseasename);
		  
	  }
	  public class YourMedicineDateItemSelectedListener implements OnItemSelectedListener {
		   

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(!flag)
		    	{
		    		flag=true;
		    	}
		    	else if(pos>=1)
		    	{
		    		String diseasename="";
		    		int v_id=visitlist.get(pos-1).visit_id;
		    		Cursor c=database.GetMedicineListByPatientIdandVisitid(p.Patient_id, v_id);
			 		   
			 		   Act.startManagingCursor(c);
			 		   if (c != null) 
			 		   {
			 			   c.moveToFirst(); 
			 			   int i=1;
			 			   while (!c.isAfterLast()) {
			 				   
			 				   String name = c.getString(2);		 				   
			 				   
			 				  diseasename=diseasename+i+". "+name+"\n";
			 				   				   
			 				   i++;
			 				
			 				   c.moveToNext();
			 			   
			 			   }
			 			   
			 		   
			 		   }
			 		
					
		    		 medicinenametextview.setText(diseasename);

		    	}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		}
	  
	  public void Show_Test()
	  {
		  String diseasename="";
		  if(visitlist.size()!=0)
		  {
  		int v_id=visitlist.get(visitlist.size()-1).visit_id;
  		Cursor c=database.GetTestListByPatientIdandVisitid(p.Patient_id, v_id);
	 		   
	 		   Act.startManagingCursor(c);
	 		   if (c != null) 
	 		   {
	 			   c.moveToFirst(); 
	 			   int i=1;
	 			   while (!c.isAfterLast()) {
	 				   
	 				   String name = c.getString(2);		 				   
	 				   
	 				  diseasename=diseasename+i+". "+name+"\n";
	 				   				   
	 				   i++;
	 				
	 				   c.moveToNext();
	 			   
	 			   }
	 			   
	 		   
	 		   }
		  }
	 		
			
  		 testnametextview.setText(diseasename);
	  }
	  
	  public class YourTestDateItemSelectedListener implements OnItemSelectedListener {
		   

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(!flag)
		    	{
		    		flag=true;
		    	}
		    	else if(pos>=1)
		    	{
		    		String diseasename="";
		    		int v_id=visitlist.get(pos-1).visit_id;
		    		Cursor c=database.GetTestListByPatientIdandVisitid(p.Patient_id, v_id);
			 		   
			 		   Act.startManagingCursor(c);
			 		   if (c != null) 
			 		   {
			 			   c.moveToFirst(); 
			 			   int i=1;
			 			   while (!c.isAfterLast()) {
			 				   
			 				   String name = c.getString(2);		 				   
			 				   
			 				  diseasename=diseasename+i+". "+name+"\n";
			 				   				   
			 				   i++;
			 				
			 				   c.moveToNext();
			 			   
			 			   }
			 			   
			 		   
			 		   }
			 		
					
		    		 testnametextview.setText(diseasename);

		    	}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		}
	  
	  
	  public void Show_Advice()
	  {
		  String diseasename="";
		  if(visitlist.size()!=0)
		  {
  		int v_id=visitlist.get(visitlist.size()-1).visit_id;
  		Cursor c=database.GetAdviceListByPatientIdandVisitid(p.Patient_id, v_id);
	 		   
	 		   Act.startManagingCursor(c);
	 		   if (c != null) 
	 		   {
	 			   c.moveToFirst(); 
	 			   int i=1;
	 			   while (!c.isAfterLast()) {
	 				   
	 				   String name = c.getString(2);		 				   
	 				   
	 				  diseasename=diseasename+i+". "+name+"\n";
	 				   				   
	 				   i++;
	 				
	 				   c.moveToNext();
	 			   
	 			   }
	 			   
	 		   
	 		   }
		  }
	 		
			
  		 advicetextview.setText(diseasename);
		  
	  }
	  public class YourAdviceDateItemSelectedListener implements OnItemSelectedListener {
		   

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(!flag)
		    	{
		    		flag=true;
		    	}
		    	else if(pos>=1)
		    	{
		    		String diseasename="";
		    		int v_id=visitlist.get(pos-1).visit_id;
		    		Cursor c=database.GetAdviceListByPatientIdandVisitid(p.Patient_id, v_id);
			 		   
			 		   Act.startManagingCursor(c);
			 		   if (c != null) 
			 		   {
			 			   c.moveToFirst(); 
			 			   int i=1;
			 			   while (!c.isAfterLast()) {
			 				   
			 				   String name = c.getString(2);		 				   
			 				   
			 				  diseasename=diseasename+i+". "+name+"\n";
			 				   				   
			 				   i++;
			 				
			 				   c.moveToNext();
			 			   
			 			   }
			 			   
			 		   
			 		   }
			 		
					
		    		 advicetextview.setText(diseasename);

		    	}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		}
	    
	  public ShowPrescriptionAdapter(Context context,String[] value,FragmentActivity act,Patient p) {
		  super();
	   this.myContext = context;
	   this.values=value;
	   this.Act=act;
	   database=new DatabaseHelper(Act);
	   this.p=p;
	   
	  }
	  @Override
	  public Object getChild(int groupPosition, int childPosition) {
	   return null;
	  }
	 
	  @Override
	  public long getChildId(int groupPosition, int childPosition) {
		  return childPosition;
	  }
	 
	 
	  @Override
	  public int getChildrenCount(int groupPosition) {
	   return 1;
	  }
	 
	  @Override
	  public Object getGroup(int groupPosition) {
	   return null;
	  }
	 
	  @Override
	  public int getGroupCount() {
	   return 4;
	  }
	 
	  @Override
	  public long getGroupId(int groupPosition) {
	   return groupPosition;
	  }
	  
	  
	  
	 
	  @Override
	  public boolean hasStableIds() {
	   return true;
	  }
	 
	  @Override
	  public boolean isChildSelectable(int position, int childPosition) {
	   return true;
	  }
	@Override
	public View getChildView(int position, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(position==0)
		{
			visitlist=new ArrayList<Visit>();
		LayoutInflater inflater = (LayoutInflater) myContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		 View rootView = inflater.inflate(R.layout.showdisease, parent, false);
        
		  diseasenametextview=(TextView)rootView.findViewById(R.id.showdiseasenametextview);
		 
		 ArrayAdapter <CharSequence> dateadapter =
				  new ArrayAdapter <CharSequence> (Act, android.R.layout.simple_spinner_item );
				dateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				dateadapter.add("Select A Visit");
		 
		 
				Cursor c=database.GetVisitListForDiseaseListByPatientId(p.Patient_id);
		 		   
		 		   Act.startManagingCursor(c);
		 		   if (c != null) 
		 		   {
		 			   c.moveToFirst(); 
		 			   while (!c.isAfterLast()) {
		 				   int id=c.getInt(0);
		 				   int visit_count=c.getInt(1);
		 				   int p_id=c.getInt(2);
		 				   String date = c.getString(3);		 				   
		 				   
		 				   Visit vd=new Visit(id, visit_count, p_id, date);
		 				   
		 				   visitlist.add(vd);
		 				   
		 				   if(date!=null)
		 				   {
		 				   dateadapter.add(date);	
		 				   }
		 				   
		 				
		 				   c.moveToNext();
		 			   
		 			   }
		 			   
		 		   
		 		   }
		 		
				
				
				
				Spinner visitdatespinner=(Spinner)rootView.findViewById(R.id.visitdatespinner);
				visitdatespinner.setAdapter(dateadapter);
				visitdatespinner.setOnItemSelectedListener(new YourDiseaseDateItemSelectedListener());
		 
				Show_Disease();
       
       return rootView;
		}
		
		else if(position==1)
		{
			visitlist=new ArrayList<Visit>();
			LayoutInflater inflater = (LayoutInflater) myContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			
			 View rootView = inflater.inflate(R.layout.showmedicine, parent, false);
	        
			  medicinenametextview=(TextView)rootView.findViewById(R.id.showdiseasenametextview);
			 
			 ArrayAdapter <CharSequence> dateadapter =
					  new ArrayAdapter <CharSequence> (Act, android.R.layout.simple_spinner_item );
					dateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					
					dateadapter.add("Select A Visit");
			 
			 
					Cursor c=database.GetVisitListForMedicineListByPatientId(p.Patient_id);
			 		   
			 		   Act.startManagingCursor(c);
			 		   if (c != null) 
			 		   {
			 			   c.moveToFirst(); 
			 			   while (!c.isAfterLast()) {
			 				 
			 				  int id=c.getInt(0);
			 				   int visit_count=c.getInt(1);
			 				   int p_id=c.getInt(2);
			 				   String date = c.getString(3);		 				   
			 				   
			 				   Visit vd=new Visit(id, visit_count, p_id, date);	 				   
			 				   
			 				   
			 				 
			 				   
			 				   visitlist.add(vd);
			 				   
			 				  if(date!=null)
			 				   {
			 				   dateadapter.add(date);
			 				   Print_Message(date);
			 				   }				   
			 				  else
			 				  {
			 					 Print_Message("DATE IS NULL");
			 				  }
			 				
			 				   c.moveToNext();
			 			   
			 			   }
			 			   
			 		   
			 		   }
			 		
					
					
					
					Spinner visitdatespinner=(Spinner)rootView.findViewById(R.id.visitdatespinner);
					visitdatespinner.setAdapter(dateadapter);
					visitdatespinner.setOnItemSelectedListener(new YourMedicineDateItemSelectedListener());
			 
	       Show_Medicine();
	       
	       return rootView;
		}
		
		
		else if(position==2)
		{
			visitlist=new ArrayList<Visit>();
			LayoutInflater inflater = (LayoutInflater) myContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			
			 View rootView = inflater.inflate(R.layout.showtest, parent, false);
	        
			  testnametextview=(TextView)rootView.findViewById(R.id.showdiseasenametextview);
			 
			 ArrayAdapter <CharSequence> dateadapter =
					  new ArrayAdapter <CharSequence> (Act, android.R.layout.simple_spinner_item );
					dateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					
					dateadapter.add("Select A Visit");
			 
			 
					Cursor c=database.GetVisitListForTestListByPatientId(p.Patient_id);
			 		   
			 		   Act.startManagingCursor(c);
			 		   if (c != null) 
			 		   {
			 			   c.moveToFirst(); 
			 			   while (!c.isAfterLast()) {
			 				   int id=c.getInt(0);
			 				   int visit_count=c.getInt(1);
			 				   int p_id=c.getInt(2);
			 				   String date = c.getString(3);		 				   
			 				   
			 				   Visit vd=new Visit(id, visit_count, p_id, date);
			 				   
			 				   visitlist.add(vd);
			 				   
			 				  if(date!=null)
			 				   {
			 				   dateadapter.add(date);
			 				   Print_Message(date);
			 				   }				   
			 				  else
			 				  {
			 					 Print_Message("DATE IS NULL");
			 				  }		   
			 				   
			 				
			 				   c.moveToNext();
			 			   
			 			   }
			 			   
			 		   
			 		   }
			 		
					
					
					
					Spinner visitdatespinner=(Spinner)rootView.findViewById(R.id.visitdatespinner);
					visitdatespinner.setAdapter(dateadapter);
					visitdatespinner.setOnItemSelectedListener(new YourTestDateItemSelectedListener());
			 
	       Show_Test();
	       
	       return rootView;
		}
		
		
		else
			
		{
			visitlist=new ArrayList<Visit>();
			LayoutInflater inflater = (LayoutInflater) myContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			
			 View rootView = inflater.inflate(R.layout.showadvice, parent, false);
	        
			  advicetextview=(TextView)rootView.findViewById(R.id.showdiseasenametextview);
			 
			 ArrayAdapter <CharSequence> dateadapter =
					  new ArrayAdapter <CharSequence> (Act, android.R.layout.simple_spinner_item );
					dateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					
					dateadapter.add("Select A Visit");
			 
			 
					Cursor c=database.GetVisitListForAdviceListByPatientId(p.Patient_id);
			 		   
			 		   Act.startManagingCursor(c);
			 		   if (c != null) 
			 		   {
			 			   c.moveToFirst(); 
			 			   while (!c.isAfterLast()) {
			 				   int id=c.getInt(0);
			 				   int visit_count=c.getInt(1);
			 				   int p_id=c.getInt(2);
			 				   String date = c.getString(3);		 				   
			 				   
			 				   Visit vd=new Visit(id, visit_count, p_id, date);
			 				   
			 				   visitlist.add(vd);
			 				   
			 				  if(date!=null)
			 				   {
			 				   dateadapter.add(date);
			 				   Print_Message(date);
			 				   }				   
			 				  else
			 				  {
			 					 Print_Message("DATE IS NULL");
			 				  }
			 				
			 				   c.moveToNext();
			 			   
			 			   }
			 			   
			 		   
			 		   }
			 		
					
					
					
					Spinner visitdatespinner=(Spinner)rootView.findViewById(R.id.visitdatespinner);
					visitdatespinner.setAdapter(dateadapter);
					visitdatespinner.setOnItemSelectedListener(new YourAdviceDateItemSelectedListener());
			 
	       
	       Show_Advice();
	       return rootView;
		}
		
	}
	@Override
	public View getGroupView(int position, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) myContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.exadapter, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);
		
		
		return rowView;
	}
	 }