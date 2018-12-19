package com.mkyong.android.adaptor;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.datashow.GraphFragment;
import com.example.test3.R;
import com.myclass.BloodTest;
import com.myclass.Patient;
import com.myclass.UrineTest;

import database.com.DatabaseHelper;

public class ShowTestReportAdapter extends BaseExpandableListAdapter {
	FragmentActivity Act;

	private Context myContext;

	private String[] values;

	TextView hemtextview;
	TextView esrtextview;
	TextView wbctextview;
	TextView neotextview;
	TextView lymtextview;
	TextView monotextview;
	TextView asotextview;
	TextView crptextview;

	ArrayList<String> bloodhemlist;
	ArrayList<String> esrlist;
	ArrayList<String> wbclist;
	ArrayList<String> neolist;
	ArrayList<String> lymlist;
	ArrayList<String> monolist;
	ArrayList<String> asolist;
	ArrayList<String> blooddatelist;

	ArrayList<String> glucoselist;
	ArrayList<String> rbclist;
	ArrayList<String> uwbclist;
	ArrayList<String> uhemlist;
	ArrayList<String> pHlist;
	ArrayList<String> urinedatelist;

	Double maxhem = 0.0;
	Double minhem = 10000.0;

	Double maxesr = 0.0;
	Double minesr = 10000.0;

	Double maxwbc = 0.0;
	Double minwbc = 10000.0;

	Double maxneo = 0.0;
	Double minneo = 10000.0;

	Double maxlym = 0.0;
	Double minlym = 10000.0;

	Double maxmono = 0.0;
	Double minmono = 10000.0;

	Double maxaso = 0.0;
	Double minaso = 10000.0;

	Double maxglucose = 0.0;
	Double minglucose = 10000.0;

	Double maxrbc = 0.0;
	Double minrbc = 10000.0;

	Double maxuwbc = 0.0;
	Double minuwbc = 10000.0;

	Double maxuhem = 0.0;
	Double minuhem = 10000.0;

	Double maxpH = 0.0;
	Double minpH = 10000.0;

	TextView glucosetextview;
	TextView rbctextview;
	TextView uwbctextview;
	TextView uhemtextview;
	TextView pHtextview;

	ArrayList<BloodTest> bloodtestlist;
	ArrayList<UrineTest> urinetestlist;
	DatabaseHelper database;

	Patient p;

	boolean flag = false;

	public class YourBloodTestItemSelectedListener implements
			OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub

			if (!flag) {
				flag = true;
			} else if (pos >= 1) {
				BloodTest bt = bloodtestlist.get(pos - 1);

				hemtextview.setText(bt.Hemoglobin + "%");
				esrtextview.setText(bt.ESR + "mm");
				wbctextview.setText(bt.WBC + "mm3");
				neotextview.setText(bt.Neutrophilis + "%");
				lymtextview.setText(bt.Lymphocytes + "%");
				monotextview.setText(bt.Monocytes + "%");
				asotextview.setText(bt.ASO + "%");
				if (bt.CRP == 1) {
					crptextview.setText("Positive");
				} else {
					crptextview.setText("Negative");

				}

			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

	public class YourUrineTestItemSelectedListener implements
			OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub

			if (!flag) {
				flag = true;
			} else if (pos >= 1) {
				UrineTest ut = urinetestlist.get(pos - 1);

				glucosetextview.setText(ut.Glucose + "%");
				rbctextview.setText(ut.Red_Blood_Cells + "mm3");
				uwbctextview.setText(ut.White_Blood_Cells + "mm3");
				uhemtextview.setText(ut.Hemoglobin + "%");
				pHtextview.setText(ut.pH + "");

			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

	public ShowTestReportAdapter(Context context, String[] value,
			FragmentActivity act, Patient p) {
		super();
		this.myContext = context;
		this.values = value;
		this.Act = act;
		this.p = p;
		database = new DatabaseHelper(this.Act);
		bloodtestlist = new ArrayList<BloodTest>();
		urinetestlist = new ArrayList<UrineTest>();

		bloodhemlist = new ArrayList<String>();
		esrlist = new ArrayList<String>();
		wbclist = new ArrayList<String>();
		neolist = new ArrayList<String>();
		lymlist = new ArrayList<String>();
		monolist = new ArrayList<String>();
		asolist = new ArrayList<String>();
		blooddatelist = new ArrayList<String>();

		glucoselist = new ArrayList<String>();
		rbclist = new ArrayList<String>();
		uwbclist = new ArrayList<String>();
		uhemlist = new ArrayList<String>();
		pHlist = new ArrayList<String>();
		urinedatelist = new ArrayList<String>();

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

	@SuppressLint("UseValueOf")
	@Override
	public View getChildView(int position, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = (LayoutInflater) myContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (position == 0) {
			final View rootView = inflater.inflate(R.layout.showbloodtest,
					parent, false);

			hemtextview = (TextView) rootView.findViewById(R.id.hemtext);
			esrtextview = (TextView) rootView.findViewById(R.id.esrtext);
			wbctextview = (TextView) rootView.findViewById(R.id.wbctext);
			neotextview = (TextView) rootView.findViewById(R.id.neotext);
			lymtextview = (TextView) rootView.findViewById(R.id.lymtext);
			monotextview = (TextView) rootView.findViewById(R.id.monotext);
			asotextview = (TextView) rootView.findViewById(R.id.asotext);
			crptextview = (TextView) rootView.findViewById(R.id.crptext);

			ArrayAdapter<CharSequence> dateadapter = new ArrayAdapter<CharSequence>(
					Act, android.R.layout.simple_spinner_item);
			dateadapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			dateadapter.add("Select A Visit");
			Cursor c = database.getBloodTestListByPatientId(p.Patient_id);

			Act.startManagingCursor(c);
			if (c != null) {
				c.moveToFirst();
				while (!c.isAfterLast()) {
					int id = c.getInt(0);
					int visit_count = c.getInt(1);
					int p_id = c.getInt(2);
					String date = c.getString(3);

					int hem = c.getInt(4);
					int esr = c.getInt(5);
					int wbc = c.getInt(6);
					int neo = c.getInt(7);
					int lym = c.getInt(8);
					int mono = c.getInt(9);
					int aso = c.getInt(10);
					int crp = c.getInt(11);

					BloodTest bt = new BloodTest(id, p_id, visit_count, date,
							hem, esr, wbc, neo, lym, mono, aso, crp);
					bloodtestlist.add(bt);

					dateadapter.add(date);

					blooddatelist.add(date);
					if (hem != 0) {
						Double hemvalue = new Double(hem);

						if (hemvalue > maxhem) {
							maxhem = hemvalue;
						}

						if (hemvalue < minhem) {
							minhem = hemvalue;

						}
					}

					if (esr != 0) {

						Double esrvalue = new Double(esr);

						if (esrvalue > maxesr) {
							maxesr = esrvalue;
						}

						if (esrvalue < minesr) {
							minesr = esrvalue;

						}
					}

					if (wbc != 0) {
						Double wbcvalue = new Double(wbc);

						if (wbcvalue > maxwbc) {
							maxwbc = wbcvalue;
						}

						if (wbcvalue < minwbc) {
							minwbc = wbcvalue;

						}
					}

					if (neo != 0) {
						Double neovalue = new Double(neo);
						if (neovalue > maxneo) {
							maxneo = neovalue;
						}

						if (neovalue < minneo) {
							minneo = neovalue;

						}
					}
					if (lym != 0) {

						Double lymvalue = new Double(lym);
						if (lymvalue > maxlym) {
							maxlym = lymvalue;
						}

						if (lymvalue < minlym) {
							minlym = lymvalue;

						}
					}

					if (mono != 0) {
						Double monovalue = new Double(mono);
						if (monovalue > maxmono) {
							maxmono = monovalue;
						}

						if (monovalue < minmono) {
							minmono = monovalue;

						}
					}
					if (aso != 0) {

						Double asovalue = new Double(aso);
						if (asovalue > maxaso) {
							maxaso = asovalue;
						}

						if (asovalue < minaso) {
							minaso = asovalue;

						}
					}

					bloodhemlist.add(String.valueOf(hem));
					esrlist.add(String.valueOf(esr));
					wbclist.add(String.valueOf(wbc));
					neolist.add(String.valueOf(neo));
					lymlist.add(String.valueOf(lym));
					monolist.add(String.valueOf(mono));
					asolist.add(String.valueOf(aso));

					hemtextview.setText(hem + "%");
					esrtextview.setText(esr + "mm");
					wbctextview.setText(wbc + "mm3");
					neotextview.setText(neo + "%");
					lymtextview.setText(lym + "%");
					monotextview.setText(mono + "%");
					asotextview.setText(String.valueOf(aso));
					if (crp == 1) {
						crptextview.setText("Positive");
					} else {
						crptextview.setText("Negative");

					}

					c.moveToNext();

				}

			}

			Spinner visitdatespinner = (Spinner) rootView
					.findViewById(R.id.visitdatespinner);
			visitdatespinner.setAdapter(dateadapter);
			visitdatespinner
					.setOnItemSelectedListener(new YourBloodTestItemSelectedListener());

			TextView hemgraph = (TextView) rootView.findViewById(R.id.hemgraph);
			hemgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (bloodhemlist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", bloodhemlist);
						data.putStringArrayList("DList", blooddatelist);
						data.putDouble("MAX", maxhem);
						data.putDouble("MIN", minhem);
						data.putString("NAME", "Blood Test Hemoglobin Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView esrgraph = (TextView) rootView.findViewById(R.id.esrgraph);
			esrgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (esrlist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", esrlist);
						data.putStringArrayList("DList", blooddatelist);
						data.putDouble("MAX", maxesr);
						data.putDouble("MIN", minesr);
						data.putString("NAME", "Blood Test ESR Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView wbcgraph = (TextView) rootView.findViewById(R.id.wbcgraph);
			wbcgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (wbclist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", wbclist);
						data.putStringArrayList("DList", blooddatelist);
						data.putDouble("MAX", maxwbc);
						data.putDouble("MIN", minwbc);
						data.putString("NAME", "Blood Test WBC Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView neograph = (TextView) rootView.findViewById(R.id.neograph);
			neograph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (neolist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", neolist);
						data.putStringArrayList("DList", blooddatelist);
						data.putDouble("MAX", maxneo);
						data.putDouble("MIN", minneo);
						data.putString("NAME", "Blood Test Neotriphilis Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView lymgraph = (TextView) rootView.findViewById(R.id.lymgraph);
			lymgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (lymlist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", lymlist);
						data.putStringArrayList("DList", blooddatelist);
						data.putDouble("MAX", maxlym);
						data.putDouble("MIN", minlym);
						data.putString("NAME", "Blood Test Lymphocytes Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView monograph = (TextView) rootView
					.findViewById(R.id.monograph);
			monograph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (monolist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", monolist);
						data.putStringArrayList("DList", blooddatelist);
						data.putDouble("MAX", maxmono);
						data.putDouble("MIN", minmono);
						data.putString("NAME", "Blood Test Monocytes Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView asograph = (TextView) rootView.findViewById(R.id.asograph);
			asograph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (asolist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", asolist);
						data.putStringArrayList("DList", blooddatelist);
						data.putDouble("MAX", maxaso);
						data.putDouble("MIN", minaso);
						data.putString("NAME", "Blood Test ASO Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			return rootView;
		} else if (position == 1) {
			final View rootView = inflater.inflate(R.layout.showurinetest,
					parent, false);

			glucosetextview = (TextView) rootView
					.findViewById(R.id.glucosetext);
			rbctextview = (TextView) rootView.findViewById(R.id.rbctext);
			uwbctextview = (TextView) rootView.findViewById(R.id.uwbctext);
			uhemtextview = (TextView) rootView.findViewById(R.id.uhemtext);
			pHtextview = (TextView) rootView.findViewById(R.id.phtext);

			ArrayAdapter<CharSequence> dateadapter = new ArrayAdapter<CharSequence>(
					Act, android.R.layout.simple_spinner_item);
			dateadapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			dateadapter.add("Select A Visit");
			Cursor c = database.getUrineTestListByPatientId(p.Patient_id);

			Act.startManagingCursor(c);
			if (c != null) {
				c.moveToFirst();
				while (!c.isAfterLast()) {
					int id = c.getInt(0);
					// int visit_count=c.getInt(1);
					// int p_id=c.getInt(2);
					String date = c.getString(3);

					int glucose = c.getInt(4);
					int rbc = c.getInt(5);
					int uwbc = c.getInt(6);
					int uhem = c.getInt(7);
					int ph = c.getInt(8);

					urinedatelist.add(date);

					if (glucose != 0) {
						Double glucosevalue = new Double(glucose);

						if (glucosevalue > maxglucose) {
							maxglucose = glucosevalue;
						}

						if (glucosevalue < minglucose) {
							minglucose = glucosevalue;

						}
					}

					if (rbc != 0) {
						Double rbcvalue = new Double(rbc);

						if (rbcvalue > maxrbc) {
							maxrbc = rbcvalue;
						}

						if (rbcvalue < minrbc) {
							minrbc = rbcvalue;

						}
					}
					if (uwbc != 0) {

						Double uwbcvalue = new Double(uwbc);

						if (uwbcvalue > maxuwbc) {
							maxuwbc = uwbcvalue;
						}

						if (uwbcvalue < minuwbc) {
							minuwbc = uwbcvalue;

						}
					}

					if (uhem != 0) {

						Double uhemvalue = new Double(uhem);

						if (uhemvalue > maxuhem) {
							maxuhem = uhemvalue;
						}

						if (uhemvalue < minuhem) {
							minuhem = uhemvalue;

						}
					}
					if (ph != 0) {

						Double pHvalue = new Double(ph);

						if (pHvalue > maxpH) {
							maxpH = pHvalue;
						}

						if (pHvalue < minpH) {
							minpH = pHvalue;

						}
					}

					glucoselist.add(String.valueOf(glucose));
					rbclist.add(String.valueOf(rbc));
					uwbclist.add(String.valueOf(uwbc));
					uhemlist.add(String.valueOf(uhem));
					pHlist.add(String.valueOf(ph));

					UrineTest ut = new UrineTest(id, p.Patient_id,
							p.visit_count, date, glucose, rbc, uwbc, uhem, ph);
					urinetestlist.add(ut);

					dateadapter.add(date);

					glucosetextview.setText(glucose + "%");
					rbctextview.setText(rbc + "mm3");
					uwbctextview.setText(uwbc + "mm3");
					uhemtextview.setText(uhem + "%");
					pHtextview.setText(ph + "%");

					c.moveToNext();

				}

			}

			Spinner visitdatespinner = (Spinner) rootView
					.findViewById(R.id.visitdatespinner);
			visitdatespinner.setAdapter(dateadapter);
			visitdatespinner
					.setOnItemSelectedListener(new YourUrineTestItemSelectedListener());

			TextView glucosegraph = (TextView) rootView
					.findViewById(R.id.glucosegraph);
			glucosegraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (glucoselist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", glucoselist);
						data.putStringArrayList("DList", urinedatelist);
						data.putDouble("MAX", maxglucose);
						data.putDouble("MIN", minglucose);
						data.putString("NAME", "Urine Test Glucose Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView rbcgraph = (TextView) rootView.findViewById(R.id.rbcgraph);
			rbcgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (rbclist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", rbclist);
						data.putStringArrayList("DList", urinedatelist);
						data.putDouble("MAX", maxrbc);
						data.putDouble("MIN", minrbc);
						data.putString("NAME", "Urine Test RBC Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView uwbcgraph = (TextView) rootView
					.findViewById(R.id.uwbcgraph);
			uwbcgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (uwbclist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", uwbclist);
						data.putStringArrayList("DList", urinedatelist);
						data.putDouble("MAX", maxuwbc);
						data.putDouble("MIN", minuwbc);
						data.putString("NAME", "Urine Test WBC Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView uhemgraph = (TextView) rootView
					.findViewById(R.id.uhemgraph);
			uhemgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (uhemlist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", uhemlist);
						data.putStringArrayList("DList", urinedatelist);
						data.putDouble("MAX", maxuhem);
						data.putDouble("MIN", minuhem);
						data.putString("NAME", "Urine Test Hemoglobin Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			TextView pHgraph = (TextView) rootView.findViewById(R.id.pHgraph);
			pHgraph.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					if (pHlist.size() != 0) {
						GraphFragment fragment = new GraphFragment();
						Bundle data = new Bundle();
						data.putSerializable("Patient", p);
						data.putStringArrayList("List", pHlist);
						data.putStringArrayList("DList", urinedatelist);
						data.putDouble("MAX", maxpH);
						data.putDouble("MIN", minpH);
						data.putString("NAME", "Urine Test pH Graph");
						data.putInt("FROM", 2);
						fragment.setArguments(data);
						Act.getSupportFragmentManager().beginTransaction()
								.replace(R.id.item_detail_container, fragment)
								.commit();
					}
				}
			});

			return rootView;

		}

		else

		{

			final View rootView = inflater.inflate(R.layout.showtroatswabtest,
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