/**
 * 
 */
package com.data.sink;

/**
 * @author Sabuz
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test3.R;
import com.myclass.Patient;

import database.com.DatabaseEntity;
import database.com.DatabaseHelper;
import database.com.JSONParser;

public class DataSinkFragment extends Fragment {
	DatabaseHelper database;

	private static String url_create_patient = "http://10.0.2.2/doctordiary/create_patient.php";

	private static final String TAG_SUCCESS = "success";
	JSONParser jsonParser = new JSONParser();

	ArrayList<Patient> patientList = new ArrayList<Patient>();

	public DataSinkFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		database = new DatabaseHelper(this.getActivity());
		addPatient();

	}

	private void addPatient() {
		Cursor c = database.getPatientList();

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

				Patient p = new Patient(id, visit_count, name, email, mobile,
						age, birtdate, sex, blood_group, dur_month, dur_year,
						date);

				patientList.add(p);

				c.moveToNext();

			}

		}

		addPatientToServer();

	}

	private void addPatientToServer() {
		
		ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = manager.getActiveNetworkInfo();

		if (netInfo == null) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					getActivity().getBaseContext());
			dialog.setTitle("Error");
			dialog.setMessage("Internert Not Available");
			dialog.show();
		} else {
			if (netInfo.isConnected()) {
				//pd = ProgressDialog.show(getActivity().getBaseContext(), "",
						//"Loading", true, false);

				MyRunnable myrunnable = new MyRunnable();
				Thread t = new Thread(myrunnable);
				t.start();
				
			} else {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity().getBaseContext());
				dialog.setTitle("Error");
				dialog.setMessage("Netwrok Malfunctioning");
				dialog.show();
			}
		}

	}

	public void Print_Message(String msg) {
		Log.d("MSG AP", msg);

	}

	void Toast_Show(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.addpatient, container, false);

		return rootView;

	}

	public class MyRunnable implements Runnable {

		@Override
		public void run() {
			Print_Message("Size "+patientList.size());

			for (int i = 0; i < patientList.size(); i++) {
				// TODO Auto-generated method stub

				Patient p = patientList.get(i);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("doctor_id",
						String.valueOf(1)));

				params.add(new BasicNameValuePair(DatabaseEntity.VISITCOUNT,
						String.valueOf(p.visit_count)));
				params.add(new BasicNameValuePair(DatabaseEntity.PATIENT_NAME,
						p.Name));

				params.add(new BasicNameValuePair(DatabaseEntity.PATIENT_EMAIL,
						p.Email));
				params.add(new BasicNameValuePair(
						DatabaseEntity.PATIENT_MOBILE, p.Mobile));

				params.add(new BasicNameValuePair(DatabaseEntity.PATIENT_AGE,
						String.valueOf(p.Age)));
				params.add(new BasicNameValuePair(
						DatabaseEntity.PATIENT_BIRTHDATE, p.birthdate));

				params.add(new BasicNameValuePair(DatabaseEntity.PATIENT_SEX,
						p.Sex));
				params.add(new BasicNameValuePair(
						DatabaseEntity.PATIENT_BLOODGROUP, p.Blood_Group));

				params.add(new BasicNameValuePair(
						DatabaseEntity.PATIENT_DUR_MONTH, String
								.valueOf(p.Dur_month)));
				params.add(new BasicNameValuePair(
						DatabaseEntity.PATIENT_DUR_YEAR, String
								.valueOf(p.Dur_year)));

				params.add(new BasicNameValuePair(DatabaseEntity.DATE,
						p.Added_Date));

				// getting JSON Object
				// Note that create product url accepts POST method
				JSONObject json = jsonParser.makeHttpRequest(
						url_create_patient, "POST", params);

				Print_Message("after ");
				Log.d("Create Response", json.toString());

				// check for success tag
				try {
					int suc = json.getInt(TAG_SUCCESS);

					if (suc == 1) {
						//
						database.deletePatient(p.Patient_id);

						Log.d("Messag", "Pateint Successfully Inserted");

					} else {
						// failed to create product
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}

	}

}