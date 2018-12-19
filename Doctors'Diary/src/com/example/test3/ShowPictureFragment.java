package com.example.test3;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;



import com.myclass.AddConversation;
import com.myclass.MyPicture;
import com.myclass.Patient;

import database.com.DatabaseHelper;

public class ShowPictureFragment extends Fragment {

	DatabaseHelper database;
	Patient p;
	Uri imagesUri;
	ArrayList<String> picturenamelist;
	ArrayList<MyPicture> mypicturelist;

	View testview;
	File[] sdDirFiles;
	File sdDir;

	Boolean flag = false;

	public void Print_Message(String msg) {
		Log.d("MSG SPF", msg);

	}

	public ShowPictureFragment() {
	}

	@SuppressWarnings("unused")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		picturenamelist = new ArrayList<String>();
		mypicturelist = new ArrayList<MyPicture>();
		database = new DatabaseHelper(getActivity());

		p = (Patient) getArguments().getSerializable("Patient");
		Print_Message(p.Patient_id + " " + p.Name + " " + "Visit count="
				+ p.visit_count);

		File sdDir = getOutputMediaFile();

		sdDirFiles = sdDir.listFiles();
		// Toast_Show("file in "+sdDirFiles.length);

	}

	public File getOutputMediaFile() {
		File f;

		f = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/DoctorPatient/" + p.Name + p.Mobile);
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

	void Toast_Show(String msg) {
		Toast.makeText(getActivity().getBaseContext(), msg, Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.picturegallery,
				container, false);

		testview = inflater.inflate(R.layout.picturegallery, container, false);

		GridView picturelist = (GridView) rootView
				.findViewById(R.id.picturelist);

		picturelist.setAdapter(new ImageAdapter(getActivity()));

		picturelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					final int position, long id) {
				LayoutInflater layoutInflater = (LayoutInflater) getActivity()
						.getBaseContext().getSystemService(
								Context.LAYOUT_INFLATER_SERVICE);
				final View popupView = layoutInflater.inflate(
						R.layout.fullimage, null);
				final PopupWindow popupWindow = new PopupWindow(popupView, 800,
						680, true);

				popupWindow.showAtLocation(rootView, Gravity.CENTER, 120, 30);

				final TextView commenttextview = (TextView) popupView
						.findViewById(R.id.comment);
				String comment = Get_Comment(sdDirFiles[position].getName());

				if (comment != null) {
					commenttextview.setText(comment);
					Print_Message(comment);
				} else {
					Print_Message("Comment is null "
							+ sdDirFiles[position].getName());
				}

				
				Button editbutton = (Button) popupView
						.findViewById(R.id.editbutton);
				editbutton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						EditText commenteditext = (EditText) popupView
								.findViewById(R.id.editcomment);
						if (!flag) {

							commenteditext.setVisibility(1);
							flag = true;
						} else {
							commenttextview.setText(commenteditext.getText()
									.toString());
							Update_comment(sdDirFiles[position].getName(), commenteditext
									.getText().toString());
							commenteditext.setVisibility(0);
							flag = false;

						}

					}
				});

				ImageView fullimage = (ImageView) popupView
						.findViewById(R.id.fulliamge);
				// fullimage.setImageURI(Uri.fromFile(sdDirFiles[position]));
				fullimage.setScaleType(ImageView.ScaleType.FIT_XY);
				try {
					Bitmap bitmap = BitmapFactory
							.decodeFile(sdDirFiles[position].getAbsolutePath());
					Bitmap out = Bitmap.createScaledBitmap(bitmap, 320, 480,
							false);
					fullimage.setImageBitmap(out);
				} catch (OutOfMemoryError E) {
					Log.d("ANDRO_ASYNC",
							String.format("catch Out Of Memory error"));
					// E.printStackTrace();
					System.gc();
				}

				Button finish = (Button) popupView.findViewById(R.id.finish);
				finish.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						popupWindow.dismiss();

					}
				});

			}
		});

		Button addphoto = (Button) rootView.findViewById(R.id.addpicture);
		addphoto.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				AddPictureFragment fragment = new AddPictureFragment();
				Bundle data = new Bundle();
				data.putSerializable("Patient", p);
				data.putInt("FROM",0);
				fragment.setArguments(data);
				getFragmentManager().beginTransaction()
						.replace(R.id.item_detail_container, fragment).commit();

			}
		});

		return rootView;

	}

	public String Get_Comment(String argfilename) {
		Cursor c = database.GetPictureListByPatientId(p.Patient_id);
		String retcomment="";

		getActivity().startManagingCursor(c);
		if (c != null) {
			c.moveToFirst();
			while (!c.isAfterLast()) {
				int id = c.getInt(0);

				int visit_id = c.getInt(1);

				int p_id = c.getInt(2);

				String date = c.getString(3);
				String comment = c.getString(4);
				String filename = p.Name + c.getString(5) + ".jpg";
				picturenamelist.add(filename);
				
				if(filename.equals(argfilename))
				{
					retcomment= comment;
				}

				Print_Message(filename + " " + comment);
				MyPicture ad = new MyPicture(id, p_id, visit_id, date, comment,
						filename);

				mypicturelist.add(ad);
				c.moveToNext();

			}

		}

		

		return retcomment;

	}

	public void Update_comment(String argfilename, String comment) {
		
		int id=0;
		for(int i=0;i<mypicturelist.size();i++)
		{
			if(mypicturelist.get(i).filename.equals(argfilename))
			{
				id=mypicturelist.get(i).id;
			}
		}
		
		database.Update_PictureComment(id, comment);
		Toast_Show("Update Comment");
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {

			mContext = c;

		}

		public int getCount() {
			return sdDirFiles.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(128, 128));
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);

			} else {
				imageView = (ImageView) convertView;
			}

			try {
				Bitmap bitmap = BitmapFactory.decodeFile(sdDirFiles[position]
						.getAbsolutePath());
				Bitmap out = Bitmap.createScaledBitmap(bitmap, 320, 480, false);
				imageView.setImageBitmap(out);
			} catch (OutOfMemoryError E) {
				Log.d("ANDRO_ASYNC", String.format("catch Out Of Memory error"));
				// E.printStackTrace();
				System.gc();
			}
			// imageView.setImageURI(Uri.fromFile(sdDirFiles[position]));

			return imageView;
		}

	}

}
