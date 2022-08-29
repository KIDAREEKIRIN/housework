package com.personal.housework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.personal.housework.CheckList.checkList;

public class Dashboard extends AppCompatActivity {

    CardView cv_houseWork1, cv_houseWork2, cv_houseWork3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cv_houseWork1 = findViewById(R.id.cv_houseWork1);
        cv_houseWork2 = findViewById(R.id.cv_houseWork2);
//        cv_houseWork3 = findViewById(R.id.cv_houseWork3);

        clickHouseWork1(); // 가정일 알아보기.
        clickHouseWork2(); // 가족구성원의 가정일 실천하기
    }

    private void clickHouseWork1() {
        cv_houseWork1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 가정일 알아보기 클릭 시, -> 가정일 전체 카테고리로 이동.
                Intent house1 = new Intent(getApplicationContext(), WhatIsHouseWork.class);
                startActivity(house1);
            }
        });
    }

    private void clickHouseWork2() {
        cv_houseWork2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent house2 = new Intent(getApplicationContext(), checkList.class);
                startActivity(house2);
            }
        });
    }
}