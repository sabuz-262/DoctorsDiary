package com.example.test3;

import com.datashow.ShowAudioConFragment;
import com.datashow.ShowAudioNoteFragment;
import com.datashow.ShowTestReportFragment;

import com.myclass.Patient;

import database.com.DatabaseHelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class VisitFragment extends Fragment implements OnTabChangeListener {

	public static final String TAG = "FragmentTabs";

	public static final String TAB_VISIT = "Visit";
	public static final String TAB_PRESCRPTION = "Prescription";

	public static final String TAB_TEST = "Test";
	public static final String TAB_CONVERSATION = "Conversation";

	public static final String TAB_PICTURE = "Picture";
	public static final String TAB_AUDIONOTE = "Audio Note";

	Patient p;

	DatabaseHelper database;

	private View mRoot;
	private TabHost mTabHost;
	private int mCurrentTab = 0;

	public void Print_Message(String msg) {
		Log.d("MSG VF", msg);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRoot = inflater.inflate(R.layout.tabs_fragment, null);
		mTabHost = (TabHost) mRoot.findViewById(android.R.id.tabhost);
		setupTabs();
		return mRoot;
	}

	public void Update_Patient_visitCount() {
		database.updatePatientVisitCount(p.Patient_id, p.visit_count);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);

		database = new DatabaseHelper(getActivity());

		p = (Patient) getArguments().getSerializable("Patient");

		int from = getArguments().getInt("FROM");
		if (from == 100) {
			p.visit_count = p.visit_count + 1;
			Update_Patient_visitCount();
			from = 0;
		}

		mTabHost.setOnTabChangedListener(this);

		if (from == 0) {
			mCurrentTab = from;
			mTabHost.setCurrentTab(mCurrentTab);

			// manually start loading stuff in the first tab
			updateTab(TAB_VISIT, R.id.tab_1);
		}

		else if (from == 1) {
			mCurrentTab = from;
			mTabHost.setCurrentTab(mCurrentTab);

			// manually start loading stuff in the first tab
			updateTab(TAB_PRESCRPTION, R.id.tab_2);

		} else if (from == 2) {
			mCurrentTab = from;
			mTabHost.setCurrentTab(mCurrentTab);

			// manually start loading stuff in the first tab
			updateTab(TAB_TEST, R.id.tab_3);

		}

		else if (from == 3) {
			mCurrentTab = from;
			mTabHost.setCurrentTab(mCurrentTab);

			// manually start loading stuff in the first tab
			updateTab(TAB_CONVERSATION, R.id.tab_4);

		} else if (from == 4) {
			mCurrentTab = from;
			mTabHost.setCurrentTab(mCurrentTab);

			// manually start loading stuff in the first tab
			updateTab(TAB_PICTURE, R.id.tab_5);

		} else if (from == 5) {
			mCurrentTab = from;
			mTabHost.setCurrentTab(mCurrentTab);

			// manually start loading stuff in the first tab
			updateTab(TAB_AUDIONOTE, R.id.tab_6);

		}

	}

	private void setupTabs() {
		mTabHost.setup(); // important!

		mTabHost.addTab(newTab(TAB_VISIT, R.string.tab_visit, R.id.tab_1));
		mTabHost.addTab(newTab(TAB_PRESCRPTION, R.string.tab_prescription,
				R.id.tab_2));

		mTabHost.addTab(newTab(TAB_TEST, R.string.tab_test, R.id.tab_3));
		mTabHost.addTab(newTab(TAB_CONVERSATION, R.string.tab_conversation,
				R.id.tab_4));

		mTabHost.addTab(newTab(TAB_PICTURE, R.string.tab_picture, R.id.tab_5));
		mTabHost.addTab(newTab(TAB_AUDIONOTE, R.string.tab_audio, R.id.tab_6));
	}

	private TabSpec newTab(String tag, int labelId, int tabContentId) {

		View indicator = LayoutInflater.from(getActivity()).inflate(
				R.layout.tab,
				(ViewGroup) mRoot.findViewById(android.R.id.tabs), false);
		((TextView) indicator.findViewById(R.id.text)).setText(labelId);

		TabSpec tabSpec = mTabHost.newTabSpec(tag);
		tabSpec.setIndicator(indicator);
		tabSpec.setContent(tabContentId);
		return tabSpec;
	}

	@Override
	public void onTabChanged(String tabId) {

		if (TAB_VISIT.equals(tabId)) {
			updateTab(tabId, R.id.tab_1);
			mCurrentTab = 0;
			return;
		}
		if (TAB_PRESCRPTION.equals(tabId)) {
			updateTab(tabId, R.id.tab_2);
			mCurrentTab = 1;
			return;
		}

		if (TAB_TEST.equals(tabId)) {
			updateTab(tabId, R.id.tab_3);
			mCurrentTab = 2;
			return;
		}
		if (TAB_CONVERSATION.equals(tabId)) {
			updateTab(tabId, R.id.tab_4);
			mCurrentTab = 3;
			return;
		}

		if (TAB_PICTURE.equals(tabId)) {
			updateTab(tabId, R.id.tab_5);
			mCurrentTab = 4;
			return;
		}
		if (TAB_AUDIONOTE.equals(tabId)) {
			updateTab(tabId, R.id.tab_6);
			mCurrentTab = 5;
			return;
		}

	}

	private void updateTab(String tabId, int placeholder) {

		FragmentManager fm = getFragmentManager();

		if (tabId.equals(TAB_VISIT)) {
			ShowVisitDataFragment fragment = new ShowVisitDataFragment();

			Bundle data = new Bundle();
			data.putSerializable("Patient", p);
			fragment.setArguments(data);

			fm.beginTransaction().replace(placeholder, fragment, tabId)
					.commit();

		}

		else if (tabId.equals(TAB_PRESCRPTION)) {
			ShowPrescriptionFragment fragment = new ShowPrescriptionFragment();

			Bundle data = new Bundle();
			data.putSerializable("Patient", p);
			fragment.setArguments(data);

			fm.beginTransaction().replace(placeholder, fragment, tabId)
					.commit();
		}

		else if (tabId.equals(TAB_TEST)) {
			ShowTestReportFragment fragment = new ShowTestReportFragment();

			Bundle data = new Bundle();
			data.putSerializable("Patient", p);
			fragment.setArguments(data);

			fm.beginTransaction().replace(placeholder, fragment, tabId)
					.commit();
		}

		else if (tabId.equals(TAB_CONVERSATION)) {
			ShowAudioConFragment fragment = new ShowAudioConFragment();

			Bundle data = new Bundle();
			data.putSerializable("Patient", p);
			fragment.setArguments(data);

			fm.beginTransaction().replace(placeholder, fragment, tabId)
					.commit();
		}

		else if (tabId.equals(TAB_PICTURE)) {
			ShowPictureFragment fragment = new ShowPictureFragment();

			Bundle data = new Bundle();
			data.putSerializable("Patient", p);
			fragment.setArguments(data);

			fm.beginTransaction().replace(placeholder, fragment, tabId)
					.commit();
		}

		else if (tabId.equals(TAB_AUDIONOTE)) {
			ShowAudioNoteFragment fragment = new ShowAudioNoteFragment();

			Bundle data = new Bundle();
			data.putSerializable("Patient", p);
			fragment.setArguments(data);

			fm.beginTransaction().replace(placeholder, fragment, tabId)
					.commit();
		}

	}

}
