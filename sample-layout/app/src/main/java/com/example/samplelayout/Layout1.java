package com.example.samplelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {
    //LinearLayout 클래스를 상속하여 클래스 정의

    ImageView imageView;
    TextView textView;
    TextView textView2;

    public Layout1(Context context) {
        super(context);
        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //인플레이션 정의하기

        inflater.inflate(R.layout.layout1, this, true);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        //레이아웃 정의
    }

    //뷰에 데이터 설정
    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

    //뷰에 데이터 설정
    public void setName(String name) {
        textView.setText(name);
    }

    //뷰에 데이터 설정
    public void setMobile(String mobile) {
        textView2.setText(mobile);
    }
}
