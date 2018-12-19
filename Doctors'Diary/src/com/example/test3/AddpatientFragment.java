package com.example.test3;
/**
 * This class handle to store patient informatoin
 * @author Sabuz
 *
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.myclass.Patient;
import database.com.DatabaseHelper;
public class AddpatientFragment extends Fragment {
	DatabaseHelper insertDatabase;                               //database helper instance to store patient information
	TextView emailValidatorView;
	boolean emailFlag = false;             
	public AddpatientFragment() {
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		insertDatabase = new DatabaseHelper(this.getActivity());
	}
	/**
	 * Print In Log Cat
	 * 
	 */
	public void printMessage(String msg) {
		Log.d("MSG AP", msg);

	}
	void toastShow(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.addpatient, container,
				false);
		final Spinner bloodgroupspinner = (Spinner) rootView                 
				.findViewById(R.id.bloodspinner);
		ArrayAdapter<CharSequence> bloodGroupAdapter = new ArrayAdapter<CharSequence>(
				getActivity(), android.R.layout.simple_spinner_item);
		bloodGroupAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodGroupAdapter.add("Select A Blood Group");
		bloodGroupAdapter.add("A+");
		bloodGroupAdapter.add("A-");
		bloodGroupAdapter.add("B+");
		bloodGroupAdapter.add("B-");
		bloodGroupAdapter.add("O+");
		bloodGroupAdapter.add("O-");
		bloodGroupAdapter.add("AB+");
		bloodGroupAdapter.add("AB-");
		bloodgroupspinner.setAdapter(bloodGroupAdapter);
		final EditText emailedittext = (EditText) rootView
				.findViewById(R.id.patientemail);
		emailValidatorView = (TextView) rootView
				.findViewById(R.id.patientemailvalidator);
		emailedittext.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (emailedittext.getText().toString()
						.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
						&& s.length() > 0) {
					emailValidatorView.setTextColor(Color.GREEN);
					emailValidatorView.setText("valid email");
					emailFlag = true;
				} else {
					emailFlag = false;
					emailValidatorView.setTextColor(Color.RED);
					emailValidatorView.setText("invalid email");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

		});
		Button addPateintButton = (Button) rootView
				.findViewById(R.id.addpatientbutton);
		addPateintButton.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("UseValueOf")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText pname = (EditText) rootView
						.findViewById(R.id.patientname);
				String patient_Name = pname.getText().toString();
				String patientEmail = emailedittext.getText().toString();
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
				printMessage(tempdate);
				try {
					Date date = new SimpleDateFormat("dd-mm-yyyy",
							Locale.ENGLISH).parse(tempdate);
					age = calculateAge(date);
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
				String bloodGroup = bloodgroupspinner.getSelectedItem()
						.toString();

				if (patient_Name.equals("")) {
					toastShow("Enter Patient Name");

				} else if (!emailFlag) {
					toastShow("In valid Email");
				}
				else if (age < 0) {

					toastShow(" Select Patient Age");

				} else if (bloodgroupspinner.getSelectedItemId() == 0) {
					toastShow("Select Blood Group");
				}

				else {
					Calendar c = Calendar.getInstance();

					SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
					String added_date = df.format(c.getTime());

					Patient tempPatient = new Patient(0, patient_Name,
							patientEmail, patient_mobile, age, tempdate, sex,
							bloodGroup, durmonth, duryear, added_date);
					addPateint(tempPatient);
					toastShow(" Successfully Added Pateint");
					nextToMove();
				}
			}
		});
		return rootView;
	}
	public void nextToMove() {
		PatientListFragment fragment = new PatientListFragment();
		getFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment).commit();
	}
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
	public void addPateint(Patient p) {

		insertDatabase.addPatient(p);

	}

}
