package com.datashow;

/**
 * This class show test report data
 * 
 * @author Sabuz
 *
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import com.example.test3.R;
import com.example.test3.AddTestReportFragment;
import com.mkyong.android.adaptor.ShowTestReportAdapter;
import com.myclass.Patient;

public class ShowTestReportFragment extends Fragment {
	ListView memuList;
	static final String[] menuItems = new String[] { "Blood Test", "Urine Test" };
	boolean flag = false;
	Patient p;
	int lastExpandedGroupPosition = 0;

	public void Print_Message(String msg) {
		Log.d("MSG STRF", msg);
	}
	public ShowTestReportFragment() {
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
		final View rootView = inflater.inflate(R.layout.showtestreport,
				container, false);
		final ExpandableListView l = (ExpandableListView) rootView
				.findViewById(R.id.menulist);
		l.setAdapter(new ShowTestReportAdapter(getActivity().getBaseContext(),
				menuItems, getActivity(), p));
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

		Button addtest = (Button) rootView
				.findViewById(R.id.addtestreportbutton);
		addtest.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AddTestReportFragment fragment = new AddTestReportFragment();
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
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
