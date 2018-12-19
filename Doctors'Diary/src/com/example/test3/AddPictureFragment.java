package com.example.test3;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.myclass.MyPicture;
import com.myclass.Patient;

import database.com.DatabaseHelper;

public class AddPictureFragment extends Fragment {

	Camera mCamera;
	CameraPreview mPreview;
	ImageButton captureButton;
	ImageView image;
	SharedPreferences app_preference;
	View rootView;
	Patient p;

	DatabaseHelper database;
	int from;
	String filename;

	public AddPictureFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		p = (Patient) getArguments().getSerializable("Patient");
		database = new DatabaseHelper(getActivity());
		from=getArguments().getInt("FROM");
		if(from !=0)
		{
			filename=getArguments().getString("FILENAME");
		}

	}

	public Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.addpicture, container, false);
		
		if(from!=0)
		{
		
		ImageView fullimage = (ImageView) rootView
				.findViewById(R.id.imageView);
		
		fullimage.setScaleType(ImageView.ScaleType.FIT_XY);
		try {
			Bitmap bitmap = BitmapFactory
					.decodeFile(filename);
			Bitmap out = Bitmap.createScaledBitmap(bitmap, 200, 200,
					false);
			fullimage.setImageBitmap(out);
		} catch (OutOfMemoryError E) {
			Log.d("ANDRO_ASYNC",
					String.format("catch Out Of Memory error"));
			// E.printStackTrace();
			System.gc();
		}
		}

		Button back = (Button) rootView.findViewById(R.id.backbutton);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Bundle data = new Bundle();

				data.putSerializable("Patient", p);

				data.putInt("FROM", 4);

				VisitFragment fragment = new VisitFragment();
				fragment.setArguments(data);

				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});
		mCamera = getCameraInstance();
		mPreview = new CameraPreview(getActivity(), mCamera);

		FrameLayout preview = (FrameLayout) rootView
				.findViewById(R.id.camera_preview);
		preview.addView(mPreview);

		app_preference = PreferenceManager
				.getDefaultSharedPreferences(getActivity());

		captureButton = (ImageButton) rootView
				.findViewById(R.id.button_capture);
		captureButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mCamera.takePicture(mShutter, raw, mPicture);
				mCamera.setPreviewCallback(previewCB);

			}
		});

		return rootView;

	}

	private PreviewCallback previewCB = new PreviewCallback() {

		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			// TODO Auto-generated method stub

		}
	};
	private PictureCallback raw = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub

		}
	};
	private PictureCallback mPicture = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub

			File picFile = getOutputMediaFile();
			if (picFile == null)
				return;

			try {
				// Toast.makeText(getApplicationContext(), data.length,
				// Toast.LENGTH_LONG).show();
				FileOutputStream fos = new FileOutputStream(picFile);

				fos.write(data);
				fos.flush();
				fos.close();

				SharedPreferences.Editor editor = app_preference.edit();
				editor.putString("image", picFile.getAbsolutePath());
				editor.commit();

				//image = (ImageView)rootView.findViewById(R.id.imageView1);
				// image.setImageURI(Uri.fromFile(picFile));
				
				ImageView fullimage = (ImageView) rootView
						.findViewById(R.id.imageView);
				
				fullimage.setScaleType(ImageView.ScaleType.FIT_XY);
				try {
					Bitmap bitmap = BitmapFactory
							.decodeFile(picFile.getAbsolutePath());
					Bitmap out = Bitmap.createScaledBitmap(bitmap, 200, 200,
							false);
					fullimage.setImageBitmap(out);
				} catch (OutOfMemoryError E) {
					Log.d("ANDRO_ASYNC",
							String.format("catch Out Of Memory error"));
					// E.printStackTrace();
					System.gc();
				}
				
				
				
              
				AddPictureFragment fragment = new AddPictureFragment();
				Bundle bdata = new Bundle();
				bdata.putSerializable("Patient", p);
				bdata.putInt("FROM", 1);
				bdata.putString("FILENAME",picFile.getAbsolutePath());
				fragment.setArguments(bdata);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	};
	private ShutterCallback mShutter = new ShutterCallback() {

		@Override
		public void onShutter() {
			// TODO Auto-generated method stub

		}
	};

	public File getOutputMediaFile() {
		File f;
		String mounted = Environment.getExternalStorageState();
		if (mounted.equalsIgnoreCase("mounted")) {
			f = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/DoctorPatient/" + p.Name + p.Mobile);
			if (!f.exists()) {
				if (!f.mkdir()) {
					return null;
				}
			}
		} else {
			return null;
		}
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String added_date = df.format(c.getTime());
		MyPicture ad = new MyPicture(p.Patient_id, p.visit_count, added_date,
				"No Comment", timeStamp);
		database.addPicture(ad);
		mediaFile = new File(f.getPath() + File.separator + p.Name + timeStamp
				+ ".jpg");
		return mediaFile;
	}

}
