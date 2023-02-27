package com.example.actionbarproejct3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);
        // XML로 정의된 메뉴 정보를 인플레이션하여 메모리에 로딩

        View v = menu.findItem(R.id.menu_search).getActionView();
        // 메뉴 아이템 중엥서 검색을 위해 정의한 아이템을 뷰 객체로 참조
        // - null일 경우 app:actionViewClass="android.widget.SearchView" 추가
        if(v != null) {
            EditText editText = v.findViewById(R.id.editText);
            // null
            if(editText != null) {
                // 입력상자 객체에 리스너 설정
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Toast.makeText(getApplicationContext(), "입력됨.", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        }

        return true;
    }
    //해당 메서드는 액티비티가 만들어질 떄 미리 자동 호출되어 화면에 메뉴 기능을 추가할 수 있도록 합니다.

}