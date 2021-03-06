package com.datashow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.datashow.MessageListofPatientFragment.CustomAdapter;
import com.datashow.MessageListofPatientFragment.RowData;
import com.datashow.MessageListofPatientFragment.ViewHolder;
import com.example.test3.AddAudioConFragment;
import com.example.test3.R;
import com.myclass.AddConversation;
import com.myclass.Patient;

import database.com.DatabaseHelper;

public class ShowAudioConFragment extends Fragment {

	ListView markList;
	 private LayoutInflater mInflater;
	 private Vector<RowData> data;
	 RowData rd;
	DatabaseHelper database;
	boolean flag = false;
	ArrayList<AddConversation> addconversationlist;
	ArrayList<String> filelist;
	Patient p;

	void Toast_Show(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	public void Print_Message(String msg) {
		Log.d("MSG SACF", msg);

	}

	class RowData {

		protected String date;
		protected String filename;

		RowData(String name_,String f_name) {

			date = name_;
			filename=f_name;
			

		}

		@Override
		public String toString() {
			return date;
		}
	}

	
	
	class CustomAdapter extends ArrayAdapter<RowData> {

		public CustomAdapter(Context context, int resource, int textViewResourceId,
				List<RowData> objects) {

			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			TextView date = null;
			TextView filename = null;
			
			
			RowData rowData = getItem(position);
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.conversationdetails, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			date = holder.getdate();
			date.setText(rowData.date);
			filename=holder.getfile();
			filename.setText(rowData.filename);
			
			
			return convertView;
		}
	}
	
	
	
	 class ViewHolder {
			private View mRow;
			private TextView send_time = null;
			private TextView filename = null;
			

			public ViewHolder(View row) {
				mRow = row;
			}

			public TextView getdate() {
				if (null == send_time) {
					send_time = (TextView) mRow.findViewById(R.id.datetextview);
				}
				return send_time;
			}

			public TextView getfile() {
				if (null == filename) {
					filename = (TextView) mRow.findViewById(R.id.filenametextview);
				}
				return filename;
			}
			
		}
	public ShowAudioConFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		p = (Patient) getArguments().getSerializable("Patient");
		database = new DatabaseHelper(getActivity());
		addconversationlist = new ArrayList<AddConversation>();
		filelist = new ArrayList<String>();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.showaudiocon,
				container, false);
		
		
		

		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				getActivity(), android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

		Cursor c = database.GetAudioConversationListByPatientId(p.Patient_id);
		 Context con=getActivity().getBaseContext();
		 mInflater = (LayoutInflater) con.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		 
		 data = new Vector<RowData>();

		getActivity().startManagingCursor(c);
		if (c != null) {
			c.moveToFirst();
			while (!c.isAfterLast()) {
				int id = c.getInt(0);

				int visit_id = c.getInt(1);

				int p_id = c.getInt(2);

				String date = c.getString(3);

				String filename = c.getString(4);
				filelist.add(filename);
				AddConversation ad = new AddConversation(id, p_id, visit_id,
						date, filename);

				adapter.add(date + "\n" + filename);
				
				
				try {
	 				rd = new RowData(date, filename);
	 			} catch (ParseException e) {
	 				e.printStackTrace();
	 			}
	 			data.add(rd);
				addconversationlist.add(ad);
				c.moveToNext();

			}

		}

		ListView l = (ListView) rootView.findViewById(R.id.audiofilelist);
		
		CustomAdapter customadapter = new CustomAdapter(getActivity().getBaseContext(), R.layout.conversationdetails,
 				R.id.datetextview, data);
		

		l.setAdapter(customadapter);

		l.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				String PATH_TO_FILE = getOutputMediaFile() + "/"
						+ filelist.get(arg2);
				
				MediaPlayer mediaPlayer = new MediaPlayer();
				try {
					mediaPlayer.setDataSource(PATH_TO_FILE);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mediaPlayer.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mediaPlayer.start();
				
				Toast_Show( PATH_TO_FILE+" Playing");

			}
		});

		Button addconversation = (Button) rootView
				.findViewById(R.id.addconversation);
		addconversation.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				AddAudioConFragment fragment = new AddAudioConFragment();
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				fragment.setArguments(data);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		return rootView;

	}

	public File getOutputMediaFile() {
		File f;

		f = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/DoctorPatient");
		if (!f.exists()) {
			if (!f.mkdir()) {
				return null;
			} else {
				return f;
			}
		} else {
			return f;
		}

	}

	public void Show() {

		Log.d("MSG", "In show Funtion");

	}

}
