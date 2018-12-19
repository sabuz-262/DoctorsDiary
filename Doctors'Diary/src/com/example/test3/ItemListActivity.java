package com.example.test3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.data.sink.DataSinkFragment;
import com.datashow.MessageFragment;

import database.com.DatabaseHelper;

public class ItemListActivity extends FragmentActivity implements
		ItemListFragment.Callbacks {
	DatabaseHelper dbHelper;

	private boolean mTwoPane;

	ListView markList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_twopane);

		if (findViewById(R.id.item_detail_container) != null) {
			mTwoPane = true;
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);

		}

	}

	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			if (id.equals("1")) {

				AddpatientFragment fragment = new AddpatientFragment();

				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}

			else if (id.equals("2")) {

				PatientListFragmentforaddvisit fragment = new PatientListFragmentforaddvisit();

				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}

			else if (id.equals("3")) {

				PatientListFragment fragment = new PatientListFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}

			else if (id.equals("4")) {

				SearchPatientFragment fragment = new SearchPatientFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			} else if (id.equals("5")) {

				AppoinmentFragment fragment = new AppoinmentFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}

			else if (id.equals("6")) {

				MessageFragment fragment = new MessageFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
			else if (id.equals("7")) {

				DataSinkFragment fragment=new DataSinkFragment();
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		}
	}

}
