package com.example.test3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchPatientFragment extends Fragment {
	
	
	void Toast_Show(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	public SearchPatientFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("MSG", "ItemDetailFragment  createview");

		final View rootView = inflater.inflate(R.layout.search, container,
				false);

		final Spinner searchspinner = (Spinner) rootView.findViewById(R.id.searchspinner);

		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				getActivity(), android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		adapter.add("Select Option");
		adapter.add("Name");
		adapter.add("Age");
		adapter.add("Added Date");
		
		searchspinner.setAdapter(adapter);
		
		

		Button search = (Button) rootView
				.findViewById(R.id.searchpatientbutton);
		search.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText searchtextbox=(EditText)rootView.findViewById(R.id.searchtextbox);
				String text=searchtextbox.getText().toString();
				if(text.equals(""))
				{
					
					Toast_Show("Write Name or Age or Date");
				}
				else if((int)searchspinner.getSelectedItemId()==0)
				{
					Toast_Show("Select Catagory");
					
				}
				else
				{
				PatientListFragmentforSearch fragment = new PatientListFragmentforSearch();
				Bundle data = new Bundle();
				data.putInt("CAT",(int)searchspinner.getSelectedItemId());
				data.putString("Search", text);
				fragment.setArguments(data);
				
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();
				}

			}
		});

		return rootView;

	}
}
