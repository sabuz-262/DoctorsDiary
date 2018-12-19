package com.example.test3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mkyong.android.adaptor.ShowPrescriptionAdapter;
import com.myclass.Patient;

public class ShowPrescriptionFragment extends Fragment {

	ListView markList;
	static final String[] items = new String[] { "Disease", "Medicine", "Test",
			"Advice", "Audio Note" };

	ExpandableListAdapter adapter;

	Patient p;
	int lastExpandedGroupPosition=0;

	public void Print_Message(String msg) {
		Log.d("MSG SPF", msg);

	}

	public ShowPrescriptionFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		p = (Patient) getArguments().getSerializable("Patient");
		Print_Message(p.Patient_id + " " + p.Name + " " + "Visit count="
				+ p.visit_count);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.showprescription,
				container, false);

		final ExpandableListView l = (ExpandableListView) rootView
				.findViewById(R.id.menulist);

		l.setAdapter(new ShowPrescriptionAdapter(
				getActivity().getBaseContext(), items, getActivity(), p));

l.setOnGroupExpandListener(new OnGroupExpandListener() {
			

			@Override
			public void onGroupExpand(int groupPosition) {
				// TODO Auto-generated method stub
				if(groupPosition != lastExpandedGroupPosition){
		            l.collapseGroup(lastExpandedGroupPosition);
		        }

		              
		        lastExpandedGroupPosition = groupPosition;
				
			}
		});

		l.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				Log.e("onGroupCollapse", "OK");
			}
		});

		l.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Log.e("OnChildClickListener", "OK");
				return false;
			}
		});

		Button addprescription = (Button) rootView
				.findViewById(R.id.addprescriptionbutton);
		addprescription.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				AddPrescriptionFragment fragment = new AddPrescriptionFragment();
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				data.putInt("FROM", 100);
				fragment.setArguments(data);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		return rootView;

	}

}
