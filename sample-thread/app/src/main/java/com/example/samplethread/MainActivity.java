package com.example.samplethread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView;

//    MainHandler handler;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
    }

    class BackgroundThread extends Thread {
        int value = 0;

        @Override
        public void run() {
            for(int i=0; i<100; i++) {
                try {
                  Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                value += 1;

//                Message message = handler.obtainMessage();
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("value", value);
//                message.setData(bundle);
//
//                handler.sendMessage(message);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("value 값: " + value);
                    }
                });

            }
        }
    }

//    class MainHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//
//            Bundle bundle = msg.getData();
//            int value = bundle.getInt("value");
//            textView.setText("value 값: " + value);
//        }
//    }
}
