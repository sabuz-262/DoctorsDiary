package com.mkyong.android.adaptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.R;
import com.myclass.BloodTest;
import com.myclass.Patient;
import com.myclass.UrineTest;

import database.com.DatabaseHelper;

public class AddTestReportAdapter extends BaseExpandableListAdapter {
	FragmentActivity Act;

	private Context myContext;

	private String[] values;

	Patient p;

	int hemoglobin = 0;
	int esr = 0;
	int wbc = 0;
	int neotrifilis = 0;
	int lymphocytes = 0;
	int monocytes = 0;
	int aso = 0;
	int crp = 0;

	int glucose = 0;
	int rbc = 0;
	int uwbc = 0;
	int uhemoglobin = 0;
	int pH = 0;

	DatabaseHelper database;
	ExpandableListView exview;

	public void Print_Message(String msg) {
		Log.d("MSG ATRF", msg);

	}

	void Toast_Show(String msg) {
		Toast.makeText(this.Act.getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	public AddTestReportAdapter(Context context, String[] value,
			FragmentActivity act, Patient patient,ExpandableListView exview) {
		super();
		this.myContext = context;
		this.values = value;
		this.Act = act;
		this.p = patient;
		this.exview=exview;

		database = new DatabaseHelper(this.Act);

	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return 2;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int position, int childPosition) {
		return true;
	}

	public void AddBloodTest() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String date = df.format(c.getTime());

		BloodTest bt = new BloodTest(p.Patient_id, p.visit_count, date,
				hemoglobin, esr, wbc, neotrifilis, lymphocytes, monocytes, aso,
				crp);
		database.Add_BloodTest(bt);
		Toast_Show(" Successfully Added Blood Test");
	}

	public void AddUrineTest() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String date = df.format(c.getTime());

		UrineTest ut = new UrineTest(p.Patient_id, p.visit_count, date,
				glucose, rbc, uwbc, uhemoglobin, pH);
		database.addUrineTest(ut);
		Toast_Show(" Successfully Added Urine Test");
	}

	@Override
	public View getChildView(int position, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = (LayoutInflater) myContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (position == 0) {
			final View rootView = inflater.inflate(R.layout.addbloodtest,
					parent, false);

			SeekBar hemseekbar = (SeekBar) rootView
					.findViewById(R.id.hemseekBar);
			final TextView hemtext = (TextView) rootView
					.findViewById(R.id.hemtext);

			hemseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							hemoglobin = arg1;
							hemtext.setText(arg1 + "%");
						}
					});

			SeekBar esrseekbar = (SeekBar) rootView
					.findViewById(R.id.esrseekBar);
			final TextView esrtext = (TextView) rootView
					.findViewById(R.id.esrtext);

			esrseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							esr = arg1;
							esrtext.setText(arg1 + "mm");
						}
					});

			SeekBar wbcseekbar = (SeekBar) rootView
					.findViewById(R.id.wbcseekBar);
			final TextView wbctext = (TextView) rootView
					.findViewById(R.id.wbctext);

			wbcseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							wbc = arg1;
							wbctext.setText(arg1 + "mm3");
						}
					});

			SeekBar neuseekbar = (SeekBar) rootView
					.findViewById(R.id.neuseekBar);
			final TextView neutext = (TextView) rootView
					.findViewById(R.id.neutext);

			neuseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							neotrifilis = arg1;
							neutext.setText(arg1 + "mm3");
						}
					});

			SeekBar lymseekbar = (SeekBar) rootView
					.findViewById(R.id.lymseekBar);
			final TextView lymtext = (TextView) rootView
					.findViewById(R.id.lymtext);

			lymseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							lymphocytes = arg1;
							lymtext.setText(arg1 + "mm3");
						}
					});

			SeekBar monoseekbar = (SeekBar) rootView
					.findViewById(R.id.monoseekBar);
			final TextView monotext = (TextView) rootView
					.findViewById(R.id.monotext);

			monoseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							monocytes = arg1;
							monotext.setText(arg1 + "mm3");
						}
					});

			SeekBar asoseekbar = (SeekBar) rootView
					.findViewById(R.id.asoseekBar);
			final TextView asotext = (TextView) rootView
					.findViewById(R.id.asotext);

			asoseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							aso = arg1;
							asotext.setText(arg1 + "mm3");
						}
					});

			RadioButton crppositive = (RadioButton) rootView
					.findViewById(R.id.crppositive);
			if (crppositive.isChecked()) {
				crp = 1;

			} else {
				crp = 0;
			}

			Button button = (Button) rootView.findViewById(R.id.finish);

			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					AddBloodTest();
					exview.collapseGroup(0);
				}

			});

			return rootView;
		} else if (position == 1) {
			final View rootView = inflater.inflate(R.layout.addurinetest,
					parent, false);

			SeekBar glseekbar = (SeekBar) rootView.findViewById(R.id.glseekBar);
			final TextView gltext = (TextView) rootView
					.findViewById(R.id.gltext);

			glseekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar arg0, int arg1,
						boolean arg2) {
					// TODO Auto-generated method stub
					glucose = arg1;
					gltext.setText(arg1 + "%");
				}
			});

			SeekBar redseekbar = (SeekBar) rootView
					.findViewById(R.id.redseekBar);
			final TextView redtext = (TextView) rootView
					.findViewById(R.id.redtext);

			redseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							rbc = arg1;
							redtext.setText(arg1 + "mm");
						}
					});

			SeekBar whiteseekbar = (SeekBar) rootView
					.findViewById(R.id.whiteseekBar);
			final TextView whitetext = (TextView) rootView
					.findViewById(R.id.whitetext);

			whiteseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							uwbc = arg1;
							whitetext.setText(arg1 + "mm3");
						}
					});

			SeekBar hemseekbar = (SeekBar) rootView
					.findViewById(R.id.hemseekBar);
			final TextView hemtext = (TextView) rootView
					.findViewById(R.id.hemtext);

			hemseekbar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onProgressChanged(SeekBar arg0, int arg1,
								boolean arg2) {
							// TODO Auto-generated method stub
							uhemoglobin = arg1;
							hemtext.setText(arg1 + "%");
						}
					});

			SeekBar phseekbar = (SeekBar) rootView.findViewById(R.id.phseekBar);
			final TextView phtext = (TextView) rootView
					.findViewById(R.id.phtext);

			phseekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar arg0, int arg1,
						boolean arg2) {
					// TODO Auto-generated method stub
					pH = arg1;
					phtext.setText(arg1 + "mm3");
				}
			});

			Button finishbutton = (Button) rootView.findViewById(R.id.finish);

			finishbutton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					AddUrineTest();
					exview.collapseGroup(1);
				}

			});

			return rootView;

		} else {
			final View rootView = inflater.inflate(R.layout.addthroatswabtest,
					parent, false);

			return rootView;

		}

	}

	@Override
	public View getGroupView(int position, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = (LayoutInflater) myContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.exadapter, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		// ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		if (position < 3) {
			textView.setText(values[position]);
		}

		return rowView;
	}
}