package com.example.test3;
/**
 * This class handle to store new patient appointment
 * 
 * @author Sabuz
 *
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.myclass.Appoinment;
import database.com.DatabaseHelper;
public class NewPatientAppoinmentFragment extends Fragment {
	DatabaseHelper database;
	TextView emailValidatorview;
	boolean emailFlag=false;
	void toastShow(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();
	}

	public void printMessage(String msg) {
		Log.d("MSG AP", msg);
	}
	public NewPatientAppoinmentFragment() {
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		database = new DatabaseHelper(this.getActivity());

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.addappoinment,
				container, false);
		final TimePicker tpick = (TimePicker) rootView
				.findViewById(R.id.timePicker);
		tpick.setIs24HourView(true);	
		final EditText emailedittext = (EditText) rootView
				.findViewById(R.id.patientemail);
		emailValidatorview=(TextView)rootView.findViewById(R.id.patientemailvalidator);	
		emailedittext.addTextChangedListener(new TextWatcher() { 
	        public void afterTextChanged(Editable s) { 
	            if ( emailedittext.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && s.length() > 0)
	            {
	            	emailValidatorview.setTextColor(Color.GREEN);
	            	emailValidatorview.setText("valid email");
	            	emailFlag=true;
	            }
	            else
	            {
	            	emailFlag=false;
	            	emailValidatorview.setTextColor(Color.RED);
	            	emailValidatorview.setText("invalid email");
	            }
	        }
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub				
			} 	       
	    }); 
		Button button = (Button) rootView
				.findViewById(R.id.addappoinmentbutton);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText pname = (EditText) rootView
						.findViewById(R.id.patientname);
				String Pateint_Name = pname.getText().toString();

				EditText email = (EditText) rootView
						.findViewById(R.id.patientemail);
				String Pateint_Email = email.getText().toString();

				EditText mobile = (EditText) rootView
						.findViewById(R.id.patientmobile);
				String Pateint_mobile = mobile.getText().toString();

				String temphour = tpick.getCurrentHour().toString();
				String tempmin = tpick.getCurrentMinute().toString();
				String time = temphour + ":" + tempmin;

				DatePicker dpick = (DatePicker) rootView
						.findViewById(R.id.datePicker);
				int age=0;
				int month = dpick.getMonth();
				int day = dpick.getDayOfMonth();
				int year = dpick.getYear();

				String tempdate = day + "-" + month + "-" + year;
				
				
				try {
					Date date = new SimpleDateFormat("dd-mm-yyyy",
							Locale.ENGLISH).parse(tempdate);
					age = calculateAge(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Appoinment A = new Appoinment(Pateint_Name, Pateint_Email,
						Pateint_mobile, time, tempdate);
				if(age>0)
				{
					toastShow("Enter Correct Date");
				}
				else if(emailFlag)
				{
				addAppoinment(A);
				toastShow(" Successfully Added Appoinment");
				nextToMove();
				}
				else
				{
					toastShow("Invalid Email");
				}

				
			}

		});

		return rootView;

	}

	/**
	 * move to next fragment
	 * 
	 *
	 */
	public void nextToMove() {
		AppoinmentFragment fragment = new AppoinmentFragment();
		getFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment).commit();

	}
	/**
	 * Calculate age from birthdate
	 * 
	 *
	 */
	public int calculateAge(Date birthdate) {
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
		printMessage("Age is " + age);
		return age;
	}
	/**
	 * add appointment to database
	 * 
	 *
	 */

	public void addAppoinment(Appoinment p) {

		database.addAppoinment(p);

	}

}
