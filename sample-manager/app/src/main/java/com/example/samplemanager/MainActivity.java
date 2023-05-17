package com.example.samplemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        //앱 프로세스 리스트 조회
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getServiceList();
            }
        });

        //현재 액티비티 정보 조회
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentActivity();
            }
        });

        //앱 리스트 조회
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAppList();
            }
        });

        //지정한 액티비티 설치 여부
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findActivity();
            }
        });

        //알림 받기
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });
    }

    public void getServiceList() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();

        for(int i = 0; i < processInfoList.size(); i++) {
            ActivityManager.RunningAppProcessInfo info = processInfoList.get(i);
            println("#" + i + " ->" + info.pid + ", " + info.processName);
        }
    }

    public void getCurrentActivity() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = manager.getRunningTasks(1);

        ActivityManager.RunningTaskInfo info = taskList.get(0);
        println("Running Task -> " + info.topActivity.toString());
    }

    public void getAppList() {
        PackageManager manager = getPackageManager();
        List<ApplicationInfo> appInfoList = manager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (int i = 0; i < appInfoList.size(); i++) {
            ApplicationInfo info = appInfoList.get(i);
            println("#" + i + " -> " + info.loadLabel(manager).toString() + ", " + info.packageName);
        }
    }

    public void findActivity() {
        PackageManager manager = getPackageManager();

        Intent intent = new Intent(this, MainActivity.class);
        List<ResolveInfo> activityInfoList = manager.queryIntentActivities(intent, 0);

        for (int i = 0; i < activityInfoList.size(); i++) {
            ResolveInfo info = activityInfoList.get(i);
            println("#" + i + " -> " + info.activityInfo.applicationInfo.packageName);
        }
    }

    public void setAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_IMMUTABLE);

        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 60000, pendingIntent);
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}