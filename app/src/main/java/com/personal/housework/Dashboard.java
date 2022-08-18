package com.personal.housework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    CardView cv_houseWork1, cv_houseWork2, cv_houseWork3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cv_houseWork1 = findViewById(R.id.cv_houseWork1);
        cv_houseWork2 = findViewById(R.id.cv_houseWork2);
        cv_houseWork3 = findViewById(R.id.cv_houseWork3);

        clickHouseWork1();



    }

    private void clickHouseWork1() {
        cv_houseWork1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent house1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(house1);
            }
        });
    }
}