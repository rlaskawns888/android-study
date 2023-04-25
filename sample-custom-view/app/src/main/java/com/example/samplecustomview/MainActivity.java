package com.example.samplecustomview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samplecustomview.custom.CustomView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        CustomView view = new CustomView(this);
        setContentView(view);
    }
}