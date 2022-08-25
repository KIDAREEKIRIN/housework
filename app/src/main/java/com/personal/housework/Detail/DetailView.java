package com.personal.housework.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.personal.housework.DTO.HouseWork;
import com.personal.housework.R;
import com.personal.housework.Retrofit.GetDataService;
import com.personal.housework.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailView extends AppCompatActivity {

    // Layout 의 구성요소.
    EditText et_clothName, et_clothDesc;
    ImageView iv_clothPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        et_clothName = findViewById(R.id.et_clothName); // 가정일 이름.
        iv_clothPhoto = findViewById(R.id.iv_clothPhoto); // 가정일 관련 사진.
        et_clothDesc = findViewById(R.id.et_clothDesc); // 가정일 설명하는 부분.

        getSupportActionBar().setTitle(""); // 액션바 Title 없애기.


        // Detail_View 의 속성
        // 1. 양식에 맞게 (데이터베이스에 저장된) 데이터를 불러온다.
        // 2. 양식에 맞게 (데이터베이스에 알맞게) 데이터를 추가한다.

    }
}