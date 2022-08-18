package com.personal.housework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CardView cv_clothWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 의생활 CardView
        cv_clothWork = findViewById(R.id.cv_clothWork);
        // 의생활 클릭 시,
        cv_clothWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cv_clothWorkIntent = new Intent(getApplicationContext(), WhatIsHouseWork.class);
                startActivity(cv_clothWorkIntent);
            }
        });
    }
}