package com.example.samplepager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.activity_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        //미리 로딩해 놓을 아이템 개수를 3개로 늘림.
        //뷰페이저는 어댑터가 가지고 있는 아이템 중에서 몇 개를 미리 로딩해 두었다가
        //좌우 스크롤할 때 빠르게 보여줄수 있다.
        //해당 값이 처음에는 3보다 적게 설정되어 있기 때문에 3으로 설정해둠.

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        //프래그먼트 추가 메서드
        public void addItem(Fragment item) {
            items.add(item);
        }

        //프래그먼트를 가져올수있는 메서드
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        //프래그먼트 개수 확인
        @Override
        public int getCount() {
            return items.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "PAGE: " + position;
        }
    }
}