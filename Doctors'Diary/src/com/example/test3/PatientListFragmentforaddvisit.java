package com.example.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.myclass.Patient;

import database.com.DatabaseHelper;

@SuppressLint("UseValueOf")
public class PatientListFragmentforaddvisit extends Fragment {

	ListView markList;

	DatabaseHelper database;

	ArrayList<Patient> patientlist;

	public void Print_Message(String msg) {
		Log.d("MSG PLFFAV", msg);

	}

	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;

	private Integer[] imgid = { R.drawable.patient };

	class RowData {

		protected String name;
		protected String mobile;

		RowData(String title, String detail) {

			name = title;
			mobile = detail;
		}

		@Override
		public String toString() {
			return name + " " + mobile;
		}
	}

	class CustomAdapter extends ArrayAdapter<RowData> {

		public CustomAdapter(Context context, int resource,
				int textViewResourceId, List<RowData> objects) {

			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			TextView title = null;
			TextView detail = null;
			ImageView i11 = null;
			RowData rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.list, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			title = holder.gettitle();
			title.setText(rowData.name);
			detail = holder.getdetail();
			detail.setText(rowData.mobile);

			i11 = holder.getImage();
			i11.setImageResource(imgid[0]);
			return convertView;
		}
	}

	class ViewHolder {
		private View mRow;
		private TextView nametextview = null;
		private TextView mobiletextview = null;
		private ImageView i11 = null;

		public ViewHolder(View row) {
			mRow = row;
		}

		public TextView gettitle() {
			if (null == nametextview) {
				nametextview = (TextView) mRow.findViewById(R.id.title);
			}
			return nametextview;
		}

		public TextView getdetail() {
			if (null == mobiletextview) {
				mobiletextview = (TextView) mRow.findViewById(R.id.detail);
			}
			return mobiletextview;
		}

		public ImageView getImage() {
			if (null == i11) {
				i11 = (ImageView) mRow.findViewById(R.id.img);
			}
			return i11;
		}
	}

	public PatientListFragmentforaddvisit() {
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

		final View rootView = inflater.inflate(R.layout.patientlist, container,
				false);

		Context con = getActivity().getBaseContext();

		mInflater = (LayoutInflater) con
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		data = new Vector<RowData>();

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
						age,birtdate, sex, blood_group, dur_month, dur_year, date);

				patientlist.add(p);

				try {
					rd = new RowData(name, mobile);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				data.add(rd);

				c.moveToNext();

			}

		}

		CustomAdapter adapter = new CustomAdapter(getActivity()
				.getBaseContext(), R.layout.list, R.id.title, data);

		markList = (ListView) rootView.findViewById(R.id.plist);
		markList.setAdapter(adapter);

		markList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Bundle data = new Bundle();
				Patient p = patientlist.get(arg2);
				data.putSerializable("Patient", p);

				data.putInt("FROM", 100);
				Print_Message(p.Patient_id + " " + p.Name);

				VisitFragment fragment = new VisitFragment();
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
