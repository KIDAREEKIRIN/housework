package com.personal.housework.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.personal.housework.R;

public class DetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        // Detail_View 의 속성
        // 1. 양식에 맞게 (데이터베이스에 저장된) 데이터를 불러온다.
        // 2. 양식에 맞게 (데이터베이스에 알맞게) 데이터를 추가한다.
    }
}