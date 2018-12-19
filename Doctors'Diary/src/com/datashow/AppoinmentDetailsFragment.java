package com.datashow;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.test3.R;
import com.example.test3.AppoinmentFragment;
import com.myclass.Appoinment;

import database.com.DatabaseHelper;

public class AppoinmentDetailsFragment extends Fragment {

	Appoinment ap;

	DatabaseHelper database;

	void Toast_Show(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	public AppoinmentDetailsFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ap = (Appoinment) getArguments().getSerializable("Appoinment");
		database = new DatabaseHelper(this.getActivity());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.appoinmentdetails,
				container, false);

		Button back = (Button) rootView.findViewById(R.id.backbutton);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				AppoinmentFragment fragment = new AppoinmentFragment();

				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		if (ap == null) {

		} else {

			TextView patient_name = (TextView) rootView
					.findViewById(R.id.appname);
			patient_name.setText(ap.Name);

			TextView patient_email = (TextView) rootView
					.findViewById(R.id.appemail);
			patient_email.setText(ap.Email);

			TextView patient_mobile = (TextView) rootView
					.findViewById(R.id.appmobile);
			patient_mobile.setText(ap.Mobile);

			TextView patient_date = (TextView) rootView
					.findViewById(R.id.appdate);
			patient_date.setText(ap.Date);

			TextView patient_time = (TextView) rootView
					.findViewById(R.id.apptime);
			patient_time.setText(ap.Time);

		}

		TextView changedate = (TextView) rootView.findViewById(R.id.changedate);
		changedate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				LayoutInflater layoutInflater = (LayoutInflater) getActivity()
						.getBaseContext().getSystemService(
								Context.LAYOUT_INFLATER_SERVICE);
				final View popupView = layoutInflater.inflate(
						R.layout.changedate, null);
				final PopupWindow popupWindow = new PopupWindow(popupView, 400,
						300, true);

				popupWindow.showAtLocation(rootView, Gravity.CENTER, 120, 100);

				Button finish = (Button) popupView.findViewById(R.id.finish);
				finish.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DatePicker dpick = (DatePicker) popupView
								.findViewById(R.id.changedatePicker);

						int month = dpick.getMonth();
						int day = dpick.getDayOfMonth();
						int year = dpick.getYear();

						String date = day + "-" + month + "-" + year;

						Update_Date(date);
						String phoneNo = ap.Mobile.toString();
						String sms = "Appoinment Date is Changed New Appoinment Date is "
								+ date;

						try {
							SmsManager smsManager = SmsManager.getDefault();
							smsManager.sendTextMessage(phoneNo, null, sms,
									null, null);
							Toast_Show("Successfully Send Message");

						} catch (Exception e) {
							Toast_Show("Can not  Send Message");
							e.printStackTrace();
						}

						popupWindow.dismiss();

						TextView patient_date = (TextView) rootView
								.findViewById(R.id.appdate);
						patient_date.setText(date);

					}
				});
				
				
				Button cancel = (Button) popupView.findViewById(R.id.cancel);
				cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						popupWindow.dismiss();
				
					}
				});
				
			}
		});

		TextView changetime = (TextView) rootView.findViewById(R.id.changetime);
		changetime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				LayoutInflater layoutInflater = (LayoutInflater) getActivity()
						.getBaseContext().getSystemService(
								Context.LAYOUT_INFLATER_SERVICE);
				final View popupView = layoutInflater.inflate(
						R.layout.changetime, null);
				final PopupWindow popupWindow = new PopupWindow(popupView, 400,
						300, true);

				popupWindow.showAtLocation(rootView, Gravity.CENTER, 120, 100);

				final TimePicker tpick = (TimePicker) popupView
						.findViewById(R.id.changetimePicker);
				tpick.setIs24HourView(true);

				Button finish = (Button) popupView.findViewById(R.id.finish);
				finish.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						String temphour = tpick.getCurrentHour().toString();
						String tempmin = tpick.getCurrentMinute().toString();
						String time = temphour + ":" + tempmin;
						Update_Time(time);

						String phoneNo = ap.Mobile.toString();
						String sms = "Appoinment Time is Changed New Appoinment Time is "
								+ time;

						try {
							SmsManager smsManager = SmsManager.getDefault();
							smsManager.sendTextMessage(phoneNo, null, sms,
									null, null);
							Toast_Show("Successfully Send Message");

						} catch (Exception e) {
							Toast_Show("Can not  Send Message");
							e.printStackTrace();
						}
						popupWindow.dismiss();

						TextView patient_time = (TextView) rootView
								.findViewById(R.id.apptime);
						patient_time.setText(time);
					}
				});
				
				Button cancel = (Button) popupView.findViewById(R.id.cancel);
				cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						popupWindow.dismiss();
				
					}
				});

			}
		});

		Button delete = (Button) rootView.findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Delete_Appoinment();

				AppoinmentFragment fragment = new AppoinmentFragment();
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		return rootView;

	}

	public void Update_Date(String date) {
		database.updateAppoinmentDate(ap.Appoinment_id, date);

	}

	public void Update_Time(String time) {
		database.updateAppoinmentTime(ap.Appoinment_id, time);

	}

	public void Delete_Appoinment() {
		database.deleteAppoinment(ap.Appoinment_id);
	}

	public void Show() {

		Log.d("MSG", "In show Funtion");

	}

}
