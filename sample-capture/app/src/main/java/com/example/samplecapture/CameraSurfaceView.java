package com.example.samplecapture;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera camera = null;

    public CameraSurfaceView(Context context) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        camera = Camera.open();
//        setCameraOrientation();

        try {
            camera.setPreviewDisplay(mHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int i, int i1, int i2) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

//    public boolean capture(Camera.PictureCallback handler) {
//        if(camera != null) {
//            camera.takePicture(null, null, handler);
//            return true;
//        } else {
//            return false;
//        }
//    }

    public boolean capture(android.hardware.Camera.PictureCallback handler) {
        if(camera != null) {
            camera.takePicture(null, null, handler);
            return true;
        } else {
            return false;
        }
    }

//    public void setCameraOrientation() {
//        if(camera == null) {
//            return;
//        }
//        Camera.CameraInfo info = new Camera.CameraInfo();
//        Camera.getCameraInfo(0, info);
//
//        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        int rotation = manager.getDefaultDisplay().getRotation();
//
//        int degrees = 0;
//        switch (rotation) {
//            case Surface
//        }
//    }
}
