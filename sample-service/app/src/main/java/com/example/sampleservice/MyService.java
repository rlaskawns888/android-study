package com.example.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() { }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService > onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService > onStartCommand()");

        if(intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "processCommand() -> command: " + command);
        Log.d(TAG, "processCommand() -> name: " + name);

        for(int i=0; i<5; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "Waiting" + i + " s.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}