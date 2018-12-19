package com.datashow;

/**
 * This class handle to show Graph
 * 
 * @author Sabuz
 *
 */
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.test3.R;
import com.example.test3.VisitFragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.myclass.Patient;

public class GraphFragment extends Fragment {
	Patient p;
	ArrayList<String> valueaList;
	ArrayList<String> dateList;
	int from = 0;
	String name;
	Double maxValue;
	Double minValue;

	public void printMessage(String msg) {
		Log.d("MSG GF", msg);

	}
	public GraphFragment() {
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		p = (Patient) getArguments().getSerializable("Patient");
		valueaList = getArguments().getStringArrayList("List");  //get values
		dateList = getArguments().getStringArrayList("DList");   // get date
		maxValue = getArguments().getDouble("MAX");
		minValue = getArguments().getDouble("MIN");
		name = getArguments().getString("NAME");
		from = getArguments().getInt("FROM");
		printMessage(p.Patient_id + " " + p.Name + " " + "Visit Size="
				+ valueaList.size());
	}

	@SuppressLint("UseValueOf")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.graphs, container,
				false);
		Button back = (Button) rootView.findViewById(R.id.backbutton);
		back.setOnClickListener(new OnClickListener() {
		@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				data.putInt("FROM", from);
				VisitFragment fragment = new VisitFragment();
				fragment.setArguments(data);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();
			}
		});
		GraphViewData[] gt = new GraphViewData[valueaList.size()];
		for (int i = 0; i < valueaList.size(); i++) {
			gt[i] = new GraphViewData(new Double(i + 1), new Double(
					valueaList.get(i)));
		}
		GraphViewSeries exampleSeries = new GraphViewSeries(gt);

		GraphView graphView;
		// graph with custom labels and drawBackground
		graphView = new LineGraphView(getActivity(), p.Name + " " + name);
		// custom static labels
		// graphView.addSeries(exampleSeries);
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graph1);
		String[] horlabel = new String[dateList.size()];
		for (int i = 0; i < dateList.size(); i++) {
			horlabel[i] = new String(dateList.get(i));
		}
		graphView.setHorizontalLabels(horlabel);
		graphView.setVerticalLabels(new String[] { String.valueOf(maxValue),
				String.valueOf(minValue) });
		graphView.addSeries(exampleSeries); // data
		layout.addView(graphView);
		return rootView;
	}
	public void Show() {

		Log.d("MSG", "In show Funtion");
	}
}
