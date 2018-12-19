package com.mkyong.android.adaptor;

import com.example.test3.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MainMenuAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	String name;

	public MainMenuAdapter(Context context, String[] values) {
		super(context, R.layout.logoadapter, values);
		this.context = context;
		this.values = values;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.logoadapter, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);

		// Change icon based on name
		String s = values[position];

		// System.out.println(s);

		
			
				imageView.setImageResource(R.drawable.ic_launcher);
			
					if (s.equals("Add Patient")) {
						imageView.setImageResource(R.drawable.add_patient);
					} else if (s.equals("Add Visit")) {
						imageView.setImageResource(R.drawable.addvisit);
					}
					
					else if (s.equals("Conversation")) {
						imageView.setImageResource(R.drawable.mic1);
					}
					else if (s.equals("Picture")) {
						imageView.setImageResource(R.drawable.camera);
					}
				 
					else if (s.equals("Audio Note")) {
						imageView.setImageResource(R.drawable.mic);
					}
					else if (s.equals("Patient List")) {
						imageView.setImageResource(R.drawable.patientlist);
					}
					else if (s.equals("Search Patient")) {
						imageView.setImageResource(R.drawable.search);
					}
					else if (s.equals("Appointment")) {
						imageView.setImageResource(R.drawable.appointments);
					}
					else if (s.equals("Message")) {
						imageView.setImageResource(R.drawable.message);
					}
					
					else
					{
						imageView.setImageResource(R.drawable.ic_launcher);
					}
				
	
		

		return rowView;
	}
}
