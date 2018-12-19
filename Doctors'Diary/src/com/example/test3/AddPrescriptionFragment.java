package com.example.test3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;

import com.myclass.AddAdvice;
import com.myclass.AddDisease;
import com.myclass.AddMedicine;
import com.myclass.AddTest;
import com.myclass.Patient;

import database.com.DatabaseHelper;

public class AddPrescriptionFragment extends Fragment {

	private ExpandableListView exlistview;
	static final String[] items = new String[] { "Disease", "Medicine", "Test",
			"Advice", "Audio Note" };

	private static final String[] dname = new String[] { "Achondroplasia",
			"Anosmia", "Allergies", "Acne", "Adenoma", "Albinism", "Allergy",
			"Alopecia", "Anthrax", "Argyria", "Arthritis",
			"Bacterial meningitis", "Beriberi", "Black death", "Botulism",
			"Bronchitis", "Brucellosis", "Bubonic plague",
			"Carbon monoxide poisoning", "Campylobacter infection", "Cancer",
			"Cerebral palsy", "Chancroid", "Crohn's disease",
			"Coccidioidomycosis", "Cholera", "Cowpox", "Dengue",
			"Diabetes mellitus", "Diphtheria", "Dehydration", "Diarrhea",
			"Depression", "Ear infection", "Eczema", "Endometriosis",
			"Eye disorders", "Ebola", "Encephalitis", "Emphysema", "epilepsy",
			"erectile dysfunction", "exema", "Fibroids", "Fibromyalgia",
			"Food poisoning", "Foodborne illness", "Genital herpes",
			"Gonorrhea", "Gangrene", "Gastroenteritis", "Genital herpes",
			"Gerd", "Hepatitis A", "Hepatitis B", "Hepatitis C", "Hepatitis D",
			"HIV", "Influenza", "Iron-deficiency anemia", "Ignious syndrome",
			"Keloids", "Malaria", "Measles", "Mononucleosis", "Mumps",
			"Myoclonus", "Myopia", "Myxedema", "Morquio syndrome",
			"Mattticular syndrome", "Migraine", "Narcolepsy" };

	private static final String[] medicinename = new String[] {
			"Abacavir Sulfate (Ziagen)", "Abarelix (Plenaxis)",
			"Abatacept (Orencia)", "Abilify (Aripiprazole)",
			"Absorica (Isotretinoin)", "Acamprosate Calcium (Campral)",
			"Acamprosate Calcium (Campral)", "Acarbose (Precose)",
			"Accolate (Zafirlukast)", "Accutane (Isotretinoin)",

			"Bacitracin (Bacitracin)", "Baclofen (Baclofen Tablets)",
			"Baclofen Tablets (Baclofen)", "Balsalazide (Colazal)",
			"Baraclude (Entecavir)", "Bayer (Aspirin)",
			"Baygam (Immune Globulin)", "Belatacept (Nulojix)",
			"Belimumab (Benlysta)", "Benzocaine (Americaine)",
			"Bosentan (Tracleer)", "Brevibloc (Esmolol)",
			"Brilinta (Ticagrelor)", "Buspirone (Buspar)",

			"Cabazitaxel Injection (Jevtana)", "Cabergoline (Dostinex)",
			"Cafcit (Caffeine Citrate)", "Calan (Verapamil HCl)",
			"Calcijex Injection (Calcitrol)", "Calcipotriene Foam (Sorilux)",
			"Calcitriol (Rocaltrol)", "Calfactant (Infasurf)",
			"Campath (Alemtuzumab)", "Canasa (Mesalamine)",
			"Capecitabine (Xeloda)", "Capoten (Captopril)",
			"Capoten (Captopril)", "Caprelsa (Vandetanib)", "",
			"Captopril (Capoten)",

			"Dacarbazine (Dtic-Dome)", "Dacarbazine (Dtic-Dome)",
			"Daclizumab (Zenapax)", "Dacogen (Decitabine Injection)",
			"Daliresp (roflumilast)", "Dalmane (Flurazepam)",
			"Danazol (Danocrine)", "Dapsone (Aczone Gel)",
			"Declomycin (Demeclocycline HCl)", "Deferasirox (Exjade)",
			"Denosumab (Xgeva)",

			"Edecrin (Ethacrynic Acid)", "Edetate (Endrate)",
			"Efalizumab (Raptiva)", "Eflornithine (Vaniqa)",
			"Efudex (Fluorouracil)", "Elimite (Permethrin)",
			"Elitek (Rasburicase)", "Elitek (Rasburicase)",
			"Elspar (Asparaginase)", "Emtricitabine (Emtriva)",

			"Fabior (Tazarotene)", "Factrel (Gonadorelin)", 
			"Famciclovir (Famvir)", "Famciclovir (Famvir)", "Famvir (Famciclovir)",
			"Fareston (Toremifene)", "Faslodex (Fulvestrant)", "Fastin (Phentermine)", "Fazaclo (Clozapine)", 
			"Felbatol (Felbamate)", "Feldene (Piroxicam)", "Femara (Letrozole)", "Fenofibrate (Antara)", "Fenofibrate (Lipofen)",
			"Fenofibrate (Triglide)", "Finasteride (Propecia)",
			
			
			"gadobutrol (Gadavist)", "Gadodiamide (Omniscan)", "Ganciclovir (Vitrasert)",
			"Gemzar (Gemcitabine Hcl)", "Geodon (Ziprasidone)", "Glipizide (Glucotrol)", "Glucotrol (Glipizide)", 
			"Glyburide (Micronase)", "Glycopyrrolate (Robinul)", "Glyset (Miglitol)", "Gonadorelin (Factrel)", "Granisetron (Kytril)",
			"Gris Peg (Griseofulvin)", "Glyburide (Micronase)",

			"Halcion (Triazolam)", "Haldol (Haloperidol Injection)", "Healon (Sodium Hyaluronate)", "Helidac (Bismuth Subsalicylate)", 
			"Hemin (Panhematin)", "Heparin (Heparin)", "Hepsera (Adefovir Dipivoxil)", "Hexalen (Altretamine)", "Hiprex (Methenamine Hippurate)",
			"Humira (Adalimumab)"
			};

	private static final String[] Time = new String[] { "Select Time", "1-1-1",
			"1-0-1", "0-0-1", "1-0-0", "0-1-0", "1-1-1-1" };

	ExpandableListAdapter adapter;

	Patient p;
	int lastExpandedGroupPosition = 0;

	ArrayList<String> diseaselist;
	ArrayList<String> medicinelist;
	ArrayList<String> testlist;
	ArrayList<String> advicelist;
	DatabaseHelper database;

	String Mainadvice = "";

	void Toast_Show(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

	}

	public class youronGroupcollapseListener implements OnGroupCollapseListener {

		@Override
		public void onGroupCollapse(int groupPosition) {
			// TODO Auto-generated method stub
			if (groupPosition == 0) {

				if (diseaselist.size() != 0) {
					Add_DiseaseToDatabase();
					diseaselist.clear();

				}
			} else if (groupPosition == 1) {
				if (medicinelist.size() != 0) {
					Add_MedicineToDatabase();
					medicinelist.clear();
				}
			} else if (groupPosition == 2) {
				if (testlist.size() != 0) {
					Add_TestToDatabase();
					testlist.clear();
				}
			} else if (groupPosition == 3) {
				if (advicelist.size() != 0) {
					if (!Mainadvice.equals("")) {
						Add_AdviceList(Mainadvice);
						Add_AdviceToDatabase();
						advicelist.clear();
					}
				}
			}

		}

	}

	public void Add_DiseaseToDatabase() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String date = df.format(c.getTime());
		AddDisease ad = new AddDisease(p.Patient_id, p.visit_count, date,
				diseaselist);
		database.addDisease(ad);
		Toast_Show(" Successfully Added Disease");

	}

	public void SendSms() {
		String phoneNo = p.Mobile.toString();
		String sms = "";

		for (int i = 0; i < medicinelist.size(); i++) {
			sms = sms + medicinelist.get(i) + "\n";

		}

		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, sms, null, null);
			Toast_Show("Successfully Send Message");

		} catch (Exception e) {
			Toast_Show("Can not  Send Message");
			e.printStackTrace();
		}
	}

	public void Add_MedicineToDatabase() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String date = df.format(c.getTime());
		AddMedicine ad = new AddMedicine(p.Patient_id, p.visit_count, date,
				medicinelist);
		database.addMedicine(ad);
		// SendSms();
		Toast_Show(" Successfully Added Medicine ");

	}

	public void Add_TestToDatabase() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String date = df.format(c.getTime());
		AddTest at = new AddTest(p.Patient_id, p.visit_count, date, testlist);
		database.addTest(at);
		Toast_Show(" Successfully Added Test");

	}

	public void Add_AdviceToDatabase() {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String date = df.format(c.getTime());
		AddAdvice ad = new AddAdvice(p.Patient_id, p.visit_count, date,
				advicelist);
		database.addAdvice(ad);
		Toast_Show(" Successfully Added Advice");

	}

	public void Add_DiseaseList(String name) {
		diseaselist.add(name);
		Print_Message("Add Diseaselist " + name);
	}

	public void Delete_FromDiseaseList(String name) {
		diseaselist.remove(name);

	}

	public void Add_MedicineList(String name) {
		medicinelist.add(name);
	}

	public void Delete_FromMedicineList(String name) {
		medicinelist.remove(name);

	}

	public void Add_TestList(String name) {
		testlist.add(name);
	}

	public void Delete_FromTestList(String name) {
		testlist.remove(name);

	}

	public void Add_AdviceList(String name) {
		advicelist.add(name);
	}

	public void Delete_FromAdviceList(String name) {
		advicelist.remove(name);

	}

	public void Print_Message(String msg) {
		Log.d("MSG APF", msg);

	}

	public AddPrescriptionFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		p = (Patient) getArguments().getSerializable("Patient");
		Print_Message(p.Patient_id + " " + p.Name + " " + "Visit count="
				+ p.visit_count);

		database = new DatabaseHelper(this.getActivity());

		diseaselist = new ArrayList<String>();
		medicinelist = new ArrayList<String>();
		testlist = new ArrayList<String>();
		advicelist = new ArrayList<String>();

	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		exlistview.collapseGroup(lastExpandedGroupPosition);
		Print_Message("ON DESTROY");
		
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.addprescription,
				container, false);
		TextView patientnametextview = (TextView) rootView
				.findViewById(R.id.patientnametextview);
		patientnametextview.setText("Name: " + p.Name + "  Age: " + p.Age);

		final ExpandableListView l = (ExpandableListView) rootView
				.findViewById(R.id.menulist);
		exlistview=l;

		l.setAdapter(new AddPrescriptionAdapter(getActivity().getBaseContext(),
				items, getActivity(), p, l));

		l.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				// TODO Auto-generated method stub
				if (groupPosition != lastExpandedGroupPosition) {
					l.collapseGroup(lastExpandedGroupPosition);
				}

				lastExpandedGroupPosition = groupPosition;

			}
		});

		l.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View arg1,
					int groupPosition, long arg3) {
				parent.smoothScrollToPosition(groupPosition);

				// Need default behaviour here otherwise group does not get
				// expanded/collapsed
				// on click
				if (parent.isGroupExpanded(groupPosition)) {
					parent.collapseGroup(groupPosition);
				} else {
					parent.expandGroup(groupPosition);
				}

				return true;
			}
		});

		l.setOnGroupCollapseListener(new youronGroupcollapseListener());

		l.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				return false;
			}
		});

		Button back = (Button) rootView.findViewById(R.id.backbutton);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Bundle data = new Bundle();

				data.putSerializable("Patient", p);

				data.putInt("FROM", 1);

				VisitFragment fragment = new VisitFragment();
				fragment.setArguments(data);
				l.collapseGroup(lastExpandedGroupPosition);

				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		return rootView;

	}

	class AddPrescriptionAdapter extends BaseExpandableListAdapter {
		FragmentActivity Act;

		private Context myContext;

		private String[] values;

		Patient p;

		View tempview;

		ExpandableListView exlistview;

		public AddPrescriptionAdapter(Context context, String[] value,
				FragmentActivity act, Patient p, ExpandableListView exlistview) {
			super();
			this.myContext = context;
			this.values = value;
			this.Act = act;
			this.p = p;
			this.exlistview = exlistview;

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
			return 4;
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

		@Override
		public View getChildView(int position, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			if (position == 0) {
				diseaselist = new ArrayList<String>();

				LayoutInflater inflater = (LayoutInflater) myContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				final View rootView = inflater.inflate(R.layout.adddisease,
						parent, false);

				final AutoCompleteTextView diseasename = (AutoCompleteTextView) rootView
						.findViewById(R.id.diseasenamepinner);

				diseasename.setThreshold(1);
				

				ArrayAdapter<String> diseasenameadapter = new ArrayAdapter<String>(
						Act, android.R.layout.simple_dropdown_item_1line, dname);
				diseasenameadapter
						.setDropDownViewResource(android.R.layout.simple_list_item_1);
				
				diseasename.setAdapter(diseasenameadapter);

				final TableLayout diseasetable = (TableLayout) rootView
						.findViewById(R.id.desiesetable);

				final LayoutInflater layoutInflater1 = (LayoutInflater) myContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				Button adddesieasebutton = (Button) rootView
						.findViewById(R.id.adddesiesebutton);

				adddesieasebutton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						final String txt = diseasename.getText().toString();
						if (txt.equals("")) {
							Toast_Show("Disease Name Can Not Empty");

						} else {
							// TODO Auto-generated method stub
							final TableRow diseasetablerow = (TableRow) layoutInflater1
									.inflate(R.layout.diseaselist, null);

							TextView tv = (TextView) diseasetablerow
									.findViewById(R.id.showdiseasename);
							diseasename.setText("");

							tv.setText(txt);
							Add_DiseaseList(txt);
							diseasetable.addView(diseasetablerow);

							Button delete = (Button) diseasetablerow
									.findViewById(R.id.deletedisease);
							delete.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Delete_FromDiseaseList(txt);
									diseasetable.removeView(diseasetablerow);
								}
							});
						}

					}
				});

				return rootView;
			}

			else if (position == 1) {

				medicinelist = new ArrayList<String>();
				LayoutInflater inflater = (LayoutInflater) myContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rootView = inflater.inflate(R.layout.addmedicine, parent,
						false);

				final AutoCompleteTextView medicinenameautocomplete = (AutoCompleteTextView) rootView
						.findViewById(R.id.medicinenamespinner);
				medicinenameautocomplete.setThreshold(1);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(Act,
						android.R.layout.simple_dropdown_item_1line, medicinename);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				medicinenameautocomplete.setAdapter(adapter);

				final Spinner timespinner = (Spinner) rootView
						.findViewById(R.id.medicinetimespinner);

				ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(
						Act, android.R.layout.simple_spinner_item, Time);
				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				timespinner.setAdapter(adapter1);

				final Spinner howlongspinner = (Spinner) rootView
						.findViewById(R.id.howlongspinner);

				ArrayAdapter<CharSequence> adapter11 = new ArrayAdapter<CharSequence>(
						Act, android.R.layout.simple_spinner_item);
				adapter11
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				for (int i = 0; i < 100; i++) {
					if (i == 0) {
						adapter11.add(" Select Day");

					} else {
						adapter11.add(" " + i + " Days");
					}
				}

				howlongspinner.setAdapter(adapter11);

				final TableLayout tl = (TableLayout) rootView
						.findViewById(R.id.medicinetable);

				final LayoutInflater layoutInflater1 = (LayoutInflater) Act
						.getBaseContext().getSystemService(
								Context.LAYOUT_INFLATER_SERVICE);

				Button addmedicnebutton = (Button) rootView
						.findViewById(R.id.addmedicinebutton);

				addmedicnebutton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						String medicinenametext = medicinenameautocomplete
								.getText().toString();

						if (medicinenametext.equals("")) {
							Toast_Show("Medicine Name Can Not Empty");
						} else if (timespinner.getSelectedItemId() == 0) {
							Toast_Show("Select Time");

						} else if (howlongspinner.getSelectedItemId() == 0) {
							Toast_Show("Select Day");
						} else {
							// TODO Auto-generated method stub
							final TableRow tr = (TableRow) layoutInflater1
									.inflate(R.layout.medicinelist, null);

							TextView tv = (TextView) tr
									.findViewById(R.id.showmedicnename);

							final String name = medicinenameautocomplete
									.getText().toString()
									+ "  "
									+ timespinner.getSelectedItem().toString()
									+ "  "
									+ howlongspinner.getSelectedItem()
											.toString();

							Print_Message(name);
							tv.setText(name);
							tl.addView(tr);
							Add_MedicineList(name);
							medicinenameautocomplete.setText("");

							Button delete = (Button) tr
									.findViewById(R.id.deletemedicine);
							delete.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Delete_FromMedicineList(name);
									tl.removeView(tr);
								}
							});
						}

					}
				});

				return rootView;
			}

			else if (position == 2) {
				testlist = new ArrayList<String>();
				LayoutInflater inflater = (LayoutInflater) myContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rootView = inflater.inflate(R.layout.addtest, parent,
						false);

				final Spinner testnameautocomplete = (Spinner) rootView
						.findViewById(R.id.testnamespinner);

				ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(
						Act, android.R.layout.simple_spinner_item);
				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				adapter1.add("Select Test Name");
				adapter1.add("Blood ESR(Erythrocyte Sedimentation Rate)");
				adapter1.add("Blood Platelets Count");
				adapter1.add("Blood BT,CT");
				adapter1.add("Blood CRP(C-Reactive Protien)");
				adapter1.add("Blood ASO");
				adapter1.add("Blood Sugar");
				adapter1.add("Blood Cholesterol");
				adapter1.add("Blood Uric Acid");

				adapter1.add("Urine Glucose");
				adapter1.add("Urine Red blood cells ");
				adapter1.add("Urine White blood cells ");
				adapter1.add("Urine hemoglobin");
				adapter1.add("Urine Ketone bodies");
				adapter1.add("Urine pH ");
				adapter1.add("Urinary Calcium ");
				adapter1.add("Urine Phosphate ");

				adapter1.add("Throat swab for Culture and Sensibity ");
				adapter1.add("Throat swab for Strepto Grouping ");

				testnameautocomplete.setAdapter(adapter1);

				final TableLayout tl = (TableLayout) rootView
						.findViewById(R.id.testtable);

				final LayoutInflater layoutInflater1 = (LayoutInflater) Act
						.getBaseContext().getSystemService(
								Context.LAYOUT_INFLATER_SERVICE);

				Button addmedicnebutton = (Button) rootView
						.findViewById(R.id.addtestbutton);

				addmedicnebutton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (testnameautocomplete.getSelectedItem().toString()
								.equals("Select Test Name")) {
							Toast_Show("Select Test Name");
						} else {
							final TableRow tr = (TableRow) layoutInflater1
									.inflate(

									R.layout.testlist, null);

							TextView tv = (TextView) tr
									.findViewById(R.id.showtestname);
							final String testname = testnameautocomplete
									.getSelectedItem().toString();
							tv.setText(testname);
							Add_TestList(testname);
							tl.addView(tr);

							Button delete = (Button) tr
									.findViewById(R.id.deletetestbutton);
							delete.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Delete_FromTestList(testname);
									tl.removeView(tr);
								}
							});
						}

					}
				});

				return rootView;
			}

			else

			{
				advicelist = new ArrayList<String>();
				LayoutInflater inflater = (LayoutInflater) myContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rootView = inflater.inflate(R.layout.addadvice, parent,
						false);

				tempview = rootView;

				final EditText advicetext = (EditText) rootView
						.findViewById(R.id.advicetextview);

				final Button addadvicebutton = (Button) rootView
						.findViewById(R.id.addadvicebutton);

				addadvicebutton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						final String advice = advicetext.getText().toString();
						if (advice.equals("")) {
							Toast_Show("Advice Can Not Empty");
						} else {
							// TODO Auto-generated method stub
							Mainadvice = advice;
							TextView showadvice = (TextView) tempview
									.findViewById(R.id.showadvice);
							showadvice.setText(advice);
							addadvicebutton.setText("Edit");

						}

					}
				});

				final Button finishbutton = (Button) rootView
						.findViewById(R.id.finish);

				finishbutton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						final String advice = advicetext.getText().toString();
						if (advice.equals("")) {
							Toast_Show("Advice Can Not Empty");
						} else {
							Add_AdviceList(advice);
							Add_AdviceToDatabase();

							Bundle data = new Bundle();

							data.putSerializable("Patient", p);

							data.putInt("FROM", 1);

							VisitFragment fragment = new VisitFragment();
							fragment.setArguments(data);

							getFragmentManager()
									.beginTransaction()
									.replace(R.id.item_detail_container,
											fragment).commit();

						}

					}
				});

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
			// ImageView imageView = (ImageView)
			// rowView.findViewById(R.id.logo);
			textView.setText(values[position]);

			/*
			 * if(position==0) { imageView.setImageResource(R.drawable.disease);
			 * 
			 * } else if(position==1) {
			 * imageView.setImageResource(R.drawable.medicine);
			 * 
			 * } else if(position==2) {
			 * imageView.setImageResource(R.drawable.test); } else
			 * if(position==3) { imageView.setImageResource(R.drawable.advice);
			 * }
			 */
			return rowView;
		}
	}

}
