package com.datashow;

/**
 * This class handle to show patient general information
 * 
 * @author Sabuz
 *
 */
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.test3.EditInfoFragment;
import com.example.test3.R;
import com.example.test3.VisitFragment;
import com.myclass.Patient;

public class ShowGenaralInfoFragment extends Fragment {
	Patient p;

	public void printMessage(String msg) {
		Log.d("MSG SGI", msg);
	}

	public ShowGenaralInfoFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		p = (Patient) getArguments().getSerializable("Patient");
		printMessage(p.Name);
	}

	@SuppressLint("UseValueOf")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.showgenaralinfo,
				container, false);
		TextView patient_name = (TextView) rootView
				.findViewById(R.id.patientnametextview);
		patient_name.setText(p.Name);
		TextView patient_email = (TextView) rootView
				.findViewById(R.id.patientemailtextview);
		patient_email.setText(p.Email);
		TextView patient_age = (TextView) rootView
				.findViewById(R.id.patientage);
		patient_age.setText(new Integer(p.Age).toString());
		TextView patient_mobile = (TextView) rootView
				.findViewById(R.id.patientmobile);
		patient_mobile.setText(p.Mobile);
		TextView patient_bloodgroup = (TextView) rootView
				.findViewById(R.id.patientbloodgroup);
		patient_bloodgroup.setText(p.Blood_Group);
		TextView patient_sex = (TextView) rootView
				.findViewById(R.id.patientsex);
		patient_sex.setText(p.Sex);
		TextView patient_addeddate = (TextView) rootView
				.findViewById(R.id.patientaddeddate);
		patient_addeddate.setText(p.Added_Date);
		TextView addvisit = (TextView) rootView.findViewById(R.id.addvisit);
		addvisit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				VisitFragment fragment = new VisitFragment();
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				fragment.setArguments(data);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();
			}
		});

		Button editinfo = (Button) rootView.findViewById(R.id.editgenaralinfo);
		editinfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditInfoFragment fragment = new EditInfoFragment();
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				fragment.setArguments(data);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();
			}
		});

		return rootView;

	}

	public void Show() {

		Log.d("MSG", "In show Funtion");

	}

}
