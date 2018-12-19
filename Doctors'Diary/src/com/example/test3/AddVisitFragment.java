package com.example.test3;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.myclass.Patient;
import com.myclass.VisitData;

import database.com.DatabaseHelper;

public class AddVisitFragment extends Fragment {

	DatabaseHelper dbHelper;

	TextView temptext;
	Patient p;

	Double temperature = 0.0;
	int weight = 0;
	int bph = 0;
	int bpl = 0;
	int hrmin = 0;
	int hrmax = 0;

	public void Print_Message(String msg) {
		Log.d("MSG AV", msg);

	}

	public AddVisitFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dbHelper = new DatabaseHelper(this.getActivity());

		p = (Patient) getArguments().getSerializable("Patient");

		Print_Message(p.Patient_id + " " + p.Name + " " + "Visit count="
				+ p.visit_count);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.addvisitdata, container,
				false);

		
		TextView patientnametextview=(TextView)rootView.findViewById(R.id.patientnametextview);
		patientnametextview.setText("Name: "+p.Name+"  Age: "+p.Age);
		SeekBar tempbar = (SeekBar) rootView.findViewById(R.id.tempseekBar);
		temptext = (TextView) rootView.findViewById(R.id.temptext);
		tempbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

				if (arg1 < 10) {

					temptext.setText("98." + arg1);

				} else if (arg1 >= 10 && arg1 < 20)

				{
					arg1 = arg1 - 10;
					temptext.setText("99." + arg1);

				}

				else if (arg1 >= 20 && arg1 < 30)

				{
					arg1 = arg1 - 20;
					temptext.setText("100." + arg1);

				} else if (arg1 >= 30 && arg1 < 40)

				{
					arg1 = arg1 - 30;
					temptext.setText("101." + arg1);

				}

				else if (arg1 >= 40 && arg1 < 50)

				{
					arg1 = arg1 - 40;
					temptext.setText("102." + arg1);

				} else if (arg1 >= 50 && arg1 < 60)

				{
					arg1 = arg1 - 50;
					temptext.setText("103." + arg1);

				}

				else if (arg1 >= 60 && arg1 < 70)

				{
					arg1 = arg1 - 60;
					temptext.setText("104." + arg1);

				} else if (arg1 >= 70 && arg1 < 80)

				{
					arg1 = arg1 - 70;
					temptext.setText("105." + arg1);

				}

				else if (arg1 >= 80 && arg1 < 90)

				{
					arg1 = arg1 - 80;
					temptext.setText("106." + arg1);

				}

				// TODO Auto-generated method stub

			}
		});

		SeekBar wseek = (SeekBar) rootView.findViewById(R.id.wseekBar);
		final TextView weighttext = (TextView) rootView
				.findViewById(R.id.wtext);

		wseek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				weight = arg1;
				weighttext.setText(arg1 + " KG");
			}
		});

		SeekBar bhseek = (SeekBar) rootView.findViewById(R.id.bhseekBar);
		final TextView bhtext = (TextView) rootView.findViewById(R.id.bhtext);

		bhseek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				bph = arg1;
				bhtext.setText(arg1 + " bp");
			}
		});

		SeekBar blseek = (SeekBar) rootView.findViewById(R.id.blseekBar);
		final TextView bltext = (TextView) rootView.findViewById(R.id.bltext);

		blseek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				bpl = arg1;
				bltext.setText(arg1 + " bp");
			}
		});

		SeekBar hminseek = (SeekBar) rootView.findViewById(R.id.hminseekBar);
		final TextView hmintext = (TextView) rootView
				.findViewById(R.id.hmintext);

		hminseek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				hrmin = arg1;
				hmintext.setText(arg1 + " ");
			}
		});

		SeekBar hmaxseek = (SeekBar) rootView.findViewById(R.id.hmaxseekBar);
		final TextView hmaxtext = (TextView) rootView
				.findViewById(R.id.hmaxtext);

		hmaxseek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				hrmax = arg1;
				hmaxtext.setText(arg1 + " ");
			}
		});

		Button back = (Button) rootView.findViewById(R.id.backbutton);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Bundle data = new Bundle();

				data.putSerializable("Patient", p);

				data.putInt("FROM", 0);

				VisitFragment fragment = new VisitFragment();
				fragment.setArguments(data);

				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		final EditText purpose = (EditText) rootView
				.findViewById(R.id.purposeofvisit);

		Button addbutton = (Button) rootView.findViewById(R.id.addvisitbutton);

		addbutton.setOnClickListener(new OnClickListener() {

			@SuppressLint("UseValueOf")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				String pur_visit = purpose.getText().toString();
				if (pur_visit.equals("")) {
					Toast_Show("Purpose of Visit Can not Empty");

				} else {
					String temp = temptext.getText().toString();
					
					if(bpl>bph)
					{
						Toast_Show("Blood Pressure Low Can not Greater then Blood Pressure High");
					}
					else if(hrmin>hrmax)
					{
						Toast_Show("Heart Rate Minimum Can not Greater then Heart Rate Maximum");
						
					}
					else
					{

					Add_visit(temp, pur_visit);
					}
				}

			}
		});

		return rootView;

	}

	void Toast_Show(String msg) {

		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	public void Add_visit(String temp, String pur_visit) {

		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String added_date = df.format(c.getTime());

		VisitData vd = new VisitData(p.Patient_id, p.visit_count, pur_visit,
				added_date, temp, weight, bph, bpl, hrmin, hrmax);

		
		dbHelper.addVisitData(vd);
		
		Toast_Show("Successfully Added Visit Data");

		Bundle data = new Bundle();

		data.putSerializable("Patient", p);

		data.putInt("FROM", 0);

		VisitFragment fragment = new VisitFragment();
		fragment.setArguments(data);

		getFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment).commit();

	}

}
