package com.example.test3;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements
		SurfaceHolder.Callback {

	Camera mCamera;
	SurfaceHolder mHolder;

	Parameters params;

	public CameraPreview(Context context, Camera camera) {
		super(context);
		// TODO Auto-generated constructor stub
		mCamera = camera;
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
		if (mHolder.getSurface() == null) {
			return;
		}

		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		params = mCamera.getParameters();
		params.setFlashMode(Parameters.FLASH_MODE_ON);
		params.setFocusMode(Parameters.FOCUS_MODE_AUTO);
		mCamera.setParameters(params);

		try {
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

		params = mCamera.getParameters();
		params.setFlashMode(Parameters.FLASH_MODE_OFF);

		mCamera.setParameters(params);

		mCamera.release();

	}

}
