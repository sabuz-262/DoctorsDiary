package com.datashow;


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
import com.example.test3.R;



public class MessageFragment extends Fragment implements OnTabChangeListener {

	private static final String TAG = "FragmentTabs";
	
	
	
	public static final String TAB_LIST = "List";
	public static final String TAB_ADDMSG = "Add Msg";
	
	
	
	
	
	

	private View mRoot;
	private TabHost mTabHost;
	private int mCurrentTab;

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

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);

		mTabHost.setOnTabChangedListener(this);
		mTabHost.setCurrentTab(mCurrentTab);
		// manually start loading stuff in the first tab
		updateTab(TAB_LIST, R.id.tab_1);
	}

	private void setupTabs() {
		mTabHost.setup(); // important!
		
		
		mTabHost.addTab(newTab(TAB_LIST, R.string.tab_msg_list, R.id.tab_1));
		mTabHost.addTab(newTab(TAB_ADDMSG, R.string.tab_add_msg, R.id.tab_2));
		
		
	}

	private TabSpec newTab(String tag, int labelId, int tabContentId) {
		Log.d(TAG, "buildTab(): tag=" + tag);

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
		Log.d(TAG, "onTabChanged(): tabId=" + tabId);
		if (TAB_LIST.equals(tabId)) {
			updateTab(tabId, R.id.tab_1);
			mCurrentTab = 0;
			return;
		}
		if (TAB_ADDMSG.equals(tabId)) {
			updateTab(tabId, R.id.tab_2);
			mCurrentTab = 1;
			return;
		}
		
		
		
		
		
	}

	private void updateTab(String tabId, int placeholder) {
		
		Log.d(TAG, "Update Tab" + tabId);
		
		FragmentManager fm = getFragmentManager();
		
		if(tabId.equals(TAB_LIST))
		{
			fm.beginTransaction()
			.replace(placeholder, new PatientListForMessageFragment(), tabId)
			.commit();
		}
		
		else if(tabId.equals(TAB_ADDMSG))
		{
			fm.beginTransaction()
			.replace(placeholder, new SendMessageFragment(), tabId)
			.commit();
		}
		
		
		
		
		
//		if (fm.findFragmentByTag(tabId) == null) {
//			fm.beginTransaction()
//					.replace(placeholder, new ItemListFragment(), tabId)
//					.commit();
//		}
	}

}
