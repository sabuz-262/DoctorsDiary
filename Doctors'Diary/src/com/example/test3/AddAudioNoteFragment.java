package com.example.test3;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.myclass.AddConversation;
import com.myclass.Patient;

import database.com.DatabaseHelper;

public class AddAudioNoteFragment extends Fragment {

	MediaRecorder recorder;
	File audiofile = null;
	private static final String TAG = "SoundRecordingActivity";
	private View startButton;
	private View stopButton;
	DatabaseHelper database;

	Patient p;

	public void Print_Message(String msg) {
		Log.d("MSG AACF", msg);

	}

	public AddAudioNoteFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		database = new DatabaseHelper(getActivity());

		p = (Patient) getArguments().getSerializable("Patient");

		Print_Message(p.Patient_id + " " + p.Name + " " + "Visit count="
				+ p.visit_count);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("MSG", "ItemDetailFragment  createview");

		final View rootView = inflater.inflate(R.layout.addaudionote,
				container, false);

		Button back = (Button) rootView.findViewById(R.id.backbutton);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Bundle data = new Bundle();

				data.putSerializable("Patient", p);

				data.putInt("FROM", 5);

				VisitFragment fragment = new VisitFragment();
				fragment.setArguments(data);

				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		startButton = rootView.findViewById(R.id.start);
		stopButton = rootView.findViewById(R.id.stop);

		startButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				try {
					startRecording();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		stopButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				stopRecording();
			}
		});

		return rootView;

	}

	public void startRecording() throws IOException {

		startButton.setEnabled(false);
		stopButton.setEnabled(true);

		// File sampleDir = Environment.getExternalStorageDirectory();
		try {
			audiofile = File.createTempFile(p.Name, ".mp3",
					getOutputMediaFile());
		} catch (IOException e) {
			Log.e(TAG, "sdcard access error");
			return;
		}
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(audiofile.getAbsolutePath());
		recorder.prepare();
		recorder.start();

	}

	public File getOutputMediaFile() {
		File f;

		f = new File(Environment.getExternalStorageDirectory()
				.getAbsoluteFile() + "/DoctorPatient");
		if (!f.exists()) {
			if (!f.mkdir()) {
				return null;
			} else {
				return f;
			}
		} else {
			return f;
		}

	}

	public void stopRecording() {
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
		recorder.stop();
		recorder.release();
		addRecordingToMediaLibrary();
	}

	protected void addRecordingToMediaLibrary() {
		ContentValues values = new ContentValues(4);
		long current = System.currentTimeMillis();
		values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
		values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
		values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp3");
		values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());
		ContentResolver contentResolver = getActivity().getBaseContext()
				.getContentResolver();

		Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Uri newUri = contentResolver.insert(base, values);

		getActivity().getBaseContext().sendBroadcast(
				new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));

		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String date = df.format(c.getTime());

		AddConversation ad = new AddConversation(p.Patient_id, p.visit_count,
				date, audiofile.getName());
		database.addAudoNote(ad);
		Print_Message(ad.filename);

		Toast.makeText(getActivity(), "Added File " + newUri, Toast.LENGTH_LONG)
				.show();
	}
}
