package com.example.samplecapture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    CameraSurfaceView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout previewFrame = findViewById(R.id.previewFrame);
        cameraView = new CameraSurfaceView(this);
        previewFrame.addView(cameraView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void takePicture() {
        cameraView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    String outUriStr = MediaStore.Images.Media.insertImage(
                                        getContentResolver(),
                                        bitmap,
                                        "Captured Image",
                                        "Captured Image using Camera");
                    if(outUriStr == null) {
                        Log.d("SampleCapture", "Image insert failed");
                        return;
                    } else {
                        Uri outUri = Uri.parse(outUriStr);
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));
                    }
                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, @NonNull String[] strings) {
        Toast.makeText(this, "onDenied", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGranted(int i, @NonNull String[] strings) {
        Toast.makeText(this, "onGranted", Toast.LENGTH_SHORT).show();
    }


}