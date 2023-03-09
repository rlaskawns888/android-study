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

        //ex1)
//        for(int i=0; i<5; i++) {
//            try {
//                Thread.sleep(1000);
//                Log.d(TAG, "Waiting" + i + " s.");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        //ex2)
        //MainAcitivty에서 Service로 전달받은 데이터를
        //다시 Service에서 MainAcivity로 전달.
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //FLAG_ACTIVITY_NEW_TASK: 새로운 테스트그를 생성하도록 플래그 생성
        // (서비스는 화면이 없기 때문에 화면이 없는 서비스에서 화면이 있는 액티비를
        // 띄우려면 새로운 태스크 생성이 필요함)

        //FLAG_ACTIVITY_SINGLE_TOP, FLAG_ACTIVITY_CLEAR_TOP
        //: MainAcitivty가 이미 메모리가 있을 때 재사용하도록 위의 플래그 2개를 추가함.

        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent);
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