package com.example.sampleservice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTxt);

        Button button = findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", nm);
                //인텐트 객체에 전달할 값 저장

                startService(intent);
                //서비스 시작
            }
        });

        //액티비티가 새로 만들어질 때 전달될 인텐트 처리
        Intent passedIntent = getIntent();
        passedIntent(passedIntent);
    }

    //액티비티 메모라가 생성되어있을 경우 호출됨.
    @Override
    protected void onNewIntent(Intent intent) {
        passedIntent(intent);
        super.onNewIntent(intent);
        //액티비티가 이미 만들어져 있을 때 전달된 인텐트 처리
    }

    private void passedIntent(Intent intent) {
        if(null != intent) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "command: " + command + ", name: " + name, Toast.LENGTH_LONG).show();
        }
    }
}