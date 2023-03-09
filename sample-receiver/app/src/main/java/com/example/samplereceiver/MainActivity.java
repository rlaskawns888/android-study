package com.example.samplereceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoPermissions.Companion.loadAllPermissions(this, 101);
        //모든 워험 권한을 자동 부여
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, @NonNull String[] permissions) {
        Toast.makeText(this, "permissions denied: " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, @NonNull String[] permissions) {
        Toast.makeText(this, "permissions granted: " + permissions.length, Toast.LENGTH_LONG).show();
    }
}