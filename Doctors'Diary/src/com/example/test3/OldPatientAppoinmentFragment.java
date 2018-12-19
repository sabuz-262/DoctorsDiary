package com.example.test3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.myclass.Appoinment;
import com.myclass.Patient;

import database.com.DatabaseHelper;

public class OldPatientAppoinmentFragment extends Fragment {

	DatabaseHelper database;
	ArrayList<Patient> patientlist;

	void Toast_Show(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	public void Print_Message(String msg) {
		Log.d("MSG OPA", msg);

	}

	public OldPatientAppoinmentFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		database = new DatabaseHelper(this.getActivity());
		patientlist = new ArrayList<Patient>();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.oldpatientappoinment,
				container, false);

		final Spinner patientlistspinner = (Spinner) rootView
				.findViewById(R.id.patientlistspinner);

		ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(
				getActivity(), android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapter1.add("Select A Patient");

		Cursor c = database.getPatientList();

		try {

			getActivity().startManagingCursor(c);
			if (c != null) {
				c.moveToFirst();
				while (!c.isAfterLast()) {
					int id = c.getInt(0);

					int visit_count = c.getInt(1);

					String name = c.getString(2);

					String email = c.getString(3);

					String mobile = c.getString(4);

					int age = c.getInt(5);

					String birtdate = c.getString(6);
					String sex = c.getString(7);

					String blood_group = c.getString(8);

					String date = c.getString(9);

					int dur_month = c.getInt(10);
					int dur_year = c.getInt(11);

					Patient p = new Patient(id, visit_count, name, email,
							mobile, age, birtdate, sex, blood_group, dur_month,
							dur_year, date);

					patientlist.add(p);

					adapter1.add(name + " " + mobile);

					c.moveToNext();

				}

			}

		} catch (Exception E) {
			Print_Message("Exception  " + E.toString());
		}

		patientlistspinner.setAdapter(adapter1);

		final TimePicker tpick = (TimePicker) rootView
				.findViewById(R.id.timePicker);
		tpick.setIs24HourView(true);

		Button button = (Button) rootView
				.findViewById(R.id.addappoinmentbutton);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int tempid = (int) patientlistspinner.getSelectedItemId();
				if (tempid == 0) {
					Toast_Show(" Select A Patient");
				} else {
					Patient p = patientlist.get(tempid - 1);

					String Pateint_Name = p.Name;

					String Pateint_Email = p.Email;

					String Pateint_mobile = p.Mobile;

					String temphour = tpick.getCurrentHour().toString();
					String tempmin = tpick.getCurrentMinute().toString();
					String time = temphour + ":" + tempmin;

					DatePicker dpick = (DatePicker) rootView
							.findViewById(R.id.datePicker);

					int month = dpick.getMonth();
					int day = dpick.getDayOfMonth();
					int year = dpick.getYear();

					String tempdate = day + "-" + month + "-" + year;
					int age=0;
					try {
						Date date = new SimpleDateFormat("dd-mm-yyyy",
								Locale.ENGLISH).parse(tempdate);
						age = Calculate_Age(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Appoinment A = new Appoinment(Pateint_Name, Pateint_Email,
							Pateint_mobile, time, tempdate);
					if(age>0)
					{
						Toast_Show("Enter Correct Date");
					}

					else
					{
					Add_Appoinment(A);

					Toast_Show(" Successfully Added Appoinment");
					Next_To_Move();
					}
				}

			}

		});

		return rootView;

	}
	
	public int Calculate_Age(Date birthdate) {
		Calendar dob = Calendar.getInstance();
		dob.setTime(birthdate);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
			age--;
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) < dob
						.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		Print_Message("Age is " + age);
		return age;
	}

	public void Next_To_Move() {
		AppoinmentFragment fragment = new AppoinmentFragment();
		getFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment).commit();

	}

	public void Add_Appoinment(Appoinment p) {

		database.addAppoinment(p);

	}

}
