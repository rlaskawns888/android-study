package com.example.sampletab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // setSupportActionBar()
        // 액티비티에 디폴트로 만들어진 액션바가 없을 경우에만 실행됨.
        // 그런데, 프로젝트가 생성될때 메인 엑티비티에는 자동으로 생성되어진다고함.
        // 이것은 (theme)를 액션바가 들어있는 테마로 설정했기 때문.
        // -> theme.xml 에서, Theme.AppCompat.Light.NoActionBar 으로 설정하면 액션바 사라짐.

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment1)
                .commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록"));
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭: " + position);

                Fragment selected = null;
                if(position == 0) {
                    selected = fragment1;
                } else if(position == 1) {
                    selected = fragment2;
                } else  if(position == 2) {
                    selected = fragment3;
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, selected)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}