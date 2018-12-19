package com.example.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import android.widget.ListView;
import android.widget.TextView;

import com.datashow.AppoinmentDetailsFragment;

import com.myclass.Appoinment;

import database.com.DatabaseHelper;

public class AppoinmentListFragment extends Fragment {

	ListView markList;

	DatabaseHelper database;

	ArrayList<Appoinment> appoinmentlist;

	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;

	public void Print_Message(String msg) {
		Log.d("MSG APL", msg);

	}

	class RowData {

		protected String name;
		protected String mobile;
		protected String time;

		RowData(String name_, String mobile_, String time_) {

			name = name_;
			mobile = mobile_;
			time = time_;
		}

		@Override
		public String toString() {
			return name + " " + mobile + " " + time;
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
			TextView name = null;
			TextView mobile = null;
			TextView time = null;
			RowData rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.appoinment, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			name = holder.getname();
			name.setText(rowData.name);
			mobile = holder.getmobile();
			mobile.setText(rowData.mobile);

			time = holder.getTime();
			time.setText(rowData.time);

			return convertView;
		}
	}

	class ViewHolder {
		private View mRow;
		private TextView name = null;
		private TextView mobile = null;
		private TextView time = null;

		public ViewHolder(View row) {
			mRow = row;
		}

		public TextView getname() {
			if (null == name) {
				name = (TextView) mRow.findViewById(R.id.name);
			}
			return name;
		}

		public TextView getmobile() {
			if (null == mobile) {
				mobile = (TextView) mRow.findViewById(R.id.mobile);
			}
			return mobile;
		}

		public TextView getTime() {
			if (null == time) {
				time = (TextView) mRow.findViewById(R.id.time);
			}
			return time;
		}
	}

	public AppoinmentListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appoinmentlist = new ArrayList<Appoinment>();
		database = new DatabaseHelper(this.getActivity());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.appoinmentlist,
				container, false);

		Context con = getActivity().getBaseContext();

		mInflater = (LayoutInflater) con
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		data = new Vector<RowData>();

		Cursor c = database.getAppoinmentList();

		getActivity().startManagingCursor(c);
		if (c != null) {
			c.moveToFirst();
			while (!c.isAfterLast()) {
				int id = c.getInt(0);

				String name = c.getString(1);

				String email = c.getString(2);

				String mobile = c.getString(3);

				String time = c.getString(4);

				String date = c.getString(5);

				Appoinment ap = new Appoinment(id, name, email, mobile, time,
						date);
				Print_Message(ap.Name + " " + ap.Appoinment_id);
				appoinmentlist.add(ap);

				try {
					rd = new RowData(name, mobile, time + "," + date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				data.add(rd);

				c.moveToNext();

			}

		}

		CustomAdapter adapter = new CustomAdapter(getActivity()
				.getBaseContext(), R.layout.appoinment, R.id.name, data);

		markList = (ListView) rootView.findViewById(R.id.plist);
		markList.setAdapter(adapter);

		markList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Bundle data = new Bundle();
				Appoinment p = appoinmentlist.get(arg2);
				data.putSerializable("Appoinment", p);

				AppoinmentDetailsFragment fragment = new AppoinmentDetailsFragment();
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
