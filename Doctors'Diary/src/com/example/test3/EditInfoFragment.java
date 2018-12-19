package com.example.test3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.datashow.ShowGenaralInfoFragment;
import com.myclass.Patient;

import database.com.DatabaseHelper;

public class EditInfoFragment extends Fragment {
	DatabaseHelper database;
	Patient p;
    int id;
	public EditInfoFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		database = new DatabaseHelper(this.getActivity());
		 p =(Patient) getArguments().getSerializable("Patient");
		 id=p.Patient_id;
		 Print_Message(p.Patient_id+p.Name);

	}

	public void Print_Message(String msg) {
		Log.d("MSG EPF", msg);

	}

	void Toast_Show(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		

		final View rootView = inflater.inflate(R.layout.editinfo, container,
				false);

		final Spinner bloodgroupspinner = (Spinner) rootView
				.findViewById(R.id.bloodspinner);

		ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(
				getActivity(), android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter1.add("Select A Blood Group");
		adapter1.add("A+");
		adapter1.add("A-");
		adapter1.add("B+");
		adapter1.add("B-");
		adapter1.add("O+");
		adapter1.add("O-");
		;
		adapter1.add("AB+");
		adapter1.add("AB-");

		bloodgroupspinner.setAdapter(adapter1);
		
		
		
		final EditText pname = (EditText) rootView
				.findViewById(R.id.patientname);
		
		pname.setText(p.Name);
		
		final EditText email = (EditText) rootView
				.findViewById(R.id.patientemail);
		email.setText(p.Email);
		
		
		
		final EditText mobile = (EditText) rootView
				.findViewById(R.id.patientmobile);
		mobile.setText(p.Mobile);

		Button button = (Button) rootView.findViewById(R.id.editpatientbutton);

		button.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("UseValueOf")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText pname = (EditText) rootView
						.findViewById(R.id.patientname);
				String patient_Name = pname.getText().toString();
                  
				EditText email = (EditText) rootView
						.findViewById(R.id.patientemail);
				
				String patient_Email = email.getText().toString();

				 EditText mobile = (EditText) rootView
							.findViewById(R.id.patientmobile);
				String patient_mobile = mobile.getText().toString();

				int age = 0;

				DatePicker dpick = (DatePicker) rootView
						.findViewById(R.id.birthdatePicker);
				int tempmonth = dpick.getMonth();
				int tempday = dpick.getDayOfMonth();
				int tempyear = dpick.getYear();

				String tempdate = tempday + "-" + tempmonth + "-" + tempyear;
				Print_Message(tempdate);

				try {
					Date date = new SimpleDateFormat("dd-mm-yyyy",
							Locale.ENGLISH).parse(tempdate);
					age = Calculate_Age(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				RadioButton male = (RadioButton) rootView
						.findViewById(R.id.male);

				String sex = "";
				if (male.isChecked()) {
					sex = "Male";
				} else {
					sex = "Female";

				}

				EditText month = (EditText) rootView
						.findViewById(R.id.monthedittext);
				EditText year = (EditText) rootView
						.findViewById(R.id.yearedittext);
				int duryear = 0;
				int durmonth = 0;

				String temp = month.getText().toString();
				if (!temp.equals("")) {
					durmonth = new Integer(temp);

				}

				temp = year.getText().toString();

				if (!temp.equals("")) {
					duryear = new Integer(temp);

				}

				String blood_group = bloodgroupspinner.getSelectedItem()
						.toString();

				if (patient_Name.equals("")) {
					Toast_Show("Enter Patient Name");
					Print_Message(patient_Name);

				} else if (patient_Email.equals("")) {
					Toast_Show("Enter Patient Email");
					Print_Message(patient_Email);

				} else if (patient_mobile.equals("")) {
					Toast_Show("Enter Patient Mobile Number");
					Print_Message(patient_mobile);

				}

				else if (age == 0) {

					Toast_Show(" Select Patient Age");

				} else if (bloodgroupspinner.getSelectedItemId() == 0) {
					Toast_Show("Select Blood Group");

				}

				else {
					Calendar c = Calendar.getInstance();

					SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
					String added_date = df.format(c.getTime());

					Patient up = new Patient(id,p.visit_count, patient_Name, patient_Email,
							patient_mobile, age,tempdate, sex, blood_group, durmonth,
							duryear, added_date);

					Update_Pateint(up);
					Print_Message(up.Patient_id+up.Name);

					Toast_Show(" Successfully Update Pateint");
					Next_To_Move(up);

				}

			}

		});
		
		
		Button backbutton = (Button) rootView.findViewById(R.id.backbutton);

		backbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Bundle data = new Bundle();

				data.putSerializable("Patient", p);


				ShowGenaralInfoFragment fragment = new ShowGenaralInfoFragment();
				fragment.setArguments(data);

				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		return rootView;

	}

	public void Next_To_Move(Patient up) {
		Bundle data = new Bundle();

		data.putSerializable("Patient", up);


		ShowGenaralInfoFragment fragment = new ShowGenaralInfoFragment();
		fragment.setArguments(data);

		getFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment).commit();

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

	public void Update_Pateint(Patient p) {

		database.updatePatientInfo(p);

	}

}
