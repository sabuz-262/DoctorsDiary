package com.example.test3;
/**
 * This class handle add test report data to database
 * 
 * @author Sabuz
 *
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import com.mkyong.android.adaptor.AddTestReportAdapter;
import com.myclass.Patient;
public class AddTestReportFragment extends Fragment {
	ListView menuList;
	static final String[] menuItems = new String[] { "Blood Test", "Urine Test" };
	ExpandableListAdapter menuAdapter;
	Patient p;
	int lastExpandedGroupPosition=0;

	public void printMessage(String msg) {
		Log.d("MSG ATRF", msg);
	}
	public AddTestReportFragment() {
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		p = (Patient) getArguments().getSerializable("Patient");
		printMessage(p.Patient_id + " " + p.Name + " " + "Visit count="
				+ p.visit_count);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.addtestreport,
				container, false);
		TextView patientnametextview=(TextView)rootView.findViewById(R.id.patientnametextview);
		patientnametextview.setText("Name: "+p.Name+"  Age: "+p.Age);
		
		final ExpandableListView l = (ExpandableListView) rootView
				.findViewById(R.id.menulist);
		l.setAdapter(new AddTestReportAdapter(getActivity().getBaseContext(),
				menuItems, getActivity(), p,l));
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
				if(groupPosition==1)
				{
					Bundle data = new Bundle();
					data.putSerializable("Patient", p);
					data.putInt("FROM", 2);
					VisitFragment fragment = new VisitFragment();
					fragment.setArguments(data);
					getFragmentManager().beginTransaction()
							.replace(R.id.item_detail_container, fragment).commit();
				}
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
		Button back = (Button) rootView.findViewById(R.id.backbutton);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				data.putInt("FROM", 2);
				VisitFragment fragment = new VisitFragment();
				fragment.setArguments(data);
			    getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();
			}
		});
		return rootView;
	}
}
