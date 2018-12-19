package com.example.test3;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.datashow.GraphFragment;

import com.myclass.Patient;
import com.myclass.VisitData;
import database.com.DatabaseHelper;

public class ShowVisitDataFragment extends Fragment {

	boolean flag = false;

	TextView visitdatetextview;
	TextView visitnumbertextview;
	TextView temperaturetextview;
	TextView purposetextview;
	TextView bphtextview;
	TextView bpltextview;
	TextView hrmaxtextview;
	TextView hrmintextview;

	Patient p;

	Double maxtemperature = 0.0;
	Double mintemperature = 10000.0;

	Double maxheartrate = 0.0;
	Double minheartrate = 10000.0;

	Double minmaxheartrate = 0.0;
	Double minminheartrate = 10000.0;

	Double maxbloodpressure = 0.0;
	Double minbloodpressure = 10000.0;

	Double bplmaxbloodpressure = 0.0;
	Double bplminbloodpressure = 10000.0;

	DatabaseHelper database;
	ArrayList<VisitData> visitdatalist;

	public void Print_Message(String msg) {
		Log.d("MSG FVF", msg);

	}

	ArrayList<String> temperaturelist;
	ArrayList<String> bloodpressurelist;
	ArrayList<String> bplbloodpressurelist;
	ArrayList<String> heartratelist;
	ArrayList<String> minheartratelist;
	ArrayList<String> datelist;

	public class YourItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub

			if (!flag) {
				flag = true;
			} else if (pos >= 1) {
				VisitData vd = visitdatalist.get(pos - 1);
				visitdatetextview.setText(vd.Date);
				Print_Message("Selected Date" + vd.Date);
				visitnumbertextview.setText(String.valueOf(vd.visit_id));
				purposetextview.setText(vd.purpose_visit);
				temperaturetextview.setText(vd.temperature + "F");

				bphtextview.setText(String.valueOf(vd.bp_high) + "bp");
				bpltextview.setText(String.valueOf(vd.bp_low) + "bp");

				hrmaxtextview
						.setText(String.valueOf(vd.heart_rate_max) + "BPM");
				hrmintextview
						.setText(String.valueOf(vd.heart_rate_min) + "BPM");

			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

	public ShowVisitDataFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		database = new DatabaseHelper(getActivity());
		visitdatalist = new ArrayList<VisitData>();

		temperaturelist = new ArrayList<String>();
		bloodpressurelist = new ArrayList<String>();
		bplbloodpressurelist = new ArrayList<String>();
		heartratelist = new ArrayList<String>();
		minheartratelist = new ArrayList<String>();

		datelist = new ArrayList<String>();
		p = (Patient) getArguments().getSerializable("Patient");

		Print_Message(p.Patient_id + " " + p.Name + " " + "Visit count="
				+ p.visit_count);
	}

	@SuppressLint("UseValueOf")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.firstvisit, container,
				false);

		Button addvisit = (Button) rootView.findViewById(R.id.addvisitbutton);
		addvisit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				AddVisitFragment fragment = new AddVisitFragment();
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				fragment.setArguments(data);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		TextView hgraph = (TextView) rootView.findViewById(R.id.hgraph);
		hgraph.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (heartratelist.size() != 0) {
					GraphFragment fragment = new GraphFragment();

					Bundle data = new Bundle();

					data.putSerializable("Patient", p);
					data.putStringArrayList("List", heartratelist);
					data.putStringArrayList("DList", datelist);
					data.putDouble("MAX", maxheartrate);
					data.putDouble("MIN", minheartrate);
					data.putString("NAME", "Heart Rate Graph");
					data.putInt("FROM", 0);

					fragment.setArguments(data);
					getFragmentManager().beginTransaction()
							.replace(R.id.item_detail_container, fragment)
							.commit();
				}

			}
		});

		TextView hrmingraph = (TextView) rootView.findViewById(R.id.hrmingraph);
		hrmingraph.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (minheartratelist.size() != 0) {
					GraphFragment fragment = new GraphFragment();

					Bundle data = new Bundle();

					data.putSerializable("Patient", p);
					data.putStringArrayList("List", minheartratelist);
					data.putStringArrayList("DList", datelist);
					data.putDouble("MAX", minmaxheartrate);
					data.putDouble("MIN", minminheartrate);
					data.putString("NAME", "Heart Rate Min Graph");
					data.putInt("FROM", 0);

					fragment.setArguments(data);
					getFragmentManager().beginTransaction()
							.replace(R.id.item_detail_container, fragment)
							.commit();
				}

			}
		});

		TextView tgraph = (TextView) rootView.findViewById(R.id.tgraph);
		tgraph.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (temperaturelist.size() != 0) {
					GraphFragment fragment = new GraphFragment();
					Bundle data = new Bundle();

					data.putSerializable("Patient", p);
					data.putStringArrayList("List", temperaturelist);
					data.putStringArrayList("DList", datelist);
					data.putDouble("MAX", maxtemperature);
					data.putDouble("MIN", mintemperature);
					data.putString("NAME", "Temperature Graph");
					data.putInt("FROM", 0);

					fragment.setArguments(data);
					getFragmentManager().beginTransaction()
							.replace(R.id.item_detail_container, fragment)
							.commit();
				}

			}
		});

		TextView bgraph = (TextView) rootView.findViewById(R.id.bgraph);
		bgraph.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (bloodpressurelist.size() != 0) {
					GraphFragment fragment = new GraphFragment();
					Bundle data = new Bundle();

					data.putSerializable("Patient", p);
					data.putStringArrayList("List", bloodpressurelist);
					data.putStringArrayList("DList", datelist);
					data.putDouble("MAX", maxbloodpressure);
					data.putDouble("MIN", minbloodpressure);
					data.putString("NAME", "Blood Pressure High Graph");
					data.putInt("FROM", 0);

					fragment.setArguments(data);
					getFragmentManager().beginTransaction()
							.replace(R.id.item_detail_container, fragment)
							.commit();
				}

			}
		});

		TextView bplgraph = (TextView) rootView.findViewById(R.id.bplgraph);
		bplgraph.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (bplbloodpressurelist.size() != 0) {
					GraphFragment fragment = new GraphFragment();
					Bundle data = new Bundle();

					data.putSerializable("Patient", p);
					data.putStringArrayList("List", bplbloodpressurelist);
					data.putStringArrayList("DList", datelist);
					data.putDouble("MAX", bplmaxbloodpressure);
					data.putDouble("MIN", bplminbloodpressure);
					data.putString("NAME", "Blood Pressure Low Graph");
					data.putInt("FROM", 0);

					fragment.setArguments(data);
					getFragmentManager().beginTransaction()
							.replace(R.id.item_detail_container, fragment)
							.commit();
				}

			}
		});

		visitdatetextview = (TextView) rootView
				.findViewById(R.id.patientvisitdate);
		visitnumbertextview = (TextView) rootView
				.findViewById(R.id.patientvisitnumber);
		temperaturetextview = (TextView) rootView
				.findViewById(R.id.patienttemperature);
		purposetextview = (TextView) rootView.findViewById(R.id.purposevisit);
		bphtextview = (TextView) rootView.findViewById(R.id.bloodpressurehigh);
		bpltextview = (TextView) rootView.findViewById(R.id.bloodpressurelow);
		hrmaxtextview = (TextView) rootView.findViewById(R.id.heartratemax);
		hrmintextview = (TextView) rootView.findViewById(R.id.heartratemin);

		ArrayAdapter<CharSequence> visitdateadapter = new ArrayAdapter<CharSequence>(
				getActivity(), android.R.layout.simple_spinner_item);
		visitdateadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		visitdateadapter.add("Select A Visit");

		Cursor c = database.getVisitDataListByPatientId(p.Patient_id);

		getActivity().startManagingCursor(c);
		if (c != null) {
			c.moveToFirst();
			while (!c.isAfterLast()) {
				int id = c.getInt(0);

				int visit_id = c.getInt(1);

				int patient_id = c.getInt(2);

				String purpose_visit = c.getString(3);

				String date = c.getString(4);

				String temperature = c.getString(5);
				int weight = c.getInt(6);
				int bph = c.getInt(7);
				int bpl = c.getInt(8);
				int hrmin = c.getInt(9);
				int hrmax = c.getInt(10);

				Double t = new Double(temperature);

				if (t > maxtemperature) {
					maxtemperature = t;
				}

				if (t < mintemperature) {
					mintemperature = t;

				}

				if (bph != 0) {
					Double b = new Double(bph);

					if (b > maxbloodpressure) {
						maxbloodpressure = b;
					}

					if (b < minbloodpressure) {
						minbloodpressure = b;

					}
					bloodpressurelist.add(String.valueOf(bph));
				}
				if (hrmax != 0) {

					Double h = new Double(hrmax);

					if (h > maxheartrate) {
						maxheartrate = h;
					}

					if (h < minheartrate) {
						minheartrate = h;

					}
					heartratelist.add(String.valueOf(hrmax));
				}

				if (hrmin != 0) {
					Double hrmintemp = new Double(hrmin);

					if (hrmintemp > minmaxheartrate) {
						minmaxheartrate = hrmintemp;
					}

					if (hrmintemp < minminheartrate) {
						minminheartrate = hrmintemp;

					}

					minheartratelist.add(String.valueOf(hrmin));
				}

				if (bpl != 0) {
					Double bpltemp = new Double(bpl);

					if (bpltemp > bplmaxbloodpressure) {
						bplmaxbloodpressure = bpltemp;
					}

					if (bpltemp < bplminbloodpressure) {
						bplminbloodpressure = bpltemp;

					}

					bplbloodpressurelist.add(String.valueOf(bpl));
				}

				temperaturelist.add(temperature);

				VisitData vd = new VisitData(id, patient_id, visit_id,
						purpose_visit, date, temperature, weight, bph, bpl,
						hrmin, hrmax);

				visitdatalist.add(vd);
				visitdateadapter.add(date);

				visitdatetextview.setText(date);
				Print_Message(date);
				visitnumbertextview.setText(String.valueOf(visit_id));
				temperaturetextview.setText(temperature + "F");
				purposetextview.setText(purpose_visit);
				bphtextview.setText(String.valueOf(bph) + "bp");
				bpltextview.setText(String.valueOf(bpl) + "bp");

				hrmaxtextview.setText(String.valueOf(hrmax) + "BPM");
				hrmintextview.setText(String.valueOf(hrmin) + "BPM");

				datelist.add(date);

				c.moveToNext();

			}

		}

		Spinner visitdatespinner = (Spinner) rootView
				.findViewById(R.id.visitdatespinner);
		visitdatespinner.setAdapter(visitdateadapter);

		visitdatespinner
				.setOnItemSelectedListener(new YourItemSelectedListener());

		return rootView;

	}

	public void Show() {

		Log.d("MSG", "In show Funtion");

	}

}
